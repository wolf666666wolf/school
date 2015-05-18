<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>社团中心</title>
	</head>
	<body>
	<%@include file="./plugin_header.jsp"%>
	<%@include file="./userchkA.jsp"%>	

<script>
$(function(){
	$("#AssType").val('<%=sda.getAssType()%>');
});

function regmodify()
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
	if(!necval(arr))
		return;
	

	var data = 
	{
			AssID:userjson.AssID,
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
	po('ass','regmodify',data);	
}
function afterPost(d)
{
	if(d.action=='ass'&&d.operate=='regmodify')
	{
		alert(d.rd.msg);
	}
}
</script>
		
		<!--wrapper 
		<div class="wrapper clearfix"> 
			<div class="ad-div"><div><img src="../images/photo.jpg" width="1024" height="136" alt="" /></div></div>
		</div>
		//wrapper-->
		
		<!--wrapper-->

		<div class="wrapper clearfix">
			<div class="side-bar">
				<div class="side-nav">
					<a href="ass_center.html" class="current">社团中心</a>
					<a href="ass_actlist.html">活动管理</a>
					<a href="ass_content.html">信息发布</a>
					<a href="ass_center2.html">资料修改</a>
				</div>
			</div>
			
			<div class="main">
				<div class="location clearfix">
					<div class="links"><a href="index.html">首  页</a><i class="icon">/</i><a href="javascript:logout();">退  出</a></div>
					<div class="time-bar"><span>今天是<%=PubFun.getCurrentDateZh() %></span><span>您好，<%=sda.getShotOrgName() %></span></div>
				</div> 
			 
				<!--form-box-->
				<div class="form-box"> 
					<ul class="form-text">
						<li class="form-item clearfix"><span class="item-title">上次登录时间：</span><span><%=sda.getLastLoginDate()+" "+sda.getLastLoginTime() %></span></li>
					</ul>
				</div>
				<!--//form-box-->
				   
			<!--form-box-->
			<div class="form-box" style="display:none"> 
				<ul class="form-text">
					<li class="form-item clearfix"><span class="item-title">用 户 名<i class="icon">*</i></span>
						<input type="text" class="form-input text-w210" id="AssName" value="<%=sda.getAssName()%>"><span class="tip">请输入4-10个字母、数字</span>
					</li>
					<li class="form-item clearfix"><span class="item-title">密码<i class="icon">*</i></span>
						<input type="password" class="form-input text-w210" id="Password" value="<%=sda.getPassword()%>"><span class="tip">字母、数字的组合，最少6位</span>
					</li>
					<li class="form-item clearfix"><span class="item-title">确认密码<i class="icon">*</i></span>
						<input type="password" class="form-input text-w210" id="Password1" value="<%=sda.getPassword()%>"><span class="tip">重新输入一遍密码</span>
					</li>
					<li class="form-item clearfix"><span class="item-title">电 子 邮 箱<i class="icon">*</i></span>
						<input type="text" class="form-input text-w210" id="Email" value="<%=sda.getEmail()%>"><span class="tip">请填有效邮箱，将用于确认链接</span>
					</li>
					<li class="form-item clearfix"><span class="item-title">社团名称<i class="icon">*</i></span>
						<input type="text" class="form-input text-w210" id="OrgName" value="<%=sda.getOrgName()%>">
					</li> 
					<li class="form-item clearfix"><span class="item-title">社团简称</span>
						<input type="text" class="form-input text-w210" id="ShotOrgName" value="<%=sda.getShotOrgName()%>"><span class="tip">省、地区+简称，4 - 8个汉字，如</span>
					</li> 
					<li class="form-item clearfix"><span class="item-title">社团类型<i class="icon">*</i></span>
						<select class="text-w210" id="AssType">
							<option value="请选择">请选择</option>
							<option value="社区选建设1">社区选建设1</option>
							<option value="社区选建设2">社区选建设2</option>
						</select> 
					</li> 
					<li class="form-item clearfix"><span class="item-title">办公地址<i class="icon">*</i></span>
						<input type="text" class="form-input text-w210" id="Address" value="<%=sda.getAddress()%>">
					</li>
					<li class="form-item clearfix"><span class="item-title">社团特色</span>
						<input type="text" class="form-input text-w210" id="AssFeature" value="<%=sda.getAssFeature()%>"> 
					</li> 
					<li class="form-item clearfix"><span class="item-title">社团人数</span>
						<input type="text" class="form-input text-w210" id="AssPeople" value="<%=sda.getAssPeople()%>"> 
					</li>
					<li class="form-item clearfix">
						<span class="item-title">咨询电话<i class="icon">*</i></span>
						<input type="text" class="form-input text-w210" id="R1" value="<%=sda.getR1()%>">
					</li>  
					<li class="form-item clearfix">
						<span class="item-title">网址</span>
						<input type="text" class="form-input text-w210" id="WebPage" value="<%=sda.getWebPage()%>">
					</li>
					<li class="form-item clearfix">
						<span class="item-title">注册联系人<i class="icon">*</i></span>
						<input type="text" class="form-input text-w210" id="LinkPerson" value="<%=sda.getLinkPerson()%>">
						<span class="tip">不公开，用来与平台联络 </span>
					</li> 
					<li class="form-item clearfix">
						<span class="item-title">联系人手机<i class="icon">*</i></span>
						<input type="text" class="form-input text-w210" id="Phone" value="<%=sda.getPhone()%>" readonly> 
						<span class="tip">不可修改 </span>
					</li> 
					<li class="form-item clearfix">
						<span class="item-title">服务机构简介</span>
						<textarea name="" class="text-w210" id="R2" value="<%=sda.getR2()%>"></textarea><span class="tip">限300字</span>
					</li> 
				</ul>   
				
				<div class="button-box"> 
					<input type="button" value="修改信息" id="Button1" class="submit-button" onclick="regmodify();">
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
