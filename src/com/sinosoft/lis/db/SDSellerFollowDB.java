/**
 * Copyright (c) 2002 sinosoft  Co. Ltd.
 * All right reserved.
 */

package com.sinosoft.lis.db;

import java.sql.*;
import com.sinosoft.lis.schema.SDSellerFollowSchema;
import com.sinosoft.lis.vschema.SDSellerFollowSet;
import com.sinosoft.lis.pubfun.PubFun;
import com.sinosoft.utility.*;

/*
 * <p>ClassName: SDSellerFollowDB </p>
 * <p>Description: DB层数据库操作类文件 </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: sinosoft </p>
 * @Database: PhysicalDataModel_1
 */
public class SDSellerFollowDB extends SDSellerFollowSchema
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
	public SDSellerFollowDB( Connection tConnection )
	{
		con = tConnection;
		db = new DBOper( con, "SDSellerFollow" );
		mflag = true;
	}

	public SDSellerFollowDB()
	{
		con = null;
		db = new DBOper( "SDSellerFollow" );
		mflag = false;
	}

	// @Method
	public boolean deleteSQL()
	{
		SDSellerFollowSchema tSchema = this.getSchema();
		if (db.deleteSQL(tSchema))
		{
		     return true;
		}
		else
		{
			// @@错误处理
			this.mErrors.copyAllErrors(db.mErrors);
			CError tError = new CError();
			tError.moduleName = "SDSellerFollowDB";
			tError.functionName = "deleteSQL";
			tError.errorMessage = "操作失败!";
			this.mErrors .addOneError(tError);
			return false;
		}
	}

	public int getCount()
	{
		SDSellerFollowSchema tSchema = this.getSchema();

		int tCount = db.getCount(tSchema);
		if (tCount < 0)
		{
			// @@错误处理
			this.mErrors.copyAllErrors(db.mErrors);
			CError tError = new CError();
			tError.moduleName = "SDSellerFollowDB";
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
			pstmt = con.prepareStatement("DELETE FROM SDSellerFollow WHERE  SerialNo = ?");
			if(this.getSerialNo() == null || this.getSerialNo().equals("null")) {
				pstmt.setNull(1, 12);
			} else {
				pstmt.setString(1, this.getSerialNo());
			}
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception ex) {
			// @@错误处理
			this.mErrors.copyAllErrors(db.mErrors);
			CError tError = new CError();
			tError.moduleName = "SDSellerFollowDB";
			tError.functionName = "delete()";
			tError.errorMessage = ex.toString();
			this.mErrors .addOneError(tError);

		// only for debug purpose
		SQLString sqlObj = new SQLString("SDSellerFollow");
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
		SQLString sqlObj = new SQLString("SDSellerFollow");
		sqlObj.setSQL(2, this);
		sqlObj.getSQL();

		try
		{
			pstmt = con.prepareStatement("UPDATE SDSellerFollow SET  SerialNo = ? , UserID = ? , SellerID = ? , FollowDate = ? , FollowTime = ? , R1 = ? , R2 = ? , R3 = ? , R4 = ? , R5 = ? , R6 = ? , R7 = ? , R8 = ? , R9 = ? , R10 = ? , R11 = ? , R12 = ? , R13 = ? , R14 = ? , R15 = ? WHERE  SerialNo = ?");
			if(this.getSerialNo() == null || this.getSerialNo().equals("null")) {
				pstmt.setNull(1, 12);
			} else {
				pstmt.setString(1, this.getSerialNo());
			}
			if(this.getUserID() == null || this.getUserID().equals("null")) {
				pstmt.setNull(2, 12);
			} else {
				pstmt.setString(2, this.getUserID());
			}
			if(this.getSellerID() == null || this.getSellerID().equals("null")) {
				pstmt.setNull(3, 12);
			} else {
				pstmt.setString(3, this.getSellerID());
			}
			if(this.getFollowDate() == null || this.getFollowDate().equals("null")) {
				pstmt.setNull(4, 12);
			} else {
				pstmt.setString(4, this.getFollowDate());
			}
			if(this.getFollowTime() == null || this.getFollowTime().equals("null")) {
				pstmt.setNull(5, 12);
			} else {
				pstmt.setString(5, this.getFollowTime());
			}
			if(this.getR1() == null || this.getR1().equals("null")) {
				pstmt.setNull(6, 12);
			} else {
				pstmt.setString(6, this.getR1());
			}
			if(this.getR2() == null || this.getR2().equals("null")) {
				pstmt.setNull(7, 12);
			} else {
				pstmt.setString(7, this.getR2());
			}
			if(this.getR3() == null || this.getR3().equals("null")) {
				pstmt.setNull(8, 12);
			} else {
				pstmt.setString(8, this.getR3());
			}
			if(this.getR4() == null || this.getR4().equals("null")) {
				pstmt.setNull(9, 12);
			} else {
				pstmt.setString(9, this.getR4());
			}
			if(this.getR5() == null || this.getR5().equals("null")) {
				pstmt.setNull(10, 12);
			} else {
				pstmt.setString(10, this.getR5());
			}
			if(this.getR6() == null || this.getR6().equals("null")) {
				pstmt.setNull(11, 12);
			} else {
				pstmt.setString(11, this.getR6());
			}
			if(this.getR7() == null || this.getR7().equals("null")) {
				pstmt.setNull(12, 12);
			} else {
				pstmt.setString(12, this.getR7());
			}
			if(this.getR8() == null || this.getR8().equals("null")) {
				pstmt.setNull(13, 12);
			} else {
				pstmt.setString(13, this.getR8());
			}
			if(this.getR9() == null || this.getR9().equals("null")) {
				pstmt.setNull(14, 12);
			} else {
				pstmt.setString(14, this.getR9());
			}
			if(this.getR10() == null || this.getR10().equals("null")) {
				pstmt.setNull(15, 12);
			} else {
				pstmt.setString(15, this.getR10());
			}
			if(this.getR11() == null || this.getR11().equals("null")) {
				pstmt.setNull(16, 12);
			} else {
				pstmt.setString(16, this.getR11());
			}
			if(this.getR12() == null || this.getR12().equals("null")) {
				pstmt.setNull(17, 12);
			} else {
				pstmt.setString(17, this.getR12());
			}
			if(this.getR13() == null || this.getR13().equals("null")) {
				pstmt.setNull(18, 12);
			} else {
				pstmt.setString(18, this.getR13());
			}
			if(this.getR14() == null || this.getR14().equals("null")) {
				pstmt.setNull(19, 12);
			} else {
				pstmt.setString(19, this.getR14());
			}
			if(this.getR15() == null || this.getR15().equals("null")) {
				pstmt.setNull(20, 12);
			} else {
				pstmt.setString(20, this.getR15());
			}
			// set where condition
			if(this.getSerialNo() == null || this.getSerialNo().equals("null")) {
				pstmt.setNull(21, 12);
			} else {
				pstmt.setString(21, this.getSerialNo());
			}
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception ex) {
			// @@错误处理
			this.mErrors.copyAllErrors(db.mErrors);
			CError tError = new CError();
			tError.moduleName = "SDSellerFollowDB";
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
		SQLString sqlObj = new SQLString("SDSellerFollow");
		sqlObj.setSQL(1, this);
		sqlObj.getSQL();

		try
		{
			pstmt = con.prepareStatement("INSERT INTO SDSellerFollow(SerialNo ,UserID ,SellerID ,FollowDate ,FollowTime ,R1 ,R2 ,R3 ,R4 ,R5 ,R6 ,R7 ,R8 ,R9 ,R10 ,R11 ,R12 ,R13 ,R14 ,R15) VALUES( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)");
			if(this.getSerialNo() == null || this.getSerialNo().equals("null")) {
				pstmt.setNull(1, 12);
			} else {
				pstmt.setString(1, this.getSerialNo());
			}
			if(this.getUserID() == null || this.getUserID().equals("null")) {
				pstmt.setNull(2, 12);
			} else {
				pstmt.setString(2, this.getUserID());
			}
			if(this.getSellerID() == null || this.getSellerID().equals("null")) {
				pstmt.setNull(3, 12);
			} else {
				pstmt.setString(3, this.getSellerID());
			}
			if(this.getFollowDate() == null || this.getFollowDate().equals("null")) {
				pstmt.setNull(4, 12);
			} else {
				pstmt.setString(4, this.getFollowDate());
			}
			if(this.getFollowTime() == null || this.getFollowTime().equals("null")) {
				pstmt.setNull(5, 12);
			} else {
				pstmt.setString(5, this.getFollowTime());
			}
			if(this.getR1() == null || this.getR1().equals("null")) {
				pstmt.setNull(6, 12);
			} else {
				pstmt.setString(6, this.getR1());
			}
			if(this.getR2() == null || this.getR2().equals("null")) {
				pstmt.setNull(7, 12);
			} else {
				pstmt.setString(7, this.getR2());
			}
			if(this.getR3() == null || this.getR3().equals("null")) {
				pstmt.setNull(8, 12);
			} else {
				pstmt.setString(8, this.getR3());
			}
			if(this.getR4() == null || this.getR4().equals("null")) {
				pstmt.setNull(9, 12);
			} else {
				pstmt.setString(9, this.getR4());
			}
			if(this.getR5() == null || this.getR5().equals("null")) {
				pstmt.setNull(10, 12);
			} else {
				pstmt.setString(10, this.getR5());
			}
			if(this.getR6() == null || this.getR6().equals("null")) {
				pstmt.setNull(11, 12);
			} else {
				pstmt.setString(11, this.getR6());
			}
			if(this.getR7() == null || this.getR7().equals("null")) {
				pstmt.setNull(12, 12);
			} else {
				pstmt.setString(12, this.getR7());
			}
			if(this.getR8() == null || this.getR8().equals("null")) {
				pstmt.setNull(13, 12);
			} else {
				pstmt.setString(13, this.getR8());
			}
			if(this.getR9() == null || this.getR9().equals("null")) {
				pstmt.setNull(14, 12);
			} else {
				pstmt.setString(14, this.getR9());
			}
			if(this.getR10() == null || this.getR10().equals("null")) {
				pstmt.setNull(15, 12);
			} else {
				pstmt.setString(15, this.getR10());
			}
			if(this.getR11() == null || this.getR11().equals("null")) {
				pstmt.setNull(16, 12);
			} else {
				pstmt.setString(16, this.getR11());
			}
			if(this.getR12() == null || this.getR12().equals("null")) {
				pstmt.setNull(17, 12);
			} else {
				pstmt.setString(17, this.getR12());
			}
			if(this.getR13() == null || this.getR13().equals("null")) {
				pstmt.setNull(18, 12);
			} else {
				pstmt.setString(18, this.getR13());
			}
			if(this.getR14() == null || this.getR14().equals("null")) {
				pstmt.setNull(19, 12);
			} else {
				pstmt.setString(19, this.getR14());
			}
			if(this.getR15() == null || this.getR15().equals("null")) {
				pstmt.setNull(20, 12);
			} else {
				pstmt.setString(20, this.getR15());
			}
			// execute sql
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception ex) {
			// @@错误处理
			this.mErrors.copyAllErrors(db.mErrors);
			CError tError = new CError();
			tError.moduleName = "SDSellerFollowDB";
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
			pstmt = con.prepareStatement("SELECT * FROM SDSellerFollow WHERE  SerialNo = ?", 
				ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
			if(this.getSerialNo() == null || this.getSerialNo().equals("null")) {
				pstmt.setNull(1, 12);
			} else {
				pstmt.setString(1, this.getSerialNo());
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
					tError.moduleName = "SDSellerFollowDB";
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
			tError.moduleName = "SDSellerFollowDB";
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

	public SDSellerFollowSet query()
	{
		Statement stmt = null;
		ResultSet rs = null;
		SDSellerFollowSet aSDSellerFollowSet = new SDSellerFollowSet();

	  if( !mflag ) {
		  con = DBConnPool.getConnection();
		}

		try
		{
			stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
			SQLString sqlObj = new SQLString("SDSellerFollow");
			SDSellerFollowSchema aSchema = this.getSchema();
			sqlObj.setSQL(5,aSchema);
			String sql = sqlObj.getSQL();

			rs = stmt.executeQuery(sql);
			int i = 0;
			while (rs.next())
			{
				i++;
				SDSellerFollowSchema s1 = new SDSellerFollowSchema();
				s1.setSchema(rs,i);
				aSDSellerFollowSet.add(s1);
			}
			try{ rs.close(); } catch( Exception ex ) {}
			try{ stmt.close(); } catch( Exception ex1 ) {}
		}
		catch(Exception e)
	    {
			// @@错误处理
			CError tError = new CError();
			tError.moduleName = "SDSellerFollowDB";
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

		return aSDSellerFollowSet;
	}

	public SDSellerFollowSet executeQuery(String sql)
	{
		Statement stmt = null;
		ResultSet rs = null;
		SDSellerFollowSet aSDSellerFollowSet = new SDSellerFollowSet();

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
				SDSellerFollowSchema s1 = new SDSellerFollowSchema();
				if (!s1.setSchema(rs,i))
				{
					// @@错误处理
					CError tError = new CError();
					tError.moduleName = "SDSellerFollowDB";
					tError.functionName = "executeQuery";
					tError.errorMessage = "sql语句有误，请查看表名及字段名信息!";
					this.mErrors .addOneError(tError);
				}
				aSDSellerFollowSet.add(s1);
			}
			try{ rs.close(); } catch( Exception ex ) {}
			try{ stmt.close(); } catch( Exception ex1 ) {}
		}
		catch(Exception e)
	    {
			// @@错误处理
			CError tError = new CError();
			tError.moduleName = "SDSellerFollowDB";
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

		return aSDSellerFollowSet;
	}

	public SDSellerFollowSet query(int nStart, int nCount)
	{
		Statement stmt = null;
		ResultSet rs = null;
		SDSellerFollowSet aSDSellerFollowSet = new SDSellerFollowSet();

	  if( !mflag ) {
		  con = DBConnPool.getConnection();
		}

		try
		{
			stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
			SQLString sqlObj = new SQLString("SDSellerFollow");
			SDSellerFollowSchema aSchema = this.getSchema();
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

				SDSellerFollowSchema s1 = new SDSellerFollowSchema();
				s1.setSchema(rs,i);
				aSDSellerFollowSet.add(s1);
			}
			try{ rs.close(); } catch( Exception ex ) {}
			try{ stmt.close(); } catch( Exception ex1 ) {}
		}
		catch(Exception e)
	    {
			// @@错误处理
			CError tError = new CError();
			tError.moduleName = "SDSellerFollowDB";
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

		return aSDSellerFollowSet;
	}

	public SDSellerFollowSet executeQuery(String sql, int nStart, int nCount)
	{
		Statement stmt = null;
		ResultSet rs = null;
		SDSellerFollowSet aSDSellerFollowSet = new SDSellerFollowSet();

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

				SDSellerFollowSchema s1 = new SDSellerFollowSchema();
				if (!s1.setSchema(rs,i))
				{
					// @@错误处理
					CError tError = new CError();
					tError.moduleName = "SDSellerFollowDB";
					tError.functionName = "executeQuery";
					tError.errorMessage = "sql语句有误，请查看表名及字段名信息!";
					this.mErrors .addOneError(tError);
				}
				aSDSellerFollowSet.add(s1);
			}
			try{ rs.close(); } catch( Exception ex ) {}
			try{ stmt.close(); } catch( Exception ex1 ) {}
		}
		catch(Exception e)
	    {
			// @@错误处理
			CError tError = new CError();
			tError.moduleName = "SDSellerFollowDB";
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

		return aSDSellerFollowSet;
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
			SQLString sqlObj = new SQLString("SDSellerFollow");
			SDSellerFollowSchema aSchema = this.getSchema();
			sqlObj.setSQL(2,aSchema);
			String sql = "update SDSellerFollow " + sqlObj.getUpdPart() + " where " + strWherePart;

			int operCount = stmt.executeUpdate(sql);
			if( operCount == 0 )
			{
				// @@错误处理
				CError tError = new CError();
				tError.moduleName = "SDSellerFollowDB";
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
			tError.moduleName = "SDSellerFollowDB";
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
        tError.moduleName = "SDSellerFollowDB";
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
        tError.moduleName = "SDSellerFollowDB";
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
        tError.moduleName = "SDSellerFollowDB";
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
        tError.moduleName = "SDSellerFollowDB";
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
 * @return SDSellerFollowSet
 */
public SDSellerFollowSet getData()
{
    int tCount = 0;
    SDSellerFollowSet tSDSellerFollowSet = new SDSellerFollowSet();
    SDSellerFollowSchema tSDSellerFollowSchema = null;
    if (null == mResultSet)
    {
        CError tError = new CError();
        tError.moduleName = "SDSellerFollowDB";
        tError.functionName = "getData";
        tError.errorMessage = "数据集为空，请先准备数据集！";
        this.mErrors.addOneError(tError);
        return null;
    }
    try
    {
        tCount = 1;
        tSDSellerFollowSchema = new SDSellerFollowSchema();
        tSDSellerFollowSchema.setSchema(mResultSet, 1);
        tSDSellerFollowSet.add(tSDSellerFollowSchema);
        //注意mResultSet.next()的作用
        while (tCount++ < SysConst.FETCHCOUNT)
        {
            if (mResultSet.next())
            {
                tSDSellerFollowSchema = new SDSellerFollowSchema();
                tSDSellerFollowSchema.setSchema(mResultSet, 1);
                tSDSellerFollowSet.add(tSDSellerFollowSchema);
            }
        }
    }
    catch (Exception ex)
    {
        CError tError = new CError();
        tError.moduleName = "SDSellerFollowDB";
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
    return tSDSellerFollowSet;
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
            tError.moduleName = "SDSellerFollowDB";
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
        tError.moduleName = "SDSellerFollowDB";
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
            tError.moduleName = "SDSellerFollowDB";
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
        tError.moduleName = "SDSellerFollowDB";
        tError.functionName = "closeData";
        tError.errorMessage = ex3.toString();
        this.mErrors.addOneError(tError);
        flag = false;
    }
    return flag;
}
}
