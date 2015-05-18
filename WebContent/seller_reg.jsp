<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>商户注册</title>
	</head>
	<body>
	<%@include file="./plugin_header.jsp"%>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.4&ak=W6Z6DLmpMykxD13CBbUF7Fb5"></script>
<script>
$(function(){
	loadClassName();
	$("#FoundDate").datepicker({dateFormat:'yy-mm-dd',changeYear: true,changeMonth: true});
});
function loadClassName()
{
   	po('seller','loadclassname',{});
}
var classlist;
function loadSubClass()
{
	var cno = $("#ServiceType").find("option:selected").val();
	$("#R5").html("");
	for(var i=0;i<classlist.length;i++)
	{
		if(classlist[i].ChannelCode==cno)
		{
			$("#R5").append("<option value='"+classlist[i].ClassName+"'>"+classlist[i].ClassName+"</option>");
		}
	}
}
function loadSelect()
{
	var selectArr=new Array();
	for(var i=0;i<chnllist.length;i++)
	{
		if(chnllist[i].R3=='2')
		{
			selectArr[selectArr.length]=chnllist[i];			
		}
	}
	for(var i=0;i<selectArr.length;i++)
	{
		for(var j=0;j<chnllist.length;j++)
		{
			if(chnllist[j].SerialNo==selectArr[i].ParentChannel)
			{
				$("#ServiceType").append("<option value='"+selectArr[i].SerialNo+"'>"+chnllist[j].ChannelName+"-"+selectArr[i].ChannelName+"</option>");
			}
		}
	}
	loadSubClass();	
}
function showMap()
{
	showAll('#model');
}
</script>
<script type="text/javascript">
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
		width:100%; height:90%;
		}
</style>	
<div id="mask" class="mask"></div>
<div>
	<div id="model" class="model">
		地图上选择位置，之后点击确认按钮。
		<div id="allmap"></div>
		<input type="button" style="width:60px;height:30px" onclick="hideAll();" value=" 确  认 "/>
	</div>
