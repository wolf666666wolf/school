package com.sinosoft.utility;
import com.sinosoft.lis.log.*;

public class SQLString
{
    // @Constructor
    public SQLString(String t)
    {
        TableName = t;
    }

    // @Field
    private String TableName;
//    private String WherePart;
    private StringBuffer WherePart = new StringBuffer(256);
//    private String PKWherePart;
    private StringBuffer PKWherePart = new StringBuffer(256);
//    private String UpdPart;
    private StringBuffer UpdPart = new StringBuffer(256);
//    private String InsPart;
    private StringBuffer InsPart = new StringBuffer(256);
//    private String mSql;
    private StringBuffer mSql = new StringBuffer(256);
    private static final String mark = "'";

    /**
     * 获得数据库操作sql
     * @param flag int
     * @param s Schema
     */
    public void setSQL(int flag, Schema s)
    {
        switch (flag)
        {
            case 1:
                // insert
                this.setInsPart(s);
                if (InsPart.equals(""))
                {
//                    mSql = "";
                    mSql.setLength(0);
                }
                else
                {
//                    mSql = "insert into " + TableName + " " + InsPart;
                    mSql.append("insert into ");
                    mSql.append(TableName);
                    mSql.append(" ");
                    mSql.append(InsPart);
                }
                break;
            case 2:
                // update (by Primary Key)
                this.setUpdPart(s);
                this.setPKWherePart(s);
                if (UpdPart.equals(""))
                {
//                    mSql = "";
                    mSql.setLength(0);
                }
                else
                {
//                    mSql = "update " + TableName + " " + UpdPart + " " + PKWherePart;
                    mSql.append("update ");
                    mSql.append(TableName);
                    mSql.append(" ");
                    mSql.append(UpdPart);
                    mSql.append(" ");
                    mSql.append(PKWherePart);
                }
                break;
            case 3:
                // delete
                this.setWherePart(s);
//                mSql = "delete from " + TableName + " " + WherePart;
                mSql.append("delete from ");
                mSql.append(TableName);
                mSql.append(" ");
                mSql.append(WherePart);
                break;
            case 4:
                // delete (by Primary Key)
                this.setPKWherePart(s);
//                mSql = "delete from " + TableName + " " + PKWherePart;
                mSql.append("delete from ");
                mSql.append(TableName);
                mSql.append(" ");
                mSql.append(PKWherePart);
                break;
            case 5:
                // select
                this.setWherePart(s);
//                mSql = "select * from " + TableName + " " + WherePart;
                mSql.append("select * from ");
                mSql.append(TableName);
                mSql.append(" ");
                mSql.append(WherePart);
                break;
            case 6:
                // select (by Primary Key)
                this.setPKWherePart(s);
//                mSql = "select * from " + TableName + " " + PKWherePart;
                mSql.append("select * from ");
                mSql.append(TableName);
                mSql.append(" ");
                mSql.append(PKWherePart);
                break;
            case 7:
                // select Count
                this.setWherePart(s);
//                mSql = "select count(*) from " + TableName + " " + WherePart;
                mSql.append("select count(1) from ");
                mSql.append(TableName);
                mSql.append(" ");
                mSql.append(WherePart);
                break;
        }
    }

    public String getSQL()
    {
//        log.addlog("mSql: " + mSql.toString());
        return mSql.toString();
    }

    public String getSQL(int sqlFlag, Schema s)
    {
        if (sqlFlag == 5 || sqlFlag == 6)
        {
            this.setSQL(sqlFlag, s);
        }
        else
        {
//            mSql = "";
            mSql.setLength(0);
        }
//        log.addlog("mSql: " + mSql.toString());
        return mSql.toString();
    }

