/**
 * <p> </p>
 * <p> </p>
 * <p>Copyright: Copyright: Copyright (c) 2003 Dalian ChaoWei Computer Technology Co.,Ltd</p>
 * <p> All right reserved;</p>
 * @author
 * ����ʱ��
 */
package cn.com.jnpc.foreign.utils;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.jnpc.util.Debug;

public class OracleConnection extends SqlGenerator {
	private static String ds = "jdbc/intraweb";


  /**
   * ����Blob���Ͳ�ѯ���
   * @param sql
   * @param columnName
   * @return
   */
  public Blob getBlob1(String sql, String columnName,int num) {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    Blob blob = null;
    try {
      conn = getConnection(0);
      ps = conn.prepareStatement(sql);
      rs = ps.executeQuery();
      while (rs.next()) {
        blob = rs.getBlob(columnName);
      }
    } catch(Exception e) {
      Debug.print_log(e,sql);
    } finally {
      this.disconnect(rs, ps, conn);
    }
    return blob;
  }


  /**
     * ����һ�ж��в�ѯ���
     * @param sql
     * @param columnName
     * @return
     */
  public String[] getSnglRowMultiCol(String sql, int num) {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    Vector v = new Vector();
    String[] colType = null;
    int colCount = 0;
    try {
      conn = getConnection(0);
      ps = conn.prepareStatement(sql);
      rs = ps.executeQuery();

      ResultSetMetaData rsmd = rs.getMetaData();
     // ȡ�ü�¼����
      colCount = rsmd.getColumnCount();
      // �����¼�е�����
      colType = new String[colCount];

      for (int i = 1; i <= rsmd.getColumnCount(); i++) {
        colType[i - 1] = rsmd.getColumnTypeName(i);
      }

      if (rs.next()) {
        for (int i = 0; i < colCount; i++) {
          if (colType[i].equals("CLOB")) {
            Clob clob = rs.getClob(i + 1);
            v.addElement(clob.getSubString(1, (int) clob.length()));
          } else {
            v.addElement(rs.getString(i + 1));
          }
        }
      }
    } catch (Exception e) {
      Debug.print_log(e,sql);
    } finally {
      this.disconnect(rs, ps, conn);
    }

    return this.cvtVtrToArr(v);
  }

  /**
     * ����һ��һ�в�ѯ���
     * @param sql
     * @param columnName
     * @return
     */
  public String getSnglRowSnglCol(String sql, int num) {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    String str = "";

    try {
      conn = getConnection(0);
      ps = conn.prepareStatement(sql);
      rs = ps.executeQuery();

      ResultSetMetaData rsmd = rs.getMetaData();
      String colType = rsmd.getColumnTypeName(1);

      while (rs.next()) {
        if (colType.equals("CLOB")) {
          Clob clob = rs.getClob(1);
          str = clob.getSubString(1, (int) clob.length());
        } else {
          str = rs.getString(1);
        }
      }
    } catch (Exception e) {
      Debug.print_log(e,sql);
    } finally {
      this.disconnect(rs, ps, conn);
    }

    return str;
  }

  /**
   * ���ض��ж��в�ѯ���
   * @param sql
   * @param colArr
   * @return
   */
  public String[][] getMultiRowMultiCol(String sql, int num) {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    Vector v = new Vector();
    int colCount = 0;
    String[] colType = null;

    try {
      conn = getConnection(0);
      ps = conn.prepareStatement(sql);
      rs = ps.executeQuery();

      ResultSetMetaData rsmd = rs.getMetaData();
      colCount = rsmd.getColumnCount();
      colType = new String[colCount];
      for (int i = 1; i <= rsmd.getColumnCount(); i++) {
        colType[i - 1] = rsmd.getColumnTypeName(i);
      }

      while (rs.next()) {
        String[] arr = new String[colCount];

        for (int i = 0; i < colCount; i++) {
          if (colType[i].equals("CLOB")) {
            Clob clob = rs.getClob(i+1);
            arr[i] = clob.getSubString(1, (int) clob.length());
          } else {
            arr[i] = rs.getString(i+1);
          }
        }

        v.addElement(arr);
      }
    } catch (Exception e) {
      Debug.print_log(e,sql);
    } finally {
      this.disconnect(rs, ps, conn);
    }

    return this.cvtVtrToArr(v, colCount);
  }

  /**
    * ���ض���һ�в�ѯ���
    * @param sql
    * @param columnName
    * @return
    */
  public String[] getMultiRowSnglCol(String sql, int num) {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    Vector v = new Vector();

    try {
      conn = getConnection(0);
      ps = conn.prepareStatement(sql);
      rs = ps.executeQuery();

      ResultSetMetaData rsmd = rs.getMetaData();
      String colType = rsmd.getColumnTypeName(1);

      while (rs.next()) {
        if (colType.equals("CLOB")) {
          Clob clob = rs.getClob(1);
          v.addElement(clob.getSubString(1, (int) clob.length()));
        } else {
          v.addElement(rs.getString(1));
        }
      }
    } catch (Exception e) {
      Debug.print_log(e,sql);
    } finally {
      this.disconnect(rs, ps, conn);
    }

    return this.cvtVtrToArr(v);
  }

  /**
   * ��l�ӳ��з���һ����ݿ�l��
   * @return
   */
  public static Connection getConnection(int num) {
    
    Connection connection = null;
    Context initContext = null;
    try {
      initContext = new InitialContext();
      DataSource dsOracle = (DataSource) initContext.lookup(ds);
      connection = dsOracle.getConnection();
    }
    catch (Exception e) {
     try{
     if(initContext==null){
     initContext = new InitialContext();
     }
        Context envContext  = (Context)initContext.lookup(ds);   
        DataSource dataSource = (DataSource)envContext.lookup("jdbc/intraweb");
        try{
          connection = dataSource.getConnection();
        }catch(SQLException ex){
        	ex.toString();
        }
     } catch (Exception e2) {
       e2.toString();
      }
    }
    return connection;
  } 
  /**
   * ���ؼ�¼����Ŀ
   * @param sql
   * @return
   */
  public int getRecordCount(String sql) {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    int count = 0;

    try {
      conn = getConnection(0);
      ps = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,
          ResultSet.CONCUR_READ_ONLY);
      rs = ps.executeQuery(sql);

      if (rs != null) {
        if (rs.next()) {
          rs.last();
          count = rs.getRow();
        }
      }
    } catch (Exception e) {
     Debug.print_log(e);
    } finally {
      this.disconnect(rs, ps, conn);
    }

    return count;
  }
}
