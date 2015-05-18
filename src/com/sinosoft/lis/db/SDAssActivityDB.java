/**
 * Copyright (c) 2002 sinosoft  Co. Ltd.
 * All right reserved.
 */

package com.sinosoft.lis.db;

import java.sql.*;
import com.sinosoft.lis.schema.SDAssActivitySchema;
import com.sinosoft.lis.vschema.SDAssActivitySet;
import com.sinosoft.lis.pubfun.PubFun;
import com.sinosoft.utility.*;

/*
 * <p>ClassName: SDAssActivityDB </p>
 * <p>Description: DB层数据库操作类文件 </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: sinosoft </p>
 * @Database: PhysicalDataModel_1
 */
public class SDAssActivityDB extends SDAssActivitySchema
{
	// @Field
	private Connection con;
	private DBOper db;
	/**
	* flag = true: 传入Connection
	* flag = false: 不传入Connection
	**/
	private boolean mflag = false;

	public CErrors mErrors = new CErrors();		// 错误信息

	/**
	 * 为批量操作而准备的语句和游标对象
	 */
	private ResultSet mResultSet = null;
	private Statement mStatement = null;
	// @Constructor
	public SDAssActivityDB( Connection tConnection )
	{
		con = tConnection;
		db = new DBOper( con, "SDAssActivity" );
		mflag = true;
	}

	public SDAssActivityDB()
	{
		con = null;
		db = new DBOper( "SDAssActivity" );
		mflag = false;
	}

	// @Method
	public boolean deleteSQL()
	{
		SDAssActivitySchema tSchema = this.getSchema();
		if (db.deleteSQL(tSchema))
		{
		     return true;
		}
		else
		{
			// @@错误处理
			this.mErrors.copyAllErrors(db.mErrors);
			CError tError = new CError();
			tError.moduleName = "SDAssActivityDB";
			tError.functionName = "deleteSQL";
			tError.errorMessage = "操作失败!";
			this.mErrors .addOneError(tError);
			return false;
		}
	}

	public int getCount()
	{
		SDAssActivitySchema tSchema = this.getSchema();

		int tCount = db.getCount(tSchema);
		if (tCount < 0)
		{
			// @@错误处理
			this.mErrors.copyAllErrors(db.mErrors);
			CError tError = new CError();
			tError.moduleName = "SDAssActivityDB";
			tError.functionName = "getCount";
			tError.errorMessage = "操作失败!";
			this.mErrors .addOneError(tError);

			return -1;
		}

		return tCount;
	}

	public boolean delete()
	{
		PreparedStatement pstmt = null;

		if( !mflag ) {
			con = DBConnPool.getConnection();
		}

		try
		{
			pstmt = con.prepareStatement("DELETE FROM SDAssActivity WHERE  ActID = ?");
			if(this.getActID() == null || this.getActID().equals("null")) {
				pstmt.setNull(1, 12);
			} else {
				pstmt.setString(1, this.getActID());
			}
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception ex) {
			// @@错误处理
			this.mErrors.copyAllErrors(db.mErrors);
			CError tError = new CError();
			tError.moduleName = "SDAssActivityDB";
			tError.functionName = "delete()";
			tError.errorMessage = ex.toString();
			this.mErrors .addOneError(tError);

		// only for debug purpose
		SQLString sqlObj = new SQLString("SDAssActivity");
		sqlObj.setSQL(4, this);
		sqlObj.getSQL();

			try {
				pstmt.close();
			} catch (Exception e){}

			if( !mflag ) {
				try {
					con.close();
				} catch (Exception e){}
			}

			return false;
		}

		if( !mflag ) {
			try {
				con.close();
			} catch (Exception e){}
		}

		return true;
	}

