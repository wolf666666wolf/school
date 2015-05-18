﻿/**
 * Copyright (c) 2002 sinosoft  Co. Ltd.
 * All right reserved.
 */

package com.sinosoft.lis.vdb;

import java.sql.*;
import com.sinosoft.lis.schema.LDMaxNoSchema;
import com.sinosoft.lis.vschema.LDMaxNoSet;
import com.sinosoft.lis.pubfun.*;
import com.sinosoft.utility.*;

/*
 * <p>ClassName: LDMaxNoDBSet </p>
 * <p>Description: DB层多记录数据库操作类文件 </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: sinosoft </p>
 * @Database: PhysicalDataModel_6
 * @CreateDate：2005-12-09
 */
public class LDMaxNoDBSet extends LDMaxNoSet
{
	// @Field
	private Connection con;
	private DBOper db;
	/**
	* flag = true: 传入Connection
	* flag = false: 不传入Connection
	**/
	private boolean mflag = false;


	// @Constructor
	public LDMaxNoDBSet(Connection tConnection)
	{
		con = tConnection;
		db = new DBOper(con,"LDMaxNo");
		mflag = true;
	}

	public LDMaxNoDBSet()
	{
		db = new DBOper( "LDMaxNo" );
		con = db.getConnection();
	}
	// @Method
	public boolean deleteSQL()
	{
		if (db.deleteSQL(this))
		{
		        return true;
		}
		else
		{
			// @@错误处理
			this.mErrors.copyAllErrors(db.mErrors);
			CError tError = new CError();
			tError.moduleName = "LDMaxNoDBSet";
			tError.functionName = "deleteSQL";
			tError.errorMessage = "操作失败!";
			this.mErrors .addOneError(tError);
			return false;
		}
	}

    /**
     * 删除操作
     * 删除条件：主键
     * @return boolean
     */
	public boolean delete()
	{
		PreparedStatement pstmt = null;

		if( !mflag ) {
			con = DBConnPool.getConnection();
		}

		try
		{
            int tCount = this.size();
			pstmt = con.prepareStatement("DELETE FROM LDMaxNo WHERE  NoType = ? AND NoLimit = ?");
            for (int i = 1; i <= tCount; i++)
            {
			if(this.get(i).getNoType() == null || this.get(i).getNoType().equals("null")) {
				pstmt.setString(1,null);
			} else {
				pstmt.setString(1, StrTool.space(this.get(i).getNoType(), 15));
			}
			if(this.get(i).getNoLimit() == null || this.get(i).getNoLimit().equals("null")) {
				pstmt.setString(2,null);
			} else {
				pstmt.setString(2, StrTool.space(this.get(i).getNoLimit(), 20));
			}
                pstmt.addBatch();
            }
            pstmt.executeBatch();
			pstmt.close();
		} catch (Exception ex) {
			// @@错误处理
ex.printStackTrace();
			this.mErrors.copyAllErrors(db.mErrors);
			CError tError = new CError();
			tError.moduleName = "LDMaxNoDBSet";
			tError.functionName = "delete()";
			tError.errorMessage = ex.toString();
			this.mErrors .addOneError(tError);

			try {
				pstmt.close();
			} catch (Exception e){e.printStackTrace();}

			if( !mflag ) {
				try {
					con.close();
				} catch (Exception e){e.printStackTrace();}
			}

			return false;
		}

		if( !mflag ) {
			try {
				con.close();
			} catch (Exception e){e.printStackTrace();}
		}

		return true;
	}

    /**
     * 更新操作
     * 更新条件：主键
     * @return boolean
     */
	public boolean update()
	{
		PreparedStatement pstmt = null;

		if( !mflag ) {
			con = DBConnPool.getConnection();
		}

		try
		{
            int tCount = this.size();
			pstmt = con.prepareStatement("UPDATE LDMaxNo SET  NoType = ? , NoLimit = ? , MaxNo = ? WHERE  NoType = ? AND NoLimit = ?");
            for (int i = 1; i <= tCount; i++)
            {
			if(this.get(i).getNoType() == null || this.get(i).getNoType().equals("null")) {
				pstmt.setString(1,null);
			} else {
				pstmt.setString(1, this.get(i).getNoType());
			}
			if(this.get(i).getNoLimit() == null || this.get(i).getNoLimit().equals("null")) {
				pstmt.setString(2,null);
			} else {
				pstmt.setString(2, this.get(i).getNoLimit());
			}
			pstmt.setDouble(3, this.get(i).getMaxNo());
			// set where condition
			if(this.get(i).getNoType() == null || this.get(i).getNoType().equals("null")) {
				pstmt.setString(4,null);
			} else {
				pstmt.setString(4, StrTool.space(this.get(i).getNoType(), 15));
			}
			if(this.get(i).getNoLimit() == null || this.get(i).getNoLimit().equals("null")) {
				pstmt.setString(5,null);
			} else {
				pstmt.setString(5, StrTool.space(this.get(i).getNoLimit(), 20));
			}
                pstmt.addBatch();
            }
            pstmt.executeBatch();
			pstmt.close();
		} catch (Exception ex) {
			// @@错误处理
ex.printStackTrace();
			this.mErrors.copyAllErrors(db.mErrors);
			CError tError = new CError();
			tError.moduleName = "LDMaxNoDBSet";
			tError.functionName = "update()";
			tError.errorMessage = ex.toString();
			this.mErrors .addOneError(tError);

			try {
				pstmt.close();
			} catch (Exception e){e.printStackTrace();}

			if( !mflag ) {
				try {
					con.close();
				} catch (Exception e){e.printStackTrace();}
			}

			return false;
		}

		if( !mflag ) {
			try {
				con.close();
			} catch (Exception e){e.printStackTrace();}
		}

		return true;
	}

    /**
     * 新增操作
     * @return boolean
     */
	public boolean insert()
	{
		PreparedStatement pstmt = null;

		if( !mflag ) {
			con = DBConnPool.getConnection();
		}

		try
		{
            int tCount = this.size();
			pstmt = con.prepareStatement("INSERT INTO LDMaxNo VALUES( ? , ? , ?)");
            for (int i = 1; i <= tCount; i++)
            {
			if(this.get(i).getNoType() == null || this.get(i).getNoType().equals("null")) {
				pstmt.setString(1,null);
			} else {
				pstmt.setString(1, this.get(i).getNoType());
			}
			if(this.get(i).getNoLimit() == null || this.get(i).getNoLimit().equals("null")) {
				pstmt.setString(2,null);
			} else {
				pstmt.setString(2, this.get(i).getNoLimit());
			}
			pstmt.setDouble(3, this.get(i).getMaxNo());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
			pstmt.close();
		} catch (Exception ex) {
			// @@错误处理
ex.printStackTrace();
			this.mErrors.copyAllErrors(db.mErrors);
			CError tError = new CError();
			tError.moduleName = "LDMaxNoDBSet";
			tError.functionName = "insert()";
			tError.errorMessage = ex.toString();
			this.mErrors .addOneError(tError);

			try {
				pstmt.close();
			} catch (Exception e){e.printStackTrace();}

			if( !mflag ) {
				try {
					con.close();
				} catch (Exception e){e.printStackTrace();}
			}

			return false;
		}

		if( !mflag ) {
			try {
				con.close();
			} catch (Exception e){e.printStackTrace();}
		}

		return true;
	}

}
