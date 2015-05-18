package com.sinosoft.lis.logon;

import com.sinosoft.utility.CErrors;
import com.sinosoft.utility.VData;


public class logoutUI
{
    private VData mInputData;
    public CErrors mErrors = new CErrors();

    public logoutUI()
    {
    }

    //传输数据的公共方法
    public boolean submitData(VData cInputData, String cOperate)
    {
        //首先将数据在本类中做一个备份
        mInputData = (VData) cInputData.clone();

        //向BL层传输数据
//        log.addlog("Start logout UI Submit...");
        logoutBL tlogoutBL = new logoutBL();
        tlogoutBL.submitData(mInputData, cOperate);
//        log.addlog("End logout UI Submit...");

        //如果有需要处理的错误，则返回
        if (tlogoutBL.mErrors.needDealError())
        {
            this.mErrors.copyAllErrors(tlogoutBL.mErrors);
        }

//        log.addlog(mErrors.getErrorCount());
        mInputData = null;
        return true;
    }

    public static void main(String[] args)
    {
    }
}
