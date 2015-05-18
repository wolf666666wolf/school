﻿
package com.sinosoft.lis.pubfun;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Set;
import com.sinosoft.lis.log.*;
import com.sinosoft.utility.*;


public class PubSubmit
{
    //传输数据类
    private VData mInputData;
    /** 错误处理类，每个需要错误处理的类中都放置该类 */
    public CErrors mErrors = new CErrors();
    /** 数据库连接  **/
    private Connection conn = null;
    /** 立即提交标志 **/
    private final boolean commitFlag = true;

    public PubSubmit()
    {
    }

    /**
     * 传输数据的公共方法
     * 传入数据
     * @param cInputData VData
     * 数据操作标示
     * 为了解决同时提交时的数据重复插入的问题，传入一个(select)sql如果查出数据则表示已经有数据，则报错退出
     * @param cOperate String
     * @return boolean
     */

    public boolean submitData(MMap map)
    {
    	VData cInputData = new VData();
    	cInputData.add(map);
    	return submitData(cInputData,"");
    }
    public boolean submitData(VData cInputData)
    {
    	return submitData(cInputData,"");
    }
    
    public boolean submitData(VData cInputData, String cOperate)
    {
        //首先将数据在本类中做一个备份
        mInputData = (VData) cInputData.clone();

        if (!this.saveData())
        {
            return false;
        }
        mInputData = null;
        return true;
    }

