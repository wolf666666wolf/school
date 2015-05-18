/**
 * Copyright (c) 2002 sinosoft  Co. Ltd.
 * All right reserved.
 */

package com.sinosoft.lis.vschema;

import com.sinosoft.lis.schema.SDVoteItemSchema;
import com.sinosoft.utility.*;

/*
 * <p>ClassName: SDVoteItemSet </p>
 * <p>Description: SDVoteItemSchemaSet���ļ� </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: sinosoft </p>
 * @Database: PhysicalDataModel_1
 */
public class SDVoteItemSet extends SchemaSet
{
	// @Method
	public boolean add(SDVoteItemSchema aSchema)
	{
		return super.add(aSchema);
	}

	public boolean add(SDVoteItemSet aSet)
	{
		return super.add(aSet);
	}

	public boolean remove(SDVoteItemSchema aSchema)
	{
		return super.remove(aSchema);
	}

	public SDVoteItemSchema get(int index)
	{
		SDVoteItemSchema tSchema = (SDVoteItemSchema)super.getObj(index);
		return tSchema;
	}

	public boolean set(int index, SDVoteItemSchema aSchema)
	{
		return super.set(index,aSchema);
	}

	public boolean set(SDVoteItemSet aSet)
	{
		return super.set(aSet);
	}

	/**
	* ���ݴ������ XML ��ʽ�����˳��μ�<A href ={@docRoot}/dataStructure/tb.html#PrpSDVoteItem����/A>���ֶ�
	* @return: String ���ش�����ַ���
	**/
	public String encode()
	{
		StringBuffer strReturn = new StringBuffer("");
		int n = this.size();
		for (int i = 1; i <= n; i++)
		{
			SDVoteItemSchema aSchema = this.get(i);
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
			SDVoteItemSchema aSchema = new SDVoteItemSchema();
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
		SDVoteItemSchema tSchema = new SDVoteItemSchema();
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
