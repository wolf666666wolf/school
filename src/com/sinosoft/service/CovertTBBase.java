package com.sinosoft.service;

import com.sinosoft.utility.VData;
public abstract class CovertTBBase extends CovBase
{
    protected String mManageCom = "";

    public void setObject(Object tObject)
    {
        mManageCom = (String) tObject;
    }
    public void run()
    {
        Convert();
        close();
    }

    public abstract boolean Convert();
}
