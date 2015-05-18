<%@include file="../common/jsp/UsrCheck.jsp"%>
<%@page contentType="text/html;charset=UTF-8" %>
<%@page import="com.sinosoft.lis.sys.*"%>
<%@page import="com.sinosoft.utility.*"%>
<%
  
  //操作成功提示信息
  String Content = "";
  //成功标志
  String FlagStr = "";
  
  GlobalInput tG = new GlobalInput();
  tG=(GlobalInput)session.getValue("GI");     //登录信息
  //准备向后台传输的数据
  VData tVData = new VData();
  tVData.add(tG);
  String tOperate=request.getParameter("o");
  String para = request.getParameter("p");  
  tVData.add(para);

  //调用后台类进行业务处理
 QuickSaveBL tQuickSaveBL=new  QuickSaveBL();
 if(!tQuickSaveBL.submitData(tVData,tOperate))
 {
   Content = "执行操作失败";
   FlagStr = "Fail";
 }
 else
 {
	Content = "操作成功";
	FlagStr = "Succ";
 }
 
  %>
  <html>
<script language="javascript">
 parent.fraInterface.afterSubmit('<%=FlagStr%>','<%=Content%>');
</script>
</html>
