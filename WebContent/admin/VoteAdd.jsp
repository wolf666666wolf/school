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

<title>投票信息</title>
</head>
<body onload="initForm();">
<form method=post name=fm target="fraSubmit">
<table>
	<tr>
		<td class=common></td>
		<td class=titleImg>投票信息</td>
	</tr>
</table>
<table class=common border=0>
	<TR class=common>
		<td class=title>投票标题</td>
		<td class=input><Input class="common" name=VoteTitle>
		<Input class="common" name=VoteNo value="<%=request.getParameter("vno")%>" type=hidden>
		</td>
        <TD class= title>是否显示</TD>
        <td class= input><Input class=codeno name=DisplayFlag ondblclick="showCodeList('yesorno',[this,DisplayFlagName],[0,1],null,null,null,true);" onkeyup="return showCodeListKey('yesorno',[this,DisplayFlagName],[0,1],null,null,null,true);"><input class=codename name=DisplayFlagName readonly=true></td>
        <TD class= title>是否匿名</TD>
        <td class= input><Input class=codeno name=AnonyFlag ondblclick="showCodeList('yesorno',[this,AnonyFlagName],[0,1],null,null,null,true);" onkeyup="return showCodeListKey('yesorno',[this,AnonyFlagName],[0,1],null,null,null,true);"><input class=codename name=AnonyFlagName readonly=true></td>
		<td class=title></td>
		<td class=input></td>
	</TR>
	<TR class=common>
		<TD class= title>开始时间</TD>
        <td class= input><Input class="coolDatePicker" dateFormat="short" name=StartDate></td>
        <TD class= title>截止时间</TD>
        <td class= input><Input class="coolDatePicker" dateFormat="short" name=EndDate></td>
		<td class=title></td>
		<td class=input></td>
	</TR>
</table>
<br>
<INPUT class=cssButton TYPE=button VALUE=" 保 存 "
	onclick="save();"> 
	<INPUT class=cssButton
	TYPE=button VALUE=" 返 回 " onclick="top.close();">
	<br><br>
<table>
	<tr>
		<td class=common></td>
		<td class=titleImg>题目信息</td>
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
		<td class=title>题目</td>
		<td class=input><Input class="common" name=TopicTitle></td>
		<td class=title>类型</td>
		<td class=input><Input class=codeno name=VoteType ondblclick="showCodeList('votetype',[this,VoteTypeName],[0,1],null,null,null,true);" onkeyup="return showCodeListKey('votetype',[this,VoteTypeName],[0,1],null,null,null,true);"><input class=codename name=VoteTypeName readonly=true></td>
		<td class=title>编号</td>
		<td class=input><Input class="common" name=R1></td>
	</TR>
	<TR class=common>
		<td class=title>是否显示</td>
		<td class=input><Input class=codeno name=DisplayFlagT ondblclick="showCodeList('yesorno',[this,DisplayFlagTName],[0,1],null,null,null,true);" onkeyup="return showCodeListKey('yesorno',[this,DisplayFlagTName],[0,1],null,null,null,true);"><input class=codename name=DisplayFlagTName readonly=true></td>
		<td class=title></td>
		<td class=input></td>
		<td class=title></td>
		<td class=input></td>
	</TR>
</table>
</div>
<INPUT class=cssButton TYPE=button VALUE=" 增 加 "
	onclick="addtopic();"> 
<INPUT class=cssButton TYPE=button VALUE=" 修 改 "
	onclick="modtopic();"> 
<INPUT class=cssButton TYPE=button VALUE=" 删 除"
	onclick="deltopic();"> 
