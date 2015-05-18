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

<title>栏目管理</title>
</head>
<body onload="initForm();">
<form method=post name=fm target="fraSubmit">
<table>
	<tr>
		<td class=common></td>
		<td class=titleImg>一级栏目</td>
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
<INPUT class=cssButton TYPE=button VALUE=" 新 增 " onclick="addp()">
<INPUT class=cssButton TYPE=button VALUE=" 修 改 " onclick="modp();">
<INPUT class=cssButton TYPE=button VALUE=" 删 除 " onclick="delp();">
<br>
<br>
<table>
	<tr>
		<td class=common></td>
		<td class=titleImg>二级栏目</td>
	</tr>
</table>
<div id="divSelfGrid" style="display: ''" align="center">
<table class="common">
	<tr class="common">
		<td><span id="spanSunGrid"></span></td>
	</tr>
</table>

</div>

<br>
<INPUT class=cssButton TYPE=button VALUE=" 新 增 " onclick="adds()">
<INPUT class=cssButton TYPE=button VALUE=" 修 改 " onclick="mods();">
<INPUT class=cssButton TYPE=button VALUE=" 删 除 " onclick="dels();">
	<span id="spanCode" style="display: none; position: absolute;"></span>
</form>

</body>
</html>
<script>
var turnPage = new turnPageClass();  
var turnPage_s = new turnPageClass();  
	function initForm()
	{
	initSelfGrid();
	initSunGrid();
	queryp();
	querys();
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
        var sno = SelfGrid.getRowColData(tRow,5);
    	OpenWindow("../admin/ChnlAdd.jsp?level=1&parent=0&sno="+sno,"一级栏目维护",400,300);
 		queryp();
    }
}
var opt = "";
function mods()
{
	var tSel = SunGrid.getSelNo();	 
	if( tSel == 0 || tSel == null )
	{
	    alert( "请先选择一条记录" );
	    return false;
	}	
	else
	{
	   	var tRow = SunGrid.getSelNo() - 1;	     
        var sno = SunGrid.getRowColData(tRow,5);
    	OpenWindow("../admin/ChnlAdd.jsp?level=2&sno="+sno,"二级栏目维护",400,400);
 		querys();
    }
}
function delp()
{
	var tSel = SelfGrid.getSelNo();	 
	if( tSel == 0 || tSel == null )
	{
	    alert( "请先选择一条记录" );
	    return false;
	}	
	else
	{
		if(confirm("删除一级栏目，附带将会删除此栏目下的所有二级栏目，确定删除么？"))
		{
			var tRow = SelfGrid.getSelNo() - 1;	     
	        var sno = SelfGrid.getRowColData(tRow,5);
			fm.action="./pa.jsp?a=chnlmgt&o=del&SerialNo="+sno;
			opt="p";
			fm.submit();
		}	   
    }
}
function dels()
{
	var tSel = SunGrid.getSelNo();	 
	if( tSel == 0 || tSel == null )
	{
	    alert( "请先选择一条记录" );
	    return false;
	}	
	else
	{
		if(confirm("确定删除么？"))
		{
			var tRow = SunGrid.getSelNo() - 1;	     
	        var sno = SunGrid.getRowColData(tRow,5);
			fm.action="./pa.jsp?a=chnlmgt&o=del&SerialNo="+sno;
			opt="s";
			fm.submit();
		}	   
    }
}
function adds()
{
	var tSel = SelfGrid.getSelNo();	 
	if( tSel == 0 || tSel == null )
	{
	    alert( "请先选择一级栏目" );
	    return false;
	}	
	else
	{
	   	var tRow = SelfGrid.getSelNo() - 1;	     
        var sno = SelfGrid.getRowColData(tRow,5);
    	OpenWindow("../admin/ChnlAdd.jsp?level=2&parent="+sno,"二级栏目维护",400,300);
 		querys();
    }	
}

