<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@page import="com.sinosoft.lis.log.*"%>

<%
    session.invalidate();
    log.addlog("\t@> 销毁 session, 退出系统 ****************************************");
%>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>退出系统</title>
</head>
<body topmargin="0" ondragstart="return false" oncontextmenu="return false">
    <br><center><font size="2">正在退出，请稍候...</font></center>
</body>
</html>
<script language="JavaScript">try{top.window.close();}catch(ex){}</script>
