<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>清华大学后勤综合服务平台</title>		

	</head>
	<body class="articlePage">
	<%@include file="./plugin_header.jsp"%>
		<script type="text/javascript">
		var actid= '<%=request.getParameter("id")%>';
		$(function(){
			loadContent();	
			loadSide();
		});
		function loadSide()
		{
			po('content','getsidelist',{});

		}
		function regact()
		{
			var nowDate = '<%=PubFun.getCurrentDate()%>';
			var regenddate = $("#RegEndDate").html();
			if(nowDate>regenddate)
			{
				alert('报名已经截止');
				return;
			}
			var data = 
		   	{
		   			ActID:actid,
		   			UserID:userjson.UserID
		   			};
		   	po('act','regact',data);
		}
		function loadContent()
		{
			var userid=userjson.UserID;
			if(userid==null||userid==undefined)userid="";
			var data = 
		   	{
		   			ActID:actid,
		   			UserID:userid
		   			};
		   	po('act','getact',data);	
		}

		function afterPost(d)
		{
			if(d.action=='content'&&d.operate=='getsidelist')
			{
				$("#result1").setTemplateElement("template1");
			    $("#result1").processTemplate(d.rd.data.result1);
			    $("#result2").setTemplateElement("template2");
			    $("#result2").processTemplate(d.rd.data.result2);
			}
			if(d.action=='act'&&d.operate=='getact')
			{
				if(d.rd.succ)
				{
					var act = d.rd.data.act;
					$("#Title").html(act.Title);
					$("#ActTitle").html(act.Title+"详细信息");
					$("#AssName").html(act.ShotOrgName);
					$("#StartDate").html(act.StartDate+" "+act.StartTime);
					$("#EndDate").html(act.EndDate+" "+act.EndTime);
					$("#ActFee").html(act.ActFee);
					$("#RegEndDate").html(act.RegEndDate);
					$("#ActRule").html(act.ActRule);
					$("#ActContent").html(act.ActContent);
					$("#ActGift").html(act.ActGift);
					$("#Phone").html(act.Phone);
					$("#AcPeople").html(act.AcPeople);
					$("#AtnPeople").html(act.AtnPeople);
					$("#ActStatus").html(act.ActStatus);
					$("#R3").html(act.R3);
					if(act.ActStatus!='报名中')
					{
						$("#Button1").css('display','none');
						}
					if(act.AtnFlag==0)
					$("#Button1").val("我要参加");
					else
						$("#Button1").val("取消参加");

				}
			}
			if(d.action=='act'&&d.operate=='regact')
			{
				alert(d.rd.msg);
				if(d.rd.msg.indexOf("取消")!=-1)
					$("#Button1").val("我要参加");
				else
					$("#Button1").val("取消参加");
			}
		}
	</script>
		<div class="wrapper clearfix"> 
		
		<div class="wrapper page-header">
			<h2 class="pape-title" id="ActTitle">活动详情</h2>
		</div>
			<div class="main">
				 <div class="article-box">
				 	<div class="article-header">
				 		<h1 class="article-title"></h1>
				 		<div class="article-infor"></div>
				 	</div>
				 	<div class="wrapper clearfix">
				 	
						<!--form-box-->
						<div class="form-box"> 
							<ul class="form-text">
								<li class="form-item clearfix"><span class="item-title">活动标题</span>
									<span id="Title"></span>
								</li>
								<li class="form-item clearfix"><span class="item-title">创建人</span>
									<span id="AssName"></span>
								</li> 
								<li class="form-item clearfix"><span class="item-title">活动开始时间</span>
									<span id="StartDate"></span>
								</li>
								<li class="form-item clearfix"><span class="item-title">活动结束时间</span>
									<span id="EndDate"></span>
								</li>
								<li class="form-item clearfix"><span class="item-title">活动地点</span>
									<span id="R3"></span>
								</li>
								<li class="form-item clearfix"><span class="item-title">缴纳费用</span>
									<span id="ActFee"></span>
								</li>
								<li class="form-item clearfix"><span class="item-title">报名截止日期</span>
									<span id="RegEndDate"></span>
								</li>
								<li class="form-item clearfix"><span class="item-title">活动规则</span>
									<span id="ActRule" style="width:50%;"></span>
								</li>
								<li class="form-item clearfix"><span class="item-title">活动内容</span>
									<span id="ActContent" style="width:50%;"></span>
								</li>
								<li class="form-item clearfix"><span class="item-title">奖品赠品</span>
									<span id="ActGift"></span>
								</li>
								<li class="form-item clearfix"><span class="item-title">联系人电话</span>
									<span id="Phone"></span>
								</li>
								<li class="form-item clearfix"><span class="item-title">活动计划人数</span>
									<span id="AcPeople"></span>
								</li>
								<li class="form-item clearfix"><span class="item-title">目前参加人数</span>
									<span id="AtnPeople"></span>
								</li>
								<li class="form-item clearfix"><span class="item-title">活动状态</span>
									<span id="ActStatus"></span>
								</li>
							</ul> 
						</div>
						<%
						if(!"U".equals(ut))
						{
							%>
							<div class="button-box">  
								<input id="Button1" value="我要参加" onclick="alert('请先登陆');" class="submit-button">
							</div>
							<%
						}
						else
						{
						%>
			<div class="button-box">  
				<input id="Button1" type="button" value="我要参加" onclick="regact();" class="submit-button">
			</div>
						<%} %>
						<!--//form-box-->
					</div>
				</div>
			</div>
			
			<div class="aside"><!--section-->
				<div class="section news-broadcast-section">
					<div class="section-header">
						<a href="class_3_C0041.html" class="pic more"><img src="./images/more.png"/></a>
						<h3 class="section-title">其他新闻</h3>
					</div>
					<div class="section-content">
						<div id="result1"></div>
						<textarea id="template1" style="display:none"> 
						<ul class="news-list">
						{#foreach $T as record} 
							<li><span class="date">{$T.record.IssueDate}</span><a href="./content_{$T.record.SerialNo}.html" target="_blank">{$T.record.ContentTitle}</a></li>
						{#/for} 
						</ul>
						</textarea>
					</div>
				</div>
				<!--//section--> 
				
				<!--section-->
				<div class="section hot-services-section">
					<div class="section-header">
						<a href="class_3_C0042.html" class="pic more"><img src="./images/more.png"/></a>
						<h3 class="section-title">通告通知</h3>
					</div>
					<div class="section-content"> 
						<div id="result2"></div>
						<textarea id="template2" style="display:none"> 
						<ul class="news-list">
						{#foreach $T as record} 
							<li> 
								<span class="date">{$T.record.IssueDate}</span><a href="./content_{$T.record.SerialNo}.html"  target="_blank" class="news-title">{$T.record.ContentTitle}</a>
							</li>
						{#/for} 
						</ul>
						</textarea>
					</div>
				</div>
				<!--//section--> 
			</div>
		</div>
		 
<%@include file="./plugin_links.jsp"%>
<%@include file="./plugin_footer.jsp"%>
	</body>
</html>
