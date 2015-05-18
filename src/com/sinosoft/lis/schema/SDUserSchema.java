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
import com.sinosoft.lis.db.SDUserDB;

/*
 * <p>ClassName: SDUserSchema </p>
 * <p>Description: DB层 Schema 类文件 </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: sinosoft </p>
 * @Database: PhysicalDataModel_1
 */
public class SDUserSchema implements Schema, Cloneable
{
	// @Field
	/** 用户编码 */
	private String UserID;
	/** 密码 */
	private String Password;
	/** 用户名 */
	private String UserName;
	/** 姓名或昵称 */
	private String NickName;
	/** 电子邮件 */
	private String Email;
	/** 手机 */
	private String Phone;
	/** 是否认证 */
	private String AuthFlag;
	/** 教工号 */
	private String TNo;
	/** 证件号 */
	private String IDNo;
	/** 地址 */
	private String Address;
	/** 家中特殊人群 */
	private String Special;
	/** 注册日期 */
	private String RegDate;
	/** 注册时间 */
	private String RegTime;
	/** 最后登录日期 */
	private String LastLoginDate;
	/** 最后登录时间 */
	private String LastLoginTime;
	/** 修改密码日期 */
	private String PassModDate;
	/** 修改密码时间 */
	private String PassModTime;
	/** 上一个密码 */
	private String LastPass;
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

	public static final int FIELDNUM = 33;	// 数据库表的字段个数

	private static String[] PK;				// 主键

	public CErrors mErrors;			// 错误信息

	// @Constructor
	public SDUserSchema()
	{
		mErrors = new CErrors();

		String[] pk = new String[1];
		pk[0] = "UserID";

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
		SDUserSchema cloned = (SDUserSchema)super.clone();
		cloned.mErrors = (CErrors) mErrors.clone();
		return cloned;
	}