    /**
     * 数据库操作
     * @return: boolean
     */
    private boolean saveData()
    {
//        log.addlog("---Start Save---");

        //建立数据库连接
        if (conn == null)
        {
            conn = DBConnPool.getConnection();
        }

        if (conn == null)
        {
            // @@错误处理
            CError.buildErr(this, "数据库连接失败");
            return false;
        }

        try
        {
            //开始事务，锁表
            conn.setAutoCommit(false);

            String action = ""; //操作方式，INSERT\UPDATE\DELETE
            String className = ""; //类名
            Object o = null; //Schema或Set对象
            Object DBObject = null; //DB或DBSet对象
            Method m = null; //方法
            Constructor constructor = null; //构造函数
            Class[] parameterC = new Class[1]; //调用方法的参数数组
            Object[] parameterO = new Object[1]; //调用方法的对象数组
//            log.addlog("mInputData.size():" + mInputData.size());
//            log.addlog("mInputData :" + mInputData);
            //通过MMap来传递每个Schema或Set的数据库操作方式，约定使用
            MMap map = (MMap) mInputData.getObjectByObjectName("MMap", 0);
            if (map != null && map.keySet().size() != 0)
            {
                Set set = map.keySet();
                //Iterator iterator = map.keySet().iterator();
                //while (iterator.hasNext()) {
                for (int j = 0; j < set.size(); j++)
                {
                    //获取操作对象Schema或Set或SQL
                    //o = iterator.next();
                    o = map.getOrder().get(String.valueOf(j + 1));
                    //获取操作方式
                    action = (String) map.get(o);
                    if (action == null)
                    {
                        continue;
                    }
//                    log.addlog("\n" + o.getClass().getName() +
//                                       " Operate DB: " + action);

                    //构造相应的DB类名
                    className = o.getClass().getName();
//                    log.addlog("className :" + className);
//                    log.addlog("action :" + action);

                    if (className.endsWith("String"))
                    {
                        if (action.equals("UPDATE"))
                        {
                            className = "com.sinosoft.lis.db." +
                                        className.
                                        substring(className.lastIndexOf(".") +
                                                  1, className.lastIndexOf("S")) +
                                        "DB";
                            String tSQL = (String) o;
                            ExeSQL tExeSQL = new ExeSQL(conn);
//                            log.addlog("执行SQL语句:" + tSQL);
                            if (!tExeSQL.execUpdateSQL(tSQL))
                            {
                                CError.buildErr(this, "执行更新语句失败");
                                conn.rollback();
                                conn.close();
                                return false;
                            }
                            continue;
                        }
                        if (action.equals("DELETE"))
                        {
                            className = "com.sinosoft.lis.db." +
                                        className.
                                        substring(className.lastIndexOf(".") +
                                                  1, className.lastIndexOf("S")) +
                                        "DB";
                            String tSQL = (String) o;
                            ExeSQL tExeSQL = new ExeSQL(conn);
//                            log.addlog("执行SQL语句:" + tSQL);
                            if (!tExeSQL.execUpdateSQL(tSQL))
                            {
                                CError.buildErr(this, "执行删除语句失败");
                                conn.rollback();
                                conn.close();
                                return false;
                            }
                            continue;
                        }
                        if (action.equals("INSERT"))
                        {
                            className = "com.sinosoft.lis.db." +
                                        className.
                                        substring(className.lastIndexOf(".") +
                                                  1, className.lastIndexOf("S")) +
                                        "DB";
                            String tSQL = (String) o;
                            ExeSQL tExeSQL = new ExeSQL(conn);
                            //System.out.println("执行SQL语句:" + tSQL);
                            if (!tExeSQL.execUpdateSQL(tSQL))
                            {
                                CError.buildErr(this,
                                                "执行插入语句失败原因：" +
                                                tExeSQL.mErrors.getError(0).
                                                errorMessage);
                                conn.rollback();
                                conn.close();
                                return false;
                            }
                            continue;
                        }
                        if (action.equals("SELECT"))                  //加入select方法，如果查出则表示有重复操作并报错退出
                        {
                            className = "com.sinosoft.lis.db." +
                                        className.
                                        substring(className.lastIndexOf(".") +
                                                  1, className.lastIndexOf("S")) +
                                        "DB";
                            String tSQL = (String) o;
                            ExeSQL tExeSQL = new ExeSQL(conn);
                            SSRS tSSRS = new SSRS();
                            //log.addlog("执行SQL语句:" + tSQL);
                            tSSRS = tExeSQL.execSQL(tSQL);

                            if (tSSRS.getMaxRow()>0)
                            {
                                CError.buildErr(this, "该操作已经执行，请检查数据！");
                                conn.rollback();
                                conn.close();
                                return false;
                            }
                            continue;
                        }
                    }
                    else if (className.endsWith("Schema"))
                    {
                        className = "com.sinosoft.lis.db." +
                                    className.
                                    substring(className.lastIndexOf(".") + 1,
                                              className.lastIndexOf("S")) +
                                    "DB";
                    }
                    else if (className.endsWith("Set"))
                    {
                        className = "com.sinosoft.lis.vdb." +
                                    className.
                                    substring(className.lastIndexOf(".") + 1,
                                              className.lastIndexOf("S")) +
                                    "DBSet";
                    }
                    Class DBClass = Class.forName(className);

                    //选择构造函数，构造相同事务的DB或DBSet对象
                    parameterC[0] = Connection.class;
                    constructor = DBClass.getConstructor(parameterC);
                    parameterO[0] = conn;
                    DBObject = constructor.newInstance(parameterO);

                    //给DB对象付值，将传入的Schema或Set对象的内容复制到DB中
                    parameterC[0] = o.getClass();
                    if (o.getClass().getName().endsWith("Schema"))
                    {
                        m = DBObject.getClass().getMethod("setSchema",
                                parameterC);
                    }
                    else if (o.getClass().getName().endsWith("Set"))
                    {
                        m = DBObject.getClass().getMethod("set", parameterC);
                    }
                    parameterO[0] = o;
                    m.invoke(DBObject, parameterO);

                    //进行数据库操作
                    if (action.equals("INSERT"))
                    {
                        m = DBObject.getClass().getMethod("insert", null);
                        Boolean b = (Boolean) m.invoke(DBObject, null);

                        if (!b.booleanValue())
                        {
                            CError.buildErr(this, "执行插入语句失败");
                            try
                            {
                                conn.rollback();
                            }
                            catch (Exception e)
                            {}
                            conn.close();
                            log.addlog(DBObject.getClass().getName() +
                                               " " + action + " Failed");
                            return false;
                        }
                    }
                    else if (action.equals("UPDATE"))
                    {
                        m = DBObject.getClass().getMethod("update", null);
                        Boolean b = (Boolean) m.invoke(DBObject, null);

                        if (!b.booleanValue())
                        {
                            CError.buildErr(this, "执行更新语句失败");
                            try
                            {
                                conn.rollback();
                            }
                            catch (Exception e)
                            {}
                            conn.close();
                            log.addlog(DBObject.getClass().getName() +
                                               " " + action + " Failed");
                            return false;
                        }
                    }
                    else if (action.equals("DELETE"))
                    {
                        m = DBObject.getClass().getMethod("delete", null);
                        Boolean b = (Boolean) m.invoke(DBObject, null);

                        if (!b.booleanValue())
                        {
                            CError.buildErr(this, "执行删除语句失败");
                            try
                            {
                                conn.rollback();
                            }
                            catch (Exception e)
                            {}
                            conn.close();
                            log.addlog(DBObject.getClass().getName() +
                                               " " + action + " Failed");
                            return false;
                        }
                    }
                    else if (action.equals("DELETE&INSERT"))
                    {
                        //DELETE
                        m = DBObject.getClass().getMethod("delete", null);
                        Boolean b = (Boolean) m.invoke(DBObject, null);

                        if (!b.booleanValue())
                        {
                            CError.buildErr(this, "执行删除，插入语句失败");
                            try
                            {
                                conn.rollback();
                            }
                            catch (Exception e)
                            {}
                            conn.close();
                            log.addlog(DBObject.getClass().getName() +
                                               " " + action + " Failed");
                            return false;
                        }

                        //INSERT
                        m = DBObject.getClass().getMethod("insert", null);
                        b = (Boolean) m.invoke(DBObject, null);

                        if (!b.booleanValue())
                        {
                            CError.buildErr(this, "执行插入语句失败");
                            try
                            {
                                conn.rollback();
                            }
                            catch (Exception e)
                            {}
                            conn.close();
                            log.addlog(DBObject.getClass().getName() +
                                               " " + action + " Failed");
                            return false;
                        }
                    }
                    else if (action.equals("BLOBINSERT"))
                    { //add by Alex at 2005.1.12
                        //first,insert a record with a empty_blob , at the same time prepare the param for the second step
                        String tSQL = "";
                        //用于第二步update时的参数
                        String pWhereSQL = " and ";
                        String pTabName = "";
                        String pUpdateField = "";

                        String[] parmStrArr = getBlobInsertStr(o, tSQL,
                                pWhereSQL, pTabName,
                                pUpdateField);

//                        COracleBlob tCOracleBlob = new COracleBlob();
//                        if (!tCOracleBlob.InsertBlankBlobRecord(parmStrArr[0], conn)) {
//                            CError.buildErr(this, "执行插入blob语句失败");
//                            try {
//                                conn.rollback();
//                            } catch (Exception e) {}
//                            conn.close();
//                            log.addlog(
//                                    "COracleBlob.InsertBlankBlobRecord" +
//                                    " " + action + " Failed");
//                            return false;
//                        }

                        //second,using UpdateBlob method to update the empty_blob with a InputStream object from the schema
                        String aClassName = o.getClass().getName();
                        Schema s = (Schema) o;

                        m = o.getClass().getMethod("get" + parmStrArr[2], null);
                        InputStream ins = (InputStream) m.invoke(o, null);
                        ins.close();
                    }
                    else if (action.equals("BLOBUPDATE"))
                    { //add by Alex at 2005.1.12
                        //first,prepare the param for the UpdateBlob
                        String tSQL = "";
                        //用于第二步update时的参数
                        String pWhereSQL = " and ";
                        String pTabName = "";
                        String pUpdateField = "";

                        String[] parmStrArr = getBlobInsertStr(o, tSQL,
                                pWhereSQL, pTabName,
                                pUpdateField);

                        //second,using UpdateBlob method to update the blobfield with a InputStream object from the schema
//                        COracleBlob tCOracleBlob = new COracleBlob();
                        String aClassName = o.getClass().getName();
                        Schema s = (Schema) o;

                        m = o.getClass().getMethod("get" + parmStrArr[2], null);
                        InputStream ins = (InputStream) m.invoke(o, null);
                        ins.close();
                    }
                    else if (action.equals("BLOBDELETE"))
                    { //add by Alex at 2005.1.12
//                        log.addlog(SysConst.DBTYPE);
                        m = DBObject.getClass().getMethod("delete", null);
                        Boolean b = (Boolean) m.invoke(DBObject, null);

                        if (!b.booleanValue())
                        {
                            CError.buildErr(this, "执行删除语句失败");
                            try
                            {
                                conn.rollback();
                            }
                            catch (Exception e)
                            {}
                            conn.close();
                            log.addlog(DBObject.getClass().getName() +
                                               " " + action + " Failed");
                            return false;
                        }

                    }

                } //end of while
            }

            //数据提交:为保正事务一致性所有数据准备完毕后一次性提交.
            if (commitFlag)
            {
                conn.commit();
                conn.close();
                conn = null;
//                log.addlog("---End Committed---");
            }
            else
            {
                log.addlog(
                        "---End Datebase Operation, but not Commit in AutoBLS---");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            // @@错误处理
            CError.buildErr(this, e.toString());
            try {
                conn.rollback();
            } catch (Exception ex) { }

            try {
                conn.close();
            } catch (Exception ex) { }

            conn = null;
            return false;
        }
        return true;
    }


    /**
     * 获取Blob操作所需信息公共方法
     * @param o Object
     * @param tSQL String
     * @param pTabName String
     * @param pUpdateField String
     * @param pWhereSQL String
     * @return String[]
     */
    private static String[] getBlobInsertStr(Object o, String tSQL, String pTabName,
                                      String pUpdateField, String pWhereSQL)
    {
        String aClassName = o.getClass().getName();

        if (aClassName.endsWith("Schema"))
        {
            Schema s = (Schema) o;
            String[] pk = s.getPK();
            pTabName = aClassName.substring(aClassName.
                                            lastIndexOf(".") + 1,
                                            aClassName.lastIndexOf("S"));

            //String ColPart = "( ";
            String ValPart = "values(";
            String mark = "'";

            int nFieldCount = s.getFieldCount();
            int jj = 0;

            for (int i = 0; i < nFieldCount - 1; i++)
            { //只循环（nFieldCount-1）次，使blob字段不加入循环
                String strFieldName = s.getFieldName(i);
                String strFieldValue = s.getV(i);
                for (int ii = 0; ii < pk.length; ii++)
                {
                    if (strFieldName.equals(pk[ii]))
                    {
                        pWhereSQL += strFieldName + " = '" + strFieldValue +
                                "' and ";
                    }
                }
                int nFieldType = s.getFieldType(i);
                boolean bFlag = false;

                switch (nFieldType)
                {
                    case Schema.TYPE_STRING:
                    case Schema.TYPE_DATE:
                        if (!strFieldValue.equals("null"))
                        {
                            strFieldValue = mark + strFieldValue +
                                            mark;
                            bFlag = true;
                        }
                        break;
                    case Schema.TYPE_DOUBLE:
                    case Schema.TYPE_FLOAT:
                    case Schema.TYPE_INT:
                        bFlag = true;
                        break;
                    default:
                        bFlag = false;
                        break;
                }

                if (bFlag)
                {
                    jj++;
                    if (jj != 1)
                    {
                        //ColPart += ",";
                        ValPart += ",";
                    }
                    //ColPart += strFieldName;
                    ValPart += strFieldValue;
                }
            } // end of for
            //ColPart += " )";
            ValPart += ",";
            ValPart += "empty_blob()"; //添加empty_blob
            ValPart += ")";
            tSQL = "insert into " + pTabName + " " +
                   ValPart;
            if (jj == 0)
            {
                tSQL = "";
            }
            pUpdateField = s.getFieldName(nFieldCount - 1);
            if (pWhereSQL.lastIndexOf("and") != -1)
            {
                pWhereSQL = "and " +
                            pWhereSQL.substring(0, pWhereSQL.lastIndexOf("and"));

            }
        }
        else
        {
            return null;
        }
        String[] returnStr = new String[4];
        returnStr[0] = tSQL;
        returnStr[1] = pTabName;
        returnStr[2] = pUpdateField;
        returnStr[3] = pWhereSQL;
        return returnStr;
    }

    public static void main(String[] args)
    {
        PubSubmit pubSubmit1 = new PubSubmit();
//        LPEdorPrintSchema tLPEdorPrintSchemaMain = new LPEdorPrintSchema();
//        tLPEdorPrintSchemaMain.setEdorNo("410110001000065");
//        tLPEdorPrintSchemaMain.setManageCom("86");
//        tLPEdorPrintSchemaMain.setPrtFlag("N");
//        tLPEdorPrintSchemaMain.setPrtTimes(0);
//        tLPEdorPrintSchemaMain.setMakeDate(PubFun.getCurrentDate());
//        tLPEdorPrintSchemaMain.setMakeTime(PubFun.getCurrentTime());
//        tLPEdorPrintSchemaMain.setOperator("001");
//        tLPEdorPrintSchemaMain.setModifyDate(PubFun.getCurrentDate());
//        tLPEdorPrintSchemaMain.setModifyTime(PubFun.getCurrentTime());
//        com.sinosoft.utility.XmlExport xmlexport = new com.sinosoft.utility.
//                XmlExport();
//        xmlexport.createDocument("", "");
//        InputStream ins = xmlexport.getInputStream();
//        tLPEdorPrintSchemaMain.setEdorInfo(ins);
        VData mResult = new VData();//BLOBUPDATE
//        MMap map = new MMap();//BLOBINSERT
//        map.put(tLPEdorPrintSchemaMain, "BLOBDELETE");
//        mResult.addElement(map);
//        pubSubmit1.submitData(mResult, "");

        MMap map = new MMap();//BLOBINSERT
        map.put("select 1 from dual WHERE 1=1", "SELECT");
        mResult.addElement(map);
        pubSubmit1.submitData(mResult, "");

    }
}
