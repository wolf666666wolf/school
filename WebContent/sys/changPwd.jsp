﻿
<%@page contentType="text/html;charset=UTF-8" %>

<%@page import="com.sinosoft.utility.*"%>
<%@page import="com.sinosoft.lis.schema.*"%>
<%@page import="com.sinosoft.lis.vschema.*"%>
<%@page import="com.sinosoft.lis.changePwd.*"%>
<%@page import="com.sinosoft.lis.encrypt.*"%>
<%@page import="com.sinosoft.lis.log.*"%>
<%@page import="com.sinosoft.lis.pubfun.GlobalInput"%>           
<SCRIPT src="UserAdd.js"></SCRIPT>
<html>
<body>
<% 
    String FlagStr = "false";
  	GlobalInput tG1 = new GlobalInput();
	tG1=(GlobalInput)session.getValue("GI");
	String usercode = tG1.Operator;
	log.addlog("usercode" + usercode);
	
	LisIDEA tLisIdea = new LisIDEA();
	String oldpwd = tLisIdea.encryptString(request.getParameter("oldPwd"));
	String newpwd = tLisIdea.encryptString(request.getParameter("newPwd"));
	LDUserSchema oldUserSchema = new LDUserSchema();
	LDUserSchema newUserSchema = new LDUserSchema();
	
	oldUserSchema.setUserCode(usercode);
	oldUserSchema.setPassword(oldpwd);
	log.addlog("oldpwd:" + oldpwd);
	
	newUserSchema.setUserCode(usercode);
	newUserSchema.setPassword(newpwd);
    log.addlog("newpwd:" + newpwd);
    
    
    VData tData = new VData();
    tData.add(oldUserSchema);
    tData.add(newUserSchema);
    LDChangePwdUI tLDChangePwdUI = new LDChangePwdUI();
    if (tLDChangePwdUI.submitData(tData,"changePwd")) {
        FlagStr = "true";
    } else {
        FlagStr = "false";
    }    
%>  

<script>
	parent.fraInterface.afterSubmit("<%=FlagStr%>");
</script>
	
</body>

</html>