//localStorage.clear();
var ls=localStorage;
function chkLogin()
{
	if(ls.u==null||ls.u==''||ls.u==undefined)
	{
		window.location.href="login.jsp";
	}
}
function t(s)
{
	if(typeof(s)=='string')
	return JSON.parse(s);
	else
	return JSON.stringify(s);
}
function po(a,o,d,c) {
	var myDate=new Date();
	var limit =myDate.getTime();
	try
	{
		//showLoading("s"+limit);
	}
	catch(e)
	{
		
	}
	var p="pd.action="+a+"&pd.data="+t(d)+"&pd.operate="+o;
		$.ajax({
			// 后台处理程序
			url : "action.jsp",
			// 数据发送方式
			type : "post",
			// 接受数据格式
			dataType : "json",
			// 要传递的数据
			data : p,
			// 回传函数
            timeout:20000000,// 设置请求超时时间（毫秒）。   
            complete: function (msg) {//请求失败时调用函数。  
            	if(msg.readyState==4&&msg.status==200)
            	{
                	//alert("请求成功!"+s2j(msg.responseText)); 
                	var ret = {action:a,data:d,operate:o,rd:s2j(msg.responseText)};
                	if(c==null)
                	{
                		try
                		{
                			//stopLoading("s"+limit);
                		}
                		catch(e)
                		{
                			
                		}
                		afterPost(ret);
                	}            	      
                	else
                	{
                		//alert(33);

                		try
                		{
                			//stopLoading("s"+limit);
                		}
                		catch(e)
                		{
                			
                		}
                		eval(c+"("+t(ret)+");");
                	}
            	}
            	else
            	{
                	//alert("请求失败!");   
            	}
            },   
            success:function(dataObj){ //请求成功后回调函数。
           	    //var dataObj=eval("("+data+")");//转换为json对象 
            	//alert(dataObj);
            	
			}
		});
	}
function json2str(o) { 
	var arr = []; 
	var fmt = function(s) { 
	if (typeof s == 'object' && s != null) return json2str(s); 
	return /^(string|number)$/.test(typeof s) ? "'" + s + "'" : s; 
	} 
	for (var i in o) arr.push("'" + i + "':" + fmt(o[i])); 
	return '{' + arr.join(',') + '}'; 
	} 
function s2j(stringValue)
{
   eval("var theJsonValue = "+stringValue);
   return theJsonValue;
}
//javascript获取指定参数及其对应的值
function getParameter(paraStr)
{
	var tURL = window.location.href;
  var result = ""; 
  //获取URL中全部参数列表数据
  var str = "&" + tURL.split("?")[1];
  var paraName = paraStr + "=";
  //判断要获取的参数是否存在
  if(str.indexOf("&"+paraName)!=-1)
  {
      //如果要获取的参数到结尾是否还包含“&”
      if(str.substring(str.indexOf(paraName),str.length).indexOf("&")!=-1)
      {
          //得到要获取的参数到结尾的字符串
          var TmpStr=str.substring(str.indexOf(paraName),str.length);
          //截取从参数开始到最近的“&”出现位置间的字符
          result=TmpStr.substr(TmpStr.indexOf(paraName),TmpStr.indexOf("&")-TmpStr.indexOf(paraName));   
      } 
      else
      {   
          result=str.substring(str.indexOf(paraName),str.length);   
      }
  }   
  else
  {   
      result="无此参数";   
  }   
  result = result.replace("&","");
  result = result.split("=")[1];
  if(result=='undefined'||result==undefined)
  result='';

  return (result);   
}


