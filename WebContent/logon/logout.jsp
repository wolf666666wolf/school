<%@ page contentType="text/html;charset=UTF-8" %>
<%
//******************************************************
// 程序名称：Logout.jsp
// 程序功能:：
// 最近更新人：DingZhong
// 最近更新日期：2002-12-22
//******************************************************
%>
<%@page import="com.sinosoft.lis.pubfun.GlobalInput"%>
<%@page import="com.sinosoft.utility.VData"%>
<%@page import="com.sinosoft.lis.logon.logoutUI"%>
<%@page import="com.sinosoft.lis.log.*"%>
<%
session.invalidate();
//log.addlog("start logout");
//log.addlog("start clear data...");
try {
	GlobalInput tG1 = new GlobalInput();
	tG1 = (GlobalInput)session.getValue("GI");
//	log.addlog(tG1.ComCode);
	//VData inputData = new VData();
	//inputData.addElement(tG1);
	//logoutUI tlogoutUI = new logoutUI(); 
//	tlogoutUI.submitData(inputData,"LogOutProcess");
//	log.addlog("completed clear data");
}
catch (Exception exception)
{
	log.addlog("Log out error ...");
}
%>
<script language=javascript>
session = null;
//top.window.location ="../index.jsp";
//核心系统与兼业需同时支持
top.window.navigate(top.window.location);
</script>  