    /**
     * 通过 Primary Key 组成 WherePart
     * @param s Schema
     */
    public void setPKWherePart(Schema s)
    {
        log.addlog("进入更新后的setPKWherePart");
//        PKWherePart = "where";
        PKWherePart.append("where");

        String[] pk = s.getPK();
        int n = pk.length;

        String strFieldName = "";
        StringBuffer strFieldValue = null;
        for (int i = 0; i < n; i++)
        {
            strFieldName = pk[i];
//            String strFieldValue = s.getV(strFieldName);
            strFieldValue = new StringBuffer(100);

            int nFieldType = s.getFieldType(strFieldName);
//            String wp = "";
            switch (nFieldType)
            {
                case Schema.TYPE_STRING:
                case Schema.TYPE_DATE:
//                    strFieldValue = mark + strFieldValue + mark;
                    strFieldValue.append(mark);
                    strFieldValue.append(s.getV(strFieldName));
                    strFieldValue.append(mark);
                    break;
                case Schema.TYPE_DOUBLE:
                case Schema.TYPE_FLOAT:
                case Schema.TYPE_INT:
                    strFieldValue.append(s.getV(strFieldName));
                    break;
                default:
                    log.addlog("出现异常数据类型");
                    break;
            }

            if (i != 0)
            {
//                wp += " and";
                PKWherePart.append(" and");
            }

//            wp += " " + strFieldName + "=" + strFieldValue;
//            PKWherePart += wp;
            PKWherePart.append(" ");
            PKWherePart.append(strFieldName);
            PKWherePart.append("=");
            PKWherePart.append(strFieldValue);
        }
    }

    public String getPKWherePart()
    {
        return PKWherePart.toString();
    }

    /**
     * 通过 Schema 对象组成 WherePart
     * @param s Schema
     */
    public void setWherePart(Schema s)
    {
        log.addlog("进入更新后的setWherePart");
//        WherePart = "where";
        WherePart.append("where");

        int nFieldCount = s.getFieldCount();
        int j = 0;

        String strFieldName = "";
        StringBuffer strFieldValue = null;
        for (int i = 0; i < nFieldCount; i++)
        {
            strFieldName = s.getFieldName(i);
//            String strFieldValue = s.getV(i);
            strFieldValue = new StringBuffer(100);

            int nFieldType = s.getFieldType(i);
            boolean bFlag = false;

            switch (nFieldType)
            {
                case Schema.TYPE_STRING:
                case Schema.TYPE_DATE:
//                    if (!strFieldValue.equals("null"))
//                    {
//                        strFieldValue = mark + strFieldValue + mark;
//                        bFlag = true;
//                    }
                    if (s.getV(i).equals("null"))
                    {
                        strFieldValue.append(s.getV(i));
                    }
                    else
                    {
                        strFieldValue.append(mark);
                        strFieldValue.append(s.getV(i));
                        strFieldValue.append(mark);
                        bFlag = true;
                    }
                    break;

                case Schema.TYPE_DOUBLE:
//                    if (!strFieldValue.equals("0.0"))
                    if (!s.getV(i).equals("0.0"))
                    {
                        strFieldValue.append(s.getV(i));
                        bFlag = true;
                    }
                    break;

                case Schema.TYPE_FLOAT:
//                    if (!strFieldValue.equals("0.0"))
                    if (!s.getV(i).equals("0.0"))
                    {
                        strFieldValue.append(s.getV(i));
                        bFlag = true;
                    }
                    break;

                case Schema.TYPE_INT:
//                    if (!strFieldValue.equals("0"))
                    if (!s.getV(i).equals("0"))
                    {
                        strFieldValue.append(s.getV(i));
                        bFlag = true;
                    }
                    break;

                default:
                    log.addlog("出现异常数据类型");
                    bFlag = false;
                    break;
            }

            if (bFlag)
            {
                j++;
//                String wp = "";
                if (j != 1)
                {
//                    wp += " and";
                    WherePart.append(" and");
                }
//                wp += " " + strFieldName + "=" + strFieldValue;
//                WherePart += wp;
                WherePart.append(" ");
                WherePart.append(strFieldName);
                WherePart.append("=");
                WherePart.append(strFieldValue);
            }
        }
        if (j == 0)
        {
//            WherePart = "";
            WherePart.setLength(0);
        }
    }

    public String getWherePart()
    {
        return WherePart.toString();
    }

