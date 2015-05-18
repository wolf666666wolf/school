/**
 * <p>程序名称: Common.js</p>
 * <p>程序功能: 公用函数变量定义 </p>
 * <p>注释更新人: 胡博</p>
 * <p>最近更新日期: 2002-10-2</p>
 * <p>注意：所有的变量类型为VAR，在JAVA中表示为STRING<p>
 */

/** 日期分隔符,初始值=":" */
var DATEVALUEDELIMITER=":";
/** 域名与域值的分隔符,初始值=":" */
var NAMEVALUEDELIMITER=":";
/** 初始值=":" */
var SBCCASECOLON="：";
/** 域之间的分隔符,初始值="|" */
var FIELDDELIMITER="`";
/** 初始值="｜" */
var SBCCASEVERTICAL="｜";
/** 记录之间的分隔符,初始值="^" */
var RECORDDELIMITER="^";
/** 每一页最大显示的行数,初始值="10" */
var MAXSCREENLINES=10;
/** 内存中存储的最大的页数,初始值="20" */
var MAXMEMORYPAGES=20;
/** 修改(颜色),初始值="FFFF00" */
var BGCOLORU="FFFF00";
/** 添加(颜色),初始值="#00F0F0" */
var BGCOLORI="#00F0F0";
/** 删除(颜色),初始值="#778899" */
var BGCOLORD="#778899";
/** 快捷菜单最大项数 */
var MAXMENUSHORTNUM = 3;

var DELAY_MILLS = 2000;
/**
 * 在String对象上添加trim方法
 */
String.prototype.trim = function()
{
	//利用正则表达式去除头尾的空格
	return this.replace(/(^\s*)|(\s*$)/g,"");
}

/**
 * 更换图片
 * Example: function changeImage(image,gif)
 * @param image 存放图片的对象或框架或页面
 * @param gif 图片的全路径
 */
function changeImage(image,gif)
{
	image.src=gif;
}

/**
 * 替换字符串函数
 * Example: replace("Minim123Minim", "123", "Minim") returns "MinimMinimMinim"
 * @param strExpression 字符串表达式
 * @param strFind 被替换的子字符串
 * @param strReplaceWith 替换的目标字符串
 * 即用strReplaceWith字符串替换掉strFind
 * @return 返回替换后的字符串表达式
 */
function replace(strExpression,strFind,strReplaceWith)
{
	var strReturn;
	strReturn =(strExpression==null?"":strExpression);
	//利用正则表达式进行全局替换
	strReturn = strExpression.replace(eval("/"+strFind+"/g"),strReplaceWith);
	return strReturn;
}

/**
 * 去掉字符串头尾空格
 * Example: trim(" Minim ") returns "Minim"<p>
 * @param strValue 字符串表达式
 * @return 头尾无空格的字符串表达式
 */
function trim(s)
{
	//使用正则表达式进行全局替换
	return s.replace(/(^\s*)|(\s*$)/g,"");
}

/**
 * 对输入域是否是正整数的校验
 * Example: isInteger("Minim") returns false;isInteger("123") returns true
 * @param strValue 输入数值表达式或字符串表达式
 * @return 布尔值（true--是整数, false--不是整数）
 */
function isInteger(strValue)
{
	//使用正则表达式进行判断
	var chkExp=/^\d+$/;
	return (chkExp.test(strValue));
}

/**
 * 对输入域是否是正数的校验
 * Example: isNumeric("Minim") returns false;isNumeric("123.1") returns true
 * @param strValue 输入数值表达式或字符串表达式
 * @return 布尔值（true--是数字, false--不是数字）
 */
function isNumeric(strValue)
{
	//使用正则表达式进行判断
	var chkExp=/^\d+(\.\d+)?$/;
	return (chkExp.test(strValue));
}

/**
 * 离开域时的数字校验
 * Example: checkNumber(HTML.Form.Object.Name)
 * @param Field HTML页面的对象名称
 * @return true或产生一个“errorMessage("请输入合法的数字")”
 */
function checkNumber(Field)
{
	var strValue=Field.value;
	if(trim(strValue)!="" && !isNumeric(strValue))
	{
		errorMessage("请输入合法的数字");
		Field.focus();
		Field.select();
		return false;
	}
	return true;
}

/**
 * 判断字符是否在s中
 */
function isCharsInBag(s, bag)
{
	var i;
	for(i = 0; i < s.length; i++)
	{
		var c = s.charAt(i);
		if(bag.indexOf(c) == -1) return false;
	}
	return true;
}

/**
 * 日期的合法判断
 * Example: isLegalDate("2002", "10", "03") returns true;isLegalDate("Minim", "10", "03") returns false
 * @param y 年份字符串
 * @param m 月份字符串
 * @param d 日期字符串
 * @return 布尔值（true--合法日期, false--非法日期）
 */
function isLegalDate(y,m,d)
{
	if(isNaN(parseInt(y,10)) || isNaN(parseInt(m,10)) || isNaN(parseInt(d,10)))
	{
		return false;
	}
	var dt = new Date(parseInt(y,10),parseInt(m,10)-1,parseInt(d,10));
	if(dt.getFullYear()==parseInt(y,10) && dt.getMonth()==parseInt(m,10)-1 && dt.getDate()==parseInt(d,10))
	{
		return true;
	}
	else
	{
		return false;
	}
}

/**
 * 对输入域是否是日期的校验
 * Example: isDate("2002-10-03") returns true;isDate("2002/10/03") returns false
 * @param date 日期字符串,格式必须为“yyyy-mm-dd”
 * @return 布尔值（true--合法日期, false--非法日期）
 */
function isDate(date)
{
	var strValue;
	strValue=date.split("-");
	if(strValue.length!=3)
	{
		return false;
	}
	if(!isInteger(strValue[0]) || !isInteger(strValue[1]) || !isInteger(strValue[2]))
	{
		return false;
	}
	var intYear=eval(strValue[0]);
	var intMonth=eval(strValue[1]);
	var intDay=eval(strValue[2]);
	if(!isLegalDate(intYear,intMonth,intDay))
	{
		return false;
	}
	return true;
}

/**
 * 比较两个日期字符串
 * Example: compareDate("2002-10-03", "2002-10-03") returns 0;compareDate("2002-10-03", "2001-10-03") returns 1
 * @param date1 日期字符串,格式必须为“yyyy-mm-dd”
 * @param date2 日期字符串,格式必须为“yyyy-mm-dd”
 * @return date1=date2则返回0 , date1>date2则返回1 , date1<date2则返回2
 */
function compareDate(date1,date2)
{
	var strValue=date1.split("-");
	var date1Temp=new Date(strValue[0],strValue[1]-1,strValue[2]);
	strValue=date2.split("-");
	var date2Temp=new Date(strValue[0],strValue[1]-1,strValue[2]);
	if(date1Temp.getTime()==date2Temp.getTime())
	{
		return 0;
	}
	else if(date1Temp.getTime()>date2Temp.getTime())
		{
			return 1;
		}
		else
		{
			return 2;
		}
}

/**
 * 对span的显示、隐藏
 * Example: showPage(HTML.ImageObject, HTML.SpanObject.ID)
 * @param img 显示图片的HTML对象
 * @param spanID HTML中SPAN对象的ID
 * @return 如果页面SPAN可见，则将其隐藏，并显示表示关闭的图片；反之
 */
function showPage(img,spanID)
{
	if(spanID.style.display=="")
	{
		//关闭
		spanID.style.display="none";
		img.src="../common/images/butCollapse.gif";
	}
	else
	{
		//打开
		spanID.style.display="";
		img.src="../common/images/butExpand.gif";
	}
}

/**
 * 打开一个窗口
 * Example: openWindow("www.163.com", null)
 * @param strURL 新窗口的完整路径（URL）或相对路径
 * @param strName 指定窗口名，可以为空
 * @return 返回新建窗口的句柄
 */
//function openWindow(strURL,strName)
//{
//	var newWindow = window.open(strURL,strName,'width=640,height=480,top=0,left=0,toolbar=0,location=0,directories=0,menubar=0,scrollbars=1,resizable=1,status=0');
//	newWindow.focus();
//	return newWindow;
//}

/**
 * 分割代码并放在select域里（基本上已经被取缔）
 * Example: setOption("name", "1=Minim&2=Hzm");将在下拉框中看到可选项Minim和Hzm
 * @param selectName HTML的select对象名
 * @param strValue 包含select对象显示内容的字符串，串的格式必须为: value1=text1&value2=text2，以“&"号分隔
 */
function setOption(selectName,strValue)
{
	var arrayField=strValue.split("&");
	var i=0;
	fm.all(selectName).length = 0;
	while(i<arrayField.length)
	{
		var option=document.createElement("option");
		var arrayTemp=arrayField[i].split("=");
		var strFieldName=arrayTemp[0];
		var strFieldValue=unescape(arrayTemp[1]);
		option.value=strFieldName;
		option.text=strFieldValue;
		fm.all(selectName).add(option);
		i++;
	}
}

/**
 * 获取日期对象
 * @param strDate 日期字符串
 * @param splitOp 分割符
 * @return 返回日期对象
 */
function getDate(strDate, splitOp) {
	if(splitOp == null) splitOp = "-";
	var arrDate = strDate.split(splitOp);
	if(arrDate[1].length == 1) arrDate[1] = "0" + arrDate[1];
	if(arrDate[2].length == 1) arrDate[2] = "0" + arrDate[2];
	return new Date(arrDate[0], arrDate[1]-1, arrDate[2]);
}

/**
 * 计算两个日期的差,返回差的月数(M)或天数(D)(其中天数除2.29这一天)
 * Example: dateDiff("2002-10-1", "2002-10-3", "D") returns "2";dateDiff("2002-1-1", "2002-10-3", "M") returns "9"
 * @param dateStart 减日期
 * @param dateEnd 被减日期
 * @param MD 标记，“M”为要求返回差的月数；“D”为要求返回差的天数
 * @return 返回两个日期差的月数(M)或天数(D)
 */
function dateDiff(dateStart,dateEnd,MD)
{
	if(dateStart==""||dateEnd=="")
	{
		return false;
	}
	if(typeof(dateStart) == "string")
	{
		dateStart = getDate(dateStart);
	}
	if(typeof(dateEnd) == "string")
	{
		dateEnd = getDate(dateEnd);
	}
	var i;
	if(MD=="D")
	{
		//按天计算差
		var endD = dateEnd.getDate();
		var endM = dateEnd.getMonth();
		var endY = dateEnd.getFullYear();
		var startD = dateStart.getDate();
		var startM = dateStart.getMonth();
		var startY = dateStart.getFullYear();
		var startT=new Date(startY,startM-1,startD);
		var endT=new Date(endY,endM-1,endD);
		var diffDay=(endT.valueOf()-startT.valueOf())/86400000;
		return diffDay;
	}
	else
	{
		//按月计算差
		var endD = dateEnd.getDate();
		var endM = dateEnd.getMonth();
		var endY = dateEnd.getFullYear();
		var startD = dateStart.getDate();
		var startM = dateStart.getMonth();
		var startY = dateStart.getFullYear();
		if(endD>=startD)
		{
			return(endY-startY)*12 +(endM-startM) + 1;
		}
		else
		{
			return(endY-startY)*12 +(endM-startM);
		}
	}
}

