<%@ page language="java" import="java.util.*,java.io.*"	pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@ page import="org.apache.commons.fileupload.*"%>

<%
String imgdir="sellerimg";

System.out.println("imgdir:"+imgdir);
String Content = "";
//成功标志
String FlagStr = "";
	response.setContentType("text/html");
	//   图片上传路径
	String uploadPath = request.getSession().getServletContext()
			.getRealPath("/")
			+ "/"+imgdir+"/temp/";
	String tempPath = request.getSession().getServletContext()
	.getRealPath("/")
	+ "/"+imgdir+"/temp/";
	//   图片网络相对路径
	String imagePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
			String fileNmae = UUID.randomUUID().toString();
	//   文件夹不存在就自动创建：
	if (!new File(uploadPath).isDirectory())
		new File(uploadPath).mkdirs();
	if (!new File(tempPath).isDirectory())
		new File(tempPath).mkdirs();
	try {
		DiskFileUpload fu = new DiskFileUpload();
		//   设置最大文件尺寸，这里是4MB
		fu.setSizeMax(4194304);
		//   设置缓冲区大小，这里是4kb
		fu.setSizeThreshold(4096);
		//   设置临时目录：
		fu.setRepositoryPath(tempPath);
		//   得到所有的文件：
		List fileItems = fu.parseRequest(request);
		Iterator i = fileItems.iterator();
		//   依次处理每一个文件：
		while (i.hasNext()) {
			FileItem file = (FileItem) i.next();
			//   获得文件名，这个文件名是用户上传时用户的绝对路径：
			String sourcefileName = file.getName();
			if (sourcefileName != null
					&& (sourcefileName.endsWith(".jpg"))) {
				//   在这里可以记录用户和文件信息,生成上传后的文件名
				String destinationfileName = null;
					destinationfileName = fileNmae+ ".jpg";
				
				File f1 = new File(uploadPath + destinationfileName);
				file.write(f1);
				Content = "上传成功!";
		    	FlagStr = "Succ";
			} else {
				out.println("上传文件出错，只能上传 *.jpg");
				Content = "上传文件出错，只能上传 *.jpg!";
		    	FlagStr = "Fail";
			}
		}
		//   跳转到上传成功提示页面
	} catch (Exception e) {
		//   可以跳转出错页面  
		
	}
	// out.flush();
	//out.close();
%>
<html>
<script type="text/javascript">
//top.close();
parent.afterUpload('<%=FlagStr%>','<%=Content%>','<%=fileNmae%>');
</script>
</html>