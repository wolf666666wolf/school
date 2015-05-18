/**
 * Copyright (c) 2002 sinosoft  Co. Ltd.
 * All right reserved.
 */

package com.sinosoft.lis.vdb;

import java.sql.*;
import com.sinosoft.lis.schema.SDSellerMessageSchema;
import com.sinosoft.lis.vschema.SDSellerMessageSet;
import com.sinosoft.lis.pubfun.*;
import com.sinosoft.utility.*;

/*
 * <p>ClassName: SDSellerMessageDBSet </p>
 * <p>Description: DB层多记录数据库操作类文件 </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: sinosoft </p>
 * @Database: PhysicalDataModel_1
 */
public class SDSellerMessageDBSet extends SDSellerMessageSet
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
	public SDSellerMessageDBSet(Connection tConnection)
	{
		con = tConnection;
		db = new DBOper(con,"SDSellerMessage");
		mflag = true;
	}

	public SDSellerMessageDBSet()
	{
		db = new DBOper( "SDSellerMessage" );
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
			tError.moduleName = "SDSellerMessageDBSet";
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
			pstmt = con.prepareStatement("DELETE FROM SDSellerMessage WHERE  SerialNo = ?");
            for (int i = 1; i <= tCount; i++)
            {
			if(this.get(i).getSerialNo() == null || this.get(i).getSerialNo().equals("null")) {
				pstmt.setString(1,null);
			} else {
				pstmt.setString(1, this.get(i).getSerialNo());
			}

		// only for debug purpose
		SQLString sqlObj = new SQLString("SDSellerMessage");
		sqlObj.setSQL(4, this.get(i));
		sqlObj.getSQL();

                pstmt.addBatch();
            }
            pstmt.executeBatch();
			pstmt.close();
		} catch (Exception ex) {
			// @@错误处理
ex.printStackTrace();
			this.mErrors.copyAllErrors(db.mErrors);
			CError tError = new CError();
			tError.moduleName = "SDSellerMessageDBSet";
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
			pstmt = con.prepareStatement("UPDATE SDSellerMessage SET  SerialNo = ? , UserID = ? , SellerID = ? , MsgDate = ? , MsgTime = ? , Level = ? , Context = ? , Phone = ? , OrderID = ? , R1 = ? , R2 = ? , R3 = ? , R4 = ? , R5 = ? , R6 = ? , R7 = ? , R8 = ? , R9 = ? , R10 = ? , R11 = ? , R12 = ? , R13 = ? , R14 = ? , R15 = ? WHERE  SerialNo = ?");
            for (int i = 1; i <= tCount; i++)
            {
			if(this.get(i).getSerialNo() == null || this.get(i).getSerialNo().equals("null")) {
				pstmt.setString(1,null);
			} else {
				pstmt.setString(1, this.get(i).getSerialNo());
			}
			if(this.get(i).getUserID() == null || this.get(i).getUserID().equals("null")) {
				pstmt.setString(2,null);
			} else {
				pstmt.setString(2, this.get(i).getUserID());
			}
			if(this.get(i).getSellerID() == null || this.get(i).getSellerID().equals("null")) {
				pstmt.setString(3,null);
			} else {
				pstmt.setString(3, this.get(i).getSellerID());
			}
			if(this.get(i).getMsgDate() == null || this.get(i).getMsgDate().equals("null")) {
				pstmt.setString(4,null);
			} else {
				pstmt.setString(4, this.get(i).getMsgDate());
			}
			if(this.get(i).getMsgTime() == null || this.get(i).getMsgTime().equals("null")) {
				pstmt.setString(5,null);
			} else {
				pstmt.setString(5, this.get(i).getMsgTime());
			}
			if(this.get(i).getLevel() == null || this.get(i).getLevel().equals("null")) {
				pstmt.setString(6,null);
			} else {
				pstmt.setString(6, this.get(i).getLevel());
			}
			if(this.get(i).getContext() == null || this.get(i).getContext().equals("null")) {
				pstmt.setString(7,null);
			} else {
				pstmt.setString(7, this.get(i).getContext());
			}
			if(this.get(i).getPhone() == null || this.get(i).getPhone().equals("null")) {
				pstmt.setString(8,null);
			} else {
				pstmt.setString(8, this.get(i).getPhone());
			}
			if(this.get(i).getOrderID() == null || this.get(i).getOrderID().equals("null")) {
				pstmt.setString(9,null);
			} else {
				pstmt.setString(9, this.get(i).getOrderID());
			}
			if(this.get(i).getR1() == null || this.get(i).getR1().equals("null")) {
				pstmt.setString(10,null);
			} else {
				pstmt.setString(10, this.get(i).getR1());
			}
			if(this.get(i).getR2() == null || this.get(i).getR2().equals("null")) {
				pstmt.setString(11,null);
			} else {
				pstmt.setString(11, this.get(i).getR2());
			}
			if(this.get(i).getR3() == null || this.get(i).getR3().equals("null")) {
				pstmt.setString(12,null);
			} else {
				pstmt.setString(12, this.get(i).getR3());
			}
			if(this.get(i).getR4() == null || this.get(i).getR4().equals("null")) {
				pstmt.setString(13,null);
			} else {
				pstmt.setString(13, this.get(i).getR4());
			}
			if(this.get(i).getR5() == null || this.get(i).getR5().equals("null")) {
				pstmt.setString(14,null);
			} else {
				pstmt.setString(14, this.get(i).getR5());
			}
			if(this.get(i).getR6() == null || this.get(i).getR6().equals("null")) {
				pstmt.setString(15,null);
			} else {
				pstmt.setString(15, this.get(i).getR6());
			}
			if(this.get(i).getR7() == null || this.get(i).getR7().equals("null")) {
				pstmt.setString(16,null);
			} else {
				pstmt.setString(16, this.get(i).getR7());
			}
			if(this.get(i).getR8() == null || this.get(i).getR8().equals("null")) {
				pstmt.setString(17,null);
			} else {
				pstmt.setString(17, this.get(i).getR8());
			}
			if(this.get(i).getR9() == null || this.get(i).getR9().equals("null")) {
				pstmt.setString(18,null);
			} else {
				pstmt.setString(18, this.get(i).getR9());
			}
			if(this.get(i).getR10() == null || this.get(i).getR10().equals("null")) {
				pstmt.setString(19,null);
			} else {
				pstmt.setString(19, this.get(i).getR10());
			}
			if(this.get(i).getR11() == null || this.get(i).getR11().equals("null")) {
				pstmt.setString(20,null);
			} else {
				pstmt.setString(20, this.get(i).getR11());
			}
			if(this.get(i).getR12() == null || this.get(i).getR12().equals("null")) {
				pstmt.setString(21,null);
			} else {
				pstmt.setString(21, this.get(i).getR12());
			}
			if(this.get(i).getR13() == null || this.get(i).getR13().equals("null")) {
				pstmt.setString(22,null);
			} else {
				pstmt.setString(22, this.get(i).getR13());
			}
			if(this.get(i).getR14() == null || this.get(i).getR14().equals("null")) {
				pstmt.setString(23,null);
			} else {
				pstmt.setString(23, this.get(i).getR14());
			}
			if(this.get(i).getR15() == null || this.get(i).getR15().equals("null")) {
				pstmt.setString(24,null);
			} else {
				pstmt.setString(24, this.get(i).getR15());
			}
			// set where condition
			if(this.get(i).getSerialNo() == null || this.get(i).getSerialNo().equals("null")) {
				pstmt.setString(25,null);
			} else {
				pstmt.setString(25, this.get(i).getSerialNo());
			}

		// only for debug purpose
		SQLString sqlObj = new SQLString("SDSellerMessage");
		sqlObj.setSQL(2, this.get(i));
		sqlObj.getSQL();

                pstmt.addBatch();
            }
            pstmt.executeBatch();
			pstmt.close();
		} catch (Exception ex) {
			// @@错误处理
ex.printStackTrace();
			this.mErrors.copyAllErrors(db.mErrors);
			CError tError = new CError();
			tError.moduleName = "SDSellerMessageDBSet";
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
			pstmt = con.prepareStatement("INSERT INTO SDSellerMessage(SerialNo ,UserID ,SellerID ,MsgDate ,MsgTime ,Level ,Context ,Phone ,OrderID ,R1 ,R2 ,R3 ,R4 ,R5 ,R6 ,R7 ,R8 ,R9 ,R10 ,R11 ,R12 ,R13 ,R14 ,R15) VALUES( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)");
            for (int i = 1; i <= tCount; i++)
            {
			if(this.get(i).getSerialNo() == null || this.get(i).getSerialNo().equals("null")) {
				pstmt.setString(1,null);
			} else {
				pstmt.setString(1, this.get(i).getSerialNo());
			}
			if(this.get(i).getUserID() == null || this.get(i).getUserID().equals("null")) {
				pstmt.setString(2,null);
			} else {
				pstmt.setString(2, this.get(i).getUserID());
			}
			if(this.get(i).getSellerID() == null || this.get(i).getSellerID().equals("null")) {
				pstmt.setString(3,null);
			} else {
				pstmt.setString(3, this.get(i).getSellerID());
			}
			if(this.get(i).getMsgDate() == null || this.get(i).getMsgDate().equals("null")) {
				pstmt.setString(4,null);
			} else {
				pstmt.setString(4, this.get(i).getMsgDate());
			}
			if(this.get(i).getMsgTime() == null || this.get(i).getMsgTime().equals("null")) {
				pstmt.setString(5,null);
			} else {
				pstmt.setString(5, this.get(i).getMsgTime());
			}
			if(this.get(i).getLevel() == null || this.get(i).getLevel().equals("null")) {
				pstmt.setString(6,null);
			} else {
				pstmt.setString(6, this.get(i).getLevel());
			}
			if(this.get(i).getContext() == null || this.get(i).getContext().equals("null")) {
				pstmt.setString(7,null);
			} else {
				pstmt.setString(7, this.get(i).getContext());
			}
			if(this.get(i).getPhone() == null || this.get(i).getPhone().equals("null")) {
				pstmt.setString(8,null);
			} else {
				pstmt.setString(8, this.get(i).getPhone());
			}
			if(this.get(i).getOrderID() == null || this.get(i).getOrderID().equals("null")) {
				pstmt.setString(9,null);
			} else {
				pstmt.setString(9, this.get(i).getOrderID());
			}
			if(this.get(i).getR1() == null || this.get(i).getR1().equals("null")) {
				pstmt.setString(10,null);
			} else {
				pstmt.setString(10, this.get(i).getR1());
			}
			if(this.get(i).getR2() == null || this.get(i).getR2().equals("null")) {
				pstmt.setString(11,null);
			} else {
				pstmt.setString(11, this.get(i).getR2());
			}
			if(this.get(i).getR3() == null || this.get(i).getR3().equals("null")) {
				pstmt.setString(12,null);
			} else {
				pstmt.setString(12, this.get(i).getR3());
			}
			if(this.get(i).getR4() == null || this.get(i).getR4().equals("null")) {
				pstmt.setString(13,null);
			} else {
				pstmt.setString(13, this.get(i).getR4());
			}
			if(this.get(i).getR5() == null || this.get(i).getR5().equals("null")) {
				pstmt.setString(14,null);
			} else {
				pstmt.setString(14, this.get(i).getR5());
			}
			if(this.get(i).getR6() == null || this.get(i).getR6().equals("null")) {
				pstmt.setString(15,null);
			} else {
				pstmt.setString(15, this.get(i).getR6());
			}
			if(this.get(i).getR7() == null || this.get(i).getR7().equals("null")) {
				pstmt.setString(16,null);
			} else {
				pstmt.setString(16, this.get(i).getR7());
			}
			if(this.get(i).getR8() == null || this.get(i).getR8().equals("null")) {
				pstmt.setString(17,null);
			} else {
				pstmt.setString(17, this.get(i).getR8());
			}
			if(this.get(i).getR9() == null || this.get(i).getR9().equals("null")) {
				pstmt.setString(18,null);
			} else {
				pstmt.setString(18, this.get(i).getR9());
			}
			if(this.get(i).getR10() == null || this.get(i).getR10().equals("null")) {
				pstmt.setString(19,null);
			} else {
				pstmt.setString(19, this.get(i).getR10());
			}
			if(this.get(i).getR11() == null || this.get(i).getR11().equals("null")) {
				pstmt.setString(20,null);
			} else {
				pstmt.setString(20, this.get(i).getR11());
			}
			if(this.get(i).getR12() == null || this.get(i).getR12().equals("null")) {
				pstmt.setString(21,null);
			} else {
				pstmt.setString(21, this.get(i).getR12());
			}
			if(this.get(i).getR13() == null || this.get(i).getR13().equals("null")) {
				pstmt.setString(22,null);
			} else {
				pstmt.setString(22, this.get(i).getR13());
			}
			if(this.get(i).getR14() == null || this.get(i).getR14().equals("null")) {
				pstmt.setString(23,null);
			} else {
				pstmt.setString(23, this.get(i).getR14());
			}
			if(this.get(i).getR15() == null || this.get(i).getR15().equals("null")) {
				pstmt.setString(24,null);
			} else {
				pstmt.setString(24, this.get(i).getR15());
			}

		// only for debug purpose
		SQLString sqlObj = new SQLString("SDSellerMessage");
		sqlObj.setSQL(1, this.get(i));
		sqlObj.getSQL();

                pstmt.addBatch();
            }
            pstmt.executeBatch();
			pstmt.close();
		} catch (Exception ex) {
			// @@错误处理
ex.printStackTrace();
			this.mErrors.copyAllErrors(db.mErrors);
			CError tError = new CError();
			tError.moduleName = "SDSellerMessageDBSet";
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
