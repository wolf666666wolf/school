<%
//程序名称：WriteToFileInit.jsp
//程序功能：
//创建日期：
//创建人  ：
//更新记录：  更新人    更新日期     更新原因/内容
%>
<!--用户校验类-->
<%@include file="../common/jsp/UsrCheck.jsp"%>

<SCRIPT src="../common/javascript/Common.js"></SCRIPT>
<SCRIPT src="../common/easyQueryVer3/EasyQueryVer3.js"></SCRIPT>

<script language="JavaScript">

// 输入框的初始化（单记录部分）

function initForm() {
  try {
  	
  	
  	
  	
  	initBankGrid();
  	
  	
  	
 
  }
  catch(re) {
    alert("InitForm 函数中发生异常:初始化界面错误!");
  }
}

// 领取项信息列表的初始化
var BankGrid;
function initBankGrid() {                               
  var iArray = new Array();
  try {
    iArray[0]=new Array();
    iArray[0][0]="序号";         			//列名（此列为顺序号，列名无意义，而且不显示）
    iArray[0][1]="30px";            	//列宽
    iArray[0][3]=0;              			//是否允许输入,1表示允许，0表示不允许

    iArray[1]=new Array();
    iArray[1][0]="用户名";         		//列名
    iArray[1][1]="100px";            	//列宽
    iArray[1][3]=0;              			//是否允许输入,1表示允许，0表示不允许

		iArray[2]=new Array();
    iArray[2][0]="用户姓名";         		//列名
    iArray[2][1]="100px";            	//列宽
    iArray[2][3]=0;  

    iArray[3]=new Array();
    iArray[3][0]="用户密码锁定状态";         		//列名
    iArray[3][1]="100px";            	//列宽
    iArray[3][3]=0;              			//是否允许输入,1表示允许，0表示不允许
    
    iArray[4]=new Array();
    iArray[4][0]="用户状态";         		//列名
    iArray[4][1]="100px";            	//列宽
    iArray[4][3]=0;              			//是否允许输入,1表示允许，0表示不允许
    
    iArray[5]=new Array();
    iArray[5][0]="密码开始日期";         		//列名
    iArray[5][1]="100px";            	//列宽
    iArray[5][3]=0;              			//是否允许输入,1表示允许，0表示不允许
    
   
    iArray[6]=new Array();
    iArray[6][0]="密码终止日期";         		//列名
    iArray[6][1]="100px";            	//列宽
    iArray[6][3]=0;              			//是否允许输入,1表示允许，0表示不允许
   
   

    BankGrid = new MulLineEnter( "fm" , "BankGrid" ); 
    //这些属性必须在loadMulLine前
    BankGrid.mulLineCount = 3;   
    BankGrid.displayTitle = 1;
    BankGrid.hiddenPlus = 1;
    BankGrid.hiddenSubtraction = 1;
    BankGrid.canSel = 1;
    BankGrid.canChk = 0;
    BankGrid.selBoxEventFuncName = "showStatistics";
    BankGrid.loadMulLine(iArray);  
    
    //这些操作必须在loadMulLine后面
    //BankGrid.setRowColData(1,1,"asdf");
  }
  catch(ex) {
    alert(ex);
  }
  }
 

</script>

	
