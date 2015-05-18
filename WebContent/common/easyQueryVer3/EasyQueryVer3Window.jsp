<%@page contentType="text/html;charset=UTF-8" %>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<%
//程序名称：EasyQueryVer3Window.jsp
//程序功能：查询等待页面，负责调用后台查询，并接收返回结果
//          必须以window.showModalDialog方式打开这个窗口
//创建日期：2002-10-19
//创建人  ：胡博
//更新记录：  更新人    更新日期     更新原因/内容
%>
<%@page import="com.sinosoft.utility.*"%>
<%@include file="./EasyQueryKernel.jsp"%>
<title>正在查询数据库</title>
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<%
//String strSql = new String (request.getParameter("strSql").getBytes("ISO8859_1"));

String strSql = request.getParameter("strSql");
//System.out.println("从新设置的输出："+strSql);

String strStart = request.getParameter("strStart");
String LargeFlag = request.getParameter("LargeFlag");
String strResult = "";

//System.out.println("\n\n---EasyQuery Start---");
try
{
	strResult = easyQueryKernel(strSql, strStart, LargeFlag);
}
catch(Exception ex)
{
	System.out.println("easyQueryKernel throw Errors!\n" + "easyQuerySql:" + strSql +"\nStartRecord:" +strStart);
}
//System.out.println("---EasyQuery End---\n\n");

try
{
	//做了一步特殊字符替换，可否考虑先判定是否含有特殊字符，然后再作处理
	//对于有回车的数据取出的可能性太小了
	if(strResult.indexOf("\"")!= -1 || strResult.indexOf("'")!= -1)
	{
		String strPath = application.getRealPath("config//Conversion.config");
		strResult = StrTool.Conversion(strResult, strPath);
	}
}
catch(Exception ex)
{
	System.out.println("not found Conversion.config ");
}

//System.out.println("strResult is : "+strResult);

if (strResult.equals(""))
{
%>
<script language="javascript">
if (typeof(window.opener) == "object")
{
	window.opener.afterEasyQueryVer3("Easy Query Faile");
	window.close();
}
else
{
	//window.returnValue = "Easy Query Faile";
	window.returnValue = false;
	window.close();
//	strQueryResult = false;
}
</script>
<%
}
else
{
%>
<script language="javascript">
if (typeof(window.opener) == "object")
{
	window.opener.transferQueryResult('<%=strResult%>');
//	window.opener.afterEasyQueryVer3('<%=strResult%>');
	window.opener.afterEasyQueryVer3("ok");
	window.close();
}
else
{
	window.returnValue = '<%=strResult%>';
	window.close();
//	strQueryResult = '<%=strResult%>';
}
</script>
<%
}
%>