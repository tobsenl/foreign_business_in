package cn.com.jnpc.foreign.utils;
/**
 * <p>Title: ���������ɾ���ĵ�Java Bean</p>
 * <p>Description: ������������������Bean��ֻ������ݣ���ҵ���޹أ�Ҳ������������Ŀ�������öȼ��ߣ�</p>
 * <p>Company: /p>
 * @author
 * @version 1.0
 */

import java.io.InputStream;
import java.sql.*;

import javax.sql.*;
import javax.naming.*;



import com.jnpc.util.Debug;

import java.util.*;


public class Oracle {
	
	private String ds = "jdbc/intrawebnew";

 
  /**
   * 插入含blob的数据
   * @param sql
   * @param sis
   * @param length
   * @return
   */
  public int  insertBlob(String sql, InputStream sis,int length) {
	    Connection conn = getConnection();
//	    ResultSet rs = null;
	    PreparedStatement pstmt = null;
	    int result=0;
	    try {
	     
	      conn.setAutoCommit(false);
	      pstmt = conn.prepareStatement(sql);
	      pstmt.clearParameters();
	      pstmt.setBinaryStream(1, sis,length);
	      pstmt.executeUpdate();
	      
	      conn.commit();
	      conn.setAutoCommit(true);
	      return result ;
	    } catch (Exception e1) {
	       Debug.print_log(e1,sql);
	       try {
			conn.rollback();
			conn.setAutoCommit(true);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
//			e2.printStackTrace();
			Debug.print_log(e2,sql);
		}
		return -1;
	    
	    } finally { 
	        this.disconnect( pstmt, conn);
	    }
	  }

  /**
   * 完成数据库的增删改操作，要求传入的sql语句必须为insert,update或delete
   * 有返回值，-1表示操作不成功，0表示没有更新行，正整数代表更新的行数
   * @param sql
   */
  public int update(String sql) {
    Connection conn = getConnection();
    PreparedStatement  psmt = null;
    int result = 0;
    try {
      psmt = conn.prepareStatement(sql);
      conn.setAutoCommit(false);
      result = psmt.executeUpdate();

      conn.commit();
      conn.setAutoCommit(true);
      return result;
    } catch(SQLException e1) {
      Debug.print_log(e1,sql);
      try{
        conn.rollback();
        conn.setAutoCommit(true);
        return -1;
      } catch(SQLException e2) {
        Debug.print_log(e2,sql);
        return -1;
      }
    } finally {
      this.disconnect(psmt, conn);
    }
  }

  /**
   * ����һ��sql��䣬�����ݿ����ɾ�Ĳ���
   *�з���ֵ��-1��ʾ����ɹ���0��ʾû�и����У�����������µ�����
   * Ҫ�����sql������Ϊinsert,update��delete��
   * ������sql������һ��Transaction
   * @param sqlArr
   */
  public boolean update(String[] sqlArr) {
    Connection conn = getConnection();
    PreparedStatement  psmt = null;
    int[] result ;
    boolean flag=false;
    try {
     
      conn.setAutoCommit(false);
      for (int i = 0; i < sqlArr.length; i++) {
        // ����Ǹ��º�ɾ������¼����־�ļ�
        //if ( (sqlArr[i].toLowerCase().trim().startsWith("update") ||
         //     sqlArr[i].toLowerCase().trim().startsWith("delete"))) {
        //  RecordLog.println(sqlArr[i]);
       // }
    	  psmt = conn.prepareStatement(sqlArr[i]);
    	  psmt.addBatch();
//        result += psmt.executeUpdate(sqlArr[i]);
      }
      result=psmt.executeBatch();
      
//  	psmt.close();
	for (int j = 0; j < result.length; j++) {
		if (result[j] == -3) {
			flag = false;
			conn.rollback();
			// tx.rollback();
			break;
		}
	}
	if (flag) {
		conn.commit();
		 conn.setAutoCommit(true);
	}

      return flag;
    } catch(SQLException e1) {
      Debug.print_log(e1,sqlArr[0]);
      try{
        conn.rollback();
        conn.setAutoCommit(true);
        return false;
      } catch(SQLException e2) {
        Debug.print_log(e2,sqlArr[0]);
        return false;
      }
    } finally {
      this.disconnect(psmt, conn);
    }

  }