	public boolean update()
	{
		PreparedStatement pstmt = null;

		if( !mflag ) {
			con = DBConnPool.getConnection();
		}

		// only for debug purpose
		SQLString sqlObj = new SQLString("SDAssActivity");
		sqlObj.setSQL(2, this);
		sqlObj.getSQL();

		try
		{
			pstmt = con.prepareStatement("UPDATE SDAssActivity SET  ActID = ? , AssID = ? , Phone = ? , Title = ? , ActFee = ? , ActRule = ? , ActContent = ? , ActGift = ? , AcPeople = ? , StartDate = ? , StartTime = ? , EndDate = ? , EndTime = ? , RegEndDate = ? , RegEndTime = ? , AppStatus = ? , Apper = ? , AppDate = ? , AppTime = ? , AppRemark = ? , R1 = ? , R2 = ? , R3 = ? , R4 = ? , R5 = ? , R6 = ? , R7 = ? , R8 = ? , R9 = ? , R10 = ? , R11 = ? , R12 = ? , R13 = ? , R14 = ? , R15 = ? WHERE  ActID = ?");
			if(this.getActID() == null || this.getActID().equals("null")) {
				pstmt.setNull(1, 12);
			} else {
				pstmt.setString(1, this.getActID());
			}
			if(this.getAssID() == null || this.getAssID().equals("null")) {
				pstmt.setNull(2, 12);
			} else {
				pstmt.setString(2, this.getAssID());
			}
			if(this.getPhone() == null || this.getPhone().equals("null")) {
				pstmt.setNull(3, 12);
			} else {
				pstmt.setString(3, this.getPhone());
			}
			if(this.getTitle() == null || this.getTitle().equals("null")) {
				pstmt.setNull(4, 12);
			} else {
				pstmt.setString(4, this.getTitle());
			}
			if(this.getActFee() == null || this.getActFee().equals("null")) {
				pstmt.setNull(5, 12);
			} else {
				pstmt.setString(5, this.getActFee());
			}
			if(this.getActRule() == null || this.getActRule().equals("null")) {
				pstmt.setNull(6, 12);
			} else {
				pstmt.setString(6, this.getActRule());
			}
			if(this.getActContent() == null || this.getActContent().equals("null")) {
				pstmt.setNull(7, 12);
			} else {
				pstmt.setString(7, this.getActContent());
			}
			if(this.getActGift() == null || this.getActGift().equals("null")) {
				pstmt.setNull(8, 12);
			} else {
				pstmt.setString(8, this.getActGift());
			}
			if(this.getAcPeople() == null || this.getAcPeople().equals("null")) {
				pstmt.setNull(9, 12);
			} else {
				pstmt.setString(9, this.getAcPeople());
			}
			if(this.getStartDate() == null || this.getStartDate().equals("null")) {
				pstmt.setNull(10, 12);
			} else {
				pstmt.setString(10, this.getStartDate());
			}
			if(this.getStartTime() == null || this.getStartTime().equals("null")) {
				pstmt.setNull(11, 12);
			} else {
				pstmt.setString(11, this.getStartTime());
			}
			if(this.getEndDate() == null || this.getEndDate().equals("null")) {
				pstmt.setNull(12, 12);
			} else {
				pstmt.setString(12, this.getEndDate());
			}
			if(this.getEndTime() == null || this.getEndTime().equals("null")) {
				pstmt.setNull(13, 12);
			} else {
				pstmt.setString(13, this.getEndTime());
			}
			if(this.getRegEndDate() == null || this.getRegEndDate().equals("null")) {
				pstmt.setNull(14, 12);
			} else {
				pstmt.setString(14, this.getRegEndDate());
			}
			if(this.getRegEndTime() == null || this.getRegEndTime().equals("null")) {
				pstmt.setNull(15, 12);
			} else {
				pstmt.setString(15, this.getRegEndTime());
			}
			if(this.getAppStatus() == null || this.getAppStatus().equals("null")) {
				pstmt.setNull(16, 12);
			} else {
				pstmt.setString(16, this.getAppStatus());
			}
			if(this.getApper() == null || this.getApper().equals("null")) {
				pstmt.setNull(17, 12);
			} else {
				pstmt.setString(17, this.getApper());
			}
			if(this.getAppDate() == null || this.getAppDate().equals("null")) {
				pstmt.setNull(18, 12);
			} else {
				pstmt.setString(18, this.getAppDate());
			}
			if(this.getAppTime() == null || this.getAppTime().equals("null")) {
				pstmt.setNull(19, 12);
			} else {
				pstmt.setString(19, this.getAppTime());
			}
			if(this.getAppRemark() == null || this.getAppRemark().equals("null")) {
				pstmt.setNull(20, 12);
			} else {
				pstmt.setString(20, this.getAppRemark());
			}
			if(this.getR1() == null || this.getR1().equals("null")) {
				pstmt.setNull(21, 12);
			} else {
				pstmt.setString(21, this.getR1());
			}
			if(this.getR2() == null || this.getR2().equals("null")) {
				pstmt.setNull(22, 12);
			} else {
				pstmt.setString(22, this.getR2());
			}
			if(this.getR3() == null || this.getR3().equals("null")) {
				pstmt.setNull(23, 12);
			} else {
				pstmt.setString(23, this.getR3());
			}
			if(this.getR4() == null || this.getR4().equals("null")) {
				pstmt.setNull(24, 12);
			} else {
				pstmt.setString(24, this.getR4());
			}
			if(this.getR5() == null || this.getR5().equals("null")) {
				pstmt.setNull(25, 12);
			} else {
				pstmt.setString(25, this.getR5());
			}
			if(this.getR6() == null || this.getR6().equals("null")) {
				pstmt.setNull(26, 12);
			} else {
				pstmt.setString(26, this.getR6());
			}
			if(this.getR7() == null || this.getR7().equals("null")) {
				pstmt.setNull(27, 12);
			} else {
				pstmt.setString(27, this.getR7());
			}
			if(this.getR8() == null || this.getR8().equals("null")) {
				pstmt.setNull(28, 12);
			} else {
				pstmt.setString(28, this.getR8());
			}
			if(this.getR9() == null || this.getR9().equals("null")) {
				pstmt.setNull(29, 12);
			} else {
				pstmt.setString(29, this.getR9());
			}
			if(this.getR10() == null || this.getR10().equals("null")) {
				pstmt.setNull(30, 12);
			} else {
				pstmt.setString(30, this.getR10());
			}
			if(this.getR11() == null || this.getR11().equals("null")) {
				pstmt.setNull(31, 12);
			} else {
				pstmt.setString(31, this.getR11());
			}
			if(this.getR12() == null || this.getR12().equals("null")) {
				pstmt.setNull(32, 12);
			} else {
				pstmt.setString(32, this.getR12());
			}
			if(this.getR13() == null || this.getR13().equals("null")) {
				pstmt.setNull(33, 12);
			} else {
				pstmt.setString(33, this.getR13());
			}
			if(this.getR14() == null || this.getR14().equals("null")) {
				pstmt.setNull(34, 12);
			} else {
				pstmt.setString(34, this.getR14());
			}
			if(this.getR15() == null || this.getR15().equals("null")) {
				pstmt.setNull(35, 12);
			} else {
				pstmt.setString(35, this.getR15());
			}
			// set where condition
			if(this.getActID() == null || this.getActID().equals("null")) {
				pstmt.setNull(36, 12);
			} else {
				pstmt.setString(36, this.getActID());
			}
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception ex) {
			// @@错误处理
			this.mErrors.copyAllErrors(db.mErrors);
			CError tError = new CError();
			tError.moduleName = "SDAssActivityDB";
			tError.functionName = "update()";
			tError.errorMessage = ex.toString();
			this.mErrors .addOneError(tError);

			try {
				pstmt.close();
			} catch (Exception e){}

			if( !mflag ) {
				try {
					con.close();
				} catch (Exception e){}
			}

			return false;
		}

		if( !mflag ) {
			try {
				con.close();
			} catch (Exception e){}
		}

		return true;
	}

