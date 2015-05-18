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
import com.sinosoft.lis.db.SDAssActivityDB;

/*
 * <p>ClassName: SDAssActivitySchema </p>
 * <p>Description: DB层 Schema 类文件 </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: sinosoft </p>
 * @Database: PhysicalDataModel_1
 */
public class SDAssActivitySchema implements Schema, Cloneable
{
	// @Field
	/** 活动编码 */
	private String ActID;
	/** 用户编码 */
	private String AssID;
	/** 联系人手机 */
	private String Phone;
	/** 标题 */
	private String Title;
	/** 缴纳费用 */
	private String ActFee;
	/** 活动规则 */
	private String ActRule;
	/** 活动内容 */
	private String ActContent;
	/** 奖品赠品 */
	private String ActGift;
	/** 活动家化人数 */
	private String AcPeople;
	/** 开始日期 */
	private String StartDate;
	/** 开始时间 */
	private String StartTime;
	/** 结束日期 */
	private String EndDate;
	/** 结束时间 */
	private String EndTime;
	/** 报名截止日期 */
	private String RegEndDate;
	/** 报名截止时间 */
	private String RegEndTime;
	/** 审核状态 */
	private String AppStatus;
	/** 审核人 */
	private String Apper;
	/** 审核日期 */
	private String AppDate;
	/** 审核时间 */
	private String AppTime;
	/** 审核意见 */
	private String AppRemark;
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

	public static final int FIELDNUM = 35;	// 数据库表的字段个数

	private static String[] PK;				// 主键

	public CErrors mErrors;			// 错误信息

	// @Constructor
	public SDAssActivitySchema()
	{
		mErrors = new CErrors();

		String[] pk = new String[1];
		pk[0] = "ActID";

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
		SDAssActivitySchema cloned = (SDAssActivitySchema)super.clone();
		cloned.mErrors = (CErrors) mErrors.clone();
		return cloned;
	}

	// @Method
	public String[] getPK()
	{
		return PK;
	}

