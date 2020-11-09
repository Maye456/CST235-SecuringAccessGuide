package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import business.OrderBusinessInterface;

import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class Orders 
{
	@Inject
	OrderBusinessInterface service;
	
	List<Order> orders = new ArrayList<Order>();
	
	public void init() 
	{
		orders = service.getOrders();
	}

	public List<Order> getOrders() 
	{
		return orders;
	}

	public void setOrders(List<Order> orders) 
	{
		this.orders = orders;
	}	
}