  /**
   * ����Blob���Ͳ�ѯ���
   * @param sql
   * @param columnName
   * @return
   */
  public Blob getBlob(String sql, String columnName) {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement psmt = null;
    Blob blob = null;
    try {
      conn = getConnection();
      psmt = conn.prepareStatement(sql);
      rs = psmt.executeQuery(sql);
      while (rs.next()) {
        blob = rs.getBlob(columnName);
      }
    } catch(Exception e) {
      Debug.print_log(e,sql);
    } finally {
      this.disconnect(rs, psmt, conn);
    }
    return blob;
  }

  /**
   * 返回一行一列查询结果
   * @param sql
   * @param columnName
   * @return
   */
  public String getSnglRowSnglCol(String sql, String columnName) {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement psmt = null;
    String str = "";
    try {
      conn = getConnection();
      psmt = conn.prepareStatement(sql);
      rs = psmt.executeQuery();
      ResultSetMetaData rsmd = rs.getMetaData();
      String colType = rsmd.getColumnTypeName(1);
      while (rs.next()) {
        if (colType.equals("CLOB")) {
          Clob clob = rs.getClob(columnName);
          str = clob.getSubString(1,(int)clob.length());
        } else {
          str = rs.getString(columnName);
        }
      }
    } catch(Exception e) {
      Debug.print_log(e,sql);
    } finally {
      this.disconnect(rs, psmt, conn);
    }
    return str;
  }
  /**
   * 返回一行一列查询结果
   * @param sqlList
   * @param columnNameList
   * @return
   */
  public String[][] getSnglRowSnglCol(List<String> sqlList, List<String> columnNameList,List<String> keyList) {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement psmt = null;
    Vector v = new Vector();
    StringBuilder sb=new StringBuilder();
    StringBuilder value=new StringBuilder();
    String[][] val=new String[sqlList.size()][2];
    try {
      conn = getConnection();
      
      for (int i = 0; i < sqlList.size(); i++) {		
    	  sb.append(sqlList.get(i));
    	  psmt = conn.prepareStatement(sb.toString());
          rs = psmt.executeQuery();
          ResultSetMetaData rsmd = rs.getMetaData();
          String colType = rsmd.getColumnTypeName(1);
          while (rs.next()) {
            if (colType.equals("CLOB")) {
            	Clob clob = rs.getClob(columnNameList.get(i));
            	value.append(clob.getSubString(1,(int)clob.length()));
            } else {
            	value.append( rs.getString(columnNameList.get(i)));
            }
          }
          val[i][0]=keyList.get(i);
          val[i][1]=value.toString();
          value.delete(0, value.length());
          sb.delete(0, sb.length());
          rs.close();
		  psmt.close();
      }
      
    } catch(Exception e) {
    e.printStackTrace() ;
      Debug.print_log(e,sb.toString());
    } finally {
      this.disconnect(rs, psmt, conn);
    }
    return val;
  }
  /**
   * 返回一行多列查询结果
   * @param sql
   * @param columnName
   * @return
   */
  public String[] getSnglRowMultiCol(String sql, String[] colArr) {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement psmt = null;
    Vector v = new Vector();
    String[] colType = new String[colArr.length];
    try {
      conn = getConnection();
      psmt = conn.prepareStatement(sql);
      rs = psmt.executeQuery();
      ResultSetMetaData rsmd = rs.getMetaData();
      for (int i = 1; i <= rsmd.getColumnCount(); i++) {
        colType[i-1] = rsmd.getColumnTypeName(i);
      }
      while (rs.next()) {
        for (int i = 0; i < colArr.length; i++) {
          if (colType[i].equals("CLOB")) {
            Clob clob = rs.getClob(colArr[i]);
            v.addElement(clob.getSubString(1,(int)clob.length()));
          } else {
            v.addElement(rs.getString(colArr[i]));
          }
        }
      }
    } catch(Exception e) {
      Debug.print_log(e,sql);
    } finally {
      this.disconnect(rs, psmt, conn);
    }
    return this.cvtVtrToArr(v);
  }

