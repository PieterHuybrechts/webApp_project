package service;

import java.util.List;
import java.util.Properties;

import db.BlogMessageDb;
import db.BlogMessageDbRelational;
import db.DbException;
import domain.BlogMessage;

public class BlogMessageService {
	
	BlogMessageDb db;
	
	public BlogMessageService(Properties properties){
		db = new BlogMessageDbRelational(properties);
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
