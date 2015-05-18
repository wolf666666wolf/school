/*弹出窗口的javascript,luzhe-20080812*/
function $()
{
	return document.getElementById?document.getElementById(arguments[0]):eval(arguments[0]);
}

var OverH,OverW,ChangeDesc,ChangeH=50,ChangeW=50,DetailTitle;

function OpenDetail(_Dw,_Dh,_Desc,_DTitle) 
{
	$("DetailDiv").innerHTML="";
	OverH=_Dh;OverW=_Dw;ChangeDesc=_Desc;DetailTitle=_DTitle;
	$("DetailDiv").style.display='';
	if(_Dw>_Dh){ChangeH=Math.ceil((_Dh-10)/((_Dw-10)/50))}else if(_Dw<_Dh){ChangeW=Math.ceil((_Dw-10)/((_Dh-10)/50))}
	$("DetailDiv").style.top="50px";
	$("DetailDiv").style.left="50px";
	OpenNow();
}
var Nw=10,Nh=10;
function OpenNow() 
{
	if (Nw>OverW-ChangeW)ChangeW=2;
	if (Nh>OverH-ChangeH)ChangeH=2;
	Nw=Nw+ChangeW;Nh=Nh+ChangeH;

	if(OverW>Nw||OverH>Nh) 
	{
		if(OverW>Nw) 
		{
			$("DetailDiv").style.width=Nw+"px";
		}
		if(OverH>Nh) 
		{
			$("DetailDiv").style.height=Nh+"px";
		}
		window.setTimeout("OpenNow()",30)
	}
	else
	{
		Nw=10;Nh=10;ChangeH=50;ChangeW=50;
		
		var IFRAMECode = "<IFRAME ID=IFrame1 FRAMEBORDER=0 SCROLLING=NO height='"+OverH+"px' WIDTH='"+OverW+"px' SRC='"+ChangeDesc+"'></IFRAME>"

		
		var DivCode = "";
		var _W1 = OverW-20;
		
		DivCode+="<table>";
		DivCode+="<tr height='15px' WIDTH='"+OverW+"px'>";
		DivCode+="<td class='titleImg' width = '"+_W1+"px'>"+DetailTitle+"</td><td class='titleImg' width='20px'>";
		
		DivCode+="<table width='20px' heigth='15px'><tr><td><img src='../common/images/hidemenu.gif' onclick=\"DetailDiv.style.display='none'\"></td></tr></table>";
		
		DivCode+="</td>";
		DivCode+="</tr>";
		DivCode+="<tr WIDTH='"+OverW+"px'>";
		DivCode+="<td colSpan=2>";
		
		DivCode+=IFRAMECode;
		
		DivCode+="</td>";
		DivCode+="</tr>";
		$("DetailDiv").innerHTML=DivCode;

	}
}