/**
 * 对小数点后第三位四舍五入
 * Example: mathRound(123.456) returns 123.46
 * @param intValue 整型数值
 * @return 对小数点后第三位四舍五入后的整型数值
*/
function mathRound(x)
{
	var v = Math.round(x * 100) ;
	v = v/100;
	return v;
}

/**
 * 对数字按0.00格式化
 * Example: pointTwo(123.456) returns 123.45;pointTwo(123) returns 123.00
 * @param intValue 整型数值
 * @return 按0.00格式化后的整型数值
 */
function pointTwo(s)
{
	var v = s.toString();
	var len = v.length;
	var index = v.indexOf(".");
	if(index==-1)
	{
		v = v + ".00";
		return v;
	}
	else
	{
		if(len-index==3)
		{
			return v;
		}
		else if(len-index==2)
			{
				v = v +"0";
				return v;
			}
			else if(len-index==1)
				{
					v = v + "00";
					return v;
				}
				else
				{
					return v.substring(0,index+3);
				}
	}
}

/**
 * 对数字按0.0000格式化
 * Example: pointFour(123.456789) returns 123.4567;pointFour(123) returns 123.0000
 * @param intValue 整型数值
 * @return 按0.0000格式化后的整型数值
 */
function pointFour(s)
{
	var v = Math.round(parseFloat(s) * 10000)/10000;
	v = v.toString();
	var len = v.length;
	var index = v.indexOf(".");
	if(index==-1)
	{
		v = v + ".0000";
		return v;
	}
	else
	{
		if(len-index==5)
		{
			return v;
		}
		else if(len-index==4)
			{
				v = v +"0";
				return v;
			}
			else if(len-index==3)
				{
					v = v + "00";
					return v;
				}
				else if(len-index==2)
					{
						v = v + "000";
						return v;
					}
					else if(len-index==1)
						{
							v = v + "0000";
							return v;
						}
						else
						{
							return v.substring(0,index+5);
						}
	}
}
/**
 * 对数字按0.0000格式化
 * Example: pointFour(123.456789) returns 123.4567;pointFour(123) returns 123.0000
 * @param intValue 整型数值
 * @return 按0.0000格式化后的整型数值
 */
function pointSix(s)
{
	var v = Math.round(parseFloat(s) * 1000000)/1000000;
	v = v.toString();
	var len = v.length;
	var index = v.indexOf(".");
	if(index==-1)
	{
		v = v + ".000000";
		return v;
	}
	else
	{
		if(len-index==7)
		{
			return v;
		}
		else if(len-index==6)
			{
				v = v +"0";
				return v;
			}
			else if(len-index==5)
				{
					v = v + "00";
					return v;
				}
				else if(len-index==4)
					{
						v = v + "000";
						return v;
					}
					else if(len-index==3)
						{
							v = v + "0000";
							return v;
						}
						else if(len-index==2)
						{
							v = v + "00000";
							return v;
						}
						else if(len-index==1)
						{
							v = v + "000000";
							return v;
						}
						else
						{
							return v.substring(0,index+7);
						}
	}
}
/**
 * 在浏览器中弹出一个alert框显示错误信息
 * @param strErrMsg 要显示的错误信息字符串
 */
function errorMessage(strErrMsg)
{
	alert(strErrMsg);
}

/**
 * 对输入域是否是日期的校验(日期格式xxxx/xx/xx)，建议修改，与isDate函数合并
 * Example: isDateI("2004/10/4") returns true;isDateI("2004-10-4") returns false
 * @param date 格式必须为“YYYY/MM/DD”的日期字符串
 * @return 布尔值（true--合法日期, false--非法日期）
 */
function isDateI(date)
{
	var strValue;
	strValue=date.split("/");
	if(strValue.length!=3) return false;
	if(!isInteger(strValue[0]) || !isInteger(strValue[1]) || !isInteger(strValue[2])) return false;
	var intYear=eval(strValue[0]);
	var intMonth=eval(strValue[1]);
	var intDay=eval(strValue[2]);
	if(intYear<0 || intYear>9999 || intMonth<0 || intMonth>12 || intDay<0 || intDay>31) return false;
	return true;
}

/**
 * 对输入域是否是日期的校验(日期格式xxxxxxxx)，建议修改，与isDate函数合并
 * Example: isDateI("2004104") returns true
 * <p>Other returns false<p>
 */
function isDateN(date)
{
	var strValue;
	strValue=new Array();
	strValue[0]=date.substring(0, 4);
	strValue[1]=date.substring(4, 6);
	strValue[2]=date.substring(6, 8);
	if(strValue.length!=3)
	{
		return false;
	}
	if(!isInteger(strValue[0]) || !isInteger(strValue[1]) || !isInteger(strValue[2]))
	{
		return false;
	}
	var intYear=eval(strValue[0]);
	var intMonth=eval(strValue[1]);
	var intDay=eval(strValue[2]);
	if(!isLegalDate(intYear,intMonth,intDay))
	{
		return false;
	}
	return true;
}

/**
 * 得到当前的系统时间：
 * splitOp 为分割符，Example：
 * splitOp='-' 则日期格式为 年-月-日
 * splitOp='/' 则日期格式为 年/月/日
 * splitOp如果为空，则默认是：'-'
 */
function getCurrentDate(splitOp)
{
   var CurrentDate = "";
	var strURL = "../common/jsp/CurrentDate.jsp";
	var strPara = "CurFlag=CurDate";
	Request = new ActiveXObject("Microsoft.XMLHTTP");
	Request.open("POST",strURL, false);
	Request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	Request.send(strPara);
	try
	{
		CurrentDate = Request.responseText;
	}
	catch(e1)
	{
		alert("取服务器时间超时，请重试！");
		return "";
	}
  if(CurrentDate==null || CurrentDate=='')
  {
    alert("取服务器时间超时，请重试！");
    return "";
  }
  if(splitOp==null || trim(splitOp)=='')
  {
    return CurrentDate;
  }
  CurrentDate = CurrentDate.replace("-",splitOp);
  CurrentDate = CurrentDate.replace("-",splitOp);
  return CurrentDate;
}

/**
 * 得到当前的系统时间：
 * splitOp 为分割符，Example：
 * splitOp=':' 则日期格式为 时：分：秒
 * splitOp='/' 则日期格式为 时/分/秒
 * splitOp如果为空，则默认是：'：'
 */
function getCurrentTime(splitOp)
{
	var CurrentTime = "";
	var strURL = "../common/jsp/CurrentDate.jsp";
	var strPara = "CurFlag=CurTime";
	Request = new ActiveXObject("Microsoft.XMLHTTP");
	Request.open("POST",strURL, false);
	Request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	Request.send(strPara);
	try
	{
		CurrentTime = Request.responseText;
	}
	catch(e1)
	{
		alert("取服务器时间超时，请重试！");
		return "";
	}
  if(CurrentTime==null || CurrentTime=='')
  {
    alert("取服务器时间超时，请重试！");
    return "";
  }
  if(splitOp==null || trim(splitOp)=='')
  {
    return CurrentTime;
  }
  CurrentTime = CurrentTime.replace(":",splitOp);
  CurrentTime = CurrentTime.replace(":",splitOp);
  return CurrentTime;
}

/**
 * 获取字符串的部分子串，该函数得到c_Str中的第c_i个以c_Split分割的字符串
 * Example: getStr("Minim|Hzm|Yt|", "2", "|") returns "Hzm"
 * @param c_Str 有分隔规则的字符串
 * @param c_i 取第几个分隔子串
 * @param c_Split 分隔符
 * @return 返回第c_i个分隔子串
 */
function getStr(c_Str , c_i ,c_Split)
{
	var t_Str1, t_Str2 , t_strOld;
	var i, i_Start, j_End;
	t_Str1 = c_Str;
	t_Str2 = c_Split;
	i = 0;
	try
	{
		while(i < c_i)
		{
			i_Start = t_Str1.indexOf(t_Str2,0);
			if(i_Start >= 0)
			{
				i = i + 1;
				t_strOld = t_Str1;
				t_Str1 = t_Str1.substring(i_Start+t_Str2.length,t_Str1.length);
			}
			else
			{
				if(i != c_i - 1)
				{
					t_Str1="";
				}
				break;
			}
		}
		if(i_Start >= 0)
		{
			t_Str1=t_strOld.substring(0,i_Start);
		}
	}
	catch(ex)
	{
		t_Str1="";
	}
	return t_Str1;
}

/**
 * 判断号码类型，如个人保单号，集体保单号（有在使用，但是此方法应该淘汰，或者进行改进）
 * 在“项目规范_约定”的“新旧号码对照.xls”中规定“号码的[12,13]位”为代码类型标志;
 * Example: getCodeType("abcdefghijk11asdfasdf") returns "11"
 * @param strCode 含代码字符串
 * @return 号码字符串
 */
function getCodeType(strCode)
{
	if((strCode == null) ||(strCode == ""))
	{
		return "00";
	}
	else
	{
		//在“项目规范_约定”的“新旧号码对照.xls”中规定“号码的[12,13]位”为代码类型标志
		return strCode.substring(11, 13);
	}
}

/**
 * 判断输入号码中包含的类型号码和指定类型号码是否一致（有在使用，但是此方法应该淘汰，或者进行改进）
 * 在“项目规范_约定”的“新旧号码对照.xls”中规定“号码的[12,13]位”为代码类型标志;
 * Example: judgeCodeType("abcdefghijk11asdfasdf", "11") returns ture
 * @param strCode 含代码字符串
 * @param strType 类型号码，参照“新旧号码对照.xls”
 * @return 布尔值（true--一致, false--不一致）
 */
function judgeCodeType(strCode, strType)
{
	if((strCode == null) ||(strCode == "") ||(strType == null) ||(strType == ""))
	{
		return false;
	}
	else
	{
		return(getCodeType(strCode).compareTo(strType) == 0);
	}
}

/**
 * 清空界面上的所有输入栏
 * Example: EmptyFormElements()
 */
function emptyFormElements()
{
	var formsNum = 0;	//窗口中的FORM数
	var elementsNum = 0;	//FORM中的元素数
	//遍历所有FORM
	for(formsNum=0; formsNum<window.document.forms.length; formsNum++)
	{
		//遍历FORM中的所有ELEMENT
		for(elementsNum=0; elementsNum<window.document.forms[formsNum].elements.length; elementsNum++)
		{
			if(window.document.forms[formsNum].elements[elementsNum].type == "text")
			{
				window.document.forms[formsNum].elements[elementsNum].value = "";
			}
		}
	}
}

