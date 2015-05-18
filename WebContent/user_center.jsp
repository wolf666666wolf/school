<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>个人中心</title>
	</head>
	<body>
	<%@include file="./plugin_header.jsp"%>
	<%@include file="./userchkU.jsp"%>	
<script>
$(function(){
	loadInfo();	
	$("#Special").val('<%=sdu.getSpecial()%>');
	$("#R1").val('<%=sdu.getR1()%>');
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
	   var TNo=$("#TNo").val();
	   var IDNo=$("#IDNo").val();
	   if(TNo==''&&IDNo=='')
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
				R1:R1
				};
		po('user','regmodify',data);
}
</script>
		<!--wrapper-->

		<div class="wrapper clearfix">
			<div class="side-bar">
				<div class="side-nav">
					<a href="user_center.html" class="current">我的中心</a>
					<a href="user_orderlist.html">订单信息</a>
					<a href="user_content.html">我的发言</a>
					<a href="user_actlist.html">我的活动</a>
					<a href="user_favoritelist.html">我的收藏</a>
					<a href="user_followlist.html">我的关注</a> 
					<a href="user_center2.html">资料修改</a> 
				</div>
			</div>
			
			<div class="main">
				<div class="location clearfix">
					<div class="links"><a href="index.html">首  页</a><i class="icon">/</i><a href="javascript:logout();">退  出</a></div>
					<div class="time-bar"><span>今天是<%=PubFun.getCurrentDateZh() %></span><span><%=sdu.getNickName() %>，您好</span></div>
				</div> 
			 
				<!--form-box-->
				<div class="form-box"> 
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
						<li class="form-item clearfix"><span class="item-title">评论次数：</span><span id="sc5">次</span>
						</li>
						<li class="form-item clearfix"><span class="item-title">参加活动次数：</span><span id="sc6">次</span>
						</li>
					</ul>
				</div>
				<!--//form-box-->
				 
			<!--form-box-->
			<div class="form-box" style="display:none"> 
				<ul class="form-text">
					<li class="form-item clearfix"><span class="item-title">用 户 名<i class="icon">*</i></span>
						<input type="text" class="form-input text-w210" id="UserName" value="<%=sdu.getUserName()%>"><span class="tip">请输入4-10个字母、数字</span>
					</li>
					<li class="form-item clearfix"><span class="item-title">密　　 码<i class="icon">*</i></span>
						<input class="form-input text-w210" id="Password" type="password" value="<%=sdu.getPassword()%>"><span class="tip">字母、数字的组合，最少6位</span>
					</li>
					<li class="form-item clearfix"><span class="item-title">确 认 密 码<i class="icon">*</i></span>
						<input class="form-input text-w210" id="Password1" type="password" value="<%=sdu.getPassword()%>"><span class="tip">重新输入一遍密码</span>
					</li>
					<li class="form-item clearfix"><span class="item-title">姓名或昵称</span>
						<input type="text" class="form-input text-w210" id="NickName" value="<%=sdu.getNickName()%>"><span class="tip">是平台上您展示和交流的名字</span>
					</li>
					<li class="form-item clearfix"><span class="item-title">电 子 邮 箱<i class="icon">*</i></span>
						<input type="text" class="form-input text-w210" id="Email"  value="<%=sdu.getEmail()%>"><span class="tip">请填有效邮箱，将用于确认链接</span>
					</li>
					<li class="form-item clearfix"><span class="item-title">教工号</span>
						<input type="text" class="form-input text-w210" id="TNo"  value="<%=sdu.getTNo()%>"><span class="tip">与下面证件号码，二者提供其一即可</span>
					</li>
					<li class="form-item clearfix"><span class="item-title">证件号码</span>
						<input type="text" class="form-input text-w210" id="IDNo" value="<%=sdu.getIDNo()%>">
					</li>
					<li class="form-item clearfix"><span class="item-title">所 在 小 区 <i class="icon">*</i></span>
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
						<span class="tip">不可修改</span>
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
