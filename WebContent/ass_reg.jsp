<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>社团注册</title>
	</head>
	<body>
	<%@include file="./plugin_header.jsp"%>
	
		
		<!--sub-nav-->
		<div class="sub-nav clearfix">
			<p>已有账号，直接  <a href="login.html">登录</a></p>
		</div>
		<!--//sub-nav--> 
		 
		<div class="wrapper page-header">
			<h2 class="pape-title">社团注册申请</h2>
		</div>
		
		<!--wrapper-->
		<div class="wrapper clearfix">  
			<!--form-box-->
			<div class="form-box"> 
				<ul class="form-text">
					<li class="form-item clearfix"><span class="item-title">用户名<i class="icon">*</i></span>
						<input type="text" class="form-input text-w210" id="AssName"><span class="tip">请输入4-10个字母、数字</span>
					</li>
					<li class="form-item clearfix"><span class="item-title">密码<i class="icon">*</i></span>
						<input type="password" class="form-input text-w210" id="Password"><span class="tip">字母、数字的组合，最少6位</span>
					</li>
					<li class="form-item clearfix"><span class="item-title">确认密码<i class="icon">*</i></span>
						<input type="password" class="form-input text-w210" id="Password1"><span class="tip">重新输入一遍密码</span>
					</li>
					<li class="form-item clearfix"><span class="item-title">电子邮箱<i class="icon">*</i></span>
						<input type="text" class="form-input text-w210" id="Email"><span class="tip"></span>
					</li>
					<li class="form-item clearfix"><span class="item-title">社团名称<i class="icon">*</i></span>
						<input type="text" class="form-input text-w210" id="OrgName">
					</li> 
					<li class="form-item clearfix"><span class="item-title">社团简称<i class="icon">*</i></span>
						<input type="text" class="form-input text-w210" id="ShotOrgName"><span class="tip"></span>
					</li> 
					<li class="form-item clearfix"><span class="item-title">社团类型<i class="icon">*</i></span>
						<select class="text-w210" id="AssType">
							<option value="行业协会商会类">行业协会商会类</option>
							<option value="科技类">科技类</option>
							<option value="公益慈善类">公益慈善类</option>
							<option value="城乡社区服务类">城乡社区服务类</option>
							<option value="非经营性质的民间小型团体">非经营性质的民间小型团体</option>
						</select> 
					</li> 
					<li class="form-item clearfix"><span class="item-title">办公地址<i class="icon">*</i></span>
						<input type="text" class="form-input text-w210" id="Address">
					</li>
					<li class="form-item clearfix"><span class="item-title">社团特色</span>
						<input type="text" class="form-input text-w210" id="AssFeature"> 
					</li> 
					<li class="form-item clearfix"><span class="item-title">社团人数</span>
						<input type="text" class="form-input text-w210" id="AssPeople"> 
					</li>
					<li class="form-item clearfix">
						<span class="item-title">咨询电话<i class="icon">*</i></span>
						<input type="text" class="form-input text-w210" id="R1">
						<span class="tip">手机号码或固定电话号码均可 </span>
					</li>  
					<li class="form-item clearfix">
						<span class="item-title">网址</span>
						<input type="text" class="form-input text-w210" id="WebPage">
					</li>
					<li class="form-item clearfix">
						<span class="item-title">注册联系人<i class="icon">*</i></span>
						<input type="text" class="form-input text-w210" id="LinkPerson">
						<span class="tip">不公开，用来与平台联络 </span>
					</li> 
					<li class="form-item clearfix">
						<span class="item-title">联系人手机<i class="icon">*</i></span>
						<input type="text" class="form-input text-w210" id="Phone">  
						<button class="button button_80x30" onclick="sendCode();">发送认证码</button>
						<span class="tip">请填写正确的手机号码，用于接收手机认证码</span>
					</li> 
					<li class="form-item clearfix">
						<span class="item-title">认证码 <i class="icon">*</i></span>
						<input type="text" class="form-input text-w210" id="VerifyCode">
						<span class="tip">请填写手机接到的认证码</span>
					</li>
					<li class="form-item clearfix">
						<span class="item-title">服务机构简介</span>
						<textarea name="" class="text-w210" id="R2"></textarea><span class="tip">限300字</span>
					</li> 
				</ul>   
				
				<div class="button-box"> 
					<input type="button" value="提交注册" id="Button1" class="submit-button" onclick="reg();">
				</div>
			</div>
			<!--//form-box--> 
		</div>
		<!--//wrapper-->
<script type="text/javascript">
function reg()
{
	//取值
	var AssName = $("#AssName").val();
	var Password = $("#Password").val();
	var Password1 = $("#Password1").val();
	var Email = $("#Email").val();
	var OrgName = $("#OrgName").val();
	var ShotOrgName = $("#ShotOrgName").val();
	var Address = $("#Address").val();
	var AssFeature = $("#AssFeature").val();
	var AssPeople = $("#AssPeople").val();
	var R1 = $("#R1").val();
	var WebPage = $("#WebPage").val();
	var LinkPerson = $("#LinkPerson").val();
	var Phone = $("#Phone").val();
	var R2 = $("#R2").val();
	var AssType = $("#AssType").find("option:selected").val();
	//校验
	var arr=[
			AssName,"用户名",
			Password,"密码",
			Password1,"确认密码",
			Email,"电子邮箱",
			OrgName,"社团名称",
			Address,"办公地址",
			R1,"咨询电话",
			LinkPerson,"注册联系人",
			Phone,"联系人手机"
	];

	   if(!UserNameChk(AssName)||AssName.length<4||AssName.length>10)
	   {
	   alert('用户名应为4-10个字母、数字');
	   return;
	   
	   }


	   if(!pwdChk(Password))
		   {
		   alert('密码应为字母、数字的组合，最少6位');
		   return;
		   
		   }
	   if(!emailChk(Email))
	   {
	   alert('电子邮箱格式错误');
	   return;
	   
	   }
	if(!necval(arr))
		return;

	   if(!emailPhone(Phone))
		  {
			alert('请输入正确的联系人手机');
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
	var data = 
	{
			Password:Password,
			AssName:AssName,
			ShotOrgName:ShotOrgName,
			OrgName:OrgName,
			Email:Email,
			Phone:Phone,
			AssPeople:AssPeople,
			AssFeature:AssFeature,
			AssType:AssType,
			WebPage:WebPage,
			Address:Address,
			LinkPerson:LinkPerson,
			R1:R1,
			R2:R2
			};
	   //$("#Button1").val("提交中");
	   //$("#Button1").attr("disabled","disabled");
	po('ass','reg',data);
	
	
}
var phonecode;
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

function afterPost(d)
{
	if(d.action=='ass'&&d.operate=='reg')
	{
		alert(d.rd.msg);
		if(d.rd.succ)
   		{
   			window.location.href="./ass_center.html";
   		}
	}
	if(d.action=='user'&&d.operate=='regsendcode')
	{
		alert("发送成功");
		
	}
}
</script>
<%@include file="./plugin_links.jsp"%>
<%@include file="./plugin_footer.jsp"%>
	</body>
</html>
