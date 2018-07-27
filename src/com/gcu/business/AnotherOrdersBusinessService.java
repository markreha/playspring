package com.gcu.business;

import java.util.List;

import com.gcu.model.Order;

public class AnotherOrdersBusinessService implements OrdersBusinessInterface
{
	public void init()
	{
		System.out.println("init() in OrdersBusinessService");
	}
	
	public void destroy()
	{
		System.out.println("destroy() in OrdersBusinessService");
	}
	
	public List<Order> getAllOrders()
	{
		return null;
	}
	
	@Override
	public void test()
	{
		System.out.println("Hello from AnotherOrdersBusinessService");
	}

}
