package cn.com.jnpc.foreign.utils;
/**
 * <p>Title: �Զ����SQL����Java Bean</p>
 * <p>Description: �Զ����SQL��䣬��Oracle���ʹ�ã���ҵ���޹أ�Ҳ������������Ŀ�������öȼ��ߣ�</p>
 * <p>Company: </p>
 * @author
 * @version 1.0
 */





public class SqlGenerator extends Oracle {
  public SqlGenerator(){
    super();
  }
  /**
   * ͨ������ֶ�������顢�ֶ��������顢�Լ���Ӧֵ�������һ��insert���
   * @param tblName ����
   * @param colNames �ֶ��������
   * @param colTypes ��Ӧ���ֶ���������
   * @param colValues �ֶζ�Ӧֵ�������
   * @return
   */
  public String genInsStmt(String tblName, String[] colNames, String[] colValues) {
    String[] colTypes = this.getColTypes(tblName, colNames);
    StringBuffer strBuff = new StringBuffer();
    strBuff.append("insert into ");
    strBuff.append(tblName);
    strBuff.append("(");

    for (int i = 0; i < (colNames.length - 1); i++) {
      strBuff.append(colNames[i]);
      strBuff.append(",");
    }

    strBuff.append(colNames[colNames.length - 1]);
    strBuff.append(") values (");

    for (int i = 0; i < (colTypes.length - 1); i++) {
      if (!"".equals(colValues[i])) { // ����ֶζ�Ӧ��ֵ��Ϊnull

        if (colTypes[i].equals("NUMBER")) {
          strBuff.append(colValues[i]);
        }

        if (colTypes[i].equals("VARCHAR2")) {
          strBuff.append("'");
          strBuff.append(colValues[i]);
          strBuff.append("'");
        }

        if (colTypes[i].equals("DATE")) {
          strBuff.append("to_date('");
          strBuff.append(colValues[i]);
          strBuff.append("','YYYY-MM-DD HH24:mi:ss')");
        }

        if (colTypes[i].equalsIgnoreCase("CLOB")) {
          //strBuff.append("'");
          //strBuff.append(colValues[i]);
          //strBuff.append("'");
          strBuff.append("EMPTY_CLOB()");
        }

        if (colTypes[i].equalsIgnoreCase("BLOB")) {
          strBuff.append("EMPTY_BLOB()");
        }

      } else { // ����ֶζ�Ӧ��ֵΪnull
        strBuff.append("null");
      }
       // ���if������

      strBuff.append(",");
    }
     // forѭ������

    int i = colTypes.length - 1;

    if (!"".equals(colValues[i])) { // ����ֶζ�Ӧ��ֵ��Ϊnull

      if ("NUMBER".equals(colTypes[i])) {
        strBuff.append(colValues[i]);
      }

      if ("VARCHAR2".equals(colTypes[i])) {
        strBuff.append("'");
        strBuff.append(colValues[i]);
        strBuff.append("'");
      }

      if ("DATE".equals(colTypes[i])) {
        strBuff.append("to_date('");
        strBuff.append(colValues[i]);
        strBuff.append("','YYYY-MM-DD HH24:mi:ss')");
      }

      if ("CLOB".equalsIgnoreCase(colTypes[i])) {
        //strBuff.append("'");
        //strBuff.append(colValues[i]);
        //strBuff.append("'");
        strBuff.append("EMPTY_CLOB()");
      }

      if ("BLOB".equalsIgnoreCase(colTypes[i])) {
        strBuff.append("EMPTY_BLOB()");
      }

    } else { // ����ֶζ�Ӧ��ֵΪnull
      strBuff.append("null");
    }
     // ���if������

    strBuff.append(")");

    return strBuff.toString();
  }

