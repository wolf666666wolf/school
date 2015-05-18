﻿package com.sinosoft.utility;

public class DBPack
{
    private DBOper db = null;

    /** 错误处理类，每个需要错误处理的类中都放置该类 */
    public CErrors mErrors = new CErrors();

    public DBPack()
    {
    }

    public DBPack(String aSchName)
    {
        db = new DBOper(aSchName);
    }

    public boolean update(Schema s)
    {
        if (!db.update(s))
        {
            mErrors.copyAllErrors(db.mErrors);
            return false;
        }
        return true;
    }

    public static void main(String[] args)
    {
        DBPack DBPack1 = new DBPack();
    }
}