</div>	
		<!--sub-nav-->
		<div class="sub-nav clearfix">
			<p>已有账号，直接  <a href="login.html">登录</a></p>
		</div>
		<!--//sub-nav--> 
		 
		<div class="wrapper page-header">
			<h2 class="pape-title">商户注册信息</h2>
		</div>
		
		<!--wrapper-->
		<div class="wrapper clearfix">  
			<!--form-box-->
			<div class="form-box"> 
				<ul class="form-text">
					<li class="form-item clearfix"><span class="item-title">用户名<i class="icon">*</i></span>
						<input type="text" id="SellerName" class="form-input text-w210"><span class="tip">请输入4-10个字母、数字</span>
					</li>
					<li class="form-item clearfix"><span class="item-title">密码<i class="icon">*</i></span>
						<input type="password" id="Password" class="form-input text-w210"><span class="tip">字母、数字的组合，最少6位</span>
					</li>
					<li class="form-item clearfix"><span class="item-title">确认密码<i class="icon">*</i></span>
						<input type="password" id="Password1" class="form-input text-w210"><span class="tip">重新输入一遍密码</span>
					</li>
					<li class="form-item clearfix"><span class="item-title">机构全称</span>
						<input type="text" id="OrgName" class="form-input text-w210"><span class="tip">请与营业执照登记名称一致，不可重复注册</span>
					</li> 
					<li class="form-item clearfix"><span class="item-title">是否认证<i class="icon">*</i></span>
						<select id="AuthFlag" class="text-w210">
							<option value="Y">是</option>
							<option value="N">否</option>
						</select><span class="tip">不认证，则用户不能对商户下订单，只能展示产品和服务。</span>
					</li>
					<li class="form-item clearfix"><span class="item-title">电子邮箱<i class="icon">*</i></span>
						<input type="text" id="Email" class="form-input text-w210"><span class="tip"></span>
					</li>
					<li class="form-item clearfix"><span class="item-title">坐标<i class="icon">*</i></span>
						<input type="text" id="XY" class="form-input text-w210" readonly><button class="button button_80x30" onclick="showMap();">获得坐标</button>
					</li>
					<li class="form-item clearfix"><span class="item-title">机构简称<i class="icon">*</i></span>
						<input type="text" id="ShotOrgName" class="form-input text-w210"><span class="tip">用于对外显示</span>
					</li> 
					<li class="form-item clearfix"><span class="item-title">成立日期 <i class="icon">*</i></span>
						<input type="text" id="FoundDate" class="form-input text-w210" readonly>
					</li>
					<li class="form-item clearfix"><span class="item-title">服务类别</span>
						<select id="ServiceType" class="text-w210" onchange="loadSubClass();";>
						</select>
					</li>
					<li class="form-item clearfix"><span class="item-title">二级分类</span>
						<select id="R5" class="text-w210">
						</select>
					</li>
					<li class="form-item clearfix">
						<span class="item-title">机构地址</span>
						<input type="text" id="Address" class="form-input text-w210">
						<span class="tip">用于邮寄，准确填写</span>
					</li>
					<li class="form-item clearfix">
						<span class="item-title">服务特色</span>
						<input type="text" id="ServiceFeature" class="form-input text-w210">
					</li>
					<li class="form-item clearfix">
						<span class="item-title">机构人数</span>
						<input type="text" id="OrgPeople" class="form-input text-w210">
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
						<img id="seller_img" src="" alt="" width="480" height="315">
					</li>
					<li class="form-item clearfix">
						<span class="item-title">咨询电话<i class="icon">*</i></span>
						<input type="text"  id="TelPhone" class="form-input text-w210">
						<span class="tip">手机号码或固定电话号码均可</span>						
					</li>
					<li class="form-item clearfix">
						<span class="item-title">网址</span>
						<input type="text" id="WebPage" class="form-input text-w210"> 
					</li>
					<li class="form-item clearfix">
						<span class="item-title">注册联系人<i class="icon">*</i></span>
						<input type="text" id="LinkPerson" class="form-input text-w210">
						<span class="tip">不公开，用来与平台联络</span>
					</li>
					<li class="form-item clearfix">
						<span class="item-title">联系人手机<i class="icon">*</i></span>
						<input type="text" id="Phone" class="form-input text-w210"> 
						<span class="tip">请填写正确的手机号码，用于接收手机验证码</span>
					</li>
					<li class="form-item clearfix">
						<span class="item-title">服务机构简介</span>
						<textarea id="OrgBrief" class="text-w210"></textarea><span class="tip">限150字</span>
					</li>
					<li class="form-item clearfix">
						<span class="item-title">传真</span>
						<input type="text" id="FaxNo" class="form-input text-w210"> 
					</li> 
					<li class="form-item clearfix">
						<span class="item-title">乘车路线</span>
						<textarea id="RidingRoute" class="text-w210"></textarea><span class="tip">公交、地铁</span>
					</li>
					<li class="form-item clearfix" style="display:none">
						<span class="item-title">其他说明</span> 
						<textarea id="Remark" class="text-w210"></textarea>
					</li>
				</ul>   
				<style>
				.button-box .reg-span
				{
					display: block;
					 margin: 0 auto; 
					 border:0 none; 
					 text-align: center; 
					 text-decoration: none; 
					 color: #374c81; font: normal 16px/34px 'Microsoft YaHei','\5FAE\8F6F\96C5\9ED1';
				}
				
				</style>
				<div class="button-box"> 
				<span class="reg-span"><input type="checkbox" />&nbsp;&nbsp;同意<a href="#">《注册协议》</a></span>
					<input type="button" value="提交注册" onclick="reg();" class="submit-button">
				</div>
			</div>
			<!--//form-box--> 
		</div>
		<!--//wrapper-->
		

