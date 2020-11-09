package beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Order")
public class Order implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8881892971408708862L;

	String orderNo;
	String productName;
	float price;
	int quantity;
	
	public Order() {}
	
	public Order (String orderNo, String productName, float price, int quantity)
	{
		this.orderNo = orderNo;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
	}

	public String getOrderNo() 
	{
		return orderNo;
	}

	public void setOrderNo(String orderNo) 
	{
		this.orderNo = orderNo;
	}

	public String getProductName() 
	{
		return productName;
	}

	public void setProductName(String productName) 
	{
		this.productName = productName;
	}

	public float getPrice() 
	{
		return price;
	}

	public void setPrice(float price) 
	{
		this.price = price;
	}

	public int getQuantity() 
	{
		return quantity;
	}

	public void setQuantity(int quantity) 
	{
		this.quantity = quantity;
	}
}
