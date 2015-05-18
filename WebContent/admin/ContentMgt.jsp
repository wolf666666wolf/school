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

<title>内容管理</title>
</head>
<body onload="initForm();">
<form method=post name=fm target="fraSubmit">
<table>
	<tr>
		<td class=common></td>
		<td class=titleImg>内容列表</td>
	</tr>
</table>
<table class=common border=0>
	<TR class=common>
		<td class=title>标题</td>
		<td class=input><Input class="common" name=ContentTitle></td>
		<td class=title>栏目</td>
		<td class=input><Input class=codeno name=ChannelCode ondblclick="showCodeList('chnlcode',[this,ChannelCodeName],[0,1],null,null,null,true);" onkeyup="return showCodeListKey('chnlcode',[this,ChannelCodeName],[0,1],null,null,null,true);"><input class=codename name=ChannelCodeName readonly=true></td>
		<td class=title>来源</td>
		<td class=input><Input class="common" name=ContentFrom></td>
	</TR>
	<TR class=common>
		<td class=title>发布日期(起)</td>
		<td class=input><Input class="coolDatePicker" dateFormat="short" name=IssueDateStart></td>
		<td class=title>发布日期(止)</td>
		<td class=input><Input class="coolDatePicker" dateFormat="short" name=IssueDateEnd></td>
		<td class=title>是否显示</td>
		<td class=input><Input class=codeno name=R1 ondblclick="showCodeList('yesorno',[this,R1Name],[0,1],null,null,null,true);" onkeyup="return showCodeListKey('yesorno',[this,R1Name],[0,1],null,null,null,true);"><input class=codename name=R1Name readonly=true></td>
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
	OpenWindow("../admin/ContentAdd.jsp","新增内容");
	queryp();	
}
function delp()
{
	var tSel = SelfGrid.getSelNo();	 
	if( tSel == 0 || tSel == null )
	{
	    alert( "请先选择一个文章" );
	    return false;
	}
	if(!confirm("确定删除？"))
	{
		return;	
	}
	var tRow = SelfGrid.getSelNo() - 1;	     
    var vno = SelfGrid.getRowColData(tRow,7);

	fm.action="./pa.jsp?a=contentmgt&o=del&SerialNo="+vno;
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
		OpenWindow("../admin/ContentAdd.jsp?cno="+vno,"修改内容");
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
	var SQL = "select ContentTitle,case channelcode when 'C0000' then '静态页面' else "
	+"(select b.channelname||'>'||a.channelname from sdchannel a,sdchannel b where a.parentchannel=b.serialno and a.R3='3' "
			+" and a.serialno=channelcode) end,issuedate,issuetime,contentfrom,r1,serialno from sdcontent where 1=1 ";
	if(fm.ContentTitle.value!='')
	{
		SQL+=" and ContentTitle like '%%"+fm.ContentTitle.value+"%%'";		
	}
	if(fm.ContentFrom.value!='')
	{
		SQL+=" and ContentFrom like '%%"+fm.ContentFrom.value+"%%'";		
	}
	if(fm.ChannelCode.value!='')
	{
		SQL+=" and ChannelCode='"+fm.ChannelCode.value+"'";		
	}
	if(fm.R1.value!='')
	{
		SQL+=" and R1='"+fm.R1.value+"'";		
	}
	if(fm.IssueDateStart.value!='')
	{
		SQL+=" and IssueDate >='"+fm.IssueDateStart.value+"'";		
	}
	if(fm.IssueDateEnd.value!='')
	{
		SQL+=" and IssueDate <='"+fm.IssueDateEnd.value+"'";		
	}
	SQL+=" order by R1 asc, serialno desc";
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
      iArray[i][0]="标题";         		//列名
      iArray[i][1]="180px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="所属栏目";         		//列名
      iArray[i][1]="80px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="发布日期";         		//列名
      iArray[i][1]="40px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="发布时间";         		//列名
      iArray[i][1]="40px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="来源";         		//列名
      iArray[i][1]="50px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="是否显示";         		//列名
      iArray[i][1]="50px";            		//列宽
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