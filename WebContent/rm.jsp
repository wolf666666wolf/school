<%@page contentType="text/html;charset=utf-8" %>
<%@page import="com.sinosoft.lis.pubfun.PubFun"%>
<%@page import="com.sinosoft.lis.*"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="java.io.*"%>
<%@page import="javax.xml.parsers.*"%>
<%@page import="javax.xml.transform.*"%>
<%@page import="javax.xml.transform.dom.*"%>
<%@page import="javax.xml.transform.stream.*"%>
<%@page import="org.w3c.dom.Document"%>
<%@page import="org.xml.sax.*"%>
<%@page import="com.sinosoft.lis.web.*"%>
<%@page import="com.sinosoft.utility.*"%>
<%
if("GET".equals(request.getMethod()))
{
	out.print("只接受POST请求");
	return;
}
try {
	DocumentBuilderFactory buildFactory=DocumentBuilderFactory.newInstance();
	DocumentBuilder build;
	build = buildFactory.newDocumentBuilder();
	
	
	
	
	///////
	BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));   

        StringBuilder sb = new StringBuilder();   

    

        String line = null;   

        try {   

            while ((line = reader.readLine()) != null) {   

                sb.append(line + "/n");   

            }   

        } catch (IOException e) {   

            e.printStackTrace();   

        } finally {  

        }   
        String st = sb.toString();
        st=st.replace("utf-8","UTF-8");
        st=st.replace("/n","");
    	System.out.println("---"+st);
        
        StringReader sr = new StringReader(st);   
        
        InputSource is = new InputSource(sr);   
          
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();   
          
        DocumentBuilder builder=factory.newDocumentBuilder();    
          
        Document doc = builder.parse(is);    
	///////
	String a = "receive";
	String d = new JSONObject().accumulate("data",JSONArray.fromObject(PubFun.xml2JSON(st))).toString();
	String o = "receivemessage";

	VData vd = new VData();
	vd.add(d);
	vd.add(session);
	ActionBL abl = new ActionBL();
	abl.submitData(vd,a+"_"+o);
} catch (ParserConfigurationException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (SAXException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
%>