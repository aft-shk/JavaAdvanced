package com.app.tester;

import static com.app.utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.app.dao.UserDao;
import com.app.dao.UserDaoImpl;
import com.app.entities.Role;
import com.app.entities.User;
import static java.time.LocalDate.parse;
import static com.app.entities.Role.valueOf;

public class DetailsByDobAndRole {

	public static void main(String[] args) {
		try (SessionFactory sf = getFactory(); Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter begin dob");
			LocalDate d1 = LocalDate.parse(sc.next());
			System.out.println("Enter end dob");
			LocalDate d2 = LocalDate.parse(sc.next());
			System.out.println("Enter role ");
			Role r1 = Role.valueOf(sc.next().toUpperCase());
			
			// create user dao instance
			UserDao userDao = new UserDaoImpl();
			userDao.getDetailsBydobAndRole(d1, d2,r1).forEach(System.out::println);
		} // JVM sf.close() => cleaning up of DBCP
		catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
