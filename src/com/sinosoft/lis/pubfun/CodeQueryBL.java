package com.sinosoft.lis.pubfun;
import com.sinosoft.lis.schema.*;
import com.sinosoft.utility.*;
import java.util.Hashtable;
import com.sinosoft.lis.log.*;

public class CodeQueryBL
{
    /** 错误处理类，每个需要错误处理的类中都放置该类 */
    public CErrors mErrors = new CErrors();

    /** 往后面传输数据的容器 */
    private VData mInputData;

    /** 往后面传输数据的容器 */
    private VData mResult = new VData();

    /** 数据操作字符串 */
    private String mOperate;


    /** 存储查询语句 */
    private String mSQL = "";
    private StringBuffer mSBql = new StringBuffer(256);

    /** 存储全局变量 */
    private GlobalInput mGlobalInput = new GlobalInput();

    /** 存储查询条件 */
    private String mCodeCondition = "";
    private String mConditionField = "";

    /** 业务处理相关变量 */
    private LDCodeSchema mLDCodeSchema = new LDCodeSchema();

//    private LDCodeSet mLDCodeSet = new LDCodeSet();
    private ExeSQL mExeSQL = new ExeSQL();

    /** 返回的数据*/
    private String mResultStr = "";

    // 表示某一个Code是否可以从缓存中取出
    private boolean m_bCanBeCached = false;
    private static Hashtable m_hashResultStr = new Hashtable();
    private static String TOO_LONG_FLAG = "Too long";

    public CodeQueryBL()
    {
    }

    /**
     * 传输数据的公共方法, 本处理没有后续的BLS层，故该方法无用
     * @param cInputData VData
     * @param cOperate String
     * @return boolean
     */
    public boolean submitData(VData cInputData, String cOperate)
    {
        //将操作数据拷贝到本类中
        mInputData = (VData) cInputData.clone();
        mOperate = cOperate;

        //得到外部传入的数据,将数据备份到本类中
        if (!getInputData())
        {
            return false;
        }

        //进行业务处理
        if (!queryData())
        {
            return false;
        }

        return true;
    }

    /**
     * 数据输出方法，供外界获取数据处理结果
     * @return 包含有数据查询结果字符串的VData对象
     */
    public VData getResult()
    {
        return mResult;
    }

    /**
     * 从输入数据中得到所有对象
     * 输出：如果没有得到足够的业务数据对象，则返回false,否则返回true
     * @return boolean
     */
    private boolean getInputData()
    {
        // 代码查询条件
        try
        {
            mLDCodeSchema.setSchema((LDCodeSchema) mInputData.
                                    getObjectByObjectName(
                                            "LDCodeSchema", 0));
            try
            {
                mGlobalInput.setSchema((GlobalInput) mInputData.
                                       getObjectByObjectName(
                                               "GlobalInput", 0));
            }
            catch (Exception e)
            {
                mGlobalInput.ComCode = "";
                mGlobalInput.ManageCom = "";
                mGlobalInput.Operator = "";
            }
            //log.addlog("GGGGGGGGGGGGGGGGG" + mGlobalInput.ManageCom);
            //log.addlog(mGlobalInput.ComCode);
            log.addlog("ManageCom : " + mGlobalInput.ManageCom);
            TransferData tTransferData = (TransferData) mInputData.
                                         getObjectByObjectName("TransferData",
                    0);
            //暂时默认为字符串类型
            //mCodeCondition = "'" + (String)tTransferData.getValueByName("codeCondition") + "'";
            mCodeCondition = (String) tTransferData.getValueByName(
                    "codeCondition");

            if (mCodeCondition == null)
            {
                this.m_bCanBeCached = true; // 没有条件列，表示Code所对应的查询语句是固定的，所以可以使用缓存。
            }

            if (mCodeCondition.indexOf('#') == -1)
            {
                mCodeCondition = "'" + mCodeCondition + "'";
            }
            else
            {
                mCodeCondition = mCodeCondition.replace('#', '\'');
            }
            mConditionField = (String) tTransferData.getValueByName(
                    "conditionField");

//            if (mConditionField.equals("")&&mCodeCondition.equals("''"))
//            {
//                mCodeCondition = "1";
//                mConditionField = "1";
//                this.m_bCanBeCached = true; // 没有条件列，表示Code所对应的查询语句是固定的，所以可以使用缓存。
//            }
        }
        catch (Exception e)
        {
            log.addlog(
                    "CodeQueryBL throw Errors at getInputData: can not get GlobalInput!");
            mCodeCondition = "1";
            mConditionField = "1";
        }
        return true;
    }

    public void setGlobalInput(GlobalInput cGlobalInput)
    {
        mGlobalInput.setSchema(cGlobalInput);
    }