/**
 * 将界面上的所有输入栏中为"undefined"清空
 * Example: EmptyFormElements()
 */
function emptyUndefined()
{
	var formsNum = 0;	//窗口中的FORM数
	var elementsNum = 0;	//FORM中的元素数
	//遍历所有FORM
	for(formsNum=0; formsNum<window.document.forms.length; formsNum++)
	{
		//遍历FORM中的所有ELEMENT
		for(elementsNum=0; elementsNum<window.document.forms[formsNum].elements.length; elementsNum++)
		{
			if((window.document.forms[formsNum].elements[elementsNum].value == "undefined" || window.document.forms[formsNum].elements[elementsNum].value == "null") && (window.document.forms[formsNum].elements[elementsNum].type == "text" || window.document.forms[formsNum].elements[elementsNum].type == "textarea"))
			{
				window.document.forms[formsNum].elements[elementsNum].value = "";
			}
		}
	}
}

/**
 * 使用一维数组中存放的索引来过滤二维数组
 * Example: chooseArray({{1，2}，{3，4}}, {0}) returns {{1}，{3}}
 * @param dataArray 存放数据的二维数组
 * @param filterArray 存放有索引的一维数组
 * @return 按一维数组中的索引过滤过的二维数组
 */
function chooseArray(dataArray, filterArray)
{
	var arrResult = new Array();
	var recordNum, filterNum;
	try
	{
		for(recordNum=0; recordNum<dataArray.length; recordNum++)
		{
			arrResult[recordNum] = new Array();
			for(filterNum=0; filterNum<filterArray.length; filterNum++)
			{
				arrResult[recordNum].push(dataArray[recordNum][filterArray[filterNum]]);
			}
		}
	}
	catch(ex)
	{
		alert("chooseArray处理出现错误！");
	}
	return arrResult;
}

/**
 * 把js文件中的字符转换成特殊字符
 */
function Conversion(strIn)
{
	var strOut;
	strOut=replace(strIn,"@@Enter","\r\n");
	strIn=strOut;
	strOut=replace(strIn,"@@DouQuot","\"");
	strIn=strOut;
	strOut=replace(strIn,"@@SinQuot","\'");
	return strOut;
}

/**
 * 根据代码选择的代码查找并显示名称
 */
function showCodeName()
{
	var formsNum = 0;	//窗口中的FORM数
	var elementsNum = 0;	//FORM中的元素数
	var strEvent = "";	//保存onDoubleClick事件代码
	var urlStr = "";
	var sFeatures = "";
	var strCode = "";	//代码选择
	var strQueryResult = "";	//代码选择的查询结果集
	var arrCode = null;	//拆分数组
	var strCodeValue = "";	//代码值
	var cacheFlag = false;	//内存中有数据标志
	var strCodeSelect = "";
	//寻找主窗口
	var win = searchMainWindow(this);
	if(win == false)
	{
		win = this;
	}
	//遍历所有FORM
	var tForCount = window.document.forms.length;
	for(formsNum=0; formsNum<tForCount; formsNum++)
	{
		//遍历FORM中的所有ELEMENT
		var tEleCount = window.document.forms[formsNum].elements.length;
		for(elementsNum=0; elementsNum<tEleCount; elementsNum++)
		{
			//寻找代码选择元素
			if(window.document.forms[formsNum].elements[elementsNum].className == "codeno")
			{
				//取出代码值
				strCodeValue = window.document.forms[formsNum].elements[elementsNum].value;
				//空值则不处理
				if(strCodeValue == "") continue;
				//虚拟数据源处理
				if(window.document.forms[formsNum].elements[elementsNum].CodeData != null)
				{
					strQueryResult = window.document.forms[formsNum].elements[elementsNum].CodeData;
				}
				else
				{
					//从后台取数据
					//取出CODESELECT代码
					strEvent = window.document.forms[formsNum].elements[elementsNum].ondblclick;
					strCode = new String(strEvent);
					strCode = strCode.substring(strCode.indexOf("showCodeList") + 14);
					strCode = strCode.substring(0, strCode.indexOf("'"));
					//如果内存中有数据，从内存中取数据
					if(win.parent.VD.gVCode.getVar(strCode))
					{
						arrCode = win.parent.VD.gVCode.getVar(strCode);
						cacheFlag = true;
					}
					else
					{
						if(strCode=="AgentCode"||strCode=="OccupationCode9")
						{
							//由于代理人数据和职业类别数据的数据量较大，无条件遍历查询时会严重影响汉化显示速度
							//特对他们的汉化查询进行了单独处理（有条件单条查询，结果不会再缓存）
							urlStr = "../common/jsp/CodeQueryXML.jsp?codeType=" + strCode+"&codeField="+strCode+"&codeConditon="+strCodeValue;
						}
						else
						{
							urlStr = "../common/jsp/CodeQueryXML.jsp?codeType=" + strCode;
						}
						Request = new ActiveXObject("Microsoft.XMLHTTP");
						Request.open("GET", urlStr, false);
						Request.send(null);
						try
						{
							strQueryResult = Request.responseText.trim();
						}
						catch(e1)
						{
							alert("代码汉化报错："+e1.message);
						}
					}
				}
				//拆分成数组
				try
				{
					if(!cacheFlag)
					{
						try
						{
							arrCode = decodeEasyQueryResult(strQueryResult,0,1);
						}
						catch(ex)
						{
							alert("页面缺少引用EasyQueryVer3.js");
						}
						strCodeSelect = "";
						var arr2 = new Array();
						var tArrLength = arrCode.length;
						for(i=0; i<tArrLength; i++)
						{
							if(i%100==0)
							{
								arr2.push(strCodeSelect);
								strCodeSelect = "";
							}
							strCodeSelect = strCodeSelect + "<option value=" + arrCode[i][0] + ">" + arrCode[i][0] + "-" + arrCode[i][1] + "</option>";
						}
						arr2.push(strCodeSelect);
						strCodeSelect = "";
						tArrLength = arr2.length;
						for(i=0; i<tArrLength; i++)
						{
							strCodeSelect =  strCodeSelect + arr2[i];
						}
						if(strCode=="AgentCode" ||strCode=="OccupationType9")
						{
							//由于代理人数据和职业类别数据的数据量较大，无条件遍历查询时会严重影响汉化显示速度
							//特对他们的汉化查询进行了单独处理（有条件单条查询，结果不会再缓存）
						}
						else
						{
							//将拆分好的数据放到内存中
							win.parent.VD.gVCode.addArrVar(strCode, "", arrCode);
							//无论是否有数据从服务器端得到,都设置该变量
							win.parent.VD.gVCode.addVar(strCode+"Select","",strCodeSelect);
						}
					}
					cacheFlag = false;
					tArrLength = arrCode.length;
					for(i=0; i<tArrLength; i++)
					{
						if(strCodeValue == arrCode[i][0])
						{
							window.document.forms[formsNum].elements[elementsNum].value = arrCode[i][0];
							window.document.forms[formsNum].elements[elementsNum+1].value = arrCode[i][1];
							break;
						}
					}
				}
				catch(ex)
				{}
			}
			//显示title
			if(window.document.forms[formsNum].elements[elementsNum].type == "text")
			{
				window.document.forms[formsNum].elements[elementsNum].title = window.document.forms[formsNum].elements[elementsNum].value;
			}
		}
	}
}

/**
 * 根据代码选择的代码查找并显示名称，显示指定的一个
 * strCode - 代码选择的代码
 * showObjCode - 代码存放的界面对象
 * showObjName - 要显示名称的界面对象
 */
function showOneCodeName(strCode, showObjCode, showObjName)
{
	if(showObjCode.value!=''&&showObjCode.value!=null)
	showObjName.value=easyExecSql("select codename from ldcode where codetype = '"+strCode+"' and code = '"+showObjCode.value+"'");
}

/**
 * 以年龄和性别校验身份证号的函数
 * 参数，输入的证件号码，出生日期，性别
 * 返回  布尔值
 */
function chkIdNo(iIdNo, iBirthday ,iSex)
{
	var tmpStr="";
	var idDate="";
	var tmpInt=0;
	var strReturn = "";
	iIdNo = trim(iIdNo);
	iBirthday = trim(iBirthday);
	iSex = trim(iSex);
	if((iIdNo.length!=15) &&(iIdNo.length!=18))
	{
		strReturn = "输入的身份证号位数错误";
		return strReturn;
	}
	if(!(isDate(iBirthday)))
	{
		strReturn = "输入的日期格式错误";
		return strReturn;
	}
	//转换日期格式到yy－mm－dd，by Minim
	var arrDate = iBirthday.split("-");
	if(arrDate[1].length == 1) arrDate[1] = "0" + arrDate[1];
	if(arrDate[2].length == 1) arrDate[2] = "0" + arrDate[2];
	iBirthday = arrDate[0] + "-" + arrDate[1] + "-" + arrDate[2];
	if(iSex!="0" && iSex!="1")
	{
		strReturn = "输入的性别不明确";
		return strReturn;
	}
	if(iIdNo.length==15)
	{
		tmpStr=iIdNo.substring(6,12);
		tmpStr= "19" + tmpStr;
		tmpStr= tmpStr.substring(0,4) + "-" + tmpStr.substring(4,6) + "-" + tmpStr.substring(6)
		if(iBirthday == tmpStr)
		{
			if(iSex=="0")
			{
				tmpInt = parseInt(iIdNo.substring(14));
				tmpInt = tmpInt % 2
				if(tmpInt==0)
				{
					strReturn = "输入的性别与身份证号的信息不一致";
					return strReturn;
				}
			}
			else
			{
				tmpInt = parseInt(iIdNo.substring(14));
				tmpInt = tmpInt % 2
				if(tmpInt!=0)
				{
					strReturn = "输入的性别与身份证号的信息不一致";
					return strReturn;
				}
			}
		}
		else
		{
			strReturn = "输入的生日与身份证号的信息不一致";
			return strReturn;
		}
		return strReturn;
	}
	if(iIdNo.length==18)
	{
		tmpStr=iIdNo.substring(6,14);
		tmpStr= tmpStr.substring(0,4) + "-" + tmpStr.substring(4,6) + "-" + tmpStr.substring(6)
		if(iBirthday == tmpStr)
		{
			if(iSex=="0")
			{
				tmpInt = parseInt(iIdNo.substring(16,17));
				tmpInt = tmpInt % 2
				if(tmpInt==0)
				{
					strReturn = "输入的性别与身份证号的信息不一致";
					return strReturn;
				}
			}
			else
			{
				tmpInt = parseInt(iIdNo.substring(16,17));
				tmpInt = tmpInt % 2
				if(tmpInt!=0)
				{
					strReturn = "输入的性别与身份证号的信息不一致";
					return strReturn;
				}
			}
		}
		else
		{
			strReturn = "输入的生日与身份证号的信息不一致";
			return strReturn;
		}
		return strReturn;
	}
}

