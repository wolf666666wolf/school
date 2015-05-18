<%
//程序名称：EasyQueryKernel.jsp
//程序功能：EasyQuery查询功能的核心函数
//创建日期：2002-09-28
//创建人  ：胡博
//更新记录：  更新人    更新日期     更新原因/内容
%>
<%@page import="com.sinosoft.lis.pubfun.EasyQueryUI"%>
<%@page import="com.sinosoft.utility.VData"%>
<%!
//调用EasyQueryUI进行SQL语句提交和数据库查找，返回结果字符串strResult
public String easyQueryKernel(String strSql, String strStart, String LargeFlag)
{
	String strResult = "";
	String strError = "";
	Integer intStart;

	if (strStart == null || strStart.equals("") || strStart.equals("undefined"))
	{
		intStart = new Integer(String.valueOf("1"));
	}
	else
	{
//		intStart = new Integer(String.valueOf(strStart));
		intStart = new Integer(strStart);
	}

	VData tVData = new VData();
	tVData.add(strSql);
	tVData.add(intStart);
	//2005-4-18 11:16 朱向峰添加大数据量查询标志
	tVData.add(LargeFlag);

	EasyQueryUI tEasyQueryUI = new EasyQueryUI();
	tEasyQueryUI.submitData(tVData, "QUERY||MAIN");
	if(tEasyQueryUI.mErrors.needDealError())
	{
		strError = tEasyQueryUI.mErrors.getFirstError();
	}
	else
	{
		tVData.clear() ;
		tVData = tEasyQueryUI.getResult() ;
		strResult = (String)tVData.getObject(0);
		//System.out.println(strResult);
	}
	return strResult;
}
%>