	// @Method
	public String[] getPK()
	{
		return PK;
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
	public String getPassword()
	{
if(Password==null)Password="";
		return Password;
	}
	public void setPassword(String aPassword)
	{
		Password = aPassword;
	}
	public String getUserName()
	{
if(UserName==null)UserName="";
		return UserName;
	}
	public void setUserName(String aUserName)
	{
		UserName = aUserName;
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
	public String getEmail()
	{
if(Email==null)Email="";
		return Email;
	}
	public void setEmail(String aEmail)
	{
		Email = aEmail;
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
	public String getAuthFlag()
	{
if(AuthFlag==null)AuthFlag="";
		return AuthFlag;
	}
	public void setAuthFlag(String aAuthFlag)
	{
		AuthFlag = aAuthFlag;
	}
	public String getTNo()
	{
if(TNo==null)TNo="";
		return TNo;
	}
	public void setTNo(String aTNo)
	{
		TNo = aTNo;
	}
	public String getIDNo()
	{
if(IDNo==null)IDNo="";
		return IDNo;
	}
	public void setIDNo(String aIDNo)
	{
		IDNo = aIDNo;
	}
	public String getAddress()
	{
if(Address==null)Address="";
		return Address;
	}
	public void setAddress(String aAddress)
	{
		Address = aAddress;
	}
	public String getSpecial()
	{
if(Special==null)Special="";
		return Special;
	}
	public void setSpecial(String aSpecial)
	{
		Special = aSpecial;
	}
	public String getRegDate()
	{
if(RegDate==null)RegDate="";
		return RegDate;
	}
	public void setRegDate(String aRegDate)
	{
		RegDate = aRegDate;
	}
	public String getRegTime()
	{
if(RegTime==null)RegTime="";
		return RegTime;
	}
	public void setRegTime(String aRegTime)
	{
		RegTime = aRegTime;
	}
	public String getLastLoginDate()
	{
if(LastLoginDate==null)LastLoginDate="";
		return LastLoginDate;
	}
	public void setLastLoginDate(String aLastLoginDate)
	{
		LastLoginDate = aLastLoginDate;
	}
	public String getLastLoginTime()
	{
if(LastLoginTime==null)LastLoginTime="";
		return LastLoginTime;
	}
	public void setLastLoginTime(String aLastLoginTime)
	{
		LastLoginTime = aLastLoginTime;
	}
	public String getPassModDate()
	{
if(PassModDate==null)PassModDate="";
		return PassModDate;
	}
	public void setPassModDate(String aPassModDate)
	{
		PassModDate = aPassModDate;
	}
	public String getPassModTime()
	{
if(PassModTime==null)PassModTime="";
		return PassModTime;
	}
	public void setPassModTime(String aPassModTime)
	{
		PassModTime = aPassModTime;
	}
	public String getLastPass()
	{
if(LastPass==null)LastPass="";
		return LastPass;
	}
	public void setLastPass(String aLastPass)
	{
		LastPass = aLastPass;
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
	* 使用另外一个 SDUserSchema 对象给 Schema 赋值
	* @param: aSDUserSchema SDUserSchema
	**/
	public void setSchema(SDUserSchema aSDUserSchema)
	{
		this.UserID = aSDUserSchema.getUserID();
		this.Password = aSDUserSchema.getPassword();
		this.UserName = aSDUserSchema.getUserName();
		this.NickName = aSDUserSchema.getNickName();
		this.Email = aSDUserSchema.getEmail();
		this.Phone = aSDUserSchema.getPhone();
		this.AuthFlag = aSDUserSchema.getAuthFlag();
		this.TNo = aSDUserSchema.getTNo();
		this.IDNo = aSDUserSchema.getIDNo();
		this.Address = aSDUserSchema.getAddress();
		this.Special = aSDUserSchema.getSpecial();
		this.RegDate = aSDUserSchema.getRegDate();
		this.RegTime = aSDUserSchema.getRegTime();
		this.LastLoginDate = aSDUserSchema.getLastLoginDate();
		this.LastLoginTime = aSDUserSchema.getLastLoginTime();
		this.PassModDate = aSDUserSchema.getPassModDate();
		this.PassModTime = aSDUserSchema.getPassModTime();
		this.LastPass = aSDUserSchema.getLastPass();
		this.R1 = aSDUserSchema.getR1();
		this.R2 = aSDUserSchema.getR2();
		this.R3 = aSDUserSchema.getR3();
		this.R4 = aSDUserSchema.getR4();
		this.R5 = aSDUserSchema.getR5();
		this.R6 = aSDUserSchema.getR6();
		this.R7 = aSDUserSchema.getR7();
		this.R8 = aSDUserSchema.getR8();
		this.R9 = aSDUserSchema.getR9();
		this.R10 = aSDUserSchema.getR10();
		this.R11 = aSDUserSchema.getR11();
		this.R12 = aSDUserSchema.getR12();
		this.R13 = aSDUserSchema.getR13();
		this.R14 = aSDUserSchema.getR14();
		this.R15 = aSDUserSchema.getR15();
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
			if( rs.getString("UserID") == null )
				this.UserID = null;
			else
				this.UserID = rs.getString("UserID").trim();

			if( rs.getString("Password") == null )
				this.Password = null;
			else
				this.Password = rs.getString("Password").trim();

			if( rs.getString("UserName") == null )
				this.UserName = null;
			else
				this.UserName = rs.getString("UserName").trim();

			if( rs.getString("NickName") == null )
				this.NickName = null;
			else
				this.NickName = rs.getString("NickName").trim();

			if( rs.getString("Email") == null )
				this.Email = null;
			else
				this.Email = rs.getString("Email").trim();

			if( rs.getString("Phone") == null )
				this.Phone = null;
			else
				this.Phone = rs.getString("Phone").trim();

			if( rs.getString("AuthFlag") == null )
				this.AuthFlag = null;
			else
				this.AuthFlag = rs.getString("AuthFlag").trim();

			if( rs.getString("TNo") == null )
				this.TNo = null;
			else
				this.TNo = rs.getString("TNo").trim();

			if( rs.getString("IDNo") == null )
				this.IDNo = null;
			else
				this.IDNo = rs.getString("IDNo").trim();

			if( rs.getString("Address") == null )
				this.Address = null;
			else
				this.Address = rs.getString("Address").trim();

			if( rs.getString("Special") == null )
				this.Special = null;
			else
				this.Special = rs.getString("Special").trim();

			if( rs.getString("RegDate") == null )
				this.RegDate = null;
			else
				this.RegDate = rs.getString("RegDate").trim();

			if( rs.getString("RegTime") == null )
				this.RegTime = null;
			else
				this.RegTime = rs.getString("RegTime").trim();

			if( rs.getString("LastLoginDate") == null )
				this.LastLoginDate = null;
			else
				this.LastLoginDate = rs.getString("LastLoginDate").trim();

			if( rs.getString("LastLoginTime") == null )
				this.LastLoginTime = null;
			else
				this.LastLoginTime = rs.getString("LastLoginTime").trim();

			if( rs.getString("PassModDate") == null )
				this.PassModDate = null;
			else
				this.PassModDate = rs.getString("PassModDate").trim();

			if( rs.getString("PassModTime") == null )
				this.PassModTime = null;
			else
				this.PassModTime = rs.getString("PassModTime").trim();

			if( rs.getString("LastPass") == null )
				this.LastPass = null;
			else
				this.LastPass = rs.getString("LastPass").trim();

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
			System.out.println("数据库中的SDUser表字段个数和Schema中的字段个数不一致，或者执行db.executeQuery查询时没有使用select * from tables");
			// @@错误处理
			CError tError = new CError();
			tError.moduleName = "SDUserSchema";
			tError.functionName = "setSchema";
			tError.errorMessage = sqle.toString();
			this.mErrors .addOneError(tError);
			return false;
		}
		return true;
	}

	public SDUserSchema getSchema()
	{
		SDUserSchema aSDUserSchema = new SDUserSchema();
		aSDUserSchema.setSchema(this);
		return aSDUserSchema;
	}

	public SDUserDB getDB()
	{
		SDUserDB aDBOper = new SDUserDB();
		aDBOper.setSchema(this);
		return aDBOper;
	}


	/**
	* 数据打包，按 XML 格式打包，顺序参见<A href ={@docRoot}/dataStructure/tb.html#PrpSDUser描述/A>表字段
	* @return: String 返回打包后字符串
	**/
	public String encode()
	{
		StringBuffer strReturn = new StringBuffer(256);
		strReturn.append(StrTool.cTrim(UserID)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(Password)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(UserName)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(NickName)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(Email)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(Phone)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(AuthFlag)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(TNo)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(IDNo)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(Address)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(Special)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(RegDate)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(RegTime)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(LastLoginDate)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(LastLoginTime)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(PassModDate)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(PassModTime)); strReturn.append(SysConst.PACKAGESPILTER);
		strReturn.append(StrTool.cTrim(LastPass)); strReturn.append(SysConst.PACKAGESPILTER);
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
	* 数据解包，解包顺序参见<A href ={@docRoot}/dataStructure/tb.html#PrpSDUser>历史记账凭证主表信息</A>表字段
	* @param: strMessage String 包含一条纪录数据的字符串
	* @return: boolean
	**/
	public boolean decode(String strMessage)
	{
		try
		{
			UserID = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 1, SysConst.PACKAGESPILTER );
			Password = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 2, SysConst.PACKAGESPILTER );
			UserName = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 3, SysConst.PACKAGESPILTER );
			NickName = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 4, SysConst.PACKAGESPILTER );
			Email = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 5, SysConst.PACKAGESPILTER );
			Phone = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 6, SysConst.PACKAGESPILTER );
			AuthFlag = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 7, SysConst.PACKAGESPILTER );
			TNo = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 8, SysConst.PACKAGESPILTER );
			IDNo = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 9, SysConst.PACKAGESPILTER );
			Address = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 10, SysConst.PACKAGESPILTER );
			Special = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 11, SysConst.PACKAGESPILTER );
			RegDate = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 12, SysConst.PACKAGESPILTER );
			RegTime = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 13, SysConst.PACKAGESPILTER );
			LastLoginDate = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 14, SysConst.PACKAGESPILTER );
			LastLoginTime = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 15, SysConst.PACKAGESPILTER );
			PassModDate = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 16, SysConst.PACKAGESPILTER );
			PassModTime = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 17, SysConst.PACKAGESPILTER );
			LastPass = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 18, SysConst.PACKAGESPILTER );
			R1 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 19, SysConst.PACKAGESPILTER );
			R2 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 20, SysConst.PACKAGESPILTER );
			R3 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 21, SysConst.PACKAGESPILTER );
			R4 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 22, SysConst.PACKAGESPILTER );
			R5 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 23, SysConst.PACKAGESPILTER );
			R6 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 24, SysConst.PACKAGESPILTER );
			R7 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 25, SysConst.PACKAGESPILTER );
			R8 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 26, SysConst.PACKAGESPILTER );
			R9 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 27, SysConst.PACKAGESPILTER );
			R10 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 28, SysConst.PACKAGESPILTER );
			R11 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 29, SysConst.PACKAGESPILTER );
			R12 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 30, SysConst.PACKAGESPILTER );
			R13 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 31, SysConst.PACKAGESPILTER );
			R14 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 32, SysConst.PACKAGESPILTER );
			R15 = StrTool.getStr(StrTool.GBKToUnicode(strMessage), 33, SysConst.PACKAGESPILTER );
		}
		catch(NumberFormatException ex)
		{
			// @@错误处理
			CError tError = new CError();
			tError.moduleName = "SDUserSchema";
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
		if (FCode.equalsIgnoreCase("UserID"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(UserID));
		}
		if (FCode.equalsIgnoreCase("Password"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(Password));
		}
		if (FCode.equalsIgnoreCase("UserName"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(UserName));
		}
		if (FCode.equalsIgnoreCase("NickName"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(NickName));
		}
		if (FCode.equalsIgnoreCase("Email"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(Email));
		}
		if (FCode.equalsIgnoreCase("Phone"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(Phone));
		}
		if (FCode.equalsIgnoreCase("AuthFlag"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(AuthFlag));
		}
		if (FCode.equalsIgnoreCase("TNo"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(TNo));
		}
		if (FCode.equalsIgnoreCase("IDNo"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(IDNo));
		}
		if (FCode.equalsIgnoreCase("Address"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(Address));
		}
		if (FCode.equalsIgnoreCase("Special"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(Special));
		}
		if (FCode.equalsIgnoreCase("RegDate"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(RegDate));
		}
		if (FCode.equalsIgnoreCase("RegTime"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(RegTime));
		}
		if (FCode.equalsIgnoreCase("LastLoginDate"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(LastLoginDate));
		}
		if (FCode.equalsIgnoreCase("LastLoginTime"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(LastLoginTime));
		}
		if (FCode.equalsIgnoreCase("PassModDate"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(PassModDate));
		}
		if (FCode.equalsIgnoreCase("PassModTime"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(PassModTime));
		}
		if (FCode.equalsIgnoreCase("LastPass"))
		{
			strReturn = StrTool.GBKToUnicode(String.valueOf(LastPass));
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
				strFieldValue = StrTool.GBKToUnicode(UserID);
				break;
			case 1:
				strFieldValue = StrTool.GBKToUnicode(Password);
				break;
			case 2:
				strFieldValue = StrTool.GBKToUnicode(UserName);
				break;
			case 3:
				strFieldValue = StrTool.GBKToUnicode(NickName);
				break;
			case 4:
				strFieldValue = StrTool.GBKToUnicode(Email);
				break;
			case 5:
				strFieldValue = StrTool.GBKToUnicode(Phone);
				break;
			case 6:
				strFieldValue = StrTool.GBKToUnicode(AuthFlag);
				break;
			case 7:
				strFieldValue = StrTool.GBKToUnicode(TNo);
				break;
			case 8:
				strFieldValue = StrTool.GBKToUnicode(IDNo);
				break;
			case 9:
				strFieldValue = StrTool.GBKToUnicode(Address);
				break;
			case 10:
				strFieldValue = StrTool.GBKToUnicode(Special);
				break;
			case 11:
				strFieldValue = StrTool.GBKToUnicode(RegDate);
				break;
			case 12:
				strFieldValue = StrTool.GBKToUnicode(RegTime);
				break;
			case 13:
				strFieldValue = StrTool.GBKToUnicode(LastLoginDate);
				break;
			case 14:
				strFieldValue = StrTool.GBKToUnicode(LastLoginTime);
				break;
			case 15:
				strFieldValue = StrTool.GBKToUnicode(PassModDate);
				break;
			case 16:
				strFieldValue = StrTool.GBKToUnicode(PassModTime);
				break;
			case 17:
				strFieldValue = StrTool.GBKToUnicode(LastPass);
				break;
			case 18:
				strFieldValue = StrTool.GBKToUnicode(R1);
				break;
			case 19:
				strFieldValue = StrTool.GBKToUnicode(R2);
				break;
			case 20:
				strFieldValue = StrTool.GBKToUnicode(R3);
				break;
			case 21:
				strFieldValue = StrTool.GBKToUnicode(R4);
				break;
			case 22:
				strFieldValue = StrTool.GBKToUnicode(R5);
				break;
			case 23:
				strFieldValue = StrTool.GBKToUnicode(R6);
				break;
			case 24:
				strFieldValue = StrTool.GBKToUnicode(R7);
				break;
			case 25:
				strFieldValue = StrTool.GBKToUnicode(R8);
				break;
			case 26:
				strFieldValue = StrTool.GBKToUnicode(R9);
				break;
			case 27:
				strFieldValue = StrTool.GBKToUnicode(R10);
				break;
			case 28:
				strFieldValue = StrTool.GBKToUnicode(R11);
				break;
			case 29:
				strFieldValue = StrTool.GBKToUnicode(R12);
				break;
			case 30:
				strFieldValue = StrTool.GBKToUnicode(R13);
				break;
			case 31:
				strFieldValue = StrTool.GBKToUnicode(R14);
				break;
			case 32:
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

		if (FCode.equalsIgnoreCase("UserID"))
		{
			if( FValue != null )
			{
				UserID = FValue.trim();
			}
			else
				UserID = null;
		}
		if (FCode.equalsIgnoreCase("Password"))
		{
			if( FValue != null )
			{
				Password = FValue.trim();
			}
			else
				Password = null;
		}
		if (FCode.equalsIgnoreCase("UserName"))
		{
			if( FValue != null )
			{
				UserName = FValue.trim();
			}
			else
				UserName = null;
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
		if (FCode.equalsIgnoreCase("Email"))
		{
			if( FValue != null )
			{
				Email = FValue.trim();
			}
			else
				Email = null;
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
		if (FCode.equalsIgnoreCase("AuthFlag"))
		{
			if( FValue != null )
			{
				AuthFlag = FValue.trim();
			}
			else
				AuthFlag = null;
		}
		if (FCode.equalsIgnoreCase("TNo"))
		{
			if( FValue != null )
			{
				TNo = FValue.trim();
			}
			else
				TNo = null;
		}
		if (FCode.equalsIgnoreCase("IDNo"))
		{
			if( FValue != null )
			{
				IDNo = FValue.trim();
			}
			else
				IDNo = null;
		}
		if (FCode.equalsIgnoreCase("Address"))
		{
			if( FValue != null )
			{
				Address = FValue.trim();
			}
			else
				Address = null;
		}
		if (FCode.equalsIgnoreCase("Special"))
		{
			if( FValue != null )
			{
				Special = FValue.trim();
			}
			else
				Special = null;
		}
		if (FCode.equalsIgnoreCase("RegDate"))
		{
			if( FValue != null )
			{
				RegDate = FValue.trim();
			}
			else
				RegDate = null;
		}
		if (FCode.equalsIgnoreCase("RegTime"))
		{
			if( FValue != null )
			{
				RegTime = FValue.trim();
			}
			else
				RegTime = null;
		}
		if (FCode.equalsIgnoreCase("LastLoginDate"))
		{
			if( FValue != null )
			{
				LastLoginDate = FValue.trim();
			}
			else
				LastLoginDate = null;
		}
		if (FCode.equalsIgnoreCase("LastLoginTime"))
		{
			if( FValue != null )
			{
				LastLoginTime = FValue.trim();
			}
			else
				LastLoginTime = null;
		}
		if (FCode.equalsIgnoreCase("PassModDate"))
		{
			if( FValue != null )
			{
				PassModDate = FValue.trim();
			}
			else
				PassModDate = null;
		}
		if (FCode.equalsIgnoreCase("PassModTime"))
		{
			if( FValue != null )
			{
				PassModTime = FValue.trim();
			}
			else
				PassModTime = null;
		}
		if (FCode.equalsIgnoreCase("LastPass"))
		{
			if( FValue != null )
			{
				LastPass = FValue.trim();
			}
			else
				LastPass = null;
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
		SDUserSchema other = (SDUserSchema)otherObject;
		return
			UserID.equals(other.getUserID())
			&& Password.equals(other.getPassword())
			&& UserName.equals(other.getUserName())
			&& NickName.equals(other.getNickName())
			&& Email.equals(other.getEmail())
			&& Phone.equals(other.getPhone())
			&& AuthFlag.equals(other.getAuthFlag())
			&& TNo.equals(other.getTNo())
			&& IDNo.equals(other.getIDNo())
			&& Address.equals(other.getAddress())
			&& Special.equals(other.getSpecial())
			&& RegDate.equals(other.getRegDate())
			&& RegTime.equals(other.getRegTime())
			&& LastLoginDate.equals(other.getLastLoginDate())
			&& LastLoginTime.equals(other.getLastLoginTime())
			&& PassModDate.equals(other.getPassModDate())
			&& PassModTime.equals(other.getPassModTime())
			&& LastPass.equals(other.getLastPass())
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
		if( strFieldName.equals("UserID") ) {
			return 0;
		}
		if( strFieldName.equals("Password") ) {
			return 1;
		}
		if( strFieldName.equals("UserName") ) {
			return 2;
		}
		if( strFieldName.equals("NickName") ) {
			return 3;
		}
		if( strFieldName.equals("Email") ) {
			return 4;
		}
		if( strFieldName.equals("Phone") ) {
			return 5;
		}
		if( strFieldName.equals("AuthFlag") ) {
			return 6;
		}
		if( strFieldName.equals("TNo") ) {
			return 7;
		}
		if( strFieldName.equals("IDNo") ) {
			return 8;
		}
		if( strFieldName.equals("Address") ) {
			return 9;
		}
		if( strFieldName.equals("Special") ) {
			return 10;
		}
		if( strFieldName.equals("RegDate") ) {
			return 11;
		}
		if( strFieldName.equals("RegTime") ) {
			return 12;
		}
		if( strFieldName.equals("LastLoginDate") ) {
			return 13;
		}
		if( strFieldName.equals("LastLoginTime") ) {
			return 14;
		}
		if( strFieldName.equals("PassModDate") ) {
			return 15;
		}
		if( strFieldName.equals("PassModTime") ) {
			return 16;
		}
		if( strFieldName.equals("LastPass") ) {
			return 17;
		}
		if( strFieldName.equals("R1") ) {
			return 18;
		}
		if( strFieldName.equals("R2") ) {
			return 19;
		}
		if( strFieldName.equals("R3") ) {
			return 20;
		}
		if( strFieldName.equals("R4") ) {
			return 21;
		}
		if( strFieldName.equals("R5") ) {
			return 22;
		}
		if( strFieldName.equals("R6") ) {
			return 23;
		}
		if( strFieldName.equals("R7") ) {
			return 24;
		}
		if( strFieldName.equals("R8") ) {
			return 25;
		}
		if( strFieldName.equals("R9") ) {
			return 26;
		}
		if( strFieldName.equals("R10") ) {
			return 27;
		}
		if( strFieldName.equals("R11") ) {
			return 28;
		}
		if( strFieldName.equals("R12") ) {
			return 29;
		}
		if( strFieldName.equals("R13") ) {
			return 30;
		}
		if( strFieldName.equals("R14") ) {
			return 31;
		}
		if( strFieldName.equals("R15") ) {
			return 32;
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
				strFieldName = "UserID";
				break;
			case 1:
				strFieldName = "Password";
				break;
			case 2:
				strFieldName = "UserName";
				break;
			case 3:
				strFieldName = "NickName";
				break;
			case 4:
				strFieldName = "Email";
				break;
			case 5:
				strFieldName = "Phone";
				break;
			case 6:
				strFieldName = "AuthFlag";
				break;
			case 7:
				strFieldName = "TNo";
				break;
			case 8:
				strFieldName = "IDNo";
				break;
			case 9:
				strFieldName = "Address";
				break;
			case 10:
				strFieldName = "Special";
				break;
			case 11:
				strFieldName = "RegDate";
				break;
			case 12:
				strFieldName = "RegTime";
				break;
			case 13:
				strFieldName = "LastLoginDate";
				break;
			case 14:
				strFieldName = "LastLoginTime";
				break;
			case 15:
				strFieldName = "PassModDate";
				break;
			case 16:
				strFieldName = "PassModTime";
				break;
			case 17:
				strFieldName = "LastPass";
				break;
			case 18:
				strFieldName = "R1";
				break;
			case 19:
				strFieldName = "R2";
				break;
			case 20:
				strFieldName = "R3";
				break;
			case 21:
				strFieldName = "R4";
				break;
			case 22:
				strFieldName = "R5";
				break;
			case 23:
				strFieldName = "R6";
				break;
			case 24:
				strFieldName = "R7";
				break;
			case 25:
				strFieldName = "R8";
				break;
			case 26:
				strFieldName = "R9";
				break;
			case 27:
				strFieldName = "R10";
				break;
			case 28:
				strFieldName = "R11";
				break;
			case 29:
				strFieldName = "R12";
				break;
			case 30:
				strFieldName = "R13";
				break;
			case 31:
				strFieldName = "R14";
				break;
			case 32:
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
		if( strFieldName.equals("UserID") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("Password") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("UserName") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("NickName") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("Email") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("Phone") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("AuthFlag") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("TNo") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("IDNo") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("Address") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("Special") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("RegDate") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("RegTime") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("LastLoginDate") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("LastLoginTime") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("PassModDate") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("PassModTime") ) {
			return Schema.TYPE_STRING;
		}
		if( strFieldName.equals("LastPass") ) {
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
			default:
				nFieldType = Schema.TYPE_NOFOUND;
		};
		return nFieldType;
	}
}
