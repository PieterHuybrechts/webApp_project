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
			
			String url = (String)env.lookup("url");
			String user = (String)env.lookup("user");
			String password = (String)env.lookup("password");
			String ssl = (String)env.lookup("ssl");
			String sslfactory = (String)env.lookup("sslfactory");
			
			properties.put("url", url);
			properties.put("user", user);
			properties.put("password", password);
			properties.put("ssl", ssl);
			properties.put("sslfactory", sslfactory);
			
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
	
	public List<BlogMessage> getAllBlogMessages() throws ServiceException{
		try {
			return db.getAllBlogMessages();
		} catch (DbException e) {
			throw new ServiceException(e.getMessage(),e);
		}
	}
	
	
}