/**
 * 严格校验身份证号码
 * 兰军 2005-7-2 17:05
 * 公民身份号码是特征组合码，
 * 由十七位数字本体码和一位数字校验码组成。
 * 排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，
 * 三位数字顺序码和一位数字校验码。顺序码的奇数分给男性，偶数分给女性。
 * 校验码是根据前面十七位数字码，按照ISO7064:1983.MOD11-2校验码计算出来的检验码。
 */
function checkIdCard(idCard)
{
	var SystemDate=new Date();
	var year=SystemDate.getFullYear();
	var month=SystemDate.getMonth()+1;
	var day=SystemDate.getDate();
	var yyyy; //年
	var mm; //月
	var dd; //日
	var birthday; //生日
	var sex; //性别
	var id=idCard;
	var id_length=id.length;
	if (id_length==0)
	{
		alert("请输入身份证号码!");
		return false;
	}
	if (id_length!=15 && id_length!=18)
	{
		alert("身份证号长度应为15位或18位！");
		return false;
	}
	if (id_length==15)
	{
		for(var i =0 ;i<id_length;i++)
		{
			if(isNaN(idCard.charAt(i)))
			{
				alert("15位身份证号中不能有字符！");
				return false;
			}
		}
		yyyy="19"+id.substring(6,8);
		mm=id.substring(8,10);
		dd=id.substring(10,12);
		if (mm>12 || mm<=0)
		{
			alert("身份证号月份非法！");
			return false;
		}
		if (dd>31 || dd<=0)
		{
			alert("身份证号日期非法！");
			return false;
		}
		//4,6,9,11月份日期不能超过30
		if((mm==4||mm==6||mm==9||mm==11)&&(dd>30))
		{
			alert("身份证号日期非法！");
			return false;
		}
		//判断2月份
		if(mm==2)
		{
			if(LeapYear(yyyy))
			{
				if(dd>29)
				{
					alert("身份证号日期非法！");
					return false;
				}
			}
			else
			{
				if(dd>28)
				{
					alert("身份证号日期非法！");
					return false;
				}
			}
		}
	}
	else
	{
		for(var i =0 ;i<id_length-1;i++)
		{
			if(isNaN(idCard.charAt(i)))
			{
				alert("身份证号中前17位中不能有字符！");
				return false;
			}
		}
		if(isNaN(idCard.charAt(17))&& idCard.charAt(17) !="X" && idCard.charAt(17) !="x" )
		{
			alert("身份证校验错误，请认真检查！");
			return false;
		}
		if (idCard.indexOf("X") > 0 && idCard.indexOf("X")!=17 || idCard.indexOf("x")>0 && idCard.indexOf("x")!=17)
		{
			alert("身份证中\"X\"输入位置不正确！");
			return false;
		}
		yyyy=id.substring(6,10);
		if (yyyy>year || yyyy<1900)
		{
			alert("身份证号年度非法！");
			return false;
		}
		mm=id.substring(10,12);
		if (mm>12 || mm<=0)
		{
			alert("身份证号月份非法！");
			return false;
		}
		if(yyyy==year&&mm>month)
		{
			alert("身份证号月份非法！");
			return false;
		}
		dd=id.substring(12,14);
		if (dd>31 || dd<=0)
		{
			alert("身份证号日期非法！");
			return false;
		}
		//4,6,9,11月份日期不能超过30
		if((mm==4||mm==6||mm==9||mm==11)&&(dd>30))
		{
			alert("身份证号日期非法！");
			return false;
		}
		//判断2月份
		if(mm==2)
		{
			if(LeapYear(yyyy))
			{
				if(dd>29)
				{
					alert("身份证号日期非法！");
					return false;
				}
			}
			else
			{
				if(dd>28)
				{
					alert("身份证号日期非法！");
					return false;
				}
			}
		}
		if(yyyy==year&&mm==month&&dd>day)
		{
			alert("身份证号日期非法！");
			return false;
		}
		if (id.charAt(17)=="x" || id.charAt(17)=="X")
		{
			if ("x"!=GetVerifyBit(id) && "X"!=GetVerifyBit(id))
			{
				alert("身份证校验错误，请认真检查！");
				return false;
			}
		}
		else
		{
			if (id.charAt(17)!=GetVerifyBit(id))
			{
				alert("身份证校验错误，请认真检查！");
				return false;
			}
		}
	}
	return true;
}

/**
 * 计算身份证校验码
 * 兰军 2005-7-2 17:06
 * 原理:
 * ∑(a[i]*W[i]) mod 11 ( i = 2, 3, ..., 18 )(1)
 * "*" 表示乘号
 * i--------表示身份证号码每一位的序号，从右至左，最左侧为18，最右侧为1。
 * a[i]-----表示身份证号码第 i 位上的号码
 * W[i]-----表示第 i 位上的权值 W[i] = 2^(i-1) mod 11
 * 计算公式 (1) 令结果为 R
 * 根据下表找出 R 对应的校验码即为要求身份证号码的校验码C。
 * R 0 1 2 3 4 5 6 7 8 9 10
 * C 1 0 X 9 8 7 6 5 4 3 2
 * X 就是 10，罗马数字中的 10 就是 X
 * 15位转18位中,计算校验位即最后一位
 */
function GetVerifyBit(id)
{
	var result;
	var nNum=eval(id.charAt(0)*7+id.charAt(1)*9+id.charAt(2)*10+id.charAt(3)*5+id.charAt(4)*8+id.charAt(5)*4+id.charAt(6)*2+id.charAt(7)*1+id.charAt(8)*6+id.charAt(9)*3+id.charAt(10)*7+id.charAt(11)*9+id.charAt(12)*10+id.charAt(13)*5+id.charAt(14)*8+id.charAt(15)*4+id.charAt(16)*2);
	nNum=nNum%11;
	switch (nNum)
	{
		case 0 :
			result="1";
			break;
		case 1 :
			result="0";
			break;
		case 2 :
			result="X";
			break;
		case 3 :
			result="9";
			break;
		case 4 :
			result="8";
			break;
		case 5 :
			result="7";
			break;
		case 6 :
			result="6";
			break;
		case 7 :
			result="5";
			break;
		case 8 :
			result="4";
			break;
		case 9 :
			result="3";
			break;
		case 10 :
			result="2";
			break;
	}
	return result;
}

/**
 * 判断是否闰年
 * 参数intYear代表年份的值
 * returntrue:是闰年false:不是闰年
 */
function LeapYear(intYear)
{
	if(intYear%100==0)
	{
		if(intYear%400==0)
		{
			return true;
		}
	}
	else
	{
		if((intYear%4)==0)
		{
			return true;
		}
	}
	return false;
}

/**
 * 通过身份证号的得到出生日期函数
 * 参数，输入的证件号码
 * 返回  出生日期
 */
function getBirthdatByIdNo(iIdNo)
{
	var tmpStr="";
	var idDate="";
	var tmpInt=0;
	var strReturn = "";
	iIdNo = trim(iIdNo);
	if((iIdNo.length!=15) &&(iIdNo.length!=18))
	{
		strReturn = "输入的身份证号位数错误";
		return strReturn;
	}
	if(iIdNo.length==15)
	{
		tmpStr=iIdNo.substring(6,12);
		tmpStr= "19" + tmpStr;
		tmpStr= tmpStr.substring(0,4) + "-" + tmpStr.substring(4,6) + "-" + tmpStr.substring(6)
		return tmpStr;
	}
	else
	{
		tmpStr=iIdNo.substring(6,14);
		tmpStr= tmpStr.substring(0,4) + "-" + tmpStr.substring(4,6) + "-" + tmpStr.substring(6)
		return tmpStr;
	}
}

/**
 * 计算投保人年龄，判定是否异常
 * 正常返回true，异常返回false
 */
function CheckAge(birthday)
{
	var i = calAge(birthday);
	if (i>150 ||i<0)
	{
		return false;
	}
	else
	{
		return true;
	}
}

/**
 * 用出生日期计算年龄，目前不支持yyyymmdd模式
 * 参数，出生日期yy－mm－dd
 * 返回  年龄
 */
function calAge(birthday)
{
	var arrBirthday = birthday.split("-");
	if (arrBirthday.length == 1)
	{
		if(arrBirthday[0].length != 8)
		{
			return "";
		}
		var arrBirthdays = new Array();
		arrBirthdays[0] = arrBirthday[0].substring(0, 4);
		arrBirthdays[1] = arrBirthday[0].substring(4, 6);
		arrBirthdays[2] = arrBirthday[0].substring(6, 8);
		var today = new Date();
		var arrToday = new Array();
		arrToday[0] = today.getFullYear();
		arrToday[1] = today.getMonth() + 1;
		arrToday[2] = today.getDate();
		var age = arrToday[0] - arrBirthdays[0] - 1;
		//当前月大于出生月
		if(arrToday[1] > arrBirthdays[1])
		{
			age = age + 1;
			return age;
		}
		else if(arrToday[1] < arrBirthdays[1])
		{
			//当前月小于出生月
			return age;
		}
		else if(arrToday[2] >= arrBirthdays[2])
		{
			//当前月等于出生月的时候，看出生日
			age = age + 1;
			return age;
		}
		else
		{
			return age;
		}
	}
	else
	{
		if(arrBirthday[1].length == 1)
		{
			arrBirthday[1] = "0" + arrBirthday[1];
		}
		if(arrBirthday[2].length == 1)
		{
			arrBirthday[2] = "0" + arrBirthday[2];
		}
		var today = new Date();
		var arrToday = new Array();
		arrToday[0] = today.getFullYear();
		arrToday[1] = today.getMonth() + 1;
		arrToday[2] = today.getDate();
		var age = arrToday[0] - arrBirthday[0] - 1;
		//当前月大于出生月
		if(arrToday[1] > arrBirthday[1])
		{
			age = age + 1;
			return age;
		}
		else if(arrToday[1] < arrBirthday[1])
		{
			//当前月小于出生月
			return age;
		}
		else if(arrToday[2] >= arrBirthday[2])
		{
			//当前月等于出生月的时候，看出生日
			age = age + 1;
			return age;
		}
		else
		{
			return age;
		}
	}
}

/**
 * 用出生日期和保单申请日期计算年龄
 * 参数，出生日期yy－mm－dd，yyyymmdd
 * 返回  年龄
 */
