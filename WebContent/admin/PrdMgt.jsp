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

<title>产品管理</title>
</head>
<body onload="initForm();">
<form method=post name=fm target="fraSubmit">

<table class=common border=0>
	<TR class=common>
		<td class=title>产品名称</td>
		<td class=input><Input class="common" name=PrdName></td>
		<td class=title>商家</td>
		<td class=input><Input class="common" name=OrgName></td>
		<td class=title>分类</td>
		<td class=input><Input class=codeno name=ChannelCode ondblclick="showCodeList('chnl2code',[this,ChannelCodeName],[0,1],null,null,null,true);" onkeyup="return showCodeListKey('chnl2code',[this,ChannelCodeName],[0,1],null,null,null,true);"><input class=codename name=ChannelCodeName readonly=true></td>
	</TR>
	</table>
	  
    <br>&nbsp;&nbsp;&nbsp;&nbsp;
<INPUT class=cssButton TYPE=button VALUE=" 查 询 "
	onclick="queryp();">
<table>
	<tr>
		<td class=common></td>
		<td class=titleImg>产品列表</td>
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
        var uno = SelfGrid.getRowColData(tRow,8);
		OpenWindow("../admin/PrdAdd.jsp?uno="+uno,"查看产品信息");
		queryp();
	}
}
function queryp()
{
	var SQL = "select (select b.channelname||'>'||a.channelname from sdchannel a,sdchannel b where a.parentchannel=b.serialno and a.serialno=prdtype),(select orgname from sdseller where sdseller.sellerid=sdproduct.sellerid),prdname,prdprice,discount,effenddate,modifydate,prdid,r1, "
	+" (select count(*) from sdorder y where  y.prdid=sdproduct.prdid),"
	+" (select count(*) from sdproductassess x,sdorder y where x.orderid=y.orderid and y.prdid=sdproduct.prdid)"
	+" from sdproduct	 where 1=1 ";
	if(fm.PrdName.value!='')
	{
		SQL+=" and PrdName like '%%"+fm.PrdName.value+"%%'";		
	}
	if(fm.OrgName.value!='')
	{
		SQL+=" and exists (select 1 from sdseller x where x.sellerid=sdproduct.sellerid and (x.sellername like '%%"+fm.OrgName.value+"%%' or OrgName like '%%"+fm.OrgName.value+"%%'))";
	}
	if(fm.ChannelCode.value!='')
	{
		SQL+=" and prdtype='"+fm.ChannelCode.value+"'";		
	}
	SQL+=" order by R1 asc,modifydate asc,modifytime asc";
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
      iArray[i][0]="分类";         		//列名
      iArray[i][1]="60px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="商家";         		//列名
      iArray[i][1]="60px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="名称";         		//列名
      iArray[i][1]="60px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="价格";         		//列名
      iArray[i][1]="30px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="折扣";         		//列名
      iArray[i][1]="30px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="有效日期";         		//列名
      iArray[i][1]="40px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
      
      iArray[i]=new Array();
      iArray[i][0]="提交时间";         		//列名
      iArray[i][1]="40px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
      
      iArray[i]=new Array();
      iArray[i][0]="产品编码";         		//列名
      iArray[i][1]="40px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=3;              			//是否允许输入,1表示允许，0表示不允许
      
      iArray[i]=new Array();
      iArray[i][0]="审核";         		//列名
      iArray[i][1]="30px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
      
      iArray[i]=new Array();
      iArray[i][0]="订单数";         		//列名
      iArray[i][1]="30px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
      
      iArray[i]=new Array();
      iArray[i][0]="评价数";         		//列名
      iArray[i][1]="30px";            		//列宽
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