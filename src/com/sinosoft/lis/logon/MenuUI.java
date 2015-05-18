
package com.sinosoft.lis.logon;

import com.sinosoft.utility.CError;
import com.sinosoft.utility.CErrors;
//import com.sinosoft.utility.StrTool;
import com.sinosoft.utility.VData;


public class MenuUI
{
    /** 错误处理类，每个需要错误处理的类中都放置该类 */
    public CErrors mErrors = new CErrors();

    /** 往后面传输数据的容器 */
    private VData mInputData = new VData();

    /** 数据操作字符串 */
    private String mOperate;

    public MenuUI()
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

        MenuBL tMenuBL = new MenuBL();

        if (tMenuBL.submitData(cInputData, mOperate))
        {
            mInputData = tMenuBL.getResult();
        }
        else
        {
            // @@错误处理
            this.mErrors.copyAllErrors(tMenuBL.mErrors);
            CError tError = new CError();
            tError.moduleName = "MenuUI";
            tError.functionName = "submitData";
            tError.errorMessage = "数据查询失败!";
            this.mErrors.addOneError(tError);
            mInputData.clear();
            return false;
        }

        return true;
    }

    public VData getResult()
    {
        return mInputData;
    }

    /**
     * 测试函数
     * @param args String[]
     */
    public static void main(String[] args)
    {
//        MenuUI tMenuUI = new MenuUI();
//        VData tData = new VData();
//        tMenuUI.submitData(tData, "QUERY||MAIN");
//        if (tMenuUI.mErrors.needDealError())
//        {
//            log.addlog(tMenuUI.mErrors.getFirstError());
//        }
//        else
//        {
//            tData.clear();
//            tData = tMenuUI.getResult();
//            String tStr = "";
//            tStr = (String) tData.get(0);
//            log.addlog(StrTool.unicodeToGBK(tStr));
//        }
    }
}
