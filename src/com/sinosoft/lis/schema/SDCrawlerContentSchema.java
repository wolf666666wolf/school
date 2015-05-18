/**
 * Copyright (c) 2002 sinosoft  Co. Ltd.
 * All right reserved.
 */

package com.sinosoft.lis.schema;

import java.sql.*;
import java.io.*;
import java.util.Date;
import com.sinosoft.lis.pubfun.FDate;
import com.sinosoft.utility.*;
import com.sinosoft.lis.db.SDCrawlerContentDB;

/*
 * <p>ClassName: SDCrawlerContentSchema </p>
 * <p>Description: DB�� Schema ���ļ� </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: sinosoft </p>
 * @Database: PhysicalDataModel_1
 */
public class SDCrawlerContentSchema implements Schema, Cloneable
{
	// @Field
	/** ��ˮ�� */
	private String SerialNo;
	/** ��Ŀ���� */
	private String ChannelCode;
	/** ���� */
	private String ContentTitle;
	/** ����(�ı�) */
	private String ContentTxt;
	/** ����(��ʽ) */
	private String ContentHtml;
	/** �������� */
	private String IssueDate;
	/** ����ʱ�� */
	private String IssueTime;
	/** ��Դ */
	private String ContentFrom;
	/** �����ֶ�1 */
	private String R1;
	/** �����ֶ�2 */
	private String R2;
	/** �����ֶ�3 */
	private String R3;
	/** �����ֶ�4 */
	private String R4;
	/** �����ֶ�5 */
	private String R5;
	/** �����ֶ�6 */
	private String R6;
	/** �����ֶ�7 */
	private String R7;
	/** �����ֶ�8 */
	private String R8;
	/** �����ֶ�9 */
	private String R9;
	/** �����ֶ�10 */
	private String R10;
	/** �����ֶ�11 */
	private String R11;
	/** �����ֶ�12 */
	private String R12;
	/** �����ֶ�13 */
	private String R13;
	/** �����ֶ�14 */
	private String R14;
	/** �����ֶ�15 */
	private String R15;

	public static final int FIELDNUM = 23;	// ���ݿ����ֶθ���

	private static String[] PK;				// ����

	public CErrors mErrors;			// ������Ϣ

	// @Constructor
	public SDCrawlerContentSchema()
	{
		mErrors = new CErrors();

		String[] pk = new String[1];
		pk[0] = "SerialNo";

		PK = pk;
	}

	/**
	* Schema��¡
	* @return Object
	* @throws CloneNotSupportedException
	*/
	public Object clone()
		throws CloneNotSupportedException
	{
		SDCrawlerContentSchema cloned = (SDCrawlerContentSchema)super.clone();
		cloned.mErrors = (CErrors) mErrors.clone();
		return cloned;
	}

	// @Method
	public String[] getPK()
	{
		return PK;
	}

