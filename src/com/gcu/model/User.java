package com.gcu.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User
{
	@NotNull(message="First name cannot be null.")
    @Size(min=2, max=30, message="First name must be between 2 and 30 characters.")
	private String firstName;
    
	@NotNull(message="Last name cannot be null.")
    @Size(min=2, max=30, message="Last name must be between 2 and 30 characters.")
	private String lastName;
    
	@NotNull(message="Gender cannot be null.")
    @Min(value=0, message="Invalid value for gender.")
    @Max(value=1, message="Invalid value for gender.")
	private int gender;

	public User()
	{
		firstName = "";
		lastName = "";
		gender = 0;
	}
	public User(String firstName, String lastName, int gender)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	public int getGender()
	{
		return gender;
	}
	public void setGender(int gender)
	{
		this.gender = gender;
	}
}
