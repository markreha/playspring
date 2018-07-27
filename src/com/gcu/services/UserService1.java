package com.gcu.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcu.model.User;

@RestController
@RequestMapping("/service1")
public class UserService1
{
	@GetMapping("/users")
	public List<User> getUsers()
	{
		// Create some Users
		List<User> users = new ArrayList<User>();
		users.add(new User("Brianna", "Reha", 0));
		users.add(new User("Justine", "Reha", 0));
		
		// Return a List of Users
		return users;
	}
}