	public String getSerialNo()
	{
if(SerialNo==null)SerialNo="";
		return SerialNo;
	}
	public void setSerialNo(String aSerialNo)
	{
		SerialNo = aSerialNo;
	}
	public String getChannelCode()
	{
if(ChannelCode==null)ChannelCode="";
		return ChannelCode;
	}
	public void setChannelCode(String aChannelCode)
	{
		ChannelCode = aChannelCode;
	}
	public String getContentTitle()
	{
if(ContentTitle==null)ContentTitle="";
		return ContentTitle;
	}
	public void setContentTitle(String aContentTitle)
	{
		ContentTitle = aContentTitle;
	}
	public String getContentTxt()
	{
if(ContentTxt==null)ContentTxt="";
		return ContentTxt;
	}
	public void setContentTxt(String aContentTxt)
	{
		ContentTxt = aContentTxt;
	}
	public String getContentHtml()
	{
if(ContentHtml==null)ContentHtml="";
		return ContentHtml;
	}
	public void setContentHtml(String aContentHtml)
	{
		ContentHtml = aContentHtml;
	}
	public String getIssueDate()
	{
if(IssueDate==null)IssueDate="";
		return IssueDate;
	}
	public void setIssueDate(String aIssueDate)
	{
		IssueDate = aIssueDate;
	}
	public String getIssueTime()
	{
if(IssueTime==null)IssueTime="";
		return IssueTime;
	}
	public void setIssueTime(String aIssueTime)
	{
		IssueTime = aIssueTime;
	}
	public String getContentFrom()
	{
if(ContentFrom==null)ContentFrom="";
		return ContentFrom;
	}
	public void setContentFrom(String aContentFrom)
	{
		ContentFrom = aContentFrom;
	}
	public String getR1()
	{
if(R1==null)R1="";
		return R1;
	}
	public void setR1(String aR1)
	{
		R1 = aR1;
	}
	public String getR2()
	{
if(R2==null)R2="";
		return R2;
	}
	public void setR2(String aR2)
	{
		R2 = aR2;
	}
	public String getR3()
	{
if(R3==null)R3="";
		return R3;
	}
	public void setR3(String aR3)
	{
		R3 = aR3;
	}
	public String getR4()
	{
if(R4==null)R4="";
		return R4;
	}
	public void setR4(String aR4)
	{
		R4 = aR4;
	}
	public String getR5()
	{
if(R5==null)R5="";
		return R5;
	}
	public void setR5(String aR5)
	{
		R5 = aR5;
	}
	public String getR6()
	{
if(R6==null)R6="";
		return R6;
	}
	public void setR6(String aR6)
	{
		R6 = aR6;
	}
	public String getR7()
	{
if(R7==null)R7="";
		return R7;
	}
	public void setR7(String aR7)
	{
		R7 = aR7;
	}
	public String getR8()
	{
if(R8==null)R8="";
		return R8;
	}
	public void setR8(String aR8)
	{
		R8 = aR8;
	}
	public String getR9()
	{
if(R9==null)R9="";
		return R9;
	}
	public void setR9(String aR9)
	{
		R9 = aR9;
	}
	public String getR10()
	{
if(R10==null)R10="";
		return R10;
	}
	public void setR10(String aR10)
	{
		R10 = aR10;
	}
	public String getR11()
	{
if(R11==null)R11="";
		return R11;
	}
	public void setR11(String aR11)
	{
		R11 = aR11;
	}
	public String getR12()
	{
if(R12==null)R12="";
		return R12;
	}
	public void setR12(String aR12)
	{
		R12 = aR12;
	}
	public String getR13()
	{
if(R13==null)R13="";
		return R13;
	}
	public void setR13(String aR13)
	{
		R13 = aR13;
	}
	public String getR14()
	{
if(R14==null)R14="";
		return R14;
	}
	public void setR14(String aR14)
	{
		R14 = aR14;
	}
	public String getR15()
	{
if(R15==null)R15="";
		return R15;
	}
	public void setR15(String aR15)
	{
		R15 = aR15;
	}

	/**
	* ʹ������һ�� SDCrawlerContentSchema ����� Schema ��ֵ
	* @param: aSDCrawlerContentSchema SDCrawlerContentSchema
	**/
	public void setSchema(SDCrawlerContentSchema aSDCrawlerContentSchema)
	{
		this.SerialNo = aSDCrawlerContentSchema.getSerialNo();
		this.ChannelCode = aSDCrawlerContentSchema.getChannelCode();
		this.ContentTitle = aSDCrawlerContentSchema.getContentTitle();
		this.ContentTxt = aSDCrawlerContentSchema.getContentTxt();
		this.ContentHtml = aSDCrawlerContentSchema.getContentHtml();
		this.IssueDate = aSDCrawlerContentSchema.getIssueDate();
		this.IssueTime = aSDCrawlerContentSchema.getIssueTime();
		this.ContentFrom = aSDCrawlerContentSchema.getContentFrom();
		this.R1 = aSDCrawlerContentSchema.getR1();
		this.R2 = aSDCrawlerContentSchema.getR2();
		this.R3 = aSDCrawlerContentSchema.getR3();
		this.R4 = aSDCrawlerContentSchema.getR4();
		this.R5 = aSDCrawlerContentSchema.getR5();
		this.R6 = aSDCrawlerContentSchema.getR6();
		this.R7 = aSDCrawlerContentSchema.getR7();
		this.R8 = aSDCrawlerContentSchema.getR8();
		this.R9 = aSDCrawlerContentSchema.getR9();
		this.R10 = aSDCrawlerContentSchema.getR10();
		this.R11 = aSDCrawlerContentSchema.getR11();
		this.R12 = aSDCrawlerContentSchema.getR12();
		this.R13 = aSDCrawlerContentSchema.getR13();
		this.R14 = aSDCrawlerContentSchema.getR14();
		this.R15 = aSDCrawlerContentSchema.getR15();
	}

