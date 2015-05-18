package com.sinosoft.lis.pubfun;


public class GlobalInput
{
    /** 当前操作员 */
    public String Operator;
    /** 当前管理机构 */
    public String ManageCom;
    /** 当前登陆机构 */
    public String ComCode;
    /** 兼业代理机构 */
    public String AgentCom;

//  /** 当前险种 */
//  public String RiskCode;
//  /** 当前险种版本 */
//  public String RiskVersion;

    public GlobalInput()
    {
    }

    /**
     * 两个GlobalInput对象之间的直接复制
     * @param cGlobalInput 包含有具体值的GlobalInput对象
     */
    public void setSchema(GlobalInput cGlobalInput)
    {
        //获取登陆用户基础信息：用户编码、管理机构等
        this.Operator = cGlobalInput.Operator;
        this.ComCode = cGlobalInput.ComCode;
        this.ManageCom = cGlobalInput.ManageCom;
        this.AgentCom = cGlobalInput.AgentCom;
    }
}
