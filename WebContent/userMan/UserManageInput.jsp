<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="../common/jsp/UsrCheck.jsp"%>

<html> 
<%
//程序名称：
//程序功能：
//创建日期：2007-07-25 11:10:36
//创建人  ：杨亚峰
//更新记录：  更新人    更新日期     更新原因/内容
%>
<head > 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <SCRIPT src="../common/javascript/Common.js" ></SCRIPT>
  <SCRIPT src="../common/javascript/MulLine.js"></SCRIPT>
  <SCRIPT src="../common/cvar/CCodeOperate.js"></SCRIPT>
  
  <SCRIPT src="../common/easyQueryVer3/EasyQueryVer3.js"></SCRIPT>
  <SCRIPT src="../common/javascript/VerifyInput.js"></SCRIPT>
    <SCRIPT src="../common/Calendar/Calendar.js"></SCRIPT>
  <LINK href="../common/css/Project.css" rel=stylesheet type=text/css>
  <LINK href="../common/css/mulLine.css" rel=stylesheet type=text/css>

  <SCRIPT src="UserManage.js"></SCRIPT> 
  <%@include file="UserManageInit.jsp"%>
  
  <title>用户权限密码信息维护 </title>
</head>

<%
  GlobalInput tGlobalInput = new GlobalInput(); 
  tGlobalInput = (GlobalInput)session.getValue("GI");
%>

<script>

  comCode = "<%=tGlobalInput.ComCode%>";
  dealType = "<%=request.getParameter("DealType")%>";	
</script>

<body  onload="initForm();" >
  <!--form action="./UserManageSave.jsp" method=post name=fm target="fraTitle"-->
  <form method=post name=fm target="fraTitle">
    <%@include file="../common/jsp/InputButton.jsp"%>
    
    <!-- 保单信息部分 -->
  <table class= common border=0 width=100%>
    <tr>
			<td class= titleImg align= center>客户信息：</td>
		</tr>
	</table>
	
    <table  class= common align=center>
      	<TR  class= common>
          
          <TD  class= title>
            用户名
          </TD>
          <TD  class= input>
            <INPUT class = common name=UserCode>
          </TD>
          
          
          <TD  class= title>
            处理类型
          </TD>
          <TD  class= input>
            <Input class="codeno" name=dealLX verify="处理类型|notnull" CodeData="0|^1|用户密码解锁^2|已建帐户确认^3|过期密码时间重置"  ondblclick="return showCodeListEx('xitong',[this,dealLXName],[0,1]);" onkeyup="return showCodeListKeyEx('xitong',[this,dealLXName],[0,1]);" onchange=""><input class=codename name=dealLXName readonly=true></TD>  
      
          </TD>
         

        </TR>
        
    </table>
    <br><INPUT VALUE="查  询" TYPE=button class=cssButton onclick="easyQueryClick();"><br><br>  
    
    <hr>  
    <!-- 生成送银行文件 fraSubmit-->
    <table class= common border=0 width=100%>
    	<tr>
			<td class= titleImg align= center>请选择信息：</td>
  		</tr>
  	</table>   
        
    <!-- 批次号信息（列表） -->
    <table>
    	<tr>
        	<td class=common>
			    <IMG  src= "../common/images/butExpand.gif" style= "cursor:hand;" OnClick= "showPage(this,divBank1);">
    		</td>
    		<td class= titleImg>
    			 基本客户信息：
    		</td>
    	</tr>
    </table>
  	<Div  id= "divBank1" style= "display: ''">
      	<table  class= common>
          	<tr  class= common>
      	  		<td text-align: left colSpan=1>
  					<span id="spanBankGrid" >
  					</span> 
  				</td>
  			</tr>
  		</table>
  	</div>
  	
    <Div id= "divPage" align=center style= "display: '' ">
    	<center>
    		<INPUT CLASS=cssButton VALUE="首  页" TYPE=button onclick="turnPage.firstPage();"> 
    		<INPUT CLASS=cssButton VALUE="上一页" TYPE=button onclick="turnPage.previousPage();"> 					
    		<INPUT CLASS=cssButton VALUE="下一页" TYPE=button onclick="turnPage.nextPage();"> 
    		<INPUT CLASS=cssButton VALUE="尾  页" TYPE=button onclick="turnPage.lastPage();">
		</center>
    </Div>

    
    <br>
    
    <!--
    <table  class= common>
    <TR  class= common>
      <TD  class= title>
        请输入要生成的文件名称:
      </TD>
      <TD  class= input>
        <Input class= common5 TYPE=file name=FileName verify="文件名称|notnull" > 
      </TD>           
    </TR>
    </table>
    -->
    
    <Div id=DivFileDownload style="display:'none'">
      <A id=fileUrl href=""></A>
    </Div>
    
       <!--INPUT VALUE="test" class= cssButton TYPE=button onclick="testtt()"-->
    <INPUT VALUE= "确           认" class= cssButton TYPE=button onclick="submitForm()">
    <INPUT VALUE="" TYPE=hidden name=tempUserCode>
    <INPUT VALUE="" TYPE=hidden name=checkCart>
    <input type=hidden id="fmtransact" name="fmtransact">

    
    <br><br><hr>
	     		  					
    <br>
  
  
  </form>
  <span id="spanCode"  style="display: none; position:absolute; slategray"></span>
  
</body>
</html>

