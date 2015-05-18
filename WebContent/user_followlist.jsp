<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>我的关注</title>
	</head>
	<body>
<%@include file="./plugin_header.jsp"%>
<%@include file="./userchkU.jsp"%>	
<script>
$(function(){
	loadList();
});	
function loadList()
{
	var data = 
   	{
   			UserID:userjson.UserID
   			};
   	po('user','getfollowlist',data);	
}
function afterPost(d)
{
	if(d.action=='user'&&d.operate=='getfollowlist')
	{
		if(d.rd.succ)
		{
			 // 附上模板  
		    $("#result1").setTemplateElement("template");  
		    // 给模板加载数据  
		    $("#result1").processTemplate(d.rd.data.followlist);
		    for(var i=0;i<d.rd.data.followlist.length;i++)
		    {
		    	var s = d.rd.data.followlist[i];
		   		$("#star_div_"+s.SellerID).html(getStar(s.R6)); 	
		   	}
		}
		
	}
}
</script>	
		<div class="wrapper clearfix">
			<div class="side-bar">
				<div class="side-nav">
					<a href="user_center.html">我的中心</a>
					<a href="user_orderlist.html">订单信息</a>
					<a href="user_content.html">我的发言</a>
					<a href="user_actlist.html">我的活动</a>
					<a href="user_favoritelist.html">我的收藏</a>
					<a href="user_followlist.html" class="current">我的关注</a> 
				</div>
			</div>
			<div class="main">
			
			<div id="result1" ></div>		
			</div>
				<textarea id="template" style="display:none"> 
				{#foreach $T as record}   
				<ul class="merchant-summary clearfix"> 
					<li class="merchant-item clearfix">
						<span class="pic"><img src="./sellerimg/{$T.record.SellerID}.jpg" alt="" width="344" height="225"></span>
						<!--form-box-->
						<div class="form-box"> 
							<a href="seller_{$T.record.SellerID}.html"><h3 class="form-title">{$T.record.ShotOrgName}</h3></a>
							<ul class="form-text"> 
								<li class="form-item clearfix"><span class="item-title">评价星级</span> 
									<div class="star-bar clearfix" id="star_div_{$T.record.SellerID}"></div>
								</li>  
								<li class="form-item clearfix"><span class="item-title">联系电话</span> 
									<span>{$T.record.TelPhone}</span>
								</li>
								<li class="form-item clearfix"><span class="item-title">地址</span>  
									<span>{$T.record.Address}</span>
								</li> 
							</ul> 
						</div>
						<!--//form-box--> 
					</li>
				</ul> 
					{#/for} 
					</textarea>
			</div>
		</div>
<%@include file="./plugin_links.jsp"%>
<%@include file="./plugin_footer.jsp"%>
	</body>
</html>