	/**
	* ʹ�� ResultSet �еĵ� i �и� Schema ��ֵ
	* @param: rs ResultSet
	* @param: i int
	* @return: boolean
	**/
	public boolean setSchema(ResultSet rs,int i)
	{
		try
		{
			//rs.absolute(i);		// �ǹ����α�
			if( rs.getString("SerialNo") == null )
				this.SerialNo = null;
			else
				this.SerialNo = rs.getString("SerialNo").trim();

			if( rs.getString("ChannelCode") == null )
				this.ChannelCode = null;
			else
				this.ChannelCode = rs.getString("ChannelCode").trim();

			if( rs.getString("ContentTitle") == null )
				this.ContentTitle = null;
			else
				this.ContentTitle = rs.getString("ContentTitle").trim();

			if( rs.getString("ContentTxt") == null )
				this.ContentTxt = null;
			else
				this.ContentTxt = rs.getString("ContentTxt").trim();

			if( rs.getString("ContentHtml") == null )
				this.ContentHtml = null;
			else
				this.ContentHtml = rs.getString("ContentHtml").trim();

			if( rs.getString("IssueDate") == null )
				this.IssueDate = null;
			else
				this.IssueDate = rs.getString("IssueDate").trim();

			if( rs.getString("IssueTime") == null )
				this.IssueTime = null;
			else
				this.IssueTime = rs.getString("IssueTime").trim();

			if( rs.getString("ContentFrom") == null )
				this.ContentFrom = null;
			else
				this.ContentFrom = rs.getString("ContentFrom").trim();

			if( rs.getString("R1") == null )
				this.R1 = null;
			else
				this.R1 = rs.getString("R1").trim();

			if( rs.getString("R2") == null )
				this.R2 = null;
			else
				this.R2 = rs.getString("R2").trim();

			if( rs.getString("R3") == null )
				this.R3 = null;
			else
				this.R3 = rs.getString("R3").trim();

			if( rs.getString("R4") == null )
				this.R4 = null;
			else
				this.R4 = rs.getString("R4").trim();

			if( rs.getString("R5") == null )
				this.R5 = null;
			else
				this.R5 = rs.getString("R5").trim();

			if( rs.getString("R6") == null )
				this.R6 = null;
			else
				this.R6 = rs.getString("R6").trim();

			if( rs.getString("R7") == null )
				this.R7 = null;
			else
				this.R7 = rs.getString("R7").trim();

			if( rs.getString("R8") == null )
				this.R8 = null;
			else
				this.R8 = rs.getString("R8").trim();

			if( rs.getString("R9") == null )
				this.R9 = null;
			else
				this.R9 = rs.getString("R9").trim();

			if( rs.getString("R10") == null )
				this.R10 = null;
			else
				this.R10 = rs.getString("R10").trim();

			if( rs.getString("R11") == null )
				this.R11 = null;
			else
				this.R11 = rs.getString("R11").trim();

			if( rs.getString("R12") == null )
				this.R12 = null;
			else
				this.R12 = rs.getString("R12").trim();

			if( rs.getString("R13") == null )
				this.R13 = null;
			else
				this.R13 = rs.getString("R13").trim();

			if( rs.getString("R14") == null )
				this.R14 = null;
			else
				this.R14 = rs.getString("R14").trim();

			if( rs.getString("R15") == null )
				this.R15 = null;
			else
				this.R15 = rs.getString("R15").trim();

		}
		catch(SQLException sqle)
		{
			System.out.println("���ݿ��е�SDCrawlerContent���ֶθ�����Schema�е��ֶθ�����һ�£�����ִ��db.executeQuery��ѯʱû��ʹ��select * from tables");
			// @@������
			CError tError = new CError();
			tError.moduleName = "SDCrawlerContentSchema";
			tError.functionName = "setSchema";
			tError.errorMessage = sqle.toString();
			this.mErrors .addOneError(tError);
			return false;
		}
		return true;
	}

