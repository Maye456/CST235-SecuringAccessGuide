package business;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Connection;

import beans.Order;
import data.DataAccessInterface;
@Stateless
@Local(OrderBusinessInterface.class)
@Alternative
public class OrderBusinessService implements OrderBusinessInterface
{
	@Inject
	DataAccessInterface<Order> orderDataService;
	
	@Resource(mappedName="java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(mappedName="java:/jms/queue/Order")
	private Queue queue;

	List<Order> orders = new ArrayList<Order>();
	
	@Override
	public void test() 
	{
		System.out.println("========= Hello from the test method. Order Business Version #1");
	}
	
	public OrderBusinessService()
	{

	}

	@Override
	public List<Order> getOrders() 
	{
		return orderDataService.findAll();
	}

	@Override
	public void setOrders(List<Order> orders) 
	{
		
	}

	@Override
	public void sendOrder(Order order) 
	{
		// Send a Message for an Order
		try 
		{
			Connection connection = connectionFactory.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			MessageProducer messageProducer = session.createProducer(queue);
			
			TextMessage message1 = session.createTextMessage();
			message1.setText("This is a test message");
			messageProducer.send(message1);
			
			ObjectMessage message2 = session.createObjectMessage();
			message2.setObject(order);
			messageProducer.send(message2);
			
			connection.close();
		} 
		catch (JMSException e) 
		{
			e.printStackTrace();
		}		
	}
}
