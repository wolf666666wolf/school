package com.sinosoft.service;

public class Service{
	/**��Ӧҵ����������*/
	private String className;
	/**������������*/
	private String sverviceName;
	/**ҵ����������*/
	private String scope;
	/**Ȩ������*/
	private String[] roles;
	/**ҵ����*/
	private Handle serviceHandle;
	
	public void setClassName(String className)
	{
		this.className = className;
	}
	public String getClassName()
	{
		return className;
	}
	
	public void setServiceName(String serviceName)
	{
		this.sverviceName = serviceName;
	}
	public String getServiceName()
	{
		return this.sverviceName;
	}
	
	public void setScope(String scope)
	{
		this.scope = scope;
	}
	public String getScope()
	{
		return this.scope;
	}
	
	public void setRoles(String[] roles)
	{
		this.roles = roles;
	}
	public String[] getRoles()
	{
		return this.roles;
	}
	
	public void setHandle(Handle handle)
	{
		this.serviceHandle = handle;
	}
	/**��ȡҵ������*/
	public Handle getHandle() {
		return serviceHandle;
	}

}