function loading(canvas,options){
    this.canvas = canvas;
    if(options){
      this.radius = options.radius||12;
      this.circleLineWidth = options.circleLineWidth||4;
      this.circleColor = options.circleColor||'lightgray';
      this.moveArcColor = options.moveArcColor||'gray';
    }else{      
      this.radius = 12;
      this.circelLineWidth = 4;
      this.circleColor = 'lightgray';
      this.moveArcColor = 'gray';
    }
  }
  loading.prototype = {
    show:function (){
      var canvas = this.canvas;
      if(!canvas.getContext)return;
      if(canvas.__loading)return;
      canvas.__loading = this;
      var ctx = canvas.getContext('2d');
      var radius = this.radius;      
      var me = this;
      var rotatorAngle = Math.PI*1.5;
      var step = Math.PI/6;
      canvas.loadingInterval = setInterval(function(){
        ctx.clearRect(0,0,canvas.width,canvas.height);         
        var lineWidth = me.circleLineWidth;
        var center = {x:canvas.width/2 - radius,y:canvas.height/2-radius};          
        ctx.beginPath();
        ctx.lineWidth = lineWidth;
        ctx.strokeStyle = me.circleColor;
        ctx.arc(center.x,center.y,radius,0,Math.PI*2);
        ctx.closePath();
        ctx.stroke();
        //在圆圈上面画小圆
        ctx.beginPath();
        ctx.strokeStyle = me.moveArcColor;
        ctx.arc(center.x,center.y,radius,rotatorAngle,rotatorAngle+Math.PI*.45);
        ctx.stroke();
        rotatorAngle+=step;
       
      },50);
    },
    hide:function(){
      var canvas = this.canvas;
      canvas.__loading = false;
      if(canvas.loadingInterval){
        window.clearInterval(canvas.loadingInterval);
      }
      var ctx = canvas.getContext('2d');
      if(ctx)ctx.clearRect(0,0,canvas.width,canvas.height);
    }
  };

  function showLoading(n)
  {
	  //alert(n+"s");
	  //alert(1);
  	$(document.body).append("<canvas id=\""+n+"\" class=\"loadingCanvas\">您的浏览器不支持html5</canvas>");
	  //alert(2);
  	eval("var "+n+" = new loading(document.getElementById('"+n+"'),{radius:30,circleLineWidth:29});");
	  //alert(3);
  	eval(""+n+".show();");
	  //alert(4+n);
  }
  function stopLoading(n)
  {
	  $("#"+n).remove();
  }
  
function showMsg(s,t)
{
	if(t==null||t==undefined)
		t=1000;
	var myDate=new Date()
	var limit =myDate.getTime();
	$(document.body).append("<div class=\"dis_non_msg\" id=\""+limit+"\"><h2 class=\"title\">"+s+"</h2></div>");
	var t=setTimeout("showMsgHide('"+limit+"')",t);
}
function showMsgHide(l)
{
	$("#"+l).remove();
}
function setHeaderTitleClass()
{
	var t=getParameter("tag");
	var dataArr=
		[
		    {tag:'1',classname:'lighting_header',allhref:'parlor.html'},  
		    {tag:'2',classname:'bedroom_header',allhref:'bedroom.html'},  
		    {tag:'3',classname:'study_header',allhref:'study.html'},  
		    {tag:'4',classname:'toilet_header',allhref:'toilet.html'},  
		    {tag:'5',classname:'restaurant_header',allhref:'restaurant.html'}       
	             ];
	for(var i=0;i<dataArr.length;i++)
	{
		if(t==dataArr[i].tag||window.location.href.indexOf(dataArr[i].allhref)!=-1)
		{
			if(t=='')t=dataArr[i].tag;
			$(".shop_header").addClass(dataArr[i].classname);		
			var allurl=dataArr[i].allhref;
			$("#header_nav_all_li").click(function(){
				top.location.href=allurl;
			});	
			$("#header_nav_recommend_li").click(function(){
				top.location.href="recommended.html?tag="+t;
			});	
			$("#header_nav_sale_li").click(function(){
				top.location.href="sale.html?tag="+t;
			});				
		}
	}
}
function necval(a)
{
	for(var i=0;i<a.length;i=i+2)
	{
		var val = a[i];
		var msg = a[i+1];
		if(val=='')
		{
			alert(msg+"不能为空");
			return false;
		}
	}
	return true;
}



