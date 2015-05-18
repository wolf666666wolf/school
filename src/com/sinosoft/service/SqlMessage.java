package com.sinosoft.service;

import java.util.ResourceBundle;
import java.util.Hashtable;
import java.util.Map;
import java.text.MessageFormat;

/**
 * <p>Title: Sql���Ĳ���,���ϲ�.����EasyQueryUIǰ��ʹ��</p>
 *
 * <p>Description: ejbת�����</p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: sinosoft</p>
 *
 * @author litao
 * @version 1.0
 */
public class SqlMessage {
    private Map nameMap = new Hashtable();
    private String dir = "SqlInfo";
    private static SqlMessage sqlMessage = new SqlMessage();
    private SqlMessage() {
    }
    /**
     * ��̬ģʽ
     * */
    public static SqlMessage getSqlMessage() {
        return sqlMessage;
    }
    
    /**
     * ����Sql��Ϣ,���غϳ�sql���
     * */
    public String getSql(String name, String seqno, String[] para) {
        ResourceBundle tResourceBundle = getResourceBundle(name);
        String tSql = tResourceBundle.getString(seqno);
        return MessageFormat.format(tSql, para);
    }

    /**��ȡSQl��Ӧ��Դ*/
    private ResourceBundle getResourceBundle(String name) {
        ResourceBundle tResourceBundle = (ResourceBundle) nameMap.get(name);
        if (tResourceBundle == null) {
            tResourceBundle = ResourceBundle.getBundle(dir +
                    "." + name);
            nameMap.put(name, tResourceBundle);
        }
        return tResourceBundle;
    }
}
