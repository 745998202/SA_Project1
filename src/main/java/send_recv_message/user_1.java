package send_recv_message;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ClientException;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.Message;
import com.aliyun.mns.model.QueueMeta;



public class user_1 extends JFrame {

	private JButton button1;
	private JButton button2;
	private CloudAccount accountSend;
	private CloudAccount accountRecv;//用于接收的连接
	private MNSClient clientSend;
	private MNSClient clientRecv;
	private CloudQueue sendQueue;
	private CloudQueue recvQueue;
	public user_1(){
		setSize(400, 400);
		setLayout(new GridLayout(1,1));
		button1 = new JButton("USER_TWO");
		button2 = new JButton("USER_THREE");
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Dialogto2 dialogto2 = new Dialogto2();
				dialogto2.start();
			}
		});
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Dialogto3 dialogto3 = new Dialogto3();
				dialogto3.start();
			}
		});
		Container container = getContentPane();
		container.add(button1);
		container.add(button2);
		pack();
		
	}
	
	private class Dialogto2 extends Thread{
		
		private JFrame frame;
		private JTextArea recvText;
		private JTextArea sendText;
		private JButton sendButton;
		
		public Dialogto2() {
			// TODO Auto-generated constructor stub
			//初始化总框架大小
			frame = new JFrame("与二号聊天中");
			frame.setSize(400, 300);
			//设置初始化
			frame.setLayout(new GridBagLayout());
			Container container = frame.getContentPane();
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.ipadx = 400;
			constraints.ipady = 200;
			constraints.gridwidth = 3;
			constraints.gridheight = 2;
			recvText = new JTextArea();
			recvText.enable(false);
			container.add(recvText, constraints);
			constraints.ipadx=0;
			constraints.ipady=0;
			constraints.gridwidth = 2;
			constraints.gridheight = 1;
			constraints.gridx = 1;
			constraints.gridy = 2;
			sendButton = new JButton("发送");
			sendButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					String editMessage = sendText.getText();
					if(!(editMessage.equals(""))) {
						accountSend= new CloudAccount("LTAIemSPJRU9fFsU", "QWQBBO5OIPRpAjup46g1ZosPEMvBW1", "http://1502738011240336.mns.cn-hangzhou.aliyuncs.com/");
						clientSend = accountSend.getMNSClient();
						sendQueue = clientSend.getQueueRef("1to2");
						Message myMessage = new Message();
						myMessage.setMessageBody(editMessage);
						Message putMessage = sendQueue.putMessage(myMessage);
						recvText.setText(recvText.getText()+"\n我： "+sendText.getText());
						sendText.setText("");
						clientSend.close();
					}
				}
			});
			container.add(sendButton,constraints);
			constraints.gridx = 0;
			constraints.gridy = 3;
			constraints.ipadx = 400;
			constraints.ipady = 200;
			constraints.gridwidth = 3;
			constraints.gridheight = 2;
			sendText = new JTextArea();
			container.add(sendText,constraints);
			frame.setVisible(true);	
		}
		@Override
		public void run() {
			while(true) {
				accountRecv= new CloudAccount("LTAIemSPJRU9fFsU", "QWQBBO5OIPRpAjup46g1ZosPEMvBW1", "http://1502738011240336.mns.cn-hangzhou.aliyuncs.com/");
				clientRecv = accountRecv.getMNSClient();
				recvQueue = clientRecv.getQueueRef("2to1");
				Message recvMessage = null;
				try {
				recvMessage = recvQueue.popMessage(30);
				if(recvMessage!=null) {
					recvText.setText(recvText.getText()+"\n二号: "+recvMessage.getMessageBody().toString());
					recvQueue.deleteMessage(recvMessage.getReceiptHandle());
				}
				else {
					System.out.println("There are no Message !!!");
				}
				}catch(ClientException e0) {
					e0.printStackTrace();
				}catch(ServiceException e1) {
					e1.printStackTrace();
				}catch(Exception e2) {
					e2.printStackTrace();
				}
				clientRecv.close();
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}
	
private class Dialogto3 extends Thread{
		
		private JFrame frame;
		private JTextArea recvText;
		private JTextArea sendText;
		private JButton sendButton;
		
		public Dialogto3() {
			// TODO Auto-generated constructor stub
			//初始化总框架大小
			frame = new JFrame("与三号聊天中");
			frame.setSize(400, 300);
			//设置初始化
			frame.setLayout(new GridBagLayout());
			Container container = frame.getContentPane();
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.ipadx = 400;
			constraints.ipady = 200;
			constraints.gridwidth = 3;
			constraints.gridheight = 2;
			recvText = new JTextArea();
			recvText.enable(false);
			container.add(recvText, constraints);
			constraints.ipadx=0;
			constraints.ipady=0;
			constraints.gridwidth = 2;
			constraints.gridheight = 1;
			constraints.gridx = 1;
			constraints.gridy = 2;
			sendButton = new JButton("发送");
			sendButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					String editMessage = sendText.getText();
					if(!(editMessage.equals(""))) {
						accountSend= new CloudAccount("LTAIemSPJRU9fFsU", "QWQBBO5OIPRpAjup46g1ZosPEMvBW1", "http://1502738011240336.mns.cn-hangzhou.aliyuncs.com/");
						clientSend = accountSend.getMNSClient();
						sendQueue = clientSend.getQueueRef("1to3");
						Message myMessage = new Message();
						myMessage.setMessageBody(editMessage);
						Message putMessage = sendQueue.putMessage(myMessage);
						recvText.setText(recvText.getText()+"\n我： "+sendText.getText());
						sendText.setText("");
						clientSend.close();
					}
				}
			});
			container.add(sendButton,constraints);
			constraints.gridx = 0;
			constraints.gridy = 3;
			constraints.ipadx = 400;
			constraints.ipady = 200;
			constraints.gridwidth = 3;
			constraints.gridheight = 2;
			sendText = new JTextArea();
			container.add(sendText,constraints);
			frame.setVisible(true);	
		}
		@Override
		public void run() {
			while(true) {
				accountRecv= new CloudAccount("LTAIemSPJRU9fFsU", "QWQBBO5OIPRpAjup46g1ZosPEMvBW1", "http://1502738011240336.mns.cn-hangzhou.aliyuncs.com/");
				clientRecv = accountRecv.getMNSClient();
				recvQueue = clientRecv.getQueueRef("3to1");
				Message recvMessage = null;
				try {
				recvMessage = recvQueue.popMessage(30);
				if(recvMessage!=null) {
					recvText.setText(recvText.getText()+"\n三号: "+recvMessage.getMessageBody().toString());
					recvQueue.deleteMessage(recvMessage.getReceiptHandle());
				}
				else {
					System.out.println("There are no Message !!!");
				}
				}catch(ClientException e0) {
					e0.printStackTrace();
				}catch(ServiceException e1) {
					e1.printStackTrace();
				}catch(Exception e2) {
					e2.printStackTrace();
				}
				clientRecv.close();
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}

	public static void main(String args[])
	{
		user_1 use = new user_1();
		use.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		use.setVisible(true);
	}

}
