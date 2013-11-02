package cn.com.jnpc.foreign.utils;

import java.sql.*;
import java.util.*;

import com.jnpc.util.Debug;

public class Global {
  /**
   * @param      sql
   * @call       none
   * @return     ����õ�SQL���
   * @function   ���˴�SQL����еĵ���ţ�ת��''
   * @date
   * @author
   * @modify
   */
  public static String sqlFilter(String sql) {
    String sqlStr = ""; 	//�洢�����SQL���
    String tmpContext = ""; //�洢��ʱ����õ����
    String firstStr;  		//�����ǰ�����
    String lastStr; 		//����ź�����
    char   oldStr = 39; 	//"'"��ASCII��
    int pos; 				//���嵥����ھ��е�λ��
    sqlStr = sql.trim(); 	//ȥ��SQL����еĿո�
    if (sqlStr == null) {
      /*����䣬ֱ�ӷ���*/
      return "";
    }
    pos = sqlStr.indexOf(oldStr); //�õ���һ������ھ��е�λ��
    while(pos>=0) {
      /*�ҵ�ÿһ����ڵĵ���ţ�ֱ�����в����ڵ����Ϊֹ*/
      firstStr   = sqlStr.substring(0,pos+1); //�õ������ǰ�����
      lastStr    = sqlStr.substring(pos+1,sqlStr.length()); //�õ�����ź�����
      tmpContext = tmpContext+firstStr+oldStr; //��ʱ����õ����
      sqlStr     = lastStr; //��һ��Ҫ��������
      pos        = sqlStr.indexOf(oldStr); //�õ���һ����ŵ�λ��
    }
    sqlStr = tmpContext+sqlStr;  //�õ�����õ����
    return sqlStr;  //���ش���õ����
  }

/**
 * @param      type,context
 *             type:
 *             context:��Ҫת�����ַ�
 * @call       none
 * @return     ת������ַ�
 * @function   ����WML/HTML�еı����ַ�ר�ɶ�Ӧ��ȡ���ַ�
 * @call by
 * @date       2001.10.24
 * @author
 * @modify
 */
public static String getContextConvert(String context) {
  String tmpContext = ""; 	//��ת���м䣬��ʱ��ż���ת���õ��ַ�
  String firstStr; 			//��Ҫת�����ַ��е�һ�����Ҫת���ַ�ǰ���ַ�
  String lastStr;  			//��Ҫת�����ַ��е�һ�����Ҫת���ַ����ַ�
  String tmpStr="";  			//����ܺ���Ҫת���ַ���ַ�
  String convertStr = ""; 	//ת���ɵ��Է�
  int pos; 					//�ҵ���Ҫת���ַ�������ַ��е�λ��
  //char[] chararry={'&','<','>','$','"','\''};
  //char[] chararry={'&','<','>','"','\''};	//Ҫת�����ַ�
  char[] chararry={'&','<','>','"'};	//Ҫת�����ַ�
  if (context==null)
     return "";
  tmpStr=context.trim(); //ȥ���ַ��еĿո�
  if (tmpStr.equals(""))
    return "";
  for (int i=0;i<4;i++) {
    /*�����4β����ַ����Ƿ���Ҫת���ַ������е��ַ�*/
    pos=tmpStr.indexOf(chararry[i]); //ȷ����һ����ĵ�ǰҪת�����ַ����ַ��е�λ��
    while(pos>=0) {
      /*���ַ��к���Ҫת�����ַ������ѭ��*/
      firstStr   = tmpStr.substring(0,pos); //�õ�������ַ��У���һ����ĵ�ǰҪת���ַ�ǰ���ַ�
      lastStr    = tmpStr.substring(pos+1,tmpStr.length()); //�õ�������ַ��У���һ����ĵ�ǰҪת���ַ����ַ�
      switch (i) {
        /*ת���ɶ�Ӧ���ַ�*/
        case 0:
          convertStr = "&amp;";
          break;
        case 1:
          convertStr = "&lt;";
          break;
        case 2:
          convertStr = "&gt;";
          break;
        case 3:
          convertStr = "&quot;";
          break;
        }
      tmpContext = tmpContext+firstStr+convertStr; //ʼ�մ���ת���õ��ַ�
      tmpStr = lastStr;  //ʼ�մ���δ��ת�����ַ�
      pos = tmpStr.indexOf(chararry[i]);  //ȷ����һ����ĵ�ǰҪת�����ַ����ַ��е�λ��
      } //end while(�ַ��к��е�ǰҪת�����ַ�
      tmpContext = tmpContext+tmpStr; /*�Ѿ�����һ��Ҫת���ַ�ת���ú���ַ�*/
       tmpStr = tmpContext;  /*������Ϊ�µ�δת�����ַ�����ת����һ��Ҫת�����ַ�*/
      tmpContext = ""; //������ת���õ��ַ���ַ��ÿգ�������һ�εĿ�ʼ
  }
  return tmpStr;  //����ת���õ��ַ�
  }

