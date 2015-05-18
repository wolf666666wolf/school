<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>首页--清华大学后勤综合服务平台</title>
	</head>
	<body class="homePage">
<%@include file="./plugin_header.jsp"%>
<script type="text/javascript">
$(function(){
	loadInfo();		
});
function loadInfo()
{
   	po('index','getindex',{});
}
function afterPost(d)
{
    $("#result1").setTemplateElement("template1");
    $("#result1").processTemplate(d.rd.data.result1);
    $("#result2").setTemplateElement("template2");
    $("#result2").processTemplate(d.rd.data.result2);
    $("#result3").setTemplateElement("template3");
    $("#result3").processTemplate(result3);
    $("#result4").setTemplateElement("template4");
    $("#result4").processTemplate(d.rd.data.result4);
    $("#result5").setTemplateElement("template5");
    $("#result5").processTemplate(result5);
    $("#result6").setTemplateElement("template6");
    $("#result6").processTemplate(result6);
    $("#result7").setTemplateElement("template7");
    $("#result7").processTemplate(d.rd.data.result7);
    $("#result8").setTemplateElement("template8");
    $("#result8").processTemplate(d.rd.data.result8);
    $("#result9").setTemplateElement("template9");
    $("#result9").processTemplate(d.rd.data.result9);
}
</script>
		
		<div class="wrapper clearfix">
			<div class="aside">
				<!--section-->
				<div class="section news-broadcast-section">
					<div class="section-header">
						<a href="./class_3_C0041.html" class="pic more"><img src="./images/more.png"/></a>
						<h3 class="section-title">新闻播报</h3>
					</div>
					<div class="section-content">
											
						<!-- 
						<a href="#" class="pic">
							<img src="./images/photo.jpg" alt="" width="340" height="190">
						</a> 输出元素 -->  
						<div id="result1"></div>
						<textarea id="template1" style="display:none"> 
						<ul class="news-list">
							{#foreach $T as record} 
							<li><span class="date">{$T.record.IssueDate}</span><a href="content_{$T.record.SerialNo}.html">{$T.record.ContentTitle}</a></li>
							{#/for}  
						</ul>			
						</textarea> 
					</div>
				</div>
				<!--//section--> 
				
				<!--section-->
				<div class="section hot-services-section">
					<div class="section-header">
						<a href="./hotservice.html" class="pic more"><img src="./images/more.png"/></a>
						<h3 class="section-title">最热服务</h3>
					</div>
					<div class="section-content"> 						
						<!-- 输出元素 -->  
						<div id="result2"></div>
						<textarea id="template2" style="display:none"> 
						<ul class="news-list">
							{#foreach $T as record} 
							<li class="item-1">
								<a href="{$T.record.url}" class="news-title">[{$T.record.R5}]{$T.record.title}</a>
							</li>
							{#/for} 
						</ul>
						</textarea> 
					</div>
				</div>
				<!--//section--> 
				
				<!--section-->
				<div class="section">
					<div class="section-header">
						<a href="#" class="pic more"></a>
						<h3 class="section-title">办事指南</h3>
					</div>
					<div class="section-content clearfix">
						<span class="pic fl-pic"><img src="./images/t_zwfw.jpg" width="239" height="190"/></span> 							
						<!-- 输出元素 -->  
						<div id="result3"></div>
						<textarea id="template3" style="display:none"> 
						<ul class="subpage-links">
							{#foreach $T as record} 
			            	<li><a href="{$T.record.URL}">{$T.record.ChannelName}</a></li>
							{#/for} 
			            </ul>
						</textarea> 
					</div>
				</div>
				<!--//section--> 
			</div>
			<div class="main">
				<!--section-->
				<div class="section">
					<div class="section-header">
						<a href="./class_3_C0042.html" class="pic more"><img src="./images/more.png"/></a>
						<h3 class="section-title">通知公告</h3>
					</div>
					<div class="section-content">						
						<!-- 输出元素 -->  
						<div id="result4"></div>
						<textarea id="template4" style="display:none"> 
						 <ul class="news-list">
							{#foreach $T as record} 
						 	<li>
						 		<span class="date">{$T.record.IssueDate}</span>
						 		<a href="content_{$T.record.SerialNo}.html" class="news-title">{$T.record.ContentTitle}</a>
						 	</li>						 	
							{#/for} 
						 </ul>
						</textarea> 
					</div>
				</div>
				<!--//section--> 
				
				<!--section-->
				<div class="section old-service-section">
					<div class="section-header">
						<a href="#" class="pic more"></a>
						<h3 class="section-title">为老服务</h3>
					</div>
					<div class="section-content clearfix">
						<span class="pic fl-pic"><img src="./images/t_wlfw.jpg" width="270" height="183"/></span>							
						<!-- 输出元素 -->  
						<div id="result5"></div> 
						<textarea id="template5" style="display:none"> 
						<ul class="subpage-links">
							{#foreach $T as record} 
			            	<li><a href="{$T.record.URL}">{$T.record.ChannelName}</a></li>
							{#/for} 	 
						</ul>
						</textarea>						
						<br /><br />
						<ul class="subpage-links">
							<li><a href="#"><img src="./images/q_lndx.png"/>老年大学 </a></li>
							<li><a href="#"><img src="./images/q_jkfw.png"/>健康服务</a></li>  
						</ul>
					</div>
				</div>
				<!--//section--> 
				
				<!--section-->
				<div class="section convenience-services-section">
					<div class="section-header">
						<a href="#" class="pic more"></a>
						<h3 class="section-title">便民服务</h3>
					</div>
					<div class="section-content clearfix">
						<span class="pic fl-pic"><img src="./images/t_bmfw.jpg" width="282" height="190"/></span> 	
						<!-- 输出元素 -->  
						<div id="result6"></div>
						<textarea id="template6" style="display:none"> 
						<ul class="subpage-links">
							{#foreach $T as record} 
			            	<li><a href="{$T.record.URL}">{$T.record.ChannelName}</a></li>
							{#/for} 	 
						</ul>
						</textarea>	
					</div>
				</div>
				<!--//section--> 
			</div>
		</div>
		
		<div class="wrapper section-wrapper clearfix">
			<div class="section section-item1">
				<div class="section-header">
					<a href="./class_3_C0031.html" class="pic more"><img src="./images/more.png"/></a>
					<h3 class="section-title">商品促销</h3>
				</div>
				<div class="section-content">					
						<!-- 输出元素 -->  
						<div id="result7"></div>
						<textarea id="template7" style="display:none"> 
					<ul class="news-list">
							{#foreach $T as record} 
						<li><span class="date">{$T.record.IssueDate}</span><a href="./content_{$T.record.SerialNo}.html">{$T.record.ContentTitle}</a></li>
							{#/for} 	
					</ul>
						</textarea>	
				</div>
			</div>
			<div class="section section-item2">
				<div class="section-header">
					<a href="./class_3_C0033.html" class="pic more"><img src="./images/more.png"/></a>
					<h3 class="section-title">邻里推荐</h3>
				</div>
				<div class="section-content">				
						<!-- 输出元素 -->  
						<div id="result8"></div>
						<textarea id="template8" style="display:none"> 
					<ul class="news-list">
							{#foreach $T as record} 
						<li><span class="date">{$T.record.IssueDate}</span><a href="./content_{$T.record.SerialNo}.html">{$T.record.ContentTitle}</a></li>
							{#/for} 	
					</ul>
						</textarea>	
				</div>
			</div>
			<div class="section section-item3">
				<div class="section-header">
					<a href="./act.html?C0030" class="pic more"><img src="./images/more.png"/></a>
					<h3 class="section-title">最新活动</h3>
				</div>
				<div class="section-content">				
						<!-- 输出元素 -->  
						<div id="result9"></div>
						<textarea id="template9" style="display:none"> 
					<ul class="news-list">
							{#foreach $T as record} 
						<li><span class="date">{$T.record.R5}</span><a href="./actmain_{$T.record.ActID}.html">{$T.record.Title}</a></li>
							{#/for} 	
					</ul>
						</textarea>	
				</div>
			</div>
		</div>
		<%@include file="./plugin_links.jsp"%>
		<%@include file="./plugin_footer.jsp"%>
		
	</body>
</html>
