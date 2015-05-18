<%@page contentType="text/html;charset=UTF-8" %>
<%@page import="com.sinosoft.lis.web.*"%>
<%@page import="com.sinosoft.utility.*"%>
<%
String a = request.getParameter("pd.action");
String d = request.getParameter("pd.data");
String o = request.getParameter("pd.operate");
if("GET".equals(request.getMethod()))
{
	request.setCharacterEncoding("UTF-8");
	d= new String(d.getBytes("iso8859-1"), "utf-8");
	//d=StrTool.unicodeToGBK(d);
	
}
VData vd = new VData();
vd.add(d);
vd.add(session);
ActionBL abl = new ActionBL();
abl.submitData(vd,a+"_"+o);
String r = (String)abl.getResult().getObjectByObjectName("String",0);
//System.out.println(r);

out.write(r);
%>