	public SDCrawlerContentSchema getSchema()
	{
		SDCrawlerContentSchema aSDCrawlerContentSchema = new SDCrawlerContentSchema();
		aSDCrawlerContentSchema.setSchema(this);
		return aSDCrawlerContentSchema;
	}

	public SDCrawlerContentDB getDB()
	{
		SDCrawlerContentDB aDBOper = new SDCrawlerContentDB();
		aDBOper.setSchema(this);
		return aDBOper;
	}


	/**
	* ���ݴ������ XML ��ʽ�����˳��μ�<A href ={@docRoot}/dataStructure/tb.html#PrpSDCrawlerContent����/A>���ֶ�
	* @return: String ���ش�����ַ���
	**/
	public String encode()
	{
		StringBuffer strReturn = new StringBuffer(256);
		strReturn.append(StrTool.cTrim(SerialNo)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(ChannelCode)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(ContentTitle)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(ContentTxt)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(ContentHtml)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(IssueDate)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(IssueTime)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(ContentFrom)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(R1)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(R2)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(R3)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(R4)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(R5)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(R6)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(R7)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(R8)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(R9)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(R10)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(R11)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(R12)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(R13)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(R14)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(R15));
		return strReturn.toString();
	}

	/**
	* ���ݽ�������˳��μ�<A href ={@docRoot}/dataStructure/tb.html#PrpSDCrawlerContent>��ʷ����ƾ֤������Ϣ</A>���ֶ�
	* @param: strMessage String ����һ����¼���ݵ��ַ���
	* @return: boolean
	**/
	public boolean decode(String strMessage)
	{
		try
		{
			SerialNo = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 1, SysConst.PACKAGESPILTER );
			ChannelCode = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 2, SysConst.PACKAGESPILTER );
			ContentTitle = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 3, SysConst.PACKAGESPILTER );
			ContentTxt = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 4, SysConst.PACKAGESPILTER );
			ContentHtml = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 5, SysConst.PACKAGESPILTER );
			IssueDate = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 6, SysConst.PACKAGESPILTER );
			IssueTime = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 7, SysConst.PACKAGESPILTER );
			ContentFrom = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 8, SysConst.PACKAGESPILTER );
			R1 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 9, SysConst.PACKAGESPILTER );
			R2 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 10, SysConst.PACKAGESPILTER );
			R3 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 11, SysConst.PACKAGESPILTER );
			R4 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 12, SysConst.PACKAGESPILTER );
			R5 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 13, SysConst.PACKAGESPILTER );
			R6 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 14, SysConst.PACKAGESPILTER );
			R7 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 15, SysConst.PACKAGESPILTER );
			R8 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 16, SysConst.PACKAGESPILTER );
			R9 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 17, SysConst.PACKAGESPILTER );
			R10 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 18, SysConst.PACKAGESPILTER );
			R11 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 19, SysConst.PACKAGESPILTER );
			R12 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 20, SysConst.PACKAGESPILTER );
			R13 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 21, SysConst.PACKAGESPILTER );
			R14 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 22, SysConst.PACKAGESPILTER );
			R15 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 23, SysConst.PACKAGESPILTER );
		}
		catch(NumberFormatException ex)
		{
			// @@������
			CError tError = new CError();
			tError.moduleName = "SDCrawlerContentSchema";
			tError.functionName = "decode";
			tError.errorMessage = ex.toString();
			this.mErrors .addOneError(tError);

			return false;
		}
		return true;
	}

	/**
	* ȡ�ö�Ӧ���������String��ʽ���ֶ�ֵ
	* @param: FCode String ϣ��ȡ�õ��ֶ���
	* @return: String
	* ���û�ж�Ӧ���ֶΣ�����""
	* ����ֶ�ֵΪ�գ�����"null"
	**/
	public String getV(String FCode)
	{
		String strReturn = "";
		if (FCode.equalsIgnoreCase("SerialNo"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(SerialNo));
		}
		if (FCode.equalsIgnoreCase("ChannelCode"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(ChannelCode));
		}
		if (FCode.equalsIgnoreCase("ContentTitle"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(ContentTitle));
		}
		if (FCode.equalsIgnoreCase("ContentTxt"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(ContentTxt));
		}
		if (FCode.equalsIgnoreCase("ContentHtml"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(ContentHtml));
		}
		if (FCode.equalsIgnoreCase("IssueDate"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(IssueDate));
		}
		if (FCode.equalsIgnoreCase("IssueTime"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(IssueTime));
		}
		if (FCode.equalsIgnoreCase("ContentFrom"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(ContentFrom));
		}
		if (FCode.equalsIgnoreCase("R1"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(R1));
		}
		if (FCode.equalsIgnoreCase("R2"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(R2));
		}
		if (FCode.equalsIgnoreCase("R3"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(R3));
		}
		if (FCode.equalsIgnoreCase("R4"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(R4));
		}
		if (FCode.equalsIgnoreCase("R5"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(R5));
		}
		if (FCode.equalsIgnoreCase("R6"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(R6));
		}
		if (FCode.equalsIgnoreCase("R7"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(R7));
		}
		if (FCode.equalsIgnoreCase("R8"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(R8));
		}
		if (FCode.equalsIgnoreCase("R9"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(R9));
		}
		if (FCode.equalsIgnoreCase("R10"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(R10));
		}
		if (FCode.equalsIgnoreCase("R11"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(R11));
		}
		if (FCode.equalsIgnoreCase("R12"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(R12));
		}
		if (FCode.equalsIgnoreCase("R13"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(R13));
		}
		if (FCode.equalsIgnoreCase("R14"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(R14));
		}
		if (FCode.equalsIgnoreCase("R15"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(R15));
		}
		if (strReturn.equals(""))
		{
			strReturn = "null";
		}

		return strReturn;
	}


	/**
	* ȡ��Schema��ָ������ֵ����Ӧ���ֶ�ֵ
	* @param: nFieldIndex int ָ�����ֶ�����ֵ
	* @return: String
	* ���û�ж�Ӧ���ֶΣ�����""
	* ����ֶ�ֵΪ�գ�����"null"
	**/
	public String getV(int nFieldIndex)
	{
		String strFieldValue = "";
		switch(nFieldIndex) {
			case 0:
				strFieldValue = StrTool.GBKToUnicode(SerialNo);
				break;
			case 1:
				strFieldValue = StrTool.GBKToUnicode(ChannelCode);
				break;
			case 2:
				strFieldValue = StrTool.GBKToUnicode(ContentTitle);
				break;
			case 3:
				strFieldValue = StrTool.GBKToUnicode(ContentTxt);
				break;
			case 4:
				strFieldValue = StrTool.GBKToUnicode(ContentHtml);
				break;
			case 5:
				strFieldValue = StrTool.GBKToUnicode(IssueDate);
				break;
			case 6:
				strFieldValue = StrTool.GBKToUnicode(IssueTime);
				break;
			case 7:
				strFieldValue = StrTool.GBKToUnicode(ContentFrom);
				break;
			case 8:
				strFieldValue = StrTool.GBKToUnicode(R1);
				break;
			case 9:
				strFieldValue = StrTool.GBKToUnicode(R2);
				break;
			case 10:
				strFieldValue = StrTool.GBKToUnicode(R3);
				break;
			case 11:
				strFieldValue = StrTool.GBKToUnicode(R4);
				break;
			case 12:
				strFieldValue = StrTool.GBKToUnicode(R5);
				break;
			case 13:
				strFieldValue = StrTool.GBKToUnicode(R6);
				break;
			case 14:
				strFieldValue = StrTool.GBKToUnicode(R7);
				break;
			case 15:
				strFieldValue = StrTool.GBKToUnicode(R8);
				break;
			case 16:
				strFieldValue = StrTool.GBKToUnicode(R9);
				break;
			case 17:
				strFieldValue = StrTool.GBKToUnicode(R10);
				break;
			case 18:
				strFieldValue = StrTool.GBKToUnicode(R11);
				break;
			case 19:
				strFieldValue = StrTool.GBKToUnicode(R12);
				break;
			case 20:
				strFieldValue = StrTool.GBKToUnicode(R13);
				break;
			case 21:
				strFieldValue = StrTool.GBKToUnicode(R14);
				break;
			case 22:
				strFieldValue = StrTool.GBKToUnicode(R15);
				break;
			default:
				strFieldValue = "";
		};
		if( strFieldValue.equals("") ) {
			strFieldValue = "null";
		}
		return strFieldValue;
	}

	/**
	* ���ö�Ӧ���������String��ʽ���ֶ�ֵ
	* @param: FCode String ��Ҫ��ֵ�Ķ���
	* @param: FValue String Ҫ����ֵ
	* @return: boolean
	**/
	public boolean setV(String FCode ,String FValue)
	{
		if( StrTool.cTrim( FCode ).equals( "" ))
			return false;

		if (FCode.equalsIgnoreCase("SerialNo"))
		{
			if( FValue != null )
			{
				SerialNo = FValue.trim();
			}
			else
				SerialNo = null;
		}
		if (FCode.equalsIgnoreCase("ChannelCode"))
		{
			if( FValue != null )
			{
				ChannelCode = FValue.trim();
			}
			else
				ChannelCode = null;
		}
		if (FCode.equalsIgnoreCase("ContentTitle"))
		{
			if( FValue != null )
			{
				ContentTitle = FValue.trim();
			}
			else
				ContentTitle = null;
		}
		if (FCode.equalsIgnoreCase("ContentTxt"))
		{
			if( FValue != null )
			{
				ContentTxt = FValue.trim();
			}
			else
				ContentTxt = null;
		}
		if (FCode.equalsIgnoreCase("ContentHtml"))
		{
			if( FValue != null )
			{
				ContentHtml = FValue.trim();
			}
			else
				ContentHtml = null;
		}
		if (FCode.equalsIgnoreCase("IssueDate"))
		{
			if( FValue != null )
			{
				IssueDate = FValue.trim();
			}
			else
				IssueDate = null;
		}
		if (FCode.equalsIgnoreCase("IssueTime"))
		{
			if( FValue != null )
			{
				IssueTime = FValue.trim();
			}
			else
				IssueTime = null;
		}
		if (FCode.equalsIgnoreCase("ContentFrom"))
		{
			if( FValue != null )
			{
				ContentFrom = FValue.trim();
			}
			else
				ContentFrom = null;
		}
		if (FCode.equalsIgnoreCase("R1"))
		{
			if( FValue != null )
			{
				R1 = FValue.trim();
			}
			else
				R1 = null;
		}
		if (FCode.equalsIgnoreCase("R2"))
		{
			if( FValue != null )
			{
				R2 = FValue.trim();
			}
			else
				R2 = null;
		}
		if (FCode.equalsIgnoreCase("R3"))
		{
			if( FValue != null )
			{
				R3 = FValue.trim();
			}
			else
				R3 = null;
		}
		if (FCode.equalsIgnoreCase("R4"))
		{
			if( FValue != null )
			{
				R4 = FValue.trim();
			}
			else
				R4 = null;
		}
		if (FCode.equalsIgnoreCase("R5"))
		{
			if( FValue != null )
			{
				R5 = FValue.trim();
			}
			else
				R5 = null;
		}
		if (FCode.equalsIgnoreCase("R6"))
		{
			if( FValue != null )
			{
				R6 = FValue.trim();
			}
			else
				R6 = null;
		}
		if (FCode.equalsIgnoreCase("R7"))
		{
			if( FValue != null )
			{
				R7 = FValue.trim();
			}
			else
				R7 = null;
		}
		if (FCode.equalsIgnoreCase("R8"))
		{
			if( FValue != null )
			{
				R8 = FValue.trim();
			}
			else
				R8 = null;
		}
		if (FCode.equalsIgnoreCase("R9"))
		{
			if( FValue != null )
			{
				R9 = FValue.trim();
			}
			else
				R9 = null;
		}
		if (FCode.equalsIgnoreCase("R10"))
		{
			if( FValue != null )
			{
				R10 = FValue.trim();
			}
			else
				R10 = null;
		}
		if (FCode.equalsIgnoreCase("R11"))
		{
			if( FValue != null )
			{
				R11 = FValue.trim();
			}
			else
				R11 = null;
		}
		if (FCode.equalsIgnoreCase("R12"))
		{
			if( FValue != null )
			{
				R12 = FValue.trim();
			}
			else
				R12 = null;
		}
		if (FCode.equalsIgnoreCase("R13"))
		{
			if( FValue != null )
			{
				R13 = FValue.trim();
			}
			else
				R13 = null;
		}
		if (FCode.equalsIgnoreCase("R14"))
		{
			if( FValue != null )
			{
				R14 = FValue.trim();
			}
			else
				R14 = null;
		}
		if (FCode.equalsIgnoreCase("R15"))
		{
			if( FValue != null )
			{
				R15 = FValue.trim();
			}
			else
				R15 = null;
		}
		return true;
	}

	public boolean equals(Object otherObject)
	{
		if (this == otherObject) return true;
		if (otherObject == null) return false;
		if (getClass() != otherObject.getClass()) return false;
		SDCrawlerContentSchema other = (SDCrawlerContentSchema)otherObject;
		return
			SerialNo.equals(other.getSerialNo())
			&& ChannelCode.equals(other.getChannelCode())
			&& ContentTitle.equals(other.getContentTitle())
			&& ContentTxt.equals(other.getContentTxt())
			&& ContentHtml.equals(other.getContentHtml())
			&& IssueDate.equals(other.getIssueDate())
			&& IssueTime.equals(other.getIssueTime())
			&& ContentFrom.equals(other.getContentFrom())
			&& R1.equals(other.getR1())
			&& R2.equals(other.getR2())
			&& R3.equals(other.getR3())
			&& R4.equals(other.getR4())
			&& R5.equals(other.getR5())
			&& R6.equals(other.getR6())
			&& R7.equals(other.getR7())
			&& R8.equals(other.getR8())
			&& R9.equals(other.getR9())
			&& R10.equals(other.getR10())
			&& R11.equals(other.getR11())
			&& R12.equals(other.getR12())
			&& R13.equals(other.getR13())
			&& R14.equals(other.getR14())
			&& R15.equals(other.getR15());
	}

	/**
	* ȡ��Schemaӵ���ֶε�����
       * @return: int
	**/
	public int getFieldCount()
	{
 		return FIELDNUM;
	}

	/**
	* ȡ��Schema��ָ���ֶ�������Ӧ������ֵ
	* ���û�ж�Ӧ���ֶΣ�����-1
       * @param: strFieldName String
       * @return: int
	**/
	public int getFieldIndex(String strFieldName)
	{
		if( strFieldName.equals("SerialNo") ) {
			return 0;
		}
		if( strFieldName.equals("ChannelCode") ) {
			return 1;
		}
		if( strFieldName.equals("ContentTitle") ) {
			return 2;
		}
		if( strFieldName.equals("ContentTxt") ) {
			return 3;
		}
		if( strFieldName.equals("ContentHtml") ) {
			return 4;
		}
		if( strFieldName.equals("IssueDate") ) {
			return 5;
		}
		if( strFieldName.equals("IssueTime") ) {
			return 6;
		}
		if( strFieldName.equals("ContentFrom") ) {
			return 7;
		}
		if( strFieldName.equals("R1") ) {
			return 8;
		}
		if( strFieldName.equals("R2") ) {
			return 9;
		}
		if( strFieldName.equals("R3") ) {
			return 10;
		}
		if( strFieldName.equals("R4") ) {
			return 11;
		}
		if( strFieldName.equals("R5") ) {
			return 12;
		}
		if( strFieldName.equals("R6") ) {
			return 13;
		}
		if( strFieldName.equals("R7") ) {
			return 14;
		}
		if( strFieldName.equals("R8") ) {
			return 15;
		}
		if( strFieldName.equals("R9") ) {
			return 16;
		}
		if( strFieldName.equals("R10") ) {
			return 17;
		}
		if( strFieldName.equals("R11") ) {
			return 18;
		}
		if( strFieldName.equals("R12") ) {
			return 19;
		}
		if( strFieldName.equals("R13") ) {
			return 20;
		}
		if( strFieldName.equals("R14") ) {
			return 21;
		}
		if( strFieldName.equals("R15") ) {
			return 22;
		}
		return -1;
	}

	/**
	* ȡ��Schema��ָ������ֵ����Ӧ���ֶ���
	* ���û�ж�Ӧ���ֶΣ�����""
       * @param: nFieldIndex int
       * @return: String
	**/
	public String getFieldName(int nFieldIndex)
	{
		String strFieldName = "";
		switch(nFieldIndex) {
			case 0:
				strFieldName = "SerialNo";
				break;
			case 1:
				strFieldName = "ChannelCode";
				break;
			case 2:
				strFieldName = "ContentTitle";
				break;
			case 3:
				strFieldName = "ContentTxt";
				break;
			case 4:
				strFieldName = "ContentHtml";
				break;
			case 5:
				strFieldName = "IssueDate";
				break;
			case 6:
				strFieldName = "IssueTime";
				break;
			case 7:
				strFieldName = "ContentFrom";
				break;
			case 8:
				strFieldName = "R1";
				break;
			case 9:
				strFieldName = "R2";
				break;
			case 10:
				strFieldName = "R3";
				break;
			case 11:
				strFieldName = "R4";
				break;
			case 12:
				strFieldName = "R5";
				break;
			case 13:
				strFieldName = "R6";
				break;
			case 14:
				strFieldName = "R7";
				break;
			case 15:
				strFieldName = "R8";
				break;
			case 16:
				strFieldName = "R9";
				break;
			case 17:
				strFieldName = "R10";
				break;
			case 18:
				strFieldName = "R11";
				break;
			case 19:
				strFieldName = "R12";
				break;
			case 20:
				strFieldName = "R13";
				break;
			case 21:
				strFieldName = "R14";
				break;
			case 22:
				strFieldName = "R15";
				break;
			default:
				strFieldName = "";
		};
		return strFieldName;
	}

	/**
	* ȡ��Schema��ָ���ֶ�������Ӧ���ֶ�����
	* ���û�ж�Ӧ���ֶΣ�����Schema.TYPE_NOFOUND
       * @param: strFieldName String
       * @return: int
	**/
	public int getFieldType(String strFieldName)
	{
		if( strFieldName.equals("SerialNo") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("ChannelCode") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("ContentTitle") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("ContentTxt") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("ContentHtml") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("IssueDate") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("IssueTime") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("ContentFrom") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("R1") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("R2") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("R3") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("R4") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("R5") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("R6") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("R7") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("R8") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("R9") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("R10") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("R11") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("R12") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("R13") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("R14") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("R15") ) {
			return Schema.TYPE_STRING;
		}
		return Schema.TYPE_NOFOUND;
	}

	/**
	* ȡ��Schema��ָ������ֵ����Ӧ���ֶ�����
	* ���û�ж�Ӧ���ֶΣ�����Schema.TYPE_NOFOUND
       * @param: nFieldIndex int
       * @return: int
	**/
	public int getFieldType(int nFieldIndex)
	{
		int nFieldType = Schema.TYPE_NOFOUND;
		switch(nFieldIndex) {
			case 0:
				nFieldType = Schema.TYPE_STRING;
				break;
			case 1:
				nFieldType = Schema.TYPE_STRING;
				break;
			case 2:
				nFieldType = Schema.TYPE_STRING;
				break;
			case 3:
				nFieldType = Schema.TYPE_STRING;
				break;
			case 4:
				nFieldType = Schema.TYPE_STRING;
				break;
			case 5:
				nFieldType = Schema.TYPE_STRING;
				break;
			case 6:
				nFieldType = Schema.TYPE_STRING;
				break;
			case 7:
				nFieldType = Schema.TYPE_STRING;
				break;
			case 8:
				nFieldType = Schema.TYPE_STRING;
				break;
			case 9:
				nFieldType = Schema.TYPE_STRING;
				break;
			case 10:
				nFieldType = Schema.TYPE_STRING;
				break;
			case 11:
				nFieldType = Schema.TYPE_STRING;
				break;
			case 12:
				nFieldType = Schema.TYPE_STRING;
				break;
			case 13:
				nFieldType = Schema.TYPE_STRING;
				break;
			case 14:
				nFieldType = Schema.TYPE_STRING;
				break;
			case 15:
				nFieldType = Schema.TYPE_STRING;
				break;
			case 16:
				nFieldType = Schema.TYPE_STRING;
				break;
			case 17:
				nFieldType = Schema.TYPE_STRING;
				break;
			case 18:
				nFieldType = Schema.TYPE_STRING;
				break;
			case 19:
				nFieldType = Schema.TYPE_STRING;
				break;
			case 20:
				nFieldType = Schema.TYPE_STRING;
				break;
			case 21:
				nFieldType = Schema.TYPE_STRING;
				break;
			case 22:
				nFieldType = Schema.TYPE_STRING;
				break;
			default:
				nFieldType = Schema.TYPE_NOFOUND;
		};
		return nFieldType;
	}
}
