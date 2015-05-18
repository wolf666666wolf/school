package com.sinosoft.utility;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Types;
import com.sinosoft.lis.log.*;


public class DBSQL
{

    private String mTable = null;
    private String mSQL = null;
    public CErrors mErrors = new CErrors();

    public DBSQL()
    {
    }

    public String getSQL()
    {
        log.addlog("mSQL" + mSQL);
        return mSQL;
    }

    public void setTable(String aTable)
    {
        mTable = aTable;
    }

    public boolean createSQL()
    {
        String tCont = "";

        Connection conn = null;
        conn = DBConnPool.getConnection();
        if (conn == null)
        {
            // @@错误处理
            CError tError = new CError();
            tError.moduleName = "LDCodeBLS";
            tError.functionName = "saveData";
            tError.errorMessage = "数据库连接失败!";
            mErrors.addOneError(tError);
            return false;
        }
        try
        {
//      conn.setAutoCommit(false);
            //获取表的字段信息
            log.addlog("Start 查询...");
            Statement stat = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,
                                                  ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stat.executeQuery("select * from " + mTable);
            ResultSetMetaData meta = rs.getMetaData();
            String tColName = null;
            String tColType = null;
            String tColNull = "";
            String tPriKey = "";
            int dataType;
            for (int i = 1; i <= meta.getColumnCount(); i++)
            {
                //获取字段名和字段类型，根据不同的字段类型实现Oracle到MYSQL数据库数据类型的转换
                tColName = meta.getColumnName(i);
                dataType = meta.getColumnType(i);
                if (dataType == Types.NUMERIC)
                {
                    if ((meta.getPrecision(i) == 38) && (meta.getScale(i) == 0))
                    {
                        tColType = "INTEGER";
                    }
                    else if ((meta.getPrecision(i) == 126) &&
                             (meta.getScale(i) == -127))
                    {
                        tColType = "FLOAT";
                    }
                    else
                    {
                        tColType = meta.getColumnTypeName(i) + "(" +
                                   meta.getPrecision(i) + "," + meta.getScale(i) +
                                   ")";
                    }
                }
                else if (dataType == Types.TIMESTAMP || dataType == Types.DATE)
                {
                    tColType = meta.getColumnTypeName(i);
                }
                else
                {
                    tColType = meta.getColumnTypeName(i) + "(" +
                               meta.getColumnDisplaySize(i) + ")";
                }

                if (meta.isNullable(i) == ResultSetMetaData.columnNoNulls)
                {
                    tColNull = "not null";
                }
                else
                {
                    tColNull = "";
                }
                tCont = tCont + " " + tColName + " " + tColType + " " +
                        tColNull + ",";

            }
            rs.close();
            stat.close();
            //生成Create语句中主键信息
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet pkrs = dbmd.getPrimaryKeys(null, null, mTable.toUpperCase());
            String tPriCont = null;
            int i = 0;
            while (pkrs.next())
            {
                i++;
                if (i == 1)
                {
                    tPriKey = " constraint " + pkrs.getObject(6).toString() +
                              " primary key (";
                    tPriCont = pkrs.getObject(4).toString();
                }
                else
                {
                    tPriCont = tPriCont + ", " + pkrs.getObject(4).toString();
                }
//        tPriKey = " constraint " + pkrs.getObject(6).toString() + " primary key (" + pkrs.getObject(4).toString() + ")";
            }
            tPriKey = tPriKey + tPriCont + ")";
            conn.close();
            mSQL = "create table " + mTable + " ( " + tCont + tPriKey + " ) ";
        }
        catch (Exception ex)
        {
            log.addlog(mTable + "表不存在！");
            ex.printStackTrace();
            // @@错误处理
            CError tError = new CError();
            tError.moduleName = "LDCodeBLS";
            tError.functionName = "submitData";
            tError.errorMessage = ex.toString();
            this.mErrors.addOneError(tError);
            try
            {
                conn.rollback();
                conn.close();
            }
            catch (Exception e)
            {}
            return false;
        }

        return true;
    }

    public static void main(String[] args)
    {

    }
}
