package com.sinosoft.lis.admin;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.sinosoft.lis.pubfun.CacheInfo;
import com.sinosoft.lis.pubfun.MMap;
import com.sinosoft.lis.pubfun.PubFun;
import com.sinosoft.lis.pubfun.PubSubmit;
import com.sinosoft.lis.schema.SDContentSchema;
import com.sinosoft.lis.vschema.SDContentSet;
import com.sinosoft.lis.vschema.SDCrawlerContentSet;



public class CrawlerBL 
{   
    public CrawlerBL()
    {
    	
    }    
   

    public void crawler()
    {
    	String URL01 = "http://hq.tsinghua.edu.cn/front/frontAction.do?ms=gotoSecond&lmid=12471";
    	String r01 = CrawlerBody(URL01);
    	CraelerNoticeList01(r01);
    	String URL02 = "http://hq.tsinghua.edu.cn/front/frontAction.do?ms=gotoSecond&lmid=12494";
    	String r02 = CrawlerBody(URL02);
    	CraelerNoticeList02(r02);
    }

    public String CrawlerBody(String URL)
    {
    	HttpClient client = new HttpClient();  
        StringBuilder sb = new StringBuilder();  
        InputStream ins = null;  
        // Create a method instance.  
        GetMethod method = new GetMethod(URL);  
        // Provide custom retry handler is necessary  
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,  
                new DefaultHttpMethodRetryHandler(3, false));  
        try {  
            // Execute the method.  
            int statusCode = client.executeMethod(method);  
            System.out.println(statusCode);  
            if (statusCode == HttpStatus.SC_OK) {  
                ins = method.getResponseBodyAsStream();  
                byte[] b = new byte[1024];  
                int r_len = 0;  
                while ((r_len = ins.read(b)) > 0) {  
                    sb.append(new String(b, 0, r_len, method  
                            .getResponseCharSet()));  
                }  
            } else {  
                System.err.println("Response Code: " + statusCode);  
            }  
        } catch (HttpException e) {  
            System.err.println("Fatal protocol violation: " + e.getMessage());  
        } catch (IOException e) {  
            System.err.println("Fatal transport error: " + e.getMessage());  
        } finally {  
            method.releaseConnection();  
            if (ins != null) {  
                try {
					ins.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
            }  
        }  
        return sb.toString();
    }
	public void CraelerNoticeList01(String r)
	{
		String[] a = r.split("<section class=\"r_cont\">");
    	r=a[1];
    	a=r.split("<section class=\"page\">");
    	r=a[0];
    	a=r.split("<dl>");
    	for(int i=1;i<a.length;i++)
    	{
    		String ri = a[i];
    		ri=ri.replace("\\ ", "");
    		ri=ri.replace("\r\n", "");
    		String c1="";
    		String c2="";
    		String Title="";
    		String From="";
    		String Date="";
    		c1=ri.split("javascript:gotoDetail")[1].split("\" class=\"left\" title=\"")[0];
        	c2=c1.substring(10, 15);
        	c1=c1.substring(2, 7);
        	Title=ri.split("javascript:gotoDetail")[1].split("\" class=\"left\" title=\"")[1].split(">")[1].split("<")[0];
        	From=ri.split("javascript:gotoDetail")[2];
        	From=From.split("\\[")[1].split("\\]")[0];
        	Date=ri.split("javascript:gotoDetail")[2];
        	Date=Date.split("<span class=\"right\">")[1].split("</span>")[0];
//        	System.out.println(Date);
        	if(!PubFun.checkZero("select count(*) from sdcontent where r4='notice' and ContentTitle='"+Title+"'"))
        		continue;
        	SDContentSchema sdcc = new SDContentSchema();
        	sdcc.setChannelCode("C0042");
        	sdcc.setR4("notice");
        	sdcc.setContentTitle(Title);
        	sdcc.setContentFrom(From);
        	sdcc.setR1("Y");
        	sdcc.setR2(c1+"|"+c2);
        	sdcc.setIssueDate(Date);
        	sdcc.setR3("http://hq.tsinghua.edu.cn/front/frontAction.do?ms=gotoThird&lmid="+c2+"&xxid="+c1+"");
			sdcc.setContentHtml(CrawlerContent(sdcc.getR3()));
			String mSNO = PubFun.CreateMaxNo("CT", "CT", 10);
        	sdcc.setSerialNo(mSNO);
        	MMap map = new MMap();
        	map.put(sdcc, "INSERT");
        	new PubSubmit().submitData(map);       
        	new CacheInfo().genContentInfo(mSNO);
    	}        
    	new CacheInfo().genContentList();
	}
	public void CraelerNoticeList02(String r)
	{
		String[] a = r.split("<section class=\"r_cont\">");
		r=a[1];
		a=r.split("<section class=\"page\">");
		r=a[0];
		a=r.split("<dl>");
		for(int i=1;i<a.length;i++)
		{
			String ri = a[i];
			ri=ri.replace("\\ ", "");
			ri=ri.replace("\r\n", "");
			String c1="";
			String c2="";
			String Title="";
			String From="";
			String Date="";
			c1=ri.split("javascript:gotoDetail")[1].split("\" class=\"left\" title=\"")[0];
			c2=c1.substring(10, 15);
			c1=c1.substring(2, 7);
			Title=ri.split("javascript:gotoDetail")[1].split("\" class=\"left\" title=\"")[1].split(">")[1].split("<")[0];
			From=ri.split("javascript:gotoDetail")[2];
			From=From.split("\\[")[1].split("\\]")[0];
			Date=ri.split("javascript:gotoDetail")[2];
			Date=Date.split("<span class=\"right\">")[1].split("</span>")[0];
//        	System.out.println(Date);
        	if(!PubFun.checkZero("select count(*) from sdcontent where r4='news' and ContentTitle='"+Title+"'"))
        		continue;
			SDContentSchema sdcc = new SDContentSchema();
			sdcc.setChannelCode("C0041");
			sdcc.setR4("news");
			sdcc.setContentTitle(Title);
			sdcc.setContentFrom(From);
			sdcc.setR1("Y");
			sdcc.setR2(c1+"|"+c2);
			sdcc.setIssueDate(Date);
			sdcc.setR3("http://hq.tsinghua.edu.cn/front/frontAction.do?ms=gotoThird&lmid="+c2+"&xxid="+c1+"");
			sdcc.setContentHtml(CrawlerContent(sdcc.getR3()));
			String mSNO = PubFun.CreateMaxNo("CT", "CT", 10);
        	sdcc.setSerialNo(mSNO);
        	MMap map = new MMap();
        	map.put(sdcc, "INSERT");
        	new PubSubmit().submitData(map);       
        	new CacheInfo().genContentInfo(mSNO);
		}
    	new CacheInfo().genContentList();
	}
	public String CrawlerContent(String url)
	{
		String str = CrawlerBody(url);
		str=str.split("<article class=\"ContRight\">")[1].split("<ul>")[1].split("</ul>")[0];		
		return str;
	}
    public static void main(String[] args)
    {
    	String URL = "http://hq.tsinghua.edu.cn/front/frontAction.do?ms=gotoThird&lmid=12471&xxid=66800";
    	String r = new CrawlerBL().CrawlerContent(URL);
    	System.out.println(r);
    }
}
