import java.lang.System.Logger;
import java.rmi.ServerException;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ClientException;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.QueueMeta;

public class CreateQueueDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 CloudAccount account = new CloudAccount("LTAIemSPJRU9fFsU", "QWQBBO5OIPRpAjup46g1ZosPEMvBW1", "http://1502738011240336.mns.cn-hangzhou.aliyuncs.com/");
		 MNSClient client = account.getMNSClient();
		 QueueMeta meta = new QueueMeta(); //生成本地QueueMeta属性，有关队列属性详细介绍见https://help.aliyun.com/document_detail/27476.html
	     meta.setQueueName("JavaCreatedQueue1");  // 设置队列名
	     meta.setPollingWaitSeconds(15);
	     meta.setMaxMessageSize(2048L);
	     
	     try {
	    	 CloudQueue queue = client.createQueue(meta);
	    	 
	     }catch(ClientException e0) {
	    	 System.out.println("Something wrong with the network connection between client and MNS service."
	                   + "Please check your network and DNS availablity.");
	            e0.printStackTrace();
	     }catch(ServiceException e1) {
	    	 e1.printStackTrace();
	            if (e1.getErrorCode() != null) {
	                if (e1.getErrorCode().equals("QueueNotExist"))
	                {
	                    System.out.println("Queue is not exist.Please create before use");
	                } else if (e1.getErrorCode().equals("TimeExpired"))
	                {
	                    System.out.println("The request is time expired. Please check your local machine timeclock");
	                }
	            	}	

	     }catch(Exception e)
	      {
		        System.out.println("Unknown exception happened!");
		        e.printStackTrace();
	      }
	     client.close();  // 程序退出时，需主动调用client的close方法进行资源释放
 

		}
	}
