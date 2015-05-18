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

<title>投票管理</title>
</head>
<body onload="initForm();">
<form method=post name=fm target="fraSubmit">
<table>
	<tr>
		<td class=common></td>
		<td class=titleImg>投票列表</td>
	</tr>
</table>

<table class=common border=0>
	<TR class=common>
		<td class=title>标题</td>
		<td class=input><Input class="common" name=VoteTitle></td>
		<td class=title>开始时间(起)</td>
		<td class=input><Input class="coolDatePicker" dateFormat="short" name=StartDateStart></td>
		<td class=title>开始时间(止)</td>
		<td class=input><Input class="coolDatePicker" dateFormat="short" name=StartDateEnd></td>
	</TR>
	<TR class=common>	
		<td class=title>是否显示</td>
		<td class=input><Input class=codeno name=R1 ondblclick="showCodeList('yesorno',[this,R1Name],[0,1],null,null,null,true);" onkeyup="return showCodeListKey('yesorno',[this,R1Name],[0,1],null,null,null,true);"><input class=codename name=R1Name readonly=true></td>
		<td class=title>截止时间(起)</td>
		<td class=input><Input class="coolDatePicker" dateFormat="short" name=EndDateStart></td>
		<td class=title>截止时间(止)</td>
		<td class=input><Input class="coolDatePicker" dateFormat="short" name=EndDateEnd></td>
	</TR>
	</table>
	  
    <br>&nbsp;&nbsp;&nbsp;&nbsp;
<INPUT class=cssButton TYPE=button VALUE=" 查 询 "
	onclick="queryp();">
<div id="divSelfGrid" style="display: ''" align="center">
<table class="common">
	<tr class="common">
		<td><span id="spanSelfGrid"></span></td>
	</tr>
</table>

</div>
<br>
<INPUT class=cssButton TYPE=button VALUE=" 新 增 " onclick="addp()">
<INPUT class=cssButton TYPE=button VALUE=" 修 改 " onclick="modp();">
<INPUT class=cssButton TYPE=button VALUE=" 删 除 " onclick="delp();">
<INPUT class=cssButton TYPE=button VALUE="查看结果" onclick="watchp();">
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
function addp()
{
	OpenWindow("../admin/VoteAdd.jsp","新增问卷");
	queryp();	
}
function watchp()
{
	var tSel = SelfGrid.getSelNo();	 
	if( tSel == 0 || tSel == null )
	{
	    alert( "请先选择一个问卷" );
	    return false;
	}
	var tRow = SelfGrid.getSelNo() - 1;	     
    var vno = SelfGrid.getRowColData(tRow,7);
	OpenWindow("../admin/VoteDetail.jsp?id="+vno,"问卷结果");
}
function delp()
{
	var tSel = SelfGrid.getSelNo();	 
	if( tSel == 0 || tSel == null )
	{
	    alert( "请先选择一个投票" );
	    return false;
	}
	if(!confirm("删除投票的同时，投票题目，选项和投票记录也将被删除，是否继续？"))
	{
		return;	
	}
	var tRow = SelfGrid.getSelNo() - 1;	     
    var vno = SelfGrid.getRowColData(tRow,7);

	fm.action="./pa.jsp?a=votemgt&o=del&VoteNo="+vno;
	opt="delitem";
	fm.submit();
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
        var vno = SelfGrid.getRowColData(tRow,7);
		OpenWindow("../admin/VoteAdd.jsp?vno="+vno,"修改投票");
		queryp();
	}
}
function afterSubmit(a,b)
{
	alert(b);
	if(a=="Succ")
	{
		queryp();
	}
}
function queryp()
{
	var SQL = "select votetitle,(select codename from ldcode where codetype = 'votetype' and code=votetype),AnonyFlag,displayflag,startdate,enddate,voteno from sdvotemain where 1=1 ";
	if(fm.VoteTitle.value!='')
	{
		SQL+=" and VoteTitle like '%%"+fm.VoteTitle.value+"%%'";		
	}
	if(fm.R1.value!='')
	{
		SQL+=" and DisplayFlag = '"+fm.R1.value+"'";		
	}
	if(fm.StartDateStart.value!='')
	{
		SQL+=" and startdate>='"+fm.StartDateStart.value+"'";		
	}
	if(fm.StartDateEnd.value!='')
	{
		SQL+=" and startdate<='"+fm.StartDateEnd.value+"'";		
	}
	if(fm.EndDateStart.value!='')
	{
		SQL+=" and enddate>='"+fm.EndDateStart.value+"'";		
	}
	if(fm.EndDateEnd.value!='')
	{
		SQL+=" and enddate<='"+fm.EndDateEnd.value+"'";		
	}
	SQL+=" order by voteno desc ";
	turnPage.pageLineNum=20;
	turnPage.queryModal(SQL, SelfGrid);
}
function cache()
{
	if(confirm("确定生成缓存？"))
	{
		fm.action="./pa.jsp?a=chnlmgt&o=cache";
		fm.submit();
	}
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
      iArray[i][0]="投票标题";         		//列名
      iArray[i][1]="280px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="类型";         		//列名
      iArray[i][1]="40px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=3;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="是否匿名";         		//列名
      iArray[i][1]="40px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="是否显示";         		//列名
      iArray[i][1]="40px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="开始时间";         		//列名
      iArray[i][1]="50px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="截止时间";         		//列名
      iArray[i][1]="50px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
      
      iArray[i]=new Array();
      iArray[i][0]="投票号";         		//列名
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