function calAgeNew(birthday,applyday)
{
	var arrBirthday = birthday.split("-");
	var arrApplyday = applyday.split("-");
	if (arrBirthday.length == 1&&arrApplyday.length == 1)
	{
		if(arrBirthday[0].length != 8||arrApplyday[0].length!= 8)
		{
			return "";
		}
		var arrBirthdays = new Array();
		arrBirthdays[0] = arrBirthday[0].substring(0, 4);
		arrBirthdays[1] = arrBirthday[0].substring(4, 6);
		arrBirthdays[2] = arrBirthday[0].substring(6, 8);
		var arrToday = new Array();
		arrToday[0] = arrApplyday[0].substring(0, 4);
		arrToday[1] = arrApplyday[0].substring(4, 6);
		arrToday[2] = arrApplyday[0].substring(6, 8);
		var age = arrToday[0] - arrBirthdays[0] - 1;
		//当前月大于出生月
		if(arrToday[1] > arrBirthdays[1])
		{
			age = age + 1;
			return age;
		}
		else if(arrToday[1] < arrBirthdays[1])
		{
			//当前月小于出生月
			return age;
		}
		else if(arrToday[2] >= arrBirthdays[2])
		{
			//当前月等于出生月的时候，看出生日
			age = age + 1;
			return age;
		}
		else
		{
			return age;
		}
	}
	else if(arrBirthday.length == 1&&arrApplyday.length != 1)
	{
		if(arrBirthday[0].length != 8)
		{
			return "";
		}
		var arrBirthdays = new Array();
		arrBirthdays[0] = arrBirthday[0].substring(0, 4);
		arrBirthdays[1] = arrBirthday[0].substring(4, 6);
		arrBirthdays[2] = arrBirthday[0].substring(6, 8);
		if(arrApplyday[1].length == 1)
		{
			arrApplyday[1] = "0" + arrApplyday[1];
		}
		if(arrApplyday[2].length == 1)
		{
			arrApplyday[2] = "0" + arrApplyday[2];
		}
		var arrToday = new Array();
		arrToday[0] = arrApplyday[0];
		arrToday[1] = arrApplyday[1];
		arrToday[2] = arrApplyday[2];
		var age = arrToday[0] - arrBirthdays[0] - 1;
		//当前月大于出生月
		if(arrToday[1] > arrBirthdays[1])
		{
			age = age + 1;
			return age;
		}
		else if(arrToday[1] < arrBirthdays[1])
		{
			//当前月小于出生月
			return age;
		}
		else if(arrToday[2] >= arrBirthdays[2])
		{
			//当前月等于出生月的时候，看出生日
			age = age + 1;
			return age;
		}
		else
		{
			return age;
		}
	}
	else if(arrBirthday.length != 1&&arrApplyday.length == 1)
	{
		if(arrApplyday[0].length != 8)
		{
			return "";
		}
		if(arrBirthday[1].length == 1)
		{
			arrBirthday[1] = "0" + arrBirthday[1];
		}
		if(arrBirthday[2].length == 1)
		{
			arrBirthday[2] = "0" + arrBirthday[2];
		}
		var arrToday = new Array();
		arrToday[0] = arrApplyday[0].substring(0, 4);
		arrToday[1] = arrApplyday[0].substring(4, 6);
		arrToday[2] = arrApplyday[0].substring(6, 8);
		var age = arrToday[0] - arrBirthday[0] - 1;
		//当前月大于出生月
		if(arrToday[1] > arrBirthday[1])
		{
			age = age + 1;
			return age;
		}
		else if(arrToday[1] < arrBirthday[1])
		{
			//当前月小于出生月
			return age;
		}
		else if(arrToday[2] >= arrBirthday[2])
		{
			//当前月等于出生月的时候，看出生日
			age = age + 1;
			return age;
		}
		else
		{
			return age;
		}
	}
	else if(arrBirthday.length != 1&&arrApplyday.length != 1)
	{
		if(arrBirthday[1].length == 1)
		{
			arrBirthday[1] = "0" + arrBirthday[1];
		}
		if(arrBirthday[2].length == 1)
		{
			arrBirthday[2] = "0" + arrBirthday[2];
		}
		if(arrApplyday[1].length == 1)
		{
			arrApplyday[1] = "0" + arrApplyday[1];
		}
		if(arrApplyday[2].length == 1)
		{
			arrApplyday[2] = "0" + arrApplyday[2];
		}
		var arrToday = new Array();
		arrToday[0] = arrApplyday[0];
		arrToday[1] = arrApplyday[1];
		arrToday[2] = arrApplyday[2];
		var age = arrToday[0] - arrBirthday[0] - 1;
		//当前月大于出生月
		if(arrToday[1] > arrBirthday[1])
		{
			age = age + 1;
			return age;
		}
		else if(arrToday[1] < arrBirthday[1])
		{
			//当前月小于出生月
			return age;
		}
		else if(arrToday[2] >= arrBirthday[2])
		{
			//当前月等于出生月的时候，看出生日
			age = age + 1;
			return age;
		}
		else
		{
			return age;
		}
	}
}

/**
 * 搜寻主窗口，用于CodeSelect缓存数据
 */
function searchMainWindow(win)
{
	if(typeof(win) != "object")
	{
		return false;
	}
	if(win.top.name == "Lis")
	{
		return win.top;
	}
	return searchMainWindow(win.top.opener);
}

//校验暂交费号发放
function verifyTempfeeNo(tempfeeNo)
{
	//去系统表LDSysVar中查询Sysvar字段名为checkNewType的纪录，判断是否需要去查询单证状态表
	var tSql = "select Sysvarvalue from LDSysVar where Sysvar='CheckNewType'";
	var tResult = easyExecSql(tSql, 1, 0, 1);
	//为了校验，设置ldsysvar变量为3
	if(tResult=="1" || tResult=="3")
	{
		//如果查到该纪录，表明需要查询单证状态表
		//去单证状态表里查询该号码是否有效,暂交费收据号
		var strSql = "select CertifyCode from LZCardTrack where Startno<='"+tempfeeNo+"' and Endno>='"+tempfeeNo+"' and Receivecom = 'D"+fm.all('AgentCode').value+"' and StateFlag='0' and CertifyCode in(select CertifyCode from LMCertifyDes where CertifyClass2 = '0')";
		var strResult=easyQueryVer3(strSql, 1, 0, 1);
		if(!strResult)
		{
			alert("该单证（单证编码为："+tempfeeNo+" ）没有发放给该代理人（"+fm.all('AgentCode').value+"）!");
			return false;
		}
	}
	return true;
}

//校验印刷号发放
function verifyPrtNo(prtNo)
{
	//去系统表LDSysVar中查询Sysvar字段名为checkNewType的纪录，判断是否需要去查询单证状态表
	var tSql = "select Sysvarvalue from LDSysVar where Sysvar='CheckNewType'";
	var tResult = easyExecSql(tSql, 1, 0, 1);
	if(tResult=="2" || tResult=="3")
	{
		//如果查到该纪录，表明需要查询单证状态表
		//去单证状态表里查询该号码是否有效,投保单印刷号码
		var strSql = "select CertifyCode from LZCardTrack where Startno<='"+prtNo+"' and Endno>='"+prtNo+"' and Receivecom = 'D"+fm.all('AgentCode').value+"' and StateFlag='0'";
		var strResult=easyQueryVer3(strSql, 1, 0, 1);
		if(!strResult)
		{
			alert("该单证（单证编码为："+prtNo+" ）没有发放给该代理人（"+fm.all('AgentCode').value+"）!");
			return false;
		}
	}
	return true;
}

/**
 * 显示元素的Title信息（解决信息较多无法直接在界面元素中获得所有信息的问题）
 */
function showTitle()
{
	var formsNum = 0;	//窗口中的FORM数
	var elementsNum = 0;	//FORM中的元素数
	var strEvent = "";	//保存onDoubleClick事件代码
	var urlStr = "";
	var sFeatures = "";
	var strCode = "";	//代码选择
	var strQueryResult = "";	//代码选择的查询结果集
	var arrCode = null;	//拆分数组
	var strCodeValue = "";	//代码值
	var cacheFlag = false;	//内存中有数据标志
	var strCodeSelect = "";
	//寻找主窗口
	var win = searchMainWindow(this);
	if (win == false) win = this;
	//遍历所有FORM
	var tFormCount = window.document.forms.length;
	for(formsNum=0; formsNum<tFormCount; formsNum++)
	{
		//遍历FORM中的所有ELEMENT
		var tEleCount = window.document.forms[formsNum].elements.length;
		for(elementsNum=0; elementsNum<tEleCount; elementsNum++)
		{
			//寻找代码选择元素
			if(window.document.forms[formsNum].elements[elementsNum].className == "code")
			{
				//取出代码值
				strCodeValue = window.document.forms[formsNum].elements[elementsNum].value;
				//空值则不处理
				if(strCodeValue == "") continue;
			}
			//显示title
			if(window.document.forms[formsNum].elements[elementsNum].type == "text")
			{
				window.document.forms[formsNum].elements[elementsNum].title = window.document.forms[formsNum].elements[elementsNum].value;
			}
		}
	}
}

/**
 * 窗口聚焦函数
 * 参数：show：要显示的窗体
 */
function myonfocus(show)
{
	if(show!=null)
	{
		try
		{
			show.focus();
		}
		catch(ex)
		{
			show=null;
		}
	}
}

//初始化控件类型
function initElementtype(){
	var tFormCount = document.forms.length;
	for(var fm=0;fm<tFormCount;fm++)
	{
		var theElements=document.forms[fm].elements;
		for(var i=0;i<theElements.length;i++)
		{
			if(theElements[i].elementtype && theElements[i].elementtype.indexOf("nacessary")!=-1)
			{
				theElements[i].insertAdjacentHTML("afterEnd","<font id='"+theElements[i].name+"n' color=red>&nbsp;*</font>");
			}
			if(theElements[i].elementtype && theElements[i].elementtype.indexOf("misty")!=-1)
			{
				theElements[i].insertAdjacentHTML("afterEnd","<font color=red>&nbsp;?</font>");
			}
			if(theElements[i].readOnly)
			{
				theElements[i].tabIndex=-1;
			}
		}
	}
}

//根据身份证号取得性别 update 2004-12-09 wzw
function getSexByIDNo(iIdNo)
{
	var tSex="";
	var strReturn="";
	if((iIdNo.length!=15) &&(iIdNo.length!=18))
	{
		strReturn = "输入的身份证号位数错误";
		return strReturn;
	}
	var tmpInt=0;
	if(iIdNo.length==15)
	{
		tmpInt = parseInt(iIdNo.substring(14));
	}
	if(iIdNo.length==18)
	{
		tmpInt = parseInt(iIdNo.substring(16,17));
	}
	tmpInt = tmpInt % 2;
	if(tmpInt==0)
	{
		tSex="1";
	}
	else
	{
		tSex="0";
	}
	return tSex;
}

/**
 * 根据设置开启新窗口
 */
