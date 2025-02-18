package com.app.beans;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

import com.app.dao.UserDaoImpl;
import com.app.entities.User;

public class UserBean {
//state -- request params from clnt mapped to Java bean properties
	private String email;
	//add more properties for voter registration
	private String fname;
	private String lname;
	private String password;
	private String dob;// string dob since WC cant transfer ref type to primitive type
	//dependency -- dao layer
	private UserDaoImpl userDao;
	//add a property to store validated user details
	private User userDetails;
	private String message;
	//def ctor 
	public UserBean() throws SQLException{
		// create dao instance
		userDao=new UserDaoImpl();
		System.out.println("user bean created...");
	}
	//getter n setter
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserDaoImpl getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}
	public User getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(User userDetails) {
		this.userDetails = userDetails;
	}
	
	public String getMessage() {
		return message;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	//Add B.L method for user authentication
	public String validateUser() throws SQLException{
		System.out.println("in validate user "+email+" "+password);
		//bean --> invokes dao's method
		userDetails=userDao.signIn(email, password);
		if(userDetails == null) {
			//invalid login
			message="Invalid Email or Password , Please retry!";
			return "login";// bean rets navigational outcome to the jsp
		}
		//=> valid login --> chk role based authorization
		message="Login Successful!";
		if(userDetails.getRole().equals("admin"))
			return "admin_main";
		//=> voter login
		if(userDetails.isStatus())
			return "logout";
		//not yet voted
		return "candidate_list";
	}
	// add a BL method for validating i/ps then registration
	public String validateAndRegister() throws SQLException {
		//parse dob --> LocalDate n validate
		//incase invalid age--return err mesg
		//o.w invoke dao's method for reg.
		LocalDate birthDate = LocalDate.parse(dob);
		
		int age = Period.between(birthDate, LocalDate.now()).getYears();
		if(age < 21) 
			return "registration failed";
		User voter = new User(fname,lname,email,password,Date.valueOf(birthDate));
		
		return userDao.voterRegistration(voter);
	}
	
	
	
}
