package business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import beans.Order;

@Stateless
@Local(OrderBusinessInterface.class)
@Alternative
public class AnotherOrderBusinessService implements OrderBusinessInterface
{
	List<Order> orders = new ArrayList<Order>();
	
	@Override
	public void test() 
	{
		System.out.println("========= Hello from the test method. Order Business Version #2");
	}
	
	
	public AnotherOrderBusinessService()
	{
		orders.add(new Order("000000000a", "Product #a from alternative bs", (float)13.25, 1));
		orders.add(new Order("000000000b", "Product #b from alternative bs", (float)21.90, 8));
		orders.add(new Order("000000000c", "Product #c from alternative bs", (float)34.47, 3));
		orders.add(new Order("000000000d", "Product #d from alternative bs", (float)4.30, 9));
		orders.add(new Order("000000000e", "Product #e from alternative bs", (float)6.67, 5));
		orders.add(new Order("000000000f", "Product #f from alternative bs", (float)8.00, 7));
		orders.add(new Order("000000000g", "Product #g from alternative bs", (float)9.17, 21));
		orders.add(new Order("000000000h", "Product #h from alternative bs", (float)10.59, 33));
		orders.add(new Order("000000000i", "Product #i from alternative bs", (float)18.35, 15));
		orders.add(new Order("000000000j", "Product #j from alternative bs", (float)26.99, 19));
	}


	@Override
	public List<Order> getOrders() 
	{
		return orders;
	}

	@Override
	public void setOrders(List<Order> orders) 
	{
		this.orders = orders;
	}


	@Override
	public void sendOrder(Order order) {
		// TODO Auto-generated method stub
		
	}
}