function OpenWindowNew(strurl,windowname,opentype,width,height)
{
	if(opentype=="left")
	{
		window.open(strurl,windowname,'width='+screen.availWidth+',height='+screen.availHeight+',top=0,left=0,toolbar=0,location=0,directories=0,menubar=0,scrollbars=1,resizable=1,status=0');
	}
	if(width=='undefined' || width==null )
	{
		width=800
	}
	if(height=='undefined'||  height==null)
	{
		height=500
	}
	if(opentype=="middle")
	{
		var iWidth=width; //模态窗口宽度
		var iHeight=height;//模态窗口高度
		var iTop=(window.screen.height-iHeight)/2;
		var iLeft=(window.screen.width-iWidth)/2;
		window.open(strurl,windowname,'width='+iWidth+',height='+iHeight+',top='+iTop+',left='+iLeft+',toolbar=0,location=0,directories=0,menubar=0,scrollbars=1,resizable=1,status=0');
	}
}

/**
 * 输入日期格式为yyyymmdd
 * 输出日期格式为yyyy-mm-dd
 */
function modifydate(strDate)
{
	var stadate;
	if ( strDate!='')
	{
		if (!isDate(strDate))
		{
			var year=strDate.substring(0,4);
			var month=strDate.substring(4,6);
			var day=strDate.substring(6);
			stadate=year+'-'+month+'-'+day;
		}
		else
		{
			stadate=strDate;
		}
		return stadate;
	}
}
/**
 * 输入日期格式为yyyy/mm/dd
 * 输出日期格式为yyyy-mm-dd
 */
function modifydate2(strDate)
{
	var stadate;
	if ( strDate!='')
	{
		if (!isDate(strDate))
		{
			var arrDateTemp = strDate.split('/');
			if(arrDateTemp[1].length==1)
			arrDateTemp[1]="0"+arrDateTemp[1];
			if(arrDateTemp[2].length==1)
			arrDateTemp[2]="0"+arrDateTemp[2];
			stadate = arrDateTemp[0]+"-"+arrDateTemp[1]+"-"+arrDateTemp[2];

		}
		else
		{
			stadate=strDate;
		}
		return stadate;
	}
}

//取得电话号码
function getfullphone(phoneno)
{
	var arrphone = new Array();
	arrphone[0]="86";
	arrphone[1]="";
	arrphone[2]="";
	arrphone[3]="";
	if(phoneno!="")
	{
		arrphone[1]=phoneno.substring(0,phoneno.indexOf("-"));
		if(phoneno.indexOf("-")==phoneno.lastIndexOf("-"))
		{
			arrphone[2]=phoneno.substring(phoneno.indexOf("-")+1,phoneno.length);
			arrphone[3]="";
		}
		else
		{
			arrphone[2]=phoneno.substring(phoneno.indexOf("-")+1,phoneno.lastIndexOf("-"));
			arrphone[3]=phoneno.substring(phoneno.lastIndexOf("-")+1,phoneno.length);
		}
	}
	return arrphone;
}

/*
 * 下面的代码是更改页面title提示显示样式的代码
 * 默认的显示title时间太短不爽
 * update 2004-12-09 wzw
 */
ToolTipGlobal={id:0,getId:function(o){this.all[this.all.length]=o;return this.id++},all:[]};
function ToolTip(defaultOpacity,font,BGround,color,border,offsetOn,offsetOff)
{
	this.id = ToolTipGlobal.getId(this);
	this.defaultOpacity = defaultOpacity;
	this.opacity = defaultOpacity;
	this.font = font;
	this.BGround = BGround;	//title显示的背景颜色
	this.border = border;
	this.timerOn = null;
	this.timerOff = null;
	this.offsetOn = offsetOn;
	this.offsetOff = offsetOff;
	this.control = null;
	var o = this;
	window.attachEvent("onload",function(){ o.setup();});
}
ToolTip.prototype.fadeOn = function()
{
	window.clearTimeout(this.timerOff);
	this.timerOn = window.setTimeout("ToolTipGlobal.all["+this.id+"].fade(1)",this.offsetOn[1]);
}
ToolTip.prototype.fadeOff = function()
{
	window.clearTimeout(this.timerOn);
	this.timerOff = window.setTimeout("ToolTipGlobal.all["+this.id+"].fade(0)",this.offsetOff[1]);
}
ToolTip.prototype.setOpacity = function(x)
{
	this.opacity = x;
	this.control.style.filter = "progid:DXImageTransform.Microsoft.Alpha(opacity="+ x +") progid:DXImageTransform.Microsoft.Shadow(color='#FF444444', Direction=135, Strength=3)";
}
ToolTip.prototype.fade = function(x)
{
	var o = this.control;
	var ox = this.opacity;
	if(x)
	{
		if(ox + this.offsetOn[0] <100)
		{
			this.setOpacity(ox+this.offsetOn[0]);
			this.fadeOn();
		}
		else
		{
			this.setOpacity(100);
		}
	}
	else
	{
		if(ox - this.offsetOff[0]>this.defaultOpacity)
		{
			this.setOpacity(ox - this.offsetOn[0]);
			this.fadeOff();
		}
		else
		{
			this.setOpacity(this.defaultOpacity);
			o.style.visibility = "hidden"
		}
	}
}
ToolTip.prototype.setup = function()
{
	var o = document.createElement("div");
	var oThis = this;
	with(o.style)
	{
		position = "absolute";
		top = "0px";
		left = "0px";
		font = this.font;
		zIndex = 99999;
		background = this.BGround;
		color = this.color;
		border = this.border;
		padding = "2px 4px";
		visibility = "hidden";
	}
	document.body.appendChild(o);
	this.control = o;
	document.attachEvent("onmouseover",function(){
		var e = window.event.srcElement;
		if(e!=null && e.title != "")
		{
			e.tip = e.title;
			e.title = "";
		}
		if(e!=null && typeof(e.tip) != "undefined" && e.tip != null)
		{
			o.innerHTML = e.tip;
			oThis.setOpacity(oThis.defaultOpacity);
			var x,y,docheight,docwidth,dh,dw;
			x = window.event.clientX + document.body.scrollLeft;	//触发事件所在横坐标
			//document.body.scrollLeft，滚动条的左移量
			y = window.event.clientY + document.body.scrollTop;	//触发事件所在纵坐标
			docheight = document.body.clientHeight;	//触发事件的所在区域高度
			docwidth  = document.body.clientWidth;	//触发事件的所在区域宽度
			dh =(o.offsetHeight + y) - docheight;
			dw =(o.offsetWidth + x)  - docwidth;
//			o.offsetWidth = e.offsetWidth;
			if(dw > 0)
			{
//				o.style.left =(x - o.offsetWidth) + document.body.scrollLeft - 5 ;
				o.style.left = x;
			}
			else
			{
//				o.style.left = x + document.body.scrollLeft + 10;
				o.style.left = x;
			}
			if(dh > 0)
			{
				o.style.top = y  - 5 ;
			}
			else
			{
				o.style.top  = y  + 10 ;
			}
//			o.style.width = e.offsetWidth;
			o.style.visibility = "visible";
			oThis.fadeOn();
		}
	});
	//鼠标移开目标的时候触发事件
	document.attachEvent("onmouseout",function(){
		var e = window.event.srcElement;
		if(e!=null && typeof(e.tip) != "undefined" && e.tip != null)
		{
//			oThis.fadeOff();
			//隐藏titile信息
			o.style.visibility = "hidden";
		}
	});
};
var tooltip = new ToolTip(20,"9pt Arial 宋体","#ffffdd","#000000","1px solid #000000",[8,20],[8,20]);

//延迟恢复按钮
//elementID-按钮的标志
//mills －延迟的时间
//lanjun 2006/09/02
function setEnable(elementID,mills)
{
	try
	{
		//alert(elementID);
		if(mills == null || mills == "")
		{
			mills = DELAY_MILLS;//默认2000ms
		}
	  setTimeout("enableButton('"+elementID+"')",mills);
  }
  catch(ex)
  {
  	//alert(ex.message);
  }
}

//恢复按钮
function enableButton(elementID)
{
	try
	{
		document.getElementById(elementID).disabled = false;
	}
	catch(ex)
	{
	}
}

