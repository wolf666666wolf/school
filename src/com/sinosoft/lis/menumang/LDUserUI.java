package com.sinosoft.lis.menumang;

import com.sinosoft.lis.db.SDUserDB;
import com.sinosoft.lis.pubfun.PubFun;
import com.sinosoft.utility.CError;
import com.sinosoft.utility.CErrors;
import com.sinosoft.utility.VData;

public class LDUserUI
{
    /** 错误处理类，每个需要错误处理的类中都放置该类 */
    public CErrors mErrors = new CErrors();

    /** 往后面传输数据的容器 */
    private VData mInputData = new VData();

    /** 数据操作字符串 */
    private String mOperate;

    int mResultNum = 0;

    public LDUserUI()
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
		System.out.println(PubFun.schema2JsonObj(new SDUserDB().executeQuery("select * from sduser").get(1)));
        LDUserBL tUserBL = new LDUserBL();

        if (tUserBL.submitData(cInputData, mOperate))
        {
            mInputData = tUserBL.getResult();
            mResultNum = tUserBL.getResultNum();
        }
        else
        {
            // @@错误处理
            this.mErrors.copyAllErrors(tUserBL.mErrors);
            CError tError = new CError();
            tError.moduleName = "LDUserUI";
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

    public int getResultNum()
    {
        return mResultNum;
    }

    /**
     * 测试函数
     * @param args String[]
     */
    public static void main(String[] args)
    {
//        LDUserBL tLDUserBL1 = new LDUserBL();
//        LDUserSchema tSchema = new LDUserSchema();
//        tSchema.setUserCode("001");
//        tSchema.setPassword("26B029A2DCE2820C");
//        tSchema.setComCode("86");
//        VData tVData = new VData();
//        tVData.add(tSchema);
//        if (!tLDUserBL1.submitData(tVData, "query"))
//        {
//            System.out.println("ppp");
//        }
//        System.out.println("kkkk");
//        for (int i = 0; i < tLDUserBL1.mErrors.getErrorCount(); i++)
//        {
//            CError tError = tLDUserBL1.mErrors.getError(i);
//            System.out.println(tError.errorMessage);
//            System.out.println(tError.moduleName);
//            System.out.println(tError.functionName);
//            System.out.println("----------------");
//        }
    }
}
