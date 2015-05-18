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
import com.sinosoft.lis.db.SDVoteTraceDB;

/*
 * <p>ClassName: SDVoteTraceSchema </p>
 * <p>Description: DB层 Schema 类文件 </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: sinosoft </p>
 * @Database: PhysicalDataModel_1
 */
public class SDVoteTraceSchema implements Schema, Cloneable
{
	// @Field
	/** 流水号 */
	private String SerialNo;
	/** 项目编码 */
	private String ItemNo;
	/** 题目编码 */
	private String TopicNo;
	/** 投票编码 */
	private String VoteNo;
	/** 项目人 */
	private String VotePerson;
	/** 投票日期 */
	private String VoteDate;
	/** 投票时间 */
	private String VoteTime;
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

	public static final int FIELDNUM = 22;	// 数据库表的字段个数

	private static String[] PK;				// 主键

	public CErrors mErrors;			// 错误信息

	// @Constructor
	public SDVoteTraceSchema()
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
		SDVoteTraceSchema cloned = (SDVoteTraceSchema)super.clone();
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
	public String getItemNo()
	{
if(ItemNo==null)ItemNo="";
		return ItemNo;
	}
	public void setItemNo(String aItemNo)
	{
		ItemNo = aItemNo;
	}
	public String getTopicNo()
	{
if(TopicNo==null)TopicNo="";
		return TopicNo;
	}
	public void setTopicNo(String aTopicNo)
	{
		TopicNo = aTopicNo;
	}
	public String getVoteNo()
	{
if(VoteNo==null)VoteNo="";
		return VoteNo;
	}
	public void setVoteNo(String aVoteNo)
	{
		VoteNo = aVoteNo;
	}
	public String getVotePerson()
	{
if(VotePerson==null)VotePerson="";
		return VotePerson;
	}
	public void setVotePerson(String aVotePerson)
	{
		VotePerson = aVotePerson;
	}
	public String getVoteDate()
	{
if(VoteDate==null)VoteDate="";
		return VoteDate;
	}
	public void setVoteDate(String aVoteDate)
	{
		VoteDate = aVoteDate;
	}
	public String getVoteTime()
	{
if(VoteTime==null)VoteTime="";
		return VoteTime;
	}
	public void setVoteTime(String aVoteTime)
	{
		VoteTime = aVoteTime;
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
	* 使用另外一个 SDVoteTraceSchema 对象给 Schema 赋值
	* @param: aSDVoteTraceSchema SDVoteTraceSchema
	**/
	public void setSchema(SDVoteTraceSchema aSDVoteTraceSchema)
	{
		this.SerialNo = aSDVoteTraceSchema.getSerialNo();
		this.ItemNo = aSDVoteTraceSchema.getItemNo();
		this.TopicNo = aSDVoteTraceSchema.getTopicNo();
		this.VoteNo = aSDVoteTraceSchema.getVoteNo();
		this.VotePerson = aSDVoteTraceSchema.getVotePerson();
		this.VoteDate = aSDVoteTraceSchema.getVoteDate();
		this.VoteTime = aSDVoteTraceSchema.getVoteTime();
		this.R1 = aSDVoteTraceSchema.getR1();
		this.R2 = aSDVoteTraceSchema.getR2();
		this.R3 = aSDVoteTraceSchema.getR3();
		this.R4 = aSDVoteTraceSchema.getR4();
		this.R5 = aSDVoteTraceSchema.getR5();
		this.R6 = aSDVoteTraceSchema.getR6();
		this.R7 = aSDVoteTraceSchema.getR7();
		this.R8 = aSDVoteTraceSchema.getR8();
		this.R9 = aSDVoteTraceSchema.getR9();
		this.R10 = aSDVoteTraceSchema.getR10();
		this.R11 = aSDVoteTraceSchema.getR11();
		this.R12 = aSDVoteTraceSchema.getR12();
		this.R13 = aSDVoteTraceSchema.getR13();
		this.R14 = aSDVoteTraceSchema.getR14();
		this.R15 = aSDVoteTraceSchema.getR15();
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

			if( rs.getString("ItemNo") == null )
				this.ItemNo = null;
			else
				this.ItemNo = rs.getString("ItemNo").trim();

			if( rs.getString("TopicNo") == null )
				this.TopicNo = null;
			else
				this.TopicNo = rs.getString("TopicNo").trim();

			if( rs.getString("VoteNo") == null )
				this.VoteNo = null;
			else
				this.VoteNo = rs.getString("VoteNo").trim();

			if( rs.getString("VotePerson") == null )
				this.VotePerson = null;
			else
				this.VotePerson = rs.getString("VotePerson").trim();

			if( rs.getString("VoteDate") == null )
				this.VoteDate = null;
			else
				this.VoteDate = rs.getString("VoteDate").trim();

			if( rs.getString("VoteTime") == null )
				this.VoteTime = null;
			else
				this.VoteTime = rs.getString("VoteTime").trim();

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
			System.out.println("数据库中的SDVoteTrace表字段个数和Schema中的字段个数不一致，或者执行db.executeQuery查询时没有使用select * from tables");
			// @@错误处理
			CError tError = new CError();
			tError.moduleName = "SDVoteTraceSchema";
			tError.functionName = "setSchema";
			tError.errorMessage = sqle.toString();
			this.mErrors .addOneError(tError);
			return false;
		}
		return true;
	}

