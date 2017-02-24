package avtiveMq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.apache.activemq.transport.stomp.Stomp.Headers.Connected;

import sun.rmi.server.Activation;

public class ProderImpl implements IProder {

	private static final String USERNAME=ActiveMQConnection.DEFAULT_USER;
	private static final String PASSWORD=ActiveMQConnection.DEFAULT_PASSWORD;
	private static final String BROKEYRL=ActiveMQConnection.DEFAULT_BROKER_URL;
	public ConnectionFactory  conFactory;
	public  Connection con;
	public Session session;
	public ThreadLocal<MessageProducer>  local=new ThreadLocal<MessageProducer>();
	public ThreadLocal<Integer>  t1=new ThreadLocal<Integer>();

	public void init() {
		conFactory =new org.apache.activemq.ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEYRL);
		try {
			con=conFactory.createConnection();
			con.start();
			session=con.createSession(true, Session.AUTO_ACKNOWLEDGE);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(String name) {
     try {
		Queue  queue=   session.createQueue(name);
	  // MessageProducer p=	session.createProducer(queue);
		MessageProducer p=null;
		if(local.get()!=null){
			p=local.get();
		}else{
			p=session.createProducer(queue);
		}
		  
	   while(true){
		   Integer count=t1.get();
		    if(count==null){
		    	count=0;
		    }
		    count+=1;
		 TextMessage message=  session.createTextMessage(Thread.currentThread().getName()+"我是宇哥!"+"第"+count+"个消息");
		 t1.set(count);
		 System.out.println(Thread.currentThread().getName()+"我是宇哥!"+"第"+count+"个消息");  
		 p.send(message);
		   session.commit();
	   }
	} catch (JMSException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