    /**
     * 通过 Schema 对象组成 UpdPart
     * @param s Schema
     */
    public void setUpdPart(Schema s)
    {
//        UpdPart = "set ";
        log.addlog("进入更新后的UpdPart");
        UpdPart.append("set ");

        int nFieldCount = s.getFieldCount();

        String strFieldName = "";
        StringBuffer strFieldValue = null;
        for (int i = 0; i < nFieldCount; i++)
        {
            strFieldName = s.getFieldName(i);
//            String strFieldValue = s.getV(i);
            strFieldValue = new StringBuffer(100);

            int nFieldType = s.getFieldType(i);

            switch (nFieldType)
            {
                case Schema.TYPE_STRING:
                case Schema.TYPE_DATE:

//                    if (!strFieldValue.equals("null"))
//                    {
//                        strFieldValue = mark + strFieldValue + mark;
//                    }
                    if (s.getV(i).equals("null"))
                    {
                        strFieldValue.append(s.getV(i));
                    }
                    else
                    {
                        strFieldValue.append(mark);
                        strFieldValue.append(s.getV(i));
                        strFieldValue.append(mark);
                    }
                    break;
                case Schema.TYPE_DOUBLE:
                case Schema.TYPE_FLOAT:
                case Schema.TYPE_INT:
                    strFieldValue.append(s.getV(i));
                    // 不管初始值是0，还是输入0，一律插入数据库。
                    break;
                default:
                    log.addlog("出现异常数据类型");
                    break;
            }

//            String wp = "";
            if (i != 0)
            {
//                wp += ",";
                UpdPart.append(",");
            }
//            wp += strFieldName + "=" + strFieldValue;
//            UpdPart += wp;
            UpdPart.append(strFieldName);
            UpdPart.append("=");
            UpdPart.append(strFieldValue);
        }
    }

    public String getUpdPart()
    {
        return UpdPart.toString();
    }

    /**
     * 通过 Schema 对象组成 InsPart
     * @param s Schema
     */
    public void setInsPart(Schema s)
    {
//        log.addlog("进入更新后的InsPart");
//        String ColPart = "( ";
//        String ValPart = "values ( ";
        StringBuffer ColPart = new StringBuffer(256);
        ColPart.append("( ");
        StringBuffer ValPart = new StringBuffer(256);
        ValPart.append("values ( ");

        int nFieldCount = s.getFieldCount();
        int j = 0;

        String strFieldName = "";
        StringBuffer strFieldValue = null;
        for (int i = 0; i < nFieldCount; i++)
        {
            strFieldName = s.getFieldName(i);
//            String strFieldValue = s.getV(i);
            strFieldValue = new StringBuffer(100);

            int nFieldType = s.getFieldType(i);
            boolean bFlag = false;
            switch (nFieldType)
            {
                case Schema.TYPE_STRING:
                case Schema.TYPE_DATE:
//                    if (!strFieldValue.equals("null"))
//                    {
//                        strFieldValue = mark + strFieldValue + mark;
//                        bFlag = true;
//                    }
                    if (s.getV(i).equals("null"))
                    {
                        strFieldValue.append(s.getV(i));
                    }
                    else
                    {
                        strFieldValue.append(mark);
                        strFieldValue.append(s.getV(i));
                        strFieldValue.append(mark);
                        bFlag = true;
                    }
                    break;
                case Schema.TYPE_DOUBLE:
                case Schema.TYPE_FLOAT:
                case Schema.TYPE_INT:
                    strFieldValue.append(s.getV(i));
                    bFlag = true;
                    break;
                default:
                    bFlag = false; // 不支持的数据类型
                    break;
            }

            if (bFlag)
            {
                j++;
//                String wp = "";
                if (j != 1)
                {
//                    ColPart += ",";
//                    ValPart += ",";
                    ColPart.append(",");
                    ValPart.append(",");
                }
//                ColPart += strFieldName;
//                ValPart += strFieldValue;
                ColPart.append(strFieldName);
                ValPart.append(strFieldValue);
            }
        }
//        ColPart += " )";
//        ValPart += " )";
        ColPart.append(" )");
        ValPart.append(" )");

//        InsPart = ColPart + " " + ValPart;
        InsPart.append(ColPart);
        InsPart.append(" ");
        InsPart.append(ValPart);
        if (j == 0)
        {
//            InsPart = "";
            InsPart.setLength(0);
        }
    }

    public String getInsPart()
    {
        return InsPart.toString();
    }
}
