/**
 * Copyright (c) 2002 sinosoft  Co. Ltd.
 * All right reserved.
 */

package com.sinosoft.lis.vschema;

import com.sinosoft.lis.schema.SDAssActAtnSchema;
import com.sinosoft.utility.*;

/*
 * <p>ClassName: SDAssActAtnSet </p>
 * <p>Description: SDAssActAtnSchemaSet���ļ� </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: sinosoft </p>
 * @Database: PhysicalDataModel_1
 */
public class SDAssActAtnSet extends SchemaSet
{
	// @Method
	public boolean add(SDAssActAtnSchema aSchema)
	{
		return super.add(aSchema);
	}

	public boolean add(SDAssActAtnSet aSet)
	{
		return super.add(aSet);
	}

	public boolean remove(SDAssActAtnSchema aSchema)
	{
		return super.remove(aSchema);
	}

	public SDAssActAtnSchema get(int index)
	{
		SDAssActAtnSchema tSchema = (SDAssActAtnSchema)super.getObj(index);
		return tSchema;
	}

	public boolean set(int index, SDAssActAtnSchema aSchema)
	{
		return super.set(index,aSchema);
	}

	public boolean set(SDAssActAtnSet aSet)
	{
		return super.set(aSet);
	}

	/**
	* ���ݴ������ XML ��ʽ�����˳��μ�<A href ={@docRoot}/dataStructure/tb.html#PrpSDAssActAtn����/A>���ֶ�
	* @return: String ���ش�����ַ���
	**/
	public String encode()
	{
		StringBuffer strReturn = new StringBuffer("");
		int n = this.size();
		for (int i = 1; i <= n; i++)
		{
			SDAssActAtnSchema aSchema = this.get(i);
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
			SDAssActAtnSchema aSchema = new SDAssActAtnSchema();
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
		SDAssActAtnSchema tSchema = new SDAssActAtnSchema();
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
