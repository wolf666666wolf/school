
<%
//程序名称：codeQueryWindow.jsp
//程序功能：校验CODE类型查询等待页面
//创建日期：2002-10-18
//创建人  ：胡 博
//更新记录：  更新人    更新日期     更新原因/内容
%>
<%@include file="./CodeQueryKernel.jsp"%>
<%@page import="com.sinosoft.lis.pubfun.*"%>

<%
  String strResult = "";
  String codeType="";
  String codeField="";
  String codeConditon ="";
  try {
  codeType = request.getParameter( "codeType");
  if(request.getParameter( "codeField" )!=null)
  codeField = request.getParameter( "codeField" );
  if(request.getParameter( "codeConditon" )!=null)
   codeConditon = request.getParameter( "codeConditon" );
  System.out.println("\n\n---codeQuery Start---\nCodeType:" + codeType);
  } catch(Exception ex) {
    System.out.println("codeQuery throw Errors!");	
  }
  
   try {
    GlobalInput tGI = new GlobalInput();
    tGI = (GlobalInput)session.getValue("GI");
    if(codeConditon==null || codeConditon.equals("")||codeField==null || codeField.equals(""))
     strResult = codeQueryKernel(codeType, tGI);
    else
     strResult = codeQueryKernel2(codeType,codeField,codeConditon, tGI);
  } catch(Exception ex) {
    System.out.println("codeQuery throw Errors!");	
  }
  System.out.println("---codeQuery End---\n\n");
			
  if (strResult == "") {
%>
  <script language="javascript">   
    window.returnValue = "Code Query Faile";
    window.close();			   				   		
  </script>
<%			
  } else {
%>
  <script language="javascript">
    window.returnValue = '<%=strResult%>';
    window.close();						   		
  </script>
<%
  }		
%>
