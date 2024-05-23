package com.app.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.app.entities.User;

public interface UserDao {
	// signin into portal
	User signIn(String email, String password) throws SQLException;

	//add a method for getting user details(not admin)
	//born between specific dates
	List<User> getUserDetails(Date begin, Date end )throws SQLException;
	
	//new registration
	String voterRegistration(User newVoter) throws SQLException;
	
	//ChangePassword
	//String changePassword(String email,String oldPass, String newPass)  throws SQLException;
	


		
	}