	public boolean insert()
	{
		PreparedStatement pstmt = null;

		if( !mflag ) {
			con = DBConnPool.getConnection();
		}

		// only for debug purpose
		SQLString sqlObj = new SQLString("SDAssActivity");
		sqlObj.setSQL(1, this);
		sqlObj.getSQL();

		try
		{
			pstmt = con.prepareStatement("INSERT INTO SDAssActivity(ActID ,AssID ,Phone ,Title ,ActFee ,ActRule ,ActContent ,ActGift ,AcPeople ,StartDate ,StartTime ,EndDate ,EndTime ,RegEndDate ,RegEndTime ,AppStatus ,Apper ,AppDate ,AppTime ,AppRemark ,R1 ,R2 ,R3 ,R4 ,R5 ,R6 ,R7 ,R8 ,R9 ,R10 ,R11 ,R12 ,R13 ,R14 ,R15) VALUES( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)");
			if(this.getActID() == null || this.getActID().equals("null")) {
				pstmt.setNull(1, 12);
			} else {
				pstmt.setString(1, this.getActID());
			}
			if(this.getAssID() == null || this.getAssID().equals("null")) {
				pstmt.setNull(2, 12);
			} else {
				pstmt.setString(2, this.getAssID());
			}
			if(this.getPhone() == null || this.getPhone().equals("null")) {
				pstmt.setNull(3, 12);
			} else {
				pstmt.setString(3, this.getPhone());
			}
			if(this.getTitle() == null || this.getTitle().equals("null")) {
				pstmt.setNull(4, 12);
			} else {
				pstmt.setString(4, this.getTitle());
			}
			if(this.getActFee() == null || this.getActFee().equals("null")) {
				pstmt.setNull(5, 12);
			} else {
				pstmt.setString(5, this.getActFee());
			}
			if(this.getActRule() == null || this.getActRule().equals("null")) {
				pstmt.setNull(6, 12);
			} else {
				pstmt.setString(6, this.getActRule());
			}
			if(this.getActContent() == null || this.getActContent().equals("null")) {
				pstmt.setNull(7, 12);
			} else {
				pstmt.setString(7, this.getActContent());
			}
			if(this.getActGift() == null || this.getActGift().equals("null")) {
				pstmt.setNull(8, 12);
			} else {
				pstmt.setString(8, this.getActGift());
			}
			if(this.getAcPeople() == null || this.getAcPeople().equals("null")) {
				pstmt.setNull(9, 12);
			} else {
				pstmt.setString(9, this.getAcPeople());
			}
			if(this.getStartDate() == null || this.getStartDate().equals("null")) {
				pstmt.setNull(10, 12);
			} else {
				pstmt.setString(10, this.getStartDate());
			}
			if(this.getStartTime() == null || this.getStartTime().equals("null")) {
				pstmt.setNull(11, 12);
			} else {
				pstmt.setString(11, this.getStartTime());
			}
			if(this.getEndDate() == null || this.getEndDate().equals("null")) {
				pstmt.setNull(12, 12);
			} else {
				pstmt.setString(12, this.getEndDate());
			}
			if(this.getEndTime() == null || this.getEndTime().equals("null")) {
				pstmt.setNull(13, 12);
			} else {
				pstmt.setString(13, this.getEndTime());
			}
			if(this.getRegEndDate() == null || this.getRegEndDate().equals("null")) {
				pstmt.setNull(14, 12);
			} else {
				pstmt.setString(14, this.getRegEndDate());
			}
			if(this.getRegEndTime() == null || this.getRegEndTime().equals("null")) {
				pstmt.setNull(15, 12);
			} else {
				pstmt.setString(15, this.getRegEndTime());
			}
			if(this.getAppStatus() == null || this.getAppStatus().equals("null")) {
				pstmt.setNull(16, 12);
			} else {
				pstmt.setString(16, this.getAppStatus());
			}
			if(this.getApper() == null || this.getApper().equals("null")) {
				pstmt.setNull(17, 12);
			} else {
				pstmt.setString(17, this.getApper());
			}
			if(this.getAppDate() == null || this.getAppDate().equals("null")) {
				pstmt.setNull(18, 12);
			} else {
				pstmt.setString(18, this.getAppDate());
			}
			if(this.getAppTime() == null || this.getAppTime().equals("null")) {
				pstmt.setNull(19, 12);
			} else {
				pstmt.setString(19, this.getAppTime());
			}
			if(this.getAppRemark() == null || this.getAppRemark().equals("null")) {
				pstmt.setNull(20, 12);
			} else {
				pstmt.setString(20, this.getAppRemark());
			}
			if(this.getR1() == null || this.getR1().equals("null")) {
				pstmt.setNull(21, 12);
			} else {
				pstmt.setString(21, this.getR1());
			}
			if(this.getR2() == null || this.getR2().equals("null")) {
				pstmt.setNull(22, 12);
			} else {
				pstmt.setString(22, this.getR2());
			}
			if(this.getR3() == null || this.getR3().equals("null")) {
				pstmt.setNull(23, 12);
			} else {
				pstmt.setString(23, this.getR3());
			}
			if(this.getR4() == null || this.getR4().equals("null")) {
				pstmt.setNull(24, 12);
			} else {
				pstmt.setString(24, this.getR4());
			}
			if(this.getR5() == null || this.getR5().equals("null")) {
				pstmt.setNull(25, 12);
			} else {
				pstmt.setString(25, this.getR5());
			}
			if(this.getR6() == null || this.getR6().equals("null")) {
				pstmt.setNull(26, 12);
			} else {
				pstmt.setString(26, this.getR6());
			}
			if(this.getR7() == null || this.getR7().equals("null")) {
				pstmt.setNull(27, 12);
			} else {
				pstmt.setString(27, this.getR7());
			}
			if(this.getR8() == null || this.getR8().equals("null")) {
				pstmt.setNull(28, 12);
			} else {
				pstmt.setString(28, this.getR8());
			}
			if(this.getR9() == null || this.getR9().equals("null")) {
				pstmt.setNull(29, 12);
			} else {
				pstmt.setString(29, this.getR9());
			}
			if(this.getR10() == null || this.getR10().equals("null")) {
				pstmt.setNull(30, 12);
			} else {
				pstmt.setString(30, this.getR10());
			}
			if(this.getR11() == null || this.getR11().equals("null")) {
				pstmt.setNull(31, 12);
			} else {
				pstmt.setString(31, this.getR11());
			}
			if(this.getR12() == null || this.getR12().equals("null")) {
				pstmt.setNull(32, 12);
			} else {
				pstmt.setString(32, this.getR12());
			}
			if(this.getR13() == null || this.getR13().equals("null")) {
				pstmt.setNull(33, 12);
			} else {
				pstmt.setString(33, this.getR13());
			}
			if(this.getR14() == null || this.getR14().equals("null")) {
				pstmt.setNull(34, 12);
			} else {
				pstmt.setString(34, this.getR14());
			}
			if(this.getR15() == null || this.getR15().equals("null")) {
				pstmt.setNull(35, 12);
			} else {
				pstmt.setString(35, this.getR15());
			}
			// execute sql
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception ex) {
			// @@错误处理
			this.mErrors.copyAllErrors(db.mErrors);
			CError tError = new CError();
			tError.moduleName = "SDAssActivityDB";
			tError.functionName = "insert()";
			tError.errorMessage = ex.toString();
			this.mErrors .addOneError(tError);

			try {
				pstmt.close();
			} catch (Exception e){}

			if( !mflag ) {
				try {
					con.close();
				} catch (Exception e){}
			}

			return false;
		}

		if( !mflag ) {
			try {
				con.close();
			} catch (Exception e){}
		}

		return true;
	}

