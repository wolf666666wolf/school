package com.sinosoft.service;

import com.sinosoft.service.exception.ServiceException;


/**
 * <p>Title: Sql����</p>
 *
 * <p>Description: ת�����</p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: sinosoft</p>
 *
 * @author litao
 * @version 1.0
 */

public class SqlParse {
	/**SQLINFO�ָ��*/
	public static final String SQLINFO = ";";
	/**SQL PARA�ָ��*/
	public static final String SQLPAEA = ":";
	/**��SQL ��־��*/
	public static final String SQLSUBPAEA = "*";
	
	public static final String SQLSHARP = "#";
	 
	
	/**
	 * ����SQL
	 * */
	public String parseSQL(String mSQL)
	{
		if ((mSQL == null) || mSQL.trim().equals("")) {
			throw new ServiceException("����SQL��Ϣ����");
		}
		String[] tSql = mSQL.split(SQLINFO);
		if (tSql.length < 2) {
			throw new ServiceException("����SQL��Ϣ����");
		}
		String[] tPara = tSql[2].split(SQLPAEA);
		for (int i = 0; i < tPara.length; i++) {
			tPara[i] = dealSubSql(tPara[i], tSql[0], tSql[1] + "_" + i);
		}
		mSQL = SqlMessage.getSqlMessage().getSql(tSql[0], tSql[1], tPara);
		mSQL = getTranslatedSQL(mSQL); 
		return mSQL;
	}

	/**
	 * notes added by zhouwh@sinosoft.com.cn 20101029
	 * ����SQL
	 * @param mSQL
	 * @return
	 */
	private String getTranslatedSQL(String mSQL){
		String tSQL ="";
		int sharpindex=mSQL.indexOf(SQLSHARP);
		while(sharpindex!=-1){
			tSQL=tSQL+mSQL.substring(0,sharpindex);
			mSQL=mSQL.substring(sharpindex+1,mSQL.length());
			int tempindex=mSQL.indexOf(SQLSHARP);
			if(tempindex!=-1){
//				CodeQueryBL codequerybl = new CodeQueryBL();
				//��ȡSQL�е���Դ�ļ�id��������codequerybl���з��봦�� 
//				String tResourceId =codequerybl.transI18NMenu(mSQL.substring(0,tempindex)); 
//				tSQL = tSQL+tResourceId;
				mSQL=mSQL.substring(tempindex+1,mSQL.length());
			}
			sharpindex=mSQL.indexOf(SQLSHARP);			 
		}
		return tSQL+mSQL;
	}
	/**��ȡ������Ϣ,������SQL����*/
	private String dealSubSql(String tStr,String resource,String sqlID)
	{
		if(tStr==null||tStr.equals(""))
		{
			return "";
		}
		if(tStr.startsWith(SQLSUBPAEA))
		{
			String ts = tStr.substring(1);
			if(ts==null||ts.equals(""))
			{
				return "";
			}
			else
			{
				 return SqlMessage.getSqlMessage().getSql(resource,sqlID,new String[] {ts});
			}
		}
		else
		{
			return tStr;
		}
	}
}
