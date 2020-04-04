package com.gcu.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gcu.business.OrdersBusinessInterface;
import com.gcu.model.Order;
import com.gcu.model.User;

@Controller
@RequestMapping("/user")
public class UserController
{
	OrdersBusinessInterface service;
    	Logger logger = LoggerFactory.getLogger(UserController.class);
		
	@RequestMapping(path="/add", method=RequestMethod.GET)		// OR could use @GetMapping("/add")
	public ModelAndView displayForm() 
	{
		return new ModelAndView("addUser", "user", new User("", "", 0));
	}

	@RequestMapping(path="/adduser", method=RequestMethod.POST)		// OR could use @PostMapping("/adduser")
	public ModelAndView addUser(@Valid @ModelAttribute("user") User user, BindingResult result) 
	{
    		// Log the API call
    		logger.info("Entering UserController.addUser()");
		
		// Validate the form
		if (result.hasErrors())
		{
			return new ModelAndView("addUser", "user", user);
		}
		
		// Call Orders Business Service
		List<Order> orders = service.getAllOrders();

		// Display a list of Orders
		return new ModelAndView("displayOrders", "orders", orders);
	}
	
	@RequestMapping(path="/orders", method=RequestMethod.GET)		// OR could use @GetMapping("/orders")
	public ModelAndView getAllOrders() 
	{
		// Call Orders Business Service
		List<Order> orders = service.getAllOrders();

		// Display a list of Orders
		return new ModelAndView("displayOrders", "orders", orders);
	}

	@Autowired
	public void setOrdersService(OrdersBusinessInterface service)
	{
		this.service = service;
	}
}
