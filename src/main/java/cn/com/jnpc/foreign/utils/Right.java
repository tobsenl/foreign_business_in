package cn.com.jnpc.foreign.utils;

/**
 * <p>Title: Ȩ��ά��</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * @author not attributable
 * @version 1.0
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.jnpc.util.Debug;

public class Right {
  private Connection conn = null;
  private DataSource ds = null;
  private InitialContext ctx = null;
  private ResultSet rs = null;
  private Statement st = null;
  public Right() {
  }
  public int getEmpNo(String user_id) {
    String[] result = null;
    OracleConnection oracleConn = new OracleConnection();
   // �����ѯ�����ֶε�sql���
   StringBuffer strBuffer = new StringBuffer();
   strBuffer.append("select count(userid) user_id from st_rightuser where userid=");
   strBuffer.append("'");
   strBuffer.append(user_id);
   strBuffer.append("'");

    String sql = strBuffer.toString();
    int flag = Integer.parseInt(oracleConn.getSnglRowSnglCol(sql,0));
    return flag;
  }

  /**
   * ���Ա����Ż�ø�Ա������
   * @param empNo Ա�����
   * @return Ա������
   */
  public String getEmpPassword(String user_id) {
    String password = "";
    OracleConnection oracleConn = new OracleConnection();
    // �����ѯ�����ֶε�sql���
    StringBuffer strBuffer = new StringBuffer();
    strBuffer.append("select passwd from st_rightuser where userid=");
    strBuffer.append("'");
    strBuffer.append(user_id);
    strBuffer.append("'");
    String sql = strBuffer.toString();
    // ִ��һ��һ�в�ѯ�����ؽ��
    password = oracleConn.getSnglRowSnglCol(sql,0);
    return password;
  }
  
  
  public Statement DBInit() throws java.lang.Exception {
    Connection connection = null;
    InitialContext initContext = null;
    try {
      initContext = new InitialContext();
      String strDataSourceName = "jdbc/login";
      DataSource dsOracle = (DataSource) initContext.lookup(strDataSourceName);
      connection = dsOracle.getConnection();
      st=connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,java.sql.ResultSet.CONCUR_READ_ONLY);
    }
    catch (Exception e) {
     try{
     if(initContext==null){
//     System.out.println("tomcat connection beginning");
     initContext = new InitialContext();
//     System.out.println("tomcat connection beginningaaaaaaaaa");
     }
        Context envContext  = (Context)initContext.lookup("java:/comp/env");   
//        System.out.println("tomcat connection beginningbbbbbbb");
        DataSource dsOracle = (DataSource)envContext.lookup("jdbc/login");
//        System.out.println("tomcat connection beginningccccccc");
        connection = dsOracle.getConnection();       
//        System.out.println("getConnection OKddd");
        st=connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,java.sql.ResultSet.CONCUR_READ_ONLY);
     } catch (Exception e2) {
       e2.toString();
      }
    }
    return st;
  }

  /*
   * �û�ʱ�������Ȩ��
   * @param:   userid :�û���½���
   * @param:   menuId :ģ����
   * @return   ����ֵ�� �����Ȩ�޷���True,û�����Ȩ�޷���False
   */
  public boolean isHaveAdd(String userId,int menuId) {
    return isHaveRight(userId,menuId,1);
  }
  /*
   * �û��Ƿ����޸�Ȩ��
   * @param:   userid :�û���½���
   * @param:   menuId :ģ����
   * @return   ����ֵ�� ���޸�Ȩ�޷���True,û���޸�Ȩ�޷���False
   */
  public boolean isHaveModify(String userId,int menuId) {
    return isHaveRight(userId,menuId,2);
  }

  /*
   * �û��Ƿ���ɾ��Ȩ��
   * @param:   userid :�û���½���
   * @param:   menuId :ģ����
   * @return   ����ֵ�� ��ɾ��Ȩ�޷���True,û��ɾ��Ȩ�޷���False
   */
  public boolean isHaveDelete(String userId,int menuId) {
    return isHaveRight(userId,menuId,4);
  }

  /*
  * �û���¼�����֤
  * @param:   userid :�û���½���
  * @param:   passwd :�û���½����
  * @return   ����ֵ�� ��֤��ȷ����1,����ȷ����0����ݿ���󷵻�-1
  */
 public boolean isHaveRight(String userid,int menuId,int flag) {
   // �û�û��Ȩ��
   boolean reHaveRight = false;
   StringBuffer strBuffer = new StringBuffer();
   try {
     SqlGenerator oracle = new SqlGenerator();
     // ���ò�ѯ��½���ڵ�ǰģ���Ȩ��
     strBuffer.append("select right_code from menu_user ");
     strBuffer.append("where menu_id=");
     strBuffer.append(menuId);
     strBuffer.append(" and user_id='");
     strBuffer.append(userid);
     strBuffer.append("'");
     // ȡ�õ�½���ڵ�ǰģ���Ȩ��Code
     String rightCode = oracle.getSnglRowSnglCol(strBuffer.toString(),"right_code");
     switch (flag) {
       case 1:
         if (rightCode.equals("1") || rightCode.equals("3") ||
             rightCode.equals("5") || rightCode.equals("7")) {
           // ����������readȨ��
           reHaveRight = true;
         }
         break;
       case 2:
         if (rightCode.equals("2") || rightCode.equals("3") ||
             rightCode.equals("6") || rightCode.equals("7")) {
           // ����������modifyȨ��
           reHaveRight = true;
         }
         break;
       case 4:
         if (rightCode.equals("4") || rightCode.equals("5") ||
             rightCode.equals("6") || rightCode.equals("7")) {
           // ����������modifyȨ��
           reHaveRight = true;
         }
         break;
     }
   } catch (Exception e){
	   e.printStackTrace();
//     System.out.println("RightVerify.java-->isHaveAdd() e1:" + e.getMessage());
   }
   return reHaveRight ;
 }

  public String computeDigest(String msg){
    try {
      java.security.MessageDigest alg = java.security.MessageDigest.getInstance("SHA-1");
      alg.reset();
      alg.update(msg.getBytes());
      byte[] hash = alg.digest();
      String digest = "";
      for (int i = 0;i < hash.length;i++){
        int v = hash[i] & 0xFF;
        if( v < 16 ) digest += "0";
        digest += Integer.toString(v,16).toUpperCase();
      }
      return digest;
    }catch (Exception e) {
      Debug.print_log(e);
      return msg;
    }
  }


  //�õ�ĳ����ĳϵͳ�µ�����Ȩ�ޣ���(�����Ȩ��,�͹���Ȩ��
  public Vector getRights(String userID,String systemID) {
    String sqlStr = "";
    String all = "";
    Vector rightIDs = new Vector();
    try {
      DBInit();

      //���û�Ȩ�ޱ���õ�ͨ���ɫ�����û���Ȩ�޺ͽ��ܵĴ���Ȩ��
      sqlStr = " select r.define from st_role r,st_user_role u where r.roleid=u.roleid"
             + " and (u.userid = '"+Global.sqlFilter(userID)+"' or u.proxy_userid='"+Global.sqlFilter(userID)+"') "
             + " and subStr(r.define,1,2)= '"+Global.sqlFilter(systemID)+"' ";
      rs=st.executeQuery(sqlStr);
      while (rs.next()){
        all = all.trim()+rs.getString(1);
      }
      //��function��õ�����Ȩ��
      sqlStr = "";
      sqlStr = " select funid from st_function where subStr(funid,1,2)= '"+Global.sqlFilter(systemID)+"' "
             + " and length(funid)=6 and ifpublic='y' order by 1";
      rs = st.executeQuery(sqlStr);
      while(rs.next()){
        all = all.trim()+rs.getString(1)+",";
      }
      //��all�ַ���н���ȥ���ظ���Ȩ��
      StringTokenizer tokenizer = new StringTokenizer(all);
      String temp = "";
      while (tokenizer.hasMoreTokens()) {
        temp = tokenizer.nextToken(",");
        if (!rightIDs.contains(temp))
          rightIDs.addElement(temp);
      }
      CloseDB();
    } catch(Exception e){
      Debug.print_log(e);
      CloseDB();
      return null;
    }

    return rightIDs;
  }

  public Vector getAdminUserIDs(String userID,String systemID,
                                String right1,String right2,String right3) {
    Vector adminUserID = new Vector();
    String sqlStr = "";
    String all = "";
    try {
      DBInit();
      //���û�Ȩ�ޱ���õ�ͨ���ɫ�����û���Ȩ�޺ͽ��ܵĴ���Ȩ��
      sqlStr = " select u.userid,r.define from st_role r,st_user_role u where r.roleid=u.roleid"
             + " and (u.userid = '" + Global.sqlFilter(userID)
             + "' or u.proxy_userid='" + Global.sqlFilter(userID) + "') "
             + " and subStr(r.define,1,2)= '" + Global.sqlFilter(systemID) + "' ";
      rs = st.executeQuery(sqlStr);
      while (rs.next()) {
        String define = rs.getString(2);
        if (define.indexOf(right1)!=-1 && define.indexOf(right2)!=-1 && define.indexOf(right3)!=-1) {
           all = all.trim() + rs.getString(1);
        }
      }
      //��all�ַ���н���ȥ���ظ���Ȩ��
      StringTokenizer tokenizer = new StringTokenizer(all);
      String temp = "";
      while (tokenizer.hasMoreTokens()) {
        temp = tokenizer.nextToken(",");
        if (!adminUserID.contains(temp))
          adminUserID.addElement(temp);
      }
      CloseDB();
    }
    catch (Exception e) {
      Debug.print_log(e);
      CloseDB();
      return null;
    }
    return adminUserID;
  }


  //�Ƿ���ĳ��Ȩ��
  public boolean isHaveRight(String userID,String functionID) {
    boolean isHave = false;
    String systemid =functionID.substring(0,2);
    Vector rightIDs = new Vector();
    rightIDs = this.getRights(userID,systemid);

    if (rightIDs != null || rightIDs.size() != 0) {
      if (rightIDs.contains(functionID))
        isHave = true;
    }
    return isHave;

  }


  //for pdb �õ�ĳ�����д�Ȩ�޵�Ա�����
  //���벿�ű�ţ�Ȩ�ޱ��
  //���ò����д�Ȩ�޵�Ա�����
  public Vector getAuditUserID(String depid,String funid) {
    Vector users = new Vector();
    String sqlStr="";

    sqlStr = "select distinct(userid) from st_user_role u,st_role r ,view_employee e "
           + "where r.roleid=u.roleid and define like '%"+Global.sqlFilter(funid)+"%' and "
           + "org='"+Global.sqlFilter(depid)+"' and userid = e.id order by 1";
    try {
      DBInit();
      rs = st.executeQuery(sqlStr);
      while (rs.next())
        users.addElement(rs.getString(1));
      CloseDB();
    } catch (Exception e){
      Debug.print_log(e);
      CloseDB();
      return null;
    }
    return users;
  }

  public Vector getSignUserID(String funid) {
    Vector signUserID = new Vector();
    String sqlStr="";
    sqlStr = "select distinct(userid) from st_user_role u,st_role r "
           + "where r.roleid=u.roleid and define like '%"+Global.sqlFilter(funid)+"%' order by 1";
    try {
      DBInit();
      rs = st.executeQuery(sqlStr);
      while (rs.next())
        signUserID.addElement(rs.getString(1));
      CloseDB();
    } catch (Exception e){
      Debug.print_log(e);
      CloseDB();
      return null;
    }
    return signUserID;
  }

  //for pdb �õ�Ȩ��
  //�����û���ź�ϵͳ���
  //�����û�����(�������ˣ���Ȩ���б�
  public java.util.Hashtable rightList(String userID,String systemID){
    String sqlStr = "";
    String all = "";
    java.util.Hashtable rightList = new java.util.Hashtable();

    try {
      DBInit();
      //�õ����˺�Ȩ�޵��б�
      sqlStr = " select r.define from st_role r,st_user_role u where r.roleid=u.roleid"
             + " and u.userid = '"+Global.sqlFilter(userID)+"'"
             + " and subStr(r.define,1,2)= '"+Global.sqlFilter(systemID)+"' ";
      rs=st.executeQuery(sqlStr);
      while (rs.next()){
        all = all.trim()+rs.getString(1);
      }


      //��function��õ�����Ȩ��
      sqlStr = "";
      sqlStr = " select funid from st_function where subStr(funid,1,2)= '"+Global.sqlFilter(systemID)+"' "
             + " and length(funid)=6 and ifpublic='y' order by 1";
      rs = st.executeQuery(sqlStr);
      while(rs.next()){
        all = all.trim()+rs.getString(1)+",";
      }

      rightList.put(userID,all);

      //�õ�����˴���ı��������û���ź�Ȩ�޵��б�
      all = "";
      sqlStr = " select distinct(userid) from st_role r,st_user_role u where r.roleid=u.roleid"
             + " and proxy_userid= '"+Global.sqlFilter(userID)+"' and subStr(r.define,1,2)='"+Global.sqlFilter(systemID)+"' order by 1";

      rs = st.executeQuery(sqlStr);
      Vector store = new Vector();
      String temp = "";
      while (rs.next()){
        store.addElement(rs.getString(1));
      }


      for(int i = 0; i<store.size();i++){
        all = "";
        temp = store.elementAt(i).toString();
        sqlStr = " select r.define from st_role r,st_user_role u where r.roleid=u.roleid"
                + " and userid = '"+Global.sqlFilter(temp)+"' and proxy_userid='"+Global.sqlFilter(userID)+"'"
                + " and subStr(r.define,1,2)= '"+Global.sqlFilter(systemID)+"' ";

        rs = st.executeQuery(sqlStr);
        while (rs.next()){
          all = all.trim()+rs.getString(1);
        }

        if (!all.equals(""))
          rightList.put(temp,all);
      }
      CloseDB();
    } catch(Exception e){
      Debug.print_log(e);
      CloseDB();
      return null;
    }
    return rightList;
  }
  /**
   * 验证用户是否存在
   * @param userid
   * @return
   */
  public String VerifyUser(String userid){
	    String flag = "1";   //�û���¼����
	    String sqlStr="";
	    //passwd = computeDigest(passwd);
	    sqlStr = "select userid from st_rightuser where userid='"+Global.sqlFilter(userid)+"'";
	    try {
	      DBInit();
	      rs = st.executeQuery(sqlStr);
	      if (rs.next())
	        flag = "0";    //��ȷ���û���¼

	      CloseDB();
	    } catch (Exception e){
	      Debug.print_log(e);
	      CloseDB();
	      return "2";
	    }
	    return flag ;
	  }

  public String VerifyUser(String userid,String passwd){
    String flag = "1";   //�û���¼����
    String sqlStr="";
    //passwd = computeDigest(passwd);
    sqlStr = "select userid from st_rightuser where userid='"+Global.sqlFilter(userid)+"' and passwd='"+Global.sqlFilter(passwd)+"'";
    try {
      DBInit();
      rs = st.executeQuery(sqlStr);
      if (rs.next())
        flag = "0";    //��ȷ���û���¼

      CloseDB();
    } catch (Exception e){
      Debug.print_log(e);
      CloseDB();
      return "2";
    }
    return flag ;
  }

  public String VerifyUserLogin(String userid,String passwd){
    String flag = "1";   //�û���¼����
    String sqlStr="";
    passwd = computeDigest(passwd);
    sqlStr = "select userid from st_rightuser where userid='"+Global.sqlFilter(userid)+"' and passwd='"+Global.sqlFilter(passwd)+"'";
    try {
      DBInit();
      rs = st.executeQuery(sqlStr);
      if (rs.next())
        flag = "0";    //��ȷ���û���¼

      CloseDB();
    } catch (Exception e){
      Debug.print_log(e);
      CloseDB();
      return "2";
    }
    return flag ;
  }


  public String CloseDB() {
    try {
      if (rs != null)
        rs.close();
      if (st != null)
        st.close();
      if (conn != null)
        conn.close();
    } catch (Exception e){
      Debug.print_log(e);
      return null;
    }
    return "ok";
  }
}