  /**
   * 返回多行一列查询结果
   * @param sql
   * @param columnName
   * @return
   */
  public String[] getMultiRowSnglCol(String sql, String columnName) {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement psmt = null;
    Vector v = new Vector();
    try {
      conn = getConnection();
      psmt = conn.prepareStatement(sql);
      rs = psmt.executeQuery();
      ResultSetMetaData rsmd = rs.getMetaData();
      String colType = rsmd.getColumnTypeName(1);
      while (rs.next()) {
        if (colType.equals("CLOB")) {
          Clob clob = rs.getClob(columnName);
          v.addElement(clob.getSubString(1,(int)clob.length()));
        } else {
          v.addElement(rs.getString(columnName));
        }
      }
    } catch(Exception e) {
//    System.out.println(""+e);
    e.printStackTrace() ;
      Debug.print_log(e,sql);
    } finally {
      this.disconnect(rs, psmt, conn);
    }
    return this.cvtVtrToArr(v);
  }
  

  
  

  /**
   * 返回多行多列查询结果
   * @param sql
   * @param colArr
   * @return
   */
  public String[][] getMultiRowMultiCol(String sql, String colArr[]) {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement psmt = null;
    Vector v = new Vector();
    int colCount = colArr.length;
    String[] colType = new String[colCount];
    try {
      conn = getConnection();
      psmt = conn.prepareStatement(sql);
      rs = psmt.executeQuery();
      ResultSetMetaData rsmd = rs.getMetaData();
      for (int i = 1; i <= rsmd.getColumnCount(); i++) {
        colType[i-1] = rsmd.getColumnTypeName(i);
      }
      while (rs.next()) {
        String[] arr = new String[colCount];
        for (int i = 0; i < colCount; i++) {
          if (colType[i].equals("CLOB")) {
            Clob clob = rs.getClob(colArr[i]);
            arr[i] = clob.getSubString(1,(int)clob.length());
          } else {
          try{
            arr[i] = rs.getString(colArr[i]);
           }catch(Exception e) {
      Debug.print_log(e,sql+colArr[i]);
    }
          }
        }
        v.addElement(arr);
      }
    } catch(Exception e) {
//    	System.out.println("=="+e);
    	e.printStackTrace();
      Debug.print_log(e,sql);
    } finally {
      this.disconnect(rs, psmt, conn);
    }
    return this.cvtVtrToArr(v, colCount);
  }

  /** 返回多行多列查询结果
  * @param sql
  * @param colArr
  * @return
  */
 public Map<String, String[][] > getMultiRowMultiCol(Map<String,String> sqlMap, Map<String,String[]> colArrMap) {
   Connection conn = null;
   ResultSet rs = null;
   PreparedStatement psmt = null;
   Vector v = new Vector();;
   Map<String, String[][] > map=new HashMap<String, String[][]>();
   StringBuilder sb=new StringBuilder();
   
	   try {
		   conn = getConnection();
		   for (Map.Entry<String, String> entry:sqlMap.entrySet()) {
			 v.clear();
			 int colCount =  colArrMap.get( entry.getKey()).length;
			 String[] colType = new String[colCount];
			 String[] colArr= colArrMap.get( entry.getKey());
			 sb.append(entry.getValue());
		   
		     psmt = conn.prepareStatement(sb.toString());
		     rs = psmt.executeQuery();
		     ResultSetMetaData rsmd = rs.getMetaData();
		     for (int i = 1; i <= rsmd.getColumnCount(); i++) {
		       colType[i-1] = rsmd.getColumnTypeName(i);
		     }
		     while (rs.next()) {
		       String[] arr = new String[colCount];
		       for (int i = 0; i < colCount; i++) {
		         if (colType[i].equals("CLOB")) {
		           Clob clob = rs.getClob(colArr[i]);
		           arr[i] = clob.getSubString(1,(int)clob.length());
		         } else {
		         try{
		           arr[i] = rs.getString(colArr[i]);
		          }catch(Exception e) {
		        	  Debug.print_log(e,sb+colArr[i]);
		          }
		         }
		       }
		       v.addElement(arr);
//		       String [][] strs=this.cvtVtrToArr(v, colCount);
		     }
		     map.put(entry.getKey(), this.cvtVtrToArr(v, colCount)) ;
		     rs.close();
		     psmt.close();
//		     rs=null;
//		     psmt=null;
		     sb.delete(0, sb.length());
		   }
	   } catch(Exception e) {
//	   	System.out.println("=="+e);
	   	e.printStackTrace();
	     Debug.print_log(e,sb.toString());
	   } finally {
	     this.disconnect(rs, psmt, conn);
	   }
  
  
   return map;//
 }
  
