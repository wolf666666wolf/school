<%@include file="../common/jsp/UsrCheck.jsp"%>
<%@page contentType="text/html;charset=UTF-8" %>
<%@page import="com.sinosoft.service.*"%>
<%@page import="com.sinosoft.utility.*"%>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
request.setCharacterEncoding("UTF8");

  //操作成功提示信息
  String Content = "";
  //成功标志
  String FlagStr = "";
  
  String returnString = "";
  GlobalInput tG = new GlobalInput();
  tG=(GlobalInput)session.getValue("GI");     //登录信息

  BusinessDelegate tBusinessDelegate = BusinessDelegate.getBusinessDelegate();
	String action = request.getParameter("a");
	String operate = request.getParameter("o");
	VData vd = new VData();
	vd.add(response);
	vd.add(request);
	vd.add(tG);
	if(!tBusinessDelegate.submitData(vd, operate, action)) 
	{
		   Content = "执行操作失败";
		   FlagStr = "Fail";
		 }
		 else
		 {
			Content = "操作成功";
			FlagStr = "Succ";
			try{
			returnString = (String)tBusinessDelegate.getResult().getObject(0);
			}
			catch(Exception e){}
		 }
 
  %>
  <html>
<script language="javascript">
 parent.fraInterface.afterSubmit('<%=FlagStr%>','<%=Content%>','<%=returnString%>');
</script>
</html>
