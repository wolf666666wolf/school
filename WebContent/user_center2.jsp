<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>资料修改</title>
	</head>
	<body>
	<%@include file="./plugin_header.jsp"%>
	<%@include file="./userchkU.jsp"%>	
<script>
var up = '<%=sdu.getPhone()%>';
$(function(){
	loadInfo();	
	$("#Special").val('<%=sdu.getSpecial()%>');
	$("#R1").val('<%=sdu.getR1()%>');
	if(up=='')
	{
		$("#disf21").css("display","none");
		$("#Phone").removeAttr("readonly");
	}
	else
	{
		$("#disf11").css("display","none");
		$("#disf12").css("display","none");
	}
});
function loadInfo()
{
	var data = 
   	{
			UserID:userjson.UserID
   			};
   	po('user','usercenter',data);	
}
function afterPost(d)
{
	if(d.action=='user'&&d.operate=='usercenter')
	{
		if(d.rd.succ)
		{
			$("#sc1").html(d.rd.data.c1+"次");
			$("#sc2").html(d.rd.data.c2+"次");
			$("#sc3").html(d.rd.data.c3+"次");
			$("#sc4").html(d.rd.data.c4+"次");
			$("#sc5").html(d.rd.data.c5+"次");
			$("#sc6").html(d.rd.data.c6+"次");
		}
	}
	if(d.action=='user'&&d.operate=='regmodify')
	{
		alert(d.rd.msg);
		window.location.href=window.location.href;
	}
   	if(d.action=='user'&&d.operate=='regsendcode')
   	{
   		alert(d.rd.msg);
   	}
}

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