	public boolean getInfo()
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		if( !mflag ) {
			con = DBConnPool.getConnection();
		}

		try
		{
			pstmt = con.prepareStatement("SELECT * FROM SDAssActivity WHERE  ActID = ?", 
				ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
			if(this.getActID() == null || this.getActID().equals("null")) {
				pstmt.setNull(1, 12);
			} else {
				pstmt.setString(1, this.getActID());
			}
			rs = pstmt.executeQuery();
			int i = 0;
			while (rs.next())
			{
				i++;
				if (!this.setSchema(rs,i))
				{
					// @@错误处理
					CError tError = new CError();
					tError.moduleName = "SDAssActivityDB";
					tError.functionName = "getInfo";
					tError.errorMessage = "取数失败!";
					this.mErrors .addOneError(tError);

					try{ rs.close(); } catch( Exception ex ) {}
					try{ pstmt.close(); } catch( Exception ex1 ) {}

					if (!mflag)
					{
						try
						{
							con.close();
						}
						catch(Exception et){}
					}
					return false;
				}
				break;
			}
			try{ rs.close(); } catch( Exception ex2 ) {}
			try{ pstmt.close(); } catch( Exception ex3 ) {}

			if( i == 0 )
			{
				if (!mflag)
				{
					try
					{
						con.close();
					}
					catch(Exception et){}
				}
				return false;
			}
		}
		catch(Exception e)
	    {
			// @@错误处理
			CError tError = new CError();
			tError.moduleName = "SDAssActivityDB";
			tError.functionName = "getInfo";
			tError.errorMessage = e.toString();
			this.mErrors .addOneError(tError);

			try{ rs.close(); } catch( Exception ex ) {}
			try{ pstmt.close(); } catch( Exception ex1 ) {}

			if (!mflag)
			{
				try
				{
					con.close();
				}
				catch(Exception et){}
			}
			return false;
	    }
	    // 断开数据库连接
		if (!mflag)
		{
			try
			{
				con.close();
			}
			catch(Exception e){}
		}

		return true;
	}

