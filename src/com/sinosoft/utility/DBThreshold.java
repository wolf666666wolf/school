package com.sinosoft.utility;

/**
 * <p>Description: 结果集记录限制工具类　</p>
 * 在生产环境下，经常会出现对于大表只有很少查询条件或者根本没有查询条件的SQL语句。
 * 直接的后果就是生产环境经常出现内存溢出甚至服务停机。这个类的作用就是将这样的
 * 查询语句都找出来。
 */
public class DBThreshold {
    private static int THRESHOLD = 10000;

    private int m_count = 0;
    private int m_threshold = 0;
    private String m_strSQL = null;

    public DBThreshold() {
        this.m_count = 0;
        this.m_threshold = DBThreshold.THRESHOLD;
    }

    public DBThreshold(int threshold) {
        this.m_count = 0;
        this.m_threshold = threshold;
    }

    public void setSQL(String strSQL) {
        if( strSQL != null ) {
            this.m_strSQL = strSQL;
        }
    }

    public void reset() {
        this.m_count = 0;
    }

    public void increase() {
        if( this.m_count >= 0 ) {
            this.m_count++;
        }

        if( this.m_count > this.m_threshold ) {
            this.m_count = -1;
            try {
                if( this.m_strSQL != null ) {
                    throw new Exception(this.m_strSQL);
                } else {
                    throw new Exception("Too many records, but the SQL hasn't been recorded");
                }
            } catch (Exception ex) {
            	ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        DBThreshold dbthreshold = new DBThreshold(2);

        dbthreshold.increase();
        dbthreshold.increase();
        dbthreshold.increase();
    }
}
