<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>商户信息</title>
	</head>
	<body>
<%@include file="./plugin_header.jsp"%>	

<script>
var sellerid = '<%=request.getParameter("id")%>';
$(function(){
	loadInfo();
	loadCode();
});	 

function loadPath(pc)
{	
	var pccode="";		
	var pcname="";
	var sccode=pc;
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
	var ph = "<a href=\"index.html\">首  页</a><i>></i><a>"+pcname+"</a><i>></i><a href=\"./class_2_"+sccode+".html\">"+scname+"</a><i>></i><a>商户信息</a>";
	$("#path").html(ph);
}
function loadCode()
{
	var qrcu = window.location.href;
	qrcu=qrcu.split("seller_")[0];
	qrcu=qrcu+"h5/view-seller.jsp?id="+sellerid;
	$("#code").qrcode({
		render: "table", //table方式
		width: 200, //宽度
		height:200, //高度
		text: qrcu //任意内容
	});	
}
function loadInfo()
{
	var UserID='';
	if(ut!='U')
		UserID='';
	else
		UserID=userjson.UserID;
	var data = 
   	{
   			SellerID:sellerid,
   			UserID:UserID
   			};
   	po('content','getseller',data);
}
function follow()
{
	if(ut!='U')
	{
		alert('请先登陆');
		window.location.href="login.html";
		return;
	}
	var data = 
   	{
   			SellerID:sellerid,
   			UserID:userjson.UserID
   			};
   	po('seller','follow',data);
}
function fav(id)
{
	if(ut!='U')
	{
		alert('请先登陆');
		window.location.href="login.html";
		return;
	}
	var data = 
   	{
   			PrdID:id,
   			UserID:userjson.UserID
   			};
   	po('product','favorite',data);
}
function sendorder()
{
	var pids = "";
	for(var i=0;i<$(".P_").length;i++)
	{
		var pid = $(".P_")[i].id;	
		if($("#"+pid)[0].checked)
		{
			var c=$("#C_"+pid.split("_")[1]).val();	
			if(!isInteger(c))
			{
				alert("请录入正确的数字");
				return;
			}
			pids+=pid.split("_")[1]+","+c+".";
		}
	}
	if(pids=='')
	{
		alert("请至少选择一项");
		return;
	}
	pids=pids.substr(0,pids.length-1);
	window.location.href="./order_"+pids+".html";	
}
function isInteger( str ){
	var regu = /^[-]{0,1}[0-9]{1,}$/;
	return regu.test(str);
	}
function afterPost(d)
{
	//alert(t(d.rd.data.sdps));
	if(d.action=='content'&&d.operate=='getseller')
	{
		if(d.rd.succ)
		{		
			//商家信息
			var s = d.rd.data.seller;
			$("#SellerID").html(s.SellerID);
			$("#SellerName2").html(s.SellerName);
			$("#OrgName").html(s.OrgName);
			$("#SellerName").html(s.ShotOrgName+"详细信息");
			$("#ShotOrgName").html(s.ShotOrgName);
			$("#sc2").html(d.rd.data.c2);
			$("#sc6").html(d.rd.data.c6);
			$("#R8").html(s.R8);
			$("#SellerLevel").html(getStar(d.rd.data.seller.R6));
			var ass="";
			if(d.rd.data.seller.R7=='Y')
				ass="待审核,";
			var as = "未认证("+ass+"无法下单)";
			if(s.AuthFlag=='Y')
			{
				as="已认证";
			}
			$("#AuthFlag").html(as);
			$("#TelPhone").html(s.TelPhone);
			$("#Address").html(s.Address);
			$("#Remark").html(s.Remark);
			//产品信息
		    // 附上模板  
		    $("#result1").setTemplateElement("template");  
		    // 给模板加载数据  
		    $("#result1").processTemplate(d.rd.data.prdlist);
		    if(s.AuthFlag!='Y')
			{
		    	$(".sendorderbtn").attr('href','javascript:alert("非认证商家无法下单");');
		    }	
		    if(d.rd.data.followlist.length>0)
		    {
				$("#btn_follow").html("取消关注");
		    }
		    else
		    {
				$("#btn_follow").html("关注商家");
		   	}
		    $("#XY").val(s.XY);
		    
		    //下架商品处理
		    for(var i=0;i<d.rd.data.prdlist.length;i++)
		    {
		   		var pjs = d.rd.data.prdlist[i];
		   		if(pjs.EffEndDate=="下架")
		   		{
			    	$("#sob_"+pjs.PrdID).attr('href','javascript:alert("商品/服务已下架，无法下单");');
		   		}
		   	}
			loadPath(s.ServiceType);

		    loadMap();
		}
		else
		{
	   		alert(d.rd.msg);		
		}
	}
	if(d.action=='seller'&&d.operate=='follow')
	{
		alert(d.rd.msg);
		if(d.rd.msg=='关注成功')
		{
			$("#btn_follow").html("取消关注");
		}
		else
		{
			$("#btn_follow").html("关注商家");
		}
	}
	if(d.action=='product'&&d.operate=='favorite')
	{
		alert(d.rd.msg);
		if(d.rd.msg=='收藏成功')
		{
			$("#sendorderbtn_"+d.data.PrdID).html("取消收藏");
		}
		else
		{
			$("#sendorderbtn_"+d.data.PrdID).html("收藏");
		}
	}
} 
</script>

