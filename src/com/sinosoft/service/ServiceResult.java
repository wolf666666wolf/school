package com.sinosoft.service;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class ServiceResult {

	//private List list = new ArrayList();
	private Map tResultMap = new Hashtable();
	public void ServiceResult()
	{
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void setReturnList(Object tKey,Object tValue)
	{
		synchronized (tResultMap) 
		{
			tResultMap.put(tKey,tValue);
		}
	}
	
	public Map getReturnList()
	{
		return this.tResultMap;
	}

}
