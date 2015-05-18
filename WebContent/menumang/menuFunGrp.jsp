<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="../common/jsp/UsrCheck.jsp"%>
<%@page import="com.sinosoft.lis.pubfun.GlobalInput"%>
<%
    GlobalInput tG1 = new GlobalInput();
	tG1=(GlobalInput)session.getValue("GI");
	String Operator = tG1.Operator;;
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <SCRIPT src="../common/javascript/Common.js" ></SCRIPT>
  <SCRIPT src="../common/cvar/CCodeOperate.js"></SCRIPT>
  <SCRIPT src="../common/javascript/MulLine.js"></SCRIPT>
  <SCRIPT src="../common/easyQueryVer3/EasyQueryVer3.js"></SCRIPT>
  <SCRIPT src="../common/easyQueryVer3/EasyQueryCache.js"></SCRIPT>
  <LINK href="../common/css/Project.css" rel=stylesheet type=text/css>
  <LINK href="../common/css/mulLine.css" rel=stylesheet type=text/css>

  <SCRIPT src="menuFunGrp.js"></SCRIPT>
  <script src="treeMenu.js"></script>
  <%@include file="menuFunInit.jsp"%>

</head>
<body  onload="initForm();" >

<form action="./menuFunMan.jsp" method=post name=fm target="fraSubmit">
   <table  >
    	<tr>
		<TD  class= input style= "display: none">
         <Input class="code" name=Action>
         class= input style= "display: none">
         <Input class="code" name=isChild>
         class= input style= "display: none">
         <Input class="code" name=isChild2>//2005
       </TD>
	   </tr>
      <TR  class= common>
          <TD  class= title> 菜单节点名称</TD>
          <TD  class= input><Input class=common name=NodeName > </TD>
          <TD class= title> 映射文件</TD>
          <TD  class= input> <Input class=common name=RunScript  ></TD>
      </TR> 
    </Table>
  <input type="checkbox" name="checkbox1" value="1"><b>作为子菜单插入(不选则按照同级菜单插入)</b>
  &nbsp;&nbsp;
  <Div id= divcheckbox2 style="display: 'none'">
  <input type="checkbox" name="checkbox2" value="1"><b>作为页面权限菜单插入</b>
</div>
  <p>
  <Div id= divCmdButton style="display: ''">
     <INPUT VALUE="查询菜单" TYPE=button class="cssButton" onclick="queryClick()">
     <INPUT VALUE="增加菜单" TYPE=button class="cssButton" onclick="insertClick()">
     <INPUT VALUE="删除菜单" TYPE=button class="cssButton" onclick="deleteClick()">
  </Div>

  <Div  id= "divQueryGrp" style= "display: ''">
    <table>
      <td class= titleImg>
    	 菜单列表
      </td>

    </table>

     <table  class= common>
        <tr>
    	  	<td><span id="spanQueryGrpGrid"></span></td>
	    </tr>
     </table>

            <!-- xxx rewrote on 2007-04-23 -->
            <br>
            <!-- 查询结果翻页按钮 -->
            <div id="divTurnPageMenuGrid" align="center" style="display:''">
                <input type="button" class="cssButton" value="首  页" onclick="userFirstPage()">
                <input type="button" class="cssButton" value="上一页" onclick="userPageUp()">
                <input type="button" class="cssButton" value="下一页" onclick="userPageDown()">
                <input type="button" class="cssButton" value="尾  页" onclick="userLastPage()">
            </div>
</div>
</form>

 <span id="spanCode"  style="display: none; position:absolute; slategray"></span>
</body>
</html>
