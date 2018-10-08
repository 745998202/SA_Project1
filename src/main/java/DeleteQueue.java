import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ClientException;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.Message;

public class DeleteQueue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CloudAccount account  = new CloudAccount("LTAIemSPJRU9fFsU", "QWQBBO5OIPRpAjup46g1ZosPEMvBW1", "http://1502738011240336.mns.cn-hangzhou.aliyuncs.com/");
		MNSClient client = account.getMNSClient();
		try {
			CloudQueue queue = client.getQueueRef("JavaCreatedQueue1");
			queue.delete();
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
