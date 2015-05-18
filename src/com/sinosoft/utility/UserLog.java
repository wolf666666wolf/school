﻿package com.sinosoft.utility;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import com.sinosoft.lis.log.*;
/*****************************************************************
 *               Program NAME: 写日志类
 *****************************************************************
 *                       使用说明
 *相关目录中必须包含SysConst.class，StrTool.class和ChgData.class
 *对日志的相关操作。首先要将Appconfig.properties文件拷贝到当前目录"."下；
 *可以通过getDefaultPath()类方法获取当前目录路径全称；
 *在配置文件Appconfig.properties中设置应用日志和系统日志的存放路径
 *该类的println()方法写入应用程序日志文件，printException写入系统日志文件
 *现在使用接口改为addUserLog(String strLog)和addSysLog(String strLog)方法
 *
*/
       class UserLog
{
   private static String strUserLogFileName = ConfigInfo.GetValuebyName(
           SysConst.USERLOGPATH);
   //应用程序日志
   private static String strSysLogFileName = ConfigInfo.GetValuebyName(SysConst.
           SYSLOGPATH);
   //系统日志

   /****************************************
    *            构造方法(default)
    ****************************************
    */
   public UserLog()
   {
       //主要进行日志文件名的初始化；
       //初始化应用程序日志文件名称，一天使用一个带日期的新文件
       String userLogExtendsion = strUserLogFileName.substring(
               strUserLogFileName.indexOf("."));
       String strDate = StrTool.getDate();
       String strDateYear = strDate.substring(0, strDate.indexOf("/"));
       strDate = strDate.substring(strDate.indexOf("/") + 1);
       String strDateMonth = strDate.substring(0, strDate.indexOf("/"));
       strDate = strDate.substring(strDate.indexOf("/") + 1);
       String strDateDay = strDate;
       //log.addlog(strDateYear + strDateMonth + strDateDay);

       //网页刷新时，缓存中会有对象变量，为了防止文件名称重复被追加日期，进行判断
       if ((strUserLogFileName.indexOf("(") == -1) &&
           (strUserLogFileName.indexOf(")") == -1))
       {
           strUserLogFileName = strUserLogFileName.substring(0,
                   strUserLogFileName.indexOf("."))
                                + "("
                                + strDateYear
                                + "_"
                                + strDateMonth
                                + "_"
                                + strDateDay
                                + ")"
                                + userLogExtendsion;
       }

       //初始化系统日志文件名称，一天使用一个带日期的新文件
       String sysLogExtendsion = strSysLogFileName.substring(strSysLogFileName.
               indexOf("."));
       strDate = StrTool.getDate();
       strDateYear = strDate.substring(0, strDate.indexOf("/"));
       strDate = strDate.substring(strDate.indexOf("/") + 1);
       strDateMonth = strDate.substring(0, strDate.indexOf("/"));
       strDate = strDate.substring(strDate.indexOf("/") + 1);
       strDateDay = strDate;
       //log.addlog(strDateYear + strDateMonth + strDateDay);

       //网页刷新时，缓存中会有对象变量，为了防止文件名称重复被追加日期，进行判断
       if ((strSysLogFileName.indexOf("(") == -1) &&
           (strSysLogFileName.indexOf(")") == -1))
       {
           strSysLogFileName = strSysLogFileName.substring(0,
                   strSysLogFileName.indexOf("."))
                               + "("
                               + strDateYear
                               + "_"
                               + strDateMonth
                               + "_"
                               + strDateDay
                               + ")"
                               + sysLogExtendsion;
       }

   }

   /****************************************
    *              构造方法
    *   参数：ALLLogPath :应用程序日志和
    *                     系统信息日志使
    *                     用相同路径
    ****************************************
    */
   public UserLog(String AllLogPath)
   {
       strUserLogFileName = AllLogPath;
       strSysLogFileName = AllLogPath;
   }

   /****************************************
    *              构造方法
    *   参数：UserLogPath :应用程序日志路径
    *         SysLogPath  :系统信息日志路径
    ****************************************
    */
   public UserLog(String UserLogPath, String SysLogPath)
   {
       strUserLogFileName = UserLogPath;
       strSysLogFileName = SysLogPath;
   }

   /****************************************
    *   生成错误信息的临时文件，包括html等
    *    参数： strFileName:文件名称,
    *              strValue:写入日志的内容
    *  返回值:  0：成功，-1:失败
    ****************************************
    */
   public static int generateTempFile(String strFileName, String strValue)
   {
       String strFileValue = strValue;

       try
       {
           RandomAccessFile in = new RandomAccessFile(strFileName, "rw");
           in.write(strFileValue.getBytes());
           in.close();
       }
       catch (IOException exception)
       {
           System.out.print(exception.toString());
           return SysConst.FAILURE;
       }
       return SysConst.SUCCESS;
   }


   /****************************************
    *       写入应用程序日志
    *   参数： strValue:写入日志的内容
    * 返回值:  0：成功，-1:失败
    ****************************************
    */
   private static int print(String strValue)
   {
       try
       {
           RandomAccessFile in = new RandomAccessFile(strUserLogFileName, "rw");
           in.seek(in.length());
           in.write(strValue.getBytes());
           in.write(13);
           in.write(10); //Minim add for 回车换行；
           in.close();
       }
       catch (IOException exception)
       {
           System.out.print(exception.toString());
           return SysConst.FAILURE;
       }
       return SysConst.SUCCESS;
   }