<INPUT class=cssButton TYPE=button VALUE=" 选 项"
	onclick="configtopic();"> 
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
	var vno = fm.VoteNo.value;
	if(vno!=''&&vno!=null&&vno!='null')
	{
		var SQL = "select * from sdvotemain where voteno='"+vno+"'";
		var arr = easyExecSql(SQL, 1, 0);
		if(arr)
		{
			fm.VoteNo.value=arr[0][0];
			fm.VoteTitle.value=arr[0][1];
			fm.AnonyFlag.value=arr[0][3];
			fm.StartDate.value=arr[0][4];
			fm.EndDate.value=arr[0][5];
			fm.DisplayFlag.value=arr[0][6];
		}
	}
}
function addtopic()
{
	if(fm.TopicTitle.value=='')
	{
		alert('请填写题目');
		return;
	}
	if(fm.VoteType.value=='')
	{
		alert('请选择类型');
		return;
	}
	if(fm.R1.value=='')
	{
		alert('请填写编号');
		return;
	}
	if(fm.DisplayFlag.value=='')
	{
		alert('请选择是否显示');
		return;
	}
	fm.action="./pa.jsp?a=votemgt&o=addtopic";
	opt="addtopic";
	fm.submit();
}
function save()
{
	if(fm.VoteTitle.value=='')
	{
		alert('请录入投票标题');	
		return;
	}
	if(fm.AnonyFlag.value=='')
	{
		alert('请选择是否匿名');	
		return;
	}
	if(fm.DisplayFlag.value=='')
	{
		alert('请选择是否显示');	
		return;
	}
	if(fm.StartDate.value=='')
	{
		alert('请选择开始时间');
		return;	
	}
	if(fm.EndDate.value=='')
	{
		alert('请选择截止时间');	
		return;
	}
	fm.action="./pa.jsp?a=votemgt&o=save";
	opt="save";
	fm.submit();
}
var opt="";
function afterSubmit(a,b,c)
{
	alert(b);
	if(a=="Succ")
	{
		if(opt=='save')
		{
			if(c!=''&&c!='null'&&c!=null)
			{
				fm.VoteNo.value=c;
			}
		}
		if(opt=='addtopic'||opt=='modtopic'||opt=='deltopic')
		{
			queryi();

		    fm.TopicTitle.value='';
		    fm.VoteType.value='';
		    fm.R1.value='';
		    fm.DisplayFlagT.value='';
		}
	}
}
function queryi()
{
	var SQL = "select R1,TopicTitle,votetype,displayflag,TopicNo from sdvotetopic where voteno='"+fm.VoteNo.value+"' order by cast (R1 as number) desc";
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
    var vno = SelfGrid.getRowColData(tRow,5);
    fm.TopicTitle.value=SelfGrid.getRowColData(tRow,2);
    fm.VoteType.value=SelfGrid.getRowColData(tRow,3);
    fm.R1.value=SelfGrid.getRowColData(tRow,1);
    fm.DisplayFlagT.value=SelfGrid.getRowColData(tRow,4);
}
function modtopic()
{
	var tSel = SelfGrid.getSelNo();	 
	if( tSel == 0 || tSel == null )
	{
	    alert( "请先选择一个题目" );
	    return false;
	}
	var tRow = SelfGrid.getSelNo() - 1;
	if(fm.TopicTitle.value=='')
	{
		alert('请填写题目');
		return;
	}
	if(fm.VoteType.value=='')
	{
		alert('请选择类型');
		return;
	}
	if(fm.R1.value=='')
	{
		alert('请填写编号');
		return;
	}
	if(fm.DisplayFlagT.value=='')
	{
		alert('请选择是否显示');
		return;
	}
	fm.action="./pa.jsp?a=votemgt&o=modtopic&topicno="+SelfGrid.getRowColData(tRow,5);
	opt="modtopic";
	fm.submit();
}
function deltopic()
{
	var tSel = SelfGrid.getSelNo();	 
	if( tSel == 0 || tSel == null )
	{
	    alert( "请先选择一个题目" );
	    return false;
	}	
	var tRow = SelfGrid.getSelNo() - 1;

	fm.action="./pa.jsp?a=votemgt&o=deltopic&topicno="+SelfGrid.getRowColData(tRow,5);
	opt="deltopic";
	fm.submit();
}
function configtopic()
{
	var tSel = SelfGrid.getSelNo();	 
	if( tSel == 0 || tSel == null )
	{
	    alert( "请先选择一个题目" );
	    return false;
	}
	var tRow = SelfGrid.getSelNo() - 1;
	OpenWindow("../admin/ItemAdd.jsp?TopicNo="+SelfGrid.getRowColData(tRow,5),"选项管理");

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
      iArray[i][0]="编号";         		//列名
      iArray[i][1]="50px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="题目";         		//列名
      iArray[i][1]="180px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许             
             
      iArray[i]=new Array();
      iArray[i][0]="类型";         		//列名
      iArray[i][1]="80px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许             
             
      iArray[i]=new Array();
      iArray[i][0]="是否显示";         		//列名
      iArray[i][1]="80px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许             
      
      iArray[i]=new Array();
      iArray[i][0]="题目号";         		//列名
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