  /**
   * ���ؼ�¼����Ŀ
   * @param sql
   * @return
   */
  public int getRecordCount(String sql) {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement psmt = null;
    int count = 0;
    try {
      conn = getConnection();
      psmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      rs   = psmt.executeQuery();
      if (rs != null) {
        if (rs.next()) {
          rs.last();
          count = rs.getRow();
        }
      }
    } catch(Exception e) {
      Debug.print_log(e,sql);
    } finally {
      this.disconnect(rs, psmt, conn);
    }
    return count;
  }

  /**
   * ����sql������õ����ֶε��������
   * @param sql
   * @return
   */
  public String[] getColNames(String sql) {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement psmt = null;
    Vector v = new Vector();
    StringBuffer s = new StringBuffer();
    try {
      conn = getConnection();
      psmt = conn.prepareStatement(sql);
      rs   = psmt.executeQuery();
      ResultSetMetaData rsmd = rs.getMetaData();
      for (int i = 1; i <= rsmd.getColumnCount(); i++) {
        v.addElement(rsmd.getColumnName(i));
      }
    } catch(Exception e) {
      Debug.print_log(e,sql);
    } finally {
      this.disconnect(rs, psmt, conn);
    }
    return this.cvtVtrToArr(v);
  }

  /**
   * �����ֶζ�Ӧ���ֶ�����
   * @param tblName ����
   * @param Id ���Id�ֶ�
   * @param names �ֶ���ɵ���
   * @return
   */
  public String[] getColTypes(String tblName, String[] colNames) {
    Connection conn = null;
    ResultSet rs = null;
    Statement stmt = null;
    StringBuffer strBuff = new StringBuffer();
    int k = 0;
    String[] colTypes = new String[colNames.length];
    String[] selColName = {"column_name","data_type"};
    strBuff.append("select column_name,data_type from user_tab_columns where table_name='");
    strBuff.append(tblName.toUpperCase());
    strBuff.append("' and (column_name in (");
    for (int i = 0; i < colNames.length; i++) {
      strBuff.append("'");
      strBuff.append(colNames[i].toUpperCase());
      strBuff.append("',");
    }
    strBuff.deleteCharAt(strBuff.length()-1);
    strBuff.append("))");
    String sql = strBuff.toString();
    // �ֶ��������飬��Id�ֶ�
    String[][] colNameType = this.getMultiRowMultiCol(sql,selColName);
    // �Ѵ�����ֵ��в�ѯ����ֶ������ֶ����������в鵽���봫����ֶ����������Ӧ���ֶ����ͷ����µ��ֶ�����������
    for (int i = 0; i < colNames.length; i++) {
      for (int j = 0; j < colNameType.length; j++) {
        if (colNames[i].toUpperCase().equals(colNameType[j][0])) {
          colTypes[k] = colNameType[j][1];
          k++;
        }
      }
    }
    return colTypes;
  }

