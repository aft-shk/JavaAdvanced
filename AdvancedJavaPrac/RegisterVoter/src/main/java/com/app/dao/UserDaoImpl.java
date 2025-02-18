package com.app.dao;

import java.sql.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import static com.app.utils.DBUtils.*;
import com.app.entities.User;

public class UserDaoImpl implements  UserDao {
	private Connection cn;
	private PreparedStatement pst1,pst2,pst3;
	
	
	public UserDaoImpl() throws SQLException{
		//def ctor of USERDAOIMPL
		cn = openConnection();
		pst1 = cn.prepareStatement("select * from users where email=? and password=?");
		pst2 = cn.prepareStatement("select * from users where role = voter and dob between ? and ?");
		pst3 = cn.prepareStatement("insert into users values(default,?,?,?,?,?,?,?)");
		
		
	}

	@Override
	public User signIn(String email, String password) throws SQLException {
		pst1.setString(1, email);
		pst1.setString(2, password);
		
		pst1.executeQuery();
		
		
		
		
		return null;
	}

	@Override
	public List<User> getUserDetails(Date begin, Date end) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String voterRegistration(User newVoter) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
