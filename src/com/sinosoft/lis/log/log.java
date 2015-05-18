package com.sinosoft.lis.log;

import com.sinosoft.lis.pubfun.*;
import com.sinosoft.lis.schema.*;
import com.sinosoft.lis.encrypt.*;
import java.util.Date;
import java.text.*;
import java.io.*;

public class log 
{
    public log() 
    {
    	
    }

    public static void addlog(String str)
    {
    	try
    	{
//    		str = PubFun.getCurrentTime()+":"+str;
//    		String LogPath = "c:/log/";
//    		String FileName = PubFun.getCurrentDate()+".log";
//    		String add = LogPath+FileName;
//    		OutputStream outfile=new FileOutputStream(add,true);
//    		str+="\r\n";
//    		byte [] b=str.getBytes();            
//    		outfile.write(b,0,b.length);
//    		outfile.close();
    		System.out.println(str);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    public static void addlog(int intint)
    {
    	log.addlog(String.valueOf(intint));
    }
 
 
 public static void main(String arg[])
 {
	  log.addlog("啊?啥?");	 
	  
	  LisIDEA tIdea = new LisIDEA();
	  String Password = "20080225";
	  Password = tIdea.encryptString(Password);
//	  Password = tIdea.encryptString(Password);
	  log.addlog("啊?啥?"+Password);	 
	  String PassWord2 = "AC55611C06BDCF70";
//	  PassWord2 = tIdea.decryptString(PassWord2);
	  PassWord2 = tIdea.decryptString(PassWord2);
	  log.addlog("啊?啥?"+PassWord2);
	 
	 
//     String aaa = PubFun.calDate("2008-01-01", 1, "D", "2008-09-09");
//     log.addlog("啊?啥?"+aaa);
	  
	  //临时
//		LDStudentSchema tLDStudentSchema   = new LDStudentSchema();
//		tLDStudentSchema.setSID("");
//		tLDStudentSchema.setXWZFlag("");
//		tLDStudentSchema.setBYZFlag("");
//		tLDStudentSchema.setBYQX("");
//		tLDStudentSchema.setDWMC("");
//		tLDStudentSchema.setKYCJ("");
//		tLDStudentSchema.setYJSDS("");
     
 }
    
}