<script type="text/javascript">

function showMap()
{
	showAll('#model');
}
	//兼容火狐、IE8
	function showMask(){
		$("#mask").css("height",$(document).height());
		$("#mask").css("width",$(document).width());
		$("#mask").show();
	}
	//让指定的DIV始终显示在屏幕正中间
	function letDivCenter(divName){ 
		var top = ($(window).height() - $(divName).height())/2; 
		var left = ($(window).width() - $(divName).width())/2; 
		var scrollTop = $(document).scrollTop(); 
		var scrollLeft = $(document).scrollLeft(); 
		$(divName).css( { position : 'absolute', 'top' : top + scrollTop, left : left + scrollLeft } ).show();
	}
	function showAll(divName){
		showMask();
		letDivCenter(divName);
	}
	function hideAll()
	{
		$("#mask").hide();
		$("#model").css("display","none");

	}
</script>
<style type="text/css">
	.mask {  
            position: absolute; top: 0px; filter: alpha(opacity=60); background-color: #777;
            z-index: 1002; left: 0px;
            opacity:0.5; -moz-opacity:0.5;
        }
	.model{
			position: absolute; z-index: 1003; 
     	 	width:800px; height:600px; text-align:center;
           	background-color:#fff; display: none;
		}
		#allmap
		{
		width:100%; height:95%;
		}
</style>
<div class="articlePage">
<div class="wrapper location" id="path"><a href="index.html">首  页</a><i>></i><a href=""></a><i>></i><a href="">商户信息</a></div>		
</div>
<div id="mask" class="mask"></div>
<div>
	<div id="model" class="model">
		<div id="allmap"></div>
		<input type="button" style="width:60px;height:30px" onclick="hideAll();" value=" 关  闭 "/>
	</div>
