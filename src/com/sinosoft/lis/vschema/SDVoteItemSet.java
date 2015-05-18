/**
 * Copyright (c) 2002 sinosoft  Co. Ltd.
 * All right reserved.
 */

package com.sinosoft.lis.vschema;

import com.sinosoft.lis.schema.SDVoteItemSchema;
import com.sinosoft.utility.*;

/*
 * <p>ClassName: SDVoteItemSet </p>
 * <p>Description: SDVoteItemSchemaSet类文件 </p>
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
	* 数据打包，按 XML 格式打包，顺序参见<A href ={@docRoot}/dataStructure/tb.html#PrpSDVoteItem描述/A>表字段
	* @return: String 返回打包后字符串
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
	* 数据解包
	* @param: str String 打包后字符串
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
				// @@错误处理
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
			// @@错误处理
			this.mErrors.copyAllErrors( tSchema.mErrors );
			return false;
		}
	}

}
