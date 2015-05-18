/**
 * Copyright (c) 2008 sinosoft  Co. Ltd.
 * All right reserved.
 */
package com.sinosoft.service;

import com.sinosoft.utility.CErrors;
import com.sinosoft.utility.VData;

/**
 * <p>Title: Webҵ��ϵͳ</p>
 * <p>Description:�ýӿ�Ϊweb server ��App server����ҵ�����Ϣ������</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: Sinosoft</p>
 * @author lk
 * @version 1.0
 * @date: 2008-05-20
 */
public interface IMessage extends java.io.Serializable
{
	
	public void setVData(VData aVData);

	public VData getVData();	
	
	public void setOperate(String aOperate);

	public String getOperate();	
	
	public void setBusiName(String aBusiName);
	
	public String getBusiName();		
	
	public void setSuccess(boolean aSuccess);
	
	public boolean getSuccess();	
	
	public void setUser(String aUser);
	
	public String getUser();		
	
	public void setManageCom(String aManageCom);
	
	public String getManageCom();	
	
	public void setCErrors(CErrors aCErrors);
	
	public CErrors getCErrors();	
	
	public void setResult(VData aResult);
	
	public VData getResult();		
}
