package com.sinosoft.service;

import com.sinosoft.utility.VData;

public abstract class CovBase implements Runnable
{
	/** �Ƿ���б�־*/
    private boolean bNotInUse = false;

    public ServiceResult mServiceResult = new ServiceResult();
    public boolean getInUse()
    {
        return bNotInUse;
    }
    public void setInUse()
    {
        bNotInUse = true;
    }
    /**ʹ����ɺ��ͷ�*/
    public void close()
    {
        bNotInUse = false;
    }
    
    public void setServiceResult(ServiceResult tServiceResult)
    {
    	this.mServiceResult= tServiceResult ;
    }
    //public abstract void setVData(VData tVData);
    public abstract void setObject(Object tObject);
}