	public SDAssActivitySet query()
	{
		Statement stmt = null;
		ResultSet rs = null;
		SDAssActivitySet aSDAssActivitySet = new SDAssActivitySet();

	  if( !mflag ) {
		  con = DBConnPool.getConnection();
		}

		try
		{
			stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
			SQLString sqlObj = new SQLString("SDAssActivity");
			SDAssActivitySchema aSchema = this.getSchema();
			sqlObj.setSQL(5,aSchema);
			String sql = sqlObj.getSQL();

			rs = stmt.executeQuery(sql);
			int i = 0;
			while (rs.next())
			{
				i++;
				SDAssActivitySchema s1 = new SDAssActivitySchema();
				s1.setSchema(rs,i);
				aSDAssActivitySet.add(s1);
			}
			try{ rs.close(); } catch( Exception ex ) {}
			try{ stmt.close(); } catch( Exception ex1 ) {}
		}
		catch(Exception e)
	    {
			// @@错误处理
			CError tError = new CError();
			tError.moduleName = "SDAssActivityDB";
			tError.functionName = "query";
			tError.errorMessage = e.toString();
			this.mErrors .addOneError(tError);

			try{ rs.close(); } catch( Exception ex2 ) {}
			try{ stmt.close(); } catch( Exception ex3 ) {}

			if (!mflag)
			{
				try
				{
					con.close();
				}
				catch(Exception et){}
			}
	    }

		if (!mflag)
		{
			try
			{
				con.close();
			}
			catch(Exception e){}
		}

		return aSDAssActivitySet;
	}