   /****************************************
    *       写入应用程序日志
    *   参数： intValue:写入日志的内容
    * 返回值:  0：成功，-1:失败
    ****************************************
    */
   public static int print(int intValue)
   {
       return print("" + intValue);
   }

   /****************************************
    *       写入应用程序日志
    *   参数： doubleValue:写入日志的内容
    * 返回值:  0：成功，-1:失败
    ****************************************
    */
   public static int print(double doubleValue)
   {
       return print("" + doubleValue);
   }

   /****************************************
    *       写入应用程序日志
    *   参数： object:写入日志的内容
    * 返回值:  0：成功，-1:失败
    ****************************************
    */
   public static int print(Object object)
   {
       return print("" + object.toString());
   }

   /****************************************
    *       按行写入应用程序日志
    *   参数： strValue:写入日志的内容
    * 返回值:  0：成功，-1:失败
    ****************************************
    */
   public static int println(String strValue)
   {
       String finalstrValue = null; // 写入串

       finalstrValue = "<" + StrTool.getDate() + " " + StrTool.getTime() + ">" +
                       strValue + "\n";
       return print(finalstrValue);
   }

   /****************************************
    *       按行写入应用程序日志
    *   参数： intValue:写入日志的内容
    * 返回值:  0：成功，-1:失败
    ****************************************
    */
   public static int println(int intValue)
   {
       String finalstrValue = null; // 写入串

       finalstrValue = "<" + StrTool.getDate() + " " + StrTool.getTime() + ">" +
                       intValue + "\n";
       return print(finalstrValue);
   }

   /****************************************
    *       按行写入应用程序日志
    *   参数： doubleValue:写入日志的内容
    * 返回值:  0：成功，-1:失败
    ****************************************
    */
   public static int println(double doubleValue)
   {
       String finalstrValue = null; // 写入串

       finalstrValue = "<" + StrTool.getDate() + " " + StrTool.getTime() + ">" +
                       doubleValue + "\n";
       return print(finalstrValue);
   }

   /****************************************
    *       按行写入应用程序日志
    *   参数： object:写入日志的内容
    * 返回值:  0：成功，-1:失败
    ****************************************
    */
   public static int println(Object object)
   {
       String finalstrValue = null; // 写入串

       finalstrValue = "<" + StrTool.getDate() + " " + StrTool.getTime() + ">" +
                       object.toString() + "\n";
       return print(finalstrValue);
   }

   /****************************************
    *       按行写入系统日志
    *   参数： strValue:写入日志的内容
    * 返回值:  0：成功，-1:失败
    ****************************************
    */
   private static int printException(String strValue)
   {
       String strLog = "<" + StrTool.getDate() + " " + StrTool.getTime() + ">" +
                       strValue + "\n";
       try
       {
           RandomAccessFile in = new RandomAccessFile(strSysLogFileName, "rw");
           in.seek(in.length());
           in.write(strLog.getBytes());
           in.write(13);
           in.write(10); //Minim add for 回车换行；
           in.close();
       }
       catch (IOException exception)
       {
           System.out.print(exception.toString());
           return SysConst.FAILURE;
       }
       return SysConst.SUCCESS;
   }


   /****************************************
    *       按行写入系统日志
    *   参数： intValue:写入日志的内容
    * 返回值:  0：成功，-1:失败
    ****************************************
    */
   public static int printException(int intValue)
   {
       return printException("" + intValue);
   }

   /****************************************
    *       按行写入系统日志
    *   参数： doubleValue:写入日志的内容
    * 返回值:  0：成功，-1:失败
    ****************************************
    */
   public static int printException(double doubleValue)
   {
       return printException("" + doubleValue);
   }

   /****************************************
    *       按行写入系统日志
    *   参数： object:写入日志的内容
    * 返回值:  0：成功，-1:失败
    ****************************************
    */
   public static int printException(Object object)
   {
       return printException("" + object.toString());
   }

   /****************************************
    *       写入应用日志接口
    *   参数： strLog:写入日志的内容
    * 返回值:  0：成功，-1:失败
    ****************************************
    */
   public static int addUserLog(String strLog)
   {
       return println(strLog);
   }

   /****************************************
    *       写入系统日志接口
    *   参数： strLog:写入日志的内容
    * 返回值:  0：成功，-1:失败
    ****************************************
    */
   public static int addSysLog(String strLog)
   {
       return printException(strLog);
   }

   public static String getDefaultPath()
   {
       File defaultPath = new File(".");
       return defaultPath.getAbsolutePath();
   }

   /**********************************************************************
    *                               测试用的读方法
    **********************************************************************
    */
   private static int readTest()
   {
       try
       {
           RandomAccessFile in = new RandomAccessFile(strUserLogFileName, "rw");
           // in.seek(in.length());
           // in.write(strValue.getBytes());
           int tRead = 0;
           while (tRead != -1)
           {
               tRead = in.read();
               log.addlog(tRead);

           }
           in.close();
       }
       catch (IOException exception)
       {
           System.out.print(exception.toString());
           return SysConst.FAILURE;
       }
       return SysConst.SUCCESS;
   }


   /**********************************************************************
    *                               main
    **********************************************************************
    */
   public static void main(String args[])
   {
       //you can add test code at here;
       log.addlog("Start");
       UserLog testUserLog = new UserLog();
       //UserLog("D:/Minim_work/testUserLog.txt");
       //testUserLog.addUserLog("this a user log test!");
       //testUserLog.addSysLog("this a test!");
       //log.addlog( FileSystem.getFileSystem());
       log.addlog("File save successful");
       log.addlog(getDefaultPath());

   }
}