	public String getActID()
	{
if(ActID==null)ActID="";
		return ActID;
	}
	public void setActID(String aActID)
	{
		ActID = aActID;
	}
	public String getAssID()
	{
if(AssID==null)AssID="";
		return AssID;
	}
	public void setAssID(String aAssID)
	{
		AssID = aAssID;
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
	public String getTitle()
	{
if(Title==null)Title="";
		return Title;
	}
	public void setTitle(String aTitle)
	{
		Title = aTitle;
	}
	public String getActFee()
	{
if(ActFee==null)ActFee="";
		return ActFee;
	}
	public void setActFee(String aActFee)
	{
		ActFee = aActFee;
	}
	public String getActRule()
	{
if(ActRule==null)ActRule="";
		return ActRule;
	}
	public void setActRule(String aActRule)
	{
		ActRule = aActRule;
	}
	public String getActContent()
	{
if(ActContent==null)ActContent="";
		return ActContent;
	}
	public void setActContent(String aActContent)
	{
		ActContent = aActContent;
	}
	public String getActGift()
	{
if(ActGift==null)ActGift="";
		return ActGift;
	}
	public void setActGift(String aActGift)
	{
		ActGift = aActGift;
	}
	public String getAcPeople()
	{
if(AcPeople==null)AcPeople="";
		return AcPeople;
	}
	public void setAcPeople(String aAcPeople)
	{
		AcPeople = aAcPeople;
	}
	public String getStartDate()
	{
if(StartDate==null)StartDate="";
		return StartDate;
	}
	public void setStartDate(String aStartDate)
	{
		StartDate = aStartDate;
	}
	public String getStartTime()
	{
if(StartTime==null)StartTime="";
		return StartTime;
	}
	public void setStartTime(String aStartTime)
	{
		StartTime = aStartTime;
	}
	public String getEndDate()
	{
if(EndDate==null)EndDate="";
		return EndDate;
	}
	public void setEndDate(String aEndDate)
	{
		EndDate = aEndDate;
	}
	public String getEndTime()
	{
if(EndTime==null)EndTime="";
		return EndTime;
	}
	public void setEndTime(String aEndTime)
	{
		EndTime = aEndTime;
	}
	public String getRegEndDate()
	{
if(RegEndDate==null)RegEndDate="";
		return RegEndDate;
	}
	public void setRegEndDate(String aRegEndDate)
	{
		RegEndDate = aRegEndDate;
	}
	public String getRegEndTime()
	{
if(RegEndTime==null)RegEndTime="";
		return RegEndTime;
	}
	public void setRegEndTime(String aRegEndTime)
	{
		RegEndTime = aRegEndTime;
	}
	public String getAppStatus()
	{
if(AppStatus==null)AppStatus="";
		return AppStatus;
	}
	public void setAppStatus(String aAppStatus)
	{
		AppStatus = aAppStatus;
	}
	public String getApper()
	{
if(Apper==null)Apper="";
		return Apper;
	}
	public void setApper(String aApper)
	{
		Apper = aApper;
	}
	public String getAppDate()
	{
if(AppDate==null)AppDate="";
		return AppDate;
	}
	public void setAppDate(String aAppDate)
	{
		AppDate = aAppDate;
	}
	public String getAppTime()
	{
if(AppTime==null)AppTime="";
		return AppTime;
	}
	public void setAppTime(String aAppTime)
	{
		AppTime = aAppTime;
	}
	public String getAppRemark()
	{
if(AppRemark==null)AppRemark="";
		return AppRemark;
	}
	public void setAppRemark(String aAppRemark)
	{
		AppRemark = aAppRemark;
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
	* 使用另外一个 SDAssActivitySchema 对象给 Schema 赋值
	* @param: aSDAssActivitySchema SDAssActivitySchema
	**/
	public void setSchema(SDAssActivitySchema aSDAssActivitySchema)
	{
		this.ActID = aSDAssActivitySchema.getActID();
		this.AssID = aSDAssActivitySchema.getAssID();
		this.Phone = aSDAssActivitySchema.getPhone();
		this.Title = aSDAssActivitySchema.getTitle();
		this.ActFee = aSDAssActivitySchema.getActFee();
		this.ActRule = aSDAssActivitySchema.getActRule();
		this.ActContent = aSDAssActivitySchema.getActContent();
		this.ActGift = aSDAssActivitySchema.getActGift();
		this.AcPeople = aSDAssActivitySchema.getAcPeople();
		this.StartDate = aSDAssActivitySchema.getStartDate();
		this.StartTime = aSDAssActivitySchema.getStartTime();
		this.EndDate = aSDAssActivitySchema.getEndDate();
		this.EndTime = aSDAssActivitySchema.getEndTime();
		this.RegEndDate = aSDAssActivitySchema.getRegEndDate();
		this.RegEndTime = aSDAssActivitySchema.getRegEndTime();
		this.AppStatus = aSDAssActivitySchema.getAppStatus();
		this.Apper = aSDAssActivitySchema.getApper();
		this.AppDate = aSDAssActivitySchema.getAppDate();
		this.AppTime = aSDAssActivitySchema.getAppTime();
		this.AppRemark = aSDAssActivitySchema.getAppRemark();
		this.R1 = aSDAssActivitySchema.getR1();
		this.R2 = aSDAssActivitySchema.getR2();
		this.R3 = aSDAssActivitySchema.getR3();
		this.R4 = aSDAssActivitySchema.getR4();
		this.R5 = aSDAssActivitySchema.getR5();
		this.R6 = aSDAssActivitySchema.getR6();
		this.R7 = aSDAssActivitySchema.getR7();
		this.R8 = aSDAssActivitySchema.getR8();
		this.R9 = aSDAssActivitySchema.getR9();
		this.R10 = aSDAssActivitySchema.getR10();
		this.R11 = aSDAssActivitySchema.getR11();
		this.R12 = aSDAssActivitySchema.getR12();
		this.R13 = aSDAssActivitySchema.getR13();
		this.R14 = aSDAssActivitySchema.getR14();
		this.R15 = aSDAssActivitySchema.getR15();
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
			if( rs.getString("ActID") == null )
				this.ActID = null;
			else
				this.ActID = rs.getString("ActID").trim();

			if( rs.getString("AssID") == null )
				this.AssID = null;
			else
				this.AssID = rs.getString("AssID").trim();

			if( rs.getString("Phone") == null )
				this.Phone = null;
			else
				this.Phone = rs.getString("Phone").trim();

			if( rs.getString("Title") == null )
				this.Title = null;
			else
				this.Title = rs.getString("Title").trim();

			if( rs.getString("ActFee") == null )
				this.ActFee = null;
			else
				this.ActFee = rs.getString("ActFee").trim();

			if( rs.getString("ActRule") == null )
				this.ActRule = null;
			else
				this.ActRule = rs.getString("ActRule").trim();

			if( rs.getString("ActContent") == null )
				this.ActContent = null;
			else
				this.ActContent = rs.getString("ActContent").trim();

			if( rs.getString("ActGift") == null )
				this.ActGift = null;
			else
				this.ActGift = rs.getString("ActGift").trim();

			if( rs.getString("AcPeople") == null )
				this.AcPeople = null;
			else
				this.AcPeople = rs.getString("AcPeople").trim();

			if( rs.getString("StartDate") == null )
				this.StartDate = null;
			else
				this.StartDate = rs.getString("StartDate").trim();

			if( rs.getString("StartTime") == null )
				this.StartTime = null;
			else
				this.StartTime = rs.getString("StartTime").trim();

			if( rs.getString("EndDate") == null )
				this.EndDate = null;
			else
				this.EndDate = rs.getString("EndDate").trim();

			if( rs.getString("EndTime") == null )
				this.EndTime = null;
			else
				this.EndTime = rs.getString("EndTime").trim();

			if( rs.getString("RegEndDate") == null )
				this.RegEndDate = null;
			else
				this.RegEndDate = rs.getString("RegEndDate").trim();

			if( rs.getString("RegEndTime") == null )
				this.RegEndTime = null;
			else
				this.RegEndTime = rs.getString("RegEndTime").trim();

			if( rs.getString("AppStatus") == null )
				this.AppStatus = null;
			else
				this.AppStatus = rs.getString("AppStatus").trim();

			if( rs.getString("Apper") == null )
				this.Apper = null;
			else
				this.Apper = rs.getString("Apper").trim();

			if( rs.getString("AppDate") == null )
				this.AppDate = null;
			else
				this.AppDate = rs.getString("AppDate").trim();

			if( rs.getString("AppTime") == null )
				this.AppTime = null;
			else
				this.AppTime = rs.getString("AppTime").trim();

			if( rs.getString("AppRemark") == null )
				this.AppRemark = null;
			else
				this.AppRemark = rs.getString("AppRemark").trim();

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
			System.out.println("数据库中的SDAssActivity表字段个数和Schema中的字段个数不一致，或者执行db.executeQuery查询时没有使用select * from tables");
			// @@错误处理
			CError tError = new CError();
			tError.moduleName = "SDAssActivitySchema";
			tError.functionName = "setSchema";
			tError.errorMessage = sqle.toString();
			this.mErrors .addOneError(tError);
			return false;
		}
		return true;
	}

	public SDAssActivitySchema getSchema()
	{
		SDAssActivitySchema aSDAssActivitySchema = new SDAssActivitySchema();
		aSDAssActivitySchema.setSchema(this);
		return aSDAssActivitySchema;
	}

	public SDAssActivityDB getDB()
	{
		SDAssActivityDB aDBOper = new SDAssActivityDB();
		aDBOper.setSchema(this);
		return aDBOper;
	}


	/**
	* 数据打包，按 XML 格式打包，顺序参见<A href ={@docRoot}/dataStructure/tb.html#PrpSDAssActivity描述/A>表字段
	* @return: String 返回打包后字符串
	**/
	public String encode()
	{
		StringBuffer strReturn = new StringBuffer(256);
		strReturn.append(StrTool.cTrim(ActID)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(AssID)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(Phone)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(Title)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(ActFee)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(ActRule)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(ActContent)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(ActGift)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(AcPeople)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(StartDate)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(StartTime)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(EndDate)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(EndTime)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(RegEndDate)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(RegEndTime)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(AppStatus)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(Apper)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(AppDate)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(AppTime)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(AppRemark)); strReturn.append(SysConst.PACKAGESPILTER);
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
	* 数据解包，解包顺序参见<A href ={@docRoot}/dataStructure/tb.html#PrpSDAssActivity>历史记账凭证主表信息</A>表字段
	* @param: strMessage String 包含一条纪录数据的字符串
	* @return: boolean
	**/
	public boolean decode(String strMessage)
	{
		try
		{
			ActID = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 1, SysConst.PACKAGESPILTER );
			AssID = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 2, SysConst.PACKAGESPILTER );
			Phone = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 3, SysConst.PACKAGESPILTER );
			Title = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 4, SysConst.PACKAGESPILTER );
			ActFee = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 5, SysConst.PACKAGESPILTER );
			ActRule = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 6, SysConst.PACKAGESPILTER );
			ActContent = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 7, SysConst.PACKAGESPILTER );
			ActGift = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 8, SysConst.PACKAGESPILTER );
			AcPeople = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 9, SysConst.PACKAGESPILTER );
			StartDate = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 10, SysConst.PACKAGESPILTER );
			StartTime = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 11, SysConst.PACKAGESPILTER );
			EndDate = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 12, SysConst.PACKAGESPILTER );
			EndTime = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 13, SysConst.PACKAGESPILTER );
			RegEndDate = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 14, SysConst.PACKAGESPILTER );
			RegEndTime = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 15, SysConst.PACKAGESPILTER );
			AppStatus = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 16, SysConst.PACKAGESPILTER );
			Apper = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 17, SysConst.PACKAGESPILTER );
			AppDate = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 18, SysConst.PACKAGESPILTER );
			AppTime = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 19, SysConst.PACKAGESPILTER );
			AppRemark = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 20, SysConst.PACKAGESPILTER );
			R1 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 21, SysConst.PACKAGESPILTER );
			R2 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 22, SysConst.PACKAGESPILTER );
			R3 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 23, SysConst.PACKAGESPILTER );
			R4 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 24, SysConst.PACKAGESPILTER );
			R5 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 25, SysConst.PACKAGESPILTER );
			R6 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 26, SysConst.PACKAGESPILTER );
			R7 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 27, SysConst.PACKAGESPILTER );
			R8 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 28, SysConst.PACKAGESPILTER );
			R9 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 29, SysConst.PACKAGESPILTER );
			R10 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 30, SysConst.PACKAGESPILTER );
			R11 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 31, SysConst.PACKAGESPILTER );
			R12 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 32, SysConst.PACKAGESPILTER );
			R13 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 33, SysConst.PACKAGESPILTER );
			R14 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 34, SysConst.PACKAGESPILTER );
			R15 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 35, SysConst.PACKAGESPILTER );
		}
		catch(NumberFormatException ex)
		{
			// @@错误处理
			CError tError = new CError();
			tError.moduleName = "SDAssActivitySchema";
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
		if (FCode.equalsIgnoreCase("ActID"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(ActID));
		}
		if (FCode.equalsIgnoreCase("AssID"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(AssID));
		}
		if (FCode.equalsIgnoreCase("Phone"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(Phone));
		}
		if (FCode.equalsIgnoreCase("Title"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(Title));
		}
		if (FCode.equalsIgnoreCase("ActFee"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(ActFee));
		}
		if (FCode.equalsIgnoreCase("ActRule"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(ActRule));
		}
		if (FCode.equalsIgnoreCase("ActContent"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(ActContent));
		}
		if (FCode.equalsIgnoreCase("ActGift"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(ActGift));
		}
		if (FCode.equalsIgnoreCase("AcPeople"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(AcPeople));
		}
		if (FCode.equalsIgnoreCase("StartDate"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(StartDate));
		}
		if (FCode.equalsIgnoreCase("StartTime"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(StartTime));
		}
		if (FCode.equalsIgnoreCase("EndDate"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(EndDate));
		}
		if (FCode.equalsIgnoreCase("EndTime"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(EndTime));
		}
		if (FCode.equalsIgnoreCase("RegEndDate"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(RegEndDate));
		}
		if (FCode.equalsIgnoreCase("RegEndTime"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(RegEndTime));
		}
		if (FCode.equalsIgnoreCase("AppStatus"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(AppStatus));
		}
		if (FCode.equalsIgnoreCase("Apper"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(Apper));
		}
		if (FCode.equalsIgnoreCase("AppDate"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(AppDate));
		}
		if (FCode.equalsIgnoreCase("AppTime"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(AppTime));
		}
		if (FCode.equalsIgnoreCase("AppRemark"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(AppRemark));
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
				strFieldValue = StrTool.GBKToUnicode(ActID);
				break;
			case 1:
				strFieldValue = StrTool.GBKToUnicode(AssID);
				break;
			case 2:
				strFieldValue = StrTool.GBKToUnicode(Phone);
				break;
			case 3:
				strFieldValue = StrTool.GBKToUnicode(Title);
				break;
			case 4:
				strFieldValue = StrTool.GBKToUnicode(ActFee);
				break;
			case 5:
				strFieldValue = StrTool.GBKToUnicode(ActRule);
				break;
			case 6:
				strFieldValue = StrTool.GBKToUnicode(ActContent);
				break;
			case 7:
				strFieldValue = StrTool.GBKToUnicode(ActGift);
				break;
			case 8:
				strFieldValue = StrTool.GBKToUnicode(AcPeople);
				break;
			case 9:
				strFieldValue = StrTool.GBKToUnicode(StartDate);
				break;
			case 10:
				strFieldValue = StrTool.GBKToUnicode(StartTime);
				break;
			case 11:
				strFieldValue = StrTool.GBKToUnicode(EndDate);
				break;
			case 12:
				strFieldValue = StrTool.GBKToUnicode(EndTime);
				break;
			case 13:
				strFieldValue = StrTool.GBKToUnicode(RegEndDate);
				break;
			case 14:
				strFieldValue = StrTool.GBKToUnicode(RegEndTime);
				break;
			case 15:
				strFieldValue = StrTool.GBKToUnicode(AppStatus);
				break;
			case 16:
				strFieldValue = StrTool.GBKToUnicode(Apper);
				break;
			case 17:
				strFieldValue = StrTool.GBKToUnicode(AppDate);
				break;
			case 18:
				strFieldValue = StrTool.GBKToUnicode(AppTime);
				break;
			case 19:
				strFieldValue = StrTool.GBKToUnicode(AppRemark);
				break;
			case 20:
				strFieldValue = StrTool.GBKToUnicode(R1);
				break;
			case 21:
				strFieldValue = StrTool.GBKToUnicode(R2);
				break;
			case 22:
				strFieldValue = StrTool.GBKToUnicode(R3);
				break;
			case 23:
				strFieldValue = StrTool.GBKToUnicode(R4);
				break;
			case 24:
				strFieldValue = StrTool.GBKToUnicode(R5);
				break;
			case 25:
				strFieldValue = StrTool.GBKToUnicode(R6);
				break;
			case 26:
				strFieldValue = StrTool.GBKToUnicode(R7);
				break;
			case 27:
				strFieldValue = StrTool.GBKToUnicode(R8);
				break;
			case 28:
				strFieldValue = StrTool.GBKToUnicode(R9);
				break;
			case 29:
				strFieldValue = StrTool.GBKToUnicode(R10);
				break;
			case 30:
				strFieldValue = StrTool.GBKToUnicode(R11);
				break;
			case 31:
				strFieldValue = StrTool.GBKToUnicode(R12);
				break;
			case 32:
				strFieldValue = StrTool.GBKToUnicode(R13);
				break;
			case 33:
				strFieldValue = StrTool.GBKToUnicode(R14);
				break;
			case 34:
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

		if (FCode.equalsIgnoreCase("ActID"))
		{
			if( FValue != null )
			{
				ActID = FValue.trim();
			}
			else
				ActID = null;
		}
		if (FCode.equalsIgnoreCase("AssID"))
		{
			if( FValue != null )
			{
				AssID = FValue.trim();
			}
			else
				AssID = null;
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
		if (FCode.equalsIgnoreCase("Title"))
		{
			if( FValue != null )
			{
				Title = FValue.trim();
			}
			else
				Title = null;
		}
		if (FCode.equalsIgnoreCase("ActFee"))
		{
			if( FValue != null )
			{
				ActFee = FValue.trim();
			}
			else
				ActFee = null;
		}
		if (FCode.equalsIgnoreCase("ActRule"))
		{
			if( FValue != null )
			{
				ActRule = FValue.trim();
			}
			else
				ActRule = null;
		}
		if (FCode.equalsIgnoreCase("ActContent"))
		{
			if( FValue != null )
			{
				ActContent = FValue.trim();
			}
			else
				ActContent = null;
		}
		if (FCode.equalsIgnoreCase("ActGift"))
		{
			if( FValue != null )
			{
				ActGift = FValue.trim();
			}
			else
				ActGift = null;
		}
		if (FCode.equalsIgnoreCase("AcPeople"))
		{
			if( FValue != null )
			{
				AcPeople = FValue.trim();
			}
			else
				AcPeople = null;
		}
		if (FCode.equalsIgnoreCase("StartDate"))
		{
			if( FValue != null )
			{
				StartDate = FValue.trim();
			}
			else
				StartDate = null;
		}
		if (FCode.equalsIgnoreCase("StartTime"))
		{
			if( FValue != null )
			{
				StartTime = FValue.trim();
			}
			else
				StartTime = null;
		}
		if (FCode.equalsIgnoreCase("EndDate"))
		{
			if( FValue != null )
			{
				EndDate = FValue.trim();
			}
			else
				EndDate = null;
		}
		if (FCode.equalsIgnoreCase("EndTime"))
		{
			if( FValue != null )
			{
				EndTime = FValue.trim();
			}
			else
				EndTime = null;
		}
		if (FCode.equalsIgnoreCase("RegEndDate"))
		{
			if( FValue != null )
			{
				RegEndDate = FValue.trim();
			}
			else
				RegEndDate = null;
		}
		if (FCode.equalsIgnoreCase("RegEndTime"))
		{
			if( FValue != null )
			{
				RegEndTime = FValue.trim();
			}
			else
				RegEndTime = null;
		}
		if (FCode.equalsIgnoreCase("AppStatus"))
		{
			if( FValue != null )
			{
				AppStatus = FValue.trim();
			}
			else
				AppStatus = null;
		}
		if (FCode.equalsIgnoreCase("Apper"))
		{
			if( FValue != null )
			{
				Apper = FValue.trim();
			}
			else
				Apper = null;
		}
		if (FCode.equalsIgnoreCase("AppDate"))
		{
			if( FValue != null )
			{
				AppDate = FValue.trim();
			}
			else
				AppDate = null;
		}
		if (FCode.equalsIgnoreCase("AppTime"))
		{
			if( FValue != null )
			{
				AppTime = FValue.trim();
			}
			else
				AppTime = null;
		}
		if (FCode.equalsIgnoreCase("AppRemark"))
		{
			if( FValue != null )
			{
				AppRemark = FValue.trim();
			}
			else
				AppRemark = null;
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
		SDAssActivitySchema other = (SDAssActivitySchema)otherObject;
		return
			ActID.equals(other.getActID())
			&& AssID.equals(other.getAssID())
			&& Phone.equals(other.getPhone())
			&& Title.equals(other.getTitle())
			&& ActFee.equals(other.getActFee())
			&& ActRule.equals(other.getActRule())
			&& ActContent.equals(other.getActContent())
			&& ActGift.equals(other.getActGift())
			&& AcPeople.equals(other.getAcPeople())
			&& StartDate.equals(other.getStartDate())
			&& StartTime.equals(other.getStartTime())
			&& EndDate.equals(other.getEndDate())
			&& EndTime.equals(other.getEndTime())
			&& RegEndDate.equals(other.getRegEndDate())
			&& RegEndTime.equals(other.getRegEndTime())
			&& AppStatus.equals(other.getAppStatus())
			&& Apper.equals(other.getApper())
			&& AppDate.equals(other.getAppDate())
			&& AppTime.equals(other.getAppTime())
			&& AppRemark.equals(other.getAppRemark())
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
		if( strFieldName.equals("ActID") ) {
			return 0;
		}
		if( strFieldName.equals("AssID") ) {
			return 1;
		}
		if( strFieldName.equals("Phone") ) {
			return 2;
		}
		if( strFieldName.equals("Title") ) {
			return 3;
		}
		if( strFieldName.equals("ActFee") ) {
			return 4;
		}
		if( strFieldName.equals("ActRule") ) {
			return 5;
		}
		if( strFieldName.equals("ActContent") ) {
			return 6;
		}
		if( strFieldName.equals("ActGift") ) {
			return 7;
		}
		if( strFieldName.equals("AcPeople") ) {
			return 8;
		}
		if( strFieldName.equals("StartDate") ) {
			return 9;
		}
		if( strFieldName.equals("StartTime") ) {
			return 10;
		}
		if( strFieldName.equals("EndDate") ) {
			return 11;
		}
		if( strFieldName.equals("EndTime") ) {
			return 12;
		}
		if( strFieldName.equals("RegEndDate") ) {
			return 13;
		}
		if( strFieldName.equals("RegEndTime") ) {
			return 14;
		}
		if( strFieldName.equals("AppStatus") ) {
			return 15;
		}
		if( strFieldName.equals("Apper") ) {
			return 16;
		}
		if( strFieldName.equals("AppDate") ) {
			return 17;
		}
		if( strFieldName.equals("AppTime") ) {
			return 18;
		}
		if( strFieldName.equals("AppRemark") ) {
			return 19;
		}
		if( strFieldName.equals("R1") ) {
			return 20;
		}
		if( strFieldName.equals("R2") ) {
			return 21;
		}
		if( strFieldName.equals("R3") ) {
			return 22;
		}
		if( strFieldName.equals("R4") ) {
			return 23;
		}
		if( strFieldName.equals("R5") ) {
			return 24;
		}
		if( strFieldName.equals("R6") ) {
			return 25;
		}
		if( strFieldName.equals("R7") ) {
			return 26;
		}
		if( strFieldName.equals("R8") ) {
			return 27;
		}
		if( strFieldName.equals("R9") ) {
			return 28;
		}
		if( strFieldName.equals("R10") ) {
			return 29;
		}
		if( strFieldName.equals("R11") ) {
			return 30;
		}
		if( strFieldName.equals("R12") ) {
			return 31;
		}
		if( strFieldName.equals("R13") ) {
			return 32;
		}
		if( strFieldName.equals("R14") ) {
			return 33;
		}
		if( strFieldName.equals("R15") ) {
			return 34;
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
				strFieldName = "ActID";
				break;
			case 1:
				strFieldName = "AssID";
				break;
			case 2:
				strFieldName = "Phone";
				break;
			case 3:
				strFieldName = "Title";
				break;
			case 4:
				strFieldName = "ActFee";
				break;
			case 5:
				strFieldName = "ActRule";
				break;
			case 6:
				strFieldName = "ActContent";
				break;
			case 7:
				strFieldName = "ActGift";
				break;
			case 8:
				strFieldName = "AcPeople";
				break;
			case 9:
				strFieldName = "StartDate";
				break;
			case 10:
				strFieldName = "StartTime";
				break;
			case 11:
				strFieldName = "EndDate";
				break;
			case 12:
				strFieldName = "EndTime";
				break;
			case 13:
				strFieldName = "RegEndDate";
				break;
			case 14:
				strFieldName = "RegEndTime";
				break;
			case 15:
				strFieldName = "AppStatus";
				break;
			case 16:
				strFieldName = "Apper";
				break;
			case 17:
				strFieldName = "AppDate";
				break;
			case 18:
				strFieldName = "AppTime";
				break;
			case 19:
				strFieldName = "AppRemark";
				break;
			case 20:
				strFieldName = "R1";
				break;
			case 21:
				strFieldName = "R2";
				break;
			case 22:
				strFieldName = "R3";
				break;
			case 23:
				strFieldName = "R4";
				break;
			case 24:
				strFieldName = "R5";
				break;
			case 25:
				strFieldName = "R6";
				break;
			case 26:
				strFieldName = "R7";
				break;
			case 27:
				strFieldName = "R8";
				break;
			case 28:
				strFieldName = "R9";
				break;
			case 29:
				strFieldName = "R10";
				break;
			case 30:
				strFieldName = "R11";
				break;
			case 31:
				strFieldName = "R12";
				break;
			case 32:
				strFieldName = "R13";
				break;
			case 33:
				strFieldName = "R14";
				break;
			case 34:
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
		if( strFieldName.equals("ActID") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("AssID") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("Phone") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("Title") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("ActFee") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("ActRule") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("ActContent") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("ActGift") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("AcPeople") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("StartDate") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("StartTime") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("EndDate") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("EndTime") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("RegEndDate") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("RegEndTime") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("AppStatus") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("Apper") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("AppDate") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("AppTime") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("AppRemark") ) {
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
			case 24:
				nFieldType = Schema.TYPE_STRING;
				break;
			case 25:
				nFieldType = Schema.TYPE_STRING;
				break;
			case 26:
				nFieldType = Schema.TYPE_STRING;
				break;
			case 27:
				nFieldType = Schema.TYPE_STRING;
				break;
			case 28:
				nFieldType = Schema.TYPE_STRING;
				break;
			case 29:
				nFieldType = Schema.TYPE_STRING;
				break;
			case 30:
				nFieldType = Schema.TYPE_STRING;
				break;
			case 31:
				nFieldType = Schema.TYPE_STRING;
				break;
			case 32:
				nFieldType = Schema.TYPE_STRING;
				break;
			case 33:
				nFieldType = Schema.TYPE_STRING;
				break;
			case 34:
				nFieldType = Schema.TYPE_STRING;
				break;
			default:
				nFieldType = Schema.TYPE_NOFOUND;
		};
		return nFieldType;
	}
}