	public SDAssActivitySet executeQuery(String sql)
	{
		Statement stmt = null;
		ResultSet rs = null;
		SDAssActivitySet aSDAssActivitySet = new SDAssActivitySet();

	  if( !mflag ) {
		  con = DBConnPool.getConnection();
		}

		try
		{
			stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);

			rs = stmt.executeQuery(StrTool.GBKToUnicode(sql));
			int i = 0;
			while (rs.next())
			{
				i++;
				SDAssActivitySchema s1 = new SDAssActivitySchema();
				if (!s1.setSchema(rs,i))
				{
					// @@错误处理
					CError tError = new CError();
					tError.moduleName = "SDAssActivityDB";
					tError.functionName = "executeQuery";
					tError.errorMessage = "sql语句有误，请查看表名及字段名信息!";
					this.mErrors .addOneError(tError);
				}
				aSDAssActivitySet.add(s1);
			}
			try{ rs.close(); } catch( Exception ex ) {}
			try{ stmt.close(); } catch( Exception ex1 ) {}
		}
		catch(Exception e)
	    {
			// @@错误处理
			CError tError = new CError();
			tError.moduleName = "SDAssActivityDB";
			tError.functionName = "executeQuery";
			tError.errorMessage = e.toString();
			this.mErrors .addOneError(tError);

			try{ rs.close(); } catch( Exception ex2 ) {}
			try{ stmt.close(); } catch( Exception ex3 ) {}

			if (!mflag)
			{
				try
				{
					con.close();
				}
				catch(Exception et){}
			}
	    }

		if (!mflag)
		{
			try
			{
				con.close();
			}
			catch(Exception e){}
		}

