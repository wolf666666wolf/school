<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>用户登录</title>
	</head>
	<body>
	<%@include file="./plugin_header.jsp"%>		
		<div class="wrapper clearfix">
			<!--form-box-->
			<div class="form-box login-box"> 
				<ul class="form-text">
					<li class="form-item clearfix"><span class="item-title">用户类型<i class="icon">*</i></span>
						<select class="text-w210" id="UserType">
							<option value="U">个人用户</option>
							<option value="S">商家用户</option>
							<option value="A">社团用户</option>
						</select> 
					</li> 
					<li class="form-item clearfix"><span class="item-title">用户名<i class="icon">*</i></span>
						<input type="text" class="form-input text-w210" id="UserName"><span class="tip">请输入4-10个字母、数字</span>
					</li>
					<li class="form-item clearfix"><span class="item-title">密码<i class="icon">*</i></span>
						<input type="password" class="form-input text-w210" id="Password"><span class="tip">字母、数字的组合，最少6位</span>
					</li><li class="form-item verification-code-bar clearfix">
						<span class="item-title">验证码：</span> 
						 <input  type="text" class="form-input text-w60"   id="input1" />  
    					 <input type="text" onclick="createCode()" readonly="readonly" id="checkCode" class="unchanged"/>
    					 <span class="tip">请注意大小写</span>
					</li>
				</ul>
				
				<div class="button-box"> 
					<input id="Button1" type="button" value="登   录" onclick="pagelogin();" class="submit-button">
				</div>
			</div>
			<!--//form-box-->  
		</div>
		<%@include file="./plugin_links.jsp"%>
		<%@include file="./plugin_footer.jsp"%>
		<script>
			var code ; //在全局 定义验证码  
		     function createCode(){   
		       code = "";  
		       var codeLength = 6;//验证码的长度  
		       var checkCode = document.getElementById("checkCode");  
		       var selectChar = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z');//所有候选组成验证码的字符，当然也可以用中文的  
		          
		       for(var i=0;i<codeLength;i++){   
			       var charIndex = Math.floor(Math.random()*36);  
			       code +=selectChar[charIndex];   
		       }   
		       if(checkCode){  
		         checkCode.className="code";  
		         checkCode.value = code;  
		       }   
		     }  
		      function validate (){  
		       var inputCode = document.getElementById("input1").value;  
		       if(inputCode.length <=0) {  
		           alert("请输入验证码！");
		           return false;
		       }else if(inputCode != code ){  
		          alert("验证码输入错误！");  
		          createCode();//刷新验证码  
		          return false;
		       }else {  
		         //alert("^-^ OK"); 
		         return true;
		       }  
	       }
		      
		   createCode();
		   
		   
		   

		   function pagelogin()
		   {
		   	var HUserName=$("#UserName").val();
		   	var HPassword=$("#Password").val();
		   	var UserType = $("#UserType").find("option:selected").val();
		   	if(HUserName=='')
		   	{
		   	alert('请输入用户名');
		   	return;
		      }
		   	if(HPassword=='')
		   	{
		   	alert('请输入密码');
		   	return;
		      }
		   	if(!validate())
		   	{
		   		return;	
		   	}
		   	var data = 
		   	{
		   			UserName:HUserName,
		   			Password:HPassword,
		   			UserType:UserType
		   			};
		   	po('user','login',data);
		   }

		   function afterPost(d)
		   {
		   	if(d.action=='user'&&d.operate=='login')
		   	{
		   		if(d.rd.succ)
		   		{
		   			if(d.data.UserType=='U')
		   				{
		   				window.location.href="./user_center.html";
		   				}
		   			else if(d.data.UserType=='S')
			   			{
		   				window.location.href="./seller_center.html";
			   			}
		   			else if(d.data.UserType=='A')
			   			{
		   				window.location.href="./ass_center.html";
			   			}
		   		}
		   		else
		   		{
			   		alert(d.rd.msg);	
		   		}
		   	}
		   }
		</script>
	</body>
</html>
