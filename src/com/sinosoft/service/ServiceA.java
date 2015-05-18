package com.sinosoft.service;

import java.util.Timer;
import java.util.Vector;

import com.sinosoft.utility.VData;

public class ServiceA {

	private ServiceEngineA mServiceEngineA = null;
	private Timer mServiceTimer = null;
	/** 线程池初始化对象名 */
	private String mCovertClassName = "";
	/** 等待任务执行队列 */
	private Vector mTaskWaitList = null;
	/** 运行中启动线程数量 */
	private int mthreadnum;
	/**循环探测间隔时间 单位为毫秒*/
	private int mperiod;
	/**
	 * @param covertclassname 线程池初始化对象名
	 * @param tTaskWaitList 等待任务执行队列
	 * @param threadnum 启动线程数量
	 * @param tperiod  线程池满后等待时间单位为毫秒
	 */
	public ServiceA(String covertclassname, Vector tTaskWaitList, int threadnum,int tperiod) {
		this.mCovertClassName = covertclassname;
		this.mTaskWaitList =(Vector)tTaskWaitList.clone();
		this.mthreadnum = threadnum;
		this.mperiod = tperiod;
	}
	
	
	/**
	 * @param covertclassname 线程池初始化对象名
	 * @param tTaskWaitList 等待任务执行队列
	 * @param threadnum 启动线程数量
	 * 默认线程池满后等待时间为10毫秒
	 */
	public ServiceA(String covertclassname, Vector tTaskWaitList, int threadnum) {
		this.mCovertClassName = covertclassname;
		this.mTaskWaitList = (Vector)tTaskWaitList.clone();
		this.mthreadnum = threadnum;
		this.mperiod = 10;  //默认为1000毫秒循环一次
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) { 
		
	}

	public void start() {
		try {
			System.out.println("mClassName:" + mCovertClassName);
			mServiceTimer = new Timer(); 
			mServiceEngineA = new ServiceEngineA(this.mCovertClassName,
					this.mthreadnum, mServiceTimer);
			mServiceEngineA.startEngine(this.mTaskWaitList);
			// Class[] parameterC = new Class[1];
			// parameterC[0] = String.class;
			// Object[] parameterO = new Object[1];
			// parameterO[0] = mCovertClassName;
			//
			// mServiceEngineBase = (ServiceEngineBase) Class.forName(
			// mServiceEngineClassName).getConstructor(parameterC)
			// .newInstance(parameterO);
			//
			// mServiceEngineBase.startEngine(mTaskWaitList);

			mServiceTimer.schedule(mServiceEngineA, 1000,  1000*mperiod);
			
			//判断队列是否全部被执行
			while(mTaskWaitList.size()>0)
			{
				try
				{
					//System.out.println("in mTaskWaitList.size:"+mTaskWaitList.size());
					Thread.sleep(10000);
					//System.out.println("sleep1");
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			//System.out.println("out mTaskWaitList.size:"+mTaskWaitList.size());
			//如果队列被执行，在判断线程池时候全部执行完毕，完毕才退出，否则继续等待
			while(!mServiceEngineA.getPoolNotInUse())
			{
				try
				{
					//System.out.println("mServiceEngineA.getPoolNotInUse()+busy!");
					Thread.sleep(5000);
					//System.out.println("sleep2");
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}