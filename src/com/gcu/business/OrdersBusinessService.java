package com.gcu.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.DataAccessInterface;
import com.gcu.model.Order;

public class OrdersBusinessService implements OrdersBusinessInterface
{
	@SuppressWarnings("rawtypes")
	@Autowired
	DataAccessInterface dao;
	
	public void init()
	{
		System.out.println("init() in OrdersBusinessService");
	}
	
	public void destroy()
	{
		System.out.println("destroy() in OrdersBusinessService");
	}
	
	@SuppressWarnings("unchecked")
	public List<Order> getAllOrders()
	{
		return dao.findAll();
	}
	
	@Override
	public void test()
	{
		System.out.println("Hello from OrdersBusinessService");
	}
}
