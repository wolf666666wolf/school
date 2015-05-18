<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>最热服务</title>
	</head>
	<body>
<%@include file="./plugin_header.jsp"%>
<script type="text/javascript">
$(function(){
	loadList();
});
function loadList()
{
	var data = 
   	{
   			};
   	po('content','gethotsellerlist',data);
}

function afterPost(d)
{
	//alert(t(d.rd.data.sdps));
	if(d.action=='content'&&d.operate=='gethotsellerlist')
	{
		if(d.rd.succ)
		{					 
		    // 附上模板  
		    $("#result1").setTemplateElement("template");  
		    // 给模板加载数据  
		    $("#result1").processTemplate(d.rd.data.sellerlist);
		}
		else
		{
	   		alert(d.rd.msg);		
		}
	}
} 
</script>			
		<!--wrapper-->
		<div class="wrapper clearfix">
			<div class="side-bar">
				<div class="side-nav">
				<a class="current">最热服务</a>
				</div>
			</div>
			<!-- 输出元素 -->  
			<div id="result1" ></div>	
			<textarea id="template" style="display:none">  			 
			<div class="main">
				<ul class="services-list clearfix">
					{#foreach $T as record} 				
					<li class="services-item">
						<a href="./seller_{$T.record.SellerID}.html" class="pic"><img src="./sellerimg/{$T.record.SellerID}.jpg" alt="" width="246" height="208"/><i class="marsk">点击查看</i></a> 
						{#if $T.record.AuthFlag=='Y'} <i class="certification-icon"></i>{#/if}
						
						<div class="store-info">
						<!-- 
							<div class="button-box"><a href="./assess_{$T.record.SellerID}.html">我有话说</a></div>
							 -->
							<ul class="store-text">
								<li class="store-name"><a href="./seller_{$T.record.SellerID}.html">[{$T.record.R5}]{$T.record.ShotOrgName}</a></li>
								<li class="store-detail">{$T.record.ContentTitle}</li>
							</ul>
						</div>
						<ul class="services-info"> 
							<li class="store-name"><span>{$T.record.R4}</span><span>已预订</span></li>
							<li class="store-name"><span></span><span></span></li>
							<li class="store-name"><span>{$T.record.R7}</span><span>已评价</span></li>
						</ul>
					</li>
					{#/for}					
				</ul>
			</div>
			</textarea> 		
			</div>
		</div>
		<!--//wrapper-->
		
<%@include file="./plugin_links.jsp"%>
<%@include file="./plugin_footer.jsp"%>
	</body>
</html>
