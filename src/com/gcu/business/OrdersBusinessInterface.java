package com.gcu.business;

import java.util.List;

import com.gcu.model.Order;

public interface OrdersBusinessInterface
{
	public void init();
	public void destroy();
	public void test();
	public List<Order> getAllOrders();
}