    /**
     * 查询符合条件的保单
     * 输出：如果准备数据时发生错误则返回false,否则返回true
     * @return boolean
     */
    private boolean queryData()
    {
        mSQL = "";
        int executeType = 0;

        if (mGlobalInput.ManageCom == null ||
            mGlobalInput.ManageCom.trim().equals(""))
        {
            mGlobalInput.ManageCom = "86";
        }

        if (StrTool.cTrim(mLDCodeSchema.getCodeType()).toLowerCase().compareTo(
                "chnlcode") == 0)
        {
            mSQL = "select a.serialno,b.channelname||'>'||a.channelname from sdchannel a,sdchannel b where a.parentchannel=b.serialno and a.R3='3' and a.r4='Y'" +
            		" union select 'C0000','静态页面' from dual";
        }      
        
        
        if (StrTool.cTrim(mLDCodeSchema.getCodeType()).toLowerCase().compareTo(
        "proj") == 0)
		{
        	String adds = "";
        	if(!"001".equals(mGlobalInput.Operator)&&!"admin".equals(mGlobalInput.Operator))
        	{
        		adds+=" and mngr='"+mGlobalInput.Operator+"' ";
        	}
        	
		    mSQL = "select PID,name from PMProject where 1=1 "+adds+" order by PID asc ";
		}

        //toolprolist
        if (StrTool.cTrim(mLDCodeSchema.getCodeType()).toLowerCase().compareTo(
        "toolprolist") == 0)
		{
		    mSQL = "select TID,(select codename from ldcode where codetype = 'toolname' and code=name),mod," +
		    		"(select codename from ldcode where codetype = 'toolunit' and code=unit) from PMtool order by TID asc ";
		}
        if (StrTool.cTrim(mLDCodeSchema.getCodeType()).toLowerCase().compareTo(
        "matemod") == 0)
		{
		    mSQL = "select code,codename from ldcode where codetype = 'matemod' and (codealias="+mCodeCondition+" or codealias is null or codealias='') order by code asc";
		}
        if (StrTool.cTrim(mLDCodeSchema.getCodeType()).toLowerCase().compareTo(
        "matename") == 0)
		{
		    mSQL = "select code,codename,othersign,comcode from ldcode where codetype = 'matename' order by code asc";
		}
        if (StrTool.cTrim(mLDCodeSchema.getCodeType()).toLowerCase().compareTo(
        "toolname") == 0)
		{
		    mSQL = "select code,codename,othersign,comcode from ldcode where codetype = 'toolname' order by code asc";
		}
        
///////////////////////////////////////////////////////////////////////////////




        //其他LDCODE表中定义的引用
        if (mSQL.equals(""))
        {
//
            mSBql.append(
                    "select Code, CodeName, CodeAlias, ComCode, OtherSign from ldcode where ");
            mSBql.append("1");
            mSBql.append(" = ");
            mSBql.append("1");
            mSBql.append(" and codetype = '");
            mSBql.append(StrTool.cTrim(mLDCodeSchema.getCodeType()).toLowerCase());
            mSBql.append("' order by Code");

            mSQL = mSBql.toString();
        }

        log.addlog("CodeQueryBL SQL : " + mSQL);
        if (executeType == 0)
        {
            //使用截取方式查询
            mResultStr = mExeSQL.getEncodedResult(mSQL, 1);
        }
        else
        {
            // 如果不是使用截取方式查询，并且没有其它的条件列
            if (this.m_bCanBeCached == true)
            {
                // 如果SQL中包含有8621%之类的语句，表示有管理机构之类的条件，也不能使用缓存
                if (mSQL.matches(".*86.*\\%.*"))
                {
                    mResultStr = mExeSQL.getEncodedResult(mSQL);
                }
                else
                {
                    mResultStr = (String)this.m_hashResultStr.get(mSQL);

                    if (mResultStr == null)
                    {
                        mResultStr = mExeSQL.getEncodedResult(mSQL);

                        if (mResultStr.length() > 500)
                        {
                            this.m_hashResultStr.put(mSQL, TOO_LONG_FLAG);
                        }
                        else
                        {
                            this.m_hashResultStr.put(mSQL, mResultStr);
                        }
                    }
                    else if (mResultStr.equals(TOO_LONG_FLAG))
                    {
                        mResultStr = mExeSQL.getEncodedResult(mSQL);
                    }
                }
            }
            else
            {
                mResultStr = mExeSQL.getEncodedResult(mSQL);
            }

//        else if (executeType == 1)
            //全部数据查询
//            mResultStr = mExeSQL.getEncodedResult(mSQL);
        }

        if (mExeSQL.mErrors.needDealError())
        {
            // @@错误处理,在ExeSQL中已进行错误处理，这里直接返回即可。
            this.mErrors.copyAllErrors(mExeSQL.mErrors);
            //如果sql执行错误，则返回sql描述
            log.addlog("Code Query Error Sql:" + mSQL);
        }
        mResult.clear();
        mResult.add(mResultStr);
        return true;
    }

    /**
     * 测试函数
     * @param args String[]
     */
    public static void main(String[] args)
    {
//        CodeQueryBL codeQueryBL1 = new CodeQueryBL();
//        codeQueryBL1.mLDCodeSchema.setCodeType("comcode");
//        codeQueryBL1.queryData();
//        VData tVData = codeQueryBL1.getResult();
//        log.addlog(codeQueryBL1.mSQL);
//        log.addlog(tVData.get(0));
    }
}
