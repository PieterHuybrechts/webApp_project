package service;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import db.DbException;
import db.FriendDb;
import db.FriendDbRelational;
import domain.User;

public class FriendService {

	private FriendDb db;
	
	public FriendService(){
		try {
			Properties properties = new Properties();
			Context env = (Context)new InitialContext().lookup("java:comp/env");
			
			properties.put("url", (String)env.lookup("url"));
			properties.put("user", (String)env.lookup("user"));
			properties.put("password", (String)env.lookup("password"));
			properties.put("ssl", (String)env.lookup("ssl"));
			properties.put("sslfactory", (String)env.lookup("sslfactory"));
			
			db = new FriendDbRelational(properties);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public FriendService(Properties properties){
		db = new FriendDbRelational(properties);
	}
	
	public void addFriend(User u1,User u2) throws ServiceException{
		try {
			db.addFriend(u1, u2);
		} catch (DbException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void addFriend(String user1_email,String user2_email) throws ServiceException{
		try {
			db.addFriend(user1_email, user2_email);
		} catch (DbException e) {
			throw new ServiceException(e.getMessage(),e);
		}
	}
	
	public List<User> getAllFriendsOf(User u) throws ServiceException{
		try {
			return db.getAllFriendsOf(u.getEmail());
		} catch (DbException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	
	
}
