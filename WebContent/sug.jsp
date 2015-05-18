<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>建议建言</title>
	</head>
	<body>
	<%@include file="./plugin_header.jsp"%>
	<script type="text/javascript">
	var loginStr="";
	$(function(){
		loadLoginInfo();	
	});
	function loadLoginInfo()
	{
		$("#loginmsg").html(loginStr);
	}
	<%
	if(!"U".equals(ut)&&!"S".equals(ut)&&!"A".equals(ut))
	{
		%>
		loginStr="请先<a href='./user_center.html'>登陆</a>";
		$("#Button1").attr("disabled","disabled");
		<%
	}
	else
	{
		String UName = "";
		if("U".equals(ut))
			UName=sdu.getNickName();
		if("S".equals(ut))
			UName=sds.getShotOrgName();
		if("A".equals(ut))
			UName=sda.getShotOrgName();
		%>
		loginStr="<%=UName%>";
		<%
	}
	%>	
	function submit()
	{
		if(ut==null||ut=='null')
		{
			alert("请先登陆");
			return;
		}
		var NickName = $("#loginmsg").html();
		var Title = $("#Title").val();
		var Phone = $("#Phone").val();
		var Content = $("#Content").val();
		var Com =  $("#Com").find("option:selected").val();
		if(Title=='')
		{
			alert("请输入标题");
			return;
		}
		if(Content=='')
		{
			alert("请输入内容");
			return;
		}
		if(Content.length>600)
		{
			alert("内容最多600字");
			return;		
		}
		var R3="";
		if(ut=='U')
		{
			R3=userjson.UserName;
		}
		if(ut=='S')
		{
			R3=userjson.SellerName;
		}
		if(ut=='A')
		{
			R3=userjson.AssName;
		}
		var data = 
		{
				UserType:'<%=ut%>',
				NickName:NickName,
				Title:Title,
				Phone:Phone,
				Com:Com,
				Content:Content,
				R3:R3
				};
		po('com','sug',data);

	}
	function afterPost(d)
	{
		if(d.action=='com'&&d.operate=='sug')
		{
			alert(d.rd.msg);
			window.location.href="./index.html";
		}
	}
	</script>	 
		<div class="wrapper page-header">
			<h2 class="pape-title">建议建言</h2>
		</div>
		
		<!--wrapper-->
		<div class="wrapper clearfix">  
			<!--form-box-->
			<div class="form-box"> 
				<ul class="form-text">
					<li class="form-item clearfix"><span class="item-title">标题<i class="icon">*</i></span>
						<input type="text" id="Title" class="form-input text-w210"> 
					</li>
					<li class="form-item clearfix"><span class="item-title">姓名或昵称</span>
						<span id="loginmsg"></span>
					</li> 
					<li class="form-item clearfix"><span class="item-title">社区</span>
						<select id="Com" class="text-w210">
							<option value="东楼社区">东楼社区</option>
							<option value="东楼社区">南楼社区</option>
							<option value="东楼社区">西楼社区</option>
							<option value="东楼社区">北楼社区</option>
							<option value="东楼社区">中楼社区</option>
							<option value="东楼社区">西南社区</option>
							<option value="东楼社区">西北社区</option>
							<option value="东楼社区">蓝旗营社区</option>
							<option value="东楼社区">荷清苑社区</option>
						</select><span class="tip"></span>
					</li>
					<li class="form-item clearfix"><span class="item-title">内容<i class="icon">*</i></span>
						<textarea id="Content" class="text-w210" maxlength="600"></textarea>
						 <span class="tip">限600字</span>
					</li>
					<li class="form-item clearfix"><span class="item-title">我的电话</span>
						<input type="text" id="Phone" class="form-input text-w210">
					</li>
				</ul>
				
				<div class="button-box"> 
					<input id="Button1" type="button" value="提   交" onclick="submit();" class="submit-button">
				</div>
			</div>
			<!--//form-box--> 
		</div>
		<!--//wrapper-->
<%@include file="./plugin_links.jsp"%>
<%@include file="./plugin_footer.jsp"%>
	</body>
</html>
