//WriteToFileInput.js该文件中包含客户端需要处理的函数和事件

var turnPage = new turnPageClass();          //使用翻页功能，必须建立为全局变量

var showInfo;
var mDebug="0";
var tSelNo = "";
var filePath = "";


//提交，保存按钮对应操作
function submitForm() {
  //if(verifyInput() == false) return false;  
  if(!showStatistics())
  {
  	return false;	
  }
	
			fm.checkCart.value = fm.dealLX.value;
	  	fm.action="./UserManageSave.jsp";
  fm.submit();  
}


//提交后操作,服务器数据返回后执行的操作
function afterSubmit( FlagStr, content ) {
  try { showInfo.close();window.focus(); } catch(e) {}
  
  var urlStr="../common/jsp/MessagePage.jsp?picture=C&content=" + content ;  
  showModalDialog(urlStr,window,"status:no;help:0;close:0;dialogWidth:550px;dialogHeight:300px");   
}   

// 查询按钮
function easyQueryClick() {
	// 书写SQL语句
	if(verifyInput() == false) return false;
	var strSql = " select lduser.usercode, lduser.username , lduser.lockstate , case lduser.useflag  When '0' then '初试状态，不能登陆系统，需管理员确定' When '1' then '正常状态' "+
                 "when '2' then '用户未登陆状态'  end ,lduser.pwdstartdata , lduser.pwdenddata from lduser where 1= 1 ";
 if(fm.UserCode.value != null && fm.UserCode.value != '')
 {
 			strSql += " and usercode= '"+fm.UserCode.value+"'";
 	}
 	
 if(fm.dealLX.value == 1)
 {
 	    strSql += "and lockstate = '3'";
 	}
 	if(fm.dealLX.value == 2)
 {
 	    strSql += "and UseFlag = '0'";
 	}
 	if(fm.dealLX.value == 3)
 {
 	    strSql += "and pwdenddata <= trunc(sysdate) ";
 	}
				     
  //alert(strSql);
	turnPage.queryModal(strSql, BankGrid);
	 
	
}

//
function showStatistics() {
  var tSel = BankGrid.getSelNo();	 
	if( tSel == 0 || tSel == null )
	{
		alert( "请先选择一条记录，再点击返回按钮。" );
		return false;
	}	
	else
	{
			var tRow = BankGrid.getSelNo() - 1;	     
      fm.tempUserCode.value = BankGrid.getRowColData(tRow,1);
     return true;        
   } 	
             
}
