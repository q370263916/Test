package avtiveMq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;

public class CustomImpl implements ICustomer {
	
	private static final String USERNAME=ActiveMQConnection.DEFAULT_USER;
	private static final String PASSWORD=ActiveMQConnection.DEFAULT_PASSWORD;
	private static final String BROKEYRL=ActiveMQConnection.DEFAULT_BROKER_URL;
	public ConnectionFactory  conFactory;
	public  Connection con;
	public Session session;
	public ThreadLocal<MessageConsumer>  t2=new ThreadLocal<MessageConsumer>();


	public void init() {
		conFactory =new org.apache.activemq.ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEYRL);
		try {
			con=conFactory.createConnection();
			con.start();
			session=con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void getMessage(String name) {
    try {
		Queue queue=session.createQueue(name);
		//MessageConsumer consumer=session.createConsumer(queue);
		MessageConsumer consumer=null;
		if(t2.get()!=null){
			consumer=t2.get();
		}else{
			consumer=session.createConsumer(queue);
		}
		while(true){
		  
			TextMessage message=(TextMessage) consumer.receive();
			if(message!=null){
				System.out.println("我是消费端我接收到的消息是-----"+message.getText());
				message.acknowledge();
			}else{
				break;
			}
		}
	 
	} catch (JMSException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}

	 

}