function getChnMoney(value)
{
  var intFen,i;
  var strArr,strCheck,strFen,strDW,strNum,strBig,strNow;
  // 数据为空时返回"零"
  if(trim(value)=="")
  {
    return "零";
  }
  if (isNaN(value)) //数据非法时提示，并返回空串
  {
    strErr = "数据"+value+"非法！"
    alert(strErr);
    return "";
  }
  strCheck = value+".";
  strArr = strCheck.split(".");
  strCheck = strArr[0];
  if(strCheck.length>12) //数据大于等于一万亿时提示无法处理
  {
    strErr = "数据"+value+"过大，无法处理！"
    alert(strErr);
    return "";
  }
  try
  {
    i = 0;
    strBig = "";
    intFen = value*100; //转换为以分为单位的数值
    strFen = intFen.toString();
    strArr = strFen.split(".");
    strFen = strArr[0];
    intFen = strFen.length; //获取长度
    strArr = strFen.split(""); //将各个数值分解到数组内
    while(intFen!=0) //分解并转换
    {
      i = i+1;
      switch(i) //选择单位
      {
        case 1:strDW = "分";break;
        case 2:strDW = "角";break;
        case 3:strDW = "元";break;
        case 4:strDW = "拾";break;
        case 5:strDW = "佰";break;
        case 6:strDW = "仟";break;
        case 7:strDW = "万";break;
        case 8:strDW = "拾";break;
        case 9:strDW = "佰";break;
        case 10:strDW = "仟";break;
        case 11:strDW = "亿";break;
        case 12:strDW = "拾";break;
        case 13:strDW = "佰";break;
        case 14:strDW = "仟";break;
      }
      switch (strArr[intFen-1]) //选择数字
      {
        case "1":strNum = "壹";break;
        case "2":strNum = "贰";break;
        case "3":strNum = "叁";break;
        case "4":strNum = "肆";break;
        case "5":strNum = "伍";break;
        case "6":strNum = "陆";break;
        case "7":strNum = "柒";break;
        case "8":strNum = "捌";break;
        case "9":strNum = "玖";break;
        case "0":strNum = "零";break;
      }
  
      //处理特殊情况
      strNow = strBig.split("");
      //分为零时的情况
      if((i==1)&&(strArr[intFen-1]=="0"))
      strBig = "整";
      //角为零时的情况
      else if((i==2)&&(strArr[intFen-1]=="0"))
      {
        //角分同时为零时的情况
        if(strBig!="整")
        {
          strBig = "零"+strBig;
        }
      }
      //元为零的情况
      else if((i==3)&&(strArr[intFen-1]=="0"))
      {
        strBig = "元"+strBig;
      }
      //拾－仟中一位为零且其前一位（元以上）不为零的情况时补零
      else if((i<7)&&(i>3)&&(strArr[intFen-1]=="0")&&(strNow[0]!="零")&&(strNow[0]!="元"))
      {
        strBig = "零"+strBig;
      }
      //拾－仟中一位为零且其前一位（元以上）也为零的情况时跨过
      else if((i<7)&&(i>3)&&(strArr[intFen-1]=="0")&&(strNow[0]=="零"))
      {

      } 
      //拾－仟中一位为零且其前一位是元且为零的情况时跨过
      else if((i<7)&&(i>3)&&(strArr[intFen-1]=="0")&&(strNow[0]=="元"))
      {
      	
      }
      //当万为零时必须补上万字
      else if((i==7)&&(strArr[intFen-1]=="0"))
      {
        strBig ="万"+strBig;
      }
      //拾万－仟万中一位为零且其前一位（万以上）不为零的情况时补零
      else if((i<11)&&(i>7)&&(strArr[intFen-1]=="0")&&(strNow[0]!="零")&&(strNow[0]!="万"))
      {
        strBig = "零"+strBig;
      }
      //拾万－仟万中一位为零且其前一位（万以上）也为零的情况时跨过
      else if((i<11)&&(i>7)&&(strArr[intFen-1]=="0")&&(strNow[0]=="万"))
      {
      	
      }
      //拾万－仟万中一位为零且其前一位为万位且为零的情况时跨过
      else if((i<11)&&(i>7)&&(strArr[intFen-1]=="0")&&(strNow[0]=="零"))
      {
      	
      }
      //万位为零且存在仟位和十万以上时，在万仟间补零
      else if((i<11)&&(i>8)&&(strArr[intFen-1]!="0")&&(strNow[0]=="万")&&(strNow[2]=="仟"))
      {
        strBig = strNum+strDW+"万零"+strBig.substring(1,strBig.length);
      }
      //单独处理亿位
      else if(i==11)
      {
      //亿位为零且万全为零存在仟位时，去掉万补为零
        if((strArr[intFen-1]=="0")&&(strNow[0]=="万")&&(strNow[2]=="仟"))
        {
          strBig ="亿"+"零"+strBig.substring(1,strBig.length);
        }
        //亿位为零且万全为零不存在仟位时，去掉万
        else if((strArr[intFen-1]=="0")&&(strNow[0]=="万")&&(strNow[2]!="仟"))
        {
          strBig ="亿"+strBig.substring(1,strBig.length);
        }
        //亿位不为零且万全为零存在仟位时，去掉万补为零
        else if((strNow[0]=="万")&&(strNow[2]=="仟"))
        {
          strBig = strNum+strDW+"零"+strBig.substring(1,strBig.length);
        }
        //亿位不为零且万全为零不存在仟位时，去掉万 
        else if((strNow[0]=="万")&&(strNow[2]!="仟"))
        {
          strBig = strNum+strDW+strBig.substring(1,strBig.length); 
        }
        //其他正常情况
        else
        {
          strBig = strNum+strDW+strBig;
        }
      }
      //拾亿－仟亿中一位为零且其前一位（亿以上）不为零的情况时补零
      else if((i<15)&&(i>11)&&(strArr[intFen-1]=="0")&&(strNow[0]!="零")&&(strNow[0]!="亿"))
      {
        strBig = "零"+strBig;
      }
      //拾亿－仟亿中一位为零且其前一位（亿以上）也为零的情况时跨过
      else if((i<15)&&(i>11)&&(strArr[intFen-1]=="0")&&(strNow[0]=="亿"))
      {
      	
      }
      //拾亿－仟亿中一位为零且其前一位为亿位且为零的情况时跨过
      else if((i<15)&&(i>11)&&(strArr[intFen-1]=="0")&&(strNow[0]=="零"))
      {
      	
      }
      //亿位为零且不存在仟万位和十亿以上时去掉上次写入的零
      else if((i<15)&&(i>11)&&(strArr[intFen-1]!="0")&&(strNow[0]=="零")&&(strNow[1]=="亿")&&(strNow[3]!="仟"))
      {
        strBig = strNum+strDW+strBig.substring(1,strBig.length);
      }
      //亿位为零且存在仟万位和十亿以上时，在亿仟万间补零
      else if((i<15)&&(i>11)&&(strArr[intFen-1]!="0")&&(strNow[0]=="零")&&(strNow[1]=="亿")&&(strNow[3]=="仟"))
      {
        strBig = strNum+strDW+"亿零"+strBig.substring(2,strBig.length);
      }
      else
      {
        strBig = strNum+strDW+strBig;
      }
      strFen = strFen.substring(0,intFen-1);
      intFen = strFen.length;
      strArr = strFen.split("");
    }
    return strBig;
  }
  catch(err)
  {
    return ""; //若失败则返回原值
  } 
}
  //wangjm 20080114(国寿受托系统,登录机构不论总机构或分机构都是6位,所以系统中所有的管理机构相关查询条件不能直接按原来的like 登录机构% 进行处理,
  //设计方案:如果是省级,市级机构,在ldcom 表里将 outcomcode 字段描述规则如下
  //comcode  outcomcode     name
  //140000 		14    				山西省分公司
  //140100		1401					中国人寿保险股份有限公司太原市分公司
  //140101    140101				太原分公司迎泽大街营销服务部
  //140102		140102				太原市迎泽支公司
  //按照以上规则进行描述,所有js,或 java 中如果牵涉到上级管理机构需要查询到下级管理机构信息的地方,都需要取到outcomcode 字段进行查询(like 'outcomcode%'),这样才对原有程序修改会更小
//   function QueryMngCom(manageCom)
//   { 
//   	var QueryManageCom="";                              
//    if(manageCom=="000000")// 000000  总公司  类似原来的 86 管理机构
//    {
//      QueryManageCom="000000";
//    }else
//    {
//    	var MngComSQL="select outcomcode from ldcom where comcode='"+manageCom+"'";
//      var arr = easyExecSql( MngComSQL );
//      if(arr)
//      {
//       QueryManageCom=arr[0][0];
//      }else
//      	{
//      		QueryManageCom="999999";//双击选项框可以输入,防止输入错误的机构代码(赋一个特殊值999999)代表错误机构代码
//      	}
//    } 
//    return  QueryManageCom;
//   }
 //end
 
 //由于受托系统对.js中的sql语句做了隐藏,更改QueryMngCom方法
 
 function QueryMngCom(manageCom)
 { 
 	var QueryManageCom="";                              
  if(manageCom=="000000")// 000000  总公司  类似原来的 86 管理机构
  {
    QueryManageCom="000000";
  }else
  {
        var mySql=new SqlClass();
				mySql.setJspName("../../common/javascript/CommonSql.jsp");
				mySql.setSqlId("QueryMngCom");
				mySql.addPara("comcode",manageCom);	
				var arr = easyExecSql(mySql.getString());
      if(arr)
      {
       QueryManageCom=arr[0][0];
      }else
      	{
      		QueryManageCom="999999";//双击选项框可以输入,防止输入错误的机构代码(赋一个特殊值999999)代表错误机构代码
      	}
			}
			return QueryManageCom;
	}
	
	//luzhe-20080827
	//打开新窗口的公共方法
	//---start------------------------------------------------------------------------
	function OpenWindow(wUrl,wTitle,w,h)
	{
		wUrl = wUrl.replace(/&/g,"|");
		var mUrl = '../sys/OpenWindowInterface.jsp?interface='+wUrl;
		if(w==''||w==null)
		{
			w=1000;	
		}
		if(h==''||h==null)
		{
			h=600;	
		}
		if(wTitle==null||wTitle=='')
		{
			wTitle = "系统弹出窗口";
		}
			mUrl+="&title="+wTitle;
		var iWidth=w; //模态窗口宽度
		var iHeight=h;//模态窗口高度
		//window.showModalDialog(mUrl,'DetailWondow','width='+iWidth+',height='+iHeight+',top='+iTop+',left='+iLeft+',toolbar=0,location=0,directories=0,menubar=0,scrollbars=0,resizable=0,status=0');
		var tReturn = window.showModalDialog(mUrl,window,"dialogWidth="+iWidth+"px;dialogHeight="+iHeight+"px;help=no;status=no;scroll=auto");

		return tReturn;
	}
	//---end--------------------------------------------------------------------------
	
	
	//重写showModalDialog方法
	//add by luzhe 20081007 解决IE安全级别较高时的兼容问题
	window_showModalDialog = window.showModalDialog;//首先保存原方法
	window.showModalDialog = function (a,b,c)
	{
		try
		{
			return window_showModalDialog(a,b,c);//执行原方法,如果有异常则采用替代方法
		}
		catch(ex)
		{	
			var arr = new Array();
			arr = a.split('=');
			var EndText = arr[arr.length-1];
			
			//显示提示信息的方法,EndText为提交后台的反馈信息
			if(a.indexOf("../common/jsp/MessagePage.jsp")!=-1)
			{				
				alert(EndText);
			}
			
			//showOneCodeName的问题,EndText为codeType的值
			else if(a.indexOf("../common/jsp/CodeQueryWindow.jsp")!=-1)
			{
				var urlStr = "../common/jsp/CodeQueryXMLHTTP.jsp";
				var strPara = "codeType=" + EndText;
				Request = new ActiveXObject("Microsoft.XMLHTTP");
				Request.open("POST",urlStr, false);
				Request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
				Request.send(strPara);
				var strQueryResult = "";
				try
				{
					strQueryResult = Request.responseText.trim();
				}
				catch(e1)
				{
					alert("数据返回出错："+e1.message);
				}	
				return strQueryResult;
			}
			else
			{
				alert("您的IE浏览器安全级别过高,请将IE的安全级别调整为\"低\",再登录系统!");
			}
		}
	}
