package com.gcu.services;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;

import java.util.ArrayList;
import java.util.List;

import com.gcu.model.User;

@Path("service2")
public class UserService2
{
    @GET
    @Path("/users")
    @Produces("application/json")
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
