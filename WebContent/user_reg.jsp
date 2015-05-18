<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>个人注册</title>
	</head>
	<body>
<%@include file="./plugin_header.jsp"%>
		<!--sub-nav-->
		<div class="sub-nav clearfix">
			<p>已有账号，直接  <a href="login.html">登录</a></p>
		</div>
		<!--//sub-nav--> 
		 
		<div class="wrapper page-header">
			<h2 class="pape-title">个人注册</h2>
		</div>
		
		<!--wrapper-->
		<div class="wrapper clearfix">  
			<!--form-box-->
			<div class="form-box"> 
				<ul class="form-text">
					<li class="form-item clearfix"><span class="item-title">用户名<i class="icon">*</i></span>
						<input type="text" class="form-input text-w210" id="UserName"><span class="tip">请输入4-10个字母、数字</span>
					</li>
					<li class="form-item clearfix"><span class="item-title">密码<i class="icon">*</i></span>
						<input class="form-input text-w210" id="Password" type="password"><span class="tip">字母、数字的组合，最少6位</span>
					</li>
					<li class="form-item clearfix"><span class="item-title">确认密码<i class="icon">*</i></span>
						<input class="form-input text-w210" id="Password1" type="password"><span class="tip">重新输入一遍密码</span>
					</li>
					<li class="form-item clearfix"><span class="item-title">姓名或昵称<i class="icon">*</i></span>
						<input type="text" class="form-input text-w210" id="NickName"><span class="tip">是平台上您展示和交流的名字</span>
					</li>
					<li class="form-item clearfix"><span class="item-title">电子邮箱<i class="icon">*</i></span>
						<input type="text" class="form-input text-w210" id="Email"><span class="tip"></span>
					</li>
					<li class="form-item clearfix" style="display:none;"><span class="item-title">是否认证<i class="icon">*</i></span>
						<select id="AuthFlagSelect" class="text-w210" onchange="changeAuthFlag()">
							<option value="是">是</option>
							<option value="否">否</option>
						</select><span class="tip">认证后可在网上下订单，并开通个人中心功能</span>
					</li>
					<li class="form-item clearfix" id="authdis1"><span class="item-title">教工号</span>
						<input type="text" class="form-input text-w210" id="TNo"><span class="tip">与下面证件号码，二者提供其一即可</span>
					</li>
					<li class="form-item clearfix" id="authdis2"><span class="item-title">证件号码</span>
						<input type="text" class="form-input text-w210" id="IDNo">
					</li>
					<li class="form-item clearfix"><span class="item-title">所在小区 <i class="icon">*</i></span>
						<div class="address">
							<select id="R1" class="text-w210">
								<option value="管理办公区">管理办公区</option> 
								<option value="公共教学区">公共教学区</option> 
								<option value="各院系教学区">各院系教学区</option> 
								<option value="生活服务区">生活服务区</option> 
								<option value="校企产业">校企产业</option> 
								<option value="清华科技园">清华科技园 </option> 
								<option value="体育场馆">体育场馆</option> 
								<option value="主楼">主楼</option> 
								<option value="大礼堂">大礼堂</option> 
								<option value="老图书馆">老图书馆</option> 
								<option value="新图书馆">新图书馆</option> 
								<option value="蓝旗营住宅区">蓝旗营住宅区</option> 
								<option value="东小区">东小区</option> 
								<option value="西小区">西小区</option> 
								<option value="中区">中区</option> 
								<option value="东南小区">东南小区</option> 
								<option value="西北小区">西北小区</option> 
								<option value="西南小区">西南小区</option> 
								<option value="老年公寓">老年公寓</option> 
								<option value="荷清苑小区">荷清苑小区</option> 
								<option value="青年公寓">青年公寓</option> 
								<option value="其他">其他</option> 
							</select>
						</div>
					</li>					
					<li class="form-item clearfix"><span class="item-title">地址<i class="icon">*</i></span>
						<input type="text" class="form-input text-w210" id="Address"><span class="tip">请录入详细地址</span>
					</li>
					<li class="form-item clearfix">
						<span class="item-title">家中特殊人群</span>
						<select id="Special" class="text-w210">
							<option value="否">否</option>
							<option value="是">是</option>
						</select>
					</li>
					<li class="form-item clearfix">
						<span class="item-title">手机号码 <i class="icon">*</i></span>
						<input type="text" class="form-input text-w210" id="Phone">
						<button class="button button_80x30" onclick="sendCode();">发送认证码</button>
						<span class="tip">请填写正确的手机号码，用于接收手机认证码</span>
					</li>
					<li class="form-item clearfix">
						<span class="item-title">认证码 <i class="icon">*</i></span>
						<input type="text" class="form-input text-w210" id="VerifyCode">
						<span class="tip">请填写手机接到的认证码</span>
					</li>
				</ul> 
				
				<ul class="form-text" style="display:none">
					<li class="form-item verification-code-bar clearfix">
						<span class="item-title">验证码：</span> 
						 <input  type="text" class="form-input text-w60"   id="input1" />  
    					 <input type="text" onclick="createCode()" readonly="readonly" id="checkCode" class="unchanged"/>
    					 <span class="tip">请注意大小写</span>
					</li>
				</ul>
				
				<div class="button-box">
					<input id="Button1" type="button" value="提交" onclick="reg();" class="submit-button">
				</div>
			</div>
			<!--//form-box--> 
		</div>
		<!--//wrapper-->
		
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
		         return true;  
		       }  
	       }
		      
		   //createCode();
		   
		   function changeAuthFlag()
		   {
			   if($("#AuthFlagSelect").find("option:selected").val()=='否')
				   {
				   $("#authdis1").css('display','none');
				   $("#authdis2").css('display','none');
				   }
			   else
				   {
				   $("#authdis1").css('display','');
				   $("#authdis2").css('display','');
				   
				   }
		   }
		   
		   var phonecode="";
		   function sendCode()
		   {

			   var Phone=$("#Phone").val();
			   if(Phone=='')
				{
				alert('手机号码不能为空');
				return;
			   }
			   if(!emailPhone(Phone))
				  {
					alert('请输入正确的手机号码');
					return;
				   
				  }
			   var codeLength = 6;//验证码长度
			   phonecode="";
				for (var i = 0; i < codeLength; i++) {
						phonecode += parseInt(Math.random() * 9).toString();
		            }
				 var data = 
					{
							code:phonecode,
							Phone:Phone
							};
					po('user','regsendcode',data);
			   //alert("认证码是："+phonecode+"，接通短信接口后，会将此号码发送到手机上");
		   }
		   function reg()
		   {
			   var UserName=$("#UserName").val();
			   if(UserName=='')
				{
				alert('用户名不能为空');
				return;
			   }

			   if(!UserNameChk(UserName)||UserName.length<4||UserName.length>10)
			   {
			   alert('用户名应为4-10个字母、数字');
			   return;
			   
			   }
			   var Password=$("#Password").val();
			   if(Password=='')
				{
				alert('密码不能为空');
				return;
			   }
			   if(!pwdChk(Password))
			   {
			   alert('密码应为字母、数字的组合，最少6位');
			   return;
			   
			   }
			   var Password1=$("#Password1").val();
			   var NickName=$("#NickName").val();
			   if(Password1=='')
				{
				alert('确认密码不能为空');
				return;
			   }
			   if(Password!=Password1)
				 {
				   alert('两次输入密码不一致');
					return; 
			  }
			   var Email=$("#Email").val();
			   if(Email=='')
				{
				alert('电子邮箱不能为空');
				return;
			   }
			   if(!emailChk(Email))
			   {
			   alert('电子邮箱格式错误');
			   return;
			   
			   }
			   
			   var AuthFlag =  $("#AuthFlagSelect").find("option:selected").val();
			   if(AuthFlag=='')
				{
				alert('请选择是否认证');
				return;
			   }
			   var TNo=$("#TNo").val();
			   var IDNo=$("#IDNo").val();
			   if(TNo==''&&IDNo==''&&$("#AuthFlagSelect").find("option:selected").val()!='否')
				{
					alert('教工号和证件号码至少填写一个');
					return;				   
		   		}
			   var R1 =  $("#R1").find("option:selected").val();

			   var Address=$("#Address").val();
			   if(Address=='')
				{
				alert('地址不能为空');
				return;
			   }
			   var Special =  $("#Special").find("option:selected").val();

			   var Phone=$("#Phone").val();
			   if(Phone=='')
				{
				alert('手机号码不能为空');
				return;
			   }

			   if(!emailPhone(Phone))
				  {
					alert('请输入正确的手机号码');
					return;
				   
				  }
			   var VerifyCode=$("#VerifyCode").val();
			   if(VerifyCode=='')
				{
				alert('认证码不能为空');
				return;
			   }
			   if(VerifyCode!=phonecode)
				   {
					alert('认证码错误');
					return;
				   
				   }
			   //if(!validate())
			//	   {
			//	   return;
			//	   }
			   $("#Button1").val("提交中");
			   $("#Button1").attr("disabled","disabled");
			   var data = 
				{
						UserName:UserName,
						Password:Password,
						NickName:NickName,
						Email:Email,
						Phone:Phone,
						AuthFlag:AuthFlag,
						TNo:TNo,
						IDNo:IDNo,
						Address:Address,
						Special:Special,
						R1:R1
						};
				po('user','reg',data);
		   }

		   function afterPost(d)
		   {
			   	if(d.action=='user'&&d.operate=='regsendcode')
			   	{
			   		alert(d.rd.msg);
			   	}
				if(d.action=='user'&&d.operate=='reg')
				{
		   		alert(d.rd.msg);
		   		if(d.rd.succ)
		   		{
		   			window.location.href="./user_center.html";
		   		}
		   	}
		   }
		</script>
	</body>
</html>
