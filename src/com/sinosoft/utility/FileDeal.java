package com.sinosoft.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.io.FileWriter;
import java.util.regex.*;
import com.sinosoft.lis.log.*;

public class FileDeal
{

  public CErrors mErrors = new CErrors(); // 错误信息
  private String OriPathName;
  private String DesPathName;
  private File mFile;

//  private File DesFile;

  public FileDeal(String aStr) {
    OriPathName = aStr;
  }

  public boolean FileCopy(String aStr) {
    try {
      DesPathName = aStr;
      int nChar = -1;
      FileInputStream in = new FileInputStream(OriPathName);
      FileOutputStream out = new FileOutputStream(DesPathName);
      while ( (nChar = in.read()) != -1) {
        out.write(nChar);
      }
      out.flush();
      in.close();
      out.close();
    }
    catch (Exception ex) {
      ex.printStackTrace();
      // @@错误处理
      CError tError = new CError();
      tError.moduleName = "FileDeal";
      tError.functionName = "FileCopy";
      tError.errorMessage = "文件复制出错!";
      this.mErrors.addOneError(tError);
      return false;
    }
    return true;
  }

  public boolean FileMove(String aStr) {
    try {
      DesPathName = aStr;
      mFile = new File(OriPathName);
      File tFile = new File(DesPathName);
      if (!mFile.renameTo(tFile)) {
        // @@错误处理
        CError tError = new CError();
        tError.moduleName = "FileDeal";
        tError.functionName = "FileMove";
        tError.errorMessage = "文件移动出错!";
        this.mErrors.addOneError(tError);
        return false;
      }
      if (!mFile.createNewFile()) {
        // @@错误处理
        CError tError = new CError();
        tError.moduleName = "FileDeal";
        tError.functionName = "FileMove";
        tError.errorMessage = "文件移动出错!";
        this.mErrors.addOneError(tError);
        return false;
      }
      tFile = new File(OriPathName);
      if (!tFile.delete()) {
        // @@错误处理
        CError tError = new CError();
        tError.moduleName = "FileDeal";
        tError.functionName = "FileMove";
        tError.errorMessage = "文件移动出错!";
        this.mErrors.addOneError(tError);
        return false;
      }
    }
    catch (Exception ex) {
      ex.printStackTrace();
      // @@错误处理
      CError tError = new CError();
      tError.moduleName = "FileDeal";
      tError.functionName = "FileMove";
      tError.errorMessage = "文件移动出错!";
      this.mErrors.addOneError(tError);
      return false;
    }
    return true;
  }

  public boolean FileDel() {
    File tFile = new File(OriPathName);
    if (!tFile.delete()) {
      // @@错误处理
      CError tError = new CError();
      tError.moduleName = "FileDeal";
      tError.functionName = "FileDel";
      tError.errorMessage = "文件删除出错!";
      this.mErrors.addOneError(tError);
      return false;
    }
    return true;
  }
    public static boolean writeText(String fileName, String content) {
      return writeText(fileName, content, "GBK");
    }

    public static boolean writeText(String fileName, String content,
                                    String encoding) {
      try {
        FileOutputStream fis;
        fis = new FileOutputStream(fileName);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fis,
            encoding));
        bw.write(content);
        bw.flush();
        bw.close();
        fis.close();
      }
      catch (Exception e) {
        e.printStackTrace();
        return false;
      }
      return true;
    }

    public static boolean appendText(String fileName, String content) {
      try {
        FileWriter fw = new FileWriter(fileName,true);
        File f = new File(fileName);
        if(f.exists()){
           fw.write("\n");
        }
        fw.write(content);
        fw.flush();
        fw.close();
      }
      catch (Exception e) {
        e.printStackTrace();
        return false;
      }
      return true;
    }

    public static String readText(String fileName) {
      return readText(fileName, "GBK");
    }

    public static String readText(String fileName, String encoding) {
      try {
        InputStream is = new FileInputStream(fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(is,
            encoding));
        StringBuffer sb = new StringBuffer();
        String line;
        while ( (line = br.readLine()) != null) {
          sb.append(line);
          sb.append("\n");
        }
        br.close();
        is.close();
        return sb.toString();
      }
      catch (Exception e) {
        e.printStackTrace();
      }
      return null;
    }

    public static void main(String[] args) { }
}
