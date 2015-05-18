package com.sinosoft.lis.pubfun;

import java.util.ArrayList;
import java.util.Hashtable;

import com.sinosoft.utility.ExeSQL;
import com.sinosoft.utility.SSRS;



public class QSUI
{
	public String ResultMsg = "";
	public QSUI()
	{

	}
	
	public boolean SubmitData(ArrayList arr) 
	{
		//System.out.println("here____");
		Hashtable ht = new Hashtable();
		ht = (Hashtable)arr.get ( 0 );
		String p = (String)ht.get ( "p" );
		String SQL = GetParamByKey ( p , "SQL" );
		String ind = GetParamByKey ( p , "ind" );
		String ar = GetParamByKey ( p , "ar" );
		String len = GetParamByKey ( p , "len" );
		String o = GetParamByKey ( p , "o" );
		SSRS rs = new SSRS();
		ExeSQL es = new ExeSQL();
		rs=es.execSQL(SQL);
		ResultMsg+=rs.getMaxRow();
		ResultMsg+="^";
		for(int i=1;i<=rs.getMaxRow();i++)
		{
			for(int j=1;j<=rs.getMaxCol();j++)
			{
				ResultMsg+=rs.GetText(i, j);
				if(j<=rs.getMaxCol()-1)
				{
					ResultMsg+="`";
				}
			}
			if(i<=rs.getMaxRow()-1)
			{
				ResultMsg+="|";
			}
		}
		ResultMsg+="^";
		ResultMsg+=ind;

		ResultMsg+="^";
		ResultMsg+=ar;

		ResultMsg+="^";
		ResultMsg+=len;

		ResultMsg+="^";
		ResultMsg+=o;
		System.out.println("here____"+ResultMsg);
		//ResultMsg = "哈哈哈";
		return true;
	}
	public String GetParamByKey (String p,String key)
	{
		String tReturn = "";
		if(p!=null&&key!=null&&!"".equals ( p )&&!"".equals ( key ))
		{
			String[] Params = p.split ( "\\^" );
			for(int i=0;i<Params.length;i++)
			{
				String[] row = Params[i].split ( "\\=" );
				if(key.equals ( row[0] ))
				{
					tReturn = row[1];
				}
			}
		}
		
		return tReturn;
	}
	public static void main(String[] args)
	{

	}
}
