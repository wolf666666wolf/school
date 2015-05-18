package com.sinosoft.service;

import java.util.Timer;
import java.util.Vector;

import com.sinosoft.utility.VData;

public class ServiceA {

	private ServiceEngineA mServiceEngineA = null;
	private Timer mServiceTimer = null;
	/** �̳߳س�ʼ�������� */
	private String mCovertClassName = "";
	/** �ȴ�����ִ�ж��� */
	private Vector mTaskWaitList = null;
	/** �����������߳����� */
	private int mthreadnum;
	/**ѭ��̽����ʱ�� ��λΪ����*/
	private int mperiod;
	/**
	 * @param covertclassname �̳߳س�ʼ��������
	 * @param tTaskWaitList �ȴ�����ִ�ж���
	 * @param threadnum �����߳�����
	 * @param tperiod  �̳߳�����ȴ�ʱ�䵥λΪ����
	 */
	public ServiceA(String covertclassname, Vector tTaskWaitList, int threadnum,int tperiod) {
		this.mCovertClassName = covertclassname;
		this.mTaskWaitList =(Vector)tTaskWaitList.clone();
		this.mthreadnum = threadnum;
		this.mperiod = tperiod;
	}
	
	
	/**
	 * @param covertclassname �̳߳س�ʼ��������
	 * @param tTaskWaitList �ȴ�����ִ�ж���
	 * @param threadnum �����߳�����
	 * Ĭ���̳߳�����ȴ�ʱ��Ϊ10����
	 */
	public ServiceA(String covertclassname, Vector tTaskWaitList, int threadnum) {
		this.mCovertClassName = covertclassname;
		this.mTaskWaitList = (Vector)tTaskWaitList.clone();
		this.mthreadnum = threadnum;
		this.mperiod = 10;  //Ĭ��Ϊ1000����ѭ��һ��
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
			
			//�ж϶����Ƿ�ȫ����ִ��
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
			//������б�ִ�У����ж��̳߳�ʱ��ȫ��ִ����ϣ���ϲ��˳�����������ȴ�
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