function getStar(l)
{
	var h="";
	for(var i=0;i<5;i++)
	{
		if(i<l)
			h+="<span class=\"h-span\"></span>";
		else
			h+="<span></span>";
	}	
	return h;
}
function getStarAss(l)
{
	var h="";
	for(var i=0;i<5;i++)
	{
		if(i<l)
			h+="<span class=\"h-span\" onmouseover=\"setStar("+i+")\"></span>";
		else
			h+="<span onmouseover=\"setStar("+i+")\"></span>";
	}	
	return h;
}



function loadSideNav(ncode)
{
	//先找出当前栏目的父栏目
	//chnllist
	var pcode = "";
	for(var i=0;i<chnllist.length;i++)
	{
		if(ncode==chnllist[i].SerialNo)
		{
			pcode=	chnllist[i].ParentChannel;
			break;
		}
	}
	var snh = "";
	for(var i=0;i<chnllist.length;i++)
	{
		if(pcode==chnllist[i].ParentChannel)
		{
			var ah="";
			if(ncode==chnllist[i].SerialNo)
			{
				ah=" class=\"current\"";	
			}
			snh+="<a href=\""+chnllist[i].URL+"\""+ah+">"+chnllist[i].ChannelName+"</a>";
		}
	}
	$(".side-nav").html(snh);
}
function pwdChk(p)
{
	var reg = /^(?![a-z]+$)(?!\d+$)[a-z0-9]{6,20}$/i;
	if(p.match(reg)){
	    return true;
	} 
	return false;
}
function emailChk(p)
{
    var reg= /\w@\w*\.\w/;

	if(p.match(reg)){
		return true;
	} 
	return false;
}
function emailPhone(p)
{
	var reg = /^(13[0-9]|14[0-9]|18[0-9]|17[0-9]|15[0-9])\d{8}$/;
	
	if(p.match(reg)){
		return true;
	} 
	return false;
}
function UserNameChk(u)
{
//	var b = /^[0-9a-zA-Z]*$/g;
//
//	if(u.match(b)){
//		return true;
//	} 
//	return false;
	var sa = ['\\','/',':','*','?','"','<','>','|'];
	for(var i=0;i<sa.length;i++)
	{
		if(u.indexOf(sa[i])!=-1)
		{
			return false;
		}
	}
	return true;
}
jQuery(function($){   
    $.datepicker.regional['zh-CN'] = {   
       clearText: '清除',   
       clearStatus: '清除已选日期',   
       closeText: '关闭',   
       closeStatus: '不改变当前选择',   
       prevText: '<上月',   
       prevStatus: '显示上月',   
       prevBigText: '<<',   
       prevBigStatus: '显示上一年',   
       nextText: '下月>',   
       nextStatus: '显示下月',   
       nextBigText: '>>',   
       nextBigStatus: '显示下一年',   
       currentText: '今天',   
       currentStatus: '显示本月',   
       monthNames: ['一月','二月','三月','四月','五月','六月', '七月','八月','九月','十月','十一月','十二月'],   
       monthNamesShort: ['一','二','三','四','五','六', '七','八','九','十','十一','十二'],   
       monthStatus: '选择月份',   
       yearStatus: '选择年份',   
       weekHeader: '周',   
       weekStatus: '年内周次',   
       dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],   
       dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],   
       dayNamesMin: ['日','一','二','三','四','五','六'],   
       dayStatus: '设置 DD 为一周起始',   
       dateStatus: '选择 m月 d日, DD',   
       dateFormat: 'yy-mm-dd',   
       firstDay: 1,   
       initStatus: '请选择日期',   
       isRTL: false};   
       $.datepicker.setDefaults($.datepicker.regional['zh-CN']);   
   });  