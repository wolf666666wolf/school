﻿package com.sinosoft.lis.menumang;
import com.sinosoft.utility.CErrors;
import com.sinosoft.utility.VData;

public class LDMenuFunUI
{

    //业务处理相关变量
    private VData mInputData;
    public CErrors mErrors = new CErrors();

    public LDMenuFunUI()
    {
    }

    //传输数据的公共方法
    public boolean submitData(VData cInputData, String cOperate)
    {
        //首先将数据在本类中做一个备份
        mInputData = (VData) cInputData.clone();
//        System.out.println("UI get inputdata...:");
        LDMenuFunBL tLDMenuFunBL = new LDMenuFunBL();
//        System.out.println("Start LDMenu UI Submit...");
        boolean tag = tLDMenuFunBL.submitData(mInputData, cOperate);
        if (!tag)
        {
            return false;
        }
//        System.out.println("End LDMenu UI Submit...");

        //如果有需要处理的错误，则返回
        if (tLDMenuFunBL.mErrors.needDealError())
        {
            this.mErrors.copyAllErrors(tLDMenuFunBL.mErrors);
            return false;
        }
//        System.out.println("error num=" + mErrors.getErrorCount());
        mInputData = null;
        return true;
    }

    public static void main(String[] args)
    {
//        LDMenuFunUI tLDMenuFunUI = new LDMenuFunUI();
//        LDMenuSchema tLDMenuSchema = new LDMenuSchema();
//        tLDMenuSchema.setNodeName("aaaaa");
//        tLDMenuSchema.setRunScript("bbbbbb");
//        tLDMenuSchema.setChildFlag("0");
//        tLDMenuSchema.setParentNodeCode("1001");
//        VData tVData = new VData();
//        TransferData tTransferData = new TransferData();
//        tTransferData.setNameAndValue("ischild", "false");
//        tTransferData.setNameAndValue("NodeCode", "1010");
//        tVData.add(tLDMenuSchema);
//        tVData.add(tTransferData);
//        tLDMenuFunUI.submitData(tVData, "insert");
    }
}
