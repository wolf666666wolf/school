<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>项目详细信息</title>
	</head>

	<body>
	<%@include file="./plugin_header.jsp"%>	
<script type="text/javascript">

var prdid = '<%=request.getParameter("id")%>';
$(function(){
	loadInfo();
});	 
function loadInfo()
{

	var data = 
   	{
   			PrdID:prdid
   			};
   	po('seller','getprddetail',data);
}
function afterPost(d)
{
	if(d.action=='seller'&&d.operate=='getprddetail')
	{
		if(d.rd.succ)
		{
			//alert(d.rd.data.sdpaes.length);
			var prd = d.rd.data.sdp;
			$("#PrdName").html(prd.PrdName);
			$("#PrdID").html(prd.PrdID);
			$("#Remark").html(prd.Remark);
			$("#Price").html(prd.PrdPrice);
			$("#Discount").html(prd.Discount);
			$("#MakeDate").html(prd.MakeDate);
			if(prd.R3==''||prd.R3==null)prd.R3=0;
			//$("#R3").html(prd.R3);
			$("#R4").html(prd.R4);
			$("#R5").html(prd.R5);
			$("#PrdNum").html(prd.R15);
			$("#OrderCount").html(d.rd.data.OrderCount);
			$("#AssCount").html("用户评价（"+d.rd.data.sdpaes.length+"）");
			//alert("./sellerimg/temp/"+prd.R14+".jpg?");
		    $("#prd_img").attr("src","./sellerimg/temp/"+prd.R14+".jpg?"+new Date().getMilliseconds());
			loadPath(d.rd.data.sds);
		}
	}
}

function loadPath(pc)
{	
	var pccode="";		
	var pcname="";
	var sccode=pc.ServiceType;
	var scname="";
	for(var i=0;i<chnllist.length;i++)
	{
		if(sccode==chnllist[i].SerialNo)
		{
			pccode=chnllist[i].ParentChannel;
			scname=chnllist[i].ChannelName;
		}
	}
	for(var i=0;i<chnllist.length;i++)
	{
		if(pccode==chnllist[i].SerialNo)
		{
			pcname=chnllist[i].ChannelName;
		}
	}
	var ph = "<a href=\"index.html\">首  页</a><i>></i><a>"+pcname+"</a><i>></i><a href=\"./class_2_"+sccode+".html\">"+scname+"</a><i>></i><a href=\"./seller_"+pc.SellerID+".html\">"+pc.OrgName+"</a>";
	$("#path").html(ph);
}
</script>

<div class="articlePage">
<div class="wrapper location" id="path"><a href="index.html">首  页</a><i>></i><a href="">分类信息</a><i>></i><a href="">商户信息</a></div>		
</div>
		<!--wrapper 项目详细信息开始-->
		<div class="wrapper clearfix">
			<div class="merchant-summary detail-summary clearfix">
			 
				<div class="pikachoose">
				    <img id="prd_img" src="" alt="" width="480" height="315">
				    <ul id="pikame" class="jcarousel-skin-pika">
				   
				    
				    <!-- 
				        <li><a href="#"><img src="./images/1.jpg"/></a></li>
				        <li><a href="#"><img src="./images/2.jpg"/></a></li>
				        <li><a href="#"><img src="./images/3.jpg"/></a></li>
				        <li><a href="#"><img src="./images/4.jpg"/></a></li>
				        <li><a href="#"><img src="./images/5.jpg"/></a></li>
				         -->
				    </ul>
				</div>
				<script language="javascript">
				$(document).ready(function (){
				        $("#pikame").PikaChoose({carousel:true, carouselVertical:true});
				});
				</script> 

				<!--form-box-->
				<div class="form-box">
					<div class="form-header">
						<h3 class="form-title" id="PrdName">XXXXXX</h3>
					</div>
					<ul class="form-text">
						<li class="form-item clearfix"><span class="item-title">编号</span>
							<span id="PrdID">XXXXXXXXXXXX</span>
						</li>
						<li class="form-item clearfix"><span class="item-title">价格</span>
							<span id="Price">XXXXXXXXXXXX</span>
						</li>
						<li class="form-item clearfix"><span class="item-title">折扣</span>
							<span id="Discount">90</span>
						</li>
						<li class="form-item clearfix"><span class="item-title">发布日期</span>
							<span id="MakeDate">XXXXXXXXXXXX</span>
						</li>
						<li class="form-item clearfix"><span class="item-title">目前商品数量</span>
							<span id="PrdNum">XXXXXXXXXXXX</span>
						</li>
						<li class="form-item clearfix"><span class="item-title">预订数</span>
							<span id="OrderCount">XXX</span>
						</li>
					</ul>
				</div>
				<!--//form-box-->
			</div>

			<!--section-->
			<div class="section detail-tabs">
				<div class="section-header">
					<ul class="tab-nav">
						<li class="current">商品简介</li>
						<li>详细信息</li>
						<li>服务保障</li>
						<!-- li id="AssCount">用户评价（20）</li-->
					</ul>
				</div>
				<div class="section-content">
					<div class="tab-panel" style="display: block;">
						<div class="form-box">
							<ul class="form-text">
								<li class="form-item clearfix">
									<span id="Remark"></span>
								</li>
							</ul>
						</div> 
					</div>
					<div class="tab-panel" style="display: none;">
						<div class="form-box">
							<ul class="form-text">
								<li class="form-item clearfix" id="R4">
								</li>
							</ul>
						</div> 
					</div>
					<div class="tab-panel" style="display: none;">
						<div class="form-box">
							<ul class="form-text">
								<li class="form-item clearfix" id="R5">
								</li>
							</ul>
						</div> 
					</div>
					<!-- 
					<div class="tab-panel" style="display: none;">
						<div class="form-box">
							<ul class="form-text">
								<li class="form-item clearfix"><span class="item-title">编号</span>
									<span>44444XXXXXXXXXXXX</span>
								</li>
								<li class="form-item clearfix"><span class="item-title">价格</span>
									<span>XXXXXXXXXXXX</span>
								</li>
								<li class="form-item clearfix"><span class="item-title">折扣</span>
									<span>90</span>
								</li>
								<li class="form-item clearfix"><span class="item-title">发布日期</span>
									<span>XXXXXXXXXXXX</span>
								</li>
								<li class="form-item clearfix"><span class="item-title">浏览次数</span>
									<span>XXXXXXXXXXXX</span>
								</li>
								<li class="form-item clearfix"><span class="item-title">预订数</span>
									<span>XXX</span>
								</li>
							</ul>
						</div> 
						 -->
					</div>
				</div>
			</div>
			<!--//section-->
			<script>
				$('.detail-tabs .tab-nav li').hover(function(){ 
					$(this).addClass('current').siblings('li').removeClass('current');
					$(this).parents('.detail-tabs').find('.tab-panel').hide();
					$(this).parents('.detail-tabs').find('.tab-panel').eq($(this).index()).show();
				});
			</script>
		</div>
		<!--//wrapper 项目详细信息结束-->

<%@include file="./plugin_links.jsp"%>
<%@include file="./plugin_footer.jsp"%>
	</body>

</html>