	public SDVoteTraceSchema getSchema()
	{
		SDVoteTraceSchema aSDVoteTraceSchema = new SDVoteTraceSchema();
		aSDVoteTraceSchema.setSchema(this);
		return aSDVoteTraceSchema;
	}

	public SDVoteTraceDB getDB()
	{
		SDVoteTraceDB aDBOper = new SDVoteTraceDB();
		aDBOper.setSchema(this);
		return aDBOper;
	}


	/**
	* 数据打包，按 XML 格式打包，顺序参见<A href ={@docRoot}/dataStructure/tb.html#PrpSDVoteTrace描述/A>表字段
	* @return: String 返回打包后字符串
	**/
	public String encode()
	{
		StringBuffer strReturn = new StringBuffer(256);
		strReturn.append(StrTool.cTrim(SerialNo)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(ItemNo)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(TopicNo)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(VoteNo)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(VotePerson)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(VoteDate)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(VoteTime)); strReturn.append(SysConst.PACKAGESPILTER);
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
	* 数据解包，解包顺序参见<A href ={@docRoot}/dataStructure/tb.html#PrpSDVoteTrace>历史记账凭证主表信息</A>表字段
	* @param: strMessage String 包含一条纪录数据的字符串
	* @return: boolean
	**/
	public boolean decode(String strMessage)
	{
		try
		{
			SerialNo = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 1, SysConst.PACKAGESPILTER );
			ItemNo = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 2, SysConst.PACKAGESPILTER );
			TopicNo = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 3, SysConst.PACKAGESPILTER );
			VoteNo = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 4, SysConst.PACKAGESPILTER );
			VotePerson = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 5, SysConst.PACKAGESPILTER );
			VoteDate = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 6, SysConst.PACKAGESPILTER );
			VoteTime = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 7, SysConst.PACKAGESPILTER );
			R1 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 8, SysConst.PACKAGESPILTER );
			R2 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 9, SysConst.PACKAGESPILTER );
			R3 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 10, SysConst.PACKAGESPILTER );
			R4 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 11, SysConst.PACKAGESPILTER );
			R5 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 12, SysConst.PACKAGESPILTER );
			R6 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 13, SysConst.PACKAGESPILTER );
			R7 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 14, SysConst.PACKAGESPILTER );
			R8 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 15, SysConst.PACKAGESPILTER );
			R9 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 16, SysConst.PACKAGESPILTER );
			R10 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 17, SysConst.PACKAGESPILTER );
			R11 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 18, SysConst.PACKAGESPILTER );
			R12 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 19, SysConst.PACKAGESPILTER );
			R13 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 20, SysConst.PACKAGESPILTER );
			R14 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 21, SysConst.PACKAGESPILTER );
			R15 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 22, SysConst.PACKAGESPILTER );
		}
		catch(NumberFormatException ex)
		{
			// @@错误处理
			CError tError = new CError();
			tError.moduleName = "SDVoteTraceSchema";
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
		if (FCode.equalsIgnoreCase("ItemNo"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(ItemNo));
		}
		if (FCode.equalsIgnoreCase("TopicNo"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(TopicNo));
		}
		if (FCode.equalsIgnoreCase("VoteNo"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(VoteNo));
		}
		if (FCode.equalsIgnoreCase("VotePerson"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(VotePerson));
		}
		if (FCode.equalsIgnoreCase("VoteDate"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(VoteDate));
		}
		if (FCode.equalsIgnoreCase("VoteTime"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(VoteTime));
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
				strFieldValue = StrTool.GBKToUnicode(ItemNo);
				break;
			case 2:
				strFieldValue = StrTool.GBKToUnicode(TopicNo);
				break;
			case 3:
				strFieldValue = StrTool.GBKToUnicode(VoteNo);
				break;
			case 4:
				strFieldValue = StrTool.GBKToUnicode(VotePerson);
				break;
			case 5:
				strFieldValue = StrTool.GBKToUnicode(VoteDate);
				break;
			case 6:
				strFieldValue = StrTool.GBKToUnicode(VoteTime);
				break;
			case 7:
				strFieldValue = StrTool.GBKToUnicode(R1);
				break;
			case 8:
				strFieldValue = StrTool.GBKToUnicode(R2);
				break;
			case 9:
				strFieldValue = StrTool.GBKToUnicode(R3);
				break;
			case 10:
				strFieldValue = StrTool.GBKToUnicode(R4);
				break;
			case 11:
				strFieldValue = StrTool.GBKToUnicode(R5);
				break;
			case 12:
				strFieldValue = StrTool.GBKToUnicode(R6);
				break;
			case 13:
				strFieldValue = StrTool.GBKToUnicode(R7);
				break;
			case 14:
				strFieldValue = StrTool.GBKToUnicode(R8);
				break;
			case 15:
				strFieldValue = StrTool.GBKToUnicode(R9);
				break;
			case 16:
				strFieldValue = StrTool.GBKToUnicode(R10);
				break;
			case 17:
				strFieldValue = StrTool.GBKToUnicode(R11);
				break;
			case 18:
				strFieldValue = StrTool.GBKToUnicode(R12);
				break;
			case 19:
				strFieldValue = StrTool.GBKToUnicode(R13);
				break;
			case 20:
				strFieldValue = StrTool.GBKToUnicode(R14);
				break;
			case 21:
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
		if (FCode.equalsIgnoreCase("ItemNo"))
		{
			if( FValue != null )
			{
				ItemNo = FValue.trim();
			}
			else
				ItemNo = null;
		}
		if (FCode.equalsIgnoreCase("TopicNo"))
		{
			if( FValue != null )
			{
				TopicNo = FValue.trim();
			}
			else
				TopicNo = null;
		}
		if (FCode.equalsIgnoreCase("VoteNo"))
		{
			if( FValue != null )
			{
				VoteNo = FValue.trim();
			}
			else
				VoteNo = null;
		}
		if (FCode.equalsIgnoreCase("VotePerson"))
		{
			if( FValue != null )
			{
				VotePerson = FValue.trim();
			}
			else
				VotePerson = null;
		}
		if (FCode.equalsIgnoreCase("VoteDate"))
		{
			if( FValue != null )
			{
				VoteDate = FValue.trim();
			}
			else
				VoteDate = null;
		}
		if (FCode.equalsIgnoreCase("VoteTime"))
		{
			if( FValue != null )
			{
				VoteTime = FValue.trim();
			}
			else
				VoteTime = null;
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
		SDVoteTraceSchema other = (SDVoteTraceSchema)otherObject;
		return
			SerialNo.equals(other.getSerialNo())
			&& ItemNo.equals(other.getItemNo())
			&& TopicNo.equals(other.getTopicNo())
			&& VoteNo.equals(other.getVoteNo())
			&& VotePerson.equals(other.getVotePerson())
			&& VoteDate.equals(other.getVoteDate())
			&& VoteTime.equals(other.getVoteTime())
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
		if( strFieldName.equals("ItemNo") ) {
			return 1;
		}
		if( strFieldName.equals("TopicNo") ) {
			return 2;
		}
		if( strFieldName.equals("VoteNo") ) {
			return 3;
		}
		if( strFieldName.equals("VotePerson") ) {
			return 4;
		}
		if( strFieldName.equals("VoteDate") ) {
			return 5;
		}
		if( strFieldName.equals("VoteTime") ) {
			return 6;
		}
		if( strFieldName.equals("R1") ) {
			return 7;
		}
		if( strFieldName.equals("R2") ) {
			return 8;
		}
		if( strFieldName.equals("R3") ) {
			return 9;
		}
		if( strFieldName.equals("R4") ) {
			return 10;
		}
		if( strFieldName.equals("R5") ) {
			return 11;
		}
		if( strFieldName.equals("R6") ) {
			return 12;
		}
		if( strFieldName.equals("R7") ) {
			return 13;
		}
		if( strFieldName.equals("R8") ) {
			return 14;
		}
		if( strFieldName.equals("R9") ) {
			return 15;
		}
		if( strFieldName.equals("R10") ) {
			return 16;
		}
		if( strFieldName.equals("R11") ) {
			return 17;
		}
		if( strFieldName.equals("R12") ) {
			return 18;
		}
		if( strFieldName.equals("R13") ) {
			return 19;
		}
		if( strFieldName.equals("R14") ) {
			return 20;
		}
		if( strFieldName.equals("R15") ) {
			return 21;
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
				strFieldName = "ItemNo";
				break;
			case 2:
				strFieldName = "TopicNo";
				break;
			case 3:
				strFieldName = "VoteNo";
				break;
			case 4:
				strFieldName = "VotePerson";
				break;
			case 5:
				strFieldName = "VoteDate";
				break;
			case 6:
				strFieldName = "VoteTime";
				break;
			case 7:
				strFieldName = "R1";
				break;
			case 8:
				strFieldName = "R2";
				break;
			case 9:
				strFieldName = "R3";
				break;
			case 10:
				strFieldName = "R4";
				break;
			case 11:
				strFieldName = "R5";
				break;
			case 12:
				strFieldName = "R6";
				break;
			case 13:
				strFieldName = "R7";
				break;
			case 14:
				strFieldName = "R8";
				break;
			case 15:
				strFieldName = "R9";
				break;
			case 16:
				strFieldName = "R10";
				break;
			case 17:
				strFieldName = "R11";
				break;
			case 18:
				strFieldName = "R12";
				break;
			case 19:
				strFieldName = "R13";
				break;
			case 20:
				strFieldName = "R14";
				break;
			case 21:
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
		if( strFieldName.equals("ItemNo") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("TopicNo") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("VoteNo") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("VotePerson") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("VoteDate") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("VoteTime") ) {
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
			default:
				nFieldType = Schema.TYPE_NOFOUND;
		};
		return nFieldType;
	}
}
