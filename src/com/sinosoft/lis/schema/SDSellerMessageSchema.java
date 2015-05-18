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
import com.sinosoft.lis.db.SDSellerMessageDB;

/*
 * <p>ClassName: SDSellerMessageSchema </p>
 * <p>Description: DB层 Schema 类文件 </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: sinosoft </p>
 * @Database: PhysicalDataModel_1
 */
public class SDSellerMessageSchema implements Schema, Cloneable
{
	// @Field
	/** 流水号 */
	private String SerialNo;
	/** 用户编码 */
	private String UserID;
	/** 商户编码 */
	private String SellerID;
	/** 留言日期 */
	private String MsgDate;
	/** 留言时间 */
	private String MsgTime;
	/** 等级 */
	private String Level;
	/** 内容 */
	private String Context;
	/** 电话 */
	private String Phone;
	/** 关联订单 */
	private String OrderID;
	/** 备用字段1 */
	private String R1;
	/** 备用字段2 */
	private String R2;
	/** 备用字段3 */
	private String R3;
	/** 备用字段4 */
	private String R4;
	/** 备用字段5 */
	private String R5;
	/** 备用字段6 */
	private String R6;
	/** 备用字段7 */
	private String R7;
	/** 备用字段8 */
	private String R8;
	/** 备用字段9 */
	private String R9;
	/** 备用字段10 */
	private String R10;
	/** 备用字段11 */
	private String R11;
	/** 备用字段12 */
	private String R12;
	/** 备用字段13 */
	private String R13;
	/** 备用字段14 */
	private String R14;
	/** 备用字段15 */
	private String R15;

	public static final int FIELDNUM = 24;	// 数据库表的字段个数

	private static String[] PK;				// 主键

	public CErrors mErrors;			// 错误信息

	// @Constructor
	public SDSellerMessageSchema()
	{
		mErrors = new CErrors();

		String[] pk = new String[1];
		pk[0] = "SerialNo";

		PK = pk;
	}