function afterSubmit(a,b)
{
	alert(b);
	if(a=="Succ")
	{
		if(opt=='p')
			queryp();
		else if (opt=='s')
			querys();
		else
		{
			
		}
	}
}
function addp()
{
	OpenWindow("../admin/ChnlAdd.jsp?level=1&parent=0","一级栏目维护",400,300);
	queryp();
}
function queryp()
{
	var SQL = "select ChannelName,URL,R1,R2,SerialNo,ParentChannel,ChannelLevel from sdchannel where ParentChannel='0' order by cast (R1 as number) asc";
	turnPage.pageLineNum=20;
	turnPage.queryModal(SQL, SelfGrid);
}
function querys()
{
	var tSel = SelfGrid.getSelNo();	 
	if( tSel == 0 || tSel == null )
	{
	  
	}	
	else
	{
	   	var tRow = SelfGrid.getSelNo() - 1;	     
        var sno = SelfGrid.getRowColData(tRow,5);
        var SQL = "select ChannelName,URL,R1,R2,SerialNo,ParentChannel,ChannelLevel,(select codename from ldcode where codetype = 'chnltype' and code=R3),"
        +"r4,r5,r6,r7,r8 "
        +" from sdchannel where ParentChannel='"+sno+"' order by cast (R1 as number) asc";
    	turnPage_s.pageLineNum=20;
    	turnPage_s.queryModal(SQL, SunGrid);
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
      iArray[i][0]="名称";         		//列名
      iArray[i][1]="80px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="链接";         		//列名
      iArray[i][1]="100px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=3;              			//是否允许输入,1表示允许，0表示不允许
             
      iArray[i]=new Array();
      iArray[i][0]="顺序";         		//列名
      iArray[i][1]="40px";            		//列宽
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
      
      iArray[i]=new Array();
      iArray[i][0]="父栏目";         		//列名
      iArray[i][1]="40px";            		//列宽
      iArray[i][2]=100;            			//列最大值
      iArray[i++][3]=3;              			//是否允许输入,1表示允许，0表示不允许
      
      iArray[i]=new Array();
      iArray[i][0]="栏目级别";         		//列名
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
function initSunGrid()
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
    iArray[i][0]="名称";         		//列名
    iArray[i][1]="40px";            		//列宽
    iArray[i][2]=100;            			//列最大值
    iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
           
    iArray[i]=new Array();
    iArray[i][0]="链接";         		//列名
    iArray[i][1]="80px";            		//列宽
    iArray[i][2]=100;            			//列最大值
    iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
           
    iArray[i]=new Array();
    iArray[i][0]="顺序";         		//列名
    iArray[i][1]="25px";            		//列宽
    iArray[i][2]=100;            			//列最大值
    iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
           
    iArray[i]=new Array();
    iArray[i][0]="是否显示";         		//列名
    iArray[i][1]="30px";            		//列宽
    iArray[i][2]=100;            			//列最大值
    iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
    
    iArray[i]=new Array();
    iArray[i][0]="流水号";         		//列名
    iArray[i][1]="40px";            		//列宽
    iArray[i][2]=100;            			//列最大值
    iArray[i++][3]=3;              			//是否允许输入,1表示允许，0表示不允许
    
    iArray[i]=new Array();
    iArray[i][0]="父栏目";         		//列名
    iArray[i][1]="40px";            		//列宽
    iArray[i][2]=100;            			//列最大值
    iArray[i++][3]=3;              			//是否允许输入,1表示允许，0表示不允许
    
    iArray[i]=new Array();
    iArray[i][0]="栏目级别";         		//列名
    iArray[i][1]="40px";            		//列宽
    iArray[i][2]=100;            			//列最大值
    iArray[i++][3]=3;              			//是否允许输入,1表示允许，0表示不允许
    
    iArray[i]=new Array();
    iArray[i][0]="栏目类型";         		//列名
    iArray[i][1]="30px";            		//列宽
    iArray[i][2]=100;            			//列最大值
    iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
    
    iArray[i]=new Array();
    iArray[i][0]="管理员发布";         		//列名
    iArray[i][1]="30px";            		//列宽
    iArray[i][2]=100;            			//列最大值
    iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
    
    iArray[i]=new Array();
    iArray[i][0]="认证商家发布";         		//列名
    iArray[i][1]="40px";            		//列宽
    iArray[i][2]=100;            			//列最大值
    iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
    
    iArray[i]=new Array();
    iArray[i][0]="非认证商家发布";         		//列名
    iArray[i][1]="40px";            		//列宽
    iArray[i][2]=100;            			//列最大值
    iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
    
    iArray[i]=new Array();
    iArray[i][0]="社团发布";         		//列名
    iArray[i][1]="30px";            		//列宽
    iArray[i][2]=100;            			//列最大值
    iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
    
    iArray[i]=new Array();
    iArray[i][0]="个人发布";         		//列名
    iArray[i][1]="30px";            		//列宽
    iArray[i][2]=100;            			//列最大值
    iArray[i++][3]=0;              			//是否允许输入,1表示允许，0表示不允许
    
          
             
             
      
      SunGrid = new MulLineEnter( "fm" , "SunGrid" ); 
      //这些属性必须在loadMulLine前
      SunGrid.mulLineCount = 0;   
      SunGrid.displayTitle = 1;
      SunGrid.locked = 1;
      SunGrid.canSel = 1;
      SunGrid.canChk = 0;
      SunGrid.hiddenPlus = 1;
      SunGrid.hiddenSubtraction = 1;  
      SunGrid.selBoxEventFuncName = "ShowData2";         
      SunGrid.loadMulLine(iArray);  
      //这些操作必须在loadMulLine后面

	}
	catch(ex)
	{
		alert(ex);
	}
}
</script>