<%@page contentType="text/html;charset=UTF-8" %>
<%@page import="com.sinosoft.lis.wx.*"%>
<%
	String at = WXBL.getInstance().get_jsapi_ticket();
%>
<%=at%>