 /**
   * @param      id
   * @call       none
   * @return     ����õ�id
   * @function   ���ַ�id���м�һ����
   * @date
   * @author
   * @modify
   */
  public String idAddOne(String id) {
    try {
      int i = Integer.parseInt(id);
      i = i + 1;
      String s = Integer.toString(i);
      int k = 9 - s.length();
      for (int j=0;j<k;j++) {
        s = "0"+ s ;
      }
      return s;
      } catch (Exception e){
          Debug.print_log(e);
          return null;
      }
  }

  public String changeDate(java.sql.Date date,String time) {
    try {
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      String s = Integer.toString(cal.get(Calendar.YEAR)) + "-";
      s = s + Integer.toString(cal.get(Calendar.MONTH) + 1) + "-";
      s = s + Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
      StringTokenizer tokenizer = new StringTokenizer(time," ");
      tokenizer.nextToken();
      String t = tokenizer.nextToken();
      tokenizer = new StringTokenizer(t,":");
      s = s + " " + tokenizer.nextToken();
      s = s + ":" + tokenizer.nextToken();
      return s;
    } catch (Exception e){
        Debug.print_log(e);
        return null;
    }
  }

 /**
   * @param      comm
   * @call       none
   * @return     ����õ�comm
   * @function   ���ַ�comm���м�һ����
   * @date
   * @author
   * @modify
   */
  public String commAddOne(String comm) {
    try {
      int i = Integer.parseInt(comm);
      i = i + 1;
      String s = Integer.toString(i);
      int k = 5 - s.length();
      for (int j=0;j<k;j++) {
        s = "0"+ s ;
      }
      return s;
      } catch (Exception e){
          Debug.print_log(e);
          return null;
      }
  }

 /**
   * @param      meetnum
   * @call       none
   * @return     ����õ�meetnum
   * @function   Ϊ�����Ҫ�ż�һ
   * @date
   * @author
   * @modify
   */
  public String meetnumAddOne(String meetnum) {
    try {
      int i = Integer.parseInt(meetnum);
      i = i + 1;
      String s = Integer.toString(i);
      int k = 3 - s.length();
      for (int j=0;j<k;j++){
        s = "0"+ s ;
      }
      return s;
      } catch (Exception e){
          Debug.print_log(e);
          return null;
      }
  }

  public static String[][] CreateStringArrayFromResultSet(ResultSet rs, int[] SizeOfResultSet) {
    String[][] result = null;
    String[][] TemporaryResult = null;
    int MAX_SIZE_CANDIDATE = 1000;
    try{
      ResultSetMetaData rsmd = rs.getMetaData();
      int numberOfColumns = rsmd.getColumnCount();
      TemporaryResult = new String[MAX_SIZE_CANDIDATE + 1][numberOfColumns];
      for (int i = 1; i <= numberOfColumns; i++) {
        String columnName = rsmd.getColumnName(i);
        TemporaryResult[0][i-1] = columnName;
      }
      int icount = 1;
      while (rs.next()) {
        for (int i = 1; i <= numberOfColumns; i++) {
        String columnValue = rs.getString(i);
        TemporaryResult[icount][i-1] = columnValue;
      }
      icount++;
      }//END of while
      int numberOfRows = icount;
      // copy the size of ResultSet
      SizeOfResultSet[0] = numberOfColumns;
      SizeOfResultSet[1] = numberOfRows;
      // copy to result array from temporary array.
      result = new String[numberOfRows + 1][numberOfColumns];
      for (int i = 0; i <= numberOfRows; i++) {
        for (int j = 0; j < numberOfColumns; j++) {
          result[i][j]=TemporaryResult[i][j];
        }
      }
    }catch(SQLException e) {
      Debug.print_log(e);
    }
     return result;
  }

  /**
   * ���Ա����Ų�ѯԱ������
   * @param userId
   * @return
   */
  public String getUserName(String userId){
    String userName = "";
    OracleConnection orcl = new OracleConnection();
    String sql ="select name from JNPC_USER_ALL where id="+userId;
    //ȡ��Ա������
    userName = orcl.getSnglRowSnglCol(sql, 1);
    userName = orcl.getSnglRowSnglCol(sql,"name");
    return userName;
  }

}

