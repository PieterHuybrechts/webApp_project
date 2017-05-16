package db;

import java.util.List;

import domain.BlogMessage;

public interface BlogMessageDb {

	public void addBlogMessage(BlogMessage message) throws DbException;
	public List<BlogMessage> getAllBlogMessages() throws DbException;
	
}
