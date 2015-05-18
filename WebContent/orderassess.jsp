<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>我有话说</title>
	</head>
	<body>
<%@include file="./plugin_header.jsp"%>
<%@include file="./userchkU.jsp"%>		
<script>
var OrderID='<%=request.getParameter("id")%>';

$(function(){
	loadOrderInfo();
	$("#SellerLevel").html(getStarAss(1));
});
var sellerlevel=0;
function setStar(i)
{
	sellerlevel=i+1;
	$("#SellerLevel").html(getStarAss(sellerlevel));

}
function loadOrderInfo()
{
	var data = 
   	{
			OrderID:OrderID
   			};
   	po('order','getorderinfo',data);
}
function sub()
{
	var Phone = $("#Phone").val();
	var Content =  $("#Content").val();
	if(Phone=='')
	{
		alert("请填写电话");
		return;
	}
	if(Content=='')
	{
		alert("请填写留言");
		return;
	}
	var data = 
   	{
			OrderID:OrderID,
			Phone:Phone,
			Content:Content,
			UserID:userjson.UserID,
			Level:sellerlevel
   			};
   	po('product','assess',data);
}
function afterPost(d)
{
	if(d.action=='order'&&d.operate=='getorderinfo')
	{
		$("#SellerID").html(d.rd.data.sellerinfo.SellerID);
		$("#SellerName").html(d.rd.data.sellerinfo.SellerName);
		$("#ServiceType").html(d.rd.data.sellerinfo.ServiceType);
		$("#OrderID").html(OrderID);
	}
	if(d.action=='product'&&d.operate=='assess')
	{
		alert(d.rd.msg);
		if(d.rd.succ)
		{
			window.location.href="user_orderlist.html";	
		}
	}
}
</script> 
		<div class="wrapper page-header">
			<h2 class="pape-title">我有话说</h2>
		</div>
		
		<!--wrapper-->
		<div class="wrapper clearfix">  
			<!--form-box-->
			<div class="form-box"> 
				<ul class="form-text">
					<li class="form-item clearfix"><span class="item-title">商户编号</span>
						<span id="SellerID"></span>
					</li>
					<li class="form-item clearfix"><span class="item-title">商户名称</span>
						<span id="SellerName"></span>
					</li> 
					<li class="form-item clearfix"><span class="item-title">评价星级</span> 
						<div class="star-bar clearfix" id="SellerLevel"><span></span><span></span><span></span><span></span><span></span></div>
						<span class="tip">滑动鼠标来评星</span>
					</li> 
					<li class="form-item clearfix"><span class="item-title">商户类型</span>
						<span id="ServiceType"></span>
					</li>
					<li class="form-item clearfix"><span class="item-title">我的电话</span> 
						<input type="text" class="form-input text-w210" id="Phone">					
					</li>
					<li class="form-item clearfix"><span class="item-title">留言</span>
						<textarea id="Content" class="text-w210"></textarea>
						 <span class="tip">大众不可见。</span>
					</li>
					<li class="form-item clearfix"><span class="item-title">我的订单</span>
						<span id="OrderID"></span>
					</li> 
				</ul>
				
				<div class="button-box"> 
					<input type="button" value="发给商家" class="submit-button" onclick="sub();">
				</div>
			</div>
			<!--//form-box--> 
		</div>
		<!--//wrapper-->
<%@include file="./plugin_links.jsp"%>
<%@include file="./plugin_footer.jsp"%>
	</body>
</html>
