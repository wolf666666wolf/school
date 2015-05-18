package com.sinosoft.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import com.sinosoft.utility.VData;

public class ServiceEngineA extends TimerTask {
	private Vector mTaskWaitList = new Vector();
	private CovertPoolA mCovertPoolA = null;
	private HashMap mHashMap = new HashMap();
	private int runcount = 0;
	private Timer mTimer;

	// 同时初始化引擎的工作池
	public ServiceEngineA(String classname, int threadnum, Timer tTimer) {
		mCovertPoolA = new CovertPoolA(classname, threadnum);
		mTimer = tTimer;
	}

	public synchronized void run() {
		// TODO Auto-generated method stub

		int count = mTaskWaitList.size();
		Iterator tIterator = mTaskWaitList.iterator();
		boolean flag = false;
		runcount++;
//		System.out.println("当前是第" + "runcount" + "次探测!  runcount test:"
//				+ runcount);
		if (count > 0) {

			// for (int i = 0; i < count; i++) {
			// if (!mHashMap.containsKey(mTaskWaitList.get(i))) {
			// System.out.println("dangqianjigou:"+mTaskWaitList.get(i));
			// flag = true; // 存在未处理的任务就修改为false;
			// break;
			// }
			// }
			while (tIterator.hasNext()) {
				if (!mHashMap.containsKey(tIterator.next())) {
					//System.out.println("not in hashmap");
					flag = true;// 存在未处理的任务就修改为false;
					break;
				} else {
					tIterator.remove();
				}
			}
		}
		if (flag) {
			tIterator = mTaskWaitList.iterator();
			while (tIterator.hasNext()) {
				Object tObject = tIterator.next();
				if (mHashMap.containsKey(tObject)) {
					System.out.println("该任务已经被执行!:wkwkwkwkw:"
							+ tObject.toString());
					tIterator.remove();
					continue;
				}
				//System.out.println("get pool:");
				CovBase tCovBase = mCovertPoolA.getCovert();
				if (tCovBase == null) {
//					System.out.println("没有空闲!weikaiweikai!");
					break;
				}

//				VData tVData = new VData();
//				tVData.add(tObject);
				//System.out.println("tObject:" + tObject.toString());
				tCovBase.setObject(tObject);
				new Thread(tCovBase).start();
				mHashMap.put(tObject, "");
				tIterator.remove();
			}
		} else {
			System.out.println("mTimer.cancel");
			mTimer.cancel(); // 不存在未处理的任务 停止监听
		}
	}

	public void startEngine(Vector tTaskWaitList) {
		this.mTaskWaitList = tTaskWaitList;
	}
	public boolean getPoolNotInUse()
	{
		return mCovertPoolA.testPool(); 
	}
}
