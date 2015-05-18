/**
 * Copyright (c) 2002 sinosoft  Co. Ltd.
 * All right reserved.
 */

package com.sinosoft.lis.vschema;

import com.sinosoft.lis.schema.SDSellerFollowSchema;
import com.sinosoft.utility.*;

/*
 * <p>ClassName: SDSellerFollowSet </p>
 * <p>Description: SDSellerFollowSchemaSet���ļ� </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: sinosoft </p>
 * @Database: PhysicalDataModel_1
 */
public class SDSellerFollowSet extends SchemaSet
{
	// @Method
	public boolean add(SDSellerFollowSchema aSchema)
	{
		return super.add(aSchema);
	}

	public boolean add(SDSellerFollowSet aSet)
	{
		return super.add(aSet);
	}

	public boolean remove(SDSellerFollowSchema aSchema)
	{
		return super.remove(aSchema);
	}

	public SDSellerFollowSchema get(int index)
	{
		SDSellerFollowSchema tSchema = (SDSellerFollowSchema)super.getObj(index);
		return tSchema;
	}

	public boolean set(int index, SDSellerFollowSchema aSchema)
	{
		return super.set(index,aSchema);
	}

	public boolean set(SDSellerFollowSet aSet)
	{
		return super.set(aSet);
	}

	/**
	* ���ݴ������ XML ��ʽ�����˳��μ�<A href ={@docRoot}/dataStructure/tb.html#PrpSDSellerFollow����/A>���ֶ�
	* @return: String ���ش�����ַ���
	**/
	public String encode()
	{
		StringBuffer strReturn = new StringBuffer("");
		int n = this.size();
		for (int i = 1; i <= n; i++)
		{
			SDSellerFollowSchema aSchema = this.get(i);
			strReturn.append(aSchema.encode());
			if( i != n ) strReturn.append(SysConst.RECORDSPLITER);
		}

		return strReturn.toString();
	}

	/**
	* ���ݽ��
	* @param: str String ������ַ���
	* @return: boolean
	**/
	public boolean decode( String str )
	{
		int nBeginPos = 0;
		int nEndPos = str.indexOf('^');
		this.clear();

		while( nEndPos != -1 )
		{
			SDSellerFollowSchema aSchema = new SDSellerFollowSchema();
			if(aSchema.decode(str.substring(nBeginPos, nEndPos)))
			{
			this.add(aSchema);
			nBeginPos = nEndPos + 1;
			nEndPos = str.indexOf('^', nEndPos + 1);
			}
			else
			{
				// @@������
				this.mErrors.copyAllErrors( aSchema.mErrors );
				return false;
			}
		}
		SDSellerFollowSchema tSchema = new SDSellerFollowSchema();
		if(tSchema.decode(str.substring(nBeginPos)))
		{
		this.add(tSchema);
		return true;
		}
		else
		{
			// @@������
			this.mErrors.copyAllErrors( tSchema.mErrors );
			return false;
		}
	}

}
