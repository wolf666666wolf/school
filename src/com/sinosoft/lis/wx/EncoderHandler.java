/*    */ package com.sinosoft.lis.wx;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.security.MessageDigest;
/*    */ 
/*    */ public class EncoderHandler
/*    */ {
/*    */   private static final String ALGORITHM = "MD5";
/* 14 */   private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', 
/* 15 */     '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
/*    */ 
/*    */   public static String encode(String algorithm, String str)
/*    */   {
/* 25 */     if (str == null)
/* 26 */       return null;
/*    */     try
/*    */     {
/* 29 */       MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
/* 30 */       messageDigest.update(str.getBytes());
/* 31 */       return getFormattedText(messageDigest.digest()); } catch (Exception e) {
	 throw new RuntimeException(e);
/*    */     }
/* 33 */    
/*    */   }
/*    */ 
/*    */   public static String encodeByMD5(String str)
/*    */   {
/* 45 */     if (str == null)
/* 46 */       return null;
/*    */     try
/*    */     {
/* 49 */       MessageDigest messageDigest = MessageDigest.getInstance("MD5");
/* 50 */       messageDigest.update(str.getBytes());
/* 51 */       return getFormattedText(messageDigest.digest()); } catch (Exception e) {
	
	 throw new RuntimeException(e);
/*    */     }
/* 53 */    
/*    */   }
/*    */ 
/*    */   private static String getFormattedText(byte[] bytes)
/*    */   {
/* 66 */     int len = bytes.length;
/* 67 */     StringBuilder buf = new StringBuilder(len * 2);
/*    */ 
/* 69 */     for (int j = 0; j < len; j++) { buf.append(HEX_DIGITS[(bytes[j] >> 4 & 0xF)]);
/* 70 */       buf.append(HEX_DIGITS[(bytes[j] & 0xF)]);
/*    */     }
/* 72 */     return buf.toString();
/*    */   }
/*    */ 
/*    */   public static void main(String[] args) {
/* 76 */     System.out.println("111111 MD5  :" + 
/* 77 */       encodeByMD5("111111"));
/* 78 */     System.out.println("111111 MD5  :" + 
/* 79 */       encode("MD5", "111111"));
/* 80 */     System.out.println("111111 SHA1 :" + 
/* 81 */       encode("SHA1", "111111"));
/*    */   }
/*    */ }

/* Location:           E:\workspace_lyj\Tomcat6_8080\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.sinosoft.lis.wx.EncoderHandler
 * JD-Core Version:    0.6.0
 */