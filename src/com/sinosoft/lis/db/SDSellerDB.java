/**
 * Copyright (c) 2002 sinosoft  Co. Ltd.
 * All right reserved.
 */

package com.sinosoft.lis.db;

import java.sql.*;
import com.sinosoft.lis.schema.SDSellerSchema;
import com.sinosoft.lis.vschema.SDSellerSet;
import com.sinosoft.lis.pubfun.PubFun;
import com.sinosoft.utility.*;

/*
 * <p>ClassName: SDSellerDB </p>
 * <p>Description: DB�����ݿ�������ļ� </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: sinosoft </p>
 * @Database: PhysicalDataModel_1
 */
public class SDSellerDB extends SDSellerSchema
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
	public SDSellerDB( Connection tConnection )
	{
		con = tConnection;
		db = new DBOper( con, "SDSeller" );
		mflag = true;
	}

	public SDSellerDB()
	{
		con = null;
		db = new DBOper( "SDSeller" );
		mflag = false;
	}

	// @Method
	public boolean deleteSQL()
	{
		SDSellerSchema tSchema = this.getSchema();
		if (db.deleteSQL(tSchema))
		{
		     return true;
		}
		else
		{
			// @@������
			this.mErrors.copyAllErrors(db.mErrors);
			CError tError = new CError();
			tError.moduleName = "SDSellerDB";
			tError.functionName = "deleteSQL";
			tError.errorMessage = "����ʧ��!";
			this.mErrors .addOneError(tError);
			return false;
		}
	}

	public int getCount()
	{
		SDSellerSchema tSchema = this.getSchema();

		int tCount = db.getCount(tSchema);
		if (tCount < 0)
		{
			// @@������
			this.mErrors.copyAllErrors(db.mErrors);
			CError tError = new CError();
			tError.moduleName = "SDSellerDB";
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
			pstmt = con.prepareStatement("DELETE FROM SDSeller WHERE  SellerID = ?");
			if(this.getSellerID() == null || this.getSellerID().equals("null")) {
				pstmt.setNull(1, 12);
			} else {
				pstmt.setString(1, this.getSellerID());
			}
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception ex) {
			// @@������
			this.mErrors.copyAllErrors(db.mErrors);
			CError tError = new CError();
			tError.moduleName = "SDSellerDB";
			tError.functionName = "delete()";
			tError.errorMessage = ex.toString();
			this.mErrors .addOneError(tError);

		// only for debug purpose
		SQLString sqlObj = new SQLString("SDSeller");
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
		SQLString sqlObj = new SQLString("SDSeller");
		sqlObj.setSQL(2, this);
		sqlObj.getSQL();

		try
		{
			pstmt = con.prepareStatement("UPDATE SDSeller SET  SellerID = ? , Password = ? , SellerName = ? , OrgName = ? , Email = ? , Phone = ? , AuthFlag = ? , XY = ? , ShotOrgName = ? , FoundDate = ? , ServiceType = ? , Address = ? , ServiceFeature = ? , OrgPeople = ? , TelPhone = ? , WebPage = ? , LinkPerson = ? , FaxNo = ? , RidingRoute = ? , Remark = ? , OrgBrief = ? , RegDate = ? , RegTime = ? , LastLoginDate = ? , LastLoginTime = ? , PassModDate = ? , PassModTime = ? , LastPass = ? , R1 = ? , R2 = ? , R3 = ? , R4 = ? , R5 = ? , R6 = ? , R7 = ? , R8 = ? , R9 = ? , R10 = ? , R11 = ? , R12 = ? , R13 = ? , R14 = ? , R15 = ? WHERE  SellerID = ?");
			if(this.getSellerID() == null || this.getSellerID().equals("null")) {
				pstmt.setNull(1, 12);
			} else {
				pstmt.setString(1, this.getSellerID());
			}
			if(this.getPassword() == null || this.getPassword().equals("null")) {
				pstmt.setNull(2, 12);
			} else {
				pstmt.setString(2, this.getPassword());
			}
			if(this.getSellerName() == null || this.getSellerName().equals("null")) {
				pstmt.setNull(3, 12);
			} else {
				pstmt.setString(3, this.getSellerName());
			}
			if(this.getOrgName() == null || this.getOrgName().equals("null")) {
				pstmt.setNull(4, 12);
			} else {
				pstmt.setString(4, this.getOrgName());
			}
			if(this.getEmail() == null || this.getEmail().equals("null")) {
				pstmt.setNull(5, 12);
			} else {
				pstmt.setString(5, this.getEmail());
			}
			if(this.getPhone() == null || this.getPhone().equals("null")) {
				pstmt.setNull(6, 12);
			} else {
				pstmt.setString(6, this.getPhone());
			}
			if(this.getAuthFlag() == null || this.getAuthFlag().equals("null")) {
				pstmt.setNull(7, 12);
			} else {
				pstmt.setString(7, this.getAuthFlag());
			}
			if(this.getXY() == null || this.getXY().equals("null")) {
				pstmt.setNull(8, 12);
			} else {
				pstmt.setString(8, this.getXY());
			}
			if(this.getShotOrgName() == null || this.getShotOrgName().equals("null")) {
				pstmt.setNull(9, 12);
			} else {
				pstmt.setString(9, this.getShotOrgName());
			}
			if(this.getFoundDate() == null || this.getFoundDate().equals("null")) {
				pstmt.setNull(10, 12);
			} else {
				pstmt.setString(10, this.getFoundDate());
			}
			if(this.getServiceType() == null || this.getServiceType().equals("null")) {
				pstmt.setNull(11, 12);
			} else {
				pstmt.setString(11, this.getServiceType());
			}
			if(this.getAddress() == null || this.getAddress().equals("null")) {
				pstmt.setNull(12, 12);
			} else {
				pstmt.setString(12, this.getAddress());
			}
			if(this.getServiceFeature() == null || this.getServiceFeature().equals("null")) {
				pstmt.setNull(13, 12);
			} else {
				pstmt.setString(13, this.getServiceFeature());
			}
			if(this.getOrgPeople() == null || this.getOrgPeople().equals("null")) {
				pstmt.setNull(14, 12);
			} else {
				pstmt.setString(14, this.getOrgPeople());
			}
			if(this.getTelPhone() == null || this.getTelPhone().equals("null")) {
				pstmt.setNull(15, 12);
			} else {
				pstmt.setString(15, this.getTelPhone());
			}
			if(this.getWebPage() == null || this.getWebPage().equals("null")) {
				pstmt.setNull(16, 12);
			} else {
				pstmt.setString(16, this.getWebPage());
			}
			if(this.getLinkPerson() == null || this.getLinkPerson().equals("null")) {
				pstmt.setNull(17, 12);
			} else {
				pstmt.setString(17, this.getLinkPerson());
			}
			if(this.getFaxNo() == null || this.getFaxNo().equals("null")) {
				pstmt.setNull(18, 12);
			} else {
				pstmt.setString(18, this.getFaxNo());
			}
			if(this.getRidingRoute() == null || this.getRidingRoute().equals("null")) {
				pstmt.setNull(19, 12);
			} else {
				pstmt.setString(19, this.getRidingRoute());
			}
			if(this.getRemark() == null || this.getRemark().equals("null")) {
				pstmt.setNull(20, 12);
			} else {
				pstmt.setString(20, this.getRemark());
			}
			if(this.getOrgBrief() == null || this.getOrgBrief().equals("null")) {
				pstmt.setNull(21, 12);
			} else {
				pstmt.setString(21, this.getOrgBrief());
			}
			if(this.getRegDate() == null || this.getRegDate().equals("null")) {
				pstmt.setNull(22, 12);
			} else {
				pstmt.setString(22, this.getRegDate());
			}
			if(this.getRegTime() == null || this.getRegTime().equals("null")) {
				pstmt.setNull(23, 12);
			} else {
				pstmt.setString(23, this.getRegTime());
			}
			if(this.getLastLoginDate() == null || this.getLastLoginDate().equals("null")) {
				pstmt.setNull(24, 12);
			} else {
				pstmt.setString(24, this.getLastLoginDate());
			}
			if(this.getLastLoginTime() == null || this.getLastLoginTime().equals("null")) {
				pstmt.setNull(25, 12);
			} else {
				pstmt.setString(25, this.getLastLoginTime());
			}
			if(this.getPassModDate() == null || this.getPassModDate().equals("null")) {
				pstmt.setNull(26, 12);
			} else {
				pstmt.setString(26, this.getPassModDate());
			}
			if(this.getPassModTime() == null || this.getPassModTime().equals("null")) {
				pstmt.setNull(27, 12);
			} else {
				pstmt.setString(27, this.getPassModTime());
			}
			if(this.getLastPass() == null || this.getLastPass().equals("null")) {
				pstmt.setNull(28, 12);
			} else {
				pstmt.setString(28, this.getLastPass());
			}
			if(this.getR1() == null || this.getR1().equals("null")) {
				pstmt.setNull(29, 12);
			} else {
				pstmt.setString(29, this.getR1());
			}
			if(this.getR2() == null || this.getR2().equals("null")) {
				pstmt.setNull(30, 12);
			} else {
				pstmt.setString(30, this.getR2());
			}
			if(this.getR3() == null || this.getR3().equals("null")) {
				pstmt.setNull(31, 12);
			} else {
				pstmt.setString(31, this.getR3());
			}
			if(this.getR4() == null || this.getR4().equals("null")) {
				pstmt.setNull(32, 12);
			} else {
				pstmt.setString(32, this.getR4());
			}
			if(this.getR5() == null || this.getR5().equals("null")) {
				pstmt.setNull(33, 12);
			} else {
				pstmt.setString(33, this.getR5());
			}
			if(this.getR6() == null || this.getR6().equals("null")) {
				pstmt.setNull(34, 12);
			} else {
				pstmt.setString(34, this.getR6());
			}
			if(this.getR7() == null || this.getR7().equals("null")) {
				pstmt.setNull(35, 12);
			} else {
				pstmt.setString(35, this.getR7());
			}
			if(this.getR8() == null || this.getR8().equals("null")) {
				pstmt.setNull(36, 12);
			} else {
				pstmt.setString(36, this.getR8());
			}
			if(this.getR9() == null || this.getR9().equals("null")) {
				pstmt.setNull(37, 12);
			} else {
				pstmt.setString(37, this.getR9());
			}
			if(this.getR10() == null || this.getR10().equals("null")) {
				pstmt.setNull(38, 12);
			} else {
				pstmt.setString(38, this.getR10());
			}
			if(this.getR11() == null || this.getR11().equals("null")) {
				pstmt.setNull(39, 12);
			} else {
				pstmt.setString(39, this.getR11());
			}
			if(this.getR12() == null || this.getR12().equals("null")) {
				pstmt.setNull(40, 12);
			} else {
				pstmt.setString(40, this.getR12());
			}
			if(this.getR13() == null || this.getR13().equals("null")) {
				pstmt.setNull(41, 12);
			} else {
				pstmt.setString(41, this.getR13());
			}
			if(this.getR14() == null || this.getR14().equals("null")) {
				pstmt.setNull(42, 12);
			} else {
				pstmt.setString(42, this.getR14());
			}
			if(this.getR15() == null || this.getR15().equals("null")) {
				pstmt.setNull(43, 12);
			} else {
				pstmt.setString(43, this.getR15());
			}
			// set where condition
			if(this.getSellerID() == null || this.getSellerID().equals("null")) {
				pstmt.setNull(44, 12);
			} else {
				pstmt.setString(44, this.getSellerID());
			}
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception ex) {
			// @@������
			this.mErrors.copyAllErrors(db.mErrors);
			CError tError = new CError();
			tError.moduleName = "SDSellerDB";
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
		SQLString sqlObj = new SQLString("SDSeller");
		sqlObj.setSQL(1, this);
		sqlObj.getSQL();

		try
		{
			pstmt = con.prepareStatement("INSERT INTO SDSeller(SellerID ,Password ,SellerName ,OrgName ,Email ,Phone ,AuthFlag ,XY ,ShotOrgName ,FoundDate ,ServiceType ,Address ,ServiceFeature ,OrgPeople ,TelPhone ,WebPage ,LinkPerson ,FaxNo ,RidingRoute ,Remark ,OrgBrief ,RegDate ,RegTime ,LastLoginDate ,LastLoginTime ,PassModDate ,PassModTime ,LastPass ,R1 ,R2 ,R3 ,R4 ,R5 ,R6 ,R7 ,R8 ,R9 ,R10 ,R11 ,R12 ,R13 ,R14 ,R15) VALUES( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)");
			if(this.getSellerID() == null || this.getSellerID().equals("null")) {
				pstmt.setNull(1, 12);
			} else {
				pstmt.setString(1, this.getSellerID());
			}
			if(this.getPassword() == null || this.getPassword().equals("null")) {
				pstmt.setNull(2, 12);
			} else {
				pstmt.setString(2, this.getPassword());
			}
			if(this.getSellerName() == null || this.getSellerName().equals("null")) {
				pstmt.setNull(3, 12);
			} else {
				pstmt.setString(3, this.getSellerName());
			}
			if(this.getOrgName() == null || this.getOrgName().equals("null")) {
				pstmt.setNull(4, 12);
			} else {
				pstmt.setString(4, this.getOrgName());
			}
			if(this.getEmail() == null || this.getEmail().equals("null")) {
				pstmt.setNull(5, 12);
			} else {
				pstmt.setString(5, this.getEmail());
			}
			if(this.getPhone() == null || this.getPhone().equals("null")) {
				pstmt.setNull(6, 12);
			} else {
				pstmt.setString(6, this.getPhone());
			}
			if(this.getAuthFlag() == null || this.getAuthFlag().equals("null")) {
				pstmt.setNull(7, 12);
			} else {
				pstmt.setString(7, this.getAuthFlag());
			}
			if(this.getXY() == null || this.getXY().equals("null")) {
				pstmt.setNull(8, 12);
			} else {
				pstmt.setString(8, this.getXY());
			}
			if(this.getShotOrgName() == null || this.getShotOrgName().equals("null")) {
				pstmt.setNull(9, 12);
			} else {
				pstmt.setString(9, this.getShotOrgName());
			}
			if(this.getFoundDate() == null || this.getFoundDate().equals("null")) {
				pstmt.setNull(10, 12);
			} else {
				pstmt.setString(10, this.getFoundDate());
			}
			if(this.getServiceType() == null || this.getServiceType().equals("null")) {
				pstmt.setNull(11, 12);
			} else {
				pstmt.setString(11, this.getServiceType());
			}
			if(this.getAddress() == null || this.getAddress().equals("null")) {
				pstmt.setNull(12, 12);
			} else {
				pstmt.setString(12, this.getAddress());
			}
			if(this.getServiceFeature() == null || this.getServiceFeature().equals("null")) {
				pstmt.setNull(13, 12);
			} else {
				pstmt.setString(13, this.getServiceFeature());
			}
			if(this.getOrgPeople() == null || this.getOrgPeople().equals("null")) {
				pstmt.setNull(14, 12);
			} else {
				pstmt.setString(14, this.getOrgPeople());
			}
			if(this.getTelPhone() == null || this.getTelPhone().equals("null")) {
				pstmt.setNull(15, 12);
			} else {
				pstmt.setString(15, this.getTelPhone());
			}
			if(this.getWebPage() == null || this.getWebPage().equals("null")) {
				pstmt.setNull(16, 12);
			} else {
				pstmt.setString(16, this.getWebPage());
			}
			if(this.getLinkPerson() == null || this.getLinkPerson().equals("null")) {
				pstmt.setNull(17, 12);
			} else {
				pstmt.setString(17, this.getLinkPerson());
			}
			if(this.getFaxNo() == null || this.getFaxNo().equals("null")) {
				pstmt.setNull(18, 12);
			} else {
				pstmt.setString(18, this.getFaxNo());
			}
			if(this.getRidingRoute() == null || this.getRidingRoute().equals("null")) {
				pstmt.setNull(19, 12);
			} else {
				pstmt.setString(19, this.getRidingRoute());
			}
			if(this.getRemark() == null || this.getRemark().equals("null")) {
				pstmt.setNull(20, 12);
			} else {
				pstmt.setString(20, this.getRemark());
			}
			if(this.getOrgBrief() == null || this.getOrgBrief().equals("null")) {
				pstmt.setNull(21, 12);
			} else {
				pstmt.setString(21, this.getOrgBrief());
			}
			if(this.getRegDate() == null || this.getRegDate().equals("null")) {
				pstmt.setNull(22, 12);
			} else {
				pstmt.setString(22, this.getRegDate());
			}
			if(this.getRegTime() == null || this.getRegTime().equals("null")) {
				pstmt.setNull(23, 12);
			} else {
				pstmt.setString(23, this.getRegTime());
			}
			if(this.getLastLoginDate() == null || this.getLastLoginDate().equals("null")) {
				pstmt.setNull(24, 12);
			} else {
				pstmt.setString(24, this.getLastLoginDate());
			}
			if(this.getLastLoginTime() == null || this.getLastLoginTime().equals("null")) {
				pstmt.setNull(25, 12);
			} else {
				pstmt.setString(25, this.getLastLoginTime());
			}
			if(this.getPassModDate() == null || this.getPassModDate().equals("null")) {
				pstmt.setNull(26, 12);
			} else {
				pstmt.setString(26, this.getPassModDate());
			}
			if(this.getPassModTime() == null || this.getPassModTime().equals("null")) {
				pstmt.setNull(27, 12);
			} else {
				pstmt.setString(27, this.getPassModTime());
			}
			if(this.getLastPass() == null || this.getLastPass().equals("null")) {
				pstmt.setNull(28, 12);
			} else {
				pstmt.setString(28, this.getLastPass());
			}
			if(this.getR1() == null || this.getR1().equals("null")) {
				pstmt.setNull(29, 12);
			} else {
				pstmt.setString(29, this.getR1());
			}
			if(this.getR2() == null || this.getR2().equals("null")) {
				pstmt.setNull(30, 12);
			} else {
				pstmt.setString(30, this.getR2());
			}
			if(this.getR3() == null || this.getR3().equals("null")) {
				pstmt.setNull(31, 12);
			} else {
				pstmt.setString(31, this.getR3());
			}
			if(this.getR4() == null || this.getR4().equals("null")) {
				pstmt.setNull(32, 12);
			} else {
				pstmt.setString(32, this.getR4());
			}
			if(this.getR5() == null || this.getR5().equals("null")) {
				pstmt.setNull(33, 12);
			} else {
				pstmt.setString(33, this.getR5());
			}
			if(this.getR6() == null || this.getR6().equals("null")) {
				pstmt.setNull(34, 12);
			} else {
				pstmt.setString(34, this.getR6());
			}
			if(this.getR7() == null || this.getR7().equals("null")) {
				pstmt.setNull(35, 12);
			} else {
				pstmt.setString(35, this.getR7());
			}
			if(this.getR8() == null || this.getR8().equals("null")) {
				pstmt.setNull(36, 12);
			} else {
				pstmt.setString(36, this.getR8());
			}
			if(this.getR9() == null || this.getR9().equals("null")) {
				pstmt.setNull(37, 12);
			} else {
				pstmt.setString(37, this.getR9());
			}
			if(this.getR10() == null || this.getR10().equals("null")) {
				pstmt.setNull(38, 12);
			} else {
				pstmt.setString(38, this.getR10());
			}
			if(this.getR11() == null || this.getR11().equals("null")) {
				pstmt.setNull(39, 12);
			} else {
				pstmt.setString(39, this.getR11());
			}
			if(this.getR12() == null || this.getR12().equals("null")) {
				pstmt.setNull(40, 12);
			} else {
				pstmt.setString(40, this.getR12());
			}
			if(this.getR13() == null || this.getR13().equals("null")) {
				pstmt.setNull(41, 12);
			} else {
				pstmt.setString(41, this.getR13());
			}
			if(this.getR14() == null || this.getR14().equals("null")) {
				pstmt.setNull(42, 12);
			} else {
				pstmt.setString(42, this.getR14());
			}
			if(this.getR15() == null || this.getR15().equals("null")) {
				pstmt.setNull(43, 12);
			} else {
				pstmt.setString(43, this.getR15());
			}
			// execute sql
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception ex) {
			// @@������
			this.mErrors.copyAllErrors(db.mErrors);
			CError tError = new CError();
			tError.moduleName = "SDSellerDB";
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
			pstmt = con.prepareStatement("SELECT * FROM SDSeller WHERE  SellerID = ?", 
				ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
			if(this.getSellerID() == null || this.getSellerID().equals("null")) {
				pstmt.setNull(1, 12);
			} else {
				pstmt.setString(1, this.getSellerID());
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
					tError.moduleName = "SDSellerDB";
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
			tError.moduleName = "SDSellerDB";
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

	public SDSellerSet query()
	{
		Statement stmt = null;
		ResultSet rs = null;
		SDSellerSet aSDSellerSet = new SDSellerSet();

	  if( !mflag ) {
		  con = DBConnPool.getConnection();
		}

		try
		{
			stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
			SQLString sqlObj = new SQLString("SDSeller");
			SDSellerSchema aSchema = this.getSchema();
			sqlObj.setSQL(5,aSchema);
			String sql = sqlObj.getSQL();

			rs = stmt.executeQuery(sql);
			int i = 0;
			while (rs.next())
			{
				i++;
				SDSellerSchema s1 = new SDSellerSchema();
				s1.setSchema(rs,i);
				aSDSellerSet.add(s1);
			}
			try{ rs.close(); } catch( Exception ex ) {}
			try{ stmt.close(); } catch( Exception ex1 ) {}
		}
		catch(Exception e)
	    {
			// @@������
			CError tError = new CError();
			tError.moduleName = "SDSellerDB";
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

		return aSDSellerSet;
	}

	public SDSellerSet executeQuery(String sql)
	{
		Statement stmt = null;
		ResultSet rs = null;
		SDSellerSet aSDSellerSet = new SDSellerSet();

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
				SDSellerSchema s1 = new SDSellerSchema();
				if (!s1.setSchema(rs,i))
				{
					// @@������
					CError tError = new CError();
					tError.moduleName = "SDSellerDB";
					tError.functionName = "executeQuery";
					tError.errorMessage = "sql���������鿴�������ֶ�����Ϣ!";
					this.mErrors .addOneError(tError);
				}
				aSDSellerSet.add(s1);
			}
			try{ rs.close(); } catch( Exception ex ) {}
			try{ stmt.close(); } catch( Exception ex1 ) {}
		}
		catch(Exception e)
	    {
			// @@������
			CError tError = new CError();
			tError.moduleName = "SDSellerDB";
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

		return aSDSellerSet;
	}

	public SDSellerSet query(int nStart, int nCount)
	{
		Statement stmt = null;
		ResultSet rs = null;
		SDSellerSet aSDSellerSet = new SDSellerSet();

	  if( !mflag ) {
		  con = DBConnPool.getConnection();
		}

		try
		{
			stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
			SQLString sqlObj = new SQLString("SDSeller");
			SDSellerSchema aSchema = this.getSchema();
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

				SDSellerSchema s1 = new SDSellerSchema();
				s1.setSchema(rs,i);
				aSDSellerSet.add(s1);
			}
			try{ rs.close(); } catch( Exception ex ) {}
			try{ stmt.close(); } catch( Exception ex1 ) {}
		}
		catch(Exception e)
	    {
			// @@������
			CError tError = new CError();
			tError.moduleName = "SDSellerDB";
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

		return aSDSellerSet;
	}

	public SDSellerSet executeQuery(String sql, int nStart, int nCount)
	{
		Statement stmt = null;
		ResultSet rs = null;
		SDSellerSet aSDSellerSet = new SDSellerSet();

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

				SDSellerSchema s1 = new SDSellerSchema();
				if (!s1.setSchema(rs,i))
				{
					// @@������
					CError tError = new CError();
					tError.moduleName = "SDSellerDB";
					tError.functionName = "executeQuery";
					tError.errorMessage = "sql���������鿴�������ֶ�����Ϣ!";
					this.mErrors .addOneError(tError);
				}
				aSDSellerSet.add(s1);
			}
			try{ rs.close(); } catch( Exception ex ) {}
			try{ stmt.close(); } catch( Exception ex1 ) {}
		}
		catch(Exception e)
	    {
			// @@������
			CError tError = new CError();
			tError.moduleName = "SDSellerDB";
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

		return aSDSellerSet;
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
			SQLString sqlObj = new SQLString("SDSeller");
			SDSellerSchema aSchema = this.getSchema();
			sqlObj.setSQL(2,aSchema);
			String sql = "update SDSeller " + sqlObj.getUpdPart() + " where " + strWherePart;

			int operCount = stmt.executeUpdate(sql);
			if( operCount == 0 )
			{
				// @@������
				CError tError = new CError();
				tError.moduleName = "SDSellerDB";
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
			tError.moduleName = "SDSellerDB";
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
        tError.moduleName = "SDSellerDB";
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
        tError.moduleName = "SDSellerDB";
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
        tError.moduleName = "SDSellerDB";
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
        tError.moduleName = "SDSellerDB";
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
 * @return SDSellerSet
 */
public SDSellerSet getData()
{
    int tCount = 0;
    SDSellerSet tSDSellerSet = new SDSellerSet();
    SDSellerSchema tSDSellerSchema = null;
    if (null == mResultSet)
    {
        CError tError = new CError();
        tError.moduleName = "SDSellerDB";
        tError.functionName = "getData";
        tError.errorMessage = "���ݼ�Ϊ�գ�����׼�����ݼ���";
        this.mErrors.addOneError(tError);
        return null;
    }
    try
    {
        tCount = 1;
        tSDSellerSchema = new SDSellerSchema();
        tSDSellerSchema.setSchema(mResultSet, 1);
        tSDSellerSet.add(tSDSellerSchema);
        //ע��mResultSet.next()������
        while (tCount++ < SysConst.FETCHCOUNT)
        {
            if (mResultSet.next())
            {
                tSDSellerSchema = new SDSellerSchema();
                tSDSellerSchema.setSchema(mResultSet, 1);
                tSDSellerSet.add(tSDSellerSchema);
            }
        }
    }
    catch (Exception ex)
    {
        CError tError = new CError();
        tError.moduleName = "SDSellerDB";
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
    return tSDSellerSet;
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
            tError.moduleName = "SDSellerDB";
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
        tError.moduleName = "SDSellerDB";
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
            tError.moduleName = "SDSellerDB";
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
        tError.moduleName = "SDSellerDB";
        tError.functionName = "closeData";
        tError.errorMessage = ex3.toString();
        this.mErrors.addOneError(tError);
        flag = false;
    }
    return flag;
}
}
