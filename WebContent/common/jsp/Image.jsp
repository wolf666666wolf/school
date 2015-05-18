<%@page contentType="image/*;charset=UTF-8"%>
<%@page import="java.awt.*,java.awt.image.*,java.util.*,javax.imageio.*,java.io.*"%>
<%@page import="com.sinosoft.service.BusinessDelegate"%>
<%@page import="com.sinosoft.utility.*"%>
<%@page import="com.sinosoft.lis.schema.*"%>
<%@page import="com.sinosoft.lis.pubfun.*"%>
<%
	BusinessDelegate tBusinessDelegate=BusinessDelegate.getBusinessDelegate();
	VData tData=new VData();
	TransferData tTransferData = new TransferData();
	String path="";
	
	String docID = request.getParameter("docid");	
	tTransferData.setNameAndValue("DocID", docID);
	tData.add(tTransferData);
	try
	{
		tBusinessDelegate.submitData(tData, "GetURL","FileDownLoad");
	  tData.clear();
	  tData=tBusinessDelegate.getResult();
	  
		tTransferData = (TransferData)tData.getObjectByObjectName("TransferData", 0);
		path = (String)tTransferData.getValueByName("URL");		

		File tFile = new File(path);
		InputStream is = new FileInputStream(tFile);
		int i=is.available(); 
		byte data[]=new byte[i]; 
		is.read(data);
		is.close();
		OutputStream toClient=response.getOutputStream();
		toClient.write(data);
		toClient.close();
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
%>