		return aSDAssActivitySet;
	}

	public SDAssActivitySet query(int nStart, int nCount)
	{
		Statement stmt = null;
		ResultSet rs = null;
		SDAssActivitySet aSDAssActivitySet = new SDAssActivitySet();

	  if( !mflag ) {
		  con = DBConnPool.getConnection();
		}

		try
		{
			stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
			SQLString sqlObj = new SQLString("SDAssActivity");
			SDAssActivitySchema aSchema = this.getSchema();
			sqlObj.setSQL(5,aSchema);
			String sql = sqlObj.getSQL();

			rs = stmt.executeQuery(sql);
			int i = 0;
			while (rs.next())
			{
				i++;

				if( i < nStart ) {
					continue;
				}

				if( i >= nStart + nCount ) {
					break;
				}

				SDAssActivitySchema s1 = new SDAssActivitySchema();
				s1.setSchema(rs,i);
				aSDAssActivitySet.add(s1);
			}
			try{ rs.close(); } catch( Exception ex ) {}
			try{ stmt.close(); } catch( Exception ex1 ) {}
		}
		catch(Exception e)
	    {
			// @@错误处理
			CError tError = new CError();
			tError.moduleName = "SDAssActivityDB";
			tError.functionName = "query";
			tError.errorMessage = e.toString();
			this.mErrors .addOneError(tError);

			try{ rs.close(); } catch( Exception ex2 ) {}
			try{ stmt.close(); } catch( Exception ex3 ) {}

			if (!mflag)
			{
				try
				{
					con.close();
				}
				catch(Exception et){}
			}
	    }

		if (!mflag)
		{
			try
			{
				con.close();
			}
			catch(Exception e){}
		}

		return aSDAssActivitySet;
	}

	public SDAssActivitySet executeQuery(String sql, int nStart, int nCount)
	{
		Statement stmt = null;
		ResultSet rs = null;
		SDAssActivitySet aSDAssActivitySet = new SDAssActivitySet();

	  if( !mflag ) {
		  con = DBConnPool.getConnection();
		}

		try
		{
			stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);

			rs = stmt.executeQuery(StrTool.GBKToUnicode(sql));
			int i = 0;
			while (rs.next())
			{
				i++;

				if( i < nStart ) {
					continue;
				}

				if( i >= nStart + nCount ) {
					break;
				}

				SDAssActivitySchema s1 = new SDAssActivitySchema();
				if (!s1.setSchema(rs,i))
				{
					// @@错误处理
					CError tError = new CError();
					tError.moduleName = "SDAssActivityDB";
					tError.functionName = "executeQuery";
					tError.errorMessage = "sql语句有误，请查看表名及字段名信息!";
					this.mErrors .addOneError(tError);
				}
				aSDAssActivitySet.add(s1);
			}
			try{ rs.close(); } catch( Exception ex ) {}
			try{ stmt.close(); } catch( Exception ex1 ) {}
		}
		catch(Exception e)
	    {
			// @@错误处理
			CError tError = new CError();
			tError.moduleName = "SDAssActivityDB";
			tError.functionName = "executeQuery";
			tError.errorMessage = e.toString();
			this.mErrors .addOneError(tError);

			try{ rs.close(); } catch( Exception ex2 ) {}
			try{ stmt.close(); } catch( Exception ex3 ) {}

			if (!mflag)
			{
				try
				{
					con.close();
				}
				catch(Exception et){}
			}
	    }

		if (!mflag)
		{
			try
			{
				con.close();
			}
			catch(Exception e){}
		}

		return aSDAssActivitySet;
	}

	public boolean update(String strWherePart)
	{
		Statement stmt = null;

	  if( !mflag ) {
		  con = DBConnPool.getConnection();
		}

		try
		{
			stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
			SQLString sqlObj = new SQLString("SDAssActivity");
			SDAssActivitySchema aSchema = this.getSchema();
			sqlObj.setSQL(2,aSchema);
			String sql = "update SDAssActivity " + sqlObj.getUpdPart() + " where " + strWherePart;

			int operCount = stmt.executeUpdate(sql);
			if( operCount == 0 )
			{
				// @@错误处理
				CError tError = new CError();
				tError.moduleName = "SDAssActivityDB";
				tError.functionName = "update";
				tError.errorMessage = "更新数据失败!";
				this.mErrors .addOneError(tError);

				if (!mflag)
				{
					try
					{
						con.close();
					}
					catch(Exception et){}
				}
				return false;
			}
		}
		catch(Exception e)
	    {
			// @@错误处理
			CError tError = new CError();
			tError.moduleName = "SDAssActivityDB";
			tError.functionName = "update";
			tError.errorMessage = e.toString();
			this.mErrors .addOneError(tError);

			try{ stmt.close(); } catch( Exception ex1 ) {}

			if (!mflag)
			{
				try
				{
					con.close();
				}
				catch(Exception et){}
			}
			return false;
	    }
	    // 断开数据库连接
		if (!mflag)
		{
			try
			{
				con.close();
			}
			catch(Exception e){}
		}

		return true;
	}

/**
 * 准备数据查询条件
 * @param strSQL String
 * @return boolean
 */
public boolean prepareData(String strSQL)
{
    if (mResultSet != null)
    {
        // @@错误处理
        CError tError = new CError();
        tError.moduleName = "SDAssActivityDB";
        tError.functionName = "prepareData";
        tError.errorMessage = "数据集非空，程序在准备数据集之后，没有关闭！";
        this.mErrors.addOneError(tError);
        return false;
    }

    if (!mflag)
    {
        con = DBConnPool.getConnection();
    }
    try
    {
        mStatement = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        mResultSet = mStatement.executeQuery(StrTool.GBKToUnicode(strSQL));
    }
    catch (Exception e)
    {
        // @@错误处理
        CError tError = new CError();
        tError.moduleName = "SDAssActivityDB";
        tError.functionName = "prepareData";
        tError.errorMessage = e.toString();
        this.mErrors.addOneError(tError);
        try
        {
            mResultSet.close();
        }
        catch (Exception ex2)
        {}
        try
        {
            mStatement.close();
        }
        catch (Exception ex3)
        {}
        if (!mflag)
        {
            try
            {
                con.close();
            }
            catch (Exception et)
            {}
        }
        return false;
    }

    if (!mflag)
    {
        try
        {
            con.close();
        }
        catch (Exception e)
        {}
    }
    return true;
}