//金钱格式校验
function isMoney( str ){ 
 var s=trim(str);  
 var regu = "^[0-9]+[\.][0-9]+$";
 var re = new RegExp(regu);

 var regu1 = "^[0-9]+$";
 var re1 = new RegExp(regu1);
 var regu2 = "^-[0-9]+$";
 var re2 = new RegExp(regu2);

 var regu3 = "^-[0-9]+[\.][0-9]+$";
 var re3 = new RegExp(regu3);
 if (s.search(re) != -1 ||s.search(re1) != -1||s.search(re2) != -1 ||s.search(re3) != -1  ) {
    return true;
    }
  else {
    return false;
   }
}
//金钱整数部分三位一撇
function moneyFormat(str)
{   
     if(str.length<=3)   
     {
     		return str;
     }   
     else
     {
     		if(str.indexOf('-')==-1)
     		{
     				return moneyFormat(str.substr(0,str.length-3))+","+(str.substr(str.length-3));
     		}
     		else
     		{
     				str = str.replace('-','');
     				return '-'+moneyFormat(str.substr(0,str.length-3))+","+(str.substr(str.length-3));
     		}
     }   
}   
//金钱格式转化函数
function cashFormat(str)
{
	  	var floatstr="";
	    var intstr="";
	    var newintstr="";
	    var newStr=""; 
	    var flag="int";
	    
	    var  tempstr = moneyReturns(str);
	    
	    for(var m=0;m<tempstr.length;m++){   
      var c=tempstr.charAt(m);   
      if(c=='.')
      {
      	flag="float";
      	break; 
      }      
   } 
	if(flag=="float")
	{
	  var strArr = tempstr.split(".");  
    intstr=strArr[0];  
    newintstr=moneyFormat(intstr); 
    floatstr=strArr[1];
    if(floatstr.length==2)
    {
    newStr=newintstr+"."+floatstr;
    }
    if(floatstr.length==1)
    {
    newStr=newintstr+"."+floatstr+"0";
    }
    if(floatstr.length>2)
    {
    newStr=newintstr+"."+floatstr.substr(0,2);
    }
  }
  if(flag=="int")
	{
		newintstr=moneyFormat(tempstr); 
		newStr=newintstr+".00";
	}
  return trim(newStr);
	    
	    	
//	for(var m=0;m<str.length;m++){   
//      var c=str.charAt(m);   
//      if(c=='.')
//      {
//      	flag="float";
//      	break; 
//      }      
//   } 
//	if(flag=="float")
//	{
//	  var strArr = str.split(".");  
//    intstr=strArr[0];  
//    newintstr=moneyFormat(intstr); 
//    floatstr=strArr[1];
//    if(floatstr.length==2)
//    {
//    newStr=newintstr+"."+floatstr;
//    }
//    if(floatstr.length==1)
//    {
//    newStr=newintstr+"."+floatstr+"0";
//    }
//    if(floatstr.length>2)
//    {
//    newStr=newintstr+"."+floatstr.substr(0,2);
//    }
//  }
//  if(flag=="int")
//	{
//		newintstr=moneyFormat(str); 
//		newStr=newintstr+".00";
//	}
//  return trim(newStr);
}
//将金钱格式转化为数字
function isCash(str)
{
 var mflag=false;//是否含有，的标识
 var newstr=""; //
 var newcash="";
 var s=trim(str);
 var flag="int"; //整数和小数的标识
 var transferMoney="";  
 var newMoney="";
 for(var m=0;m<s.length;m++){   
      var c=s.charAt(m);   
      if(c!=','&&c!='，')
      {
    		newcash=newcash+c;
    		transferMoney=transferMoney+c;
    	}
     else
    	{
    		transferMoney=transferMoney+",";
    		mflag=true;
    	}    
   } 
 var regu = "^[0-9]+[\.][0-9]{0,2}$";
 var re = new RegExp(regu);

 var regu1 = "^[0-9]+$";
 var re1 = new RegExp(regu1);
 //特殊情况校验整数或一位小数
 if(mflag==true)
 {
   for(var m=0;m<newcash.length;m++){   
      var c=newcash.charAt(m);   
      if(c=='.')
      {
      	flag="float";
      	break; 
      }      
     } 
	  if(flag=="float")
	  {
	    var strArr = transferMoney.split(".");  
      var intstr=strArr[0];  
      var floatstr=strArr[1];
      if(floatstr.length==2)
      {
        newMoney=intstr+"."+floatstr;
      }
      else if(floatstr.length==1)
      {
        newMoney=intstr+"."+floatstr+"0";
      }
      else
    	{
    		newMoney=transferMoney;
    	}
    }
     if(flag=="int")
	   {
		   newMoney=transferMoney+".00";
	   }
    if(isMoney(newcash))
    {
    	newstr=cashFormat(newcash);
 	    if(newstr!=newMoney)
 	    {
 	     return false;	
 	    }
 	  }
 	  else
 		{
 			return false;
 		}
}
 if (newcash.search(re) != -1 ||newcash.search(re1) != -1  ) {
    return true;
    }
  else {
    return false;
   }
}
//校验邮政编码6为数字
function checkZipCode(str)
{
  	 var s=trim(str);
  	 var regu1 = "^[0-9]+$";
     var re1 = new RegExp(regu1);
     if(s.search(re1)== -1)
     {
     	  return false;
     }
     if(s.length!=6)
     {
     	  return false;
     	}
     	return true;
}
/**
 * @param:NodeCode(必传)
 * @Param:ActivityID(必传)
 * @param:BusiNo(必传)
 * @param:BusiType
 * @param:GrpPlanNo
 * @return true/false
 * */
function CheckForceFile(NodeCode,ActivityID,BusiNo,BusiType,GrpPlanNo,LineNo)
{
    var tNodeCode = NodeCode;
    if (tNodeCode == ""||tNodeCode==null||tNodeCode==''||tNodeCode=='null'||tNodeCode=="null")
    {
        tNodeCode = "";
    }
    var tActivityID = ActivityID;
    if (tActivityID == ""||tActivityID==null||tActivityID==''||tActivityID=='null'||tActivityID=="null")
    {
        tActivityID = "";
    }
    var tBusiNo = BusiNo;
    if (tBusiNo == ""||tBusiNo==null||tBusiNo==''||tBusiNo=='null'||tBusiNo=="null")
    {
        tBusiNo = "";
    }
    var tBusiType = BusiType;
    if (tBusiType == ""||tBusiType==null||tBusiType==''||tBusiType=='null'||tBusiType=="null")
    {
        tBusiType = "";
    }
    var tGrpPlanNo = GrpPlanNo;
    if (tGrpPlanNo == ""||tGrpPlanNo==null||tGrpPlanNo==''||tGrpPlanNo=='null'||tGrpPlanNo=="null")
    {
        tGrpPlanNo = "";
    }
    mySql = new SqlClass();
    mySql.setJspName("../../fileManage/FileProcessSql.jsp");
    mySql.setSqlId("CheckForceFile");
    mySql.addPara("NodeCode", tNodeCode);
    mySql.addPara("ActivityID", tActivityID);
    mySql.addPara("BusiNo", tBusiNo);
    mySql.addPara("BusiType", tBusiType);
    mySql.addPara("ActivityID", tActivityID);
    mySql.addPara("GrpPlanNo", tGrpPlanNo);
    var arr = easyExecSql(mySql.getString());
    if (null == arr || "" == arr || '' == arr)
    {
        return true;
    }
    else
    {
    	var msg = "请上载文件["+arr+"]";
    	if(LineNo!=undefined&&LineNo!=null&&LineNo!='')
    	{
    		msg = "第"+LineNo+"行数据发生错误："+msg;
    	}
        alert(msg);
        return false;
    }
}


//将金额中的","去掉，转换为******.**类型
function moneyReturns(str)
 {
 	    var temp = str ;
 	    var areturn= str;
 	    var  a=temp.indexOf("，");
       if(a!=-1)
       {
       	 
        temp = temp.replaceAll("，",",");
      }
      var index = temp.indexOf(",");
      if(index==-1)
      {
        return trim(areturn);
     }else{
      var arr =temp.split(",");
      var i;
      var returnstrr='';
      for(i=0;i<arr.length;i++)
      {
      	returnstrr+=arr[i];
      }
        return trim(returnstrr);
     }

 	  
}


//javascript获取指定参数及其对应的值
//luzhe-add-20090827
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

function countChar(textareaName,spanName,charnum)
{  
	 var Letters = "1234567890";
	 var i;
	 var c;
	 var str = new String(charnum);
	 for( i = 0; i < str.length; i ++ )
	 {
	    c = str.charAt( i );
	    if (Letters.indexOf( c ) ==-1)
	    {
	    		alert("输入字符非法!");
	    		return false;
	    }
	 }
	 document.getElementById(spanName).innerHTML = charnum - document.getElementById(textareaName).value.length;
	 if(document.getElementById(textareaName).value.length>charnum)
	 {
	 		document.getElementById(textareaName).value = document.getElementById(textareaName).value.substr(0,charnum);
	 		document.getElementById(spanName).innerHTML = charnum - document.getElementById(textareaName).value.length;
	 		if((charnum - document.getElementById(textareaName).value.length)<0)
	 		{
			 		document.getElementById(textareaName).value = document.getElementById(textareaName).value.substr(0,(charnum-1));
			 		document.getElementById(spanName).innerHTML = charnum - document.getElementById(textareaName).value.length;
	 		}
	 }
}

//add by mahy 2009-12-02 查询经办人
function queryOperatorCommon(usercode)
{
  mySql = new SqlClass();
  mySql.setJspName("../../contract/OperationPrepareSql.jsp");
  mySql.setSqlId("OperationPub");
  mySql.addPara("UserCode", usercode);
  var arr = easyExecSql(mySql.getString());	
  if(arr)
  {
    return arr[0][0];	
  }
  else
	{
	  alert("未查询出经办人！");	
	  return false;
	}
}

//add by mahy 2009-12-07 查询归属机构
function queryManageComCommon(managecom)
{
  mySql = new SqlClass();
  mySql.setJspName("../../contract/OperationPrepareSql.jsp");
  mySql.setSqlId("OperationPub1");
  mySql.addPara("ComCode", managecom);
  var arr = easyExecSql(mySql.getString());	
  if(arr)
  {
    return arr[0][0];	
  }
  else
	{
	  alert("未查询出归属机构！");	
	  return false;
	}	
}


//显示提示信息
function ShowInfo(Message,TypeFlag)
{
	CloseInfo();
	if(TypeFlag=='1')
	{
		document.getElementById("InfoDiv").innerHTML = 
		"<table id='hideDiv' class='hideDiv' style='width:100%; height:100%;'>"+
			"<tr>"+
				"<td>"+
					"<div id='errorMsg' class='errorMsg2'>"+
						"<div id='errorTitle' class='errorTitle'></div>"+
						"<div id='errorBody' class='errorBody'>"+					
						"<img src='../common/images/common.gif'>"+Message+					
						"</div>"+
						""+
					"</div>"+
				"</td>"+
			"</tr>"+
			"</table>";
	}
	else
	{
		document.getElementById("InfoDiv").innerHTML = 
		"<table id='hideDiv' class='hideDiv' style='width:100%; height:100%;'>"+
			"<tr>"+
				"<td>"+
					"<div id='errorMsg' class='errorMsg'>"+
						"<div id='errorTitle' class='errorTitle'></div>"+
						"<div id='errorBody' class='errorBody'><img src='../common/images/success.gif'>"+Message+"</div>"+
						""+
						"<input type=button class=common id=butSubmit value='确 定' onclick='CloseInfo();' tabIndex=0>"+
					"</div>"+
				"</td>"+
			"</tr>"+
		"</table>";
	}
	
	document.getElementById("InfoDiv").style.display = "";
	document.getElementById("InfoDiv").focus;
}

function CloseInfo() {
	document.getElementById("InfoDiv").style.display = "none";
}

String.prototype.replaceAll  = function(s1,s2){   
        return this.replace(new RegExp(s1,"gm"),s2);   
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