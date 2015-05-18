﻿package com.sinosoft.lis.pubfun;

import com.sinosoft.utility.VData;
import com.sinosoft.utility.TransferData;
import com.sinosoft.utility.CErrors;

/**
 * <p>Title: </p>
 * 公用业务逻辑类接口
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: ChinaSoft</p>
 *
 * @author HYQ
 * @version 1.0
 */
public interface PubBLInterface
{
    public VData getResult();

    public CErrors getErrors();

    public boolean dealData();

    public boolean checkData();

    public boolean getInputData(VData cInputData);

    public boolean submitData(VData cInputData, String cOperate);
}