  /**
   * ���ر�����е��ֶ��������
   * @param tblName ����
   * @return
   */
  public String[][] getColumnsTypes(String tblName) {
    Connection conn = null;
    ResultSet rs = null;
    Statement stmt = null;
    StringBuffer strBuff = new StringBuffer();
    strBuff.append("select column_name,data_type from user_tab_columns where table_name='");
    strBuff.append(tblName.toUpperCase());
    strBuff.append("'");
    String sql = strBuff.toString();
    String[] colArr = this.getColNames(sql);
    String[][] ColumnsTypes = this.getMultiRowMultiCol(sql, colArr);
    return ColumnsTypes;
  }

  /**
   * �������б�����е��ֶΣ����ձ���Ⱥ�˳��l��
   * @param tblName ��������
   * @return  ���еı����ֶ�
   */
  public String[] getColumns(String[] tblName) {
    String[] result = null;
    Vector colNames = new Vector();
    Oracle oracle = new Oracle();
    for (int i = 0; i < tblName.length; i++) {
      result = oracle.getColumns(tblName[i]);
      for (int j = 0; j < result.length; j++) {
        colNames.addElement(result[j]);
      }
    }
    result = oracle.cvtVtrToArr(colNames);
    return result;
  }

  /**
   * ���ر�����е��ֶ�
   * @param tblName ����
   * @return
   */
  public String[] getColumns(String tblName) {
    Connection conn = null;
    ResultSet rs = null;
    Statement stmt = null;
    StringBuffer strBuff = new StringBuffer();
    strBuff.append("select column_name from user_tab_columns where table_name='");
    strBuff.append(tblName.toUpperCase());
    strBuff.append("'");
    String sql = strBuff.toString();
    String[] columns = this.getMultiRowSnglCol(sql, "COLUMN_NAME");
    return columns;
  }


  /**
   * ���ر�����е�����ֶ�
   * @param tblName ����
   * @return����ֶ�����
   */
  public String[] getPKColumns(String tblName) {
    Connection conn = null;
    ResultSet rs = null;
    Statement stmt = null;
    StringBuffer strBuff = new StringBuffer();
    strBuff.append("select column_name from user_cons_columns a,user_constraints b where a.constraint_name=b.constraint_name and constraint_type='P' and table_name='");
    strBuff.append(tblName.toUpperCase());
    strBuff.append("'");
    String sql = strBuff.toString();
    String[] columns = this.getMultiRowSnglCol(sql, "COLUMN_NAME");
    return columns;
  }

  /**
   * ���ر�����еķ�����ֶ�
   * @param tblName ����
   * @return������ֶ�����
   */
  public String[] getNotPKColumns(String tblName) {
    Connection conn = null;
    ResultSet rs = null;
    Statement stmt = null;
    StringBuffer strBuff = new StringBuffer();
    strBuff.append("select column_name from user_cons_columns a,user_constraints b where a.constraint_name=b.constraint_name and constraint_type<>'P' and table_name='");
    strBuff.append(tblName.toUpperCase());
    strBuff.append("'");
    String sql = strBuff.toString();
    String[] columns = this.getMultiRowSnglCol(sql, "COLUMN_NAME");
    return columns;
  }

  /**
   * ��Vectorת��Ϊһ��һά����
   * @param v
   * @param colCount
   * @return
   */
  public String[] cvtVtrToArr(Vector v) {
    Object[] obj = v.toArray();
    int rowCount = obj.length;
    String[] arr = new String[rowCount];
    for (int i = 0; i < rowCount; i++) {
      arr[i] = (String)obj[i];
    }
    return arr;
  }

  /**
   * ��Vectorת��Ϊһ���ά����
   * @param v
   * @param colCount
   * @return
   */
  public String[][] cvtVtrToArr(Vector v, int colCount) {
    Object[] obj = v.toArray();
    int rowCount = obj.length;
    String[][] arr = new String[rowCount][colCount];
    for (int i = 0; i < rowCount; i++) {
      arr[i] = (String[])obj[i];
    }
    return arr;
  }

