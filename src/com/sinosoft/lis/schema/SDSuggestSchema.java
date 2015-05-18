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
import com.sinosoft.lis.db.SDSuggestDB;

/*
 * <p>ClassName: SDSuggestSchema </p>
 * <p>Description: DB�� Schema ���ļ� </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: sinosoft </p>
 * @Database: PhysicalDataModel_1
 */
public class SDSuggestSchema implements Schema, Cloneable
{
	// @Field
	/** ��ˮ�� */
	private String SerialNo;
	/** �û����� */
	private String UserType;
	/** �������ǳ� */
	private String NickName;
	/** ���� */
	private String Title;
	/** �ֻ� */
	private String Phone;
	/** ���� */
	private String Com;
	/** ���� */
	private String Content;
	/** �������� */
	private String SuggestDate;
	/** ע��ʱ�� */
	private String SuggestTime;
	/** ������ */
	private String DealFlag;
	/** ��ע */
	private String Remark;
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

	public static final int FIELDNUM = 26;	// ���ݿ����ֶθ���

	private static String[] PK;				// ����

	public CErrors mErrors;			// ������Ϣ

	// @Constructor
	public SDSuggestSchema()
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
		SDSuggestSchema cloned = (SDSuggestSchema)super.clone();
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
	/**
	* U,S,A
	*/
	public String getUserType()
	{
if(UserType==null)UserType="";
		return UserType;
	}
	public void setUserType(String aUserType)
	{
		UserType = aUserType;
	}
	public String getNickName()
	{
if(NickName==null)NickName="";
		return NickName;
	}
	public void setNickName(String aNickName)
	{
		NickName = aNickName;
	}
	public String getTitle()
	{
if(Title==null)Title="";
		return Title;
	}
	public void setTitle(String aTitle)
	{
		Title = aTitle;
	}
	public String getPhone()
	{
if(Phone==null)Phone="";
		return Phone;
	}
	public void setPhone(String aPhone)
	{
		Phone = aPhone;
	}
	public String getCom()
	{
if(Com==null)Com="";
		return Com;
	}
	public void setCom(String aCom)
	{
		Com = aCom;
	}
	public String getContent()
	{
if(Content==null)Content="";
		return Content;
	}
	public void setContent(String aContent)
	{
		Content = aContent;
	}
	public String getSuggestDate()
	{
if(SuggestDate==null)SuggestDate="";
		return SuggestDate;
	}
	public void setSuggestDate(String aSuggestDate)
	{
		SuggestDate = aSuggestDate;
	}
	public String getSuggestTime()
	{
if(SuggestTime==null)SuggestTime="";
		return SuggestTime;
	}
	public void setSuggestTime(String aSuggestTime)
	{
		SuggestTime = aSuggestTime;
	}
	public String getDealFlag()
	{
if(DealFlag==null)DealFlag="";
		return DealFlag;
	}
	public void setDealFlag(String aDealFlag)
	{
		DealFlag = aDealFlag;
	}
	public String getRemark()
	{
if(Remark==null)Remark="";
		return Remark;
	}
	public void setRemark(String aRemark)
	{
		Remark = aRemark;
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
	* ʹ������һ�� SDSuggestSchema ����� Schema ��ֵ
	* @param: aSDSuggestSchema SDSuggestSchema
	**/
	public void setSchema(SDSuggestSchema aSDSuggestSchema)
	{
		this.SerialNo = aSDSuggestSchema.getSerialNo();
		this.UserType = aSDSuggestSchema.getUserType();
		this.NickName = aSDSuggestSchema.getNickName();
		this.Title = aSDSuggestSchema.getTitle();
		this.Phone = aSDSuggestSchema.getPhone();
		this.Com = aSDSuggestSchema.getCom();
		this.Content = aSDSuggestSchema.getContent();
		this.SuggestDate = aSDSuggestSchema.getSuggestDate();
		this.SuggestTime = aSDSuggestSchema.getSuggestTime();
		this.DealFlag = aSDSuggestSchema.getDealFlag();
		this.Remark = aSDSuggestSchema.getRemark();
		this.R1 = aSDSuggestSchema.getR1();
		this.R2 = aSDSuggestSchema.getR2();
		this.R3 = aSDSuggestSchema.getR3();
		this.R4 = aSDSuggestSchema.getR4();
		this.R5 = aSDSuggestSchema.getR5();
		this.R6 = aSDSuggestSchema.getR6();
		this.R7 = aSDSuggestSchema.getR7();
		this.R8 = aSDSuggestSchema.getR8();
		this.R9 = aSDSuggestSchema.getR9();
		this.R10 = aSDSuggestSchema.getR10();
		this.R11 = aSDSuggestSchema.getR11();
		this.R12 = aSDSuggestSchema.getR12();
		this.R13 = aSDSuggestSchema.getR13();
		this.R14 = aSDSuggestSchema.getR14();
		this.R15 = aSDSuggestSchema.getR15();
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

			if( rs.getString("UserType") == null )
				this.UserType = null;
			else
				this.UserType = rs.getString("UserType").trim();

			if( rs.getString("NickName") == null )
				this.NickName = null;
			else
				this.NickName = rs.getString("NickName").trim();

			if( rs.getString("Title") == null )
				this.Title = null;
			else
				this.Title = rs.getString("Title").trim();

			if( rs.getString("Phone") == null )
				this.Phone = null;
			else
				this.Phone = rs.getString("Phone").trim();

			if( rs.getString("Com") == null )
				this.Com = null;
			else
				this.Com = rs.getString("Com").trim();

			if( rs.getString("Content") == null )
				this.Content = null;
			else
				this.Content = rs.getString("Content").trim();

			if( rs.getString("SuggestDate") == null )
				this.SuggestDate = null;
			else
				this.SuggestDate = rs.getString("SuggestDate").trim();

			if( rs.getString("SuggestTime") == null )
				this.SuggestTime = null;
			else
				this.SuggestTime = rs.getString("SuggestTime").trim();

			if( rs.getString("DealFlag") == null )
				this.DealFlag = null;
			else
				this.DealFlag = rs.getString("DealFlag").trim();

			if( rs.getString("Remark") == null )
				this.Remark = null;
			else
				this.Remark = rs.getString("Remark").trim();

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
			System.out.println("���ݿ��е�SDSuggest���ֶθ�����Schema�е��ֶθ�����һ�£�����ִ��db.executeQuery��ѯʱû��ʹ��select * from tables");
			// @@������
			CError tError = new CError();
			tError.moduleName = "SDSuggestSchema";
			tError.functionName = "setSchema";
			tError.errorMessage = sqle.toString();
			this.mErrors .addOneError(tError);
			return false;
		}
		return true;
	}

