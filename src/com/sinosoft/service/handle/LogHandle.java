package com.sinosoft.service.handle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.sinosoft.service.Handle;
import com.sinosoft.service.IMessage;
import com.sinosoft.service.exception.ServiceException;
import com.sinosoft.utility.TransferData;
/**
* <p>Description: log��־�ļ�</p>
*
* <p>Copyright: Copyright (c) 2008</p>
*
* <p>Company: sinosoft</p>
*
* @author litao
* @version 1.0
* */
public class LogHandle implements Handle {
	/**�ļ������*/
	private OutputStream out;
	/**��ʼ��log��־�ļ�*/
	public void init(TransferData transfer) {
//		String fileName = (String) transfer.getValueByName("file");
//		if(fileName==null || fileName.equals(""))
//		{
//			throw new ServiceException("��־�ļ���û�д���");
//		}
//		try
//		{
//			File tFile = new File(fileName);
//			out = new FileOutputStream(tFile);
//		}
//		catch(Exception ex)
//		{
//			throw new ServiceException("��־�ļ�"+fileName+"����");
//		}
	}
	/**��������������ʱ�估���������,д��log��־*/
	public void invoke(IMessage message) {
//		String des = message.getBusiName();
//		des = PubFun.getCurrentDate()+"::"+PubFun.getCurrentTime()+"::"+des+"\n";
//		
//		try{
//			out.write(des.getBytes());
//			out.flush();
//		}
//		catch(IOException ex)
//		{
//			throw new ServiceException("��־�ļ�����:"+ex.getMessage());
//		}
	}

}
