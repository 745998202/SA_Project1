import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ClientException;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.Message;

public class ReceiveMessage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CloudAccount account  = new CloudAccount("LTAIemSPJRU9fFsU", "QWQBBO5OIPRpAjup46g1ZosPEMvBW1", "http://1502738011240336.mns.cn-hangzhou.aliyuncs.com/");
		MNSClient client = account.getMNSClient();
		try {
			CloudQueue queue = client.getQueueRef("JavaCreatedQueue1");
			Message recvMessage = queue.popMessage();
			if(recvMessage!=null) {
				System.out.println("Message Handle :" + recvMessage.getReceiptHandle());
				System.out.println("Message Body :" + recvMessage.getMessageBody());
				System.out.println("Message Id :" + recvMessage.getMessageId());
				System.out.println("Message dequeue count :" + recvMessage.getDequeueCount());//
				queue.deleteMessage(recvMessage.getReceiptHandle());
			}
		}catch(ClientException e0) {
			e0.printStackTrace();
		}catch(ServiceException e1) {
			e1.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		client.close();

	}

}
