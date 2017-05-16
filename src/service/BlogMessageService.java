package service;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import db.BlogMessageDb;
import db.BlogMessageDbRelational;
import db.DbException;
import domain.BlogMessage;

public class BlogMessageService {
	
	BlogMessageDb db;
	
	public BlogMessageService(Properties properties){
		db = new BlogMessageDbRelational(properties);
	}
	
	public BlogMessageService(){
		Properties properties = new Properties();
		try {
			Context env = (Context)new InitialContext().lookup("java:comp/env");
			
			properties.put("url", (String)env.lookup("url"));
			properties.put("user", (String)env.lookup("user"));
			properties.put("password", (String)env.lookup("password"));
			properties.put("ssl", (String)env.lookup("ssl"));
			properties.put("sslfactory", (String)env.lookup("sslfactory"));
			
			db = new BlogMessageDbRelational(properties);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public void addMessage(BlogMessage message) throws ServiceException{
		try {
			db.addBlogMessage(message);
		} catch (DbException e) {
			throw new ServiceException(e.getMessage(),e);
		}
	}
	
	public List<BlogMessage> getAllBlogMessages(){
		try {
			return db.getAllBlogMessages();
		} catch (DbException e) {
			return null;
		}
	}
	
	
}