<%@include file="./plugin_links.jsp"%>
<%@include file="./plugin_footer.jsp"%>
		<script>
		
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
			var code ; //在全局 定义验证码  
		     function createCode(){   
		       code = "";  
		       var codeLength = 6;//验证码的长度  
		       var checkCode = document.getElementById("checkCode");  
		       var selectChar = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z');//所有候选组成验证码的字符，当然也可以用中文的  
		          
		       for(var i=0;i<codeLength;i++){   
			       var charIndex = Math.floor(Math.random()*36);  
			       code +=selectChar[charIndex];   
		       }   
		       if(checkCode){  
		         checkCode.className="code";  
		         checkCode.value = code;  
		       }   
		     }  
		      function validate (){  
		       var inputCode = document.getElementById("input1").value;  
		       if(inputCode.length <=0) {  
		           alert("请输入验证码！");  
		       }else if(inputCode != code ){  
		          alert("验证码输入错误！");  
		          createCode();//刷新验证码  
		       }else {  
		         alert("^-^ OK");  
		       }  
	       }
		      
		   createCode();
		   
		   
		   

		   function reg()
		   {
			   var SellerName=$("#SellerName").val();
			   if(SellerName=='')
				   {
				   alert("请输入用户名");
				   return;
				   }

			   if(!UserNameChk(SellerName)||SellerName.length<4||SellerName.length>10)
			   {
			   alert('用户名应为4-10个字母、数字');
			   return;
			   
			   }
			   var Password=$("#Password").val();

			   if(Password=='')
				   {
				   alert("请输入密码");
				   return;
				   }

			   if(!pwdChk(Password))
				   {
				   alert('密码应为字母、数字的组合，最少6位');
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
			   if(ShotOrgName=='')
				  {
				 	alert("请填写机构简称");
				 	return;
				  }
			   if(!emailChk(Email))
			   {
			   alert('电子邮箱格式错误');
			   return;
			   
			   }
			   var Phone=$("#Phone").val();
			   if(Phone=='')
				  {
				 	alert("请输入联系人手机");
				 	return;
				  }

			   if(!emailPhone(Phone))
				  {
					alert('请输入正确的手机号码');
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
			   
			   if(imgurl==''||imgurl==undefined||imgurl==null)
			{
				   alert('请上传图片');
				   return;
			   }
			   var ServiceType =  $("#ServiceType").find("option:selected").val();
			   var R5 =  $("#R5").find("option:selected").val();
			   
			   var Address=$("#Address").val();
			   var ServiceFeature=$("#ServiceFeature").val();
			   var OrgPeople=$("#OrgPeople").val();
			   var TelPhone=$("#TelPhone").val();

			   if(TelPhone=='')
				  {
				 	alert("请输入咨询电话");
				 	return;
				  }
			   var WebPage=$("#WebPage").val();
			   var LinkPerson=$("#LinkPerson").val();
			   if(LinkPerson=='')
				  {
				 	alert("请输入注册联系人");
				 	return;
				  }
			   var FaxNo=$("#FaxNo").val();
			   var RidingRoute=$("#RidingRoute").val();
			   var Remark=$("#Remark").val();
			   var OrgBrief=$("#OrgBrief").val();
			   var FoundDate=$("#FoundDate").val();
			   if(FoundDate=='')
			   {
				   alert("请选择成立日期");
				   return;
			   }

			   
		   	var data = 
		   	{
		   			Password:Password,
		   			SellerName:SellerName,
		   			OrgName:OrgName,
		   			Email:Email,
		   			Phone:Phone,
		   			AuthFlag:'N',
		   			R2:AuthFlag,
		   			XY:XY,
		   			ShotOrgName:ShotOrgName,
		   			FoundDate:FoundDate,
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
		   	po('seller','reg',data);

		   }
		   function afterPost(d)
		   {
			   if(d.action=='seller'&&d.operate=='loadclassname')
				{
				   classlist=d.rd.data.classname;
					loadSelect();	
				}
		   	if(d.action=='seller'&&d.operate=='reg')
		   	{
		   		alert(d.rd.msg);
		   		if(d.rd.succ)
		   		{
		   			window.location.href="./seller_center.html";
		   		}
		   	}
		   }
		</script>
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
