package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import beans.Order;

@Stateless
@Local
@LocalBean
public class OrderDataService implements DataAccessInterface<Order>
{
	@Override
	public List<Order> findAll() 
	{
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/testapp";
		String username ="root";
		String password = "root";
		String sql = "SELECT * FROM testapp.ORDERS";
		List<Order> orders = new ArrayList<Order>();
		
		try
		{
			conn = DriverManager.getConnection(url, username, password);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) 
			{
				orders.add(new Order(rs.getString("ORDER_NO"), rs.getString("PRODUCT_NAME"), 
							rs.getFloat("PRICE"), rs.getInt("QUANTITY")));
						
			}
			rs.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			if(conn!=null)
			{
				try
				{
					conn.close();						
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
		return orders;
	}

	@Override
	public Order findById(int id) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(Order t)
	{
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/testapp";
		String username ="root";
		String password = "root";
		String sql = "INSERT INTO testapp.ORDERS(ORDER_NO, PRODUCT_NAME, PRICE, QUANTITY) VALUES ('001122334455', 'This was inserted new', 25.00, 100)";
		
		try 
		{
			conn = DriverManager.getConnection(url, username, password);
		
			Statement stmt = conn.createStatement();		
			stmt.executeUpdate(sql);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			if(conn != null)
			{
				try
				{
					conn.close();						
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
		
		}
		return true;
	}

	@Override
	public boolean update(Order t) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Order t) 
	{
		// TODO Auto-generated method stub
		return false;
	}
}
