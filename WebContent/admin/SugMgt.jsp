<html>
<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="../common/jsp/UsrCheck.jsp"%>
<script>
    var operator = "<%=tGI.Operator%>";   //记录操作员
    var manageCom = "<%=tGI.ManageCom%>"; //记录登陆机构
    var comcode = "<%=tGI.ComCode%>";//记录登陆机构
</script>
<head>
<SCRIPT src="../common/javascript/Common.js"></SCRIPT>
<SCRIPT src="../common/cvar/CCodeOperate.js"></SCRIPT>
<SCRIPT src="../common/javascript/MulLine.js"></SCRIPT>
<SCRIPT src="../common/easyQueryVer3/EasyQueryVer3.js"></SCRIPT>
<SCRIPT src="../common/easyQueryVer3/EasyQueryCache.js"></SCRIPT>
<SCRIPT src="../common/Calendar/Calendar.js"></SCRIPT>
<LINK href="../common/css/Project.css" rel=stylesheet type=text/css>
<LINK href="../common/css/mulLine.css" rel=stylesheet type=text/css>

<title>建议管理</title>
</head>
<body onload="initForm();">
<form method=post name=fm target="fraSubmit">

<table class=common border=0>
	<TR class=common>
		<td class=title>昵称</td>
		<td class=input><Input class="common" name=NickName></td>
		<td class=title>手机</td>
		<td class=input><Input class="common" name=Phone></td>
		<td class=title>标题</td>
		<td class=input><Input class="common" name=Content></td>
	</TR>
	<TR class=common>	
		<td class=title>是否处理</td>
		<td class=input><Input class=codeno name=R1 ondblclick="showCodeList('yesorno',[this,R1Name],[0,1],null,null,null,true);" onkeyup="return showCodeListKey('yesorno',[this,R1Name],[0,1],null,null,null,true);"><input class=codename name=R1Name readonly=true></td>
		<td class=title>建议时间(起)</td>
		<td class=input><Input class="coolDatePicker" dateFormat="short" name=SuggestDateStart></td>
		<td class=title>建议时间(止)</td>
		<td class=input><Input class="coolDatePicker" dateFormat="short" name=SuggestDateEnd></td>
	</TR>
	</table>
	  
    <br>&nbsp;&nbsp;&nbsp;&nbsp;
<INPUT class=cssButton TYPE=button VALUE=" 查 询 "
	onclick="queryp();">
<table>
	<tr>
		<td class=common></td>
		<td class=titleImg>建议列表</td>
	</tr>
</table>
<div id="divSelfGrid" style="display: ''" align="center">
<table class="common">
	<tr class="common">
		<td><span id="spanSelfGrid"></span></td>
	</tr>
</table>

</div>
<br>
<INPUT class=cssButton TYPE=button VALUE=" 查 看 " onclick="modp();">
<br>
<br>

	<span id="spanCode" style="display: none; position: absolute;"></span>
</form>

</body>
</html>
<script>
var turnPage = new turnPageClass();  
function initForm()
{
	initSelfGrid();
	queryp();
}
function modp()
{
	var tSel = SelfGrid.getSelNo();	 
	if( tSel == 0 || tSel == null )
	{
	    alert( "请先选择一条记录" );
	    return false;
	}	
	else
	{
	   	var tRow = SelfGrid.getSelNo() - 1;	     
        var sno = SelfGrid.getRowColData(tRow,8);
		OpenWindow("../admin/SugAdd.jsp?sno="+sno,"建议信息");
		queryp();
	}
}
function queryp()
{
	var SQL = "select UserType,nickname,phone,title,suggestdate,suggesttime,dealflag,serialno from sdsuggest	 where 1=1 ";
	if(fm.NickName.value!='')
	{
		SQL+=" and NickName like '%%"+fm.NickName.value+"%%'";
	}
	if(fm.Phone.value!='')
	{
		SQL+=" and Phone like '%%"+fm.Phone.value+"%%'";
	}
	if(fm.Content.value!='')
	{
		SQL+=" and Content like '%%"+fm.Content.value+"%%'";
	}
	if(fm.R1.value!='')
	{
		SQL+=" and DealFlag like '%%"+fm.R1.value+"%%'";
	}
	if(fm.SuggestDateStart.value!='')
	{
		SQL+=" and SuggestDate >= '"+fm.SuggestDateStart.value+"'";
	}
	if(fm.SuggestDateEnd.value!='')
	{
		SQL+=" and SuggestDate <= '"+fm.SuggestDateEnd.value+"'";
	}
	SQL+=" order by suggestdate desc,suggesttime desc";
	turnPage.pageLineNum=20;
	turnPage.queryModal(SQL, SelfGrid);
}
function initSelfGrid()
{                                  
	var iArray = new Array();      
	try
	{
	var i=0;
      iArray[i]=new Array();
      iArray[i][0]="序号";         			//列名（此列为顺序号，列名无意义，而且不显示）
      iArray[i][1]="30px";            		//列宽
      iArray[i][2]=10;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="用户类型";         		//列名
      iArray[i][1]="40px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="昵称";         		//列名
      iArray[i][1]="50px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="手机";         		//列名
      iArray[i][1]="50px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="标题";         		//列名
      iArray[i][1]="80px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="建议日期";         		//列名
      iArray[i][1]="40px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="建议时间";         		//列名
      iArray[i][1]="40px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="是否处理";         		//列名
      iArray[i][1]="40px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="流水号";         		//列名
      iArray[i][1]="40px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=3;              			//是否允许输入,1表示允许，0表示不允许
      
             
      
      SelfGrid = new MulLineEnter( "fm" , "SelfGrid" ); 
      //这些属性必须在loadMulLine前
      SelfGrid.mulLineCount = 0;   
      SelfGrid.displayTitle = 1;
      SelfGrid.locked = 1;
      SelfGrid.canSel = 1;
      SelfGrid.canChk = 0;
      SelfGrid.hiddenPlus = 1;
      SelfGrid.hiddenSubtraction = 1;  
      SelfGrid.selBoxEventFuncName = "querys";         
      SelfGrid.loadMulLine(iArray);  
      //这些操作必须在loadMulLine后面

	}
	catch(ex)
	{
		alert(ex);
	}
}

</script>