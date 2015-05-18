package com.sinosoft.lis.userMan;

import com.sinosoft.lis.db.LDUserDB;
import com.sinosoft.lis.encrypt.LisIDEA;
import com.sinosoft.lis.pubfun.PubFun;
import com.sinosoft.lis.schema.LDUserSchema;
import com.sinosoft.lis.vschema.LDUserSet;
import com.sinosoft.lis.vschema.LDUserTOMenuGrpSet;
import com.sinosoft.utility.CError;
import com.sinosoft.utility.CErrors;
import com.sinosoft.utility.VData;

public class LDUserManBL
{
    /** 错误处理类，每个需要错误处理的类中都放置该类 */
    public CErrors mErrors = new CErrors();
    /** 往后面传输数据的容器 */
    private VData mResult = new VData();
    private VData mInputData = new VData();
    /** 数据操作字符串 */
    private String mOperate;
//    private String mDeletor;

    /** 业务处理相关变量 */
    /** 用户的相关信息*/
    LDUserSchema mLDUserSchema = new LDUserSchema();
    LDUserTOMenuGrpSet mLDUserToMenuGrpSet = new LDUserTOMenuGrpSet();
    String mOperator; //指示进行本操作的操作员

    String mResultStr = "";
    int mResultNum = 0;

    public LDUserManBL()
    {
        // just for debug
    }

    /**
     * 传输数据的公共方法
     * @param cInputData VData
     * @param cOperate String
     * @return boolean
     */
    public boolean submitData(VData cInputData, String cOperate)
    {

        // 判断操作是不是查询
        if (cOperate.compareTo("query") != 0
            && cOperate.compareTo("insert") != 0
            && cOperate.compareTo("update") != 0
            && cOperate.compareTo("delete") != 0)
        {
//            System.out.println("Operate is not permitted");
            return false;
        }

//        System.out.println("start BL submit...");

        //将操作数据拷贝到本类中
        this.mOperate = cOperate;

        //得到外部传入的数据,将数据备份到本类中
        if (!getInputData(cInputData))
        {
            return false;
        }
//        System.out.println("---getInputData---");

        if (mOperate.equals("query"))
        {
            if (!queryData())
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        //进行业务处理
        if (!dealData())
        {
            return false;
        }
//        System.out.println("After dealData！");
        //准备往后台的数据
        if (!prepareOutputData())
        {
            return false;
        }
//        System.out.println("After prepareOutputData");

//        System.out.println("Start LDUser BL Submit...");

        LDUserManBLS tLDUserManBLS = new LDUserManBLS();

//        System.out.println("tLDuserManBLs.submit...");
//        String str = (String) mInputData.getObjectByObjectName("String", 0);
//        System.out.println("deletor is " + str);
        tLDUserManBLS.submitData(mInputData, cOperate);

//        System.out.println("End LDUserMan BL Submit...");

        //如果有需要处理的错误，则返回
        if (tLDUserManBLS.mErrors.needDealError())
        {
            this.mErrors.copyAllErrors(tLDUserManBLS.mErrors);
            return false;
        }
        mInputData = null;
        return true;
    }

    private static boolean dealData()
    {
        return true;
    }

    /**
     * 查询
     * @return boolean
     */
    private boolean queryData()
    {
        String usercode = mLDUserSchema.getUserCode();
//        System.out.println(usercode.length());
        String username = mLDUserSchema.getUserName();
        String comcode = mLDUserSchema.getComCode();
        String agentcom = mLDUserSchema.getAgentCom();
        LDUserDB tLDUserDB = new LDUserDB();
        LDUserSet tLDUserSet = new LDUserSet();
        String strSql, wherePart = " where 1 = 1 and usercode<>'001' ";
//and usercode<>'001'
        if ((usercode != null) && (!usercode.trim().equals("")))
        {
            wherePart = wherePart + " and  usercode = '" + usercode + "'";
//            System.out.println(wherePart);
        }

        if ((username != null) && (!username.trim().equals("")))
        {
            wherePart = wherePart + " and  username = '" + username + "'";
        }



        strSql = "select * from lduser " + wherePart;
//        System.out.println(strSql);
        tLDUserSet = tLDUserDB.executeQuery(strSql);

//        System.out.println("tLDUserSet size :" + tLDUserSet.size());

        if (tLDUserSet == null)
        {
            return false;
        }

        LisIDEA tIdea = new LisIDEA();
        for (int i = 1; i <= tLDUserSet.size(); i++)
        {
            String encryptPwd = tLDUserSet.get(i).getPassword();

            if (encryptPwd.length() != 16)
            {
                continue;
            }

            String decryptPwd = tIdea.decryptString(encryptPwd);
//            System.out.println(encryptPwd);
//            System.out.println(decryptPwd);
            tLDUserSet.get(i).setPassword(decryptPwd);
        }

        String tReturn = tLDUserSet.encode();
        tReturn = "0|" + tLDUserSet.size() + "^" + tReturn;
//        System.out.println("------------****" + tReturn);
        mResult.clear();
        mResult.add(tReturn);
        return true;
    }

    public VData getResult()
    {
        return mResult;
    }

    public int getResultNum()
    {
        return mResultNum;
    }

    public String getResultStr()
    {
        String resultStr = "";
//        for (int i = 1; i <= mResultNum; i++)
//        {
//
//        }
        return resultStr;
    }


    /**
     * 从输入数据中得到所有对象
     * 输出：如果没有得到足够的业务数据对象，则返回false,否则返回true
     * @param cInputData VData
     * @return boolean
     */
    private boolean getInputData(VData cInputData)
    {
        //得到增加更新时的操作员或删除时的当前操作员
        mOperator = (String) cInputData.getObjectByObjectName("String", 0);

        // 检验查询条件
        mLDUserSchema = (LDUserSchema) cInputData.getObjectByObjectName(
                "LDUserSchema", 0);

        if (mLDUserSchema == null)
        {
//            System.out.println("cant get userschema");
            return false;
        }

        String curDate = PubFun.getCurrentDate();
        String curTime = PubFun.getCurrentTime();

        if (mOperate.compareTo("insert") == 0)
        {
//            System.out.println("insert operate");
            mLDUserSchema.setMakeTime(curTime);
            mLDUserSchema.setMakeDate(curDate);
        }
//        System.out.println("come here");
//        System.out.println("password is:" + mLDUserSchema.getPassword());
        // decrypt password if possible

//      System.out.println("comeHere");

        mLDUserToMenuGrpSet = (LDUserTOMenuGrpSet) cInputData.
                              getObjectByObjectName("LDUserTOMenuGrpSet", 0);
//        System.out.println("completed get input data");
        return true;
    }

    /**
     * 准备往后层输出所需要的数据
     * 输出：如果准备数据时发生错误则返回false,否则返回true
     * @return boolean
     */
    private boolean prepareOutputData()
    {
        mResult.clear();
        mInputData.clear();
        try
        {
            mInputData.add(mOperator);
            mInputData.add(mLDUserSchema);
            mInputData.add(mLDUserToMenuGrpSet);
//            System.out.println("prepareOutput deletor : " + mOperator);
//            String str = (String) mInputData.getObjectByObjectName("String", 0);
//            System.out.println("deletor is : " + str);
        }
        catch (Exception ex)
        {
            // @@错误处理
//            System.out.println("BL excetion happend");

            CError tError = new CError();
            tError.moduleName = "MenuQueryBL";
            tError.functionName = "prepareOutputData";
            tError.errorMessage = "在准备往后层处理所需要的数据时出错。";
            this.mErrors.addOneError(tError);
            return false;
        }
        return true;
    }
}
