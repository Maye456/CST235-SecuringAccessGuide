package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import beans.Order;
import beans.User;
import business.MyTimerService;
import business.OrderBusinessInterface;

@ManagedBean
@ViewScoped
public class FormController 
{
	@Inject
	OrderBusinessInterface service;
	
	@EJB
	MyTimerService timer;
	
	public FormController() {}
	
//	public String onSubmit()
//	{
//		// Get user values from input form
//		FacesContext context = FacesContext.getCurrentInstance();
//		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
//		
//		// Prints a message to the console to tell us which business service is currently selected in the beans.xml
//		// file as an alternative
//		service.test();
//		
//		// Start a timer when the login is clicked
//		timer.setTimer(10000);
//		
//		// Put user data in the POST request
//		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
//		
//		// Call getAllOrders()
//		FormController fc = new FormController();
//		fc.getAllOrders();
//		fc.insertOrder();
//		fc.getAllOrders();
//		
//		// Show next page
//		return "TestResponse.xhtml";
//	}
	
	public String onLogOff()
	{
		// Invalidate the Session to clear the security token

		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		// Redirect to a protected page (so we get a full HTTP Request) to get Login Page

		return "TestResponse.xhtml?faces-redirect=true";
	}
	
	public OrderBusinessInterface getService()
	{
		return service;
	}
	
	private void getAllOrders()
	{
		// Connect
		String dbURL = "jdbc:mysql://localhost:3306/testapp";
		String user = "root";
		String password = "root";
		
		Connection c = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		// Database work
		try
		{
			c = DriverManager.getConnection(dbURL, user, password);
			System.out.println("Connection is successful " + dbURL + " user = " + user + " pw = " + password);
			
			// Create a SQL statement
			stmt = c.createStatement();
			
			// Execute the statement
			rs = stmt.executeQuery("SELECT * FROM testapp.ORDERS");
			
			// Process the rows in the result set
			while(rs.next())
			{
				System.out.println("ID:" + rs.getInt("ID") + ", " + rs.getString("ORDER_NO") + ", " + 
									rs.getString("PRODUCT_NAME") + ", " + rs.getFloat("PRICE") + ", " + 
									rs.getInt("QUANTITY"));
			}
		} 
		
		catch (SQLException e) 
		{
			System.out.println("Error communication with the database");
			e.printStackTrace();
		}
		
		finally
		{
			// Close the connection to the db
			try
			{
				rs.close();
				stmt.close();
				c.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	private void insertOrder()
	{
		// Connect
		String dbURL = "jdbc:mysql://localhost:3306/testapp";
		String user = "root";
		String password = "root";
		
		Connection c = null;
		Statement stmt = null;
		int rowsAffected = 0;

		// Database work
		try
		{
			c = DriverManager.getConnection(dbURL, user, password);
			System.out.println("Connection is successful " + dbURL + " user = " + user + " pw = " + password);
			
			// Create a SQL statement
			stmt = c.createStatement();
			
			// Execute the statement
			rowsAffected = stmt.executeUpdate("INSERT INTO testapp.ORDERS (ID, ORDER_NO, PRODUCT_NAME, PRICE, QUANTITY) values (null, '0000000638', 'CPU', 398.99, 13)");
			
			// Success Message
			System.out.println("Rows affected " + rowsAffected);
		}
		
		catch (SQLException e) 
		{
			System.out.println("Error communication with the database");
			e.printStackTrace();
		}
		
		finally
		{
			// Close the connection to the db
			try 
			{
				stmt.close();
				c.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public String onSendOrder()
	{
		DecimalFormat df = new DecimalFormat("00000000000");
		String orderNo = df.format(new Date().getTime());
		
		Order order = new Order();
		order.setOrderNo(orderNo);
		order.setProductName("This is an ordered product");
		order.setPrice((float)1000.00);
		order.setQuantity(2000);
		
		service.sendOrder(order);
		
		return "OrderResponse.xhtml";
	}
}