/**
 * Copyright (c) 2002 sinosoft  Co. Ltd.
 * All right reserved.
 */

package com.sinosoft.lis.vschema;

import com.sinosoft.lis.schema.SDVoteTopicSchema;
import com.sinosoft.utility.*;

/*
 * <p>ClassName: SDVoteTopicSet </p>
 * <p>Description: SDVoteTopicSchemaSet���ļ� </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: sinosoft </p>
 * @Database: PhysicalDataModel_1
 */
public class SDVoteTopicSet extends SchemaSet
{
	// @Method
	public boolean add(SDVoteTopicSchema aSchema)
	{
		return super.add(aSchema);
	}

	public boolean add(SDVoteTopicSet aSet)
	{
		return super.add(aSet);
	}

	public boolean remove(SDVoteTopicSchema aSchema)
	{
		return super.remove(aSchema);
	}

	public SDVoteTopicSchema get(int index)
	{
		SDVoteTopicSchema tSchema = (SDVoteTopicSchema)super.getObj(index);
		return tSchema;
	}

	public boolean set(int index, SDVoteTopicSchema aSchema)
	{
		return super.set(index,aSchema);
	}

	public boolean set(SDVoteTopicSet aSet)
	{
		return super.set(aSet);
	}

	/**
	* ���ݴ������ XML ��ʽ�����˳��μ�<A href ={@docRoot}/dataStructure/tb.html#PrpSDVoteTopic����/A>���ֶ�
	* @return: String ���ش�����ַ���
	**/
	public String encode()
	{
		StringBuffer strReturn = new StringBuffer("");
		int n = this.size();
		for (int i = 1; i <= n; i++)
		{
			SDVoteTopicSchema aSchema = this.get(i);
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
			SDVoteTopicSchema aSchema = new SDVoteTopicSchema();
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
		SDVoteTopicSchema tSchema = new SDVoteTopicSchema();
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
