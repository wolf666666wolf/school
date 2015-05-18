/**
 * Copyright (c) 2002 sinosoft  Co. Ltd.
 * All right reserved.
 */

package com.sinosoft.lis.vdb;

import java.sql.*;
import com.sinosoft.lis.schema.SDOrderSchema;
import com.sinosoft.lis.vschema.SDOrderSet;
import com.sinosoft.lis.pubfun.*;
import com.sinosoft.utility.*;

/*
 * <p>ClassName: SDOrderDBSet </p>
 * <p>Description: DB层多记录数据库操作类文件 </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: sinosoft </p>
 * @Database: PhysicalDataModel_1
 */
public class SDOrderDBSet extends SDOrderSet
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
	public SDOrderDBSet(Connection tConnection)
	{
		con = tConnection;
		db = new DBOper(con,"SDOrder");
		mflag = true;
	}

	public SDOrderDBSet()
	{
		db = new DBOper( "SDOrder" );
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
			tError.moduleName = "SDOrderDBSet";
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
			pstmt = con.prepareStatement("DELETE FROM SDOrder WHERE  OrderID = ?");
            for (int i = 1; i <= tCount; i++)
            {
			if(this.get(i).getOrderID() == null || this.get(i).getOrderID().equals("null")) {
				pstmt.setString(1,null);
			} else {
				pstmt.setString(1, this.get(i).getOrderID());
			}

		// only for debug purpose
		SQLString sqlObj = new SQLString("SDOrder");
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
			tError.moduleName = "SDOrderDBSet";
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
			pstmt = con.prepareStatement("UPDATE SDOrder SET  OrderID = ? , PrdID = ? , UserID = ? , PrdPrice = ? , OrderDate = ? , ArriveDate = ? , Owner = ? , OCount = ? , ComeFlag = ? , Requirement = ? , Address = ? , Phone = ? , Tel = ? , MakeDate = ? , MakeTime = ? , ModifyDate = ? , ModifyTime = ? , R1 = ? , R2 = ? , R3 = ? , R4 = ? , R5 = ? , R6 = ? , R7 = ? , R8 = ? , R9 = ? , R10 = ? , R11 = ? , R12 = ? , R13 = ? , R14 = ? , R15 = ? WHERE  OrderID = ?");
            for (int i = 1; i <= tCount; i++)
            {
			if(this.get(i).getOrderID() == null || this.get(i).getOrderID().equals("null")) {
				pstmt.setString(1,null);
			} else {
				pstmt.setString(1, this.get(i).getOrderID());
			}
			if(this.get(i).getPrdID() == null || this.get(i).getPrdID().equals("null")) {
				pstmt.setString(2,null);
			} else {
				pstmt.setString(2, this.get(i).getPrdID());
			}
			if(this.get(i).getUserID() == null || this.get(i).getUserID().equals("null")) {
				pstmt.setString(3,null);
			} else {
				pstmt.setString(3, this.get(i).getUserID());
			}
			if(this.get(i).getPrdPrice() == null || this.get(i).getPrdPrice().equals("null")) {
				pstmt.setString(4,null);
			} else {
				pstmt.setString(4, this.get(i).getPrdPrice());
			}
			if(this.get(i).getOrderDate() == null || this.get(i).getOrderDate().equals("null")) {
				pstmt.setString(5,null);
			} else {
				pstmt.setString(5, this.get(i).getOrderDate());
			}
			if(this.get(i).getArriveDate() == null || this.get(i).getArriveDate().equals("null")) {
				pstmt.setString(6,null);
			} else {
				pstmt.setString(6, this.get(i).getArriveDate());
			}
			if(this.get(i).getOwner() == null || this.get(i).getOwner().equals("null")) {
				pstmt.setString(7,null);
			} else {
				pstmt.setString(7, this.get(i).getOwner());
			}
			if(this.get(i).getOCount() == null || this.get(i).getOCount().equals("null")) {
				pstmt.setString(8,null);
			} else {
				pstmt.setString(8, this.get(i).getOCount());
			}
			if(this.get(i).getComeFlag() == null || this.get(i).getComeFlag().equals("null")) {
				pstmt.setString(9,null);
			} else {
				pstmt.setString(9, this.get(i).getComeFlag());
			}
			if(this.get(i).getRequirement() == null || this.get(i).getRequirement().equals("null")) {
				pstmt.setString(10,null);
			} else {
				pstmt.setString(10, this.get(i).getRequirement());
			}
			if(this.get(i).getAddress() == null || this.get(i).getAddress().equals("null")) {
				pstmt.setString(11,null);
			} else {
				pstmt.setString(11, this.get(i).getAddress());
			}
			if(this.get(i).getPhone() == null || this.get(i).getPhone().equals("null")) {
				pstmt.setString(12,null);
			} else {
				pstmt.setString(12, this.get(i).getPhone());
			}
			if(this.get(i).getTel() == null || this.get(i).getTel().equals("null")) {
				pstmt.setString(13,null);
			} else {
				pstmt.setString(13, this.get(i).getTel());
			}
			if(this.get(i).getMakeDate() == null || this.get(i).getMakeDate().equals("null")) {
				pstmt.setString(14,null);
			} else {
				pstmt.setString(14, this.get(i).getMakeDate());
			}
			if(this.get(i).getMakeTime() == null || this.get(i).getMakeTime().equals("null")) {
				pstmt.setString(15,null);
			} else {
				pstmt.setString(15, this.get(i).getMakeTime());
			}
			if(this.get(i).getModifyDate() == null || this.get(i).getModifyDate().equals("null")) {
				pstmt.setString(16,null);
			} else {
				pstmt.setString(16, this.get(i).getModifyDate());
			}
			if(this.get(i).getModifyTime() == null || this.get(i).getModifyTime().equals("null")) {
				pstmt.setString(17,null);
			} else {
				pstmt.setString(17, this.get(i).getModifyTime());
			}
			if(this.get(i).getR1() == null || this.get(i).getR1().equals("null")) {
				pstmt.setString(18,null);
			} else {
				pstmt.setString(18, this.get(i).getR1());
			}
			if(this.get(i).getR2() == null || this.get(i).getR2().equals("null")) {
				pstmt.setString(19,null);
			} else {
				pstmt.setString(19, this.get(i).getR2());
			}
			if(this.get(i).getR3() == null || this.get(i).getR3().equals("null")) {
				pstmt.setString(20,null);
			} else {
				pstmt.setString(20, this.get(i).getR3());
			}
			if(this.get(i).getR4() == null || this.get(i).getR4().equals("null")) {
				pstmt.setString(21,null);
			} else {
				pstmt.setString(21, this.get(i).getR4());
			}
			if(this.get(i).getR5() == null || this.get(i).getR5().equals("null")) {
				pstmt.setString(22,null);
			} else {
				pstmt.setString(22, this.get(i).getR5());
			}
			if(this.get(i).getR6() == null || this.get(i).getR6().equals("null")) {
				pstmt.setString(23,null);
			} else {
				pstmt.setString(23, this.get(i).getR6());
			}
			if(this.get(i).getR7() == null || this.get(i).getR7().equals("null")) {
				pstmt.setString(24,null);
			} else {
				pstmt.setString(24, this.get(i).getR7());
			}
			if(this.get(i).getR8() == null || this.get(i).getR8().equals("null")) {
				pstmt.setString(25,null);
			} else {
				pstmt.setString(25, this.get(i).getR8());
			}
			if(this.get(i).getR9() == null || this.get(i).getR9().equals("null")) {
				pstmt.setString(26,null);
			} else {
				pstmt.setString(26, this.get(i).getR9());
			}
			if(this.get(i).getR10() == null || this.get(i).getR10().equals("null")) {
				pstmt.setString(27,null);
			} else {
				pstmt.setString(27, this.get(i).getR10());
			}
			if(this.get(i).getR11() == null || this.get(i).getR11().equals("null")) {
				pstmt.setString(28,null);
			} else {
				pstmt.setString(28, this.get(i).getR11());
			}
			if(this.get(i).getR12() == null || this.get(i).getR12().equals("null")) {
				pstmt.setString(29,null);
			} else {
				pstmt.setString(29, this.get(i).getR12());
			}
			if(this.get(i).getR13() == null || this.get(i).getR13().equals("null")) {
				pstmt.setString(30,null);
			} else {
				pstmt.setString(30, this.get(i).getR13());
			}
			if(this.get(i).getR14() == null || this.get(i).getR14().equals("null")) {
				pstmt.setString(31,null);
			} else {
				pstmt.setString(31, this.get(i).getR14());
			}
			if(this.get(i).getR15() == null || this.get(i).getR15().equals("null")) {
				pstmt.setString(32,null);
			} else {
				pstmt.setString(32, this.get(i).getR15());
			}
			// set where condition
			if(this.get(i).getOrderID() == null || this.get(i).getOrderID().equals("null")) {
				pstmt.setString(33,null);
			} else {
				pstmt.setString(33, this.get(i).getOrderID());
			}

		// only for debug purpose
		SQLString sqlObj = new SQLString("SDOrder");
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
			tError.moduleName = "SDOrderDBSet";
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
			pstmt = con.prepareStatement("INSERT INTO SDOrder(OrderID ,PrdID ,UserID ,PrdPrice ,OrderDate ,ArriveDate ,Owner ,OCount ,ComeFlag ,Requirement ,Address ,Phone ,Tel ,MakeDate ,MakeTime ,ModifyDate ,ModifyTime ,R1 ,R2 ,R3 ,R4 ,R5 ,R6 ,R7 ,R8 ,R9 ,R10 ,R11 ,R12 ,R13 ,R14 ,R15) VALUES( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)");
            for (int i = 1; i <= tCount; i++)
            {
			if(this.get(i).getOrderID() == null || this.get(i).getOrderID().equals("null")) {
				pstmt.setString(1,null);
			} else {
				pstmt.setString(1, this.get(i).getOrderID());
			}
			if(this.get(i).getPrdID() == null || this.get(i).getPrdID().equals("null")) {
				pstmt.setString(2,null);
			} else {
				pstmt.setString(2, this.get(i).getPrdID());
			}
			if(this.get(i).getUserID() == null || this.get(i).getUserID().equals("null")) {
				pstmt.setString(3,null);
			} else {
				pstmt.setString(3, this.get(i).getUserID());
			}
			if(this.get(i).getPrdPrice() == null || this.get(i).getPrdPrice().equals("null")) {
				pstmt.setString(4,null);
			} else {
				pstmt.setString(4, this.get(i).getPrdPrice());
			}
			if(this.get(i).getOrderDate() == null || this.get(i).getOrderDate().equals("null")) {
				pstmt.setString(5,null);
			} else {
				pstmt.setString(5, this.get(i).getOrderDate());
			}
			if(this.get(i).getArriveDate() == null || this.get(i).getArriveDate().equals("null")) {
				pstmt.setString(6,null);
			} else {
				pstmt.setString(6, this.get(i).getArriveDate());
			}
			if(this.get(i).getOwner() == null || this.get(i).getOwner().equals("null")) {
				pstmt.setString(7,null);
			} else {
				pstmt.setString(7, this.get(i).getOwner());
			}
			if(this.get(i).getOCount() == null || this.get(i).getOCount().equals("null")) {
				pstmt.setString(8,null);
			} else {
				pstmt.setString(8, this.get(i).getOCount());
			}
			if(this.get(i).getComeFlag() == null || this.get(i).getComeFlag().equals("null")) {
				pstmt.setString(9,null);
			} else {
				pstmt.setString(9, this.get(i).getComeFlag());
			}
			if(this.get(i).getRequirement() == null || this.get(i).getRequirement().equals("null")) {
				pstmt.setString(10,null);
			} else {
				pstmt.setString(10, this.get(i).getRequirement());
			}
			if(this.get(i).getAddress() == null || this.get(i).getAddress().equals("null")) {
				pstmt.setString(11,null);
			} else {
				pstmt.setString(11, this.get(i).getAddress());
			}
			if(this.get(i).getPhone() == null || this.get(i).getPhone().equals("null")) {
				pstmt.setString(12,null);
			} else {
				pstmt.setString(12, this.get(i).getPhone());
			}
			if(this.get(i).getTel() == null || this.get(i).getTel().equals("null")) {
				pstmt.setString(13,null);
			} else {
				pstmt.setString(13, this.get(i).getTel());
			}
			if(this.get(i).getMakeDate() == null || this.get(i).getMakeDate().equals("null")) {
				pstmt.setString(14,null);
			} else {
				pstmt.setString(14, this.get(i).getMakeDate());
			}
			if(this.get(i).getMakeTime() == null || this.get(i).getMakeTime().equals("null")) {
				pstmt.setString(15,null);
			} else {
				pstmt.setString(15, this.get(i).getMakeTime());
			}
			if(this.get(i).getModifyDate() == null || this.get(i).getModifyDate().equals("null")) {
				pstmt.setString(16,null);
			} else {
				pstmt.setString(16, this.get(i).getModifyDate());
			}
			if(this.get(i).getModifyTime() == null || this.get(i).getModifyTime().equals("null")) {
				pstmt.setString(17,null);
			} else {
				pstmt.setString(17, this.get(i).getModifyTime());
			}
			if(this.get(i).getR1() == null || this.get(i).getR1().equals("null")) {
				pstmt.setString(18,null);
			} else {
				pstmt.setString(18, this.get(i).getR1());
			}
			if(this.get(i).getR2() == null || this.get(i).getR2().equals("null")) {
				pstmt.setString(19,null);
			} else {
				pstmt.setString(19, this.get(i).getR2());
			}
			if(this.get(i).getR3() == null || this.get(i).getR3().equals("null")) {
				pstmt.setString(20,null);
			} else {
				pstmt.setString(20, this.get(i).getR3());
			}
			if(this.get(i).getR4() == null || this.get(i).getR4().equals("null")) {
				pstmt.setString(21,null);
			} else {
				pstmt.setString(21, this.get(i).getR4());
			}
			if(this.get(i).getR5() == null || this.get(i).getR5().equals("null")) {
				pstmt.setString(22,null);
			} else {
				pstmt.setString(22, this.get(i).getR5());
			}
			if(this.get(i).getR6() == null || this.get(i).getR6().equals("null")) {
				pstmt.setString(23,null);
			} else {
				pstmt.setString(23, this.get(i).getR6());
			}
			if(this.get(i).getR7() == null || this.get(i).getR7().equals("null")) {
				pstmt.setString(24,null);
			} else {
				pstmt.setString(24, this.get(i).getR7());
			}
			if(this.get(i).getR8() == null || this.get(i).getR8().equals("null")) {
				pstmt.setString(25,null);
			} else {
				pstmt.setString(25, this.get(i).getR8());
			}
			if(this.get(i).getR9() == null || this.get(i).getR9().equals("null")) {
				pstmt.setString(26,null);
			} else {
				pstmt.setString(26, this.get(i).getR9());
			}
			if(this.get(i).getR10() == null || this.get(i).getR10().equals("null")) {
				pstmt.setString(27,null);
			} else {
				pstmt.setString(27, this.get(i).getR10());
			}
			if(this.get(i).getR11() == null || this.get(i).getR11().equals("null")) {
				pstmt.setString(28,null);
			} else {
				pstmt.setString(28, this.get(i).getR11());
			}
			if(this.get(i).getR12() == null || this.get(i).getR12().equals("null")) {
				pstmt.setString(29,null);
			} else {
				pstmt.setString(29, this.get(i).getR12());
			}
			if(this.get(i).getR13() == null || this.get(i).getR13().equals("null")) {
				pstmt.setString(30,null);
			} else {
				pstmt.setString(30, this.get(i).getR13());
			}
			if(this.get(i).getR14() == null || this.get(i).getR14().equals("null")) {
				pstmt.setString(31,null);
			} else {
				pstmt.setString(31, this.get(i).getR14());
			}
			if(this.get(i).getR15() == null || this.get(i).getR15().equals("null")) {
				pstmt.setString(32,null);
			} else {
				pstmt.setString(32, this.get(i).getR15());
			}

		// only for debug purpose
		SQLString sqlObj = new SQLString("SDOrder");
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
			tError.moduleName = "SDOrderDBSet";
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
