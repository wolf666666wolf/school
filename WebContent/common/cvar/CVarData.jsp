﻿<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<SCRIPT language="JavaScript1.2" src="./CVar.js"></SCRIPT>
<SCRIPT language="JavaScript1.2" >

//gVars=new CVar(1,new Array(),"adsf");
gVars =new CVar();
gVars1=new CVar();

gVCode  =new CVar();    //Code表中的所有信息
gVSwitch=new CVar();    //在显示数据时用来交换的信息

//初始化显示数据交换变量
gVSwitch.addVar("ShowCodeFrame","","");
gVSwitch.addVar("ShowCodeObj","","");
gVSwitch.addVar("ShowCodeOrder","","");
gVSwitch.addVar("ShowCodeX","",0);
gVSwitch.addVar("ShowCodeY","",0);
gVSwitch.addVar("ShowCodeError","","");
gVSwitch.addVar("ShowCodeErrorCode","","");

try {
  top.achieveVD = true;   //用于判断页面初始化完成
  //top.alert("achieveVD:" + achieveVD);
} catch(ex) {}
</SCRIPT>

</head>
</html>
