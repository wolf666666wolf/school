/*     */ package com.sinosoft.lis.wx;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Hashtable;
/*     */ import java.util.UUID;
/*     */ import net.sf.json.JSONObject;
/*     */ import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
/*     */ import org.apache.commons.httpclient.HttpClient;
/*     */ import org.apache.commons.httpclient.HttpException;
/*     */ import org.apache.commons.httpclient.methods.GetMethod;
/*     */ import org.apache.commons.httpclient.params.HttpMethodParams;
/*     */ 
/*     */ public class WXBL
/*     */ {
/*  19 */   private static WXBL wx = null;
/*  20 */   private Hashtable ht_access_token = new Hashtable();
/*  21 */   private Hashtable ht_jsapi_ticket = new Hashtable();
/*  22 */   private String appid = "wx0886f7313a64d07b";
/*  23 */   private String appsecret = "392875c0d3b7e696c1a189ff0ca03fdd";
/*     */ 
/*     */   public static WXBL getInstance()
/*     */   {
/*  31 */     if (wx == null)
/*     */     {
/*  33 */       wx = new WXBL();
/*     */     }
/*  35 */     return wx;
/*     */   }
/*     */ 
/*     */   public String get_access_token() {
/*  39 */     String access_token = (String)this.ht_access_token.get("ht_access_token");
/*  40 */     String access_token_ms = (String)this.ht_access_token.get("access_token_ms");
/*  41 */     long nms = System.currentTimeMillis();
/*     */ 
/*  43 */     if (access_token == null)
/*     */     {
/*  45 */       access_token = gen_access_token();
/*  46 */       this.ht_access_token.put("ht_access_token", access_token);
/*  47 */       this.ht_access_token.put("access_token_ms", String.valueOf(nms));
/*  48 */       return access_token;
/*     */     }
/*  50 */     long oms = Long.parseLong(access_token_ms);
/*  51 */     if (nms - oms >= 7000000L)
/*     */     {
/*  53 */       access_token = gen_access_token();
/*  54 */       this.ht_access_token.put("ht_access_token", access_token);
/*  55 */       this.ht_access_token.put("access_token_ms", String.valueOf(nms));
/*  56 */       return access_token;
/*     */     }
/*  58 */     return access_token;
/*     */   }
/*     */ 
/*     */   public String get_jsapi_ticket() {
/*  62 */     String jsapi_ticket = (String)this.ht_jsapi_ticket.get("ht_jsapi_ticket");
/*  63 */     String jsapi_ticket_ms = (String)this.ht_jsapi_ticket.get("jsapi_ticket_ms");
/*  64 */     long nms = System.currentTimeMillis();
/*     */ 
/*  66 */     if (jsapi_ticket == null)
/*     */     {
/*  68 */       jsapi_ticket = gen_jsapi_ticket();
/*  69 */       this.ht_jsapi_ticket.put("ht_jsapi_ticket", jsapi_ticket);
/*  70 */       this.ht_jsapi_ticket.put("jsapi_ticket_ms", String.valueOf(nms));
/*  71 */       return jsapi_ticket;
/*     */     }
/*  73 */     long oms = Long.parseLong(jsapi_ticket_ms);
/*  74 */     if (nms - oms >= 7000000L)
/*     */     {
/*  76 */       jsapi_ticket = gen_jsapi_ticket();
/*  77 */       this.ht_jsapi_ticket.put("ht_jsapi_ticket", jsapi_ticket);
/*  78 */       this.ht_jsapi_ticket.put("jsapi_ticket_ms", String.valueOf(nms));
/*  79 */       return jsapi_ticket;
/*     */     }
/*  81 */     return jsapi_ticket;
/*     */   }
/*     */ 
/*     */   private String gen_jsapi_ticket() {
/*  85 */     String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + get_access_token() + "&type=jsapi";
/*  86 */     String body = CrawlerBody(url);
/*  87 */     JSONObject jo = JSONObject.fromObject(body);
/*  88 */     String r = jo.get("ticket").toString();
/*  89 */     return r;
/*     */   }
/*     */ 
/*     */   private String CrawlerBody(String URL) {
/*  93 */     HttpClient client = new HttpClient();
/*  94 */     StringBuilder sb = new StringBuilder();
/*  95 */     InputStream ins = null;
/*     */ 
/*  97 */     GetMethod method = new GetMethod(URL);
/*     */ 
/*  99 */     method.getParams().setParameter("http.method.retry-handler", 
/* 100 */       new DefaultHttpMethodRetryHandler(3, false));
/*     */     try
/*     */     {
/* 103 */       int statusCode = client.executeMethod(method);
/* 104 */       System.out.println(statusCode);
/* 105 */       if (statusCode == 200) {
/* 106 */         ins = method.getResponseBodyAsStream();
/* 107 */         byte[] b = new byte[1024];
/* 108 */         int r_len = 0;
/* 109 */         while ((r_len = ins.read(b)) > 0)
/* 110 */           sb.append(
/* 111 */             new String(b, 0, r_len, method
/* 111 */             .getResponseCharSet()));
/*     */       }
/*     */       else {
/* 114 */         System.err.println("Response Code: " + statusCode);
/*     */       }
/*     */     } catch (HttpException e) {
/* 117 */       System.err.println("Fatal protocol violation: " + e.getMessage());
/*     */ 
/* 121 */       method.releaseConnection();
/* 122 */       if (ins != null)
/*     */         try {
/* 124 */           ins.close();
/*     */         }
/*     */         catch (IOException e2) {
/* 127 */           e2.printStackTrace();
/*     */         }
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/* 119 */       System.err.println("Fatal transport error: " + e.getMessage());
/*     */ 
/* 121 */       method.releaseConnection();
/* 122 */       if (ins != null)
/*     */         try {
/* 124 */           ins.close();
/*     */         }
/*     */         catch (IOException e1) {
/* 127 */           e1.printStackTrace();
/*     */         }
/*     */     }
/*     */     finally
/*     */     {
/* 121 */       method.releaseConnection();
/* 122 */       if (ins != null) {
/*     */         try {
/* 124 */           ins.close();
/*     */         }
/*     */         catch (IOException e) {
/* 127 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/* 131 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   private String gen_access_token() {
/* 135 */     String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + this.appid + "&secret=" + this.appsecret;
/* 136 */     String body = CrawlerBody(url);
/* 137 */     System.out.println(body);
/* 138 */     JSONObject jo = JSONObject.fromObject(body);
/* 139 */     String r = jo.get("access_token").toString();
/* 140 */     return r;
/*     */   }
/*     */ 
/*     */   public String get_signature(String url) {
/* 144 */     String r = "";
/* 145 */     String noncestr = UUID.randomUUID().toString();
/* 146 */     String jsapi_ticket = get_jsapi_ticket();
/* 147 */     long timestamp = System.currentTimeMillis();
/* 148 */     String string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=" + url;
/* 149 */     r = EncoderHandler.encode("SHA1", string1);
/* 150 */     JSONObject jo = new JSONObject();
/* 151 */     jo.accumulate("appId", this.appid);
/* 152 */     jo.accumulate("timestamp", timestamp);
/* 153 */     jo.accumulate("nonceStr", noncestr);
/* 154 */     jo.accumulate("signature", r);
/* 155 */     return jo.toString();
/*     */   }
/*     */   public static void main(String[] args) {
/* 158 */     WXBL wx = getInstance();
/* 159 */     String a = wx.get_access_token();
/*     */   }
/*     */ }

/* Location:           E:\workspace_lyj\Tomcat6_8080\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.sinosoft.lis.wx.WXBL
 * JD-Core Version:    0.6.0
 */