  /**
   * ͨ���������ֶ������ֵ���ֶ�������顢�ֶ��������顢�Լ���Ӧֵ�������һ��insert���
   * @param tblName ����
   * @param PKField ����ֶ���
   * @param PKValue ���ֵ
   * @param colNames �ֶ��������
   * @param colTypes ��Ӧ���ֶ���������
   * @param colValues �ֶζ�Ӧֵ�������
   * @return
   */
  public String genInsStmt(String tblName, String PKColumn, String PKValue,
    String[] colNames, String[] colValues) {
    String[] colTypes = this.getColTypes(tblName, colNames);
    StringBuffer strBuff = new StringBuffer();
    strBuff.append("insert into ");
    strBuff.append(tblName);
    strBuff.append("(" + PKColumn + ",");

    for (int i = 0; i < (colNames.length - 1); i++) {
      strBuff.append(colNames[i]);
      strBuff.append(",");
    }

    strBuff.append(colNames[colNames.length - 1]);
    strBuff.append(") values (" + PKValue + ",");

    for (int i = 0; i < (colTypes.length - 1); i++) {
      if (colValues[i] != null && !colValues[i].equals("") ) { // ����ֶζ�Ӧ��ֵ��Ϊnull

        if (colTypes[i].equals("NUMBER")) {
          strBuff.append(colValues[i]);
        }

        if (colTypes[i].equals("VARCHAR2")) {
          strBuff.append("'");
          strBuff.append(colValues[i]);
          strBuff.append("'");
        }

        if (colTypes[i].equals("DATE")) {
          strBuff.append("to_date('");
          strBuff.append(colValues[i]);
          strBuff.append("','YYYY-MM-DD HH24:MI')");
        }

        if (colTypes[i].equalsIgnoreCase("CLOB")) {
          // strBuff.append("'");
          // strBuff.append(colValues[i]);
          // strBuff.append("'");
          strBuff.append("EMPTY_CLOB()");
        }

        if (colTypes[i].equalsIgnoreCase("BLOB")) {
          strBuff.append("EMPTY_BLOB()");
        }

      } else { // ����ֶζ�Ӧ��ֵΪnull
        strBuff.append("null");
      }
       // ���if������

      strBuff.append(",");
    }
     // forѭ������

    int i = colTypes.length - 1;

    if (colValues[i] != null && !colValues[i].equals("")) { // ����ֶζ�Ӧ��ֵ��Ϊnull
      if (colTypes[i].equals("NUMBER")) {
        strBuff.append(colValues[i]);
      }

      if (colTypes[i].equals("VARCHAR2")) {
        strBuff.append("'");
        strBuff.append(colValues[i]);
        strBuff.append("'");
      }

      if (colTypes[i].equals("DATE")) {
        strBuff.append("to_date('");
        strBuff.append(colValues[i]);
        strBuff.append("','YYYY-MM-DD')");
      }

      if (colTypes[i].equalsIgnoreCase("CLOB")) {
      //  strBuff.append("'");
      //  strBuff.append(colValues[i]);
      //  strBuff.append("'");
        strBuff.append("EMPTY_CLOB()");
      }

      if (colTypes[i].equalsIgnoreCase("BLOB")) {
        strBuff.append("EMPTY_BLOB()");
      }

    } else { // ����ֶζ�Ӧ��ֵΪnull
      strBuff.append("null");
    }
     // ���if������

    strBuff.append(")");
    return strBuff.toString();
  }

  /**
   * ����һ��Update���
   * @param tblName
   * @param id
   * @param idVal
   * @param colNames
   * @param colTypes
   * @param colValues
   * @return
   */
  public String genUpdateStmt(String tblName, String id, String idVal,
    String[] colNames, String[] colValues) {
    String[] colTypes = this.getColTypes(tblName, colNames);
    StringBuffer strBuff = new StringBuffer();
    strBuff.append("update ");
    strBuff.append(tblName);
    strBuff.append(" set ");

    for (int i = 0; i < (colTypes.length - 1); i++) {
      strBuff.append(colNames[i]);
      strBuff.append("=");

      if (colValues[i] != null && !colValues[i].equals("")) { // ����ֶζ�Ӧ��ֵ��Ϊnull

        if (colTypes[i].equals("NUMBER")) {
          strBuff.append(colValues[i]);
        }

        if (colTypes[i].equals("VARCHAR2")) {
          strBuff.append("'");
          strBuff.append(colValues[i]);
          strBuff.append("'");
        }

        if (colTypes[i].equals("DATE")) {
          strBuff.append("to_date('");
          strBuff.append(colValues[i]);
          strBuff.append("','YYYY-MM-DD HH24:mi:ss')");
        }

        if (colTypes[i].equalsIgnoreCase("CLOB")) {
          //          strBuff.append("'");
          //          strBuff.append(colValues[i]);
          //          strBuff.append("'");
          strBuff.append("EMPTY_CLOB()");
        }

        if (colTypes[i].equalsIgnoreCase("BLOB")) {
          strBuff.append("EMPTY_BLOB()");
        }

      } else { // ����ֶζ�Ӧ��ֵΪnull
        strBuff.append("null");
      }
       // ���if������

      strBuff.append(",");
    }
     // forѭ������

    int i = colTypes.length - 1;
    strBuff.append(colNames[i]);
    strBuff.append("=");

    if (colValues[i] != null && !colValues[i].equals("")) { // ����ֶζ�Ӧ��ֵ��Ϊnull

      if (colTypes[i].equals("NUMBER")) {
        strBuff.append(colValues[i]);
      }

      if (colTypes[i].equals("VARCHAR2")) {
        strBuff.append("'");
        strBuff.append(colValues[i]);
        strBuff.append("'");
      }

      if (colTypes[i].equals("DATE")) {
        strBuff.append("to_date('");
        strBuff.append(colValues[i]);
        strBuff.append("','YYYY-MM-DD HH24:mi:ss')");
      }

      if (colTypes[i].equalsIgnoreCase("CLOB")) {
        // strBuff.append("'");
        // strBuff.append(colValues[i]);
        // strBuff.append("'");
        strBuff.append("EMPTY_CLOB()");
      }

      if (colTypes[i].equalsIgnoreCase("BLOB")) {
        strBuff.append("EMPTY_BLOB()");
      }

    } else { // ����ֶζ�Ӧ��ֵΪnull
      strBuff.append("null");
    }
     // ���if������

    strBuff.append(" where ");
    strBuff.append(id);
    strBuff.append("=");
    strBuff.append(idVal);

    return strBuff.toString();
  }

