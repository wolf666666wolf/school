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

<title>商户分类管理</title>
</head>
<body onload="initForm();">
<form method=post name=fm target="fraSubmit">
<table>
	<tr>
		<td class=common></td>
		<td class=titleImg>一级分类</td>
	</tr>
</table>
<div id="divSelfGrid" style="display: ''" align="center">
<table class="common">
	<tr class="common">
		<td><span id="spanSelfGrid"></span></td>
	</tr>
</table>
</div>
<table>
	<tr>
		<td class=common></td>
		<td class=titleImg>二级分类</td>
	</tr>
</table>
<div id="divSelfSubGrid" style="display: ''" align="center">
<table class="common">
	<tr class="common">
		<td><span id="spanSelfSubGrid"></span></td>
	</tr>
</table>
</div>
<table class=common border=0>
	<TR class=common>
		<td class=title>二级分类名</td>
		<td class=input><Input class="common" name=ClassName></td>
		<td class=title></td>
		<td class=input></td>
		<td class=title></td>
		<td class=input></td>
	</TR>
</table>
<br>
<INPUT class=cssButton TYPE=button VALUE=" 新 增 " onclick="addp()">
<INPUT class=cssButton TYPE=button VALUE=" 修 改 " onclick="modp();">
<INPUT class=cssButton TYPE=button VALUE=" 删 除 " onclick="delp();">
<br>
<br>

	<span id="spanCode" style="display: none; position: absolute;"></span>
</form>

</body>
</html>
<script>
var turnPage = new turnPageClass();  
var turnPage_sub = new turnPageClass();  
function initForm()
{
	initSelfGrid();
	initSelfSubGrid();
	queryp();
}
function addp()
{
	var tSel = SelfGrid.getSelNo();	 
	if( tSel == 0 || tSel == null )
	{
	    alert( "请先选择一个一级分类" );
	    return false;
	}	
	var tRow = SelfGrid.getSelNo() - 1;	     
    var vno = SelfGrid.getRowColData(tRow,2);
    if(fm.ClassName.value=='')
    {
    	aalert("请填写分类名称");
    	return;
    }
	fm.action="./pa.jsp?a=sellerclassmgt&o=add&SerialNo="+vno;
	fm.submit();
}
function queryss()
{
	var tRow = SelfSubGrid.getSelNo() - 1;	     
    var vno = SelfSubGrid.getRowColData(tRow,1);
    fm.ClassName.value=vno;
}
function delp()
{
	var tSel = SelfSubGrid.getSelNo();	 
	if( tSel == 0 || tSel == null )
	{
	    alert( "请先选择二级分类" );
	    return false;
	}
	if(!confirm("确定删除？"))
	{
		return;	
	}
	var tRow = SelfSubGrid.getSelNo() - 1;	     
    var vno = SelfSubGrid.getRowColData(tRow,2);

	fm.action="./pa.jsp?a=sellerclassmgt&o=del&SerialNo="+vno;
	fm.submit();
}
function modp()
{
	var tSel = SelfSubGrid.getSelNo();	 
	if( tSel == 0 || tSel == null )
	{
	    alert( "请先选择二级分类" );
	    return false;
	}	
	else
	{
		var tRow = SelfSubGrid.getSelNo() - 1;	     
	    var vno = SelfSubGrid.getRowColData(tRow,2);
	    if(fm.ClassName.value=='')
	    {
	    	aalert("请填写分类名称");
	    	return;
	    }

		fm.action="./pa.jsp?a=sellerclassmgt&o=mod&SerialNo="+vno;
		fm.submit();
	}
}
function afterSubmit(a,b)
{
	alert(b);
	querys();
}
function queryp()
{
	var SQL ="";
	SQL = "select a.channelname||'>'||b.channelname,b.serialno from sdchannel a,sdchannel b where a.serialno=b.parentchannel and b.r3='2'";
	turnPage.pageLineNum=20;
	turnPage.queryModal(SQL, SelfGrid);
}
function querys()
{
	var tRow = SelfGrid.getSelNo() - 1;	     
    var vno = SelfGrid.getRowColData(tRow,2);
    var SQL = "select classname,serialno from sdsellerclass where ChannelCode='"+vno+"'";
	turnPage.pageLineNum=20;
	turnPage_sub.queryModal(SQL, SelfSubGrid);
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
      iArray[i][0]="分类名";         		//列名
      iArray[i][1]="180px";            		//列宽
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


function initSelfSubGrid()
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
      iArray[i][0]="分类名";         		//列名
      iArray[i][1]="180px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
             
      
      iArray[i]=new Array();
      iArray[i][0]="流水号";         		//列名
      iArray[i][1]="40px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=3;              			//是否允许输入,1表示允许，0表示不允许
      
             
      
      SelfSubGrid = new MulLineEnter( "fm" , "SelfSubGrid" ); 
      //这些属性必须在loadMulLine前
      SelfSubGrid.mulLineCount = 0;   
      SelfSubGrid.displayTitle = 1;
      SelfSubGrid.locked = 1;
      SelfSubGrid.canSel = 1;
      SelfSubGrid.canChk = 0;
      SelfSubGrid.hiddenPlus = 1;
      SelfSubGrid.hiddenSubtraction = 1;  
      SelfSubGrid.selBoxEventFuncName = "queryss";         
      SelfSubGrid.loadMulLine(iArray);  
      //这些操作必须在loadMulLine后面

	}
	catch(ex)
	{
		alert(ex);
	}
}

</script>