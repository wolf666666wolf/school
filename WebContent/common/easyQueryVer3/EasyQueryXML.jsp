<%@page contentType="text/html;charset=UTF-8" %>
<%@page import="java.io.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="javax.servlet.http.*"%>
<%@page import="java.util.*"%> 
<%@page import="com.sinosoft.utility.*"%>
<%@include file="./EasyQueryKernel.jsp"%>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
String strResult = "";

String tReferer = request.getHeader("Referer");//获得申请此页的url信息
System.out.println(request.getHeader("Referer"));
String tHost = "http://" + request.getHeader("host");//获取应用的url信息

//校验请求此页面的url是否是应用框架内的页面
if(tReferer == null || !tReferer.startsWith(tHost))
{
	System.out.println("不是一个地址的请求服务");
%>
<script language=jscript.encode>
session = null;
try
{
	CollectGarbage();
}
catch(ex)
{
	alert(ex.message);
}
 	top.window.location ="../../index.jsp";
</script>  
<%
//	response.sendRedirect("../../index.jsp");
}
else
{
	request.setCharacterEncoding("UTF8");
	//设置request的接受字符集
	String strSql = request.getParameter("strSql");
	//System.out.println("strSql is :"+strSql);

	strSql=java.net.URLDecoder.decode(strSql,"UTF-8"); 
	String strStart = request.getParameter("strStart");
	strStart=java.net.URLDecoder.decode(strStart,"UTF-8"); 

	//System.out.println("strStart is :"+strStart);
	String LargeFlag = request.getParameter("LargeFlag");
	LargeFlag=java.net.URLDecoder.decode(LargeFlag,"UTF-8"); 

	//System.out.println("LargeFlag is :"+LargeFlag);
	
	try
	{
		strResult = easyQueryKernel(strSql, strStart,LargeFlag);
		out.write(strResult);
//		System.out.println("************"+strResult);
	}
	catch(Exception ex)
	{
		System.out.println("easyQueryKernel throw Errors!\n" + "easyQuerySql:" + strSql +"\nStartRecord:" +strStart);
	}
	
	try
	{
		//做了一步特殊字符替换，可否考虑先判定是否含有特殊字符，然后再作处理
		//对于有回车的数据取出的可能性太小了
		System.out.println("************"+strResult);
//		System.out.println("************"+(strResult.indexOf("\"")!= -1 || strResult.indexOf("'")!= -1 || strResult.indexOf("\n")!= -1));
		if(strResult.indexOf("\"")!= -1 || strResult.indexOf("'")!= -1 || strResult.indexOf("\n")!= -1)
		{
			String strPath = application.getRealPath("config//Conversion.config");
			System.out.println("@@@@@@@@@@@@@@@@@@"+strPath);
	      	//strResult = StrTool.Conversion(strResult, strPath);
		}
	}
	catch(Exception ex)
	{
		System.out.println("not found Conversion.config ");
	}
}
%>