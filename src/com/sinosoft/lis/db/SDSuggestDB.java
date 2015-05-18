/**
 * Copyright (c) 2002 sinosoft  Co. Ltd.
 * All right reserved.
 */

package com.sinosoft.lis.db;

import java.sql.*;
import com.sinosoft.lis.schema.SDSuggestSchema;
import com.sinosoft.lis.vschema.SDSuggestSet;
import com.sinosoft.lis.pubfun.PubFun;
import com.sinosoft.utility.*;

/*
 * <p>ClassName: SDSuggestDB </p>
 * <p>Description: DB�����ݿ�������ļ� </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: sinosoft </p>
 * @Database: PhysicalDataModel_1
 */
public class SDSuggestDB extends SDSuggestSchema
{
	// @Field
	private Connection con;
	private DBOper db;
	/**
	* flag = true: ����Connection
	* flag = false: ������Connection
	**/
	private boolean mflag = false;

	public CErrors mErrors = new CErrors();		// ������Ϣ

	/**
	 * Ϊ����������׼���������α����
	 */
	private ResultSet mResultSet = null;
	private Statement mStatement = null;
	// @Constructor
	public SDSuggestDB( Connection tConnection )
	{
		con = tConnection;
		db = new DBOper( con, "SDSuggest" );
		mflag = true;
	}

	public SDSuggestDB()
	{
		con = null;
		db = new DBOper( "SDSuggest" );
		mflag = false;
	}

	// @Method
	public boolean deleteSQL()
	{
		SDSuggestSchema tSchema = this.getSchema();
		if (db.deleteSQL(tSchema))
		{
		     return true;
		}
		else
		{
			// @@������
			this.mErrors.copyAllErrors(db.mErrors);
			CError tError = new CError();
			tError.moduleName = "SDSuggestDB";
			tError.functionName = "deleteSQL";
			tError.errorMessage = "����ʧ��!";
			this.mErrors .addOneError(tError);
			return false;
		}
	}