/**
 * 获取数据集
 * @return boolean
 */
public boolean hasMoreData()
{
    boolean flag = true;
    if (null == mResultSet)
    {
        CError tError = new CError();
        tError.moduleName = "SDAssActivityDB";
        tError.functionName = "hasMoreData";
        tError.errorMessage = "数据集为空，请先准备数据集！";
        this.mErrors.addOneError(tError);
        return false;
    }
    try
    {
        flag = mResultSet.next();
    }
    catch (Exception ex)
    {
        CError tError = new CError();
        tError.moduleName = "SDAssActivityDB";
        tError.functionName = "hasMoreData";
        tError.errorMessage = ex.toString();
        this.mErrors.addOneError(tError);
        try
        {
            mResultSet.close();
            mResultSet = null;
        }
        catch (Exception ex2)
        {}
        try
        {
            mStatement.close();
            mStatement = null;
        }
        catch (Exception ex3)
        {}
        if (!mflag)
        {
            try
            {
                con.close();
            }
            catch (Exception et)
            {}
        }
        return false;
    }
    return flag;
}
/**
 * 获取定量数据
 * @return SDAssActivitySet
 */
public SDAssActivitySet getData()
{
    int tCount = 0;
    SDAssActivitySet tSDAssActivitySet = new SDAssActivitySet();
    SDAssActivitySchema tSDAssActivitySchema = null;
    if (null == mResultSet)
    {
        CError tError = new CError();
        tError.moduleName = "SDAssActivityDB";
        tError.functionName = "getData";
        tError.errorMessage = "数据集为空，请先准备数据集！";
        this.mErrors.addOneError(tError);
        return null;
    }
    try
    {
        tCount = 1;
        tSDAssActivitySchema = new SDAssActivitySchema();
        tSDAssActivitySchema.setSchema(mResultSet, 1);
        tSDAssActivitySet.add(tSDAssActivitySchema);
        //注意mResultSet.next()的作用
        while (tCount++ < SysConst.FETCHCOUNT)
        {
            if (mResultSet.next())
            {
                tSDAssActivitySchema = new SDAssActivitySchema();
                tSDAssActivitySchema.setSchema(mResultSet, 1);
                tSDAssActivitySet.add(tSDAssActivitySchema);
            }
        }
    }
    catch (Exception ex)
    {
        CError tError = new CError();
        tError.moduleName = "SDAssActivityDB";
        tError.functionName = "getData";
        tError.errorMessage = ex.toString();
        this.mErrors.addOneError(tError);
        try
        {
            mResultSet.close();
            mResultSet = null;
        }
        catch (Exception ex2)
        {}
        try
        {
            mStatement.close();
            mStatement = null;
        }
        catch (Exception ex3)
        {}
        if (!mflag)
        {
            try
            {
                con.close();
            }
            catch (Exception et)
            {}
        }
        return null;
    }
    return tSDAssActivitySet;
}
/**
 * 关闭数据集
 * @return boolean
 */
public boolean closeData()
{
    boolean flag = true;
    try
    {
        if (null == mResultSet)
        {
            CError tError = new CError();
            tError.moduleName = "SDAssActivityDB";
            tError.functionName = "closeData";
            tError.errorMessage = "数据集已经关闭了！";
            this.mErrors.addOneError(tError);
            flag = false;
        }
        else
        {
            mResultSet.close();
            mResultSet = null;
        }
    }
    catch (Exception ex2)
    {
        CError tError = new CError();
        tError.moduleName = "SDAssActivityDB";
        tError.functionName = "closeData";
        tError.errorMessage = ex2.toString();
        this.mErrors.addOneError(tError);
        flag = false;
    }
    try
    {
        if (null == mStatement)
        {
            CError tError = new CError();
            tError.moduleName = "SDAssActivityDB";
            tError.functionName = "closeData";
            tError.errorMessage = "语句已经关闭了！";
            this.mErrors.addOneError(tError);
            flag = false;
        }
        else
        {
            mStatement.close();
            mStatement = null;
        }
    }
    catch (Exception ex3)
    {
        CError tError = new CError();
        tError.moduleName = "SDAssActivityDB";
        tError.functionName = "closeData";
        tError.errorMessage = ex3.toString();
        this.mErrors.addOneError(tError);
        flag = false;
    }
    return flag;
}
}