	/**
	* Schema克隆
	* @return Object
	* @throws CloneNotSupportedException
	*/
	public Object clone()
		throws CloneNotSupportedException
	{
		SDSellerMessageSchema cloned = (SDSellerMessageSchema)super.clone();
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
	public String getUserID()
	{
if(UserID==null)UserID="";
		return UserID;
	}
	public void setUserID(String aUserID)
	{
		UserID = aUserID;
	}
	public String getSellerID()
	{
if(SellerID==null)SellerID="";
		return SellerID;
	}
	public void setSellerID(String aSellerID)
	{
		SellerID = aSellerID;
	}
	public String getMsgDate()
	{
if(MsgDate==null)MsgDate="";
		return MsgDate;
	}
	public void setMsgDate(String aMsgDate)
	{
		MsgDate = aMsgDate;
	}
	public String getMsgTime()
	{
if(MsgTime==null)MsgTime="";
		return MsgTime;
	}
	public void setMsgTime(String aMsgTime)
	{
		MsgTime = aMsgTime;
	}
	public String getLevel()
	{
if(Level==null)Level="";
		return Level;
	}
	public void setLevel(String aLevel)
	{
		Level = aLevel;
	}
	public String getContext()
	{
if(Context==null)Context="";
		return Context;
	}
	public void setContext(String aContext)
	{
		Context = aContext;
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
	public String getOrderID()
	{
if(OrderID==null)OrderID="";
		return OrderID;
	}
	public void setOrderID(String aOrderID)
	{
		OrderID = aOrderID;
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
	* 使用另外一个 SDSellerMessageSchema 对象给 Schema 赋值
	* @param: aSDSellerMessageSchema SDSellerMessageSchema
	**/
	public void setSchema(SDSellerMessageSchema aSDSellerMessageSchema)
	{
		this.SerialNo = aSDSellerMessageSchema.getSerialNo();
		this.UserID = aSDSellerMessageSchema.getUserID();
		this.SellerID = aSDSellerMessageSchema.getSellerID();
		this.MsgDate = aSDSellerMessageSchema.getMsgDate();
		this.MsgTime = aSDSellerMessageSchema.getMsgTime();
		this.Level = aSDSellerMessageSchema.getLevel();
		this.Context = aSDSellerMessageSchema.getContext();
		this.Phone = aSDSellerMessageSchema.getPhone();
		this.OrderID = aSDSellerMessageSchema.getOrderID();
		this.R1 = aSDSellerMessageSchema.getR1();
		this.R2 = aSDSellerMessageSchema.getR2();
		this.R3 = aSDSellerMessageSchema.getR3();
		this.R4 = aSDSellerMessageSchema.getR4();
		this.R5 = aSDSellerMessageSchema.getR5();
		this.R6 = aSDSellerMessageSchema.getR6();
		this.R7 = aSDSellerMessageSchema.getR7();
		this.R8 = aSDSellerMessageSchema.getR8();
		this.R9 = aSDSellerMessageSchema.getR9();
		this.R10 = aSDSellerMessageSchema.getR10();
		this.R11 = aSDSellerMessageSchema.getR11();
		this.R12 = aSDSellerMessageSchema.getR12();
		this.R13 = aSDSellerMessageSchema.getR13();
		this.R14 = aSDSellerMessageSchema.getR14();
		this.R15 = aSDSellerMessageSchema.getR15();
	}

	/**
	* 使用 ResultSet 中的第 i 行给 Schema 赋值
	* @param: rs ResultSet
	* @param: i int
	* @return: boolean
	**/
	public boolean setSchema(ResultSet rs,int i)
	{
		try
		{
			//rs.absolute(i);		// 非滚动游标
			if( rs.getString("SerialNo") == null )
				this.SerialNo = null;
			else
				this.SerialNo = rs.getString("SerialNo").trim();

			if( rs.getString("UserID") == null )
				this.UserID = null;
			else
				this.UserID = rs.getString("UserID").trim();

			if( rs.getString("SellerID") == null )
				this.SellerID = null;
			else
				this.SellerID = rs.getString("SellerID").trim();

			if( rs.getString("MsgDate") == null )
				this.MsgDate = null;
			else
				this.MsgDate = rs.getString("MsgDate").trim();

			if( rs.getString("MsgTime") == null )
				this.MsgTime = null;
			else
				this.MsgTime = rs.getString("MsgTime").trim();

			if( rs.getString("Level") == null )
				this.Level = null;
			else
				this.Level = rs.getString("Level").trim();

			if( rs.getString("Context") == null )
				this.Context = null;
			else
				this.Context = rs.getString("Context").trim();

			if( rs.getString("Phone") == null )
				this.Phone = null;
			else
				this.Phone = rs.getString("Phone").trim();

			if( rs.getString("OrderID") == null )
				this.OrderID = null;
			else
				this.OrderID = rs.getString("OrderID").trim();

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
			System.out.println("数据库中的SDSellerMessage表字段个数和Schema中的字段个数不一致，或者执行db.executeQuery查询时没有使用select * from tables");
			// @@错误处理
			CError tError = new CError();
			tError.moduleName = "SDSellerMessageSchema";
			tError.functionName = "setSchema";
			tError.errorMessage = sqle.toString();
			this.mErrors .addOneError(tError);
			return false;
		}
		return true;
	}

	public SDSellerMessageSchema getSchema()
	{
		SDSellerMessageSchema aSDSellerMessageSchema = new SDSellerMessageSchema();
		aSDSellerMessageSchema.setSchema(this);
		return aSDSellerMessageSchema;
	}

	public SDSellerMessageDB getDB()
	{
		SDSellerMessageDB aDBOper = new SDSellerMessageDB();
		aDBOper.setSchema(this);
		return aDBOper;
	}


	/**
	* 数据打包，按 XML 格式打包，顺序参见<A href ={@docRoot}/dataStructure/tb.html#PrpSDSellerMessage描述/A>表字段
	* @return: String 返回打包后字符串
	**/
	public String encode()
	{
		StringBuffer strReturn = new StringBuffer(256);
		strReturn.append(StrTool.cTrim(SerialNo)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(UserID)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(SellerID)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(MsgDate)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(MsgTime)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(Level)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(Context)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(Phone)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(OrderID)); strReturn.append(SysConst.PACKAGESPILTER);
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
	* 数据解包，解包顺序参见<A href ={@docRoot}/dataStructure/tb.html#PrpSDSellerMessage>历史记账凭证主表信息</A>表字段
	* @param: strMessage String 包含一条纪录数据的字符串
	* @return: boolean
	**/
	public boolean decode(String strMessage)
	{
		try
		{
			SerialNo = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 1, SysConst.PACKAGESPILTER );
			UserID = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 2, SysConst.PACKAGESPILTER );
			SellerID = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 3, SysConst.PACKAGESPILTER );
			MsgDate = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 4, SysConst.PACKAGESPILTER );
			MsgTime = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 5, SysConst.PACKAGESPILTER );
			Level = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 6, SysConst.PACKAGESPILTER );
			Context = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 7, SysConst.PACKAGESPILTER );
			Phone = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 8, SysConst.PACKAGESPILTER );
			OrderID = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 9, SysConst.PACKAGESPILTER );
			R1 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 10, SysConst.PACKAGESPILTER );
			R2 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 11, SysConst.PACKAGESPILTER );
			R3 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 12, SysConst.PACKAGESPILTER );
			R4 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 13, SysConst.PACKAGESPILTER );
			R5 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 14, SysConst.PACKAGESPILTER );
			R6 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 15, SysConst.PACKAGESPILTER );
			R7 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 16, SysConst.PACKAGESPILTER );
			R8 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 17, SysConst.PACKAGESPILTER );
			R9 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 18, SysConst.PACKAGESPILTER );
			R10 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 19, SysConst.PACKAGESPILTER );
			R11 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 20, SysConst.PACKAGESPILTER );
			R12 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 21, SysConst.PACKAGESPILTER );
			R13 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 22, SysConst.PACKAGESPILTER );
			R14 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 23, SysConst.PACKAGESPILTER );
			R15 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 24, SysConst.PACKAGESPILTER );
		}
		catch(NumberFormatException ex)
		{
			// @@错误处理
			CError tError = new CError();
			tError.moduleName = "SDSellerMessageSchema";
			tError.functionName = "decode";
			tError.errorMessage = ex.toString();
			this.mErrors .addOneError(tError);

			return false;
		}
		return true;
	}

	/**
	* 取得对应传入参数的String形式的字段值
	* @param: FCode String 希望取得的字段名
	* @return: String
	* 如果没有对应的字段，返回""
	* 如果字段值为空，返回"null"
	**/
	public String getV(String FCode)
	{
		String strReturn = "";
		if (FCode.equalsIgnoreCase("SerialNo"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(SerialNo));
		}
		if (FCode.equalsIgnoreCase("UserID"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(UserID));
		}
		if (FCode.equalsIgnoreCase("SellerID"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(SellerID));
		}
		if (FCode.equalsIgnoreCase("MsgDate"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(MsgDate));
		}
		if (FCode.equalsIgnoreCase("MsgTime"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(MsgTime));
		}
		if (FCode.equalsIgnoreCase("Level"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(Level));
		}
		if (FCode.equalsIgnoreCase("Context"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(Context));
		}
		if (FCode.equalsIgnoreCase("Phone"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(Phone));
		}
		if (FCode.equalsIgnoreCase("OrderID"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(OrderID));
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
	* 取得Schema中指定索引值所对应的字段值
	* @param: nFieldIndex int 指定的字段索引值
	* @return: String
	* 如果没有对应的字段，返回""
	* 如果字段值为空，返回"null"
	**/
	public String getV(int nFieldIndex)
	{
		String strFieldValue = "";
		switch(nFieldIndex) {
			case 0:
				strFieldValue = StrTool.GBKToUnicode(SerialNo);
				break;
			case 1:
				strFieldValue = StrTool.GBKToUnicode(UserID);
				break;
			case 2:
				strFieldValue = StrTool.GBKToUnicode(SellerID);
				break;
			case 3:
				strFieldValue = StrTool.GBKToUnicode(MsgDate);
				break;
			case 4:
				strFieldValue = StrTool.GBKToUnicode(MsgTime);
				break;
			case 5:
				strFieldValue = StrTool.GBKToUnicode(Level);
				break;
			case 6:
				strFieldValue = StrTool.GBKToUnicode(Context);
				break;
			case 7:
				strFieldValue = StrTool.GBKToUnicode(Phone);
				break;
			case 8:
				strFieldValue = StrTool.GBKToUnicode(OrderID);
				break;
			case 9:
				strFieldValue = StrTool.GBKToUnicode(R1);
				break;
			case 10:
				strFieldValue = StrTool.GBKToUnicode(R2);
				break;
			case 11:
				strFieldValue = StrTool.GBKToUnicode(R3);
				break;
			case 12:
				strFieldValue = StrTool.GBKToUnicode(R4);
				break;
			case 13:
				strFieldValue = StrTool.GBKToUnicode(R5);
				break;
			case 14:
				strFieldValue = StrTool.GBKToUnicode(R6);
				break;
			case 15:
				strFieldValue = StrTool.GBKToUnicode(R7);
				break;
			case 16:
				strFieldValue = StrTool.GBKToUnicode(R8);
				break;
			case 17:
				strFieldValue = StrTool.GBKToUnicode(R9);
				break;
			case 18:
				strFieldValue = StrTool.GBKToUnicode(R10);
				break;
			case 19:
				strFieldValue = StrTool.GBKToUnicode(R11);
				break;
			case 20:
				strFieldValue = StrTool.GBKToUnicode(R12);
				break;
			case 21:
				strFieldValue = StrTool.GBKToUnicode(R13);
				break;
			case 22:
				strFieldValue = StrTool.GBKToUnicode(R14);
				break;
			case 23:
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
	* 设置对应传入参数的String形式的字段值
	* @param: FCode String 需要赋值的对象
	* @param: FValue String 要赋的值
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
		if (FCode.equalsIgnoreCase("UserID"))
		{
			if( FValue != null )
			{
				UserID = FValue.trim();
			}
			else
				UserID = null;
		}
		if (FCode.equalsIgnoreCase("SellerID"))
		{
			if( FValue != null )
			{
				SellerID = FValue.trim();
			}
			else
				SellerID = null;
		}
		if (FCode.equalsIgnoreCase("MsgDate"))
		{
			if( FValue != null )
			{
				MsgDate = FValue.trim();
			}
			else
				MsgDate = null;
		}
		if (FCode.equalsIgnoreCase("MsgTime"))
		{
			if( FValue != null )
			{
				MsgTime = FValue.trim();
			}
			else
				MsgTime = null;
		}
		if (FCode.equalsIgnoreCase("Level"))
		{
			if( FValue != null )
			{
				Level = FValue.trim();
			}
			else
				Level = null;
		}
		if (FCode.equalsIgnoreCase("Context"))
		{
			if( FValue != null )
			{
				Context = FValue.trim();
			}
			else
				Context = null;
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
		if (FCode.equalsIgnoreCase("OrderID"))
		{
			if( FValue != null )
			{
				OrderID = FValue.trim();
			}
			else
				OrderID = null;
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
		SDSellerMessageSchema other = (SDSellerMessageSchema)otherObject;
		return
			SerialNo.equals(other.getSerialNo())
			&& UserID.equals(other.getUserID())
			&& SellerID.equals(other.getSellerID())
			&& MsgDate.equals(other.getMsgDate())
			&& MsgTime.equals(other.getMsgTime())
			&& Level.equals(other.getLevel())
			&& Context.equals(other.getContext())
			&& Phone.equals(other.getPhone())
			&& OrderID.equals(other.getOrderID())
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
	* 取得Schema拥有字段的数量
       * @return: int
	**/
	public int getFieldCount()
	{
 		return FIELDNUM;
	}

	/**
	* 取得Schema中指定字段名所对应的索引值
	* 如果没有对应的字段，返回-1
       * @param: strFieldName String
       * @return: int
	**/
	public int getFieldIndex(String strFieldName)
	{
		if( strFieldName.equals("SerialNo") ) {
			return 0;
		}
		if( strFieldName.equals("UserID") ) {
			return 1;
		}
		if( strFieldName.equals("SellerID") ) {
			return 2;
		}
		if( strFieldName.equals("MsgDate") ) {
			return 3;
		}
		if( strFieldName.equals("MsgTime") ) {
			return 4;
		}
		if( strFieldName.equals("Level") ) {
			return 5;
		}
		if( strFieldName.equals("Context") ) {
			return 6;
		}
		if( strFieldName.equals("Phone") ) {
			return 7;
		}
		if( strFieldName.equals("OrderID") ) {
			return 8;
		}
		if( strFieldName.equals("R1") ) {
			return 9;
		}
		if( strFieldName.equals("R2") ) {
			return 10;
		}
		if( strFieldName.equals("R3") ) {
			return 11;
		}
		if( strFieldName.equals("R4") ) {
			return 12;
		}
		if( strFieldName.equals("R5") ) {
			return 13;
		}
		if( strFieldName.equals("R6") ) {
			return 14;
		}
		if( strFieldName.equals("R7") ) {
			return 15;
		}
		if( strFieldName.equals("R8") ) {
			return 16;
		}
		if( strFieldName.equals("R9") ) {
			return 17;
		}
		if( strFieldName.equals("R10") ) {
			return 18;
		}
		if( strFieldName.equals("R11") ) {
			return 19;
		}
		if( strFieldName.equals("R12") ) {
			return 20;
		}
		if( strFieldName.equals("R13") ) {
			return 21;
		}
		if( strFieldName.equals("R14") ) {
			return 22;
		}
		if( strFieldName.equals("R15") ) {
			return 23;
		}
		return -1;
	}

	/**
	* 取得Schema中指定索引值所对应的字段名
	* 如果没有对应的字段，返回""
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
				strFieldName = "UserID";
				break;
			case 2:
				strFieldName = "SellerID";
				break;
			case 3:
				strFieldName = "MsgDate";
				break;
			case 4:
				strFieldName = "MsgTime";
				break;
			case 5:
				strFieldName = "Level";
				break;
			case 6:
				strFieldName = "Context";
				break;
			case 7:
				strFieldName = "Phone";
				break;
			case 8:
				strFieldName = "OrderID";
				break;
			case 9:
				strFieldName = "R1";
				break;
			case 10:
				strFieldName = "R2";
				break;
			case 11:
				strFieldName = "R3";
				break;
			case 12:
				strFieldName = "R4";
				break;
			case 13:
				strFieldName = "R5";
				break;
			case 14:
				strFieldName = "R6";
				break;
			case 15:
				strFieldName = "R7";
				break;
			case 16:
				strFieldName = "R8";
				break;
			case 17:
				strFieldName = "R9";
				break;
			case 18:
				strFieldName = "R10";
				break;
			case 19:
				strFieldName = "R11";
				break;
			case 20:
				strFieldName = "R12";
				break;
			case 21:
				strFieldName = "R13";
				break;
			case 22:
				strFieldName = "R14";
				break;
			case 23:
				strFieldName = "R15";
				break;
			default:
				strFieldName = "";
		};
		return strFieldName;
	}

	/**
	* 取得Schema中指定字段名所对应的字段类型
	* 如果没有对应的字段，返回Schema.TYPE_NOFOUND
       * @param: strFieldName String
       * @return: int
	**/
	public int getFieldType(String strFieldName)
	{
		if( strFieldName.equals("SerialNo") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("UserID") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("SellerID") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("MsgDate") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("MsgTime") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("Level") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("Context") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("Phone") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("OrderID") ) {
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
	* 取得Schema中指定索引值所对应的字段类型
	* 如果没有对应的字段，返回Schema.TYPE_NOFOUND
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
			default:
				nFieldType = Schema.TYPE_NOFOUND;
		};
		return nFieldType;
	}
}
