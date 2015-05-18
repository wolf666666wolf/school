package com.sinosoft.utility;

import java.util.Vector;


public class TransferData
{
    public TransferData()
    {
    }

    private Vector nameVData = new Vector(); //存放变量名
    private Vector valueVData = new Vector(); //存放对应该变量的值

    /**
     * 设置要传入的变量的名字和对应的值(对象)
     * 将要传入的变量的名字存入 nameVData容器中
     * 将要传入的变量的值存入 valueVData容器中
     * @param name Object 变量的名字
     * @param value Object 变量的值
     */
    public void setNameAndValue(Object name, Object value)
    {
        nameVData.add(name);
        valueVData.add(value);
    }

    /**
     * 设置要传入的变量的名字和对应的值(float)
     * 将要传入的变量的名字存入 nameVData容器中
     * 将要传入的变量的值存入 valueVData容器中
     * @param name Object 变量的名字
     * @param value float 变量的值float型
     */
    public void setNameAndValue(Object name, float value)
    {
        nameVData.add(name);
        valueVData.add(new Float(value));
    }

    /**
     * 设置要传入的变量的名字和对应的值(double)
     * 将要传入的变量的名字存入 nameVData容器中
     * 将要传入的变量的值存入 valueVData容器中
     * @param name Object 变量的名字
     * @param value double 变量的值double型
     */
    public void setNameAndValue(Object name, double value)
    {
        nameVData.add(name);
        valueVData.add(new Double(value));
    }

    /**
     * 设置要传入的变量的名字和对应的值(int)
     * 将要传入的变量的名字存入 nameVData容器中
     * 将要传入的变量的值存入 valueVData容器中
     * @param name Object 变量的名字
     * @param value int 变量的值int型
     */
    public void setNameAndValue(Object name, int value)
    {
        nameVData.add(name);
        valueVData.add(new Integer(value));
    }

    /**
     * 返回和传入的变量的名字对应的值
     * @param name Object 变量的名字
     * @return Object
     */
    public Object getValueByName(Object name)
    {
        for (int i = 0; i < nameVData.size(); i++)
        {
            if (nameVData.elementAt(i).equals(name))
            {
                return valueVData.get(i);
            }
        }
        return null;
    }

    /**
     * 返回传入的变量名字对应在集合中的位置
     * @param name Object 变量的名字
     * @return int 在集合中的位置,从0开始。没有则返回-1
     */
    public int findIndexByName(Object name)
    {
        for (int i = 0; i < nameVData.size(); i++)
        {
            if (nameVData.elementAt(i).equals(name))
            {
                return i;
            }
        }
        return -1;
    }

    /**
     * 删除集合中的对应传入变量的值
     * @param name Object 变量的名字
     * @return boolean
     */
    public boolean removeByName(Object name)
    {
        for (int i = 0; i < nameVData.size(); i++)
        {
            if (nameVData.elementAt(i).equals(name))
            {
                valueVData.remove(i);
                nameVData.remove(i);
            }
        }
        return true;
    }

    /**
     * 返回所有的变量名字值
     * @return Vector
     */
    public Vector getValueNames()
    {
        return nameVData;
    }

    /**
     * 测试函数
     * @param args String[]
     */
    public static void main(String[] args)
    {
//        TransferData transferData1 = new TransferData();
//        transferData1.setNameAndValue("int", 200);
//        Integer value = (Integer) transferData1.getValueByName("int");
//        log.addlog("value:" + value);
//        TransferData test1 = transferData1;
//        Integer value2 = (Integer) transferData1.getValueByName("int");
//        log.addlog("value2:" + value2);
    }
}
