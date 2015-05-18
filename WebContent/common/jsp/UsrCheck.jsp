<%@page import="com.sinosoft.lis.pubfun.GlobalInput"%>
<%
//添加页面控件的初始化。
GlobalInput tGI = new GlobalInput();
tGI = (GlobalInput) session.getValue("GI");
if(tGI==null)
{
	tGI= new GlobalInput();
	%>
	
<script language=javascript>
try
{
top.window.location ="../admin.jsp";
}
catch (exception)
{
top.window.location ="../admin.jsp";
}
</script>
	<%
}
%>
<%
    
%>