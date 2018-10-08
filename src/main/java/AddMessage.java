import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ClientException;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.Message;

public class AddMessage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CloudAccount account = new CloudAccount("LTAIemSPJRU9fFsU", "QWQBBO5OIPRpAjup46g1ZosPEMvBW1", "http://1502738011240336.mns.cn-hangzhou.aliyuncs.com/");
		MNSClient client = account.getMNSClient();
		try {
			CloudQueue queue = client.getQueueRef("JavaCreatedQueue1");
			Message message = new Message();
			message.setMessageBody("This is the first message from java project");
			Message putMessage = queue.putMessage(message);
			System.out.println("加入消息成功！！");
		}catch(ClientException e0){//检测连接异常
			System.out.println("Something wrong with the network connection between client and MNS service."
	                   + "Please check your network and DNS availablity.");
	            e0.printStackTrace();
		}catch(ServiceException e1) {//检测队列异常，比如队列不存在等问题
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
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		client.close();
		}

}