  /**
   * �������˫����update���
   * @param tblName ����
   * @param firstId �ؼ���1
   * @param firstIdVal �ؼ���1ֵ
   * @param secondId �ؼ���2
   * @param secondIdVal �ؼ���2ֵ
   * @param colNames �����ؼ���
   * @param colTypes ������
   * @param colValues ��ֵ
   * @return
   */
  public String genUpdateStmt(String tblName, String firstId,
    String firstIdVal, String secondId, String secondIdVal, String[] colNames,
    String[] colValues) {
    String[] colTypes = this.getColTypes(tblName, colNames);
    StringBuffer strBuff = new StringBuffer();
    strBuff.append("update ");
    strBuff.append(tblName);
    strBuff.append(" set ");

    for (int i = 0; i < (colTypes.length - 1); i++) {
      strBuff.append(colNames[i]);
      strBuff.append("=");

      if (colValues[i] != null && !colValues[i].equals("")) { // ����ֶζ�Ӧ��ֵ��Ϊnull

        if (colTypes[i].equals("NUMBER")) {
          strBuff.append(colValues[i]);
        }

        if (colTypes[i].equals("VARCHAR2")) {
          strBuff.append("'");
          strBuff.append(colValues[i]);
          strBuff.append("'");
        }

        if (colTypes[i].equals("DATE")) {
          strBuff.append("to_date('");
          strBuff.append(colValues[i]);
          strBuff.append("','YYYY-MM-DD')");
        }

        if (colTypes[i].equalsIgnoreCase("CLOB")) {
          // strBuff.append("'");
          // strBuff.append(colValues[i]);
          // strBuff.append("'");
          strBuff.append("EMPTY_CLOB()");
        }

        if (colTypes[i].equalsIgnoreCase("BLOB")) {
          strBuff.append("EMPTY_BLOB()");
        }


      } else { // ����ֶζ�Ӧ��ֵΪnull
        strBuff.append("null");
      }
       // ���if������

      strBuff.append(",");
    }
     // forѭ������

    int i = colTypes.length - 1;
    strBuff.append(colNames[i]);
    strBuff.append("=");

    if (colValues[i] != null && !colValues[i].equals("")) { // ����ֶζ�Ӧ��ֵ��Ϊnull

      if (colTypes[i].equals("NUMBER")) {
        strBuff.append(colValues[i]);
      }

      if (colTypes[i].equals("VARCHAR2")) {
        strBuff.append("'");
        strBuff.append(colValues[i]);
        strBuff.append("'");
      }

      if (colTypes[i].equals("DATE")) {
        strBuff.append("to_date('");
        strBuff.append(colValues[i]);
        strBuff.append("','YYYY-MM-DD')");
      }

      if (colTypes[i].equalsIgnoreCase("CLOB")) {
        // strBuff.append("'");
        // strBuff.append(colValues[i]);
        // strBuff.append("'");
        strBuff.append("EMPTY_CLOB()");
      }

      if (colTypes[i].equalsIgnoreCase("BLOB")) {
        strBuff.append("EMPTY_BLOB()");
      }


    } else { // ����ֶζ�Ӧ��ֵΪnull
      strBuff.append("null");
    }
     // ���if������

    strBuff.append(" where ");
    strBuff.append(firstId);
    strBuff.append("=");
    strBuff.append(firstIdVal);
    strBuff.append(" and ");
    strBuff.append(secondId);
    strBuff.append("='");
    strBuff.append(secondIdVal);
    strBuff.append("'");
    return strBuff.toString();
  }

  /**
   * ��ɲ���where��ѯ����Ĳ�ѯ���
   * @param tblName ����
   * @param colsName ��ѯ������
   * @return
   */
  public String genSelectStmt(String tblName,String[] colsName){
    StringBuffer strBuffer = new StringBuffer();
    strBuffer.append("select ");
    for(int i = 0; i < colsName.length-1; i++){
      strBuffer.append(colsName[i]);
      strBuffer.append(",");
    }
    strBuffer.append(colsName[colsName.length-1]);
    strBuffer.append(" from ");
    strBuffer.append(tblName);
    return strBuffer.toString();
  }

  /**
   * 根据sql语句返回该sql的分页查询语句
   */
  public static <T> String getByPageSql(String sql, Page page) {
	StringBuilder sb = new StringBuilder();
	sb.append("SELECT * FROM (SELECT A.*, ROWNUM RN FROM (");
	sb.append(sql);
	int size = page.getPageRe();
	int pageNo = page.getPageId();
	int tempPageNo = 0;
	if (pageNo < 1) {
	    tempPageNo = 1;
	} else {
	    tempPageNo = pageNo;
	}
	sb.append(") A WHERE ROWNUM <= ");
	sb.append(tempPageNo * size);
	sb.append(") WHERE RN >=");
	sb.append(((tempPageNo - 1) * size + 1));
	return sb.toString();
  }
}
