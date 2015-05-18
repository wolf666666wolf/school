/**
 * Copyright (c) 2008 sinosoft  Co. Ltd.
 * All right reserved.
 */
package com.sinosoft.service;

import com.sinosoft.utility.CErrors;
import com.sinosoft.utility.VData;
/**
 * <p>Title: Webҵ��ϵͳ</p>
 * <p>Description:����Ϊweb server ��App server����ҵ�����Ϣ������</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: Sinosoft</p>
 * @author lk
 * @version 1.0
 * @date: 2008-05-20
 */
public class Message implements IMessage 
{
	/**
	 * ���л�
	 */
	private static final long serialVersionUID = 1L;
	/**
     * web��ҵ������
     */	
	private VData mVData=null;
    /**
     * ��������
     */		
	private String mOperate=null;
    /**
     * ҵ������
     */		
	private String mBusiName=null;
    /**
     * �����Ƿ�ɹ�
     */		
	private boolean mSuccess=false;
    /**
     * ����Ա
     */	
	private String mUser=null;
    /**
     * �������
     */		
	private String mManageCom=null;
    /**
     * ����ʱ����
     */		
	private CErrors mCErrors=null;
    /**
     * ������
     */		
	private VData mResult=null;
	
	public String getBusiName() 
	{
		return this.mBusiName;
	}
	
	public CErrors getCErrors() 
	{
		return this.mCErrors;
	}

	public String getManageCom() 
	{
		return this.mManageCom;
	}
	
	public String getOperate() 
	{
		return this.mOperate;
	}
	
	public VData getResult() 
	{
		return this.mResult;
	}

	public boolean getSuccess() 
	{
		return this.mSuccess;
	}

	public String getUser() 
	{
		return this.mUser;
	}

	public VData getVData() 
	{
		return this.mVData;
	}
	
	public void setBusiName(String aBusiName) 
	{
	    this.mBusiName=aBusiName;
	}

	public void setCErrors(CErrors aCErrors) 
	{
        this.mCErrors=aCErrors;
	}

	public void setManageCom(String aManageCom) 
	{
        this.mManageCom=aManageCom;
	}

	public void setOperate(String aOperate) 
	{
	    this.mOperate=aOperate;
	}

	public void setResult(VData aResult) 
	{
        this.mResult=aResult;
	}

	public void setSuccess(boolean aSuccess) 
	{
        this.mSuccess=aSuccess;
	}

	public void setUser(String aUser)
	{
        this.mUser=aUser;
	}

	public void setVData(VData aVData) 
	{
        this.mVData=aVData;
	}

}
