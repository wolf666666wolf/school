package com.sinosoft.lis.logon;

import com.sinosoft.lis.pubfun.GlobalInput;
import com.sinosoft.utility.CErrors;
import com.sinosoft.utility.VData;

public class logoutBL
{
    /** 错误处理类，每个需要错误处理的类中都放置该类 */
    public CErrors mErrors = new CErrors();

    /** 往后面传输数据的容器 */
    private VData mResult = new VData();

    /** 数据操作字符串 */
//    private String mOperate;
//    private VData mInputData = new VData();


    /** 业务处理相关变量 */
    /** 菜单 */
//    private LDMenuSchema mLDMenuSchema = new LDMenuSchema();
//    private LDMenuSet mLDMenuSet = new LDMenuSet();

    private GlobalInput mGlobalInput = new GlobalInput();


    /** 返回的数据*/
//    private String mResultStr = "";
    public logoutBL()
    {}


    /**
     * 传输数据的公共方法
     * @param cInputData VData
     * @param cOperate String
     * @return boolean
     */
    public boolean submitData(VData cInputData, String cOperate)
    {
        //将操作数据拷贝到本类中
//        this.mOperate = cOperate;

        //得到外部传入的数据,将数据备份到本类中
        if (!getInputData(cInputData))
        {
            return false;
        }

        if (!dealData())
        {
            return false;
        }

        //进行业务处理
        return true;
    }

    public VData getResult()
    {
        return mResult;
    }


    /**
     * 从输入数据中得到所有对象
     * 输出：如果没有得到足够的业务数据对象，则返回false,否则返回true
     * @param cInputData VData
     * @return boolean
     */
    private boolean getInputData(VData cInputData)
    {
        // 保单查询条件
//        log.addlog("start get input data");
        mGlobalInput.setSchema((GlobalInput) cInputData.getObjectByObjectName(
                "GlobalInput", 0));
//        log.addlog("get one");
        if (mGlobalInput == null)
        {
            return false;
        }


        return true;
    }

    /**
     * 数据准备
     * @return boolean
     */
    private boolean dealData()
    {

        return true;
    }
}
