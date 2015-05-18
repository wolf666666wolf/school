package com.sinosoft.service;

import com.sinosoft.utility.VData;

public interface CovertInteface extends Runnable {
	public boolean getInUse();
	
	public void setInUse();
	
	public void close();
	
	public void setVData(VData tVData);
}
