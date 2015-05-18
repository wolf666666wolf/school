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

<title>产品信息</title>
</head>
<body onload="initForm();">
<form method=post name=fm target="fraSubmit">
<table class=common border=0>
	<TR class=common>
		<td class=title>商户编码</td>
		<td class=input><Input class="common" name=SellerID readonly></td>
		<td class=title>商户户名</td>
		<td class=input><Input class="common" name=SellerName readonly></td>
		<td class=title>机构全称</td>
		<td class=input><Input class="common" name=OrgName readonly></td>
	</TR>
	<TR class=common>
		<td class=title>产品编码</td>
		<td class=input><Input class="common" name=PrdID value="<%=request.getParameter("uno")%>" readonly></td>
		<td class=title>分类</td>
		<td class=input><Input class="common" name=PrdType readonly></td>
		<td class=title>名称</td>
		<td class=input><Input class="common" name=PrdName readonly></td>
	</TR>
	<TR class=common>
		<td class=title>价格</td>
		<td class=input><Input class="common" name=PrdPrice readonly></td>
		<td class=title>折扣</td>
		<td class=input><Input class="common" name=Discount readonly></td>
		<td class=title>有效日期</td>
		<td class=input><Input class="common" name=EffEndDate readonly></td>
	</TR>
	<TR class=common>
		<td class=title>备注</td>
		<td class=input colspan="5">
        				<textarea name=Remark  cols="100%" rows="5" witdh=100% class="common" readonly>
        				</textarea>
		</td>
	</TR>
	<TR class=common>
		<td class=title>审核状态</td>
		<td class=input><Input class=codeno name=R1 ondblclick="showCodeList('yesorno',[this,R1Name],[0,1],null,null,null,true);" onkeyup="return showCodeListKey('yesorno',[this,R1Name],[0,1],null,null,null,true);"><input class=codename name=R1Name readonly=true></td>
		<td class=title></td>
		<td class=input></td>
		<td class=title></td>
		<td class=input></td>
	</TR>
</table>
<INPUT class=cssButton TYPE=button VALUE=" 保 存 "
	onclick="save();"> <INPUT class=cssButton
	TYPE=button VALUE=" 返 回 " onclick="top.close();">

	<span id="spanCode" style="display: none; position: absolute;"></span>
	
<table>
	<tr>
		<td class=common></td>
		<td class=titleImg>订单列表</td>
	</tr>
</table>
<div id="divSelfGrid" style="display: ''" align="center">
<table class="common">
	<tr class="common">
		<td><span id="spanSelfGrid"></span></td>
	</tr>
</table>

</div>
</form>

</body>
</html>
<script>
var turnPage = new turnPageClass();  
function initForm()
{
	queryData();
	initSelfGrid();
	queryp();
}
function queryData()
{
	var vno = fm.PrdID.value;
	if(vno!=''&&vno!=null&&vno!='null')
	{
		var SQL = "select a.prdid,a.prdtype,a.sellerid,a.prdname,a.prdprice,a.effenddate,a.discount,a.remark,b.sellername,b.orgname,a.r1 from sdproduct a,sdseller b where a.sellerid=b.sellerid and a.prdid='"+vno+"'";
		var arr = easyExecSql(SQL, 1, 0);
		if(arr)
		{
			fm.PrdID.value=arr[0][0];
			fm.PrdType.value=arr[0][1];
			fm.SellerID.value=arr[0][2];
			fm.PrdName.value=arr[0][3];
			fm.PrdPrice.value=arr[0][4];
			fm.EffEndDate.value=arr[0][5];
			fm.Discount.value=arr[0][6];
			fm.Remark.value=arr[0][7];
			fm.SellerName.value=arr[0][8];
			fm.OrgName.value=arr[0][9];
			fm.R1.value=arr[0][10];
		}
	}
}
function save()
{
	if(fm.R1.value=='')
	{
		alert('请选择审核状态');	
		return;
	}
	fm.action="./pa.jsp?a=prdmgt&o=save";
	fm.submit();
}
function afterSubmit(a,b)
{
	alert(b);
	if(a=="Succ")
	{
		top.close();
	}
}
function queryp()
{
	var SQL="select a.orderid,a.userid,a.Owner,a.phone,b.AssEssDate,b.Level,b.Content from sdorder a left join sdproductassess b on a.orderid=b.orderid where a.prdid='"+fm.PrdID.value+"'";
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
      iArray[i][0]="订单编码";         		//列名
      iArray[i][1]="40px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="用户编码";         		//列名
      iArray[i][1]="40px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="订单人";         		//列名
      iArray[i][1]="40px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="联系手机";         		//列名
      iArray[i][1]="60px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="评价时间";         		//列名
      iArray[i][1]="30px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="评价等级";         		//列名
      iArray[i][1]="30px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
      
      iArray[i]=new Array();
      iArray[i][0]="评价内容";         		//列名
      iArray[i][1]="70px";            		//列宽
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