	public int getCount()
	{
		SDSuggestSchema tSchema = this.getSchema();

		int tCount = db.getCount(tSchema);
		if (tCount < 0)
		{
			// @@������
			this.mErrors.copyAllErrors(db.mErrors);
			CError tError = new CError();
			tError.moduleName = "SDSuggestDB";
			tError.functionName = "getCount";
			tError.errorMessage = "����ʧ��!";
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
			pstmt = con.prepareStatement("DELETE FROM SDSuggest WHERE  SerialNo = ?");
			if(this.getSerialNo() == null || this.getSerialNo().equals("null")) {
				pstmt.setNull(1, 12);
			} else {
				pstmt.setString(1, this.getSerialNo());
			}
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception ex) {
			// @@������
			this.mErrors.copyAllErrors(db.mErrors);
			CError tError = new CError();
			tError.moduleName = "SDSuggestDB";
			tError.functionName = "delete()";
			tError.errorMessage = ex.toString();
			this.mErrors .addOneError(tError);

		// only for debug purpose
		SQLString sqlObj = new SQLString("SDSuggest");
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
		SQLString sqlObj = new SQLString("SDSuggest");
		sqlObj.setSQL(2, this);
		sqlObj.getSQL();

		try
		{
			pstmt = con.prepareStatement("UPDATE SDSuggest SET  SerialNo = ? , UserType = ? , NickName = ? , Title = ? , Phone = ? , Com = ? , Content = ? , SuggestDate = ? , SuggestTime = ? , DealFlag = ? , Remark = ? , R1 = ? , R2 = ? , R3 = ? , R4 = ? , R5 = ? , R6 = ? , R7 = ? , R8 = ? , R9 = ? , R10 = ? , R11 = ? , R12 = ? , R13 = ? , R14 = ? , R15 = ? WHERE  SerialNo = ?");
			if(this.getSerialNo() == null || this.getSerialNo().equals("null")) {
				pstmt.setNull(1, 12);
			} else {
				pstmt.setString(1, this.getSerialNo());
			}
			if(this.getUserType() == null || this.getUserType().equals("null")) {
				pstmt.setNull(2, 12);
			} else {
				pstmt.setString(2, this.getUserType());
			}
			if(this.getNickName() == null || this.getNickName().equals("null")) {
				pstmt.setNull(3, 12);
			} else {
				pstmt.setString(3, this.getNickName());
			}
			if(this.getTitle() == null || this.getTitle().equals("null")) {
				pstmt.setNull(4, 12);
			} else {
				pstmt.setString(4, this.getTitle());
			}
			if(this.getPhone() == null || this.getPhone().equals("null")) {
				pstmt.setNull(5, 12);
			} else {
				pstmt.setString(5, this.getPhone());
			}
			if(this.getCom() == null || this.getCom().equals("null")) {
				pstmt.setNull(6, 12);
			} else {
				pstmt.setString(6, this.getCom());
			}
			if(this.getContent() == null || this.getContent().equals("null")) {
				pstmt.setNull(7, 12);
			} else {
				pstmt.setString(7, this.getContent());
			}
			if(this.getSuggestDate() == null || this.getSuggestDate().equals("null")) {
				pstmt.setNull(8, 12);
			} else {
				pstmt.setString(8, this.getSuggestDate());
			}
			if(this.getSuggestTime() == null || this.getSuggestTime().equals("null")) {
				pstmt.setNull(9, 12);
			} else {
				pstmt.setString(9, this.getSuggestTime());
			}
			if(this.getDealFlag() == null || this.getDealFlag().equals("null")) {
				pstmt.setNull(10, 12);
			} else {
				pstmt.setString(10, this.getDealFlag());
			}
			if(this.getRemark() == null || this.getRemark().equals("null")) {
				pstmt.setNull(11, 12);
			} else {
				pstmt.setString(11, this.getRemark());
			}
			if(this.getR1() == null || this.getR1().equals("null")) {
				pstmt.setNull(12, 12);
			} else {
				pstmt.setString(12, this.getR1());
			}
			if(this.getR2() == null || this.getR2().equals("null")) {
				pstmt.setNull(13, 12);
			} else {
				pstmt.setString(13, this.getR2());
			}
			if(this.getR3() == null || this.getR3().equals("null")) {
				pstmt.setNull(14, 12);
			} else {
				pstmt.setString(14, this.getR3());
			}
			if(this.getR4() == null || this.getR4().equals("null")) {
				pstmt.setNull(15, 12);
			} else {
				pstmt.setString(15, this.getR4());
			}
			if(this.getR5() == null || this.getR5().equals("null")) {
				pstmt.setNull(16, 12);
			} else {
				pstmt.setString(16, this.getR5());
			}
			if(this.getR6() == null || this.getR6().equals("null")) {
				pstmt.setNull(17, 12);
			} else {
				pstmt.setString(17, this.getR6());
			}
			if(this.getR7() == null || this.getR7().equals("null")) {
				pstmt.setNull(18, 12);
			} else {
				pstmt.setString(18, this.getR7());
			}
			if(this.getR8() == null || this.getR8().equals("null")) {
				pstmt.setNull(19, 12);
			} else {
				pstmt.setString(19, this.getR8());
			}
			if(this.getR9() == null || this.getR9().equals("null")) {
				pstmt.setNull(20, 12);
			} else {
				pstmt.setString(20, this.getR9());
			}
			if(this.getR10() == null || this.getR10().equals("null")) {
				pstmt.setNull(21, 12);
			} else {
				pstmt.setString(21, this.getR10());
			}
			if(this.getR11() == null || this.getR11().equals("null")) {
				pstmt.setNull(22, 12);
			} else {
				pstmt.setString(22, this.getR11());
			}
			if(this.getR12() == null || this.getR12().equals("null")) {
				pstmt.setNull(23, 12);
			} else {
				pstmt.setString(23, this.getR12());
			}
			if(this.getR13() == null || this.getR13().equals("null")) {
				pstmt.setNull(24, 12);
			} else {
				pstmt.setString(24, this.getR13());
			}
			if(this.getR14() == null || this.getR14().equals("null")) {
				pstmt.setNull(25, 12);
			} else {
				pstmt.setString(25, this.getR14());
			}
			if(this.getR15() == null || this.getR15().equals("null")) {
				pstmt.setNull(26, 12);
			} else {
				pstmt.setString(26, this.getR15());
			}
			// set where condition
			if(this.getSerialNo() == null || this.getSerialNo().equals("null")) {
				pstmt.setNull(27, 12);
			} else {
				pstmt.setString(27, this.getSerialNo());
			}
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception ex) {
			// @@������
			this.mErrors.copyAllErrors(db.mErrors);
			CError tError = new CError();
			tError.moduleName = "SDSuggestDB";
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
		SQLString sqlObj = new SQLString("SDSuggest");
		sqlObj.setSQL(1, this);
		sqlObj.getSQL();

		try
		{
			pstmt = con.prepareStatement("INSERT INTO SDSuggest(SerialNo ,UserType ,NickName ,Title ,Phone ,Com ,Content ,SuggestDate ,SuggestTime ,DealFlag ,Remark ,R1 ,R2 ,R3 ,R4 ,R5 ,R6 ,R7 ,R8 ,R9 ,R10 ,R11 ,R12 ,R13 ,R14 ,R15) VALUES( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)");
			if(this.getSerialNo() == null || this.getSerialNo().equals("null")) {
				pstmt.setNull(1, 12);
			} else {
				pstmt.setString(1, this.getSerialNo());
			}
			if(this.getUserType() == null || this.getUserType().equals("null")) {
				pstmt.setNull(2, 12);
			} else {
				pstmt.setString(2, this.getUserType());
			}
			if(this.getNickName() == null || this.getNickName().equals("null")) {
				pstmt.setNull(3, 12);
			} else {
				pstmt.setString(3, this.getNickName());
			}
			if(this.getTitle() == null || this.getTitle().equals("null")) {
				pstmt.setNull(4, 12);
			} else {
				pstmt.setString(4, this.getTitle());
			}
			if(this.getPhone() == null || this.getPhone().equals("null")) {
				pstmt.setNull(5, 12);
			} else {
				pstmt.setString(5, this.getPhone());
			}
			if(this.getCom() == null || this.getCom().equals("null")) {
				pstmt.setNull(6, 12);
			} else {
				pstmt.setString(6, this.getCom());
			}
			if(this.getContent() == null || this.getContent().equals("null")) {
				pstmt.setNull(7, 12);
			} else {
				pstmt.setString(7, this.getContent());
			}
			if(this.getSuggestDate() == null || this.getSuggestDate().equals("null")) {
				pstmt.setNull(8, 12);
			} else {
				pstmt.setString(8, this.getSuggestDate());
			}
			if(this.getSuggestTime() == null || this.getSuggestTime().equals("null")) {
				pstmt.setNull(9, 12);
			} else {
				pstmt.setString(9, this.getSuggestTime());
			}
			if(this.getDealFlag() == null || this.getDealFlag().equals("null")) {
				pstmt.setNull(10, 12);
			} else {
				pstmt.setString(10, this.getDealFlag());
			}
			if(this.getRemark() == null || this.getRemark().equals("null")) {
				pstmt.setNull(11, 12);
			} else {
				pstmt.setString(11, this.getRemark());
			}
			if(this.getR1() == null || this.getR1().equals("null")) {
				pstmt.setNull(12, 12);
			} else {
				pstmt.setString(12, this.getR1());
			}
			if(this.getR2() == null || this.getR2().equals("null")) {
				pstmt.setNull(13, 12);
			} else {
				pstmt.setString(13, this.getR2());
			}
			if(this.getR3() == null || this.getR3().equals("null")) {
				pstmt.setNull(14, 12);
			} else {
				pstmt.setString(14, this.getR3());
			}
			if(this.getR4() == null || this.getR4().equals("null")) {
				pstmt.setNull(15, 12);
			} else {
				pstmt.setString(15, this.getR4());
			}
			if(this.getR5() == null || this.getR5().equals("null")) {
				pstmt.setNull(16, 12);
			} else {
				pstmt.setString(16, this.getR5());
			}
			if(this.getR6() == null || this.getR6().equals("null")) {
				pstmt.setNull(17, 12);
			} else {
				pstmt.setString(17, this.getR6());
			}
			if(this.getR7() == null || this.getR7().equals("null")) {
				pstmt.setNull(18, 12);
			} else {
				pstmt.setString(18, this.getR7());
			}
			if(this.getR8() == null || this.getR8().equals("null")) {
				pstmt.setNull(19, 12);
			} else {
				pstmt.setString(19, this.getR8());
			}
			if(this.getR9() == null || this.getR9().equals("null")) {
				pstmt.setNull(20, 12);
			} else {
				pstmt.setString(20, this.getR9());
			}
			if(this.getR10() == null || this.getR10().equals("null")) {
				pstmt.setNull(21, 12);
			} else {
				pstmt.setString(21, this.getR10());
			}
			if(this.getR11() == null || this.getR11().equals("null")) {
				pstmt.setNull(22, 12);
			} else {
				pstmt.setString(22, this.getR11());
			}
			if(this.getR12() == null || this.getR12().equals("null")) {
				pstmt.setNull(23, 12);
			} else {
				pstmt.setString(23, this.getR12());
			}
			if(this.getR13() == null || this.getR13().equals("null")) {
				pstmt.setNull(24, 12);
			} else {
				pstmt.setString(24, this.getR13());
			}
			if(this.getR14() == null || this.getR14().equals("null")) {
				pstmt.setNull(25, 12);
			} else {
				pstmt.setString(25, this.getR14());
			}
			if(this.getR15() == null || this.getR15().equals("null")) {
				pstmt.setNull(26, 12);
			} else {
				pstmt.setString(26, this.getR15());
			}
			// execute sql
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception ex) {
			// @@������
			this.mErrors.copyAllErrors(db.mErrors);
			CError tError = new CError();
			tError.moduleName = "SDSuggestDB";
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
			pstmt = con.prepareStatement("SELECT * FROM SDSuggest WHERE  SerialNo = ?", 
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
					// @@������
					CError tError = new CError();
					tError.moduleName = "SDSuggestDB";
					tError.functionName = "getInfo";
					tError.errorMessage = "ȡ��ʧ��!";
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
			// @@������
			CError tError = new CError();
			tError.moduleName = "SDSuggestDB";
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
	    // �Ͽ����ݿ�����
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

	public SDSuggestSet query()
	{
		Statement stmt = null;
		ResultSet rs = null;
		SDSuggestSet aSDSuggestSet = new SDSuggestSet();

	  if( !mflag ) {
		  con = DBConnPool.getConnection();
		}

		try
		{
			stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
			SQLString sqlObj = new SQLString("SDSuggest");
			SDSuggestSchema aSchema = this.getSchema();
			sqlObj.setSQL(5,aSchema);
			String sql = sqlObj.getSQL();

			rs = stmt.executeQuery(sql);
			int i = 0;
			while (rs.next())
			{
				i++;
				SDSuggestSchema s1 = new SDSuggestSchema();
				s1.setSchema(rs,i);
				aSDSuggestSet.add(s1);
			}
			try{ rs.close(); } catch( Exception ex ) {}
			try{ stmt.close(); } catch( Exception ex1 ) {}
		}
		catch(Exception e)
	    {
			// @@������
			CError tError = new CError();
			tError.moduleName = "SDSuggestDB";
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

		return aSDSuggestSet;
	}

	public SDSuggestSet executeQuery(String sql)
	{
		Statement stmt = null;
		ResultSet rs = null;
		SDSuggestSet aSDSuggestSet = new SDSuggestSet();

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
				SDSuggestSchema s1 = new SDSuggestSchema();
				if (!s1.setSchema(rs,i))
				{
					// @@������
					CError tError = new CError();
					tError.moduleName = "SDSuggestDB";
					tError.functionName = "executeQuery";
					tError.errorMessage = "sql���������鿴�������ֶ�����Ϣ!";
					this.mErrors .addOneError(tError);
				}
				aSDSuggestSet.add(s1);
			}
			try{ rs.close(); } catch( Exception ex ) {}
			try{ stmt.close(); } catch( Exception ex1 ) {}
		}
		catch(Exception e)
	    {
			// @@������
			CError tError = new CError();
			tError.moduleName = "SDSuggestDB";
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

		return aSDSuggestSet;
	}

	public SDSuggestSet query(int nStart, int nCount)
	{
		Statement stmt = null;
		ResultSet rs = null;
		SDSuggestSet aSDSuggestSet = new SDSuggestSet();

	  if( !mflag ) {
		  con = DBConnPool.getConnection();
		}

		try
		{
			stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
			SQLString sqlObj = new SQLString("SDSuggest");
			SDSuggestSchema aSchema = this.getSchema();
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

				SDSuggestSchema s1 = new SDSuggestSchema();
				s1.setSchema(rs,i);
				aSDSuggestSet.add(s1);
			}
			try{ rs.close(); } catch( Exception ex ) {}
			try{ stmt.close(); } catch( Exception ex1 ) {}
		}
		catch(Exception e)
	    {
			// @@������
			CError tError = new CError();
			tError.moduleName = "SDSuggestDB";
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

		return aSDSuggestSet;
	}

	public SDSuggestSet executeQuery(String sql, int nStart, int nCount)
	{
		Statement stmt = null;
		ResultSet rs = null;
		SDSuggestSet aSDSuggestSet = new SDSuggestSet();

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

				SDSuggestSchema s1 = new SDSuggestSchema();
				if (!s1.setSchema(rs,i))
				{
					// @@������
					CError tError = new CError();
					tError.moduleName = "SDSuggestDB";
					tError.functionName = "executeQuery";
					tError.errorMessage = "sql���������鿴�������ֶ�����Ϣ!";
					this.mErrors .addOneError(tError);
				}
				aSDSuggestSet.add(s1);
			}
			try{ rs.close(); } catch( Exception ex ) {}
			try{ stmt.close(); } catch( Exception ex1 ) {}
		}
		catch(Exception e)
	    {
			// @@������
			CError tError = new CError();
			tError.moduleName = "SDSuggestDB";
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

		return aSDSuggestSet;
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
			SQLString sqlObj = new SQLString("SDSuggest");
			SDSuggestSchema aSchema = this.getSchema();
			sqlObj.setSQL(2,aSchema);
			String sql = "update SDSuggest " + sqlObj.getUpdPart() + " where " + strWherePart;

			int operCount = stmt.executeUpdate(sql);
			if( operCount == 0 )
			{
				// @@������
				CError tError = new CError();
				tError.moduleName = "SDSuggestDB";
				tError.functionName = "update";
				tError.errorMessage = "��������ʧ��!";
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
			// @@������
			CError tError = new CError();
			tError.moduleName = "SDSuggestDB";
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
	    // �Ͽ����ݿ�����
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
 * ׼�����ݲ�ѯ����
 * @param strSQL String
 * @return boolean
 */
public boolean prepareData(String strSQL)
{
    if (mResultSet != null)
    {
        // @@������
        CError tError = new CError();
        tError.moduleName = "SDSuggestDB";
        tError.functionName = "prepareData";
        tError.errorMessage = "���ݼ��ǿգ�������׼�����ݼ�֮��û�йرգ�";
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
        // @@������
        CError tError = new CError();
        tError.moduleName = "SDSuggestDB";
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
 * ��ȡ���ݼ�
 * @return boolean
 */
public boolean hasMoreData()
{
    boolean flag = true;
    if (null == mResultSet)
    {
        CError tError = new CError();
        tError.moduleName = "SDSuggestDB";
        tError.functionName = "hasMoreData";
        tError.errorMessage = "���ݼ�Ϊ�գ�����׼�����ݼ���";
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
        tError.moduleName = "SDSuggestDB";
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
 * ��ȡ��������
 * @return SDSuggestSet
 */
public SDSuggestSet getData()
{
    int tCount = 0;
    SDSuggestSet tSDSuggestSet = new SDSuggestSet();
    SDSuggestSchema tSDSuggestSchema = null;
    if (null == mResultSet)
    {
        CError tError = new CError();
        tError.moduleName = "SDSuggestDB";
        tError.functionName = "getData";
        tError.errorMessage = "���ݼ�Ϊ�գ�����׼�����ݼ���";
        this.mErrors.addOneError(tError);
        return null;
    }
    try
    {
        tCount = 1;
        tSDSuggestSchema = new SDSuggestSchema();
        tSDSuggestSchema.setSchema(mResultSet, 1);
        tSDSuggestSet.add(tSDSuggestSchema);
        //ע��mResultSet.next()������
        while (tCount++ < SysConst.FETCHCOUNT)
        {
            if (mResultSet.next())
            {
                tSDSuggestSchema = new SDSuggestSchema();
                tSDSuggestSchema.setSchema(mResultSet, 1);
                tSDSuggestSet.add(tSDSuggestSchema);
            }
        }
    }
    catch (Exception ex)
    {
        CError tError = new CError();
        tError.moduleName = "SDSuggestDB";
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
    return tSDSuggestSet;
}
/**
 * �ر����ݼ�
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
            tError.moduleName = "SDSuggestDB";
            tError.functionName = "closeData";
            tError.errorMessage = "���ݼ��Ѿ��ر��ˣ�";
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
        tError.moduleName = "SDSuggestDB";
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
            tError.moduleName = "SDSuggestDB";
            tError.functionName = "closeData";
            tError.errorMessage = "����Ѿ��ر��ˣ�";
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
        tError.moduleName = "SDSuggestDB";
        tError.functionName = "closeData";
        tError.errorMessage = ex3.toString();
        this.mErrors.addOneError(tError);
        flag = false;
    }
    return flag;
}
}
