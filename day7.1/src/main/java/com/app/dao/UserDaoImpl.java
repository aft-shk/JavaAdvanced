package com.app.dao;

import static com.app.utils.HibernateUtils.getFactory;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.app.entities.Role;
import com.app.entities.User;

public class UserDaoImpl implements UserDao {

	@Override
	public String registerUser(User user) {
		// user : transient(neither associated with L1 cache nor in DB) exists in java heap
		String mesg = "Registration failed !!!!";
		// 1. get Session from SF
		Session session = getFactory().getCurrentSession();
		Session session2 = getFactory().getCurrentSession();// db cn is pooled out n
		System.out.println(session == session2);// t
		// L1 cache is created -- EMPTY
		// 2. Begin a Tx
		Transaction tx = session.beginTransaction();
		System.out.println("session is open " + session.isOpen() + " is conncted " + session.isConnected());// t t

		try {
			// 3. Session API -- public Serializable save(Object o) throws HibExc
			Serializable userId = session.save(user); // i/p transient object 
			/*
			 * Hibernate adds transient entity ref to L1 cache user : PERSISTENT (part of L1
			 * cache , BUT not yet part of DB)
			 */
			System.out.println("Id class " + userId.getClass());
			tx.commit();
			System.out.println("session is open " + session.isOpen() + " is conncted " + session.isConnected());// f f

			/*
			 * Hibernate performs 1. session.flush() --> hib does auto dirty
			 * checking(=checking the state of L1 cache against DB) performs DML -- insert
			 * Persistent entites (transient -> persistent : save | persist |saveOrUpdate |
			 * merge will gain DB identity, upon commmit session.close(); -L1 cache is
			 * destroyed n pooled out db cn rets to the DBCP....
			 */
			// rec will be inserted in db
			mesg = "User registered successfully , with ID =  " + userId;
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// re throw the exc to the caller
			throw e;
		}
		System.out.println("session is open " + session.isOpen() + " is conncted " + session.isConnected());// f f

		// user - DETACHED (doesn't exist in L1 cache , BUT has DB identity)
		return mesg;
	}

	@Override
	public User getUserDetails(Long userId) {
		User user=null;//user - does not exist (in the java heap n not in L1 cache)
		// 1. get session from SF (getCurrentSession)
		Session session=getFactory().getCurrentSession();
		//2. Begin a Tx
		Transaction tx=session.beginTransaction();
		try {
			//3. get user details by it's id
			user=session.get(User.class, userId);//select * from users where id=?
			user=session.get(User.class, userId);
			user=session.get(User.class, userId);
			user=session.get(User.class, userId);
			//user - null (id doesn't exist) OR in case of valid id -- 
			//PERSISTENT (part of L1 cache , does have db identity)
			tx.commit();
		} catch (RuntimeException e) {
			if(tx != null)
				tx.rollback();
			//re throw the exc to the caller
			throw e;
		}
		return user;//user - DETACHED | doesn 't exist
	}

	@Override
	public List<User> getUserlist() {
		List<User> users = null;
		String jpql = "select u from User u";
		
		//1.get session from sf
		Session session = getFactory().getCurrentSession();
		//begin transaction 
		Transaction tx = session.beginTransaction();
		try {
			/*
			 * //3. create query object n execute the same //Session Api // public Query<T>
			 * createQuery(String hql/jpql, Class <T> class ) // throws HiberNateExcep
			 */			
			users = session.createQuery(jpql,User.class).getResultList();
			tx.commit();
		}catch(RuntimeException e) {
			if(tx!= null)
				tx.rollback();
			throw e;
		}
		return users;
	}

	@Override
	public List<User> getDetailsBydobAndRole(LocalDate beginDate, LocalDate endDate, Role role) {
		
		/*
		 * jpql query = select u from User u where dob between :start  and :end and role=:rl
		 */
		
		List<User> user2 = null;
		String jpql = "select u from User u where u.dob between :begin and :end and u.role= :userRole";
		Session session = getFactory().getCurrentSession();
		//begin transaction 
		Transaction tx = session.beginTransaction();
		try {
			
			user2 = session.createQuery(jpql,User.class)
					.setParameter("begin", beginDate)
					.setParameter("end",endDate)
					.setParameter("userRole", role)
					.getResultList();
			
			tx.commit();
		}catch(RuntimeException e) {
			if(tx!= null)
				tx.rollback();
			
			throw e;
		}
		
		

		
		
		
		
		
		return user2;
	}

	@Override
	public User login(String email, String password) {
		Session session = getFactory().getCurrentSession();
		//begin transaction 
		User user3 = null;
		String jpql = "select u from User u where u.email=:em and u.password=:pass";
		Transaction tx = session.beginTransaction();
		try {
			user3 = session.createQuery(jpql,User.class)
					.setParameter("em", email)
					.setParameter("pass",password)
					.getSingleResult();
			
			tx.commit();
			System.out.println("Loged In....");
		}catch(RuntimeException e) {
			if(tx!= null)
				tx.rollback();
			throw e;
		}
		
		return user3;
	}

	@Override
	public String changePassword(String email, String oldPass, String newPass) {
		String mesg = "change password failed";
		String jpql = "update u Users set u.password"
		User user = null;
		Session session = getFactory().getCurrentSession();
		//begin transaction 
		Transaction tx = session.beginTransaction();
		try {
			user = session.createQuery(jpql,User.class)
					.setParameter("em", email)
					.setParameter("pass",oldPass)
					.getSingleResult();
			//valid login --> user:persistent
			user.setPassword(newPass);//updating the state of persistent entity
			
			tx.commit();
			mesg="pwd changed!";
			
		}catch(RuntimeException e) {
			if(tx!= null)
				tx.rollback();
			throw e;
		}
		user.setPassword("asdf234@234");
		return "Password Changed";
		
	}


}
