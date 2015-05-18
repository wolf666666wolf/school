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

<title>商家管理</title>
</head>
<body onload="initForm();">
<form method=post name=fm target="fraSubmit">

<table class=common border=0>
	<TR class=common>
		<td class=title>用户名</td>
		<td class=input><Input class="common" name=SellerName></td>
		<td class=title>机构全称</td>
		<td class=input><Input class="common" name=OrgName></td>
		<td class=title>联系人</td>
		<td class=input><Input class="common" name=LinkPerson></td>
	</TR>
	<TR class=common>	
		<td class=title>联系电话</td>
		<td class=input><Input class="common" name=Phone></td>
		<td class=title></td>
		<td class=input></td>
		<td class=title></td>
		<td class=input></td>
	</TR>
	</table>
	  
    <br>&nbsp;&nbsp;&nbsp;&nbsp;
<INPUT class=cssButton TYPE=button VALUE=" 查 询 "
	onclick="queryp();">
<table>
	<tr>
		<td class=common></td>
		<td class=titleImg>商家列表</td>
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
        var uno = SelfGrid.getRowColData(tRow,7);
		OpenWindow("../admin/SellerAdd.jsp?uno="+uno,"查看商家信息");
		queryp();
	}
}
function queryp()
{
	var SQL = "select SellerName,OrgName,AuthFlag,(select a.channelname||'>'||b.channelname from sdchannel a,sdchannel b where a.serialno=b.parentchannel and b.serialno=ServiceType),LinkPerson,Phone,SellerID,R7,R9 from sdseller where 1=1";
	if(fm.SellerName.value!='')
	{
		SQL+=" and SellerName like '%%"+fm.SellerName.value+"%%'";
	}	
	if(fm.OrgName.value!='')
	{
		SQL+=" and OrgName like '%%"+fm.OrgName.value+"%%'";
	}	
	if(fm.LinkPerson.value!='')
	{
		SQL+=" and LinkPerson like '%%"+fm.LinkPerson.value+"%%'";
	}	
	if(fm.Phone.value!='')
	{
		SQL+=" and Phone like '%%"+fm.Phone.value+"%%'";
	}	
	
	SQL+="  order by R7 desc, regdate desc,regtime desc";
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
      iArray[i][0]="用户名";         		//列名
      iArray[i][1]="60px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="机构全称";         		//列名
      iArray[i][1]="60px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="是否认证";         		//列名
      iArray[i][1]="30px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="服务类别";         		//列名
      iArray[i][1]="70px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="联系人";         		//列名
      iArray[i][1]="40px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="电话";         		//列名
      iArray[i][1]="40px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
      
      iArray[i]=new Array();
      iArray[i][0]="商户号";         		//列名
      iArray[i][1]="40px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=3;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="申请认证";         		//列名
      iArray[i][1]="40px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="是否暂停";         		//列名
      iArray[i][1]="40px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
      
             
      
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