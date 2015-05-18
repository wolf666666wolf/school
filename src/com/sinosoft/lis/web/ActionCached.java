package com.sinosoft.lis.web;

import java.util.Hashtable;

public class ActionCached {

	// 一些只可能按单个关键信息查询的信息
	private Hashtable main = new Hashtable();
	// 静态变量
	private static String NOFOUND = "NOFOUND";
	private static ActionCached mac = null;

	// @Constructor
	private ActionCached() 
	{
	}

	public static ActionCached getInstance() 
	{
		if (mac == null) 
		{
			mac = new ActionCached();
		}
		return mac;
	}
	public void refresh() 
	{
		// Kevin 2003-07-29
		// 刷新的时候，可能会出现同步问题，但是机率很小。
		main.clear();		
	}
	public void refresh(String action) 
	{
		Hashtable aht = (Hashtable)main.get(action);
		if(aht!=null)
		{
			aht.clear();
		}
	}	
	public String findCache(String action,String data) 
	{
		Hashtable aht = (Hashtable)main.get(action);
		if(aht==null)
			return null;
		return (String)aht.get(data);
	}
	public void putCache(String action,String data,String result)
	{
		Hashtable aht = (Hashtable)main.get(action);
		if(aht==null)
		{
			aht=new Hashtable();
		}
		aht.put(data, result);
		main.put(action, aht);
	}
}