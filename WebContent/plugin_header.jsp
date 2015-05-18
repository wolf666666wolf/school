<%@page import="com.sinosoft.lis.schema.*"%>
<%@page import="com.sinosoft.lis.pubfun.*"%>
<%
//判断是否登录
String ut = (String)session.getValue("ut");
SDUserSchema sdu = (SDUserSchema)session.getValue("sdu");
SDSellerSchema sds = (SDSellerSchema)session.getValue("sds");
SDAssSchema sda = (SDAssSchema)session.getValue("sda");
String centerName = "";
String centerUrl = "";
String userJson = "";
try{
if("U".equals(ut))
{
	centerName="个人中心";
	centerUrl="user_center.html";
	userJson = PubFun.schema2JsonObj(sdu).toString();
	if(sdu.getPhone()==null||"".equals(sdu.getPhone()))
	{
		%>
		<script>
		if(window.location.href.indexOf("user_center2.html")<0)
		{
			alert("请您先绑定手机，否则无法继续使用");
			window.location.href="./user_center2.html";
		}
		</script>
		<%
	}
}
if("S".equals(ut))
{
	centerName="商家中心";
	centerUrl="seller_center.html";
	userJson = PubFun.schema2JsonObj(sds).toString();

}
if("A".equals(ut))
{
	centerName="社团中心";
	centerUrl="ass_center.html";
	userJson = PubFun.schema2JsonObj(sda).toString();

}
}
catch(Exception e)
{
	session.putValue("ut", null);
	session.putValue("sdu", null);
	session.putValue("sds", null);
	session.putValue("sda", null);
	response.sendRedirect("./index.html");	
	return;
}
if(userJson.equals(""))
{
	userJson="{}";	
}
%>
		<script src="./js/jquery-1.10.1.min.js"></script>
		<script src="./js/jquery.qrcode.min.js"></script>
		<script src="./js/jquery-ui.js"></script>
		<script src="./js/jquery-ui-timepicker-addon.js"></script>
		<script src="./js/jquery.jcarousel.min.js"></script>
		<script src="./js/jquery.pikachoose.min.js"></script>
		<script src="./js/jquery.touchwipe.min.js"></script>
		<script src="./js/json2.js"></script>
		<script src="./js/jquery-jtemplates.js"></script>
		<script src="./js/jcarousellite.min.js"></script>
		<script src="./js/public.js?t=<%=System.currentTimeMillis()%>"></script>
		<script src="./js/common.js?t=<%=System.currentTimeMillis()%>"></script>
		<script src="./js/laycode.js"></script>
		<script src="./static/chnl.js?t=<%=System.currentTimeMillis()%>"></script>
	 <link rel="stylesheet" href="./css/public.css" />
	 <link rel="stylesheet" href="./css/jquery-ui.css" />
		<!--<link rel="stylesheet" href="http://10000.tsinghuaprogress1.sinaapp.com/css/public.css" />
		 -->
		<link rel="stylesheet" href="./css/order.css" />
		<script>
		var result3=new Array();
		var result5=new Array();
		var result6=new Array();

		//初始化导航项目
		var userjson=<%=userJson%>;
		var ut='<%=ut%>';
		$(function(){
			loadNav();		
		});
		function loadNav()
		{
			var flisttemp=new Array();
			for(var i=0;i<chnllist.length;i++)
			{
				if(chnllist[i].ChannelLevel=='1')
				{
					flisttemp[flisttemp.length]=	chnllist[i];
				}
			}
			var html = "";
			for(var i=0;i<flisttemp.length;i++)
			{
				var html_i="";
				//flisttemp[i].URL=flisttemp[i].URL.replace("(id)",flisttemp[i].SerialNo);
				html_i+="<li class=\"item-"+(i+1)+"\">";
				//html+="<a href=\""+flisttemp[i].URL+"\"><span class=\"trigger\">"+flisttemp[i].ChannelName+"</span></a>";
				html_i+="<a href=\"javascript:void(0);\"><span class=\"trigger\">"+flisttemp[i].ChannelName+"</span></a>";
				var subHtml = "";
				var sflag=false;
				subHtml+="<div class=\"sub-links clearfix\">";
				for(var j=0;j<chnllist.length;j++)
				{
					if(flisttemp[i].SerialNo==chnllist[j].ParentChannel)	
					{
						//if(chnllist[j].URL!=null&&chnllist[j].URL!=''&&chnllist[j].URL!=undefined)
						//chnllist[j].URL=chnllist[j].URL.replace("(id)",chnllist[j].SerialNo);
						
						if(chnllist[j].R3!='0')
						{
							chnllist[j].URL="./class_"+chnllist[j].R3+"_"+chnllist[j].SerialNo+".html";
						}
						else
						{
							chnllist[j].URL=chnllist[j].URL+"?"+chnllist[j].SerialNo;
						}
						//alert(chnllist[j].URL.replace("(id)",chnllist[j].SerialNo));
						if(flisttemp[i].ChannelName=='办事指南')
						{
							result3[result3.length]=chnllist[j];	
						}
						if(flisttemp[i].ChannelName=='为老服务')
						{
							result5[result5.length]=chnllist[j];	
						}
						if(flisttemp[i].ChannelName=='便民服务')
						{
							result6[result6.length]=chnllist[j];	
						}
						subHtml+="<a href=\""+chnllist[j].URL+"\">"+chnllist[j].ChannelName+"</a>";
						sflag=true;
					}
				}
				
				subHtml+="</div>";
				
				if(sflag)
				{
					html_i+=subHtml;	
				}
				html_i+="</li>";
				if(flisttemp[i].R2=='Y')
					html+=html_i;
			}
			$("#header_nav_ul").html(html);
				
			//alert($("#test").val());
		}
		</script>
		<!--header-->
		<div class="header">
			<div class="wrapper clearfix">
				<div class="rigth-box">
				<%
				if(ut==null)
				{
					 %>
					<!--login-bar-->
					<div class="login-bar clearfix">
						<input type="text" class="username" id="HUserName" placeholder="个人用户/手机号"/>
						<input type="password" name="" id="HPassword" class="password" placeholder="密码"/>
						<div class="button-box">
							<a href="javascript:login();" class="login-link">登录</a>
							<a href="./reg.html" class="registered-link">注册</a>
							<a href="./findpass.html" class="forgot-link">忘记密码</a>
						</div>
					</div>
					<!--//login-bar-->
					<%}
				else
				{%>
				<div class="login-bar clearfix">
						<div class="button-box">
							<a href="./<%=centerUrl %>" class="login-link"><%=centerName %></a>
							<a href="javascript:logout();" class="registered-link">退出登录</a>
						</div>
					</div>
				<%} %>
					
					<!--weather-box-->
					<div class="weather-box">
						<iframe name="weather_inc" allowtransparency="true" src="http://i.tianqi.com/index.php?c=code&id=7" width="225" height="90" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
					</div>
					<!--//weather-box-->
					
					<div class="header-nav clearfix">
						<a href="./index.html">首页</a>
						<!-- 
						<a href="#">设为首页</a>
						 -->
						<a href="./static_aboutus.html">联系我们</a>
					</div>
				</div>
				<div class="logo">
					<span>ssssssss</span>
				</div>
			</div>
		</div>
		<!--//header-->
		
		<!--nav-box-->
		<div class="nav-box">
			<div class="nav-box-inner"> 
				<div class="wrapper">
					<div class="search-bar"> 
						<div class="search-input"><input id="kwh" placeholder="搜索"></input></div>
						<a href="search.html" class="advanced-search">高级搜索</a>
					</div>
					<ul class="main-nav clearfix" id="header_nav_ul">
					</ul>
				</div>
			</div>
		</div>
		<!--//nav-box-->
<script>
$(document).ready(function(){ 
    $("#kwh").keydown(function(e){ 
        var curKey = e.which; 
        if(curKey == 13){ 
    		window.location.href='search.html?kw='+$("#kwh").val();
        } 
    }); 
}); 
function login()
{
	var HUserName=$("#HUserName").val();
	var HPassword=$("#HPassword").val();
	if(HUserName==''&&HPassword=='')
	{
		window.location.href="./login.html";	
		return;
	}
	if(HUserName=='')
	{
	alert('请输入用户名');
	return;
   }
	if(HPassword=='')
	{
	alert('请输入密码');
	return;
   }
	var data = 
	{
			UserName:HUserName,
			Password:HPassword,
			UserType:'U'
			};
	po('user','login',data,"logincalback");
}
function logout()
{
	var data = 
	{
			};
	po('user','logout',data,"logoutcalback");
}
function logoutcalback(d)
{
	if(d.rd.succ)
	{
		window.location.href=	window.location.href;
	}
	else
	{
		alert(d.rd.msg);	
	}
}
function logincalback(d)
{
	if(d.rd.succ)
	{
		window.location.href=	window.location.href;
	}
	else
	{
		alert(d.rd.msg);	
	}
}
</script>