package com.sinosoft.lis.changePwd;

import com.sinosoft.utility.CError;
import com.sinosoft.utility.CErrors;
import com.sinosoft.utility.VData;

public class LDChangePwdUI
{
    /** 错误处理类，每个需要错误处理的类中都放置该类 */
    public CErrors mErrors = new CErrors();
    /** 往后面传输数据的容器 */
    private VData mInputData = new VData();
    /** 数据操作字符串 */
    private String mOperate;

    int mResultNum = 0;

    public LDChangePwdUI()
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
        this.mOperate = cOperate;

        LDChangePwdBL tChangePwdBL = new LDChangePwdBL();

        if (tChangePwdBL.submitData(cInputData, mOperate))
        {
            return true;
        }
        else
        {
            // @@错误处理
            this.mErrors.copyAllErrors(tChangePwdBL.mErrors);
            CError tError = new CError();
            tError.moduleName = "ChangePwdUI";
            tError.functionName = "submitData";
            tError.errorMessage = "数据查询失败!";
            this.mErrors.addOneError(tError);
            mInputData.clear();
            return false;
        }
    }

    public static void main(String[] args)
    {
    }
}
