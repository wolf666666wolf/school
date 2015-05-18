<html>
<%@page contentType="text/html;charset=UTF-8" %>
<%@page import="com.sinosoft.utility.*" %>
<%
   request.setCharacterEncoding("GBK");
   String InterFace=StrTool.GBKToUnicode(request.getParameter("interface"));
   String Title=StrTool.unicodeToGBK(request.getParameter("title"));
%>
<head>
<title></title>
<script>	
var vTitle = "<%=Title%>";
document.title = vTitle;
</script>
</head>
<body onBlur='window.focus();'>
	<IFRAME ID=IFrame1 FRAMEBORDER=0 SCROLLING=auto height='100%' WIDTH='100%' SRC="../sys/OpenWindowInterfaceFrame.jsp?interface=<%=InterFace%>"></IFRAME>
</body>
</html> 
