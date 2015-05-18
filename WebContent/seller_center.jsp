<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>商家中心</title>
	</head>
	<body>	
	<%@include file="./plugin_header.jsp"%>
	<%@include file="./userchkS.jsp"%>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.4&ak=W6Z6DLmpMykxD13CBbUF7Fb5"></script>
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
		width:100%; height:90%;
		}
</style>
<script>

$(function(){
	loadInfo();		
	$("#AuthFlag").val('<%=sds.getAuthFlag()%>');
});
function loadInfo()
{
	var data = 
   	{
			SellerID:userjson.SellerID
   			};
   	po('seller','sellercenter',data);	
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
function showMap()
{
	showAll('#model');
}
function regmodify()
{
	   var SellerName=$("#SellerName").val();
	   if(SellerName=='')
		   {
		   alert("请输入用户名");
		   return;
		   }
	   var Password=$("#Password").val();

	   if(Password=='')
		   {
		   alert("请输入密码");
		   return;
		   }
	   var Password1=$("#Password1").val();
	   if(Password!=Password1)
		{
		alert('两次输入密码不一致');
		return;
		   }
	   var OrgName=$("#OrgName").val();
	   var ShotOrgName=$("#ShotOrgName").val();
	   var Email=$("#Email").val();
	   if(Email=='')
		  {
		 	alert("请填写电子邮箱");
		 	return;
		  }
	   var Phone=$("#Phone").val();
	   if(Phone=='')
		  {
		 	alert("请填写手机");
		 	return;
		  }
	   var XY=$("#XY").val();
	   if(XY=='')
		  {
		 	alert("请选择位置");
		 	return;
		  }
	   var AuthFlag =  $("#AuthFlag").find("option:selected").val();
	   if(AuthFlag=='')
		{
		alert('请选择是否认证');
		return;
	   }
	   var ServiceType =  $("#ServiceType").find("option:selected").val();
	   var R5 =  $("#R5").find("option:selected").val();
	   
	   var Address=$("#Address").val();
	   var ServiceFeature=$("#ServiceFeature").val();
	   var OrgPeople=$("#OrgPeople").val();
	   var TelPhone=$("#TelPhone").val();
	   var WebPage=$("#WebPage").val();
	   var LinkPerson=$("#LinkPerson").val();
	   var FaxNo=$("#FaxNo").val();
	   var RidingRoute=$("#RidingRoute").val();
	   var Remark=$("#Remark").val();
	   var OrgBrief=$("#OrgBrief").val();

	var data = 
	{
			SellerID:userjson.SellerID,
			Password:Password,
			SellerName:SellerName,
			OrgName:OrgName,
			Email:Email,
			Phone:Phone,
			AuthFlag:'N',
			R7:AuthFlag,
			XY:XY,
			ShotOrgName:ShotOrgName,
			FoundDate:'2010-10-10',
			ServiceType:ServiceType,
			Address:Address,
			ServiceFeature:ServiceFeature,
			OrgPeople:OrgPeople,
			TelPhone:TelPhone,
			WebPage:WebPage,
			LinkPerson:LinkPerson,
			FaxNo:FaxNo,
			RidingRoute:RidingRoute,
			Remark:Remark,
			OrgBrief:OrgBrief,
			R5:R5,
			imgurl:imgurl
			};
	po('seller','regmodify',data);

}
function afterPost(d)
{
	if(d.action=='seller'&&d.operate=='sellercenter')
	{
		if(d.rd.succ)
		{
			$("#sc1").html(d.rd.data.c1+"");
			$("#sc2").html(d.rd.data.c2+"次");
			$("#sc3").html(d.rd.data.c3+"次");
			$("#sc4").html(d.rd.data.c4+"次");
			$("#sc5").html(d.rd.data.c5+"次");
			$("#sc6").html(d.rd.data.c6+"次");
		}
		$("#SellerLevel").html(getStar(<%=sds.getR6()%>));
	}
   	if(d.action=='seller'&&d.operate=='regmodify')
   	{
   		alert(d.rd.msg);
   	}
}


//图片上传
var imgurl="";
function submits2()
{
	form2.action="upload.jsp";
	form2.submit();
}
function afterUpload(a,b,c)
{
	alert(b);
	if(a=='Succ')
	{
		$("#seller_img").attr("src","./sellerimg/temp/"+c+".jpg?"+new Date().getMilliseconds());
		imgurl=c;
	}
		//alert(a+"|"+b+"|"+c);
}
</script>		
	
<div id="mask" class="mask"></div>
<div>
	<div id="model" class="model">
		地图上选择位置，之后点击确认按钮。
		<div id="allmap"></div>
		<input type="button" style="width:60px;height:30px" onclick="hideAll();" value=" 确  认 "/>
	</div>
</div>	
		<!--wrapper-->
		<div class="wrapper clearfix">
			<div class="side-bar">
				<div class="side-nav">
					<a href="seller_center.html" class="current">商家中心</a>
					<a href="seller_productlist.html">产品发布</a>
					<a href="seller_orderlist.html">订单管理</a>
					<a href="seller_assess.html">评价管理</a>
					<a href="seller_content.html">内容发布</a>
					<a href="seller_center2.html">资料修改</a>
				</div>
			</div>
			<div class="main">
				<div class="location clearfix">
					<div class="links"><a href="index.html">首  页</a><i class="icon">/</i><a href="javascript:logout();">退  出</a></div>
					<div class="time-bar"><span>今天是<%=PubFun.getCurrentDateZh() %></span><span>您好，<%=sds.getShotOrgName() %></span></div>
				</div> 
			 
				<!--form-box-->
				<div class="form-box"> 
					<img id="seller_img2" src="./sellerimg/<%=sds.getSellerID() %>.jpg?<%=System.currentTimeMillis() %>" alt="" width="480" height="315">				
					<ul class="form-text">
						<li class="form-item clearfix"><span class="item-title">上次登录时间：</span><span><%=sds.getLastLoginDate()%></span></li>
					</ul>
					<ul class="form-text">
						<li class="form-item clearfix"><span class="item-title">是否认证：</span><span id="AuthFlag2"><%
						if("N".equals(sds.getAuthFlag()))
							out.print("否");
						else
							out.print("是");
						if("Y".equals(sds.getR7()))
							out.print("(认证申请已提交，请耐心等待)");
						
						 %></span>
						</li>
						<li class="form-item clearfix"><span class="item-title">类型：</span><span id="sc1"><%=sds.getServiceType() %></span>
						</li>
						<li class="form-item clearfix"><span class="item-title">二级分类：</span><span><%=sds.getR5() %></span>
						</li>
						<li class="form-item clearfix">
							<span class="item-title">商户等级：</span>
							<div class="star-bar clearfix" id="SellerLevel">
							</div>
						</li>
					</ul>
					<ul class="form-text">
						<li class="form-item clearfix"><span class="item-title">订单数：</span><span id="sc2">xx次</span>
						</li>
						<li class="form-item clearfix"><span class="item-title">完成订单：</span><span id="sc3">xx次</span>
						</li>
						<li class="form-item clearfix"><span class="item-title">未处理订单：</span><span id="sc4">xx次</span>
						</li>
					</ul>
					<ul class="form-text">
						<li class="form-item clearfix"><span class="item-title">信息发布：</span><span id="sc5">xx次</span>
						</li>
						<li class="form-item clearfix"><span class="item-title">我的评价：</span><span id="sc6">xx次</span>
						</li>
					</ul>
				</div>
				<!--//form-box-->
				 
			<!--form-box-->
			<div class="form-box" style="display:none"> 
				<ul class="form-text">
					<li class="form-item clearfix"><span class="item-title">用 户 名<i class="icon">*</i></span>
						<input type="text" id="SellerName" class="form-input text-w210" value="<%=sds.getSellerName()%>"><span class="tip">请输入4-10个字母、数字</span>
					</li>
					<li class="form-item clearfix"><span class="item-title">密　　 码<i class="icon">*</i></span>
						<input type="password" id="Password" class="form-input text-w210" value="<%=sds.getPassword()%>"><span class="tip">字母、数字的组合，最少6位</span>
					</li>
					<li class="form-item clearfix"><span class="item-title">确 认 密 码<i class="icon">*</i></span>
						<input type="password" id="Password1" class="form-input text-w210" value="<%=sds.getPassword()%>"><span class="tip">重新输入一遍密码</span>
					</li>
					<li class="form-item clearfix"><span class="item-title">机 构 全 称</span>
						<input type="text" id="OrgName" class="form-input text-w210" value="<%=sds.getOrgName()%>"><span class="tip">请与营业执照登记名称一致，不可重复注册</span>
					</li> 
					<li class="form-item clearfix"><span class="item-title">是否认证<i class="icon">*</i></span>
						<select id="AuthFlag" class="text-w210">
							<option value="Y">是</option>
							<option value="N">否</option>
						</select><span class="tip"></span>
					</li>
					<li class="form-item clearfix"><span class="item-title">电 子 邮 箱<i class="icon">*</i></span>
						<input type="text" id="Email" class="form-input text-w210" value="<%=sds.getEmail()%>"><span class="tip">请填有效邮箱，将用于确认链接</span>
					</li>
					<li class="form-item clearfix"><span class="item-title">坐标<i class="icon">*</i></span>
						<input type="text" id="XY" class="form-input text-w210" value="<%=sds.getXY()%>" readonly><button class="button button_80x30" onclick="showMap();">获得坐标</button>
					</li>
					<li class="form-item clearfix"><span class="item-title">机构简称</span>
						<input type="text" id="ShotOrgName" class="form-input text-w210" value="<%=sds.getShotOrgName()%>"><span class="tip">省、地区+简称，4 - 8个汉字，如</span>
					</li> 
					<li class="form-item clearfix"><span class="item-title">成立日期 <i class="icon">*</i></span>
						<div class="time-bar">
							<select name="" id="" class="text-w70">
								<option value="2010">2010</option>
								<option value="2010">2010</option>
								<option value="2010">2010</option>
							</select>
							<span>年</span>
							<select name="" id="" class="text-w60">
								<option value="01">01</option>
								<option value="02">02</option>
								<option value="03">03</option>
							</select>
							<span>月</span>
							<select name="" id="" class="text-w60">
								<option value="01">01</option>
								<option value="02">02</option>
								<option value="03">03</option>
							</select>
							<span>日</span> 
						</div>
					</li>
					<li class="form-item clearfix">
						<span class="item-title">机构地址</span>
						<input type="text" id="Address"  value="<%=sds.getAddress()%>" class="form-input text-w210">
						<span class="tip">用于邮寄，准确填写</span>
					</li>
					<li class="form-item clearfix">
						<span class="item-title">服务特色</span>
						<input type="text" id="ServiceFeature" class="form-input text-w210" value="<%=sds.getServiceFeature()%>">
					</li>
					<li class="form-item clearfix">
						<span class="item-title">机构人数</span>
						<input type="text" id="OrgPeople" class="form-input text-w210" value="<%=sds.getOrgPeople()%>">
						<span class="tip">工作人员人数 </span>
					</li>
					<li class="form-item clearfix">
						<span class="item-title">机构照片</span>						
					 	<form action='' name="form2" method='post'	enctype='multipart/form-data' target="img_frame">
						<input type='file' name='upfile' id='upfile' >
						<button class="button button_80x30" onclick="submits2()">上传照片</button>
						</form>
						<iframe style="display: none;" name="img_frame" id="productpic"></iframe>
						<span class="tip">形象图片，大门、门面、前台（Logo标志为佳，480*315像素）</span><br />
						<img id="seller_img" src="./sellerimg/<%=sds.getSellerID()%>.jpg?<%=System.currentTimeMillis() %>" alt="" width="480" height="315">
					</li>
					<li class="form-item clearfix">
						<span class="item-title">咨询电话</span>
						<input type="text"  id="TelPhone" class="form-input text-w210" value="<%=sds.getTelPhone()%>">
					</li>
					<li class="form-item clearfix">
						<span class="item-title">网址</span>
						<input type="text" id="WebPage" class="form-input text-w210" value="<%=sds.getWebPage()%>"> 
					</li>
					<li class="form-item clearfix">
						<span class="item-title">注册联系人</span>
						<input type="text" id="LinkPerson" class="form-input text-w210" value="<%=sds.getLinkPerson()%>">
						<span class="tip">不公开，用来与平台联络</span>
					</li>
					<li class="form-item clearfix">
						<span class="item-title">联系人手机</span>
						<input type="text" id="Phone" class="form-input text-w210" value="<%=sds.getPhone()%>" readonly> 
						<span class="tip">不可修改</span>
					</li>
					<li class="form-item clearfix">
						<span class="item-title">服务机构简介</span>
						<textarea id="OrgBrief" class="text-w210" value="<%=sds.getOrgBrief()%>"></textarea><span class="tip">限150字</span>
					</li>
					<li class="form-item clearfix">
						<span class="item-title">传真</span>
						<input type="text" id="FaxNo" class="form-input text-w210" value="<%=sds.getFaxNo()%>"> 
					</li> 
					<li class="form-item clearfix">
						<span class="item-title">乘车路线</span>
						<textarea id="RidingRoute" class="text-w210" value="<%=sds.getRidingRoute()%>"></textarea><span class="tip">公交、地铁</span>
					</li>
					<li class="form-item clearfix">
						<span class="item-title">其他说明</span> 
						<textarea id="Remark" class="text-w210" value="<%=sds.getRemark()%>"></textarea>
					</li>
				</ul>   
				
				<div class="button-box"> 
					<input type="button" value="修改信息" onclick="regmodify();" class="submit-button">
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

<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("allmap"); 
	var marker=new BMap.Marker(new BMap.Point(116.319937,40.015507));;
	map.addControl(new BMap.NavigationControl());
	var point = new BMap.Point(116.319937,40.015507);
	map.centerAndZoom(point, 16);   
	map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
	map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用        
	//map.centerAndZoom("北京",14); 
		
	//单击获取点击的经纬度
	map.addEventListener("click",function(e){
		//alert(e.point.lng + "," + e.point.lat);
		marker.remove();
		marker = new BMap.Marker(new BMap.Point(e.point.lng,e.point.lat));
		map.addOverlay(marker);
		$("#XY").val(e.point.lng+"|"+e.point.lat);
	});
</script>