function regmodify()
{
	   var UserName=$("#UserName").val();
	   if(UserName=='')
		{
		alert('用户名不能为空');
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
		if(Special==''||Special==null||Special=='null')Special='否';
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
		if(up=='')
		{
			if(VerifyCode=='')
			{
				alert('请填写认证码');
				return;
			}
			if(VerifyCode!=phonecode)
			{
				alert('认证码错误');
				return;				
			}
		}
	   var data = 
		{
			   UserID:userjson.UserID,
				UserName:UserName,
				Password:Password,
				NickName:NickName,
				Email:Email,
				Phone:Phone,
				TNo:TNo,
				IDNo:IDNo,
				Address:Address,
				Special:Special,
				R1:R1,
				up:up,
				VerifyCode:VerifyCode
				};
		po('user','regmodify',data);
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
</script>
		<!--wrapper-->

		<div class="wrapper clearfix">
			<div class="side-bar">
				<div class="side-nav">
					<a href="user_center.html">我的中心</a>
					<a href="user_orderlist.html">订单信息</a>
					<a href="user_content.html">我的发言</a>
					<a href="user_actlist.html">我的活动</a>
					<a href="user_favoritelist.html">我的收藏</a>
					<a href="user_followlist.html">我的关注</a> 
					<a href="user_center2.html" class="current">资料修改</a> 
				</div>
			</div>
			
			<div class="main">
				<div class="location clearfix" style="display:none;">
					<div class="links"><a href="index.html">首  页</a><i class="icon">/</i><a href="javascript:logout();">退  出</a></div>
					<div class="time-bar"><span>今天是<%=PubFun.getCurrentDateZh() %></span><span><%=sdu.getUserName() %>，您好</span></div>
				</div> 
			 
				<!--form-box-->
				<div class="form-box" style="display:none;"> 
					<ul class="form-text">
						<li class="form-item clearfix"><span class="item-title">上次登录时间：</span><span><%=sdu.getLastLoginDate() %></span></li>
					</ul>
					<ul class="form-text">
						<li class="form-item clearfix"><span class="item-title">下单次数：</span><span id="sc1">次</span>
						</li>
						<li class="form-item clearfix"><span class="item-title">成功订单：</span><span id="sc2">次</span>
						</li>
						<li class="form-item clearfix"><span class="item-title">未完成订单：</span><span id="sc3">次</span>
						</li>
						<li class="form-item clearfix"><span class="item-title">取消订单：</span><span id="sc4">次</span>
						</li>
					</ul>
					<ul class="form-text">
						<li class="form-item clearfix"><span class="item-title">评论发言：</span><span id="sc5">次</span>
						</li>
						<li class="form-item clearfix"><span class="item-title">参加次数：</span><span id="sc6">次</span>
						</li>
					</ul>
				</div>
				<!--//form-box-->
				 
			<!--form-box-->
			<div class="form-box"> 
				<ul class="form-text">
					<li class="form-item clearfix"><span class="item-title">用户名<i class="icon">*</i></span>
						<input type="text" class="form-input text-w210" id="UserName" value="<%=sdu.getUserName()%>"><span class="tip">请输入4-10个字母、数字</span>
					</li>
					<li class="form-item clearfix"><span class="item-title">密 码<i class="icon">*</i></span>
						<input class="form-input text-w210" id="Password" type="password" value="<%=sdu.getPassword()%>"><span class="tip">字母、数字的组合，最少6位</span>
					</li>
					<li class="form-item clearfix"><span class="item-title">确认密码<i class="icon">*</i></span>
						<input class="form-input text-w210" id="Password1" type="password" value="<%=sdu.getPassword()%>"><span class="tip">重新输入一遍密码</span>
					</li>
					<li class="form-item clearfix"><span class="item-title">姓名或昵称</span>
						<input type="text" class="form-input text-w210" id="NickName" value="<%=sdu.getNickName()%>"><span class="tip">是平台上您展示和交流的名字</span>
					</li>
					<li class="form-item clearfix"><span class="item-title">电子邮箱<i class="icon">*</i></span>
						<input type="text" class="form-input text-w210" id="Email"  value="<%=sdu.getEmail()%>"><span class="tip">请填有效邮箱，将用于确认链接</span>
					</li>
					<li class="form-item clearfix"><span class="item-title" id="authdis1">教工号</span>
						<input type="text" class="form-input text-w210" id="TNo"  value="<%=sdu.getTNo()%>"><span class="tip">与下面证件号码，二者提供其一即可</span>
					</li>
					<li class="form-item clearfix"><span class="item-title" id="authdis2">证件号码</span>
						<input type="text" class="form-input text-w210" id="IDNo" value="<%=sdu.getIDNo()%>">
					</li>
					<li class="form-item clearfix"><span class="item-title">所在小区 <i class="icon">*</i></span>
						<div class="address">
							<select id="R1" class="text-w210">
								<option value="小区">区域1</option>
								<option value="是">区域2</option>
								<option value="否">区域3</option>
							</select>
						</div>
					</li>					
					<li class="form-item clearfix"><span class="item-title">地址 <i class="icon">*</i></span>
						<input type="text" class="form-input text-w210" id="Address"  value="<%=sdu.getAddress()%>"><span class="tip">请录入详细地址</span>
					</li>
					<li class="form-item clearfix">
						<span class="item-title">家中特殊人群</span>
						<select id="Special" class="text-w210" value="<%=sdu.getSpecial()%>">
							<option value="否">否</option>
							<option value="是">是</option>
						</select>
					</li>
					<li class="form-item clearfix">
						<span class="item-title">手机号码 <i class="icon">*</i></span>
						<input type="text" class="form-input text-w210" id="Phone"  value="<%=sdu.getPhone()%>" readonly>
						<button class="button button_80x30" onclick="sendCode();" id="disf11">发送认证码</button>
						<span class="tip" id="disf21">不可修改</span>
					</li>
					<li class="form-item clearfix" id="disf12">
						<span class="item-title">认证码 <i class="icon">*</i></span>
						<input type="text" class="form-input text-w210" id="VerifyCode">
						<span class="tip">请填写手机接到的认证码</span>
					</li>
				</ul> 
				
				<div class="button-box">
					<input id="Button1" type="button" value="修改信息" onclick="regmodify();" class="submit-button">
				</div>
			</div>
			<!--//form-box--> 
			</div>
		</div>
		<!--//wrapper-->

<%@include file="./plugin_links.jsp"%>
<%@include file="./plugin_footer.jsp"%>
	</body>
</html>