  /**
   * �������е�nullת��Ϊ""��������ת���������
   * @param arr
   */
  public String[] cvtNullToBlank(String[] arr) {
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == null) {
        arr[i] = "";
      }
    }
    return arr;
  }

  /**
   * �������е�nullת��Ϊ""��������ת���������
   * @param arr
   */
  public String[][] cvtNullToBlank(String[][] arr) {
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[i].length; j++) {
        if (arr[i][j] == null) {
          arr[i][j] = "";
        }
      }
    }
    return arr;
  }

  /**
   * �Ͽ�����ݿ��l��
   * @param rs
   * @param stmt
   * @param conn
   */
  public void disconnect(ResultSet rs, PreparedStatement psmt, Connection conn) {
	
    try {
    	
      if(rs != null){
        rs.close();
        rs=null;
      }
    }catch(Exception e1){
        Debug.print_log(e1);
    }try{
      if(psmt != null){
    	  psmt.close();
    	  psmt=null;
      }
    }catch(Exception e2){
        Debug.print_log(e2);
    }try{
      if(conn != null ){
        conn.close();
        conn=null;
      }
    } catch (Exception e3) {
      Debug.print_log(e3);
    }
  }

  /**
   * �Ͽ�����ݿ��l��
   * @param psmt
   * @param conn
   */
  public void disconnect(PreparedStatement psmt, Connection conn) {
    try {
        if(psmt != null){
          psmt.close();
          psmt=null;
        }
    } catch (Exception e1) {
      Debug.print_log(e1);
    }try{
       if(conn != null ){
        conn.close();
        conn=null;
      }
    } catch (Exception e2) {
      Debug.print_log(e2);
    }
  }

  /**
   * ��l�ӳ��з���һ����ݿ�l��
   * @return
   */
  public Connection getConnection() {
   
    Connection connection = null;
    InitialContext initContext = null;
    try {
      initContext = new InitialContext();
     
      String strDataSourceName = ds;
      DataSource dsOracle = (DataSource) initContext.lookup(strDataSourceName);
      connection = dsOracle.getConnection();
    }
    catch (Exception e) {
     try{
     if(initContext==null){
     initContext = new InitialContext();
     }
        Context envContext  = (Context)initContext.lookup("java:/comp/env");   
        DataSource dsOracle = (DataSource)envContext.lookup(ds);
        connection = dsOracle.getConnection();
     } catch (Exception e2) {
       e2.toString();
      }
    }
    return connection;
  }
  
  
  /**
   * 从连接池中返回一个数据库连接,域账户登录使用
   * @return
   *//*
  public static Connection getConnectionTwwms() {
    Connection connection = null;
    Context initContext = null;
    try {
      initContext = new InitialContext();
      DataSource dsOracle = (DataSource) initContext.lookup("jdbc/twwms");
      connection = dsOracle.getConnection();
    }
    catch (Exception e) {
     try{
     if(initContext==null){
     initContext = new InitialContext();
     }
        Context envContext  = (Context)initContext.lookup("java:/comp/env");   
        DataSource ds = (DataSource)envContext.lookup("jdbc/twwms");
        try{
          connection = ds.getConnection();
        }catch(SQLException ex){
        	ex.toString();
        }
     } catch (Exception e2) {
    	 e2.toString();
      }
    }
    return connection; 	
  }*/
  
  /**
   * 获取序列的下一个值，采用select seq.nextval from dual获得
   * @params seqName 序列名
   * @return 序列的下一个值，-1表示数据库错误
   */
  public int getSeqNextval(String seqName) {
	  String sql = "select "+seqName+".nextval from dual";
      return this.getInt(sql);
  }
  /**
   * 返回总行数
   * @param sql 获得单行单列的int值
   * @return    结果行数，-1表示数据库错误
   */
  public int getInt(String sql) {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement psmt = null;
    int count = -1;
    try {
      conn = getConnection();
      psmt = conn.prepareStatement(sql);
      rs = psmt.executeQuery();
      if (rs.next()) {
          count = rs.getInt(1);
      }
    } catch(Exception e) {
      Debug.print_log(e,sql);
    } finally {
      this.disconnect(rs, psmt, conn);
    }
    return count;
  }

}