	public SDSuggestSchema getSchema()
	{
		SDSuggestSchema aSDSuggestSchema = new SDSuggestSchema();
		aSDSuggestSchema.setSchema(this);
		return aSDSuggestSchema;
	}

	public SDSuggestDB getDB()
	{
		SDSuggestDB aDBOper = new SDSuggestDB();
		aDBOper.setSchema(this);
		return aDBOper;
	}


	/**
	* ���ݴ������ XML ��ʽ�����˳��μ�<A href ={@docRoot}/dataStructure/tb.html#PrpSDSuggest����/A>���ֶ�
	* @return: String ���ش�����ַ���
	**/
	public String encode()
	{
		StringBuffer strReturn = new StringBuffer(256);
		strReturn.append(StrTool.cTrim(SerialNo)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(UserType)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(NickName)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(Title)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(Phone)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(Com)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(Content)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(SuggestDate)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(SuggestTime)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(DealFlag)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(Remark)); strReturn.append(SysConst.PACKAGESPILTER);
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
	* ���ݽ�������˳��μ�<A href ={@docRoot}/dataStructure/tb.html#PrpSDSuggest>��ʷ����ƾ֤������Ϣ</A>���ֶ�
	* @param: strMessage String ����һ����¼���ݵ��ַ���
	* @return: boolean
	**/
	public boolean decode(String strMessage)
	{
		try
		{
			SerialNo = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 1, SysConst.PACKAGESPILTER );
			UserType = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 2, SysConst.PACKAGESPILTER );
			NickName = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 3, SysConst.PACKAGESPILTER );
			Title = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 4, SysConst.PACKAGESPILTER );
			Phone = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 5, SysConst.PACKAGESPILTER );
			Com = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 6, SysConst.PACKAGESPILTER );
			Content = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 7, SysConst.PACKAGESPILTER );
			SuggestDate = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 8, SysConst.PACKAGESPILTER );
			SuggestTime = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 9, SysConst.PACKAGESPILTER );
			DealFlag = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 10, SysConst.PACKAGESPILTER );
			Remark = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 11, SysConst.PACKAGESPILTER );
			R1 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 12, SysConst.PACKAGESPILTER );
			R2 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 13, SysConst.PACKAGESPILTER );
			R3 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 14, SysConst.PACKAGESPILTER );
			R4 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 15, SysConst.PACKAGESPILTER );
			R5 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 16, SysConst.PACKAGESPILTER );
			R6 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 17, SysConst.PACKAGESPILTER );
			R7 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 18, SysConst.PACKAGESPILTER );
			R8 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 19, SysConst.PACKAGESPILTER );
			R9 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 20, SysConst.PACKAGESPILTER );
			R10 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 21, SysConst.PACKAGESPILTER );
			R11 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 22, SysConst.PACKAGESPILTER );
			R12 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 23, SysConst.PACKAGESPILTER );
			R13 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 24, SysConst.PACKAGESPILTER );
			R14 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 25, SysConst.PACKAGESPILTER );
			R15 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 26, SysConst.PACKAGESPILTER );
		}
		catch(NumberFormatException ex)
		{
			// @@������
			CError tError = new CError();
			tError.moduleName = "SDSuggestSchema";
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
		if (FCode.equalsIgnoreCase("UserType"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(UserType));
		}
		if (FCode.equalsIgnoreCase("NickName"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(NickName));
		}
		if (FCode.equalsIgnoreCase("Title"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(Title));
		}
		if (FCode.equalsIgnoreCase("Phone"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(Phone));
		}
		if (FCode.equalsIgnoreCase("Com"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(Com));
		}
		if (FCode.equalsIgnoreCase("Content"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(Content));
		}
		if (FCode.equalsIgnoreCase("SuggestDate"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(SuggestDate));
		}
		if (FCode.equalsIgnoreCase("SuggestTime"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(SuggestTime));
		}
		if (FCode.equalsIgnoreCase("DealFlag"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(DealFlag));
		}
		if (FCode.equalsIgnoreCase("Remark"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(Remark));
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
				strFieldValue = StrTool.GBKToUnicode(UserType);
				break;
			case 2:
				strFieldValue = StrTool.GBKToUnicode(NickName);
				break;
			case 3:
				strFieldValue = StrTool.GBKToUnicode(Title);
				break;
			case 4:
				strFieldValue = StrTool.GBKToUnicode(Phone);
				break;
			case 5:
				strFieldValue = StrTool.GBKToUnicode(Com);
				break;
			case 6:
				strFieldValue = StrTool.GBKToUnicode(Content);
				break;
			case 7:
				strFieldValue = StrTool.GBKToUnicode(SuggestDate);
				break;
			case 8:
				strFieldValue = StrTool.GBKToUnicode(SuggestTime);
				break;
			case 9:
				strFieldValue = StrTool.GBKToUnicode(DealFlag);
				break;
			case 10:
				strFieldValue = StrTool.GBKToUnicode(Remark);
				break;
			case 11:
				strFieldValue = StrTool.GBKToUnicode(R1);
				break;
			case 12:
				strFieldValue = StrTool.GBKToUnicode(R2);
				break;
			case 13:
				strFieldValue = StrTool.GBKToUnicode(R3);
				break;
			case 14:
				strFieldValue = StrTool.GBKToUnicode(R4);
				break;
			case 15:
				strFieldValue = StrTool.GBKToUnicode(R5);
				break;
			case 16:
				strFieldValue = StrTool.GBKToUnicode(R6);
				break;
			case 17:
				strFieldValue = StrTool.GBKToUnicode(R7);
				break;
			case 18:
				strFieldValue = StrTool.GBKToUnicode(R8);
				break;
			case 19:
				strFieldValue = StrTool.GBKToUnicode(R9);
				break;
			case 20:
				strFieldValue = StrTool.GBKToUnicode(R10);
				break;
			case 21:
				strFieldValue = StrTool.GBKToUnicode(R11);
				break;
			case 22:
				strFieldValue = StrTool.GBKToUnicode(R12);
				break;
			case 23:
				strFieldValue = StrTool.GBKToUnicode(R13);
				break;
			case 24:
				strFieldValue = StrTool.GBKToUnicode(R14);
				break;
			case 25:
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
		if (FCode.equalsIgnoreCase("UserType"))
		{
			if( FValue != null )
			{
				UserType = FValue.trim();
			}
			else
				UserType = null;
		}
		if (FCode.equalsIgnoreCase("NickName"))
		{
			if( FValue != null )
			{
				NickName = FValue.trim();
			}
			else
				NickName = null;
		}
		if (FCode.equalsIgnoreCase("Title"))
		{
			if( FValue != null )
			{
				Title = FValue.trim();
			}
			else
				Title = null;
		}
		if (FCode.equalsIgnoreCase("Phone"))
		{
			if( FValue != null )
			{
				Phone = FValue.trim();
			}
			else
				Phone = null;
		}
		if (FCode.equalsIgnoreCase("Com"))
		{
			if( FValue != null )
			{
				Com = FValue.trim();
			}
			else
				Com = null;
		}
		if (FCode.equalsIgnoreCase("Content"))
		{
			if( FValue != null )
			{
				Content = FValue.trim();
			}
			else
				Content = null;
		}
		if (FCode.equalsIgnoreCase("SuggestDate"))
		{
			if( FValue != null )
			{
				SuggestDate = FValue.trim();
			}
			else
				SuggestDate = null;
		}
		if (FCode.equalsIgnoreCase("SuggestTime"))
		{
			if( FValue != null )
			{
				SuggestTime = FValue.trim();
			}
			else
				SuggestTime = null;
		}
		if (FCode.equalsIgnoreCase("DealFlag"))
		{
			if( FValue != null )
			{
				DealFlag = FValue.trim();
			}
			else
				DealFlag = null;
		}
		if (FCode.equalsIgnoreCase("Remark"))
		{
			if( FValue != null )
			{
				Remark = FValue.trim();
			}
			else
				Remark = null;
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
		SDSuggestSchema other = (SDSuggestSchema)otherObject;
		return
			SerialNo.equals(other.getSerialNo())
			&& UserType.equals(other.getUserType())
			&& NickName.equals(other.getNickName())
			&& Title.equals(other.getTitle())
			&& Phone.equals(other.getPhone())
			&& Com.equals(other.getCom())
			&& Content.equals(other.getContent())
			&& SuggestDate.equals(other.getSuggestDate())
			&& SuggestTime.equals(other.getSuggestTime())
			&& DealFlag.equals(other.getDealFlag())
			&& Remark.equals(other.getRemark())
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
		if( strFieldName.equals("UserType") ) {
			return 1;
		}
		if( strFieldName.equals("NickName") ) {
			return 2;
		}
		if( strFieldName.equals("Title") ) {
			return 3;
		}
		if( strFieldName.equals("Phone") ) {
			return 4;
		}
		if( strFieldName.equals("Com") ) {
			return 5;
		}
		if( strFieldName.equals("Content") ) {
			return 6;
		}
		if( strFieldName.equals("SuggestDate") ) {
			return 7;
		}
		if( strFieldName.equals("SuggestTime") ) {
			return 8;
		}
		if( strFieldName.equals("DealFlag") ) {
			return 9;
		}
		if( strFieldName.equals("Remark") ) {
			return 10;
		}
		if( strFieldName.equals("R1") ) {
			return 11;
		}
		if( strFieldName.equals("R2") ) {
			return 12;
		}
		if( strFieldName.equals("R3") ) {
			return 13;
		}
		if( strFieldName.equals("R4") ) {
			return 14;
		}
		if( strFieldName.equals("R5") ) {
			return 15;
		}
		if( strFieldName.equals("R6") ) {
			return 16;
		}
		if( strFieldName.equals("R7") ) {
			return 17;
		}
		if( strFieldName.equals("R8") ) {
			return 18;
		}
		if( strFieldName.equals("R9") ) {
			return 19;
		}
		if( strFieldName.equals("R10") ) {
			return 20;
		}
		if( strFieldName.equals("R11") ) {
			return 21;
		}
		if( strFieldName.equals("R12") ) {
			return 22;
		}
		if( strFieldName.equals("R13") ) {
			return 23;
		}
		if( strFieldName.equals("R14") ) {
			return 24;
		}
		if( strFieldName.equals("R15") ) {
			return 25;
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
				strFieldName = "UserType";
				break;
			case 2:
				strFieldName = "NickName";
				break;
			case 3:
				strFieldName = "Title";
				break;
			case 4:
				strFieldName = "Phone";
				break;
			case 5:
				strFieldName = "Com";
				break;
			case 6:
				strFieldName = "Content";
				break;
			case 7:
				strFieldName = "SuggestDate";
				break;
			case 8:
				strFieldName = "SuggestTime";
				break;
			case 9:
				strFieldName = "DealFlag";
				break;
			case 10:
				strFieldName = "Remark";
				break;
			case 11:
				strFieldName = "R1";
				break;
			case 12:
				strFieldName = "R2";
				break;
			case 13:
				strFieldName = "R3";
				break;
			case 14:
				strFieldName = "R4";
				break;
			case 15:
				strFieldName = "R5";
				break;
			case 16:
				strFieldName = "R6";
				break;
			case 17:
				strFieldName = "R7";
				break;
			case 18:
				strFieldName = "R8";
				break;
			case 19:
				strFieldName = "R9";
				break;
			case 20:
				strFieldName = "R10";
				break;
			case 21:
				strFieldName = "R11";
				break;
			case 22:
				strFieldName = "R12";
				break;
			case 23:
				strFieldName = "R13";
				break;
			case 24:
				strFieldName = "R14";
				break;
			case 25:
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
		if( strFieldName.equals("UserType") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("NickName") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("Title") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("Phone") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("Com") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("Content") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("SuggestDate") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("SuggestTime") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("DealFlag") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("Remark") ) {
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
			case 23:
				nFieldType = Schema.TYPE_STRING;
				break;
			case 24:
				nFieldType = Schema.TYPE_STRING;
				break;
			case 25:
				nFieldType = Schema.TYPE_STRING;
				break;
			default:
				nFieldType = Schema.TYPE_NOFOUND;
		};
		return nFieldType;
	}
}