</div>	
		<div class="wrapper page-header">
			<h2 class="pape-title" id="SellerName"></h2>
		</div>
		
		<div class="wrapper clearfix">
			<div class="merchant-summary clearfix">
				<span class="pic"><img src="./sellerimg/<%=request.getParameter("id")%>.jpg" alt="" width="480" height="315"></span>
				
				<!--form-box-->
				<div class="form-box"> 
					<ul class="form-text">
						<li class="form-item clearfix"><span class="item-title">商户编号</span>
							<span id="SellerID"></span><button class="button button_80x30" onclick="follow();" id="btn_follow">关注商家</button>
							
						</li>
						<li class="form-item clearfix"><span class="item-title">商户名称</span>
							<span id="SellerName2"></span>
						</li> 
						<li class="form-item clearfix"><span class="item-title">机构全称</span>
							<span id="OrgName"></span>
						</li> 
						<li class="form-item clearfix"><span class="item-title">机构简称</span>
							<span id="ShotOrgName"></span>
						</li> 
						<li class="form-item clearfix"><span class="item-title">评价星级</span> 
							<div class="star-bar clearfix" id="SellerLevel"></div>
						</li> 
						<li class="form-item clearfix"><span class="item-title">点评数</span>
							<span id="sc6"></span>
						</li>
						<li class="form-item clearfix"><span class="item-title">预订数</span> 
							<span id="sc2"></span>
						</li>
						<li class="form-item clearfix"><span class="item-title">浏览数</span> 
							<span id="R8"></span>
						</li>
						<li class="form-item clearfix"><span class="item-title">商户类型</span> 
							<span id="AuthFlag"></span>
						</li>	
						<li class="form-item clearfix"><span class="item-title">联系电话</span> 
							<span id="TelPhone"></span>
						</li>
						<li class="form-item clearfix"><span class="item-title">地址</span>  
						<input id="XY" type="hidden"/>
							<span id="Address"></span><br/><button class="button button_80x30" onclick="showMap();">查看地图</button>
						</li>
					</ul> 
						
				</div>
				
				<div class="form-box">
					<ul class="form-text" style="width:90%">
						<li class="form-item clearfix" style="width:100%"><span class="item-title">服务机构简介</span> 
							<span id="Remark" style="width:100%"></span> 
						</li>
						<li class="form-item clearfix" style="width:100%"><span class="item-title">微信二维码<br>(扫描关注)</span> 
						<div id="code" style="width:100%"></div>
						</li>
					</ul> 
				</div>
				<!--//form-box-->
			</div>
			
			<!--section-->
			<div class="section">
				<div class="section-header">
					<h3 class="section-title">商品与服务项目信息</h3>
				</div>
				<div class="section-content">
					<div class="table-wrapper merchant-detail-table"> 
					<!-- 输出元素 -->  
					<div id="result1" ></div>
					<textarea id="template" style="display:none">  				
						<table width="100%" border="0">
						  <tr>
						    <th scope="col" class="cell-2" style="width:50px;">项目编号</th>
						    <th scope="col" class="cell-2" style="width:10px;">商品/服务名称</th>
						    <th scope="col" class="cell-3" style="width:40px;">价格</th>
						    <th scope="col" class="cell-4" style="width:40px;">折扣</th>
						    <th scope="col" class="cell-5" style="width:50px;">有效期</th>
						    <th scope="col" class="cell-6" style="width:100px;">备注</th>
						    <th scope="col" class="cell-6" style="width:40px;">选择</th>
						    <th scope="col" class="cell-6" style="width:40px;">数量</th>
						    <th scope="col" class="cell-6" style="width:40px;">操作</th>
						  </tr>			 
					{#foreach $T as record} 
						  <tr>
						    <td class="cell-2"><span>{$T.record.PrdID}</span></td>
						    <td class="cell-2"><span><a href="./detail_{$T.record.PrdID}.html">{$T.record.PrdName}</a></span></td>
						    <td class="cell-3"><span>{$T.record.PrdPrice}</span></td>
						    <td class="cell-4"><span>{$T.record.Discount}</span></td>
						    <td class="cell-5"><span>{$T.record.EffEndDate}</span></td>
						    <td class="cell-6" style="width:300px;"><span>{$T.record.Remark}</span></td>  
						    <td class="cell-6"><span><input class="P_" type="checkbox" id="P_{$T.record.PrdID}" value="{$T.record.PrdID}"/></span></td>
						    <td class="cell-6"><span><input class="C_" id="C_{$T.record.PrdID}" style="width:20px;" value="1"/></td>
						    <td class="cell-6"><span><a href="javascript:fav('{$T.record.PrdID}');" class="join-link" id="sendorderbtn_{$T.record.PrdID}">{$T.record.favbtntext}</a></span></td>
						  </tr> 
					{#/for}	
						</table>  		
					</textarea> 	
					<a href="javascript:sendorder();" class="join-link sendorderbtn">下订单</a><br />	
					</div>
				</div>
			</div>
			<!--//section-->
		</div>
		<!-- 
		
		
						    <th rowspan="2" scope="col" class="cell-8">
						    <a href="./order_{$T.record.PrdID}.html" class="join-link sendorderbtn" id="sob_{$T.record.PrdID}">下订单</a><br />
						    <a href="javascript:fav('{$T.record.PrdID}');" class="join-link" id="sendorderbtn_{$T.record.PrdID}">{$T.record.favbtntext}</a>
						    </th>
		 -->
<%@include file="./plugin_links.jsp"%>
<%@include file="./plugin_footer.jsp"%>
	</body>
</html>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.4&ak=您的密钥"></script>
<script type="text/javascript">

function loadMap()
{

	map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
	map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用        
	//map.centerAndZoom("北京",14); 
		
	//单击获取点击的经纬度
	map.addEventListener("click",function(e){
		//alert(e.point.lng + "," + e.point.lat);
		//marker.remove();
		//marker = new BMap.Marker(new BMap.Point(e.point.lng,e.point.lat));
		//map.addOverlay(marker);
		//$("#XY").val(e.point.lng+"|"+e.point.lat);
	});
	var marker=new BMap.Marker(new BMap.Point($("#XY").val().split("|")[0],$("#XY").val().split("|")[1]));
	map.addOverlay(marker);
	map.addControl(new BMap.NavigationControl());
	var point = new BMap.Point(116.319937,40.015507);
	map.centerAndZoom(point, 16);   
}

	// 百度地图API功能
	var map = new BMap.Map("allmap"); 
</script>