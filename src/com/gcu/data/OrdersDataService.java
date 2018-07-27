package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.gcu.model.Order;

/**
 * Spring Bean implementation class OrderDataService
 */
public class OrdersDataService implements DataAccessInterface<Order>
{
	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	/**
     * Default constructor. 
     */
    public OrdersDataService() 
    {
    }

    /**
     * CRUD: finder to return all entities
     */
    public List<Order> findAll() 
    {
		String sql = "SELECT * FROM ORDERS";
		List<Order> orders = new ArrayList<Order>();
		try 
		{
			// Execute SQL Query and loop over result set
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
			while(srs.next())
			{
				orders.add(new Order(srs.getString("ORDER_NO"), srs.getString("PRODUCT_NAME"), srs.getFloat("PRICE"), srs.getInt("QUANTITY")));
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return orders;
    }

    /**
     * CRUD: finder to return a single entity
     */
	public Order findById(int id)
	{
		return null;
	}

	/**
	 * CRUD: create an entity
	 */
	public boolean create(Order order)
	{
		String sql = "INSERT INTO ORDERS(ORDER_NO, PRODUCT_NAME, PRICE, QUANTITY) VALUES(?, ?, ?, ?)";
		try 
		{
			// Execute SQL Insert
			int rows = jdbcTemplateObject.update(sql, order.getOrderNo(), order.getProductName(), order.getPrice(), order.getQuantity());
			
			// Return result of Insert
			return rows == 1 ? true : false;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * CRUD: update an entity
	 */
	public boolean update(Order order)
	{
		return true;
	}
	
	/**
	 * CRUD: delete an entity
	 */
	public boolean delete(Order order)
	{
		return false;
	}
	
    // ***** Dependencies and Helper Functions *****
    
    /**
     * IoC helper function.
     * 
     * @param dataSource Spring Data Source to inject into this DAO
     */
     public void setDataSource(DataSource dataSource) 
    {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
}
