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

<title>选项管理</title>
</head>
<body onload="initForm();">
<form method=post name=fm target="fraSubmit">
<table>
	<tr>
		<td class=common></td>
		<td class=titleImg>题目信息</td>
	</tr>
</table>
<table class=common border=0>
	<TR class=common>
		<td class=title>题目</td>
		<td class=input><Input class="common" name=TopicTitle>
		<Input class="common" name=TopicNo value="<%=request.getParameter("TopicNo")%>" type=hidden>
		</td>
        <TD class= title></TD>
        <td class= input></td>
        <TD class= title></TD>
        <td class= input></td>
		<td class=title></td>
		<td class=input></td>
	</TR>
</table>
<table>
	<tr>
		<td class=common></td>
		<td class=titleImg>选项信息</td>
	</tr>
</table>

<div id="divSelfGrid" style="display: ''" align="center">
<table class="common">
	<tr class="common">
		<td><span id="spanSelfGrid"></span></td>
	</tr>
</table>

<table class=common border=0>
	<TR class=common>
		<td class=title>选项</td>
		<td class=input><Input class="common" name=ItemTitle></td>
		<td class=title>序号</td>
		<td class=input><Input class="common" name=VoteSeq></td>
		<td class=title></td>
		<td class=input></td>
	</TR>
</table>
</div>
<INPUT class=cssButton TYPE=button VALUE=" 增 加 "
	onclick="additem();"> 
<INPUT class=cssButton TYPE=button VALUE=" 修 改 "
	onclick="moditem();"> 
<INPUT class=cssButton TYPE=button VALUE=" 删 除"
	onclick="delitem();"> 
	<INPUT class=cssButton
	TYPE=button VALUE=" 返 回 " onclick="top.close();">

	<span id="spanCode" style="display: none; position: absolute;"></span>
</form>

</body>
</html>
<script>
var turnPage = new turnPageClass();  
function initForm()
{
	initSelfGrid();
	queryData();
	queryi();
}
function queryData()
{
	var vno = fm.TopicNo.value;
	if(vno!=''&&vno!=null&&vno!='null')
	{
		var SQL = "select topictitle from sdvotetopic where topicno='"+vno+"'";
		var arr = easyExecSql(SQL, 1, 0);
		if(arr)
		{
			fm.TopicTitle.value=arr[0][0];
		}
	}
}
function additem()
{
	if(fm.ItemTitle.value=='')
	{
		alert('请填写选项');
		return;
	}
	if(fm.VoteSeq.value=='')
	{
		alert('请填写顺序');
		return;
	}
	fm.action="./pa.jsp?a=votemgt&o=additem";
	opt="additem";
	fm.submit();
}
var opt="";
function afterSubmit(a,b,c)
{
	alert(b);
	if(a=="Succ")
	{
		if(opt=='additem'||opt=='moditem'||opt=='delitem')
		{
			queryi();

		    fm.ItemTitle.value='';
		    fm.VoteSeq.value='';
		}
	}
}
function queryi()
{
	var SQL = "select voteseq,voteitem,itemno from sdvoteitem where topicno='"+fm.TopicNo.value+"' order by voteseq desc";
	turnPage.pageLineNum=20;
	turnPage.queryModal(SQL, SelfGrid);
}
function showi()
{
	var tSel = SelfGrid.getSelNo();	 
	if( tSel == 0 || tSel == null )
	{
	    alert( "请先选择一个题目" );
	    return false;
	}
	var tRow = SelfGrid.getSelNo() - 1;	     
    var vno = SelfGrid.getRowColData(tRow,3);
    fm.ItemTitle.value=SelfGrid.getRowColData(tRow,2);
    fm.VoteSeq.value=SelfGrid.getRowColData(tRow,1);
}
function moditem()
{
	var tSel = SelfGrid.getSelNo();	 
	if( tSel == 0 || tSel == null )
	{
	    alert( "请先选择一个选项" );
	    return false;
	}
	var tRow = SelfGrid.getSelNo() - 1;
	if(fm.ItemTitle.value=='')
	{
		alert('请填写选项');
		return;
	}
	if(fm.VoteSeq.value=='')
	{
		alert('请填写顺序');
		return;
	}
	fm.action="./pa.jsp?a=votemgt&o=moditem&itemno="+SelfGrid.getRowColData(tRow,3);
	opt="moditem";
	fm.submit();
}
function delitem()
{
	var tSel = SelfGrid.getSelNo();	 
	if( tSel == 0 || tSel == null )
	{
	    alert( "请先选择一个选项" );
	    return false;
	}	
	var tRow = SelfGrid.getSelNo() - 1;

	fm.action="./pa.jsp?a=votemgt&o=delitem&itemno="+SelfGrid.getRowColData(tRow,3);
	opt="delitem";
	fm.submit();
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
      iArray[i][0]="顺序";         		//列名
      iArray[i][1]="50px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="选项";         		//列名
      iArray[i][1]="180px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许             
      
      iArray[i]=new Array();
      iArray[i][0]="选项号";         		//列名
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
      SelfGrid.selBoxEventFuncName = "showi";         
      SelfGrid.loadMulLine(iArray);  
      //这些操作必须在loadMulLine后面

	}
	catch(ex)
	{
		alert(ex);
	}
}
</script>