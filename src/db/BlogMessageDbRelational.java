package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import domain.BlogMessage;

public class BlogMessageDbRelational implements BlogMessageDb {

	private Properties properties;
	private Connection connection;
	private PreparedStatement statement;

	public BlogMessageDbRelational(Properties properties) {
		this.properties = properties;
	}

	private void createConnection() throws DbException, ClassNotFoundException, SQLException {
		String url = properties.getProperty("url");
		Class.forName("org.postgresql.Driver");
		connection = DriverManager.getConnection(url, this.properties);
	}

	private void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {

		}
	}

	private void closeStatement() {
		try {
			statement.close();
		} catch (SQLException e) {

		}
	}

	@Override
	public void addBlogMessage(BlogMessage message) throws DbException{
		String sql = "INSERT into r0466226_webApp.blogmessages(subject,username) VALUES(?,?)";
		
		try{
			createConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, message.getSubject());
			statement.setString(2, message.getMessage());
			statement.execute();
		}catch(SQLException | ClassNotFoundException  e){
			throw new DbException("Could not add this message",e);
		}finally{
			closeStatement();
			closeConnection();
		}

	}

	@Override
	public List<BlogMessage> getAllBlogMessages() throws DbException{
		List<BlogMessage> messages = new ArrayList<BlogMessage>();
		
		String sql = "GET * FROM r0466226_webApp.blogmessages";
		
		try {
			createConnection();
			statement = connection.prepareStatement(sql);
			ResultSet set = statement.executeQuery();
			while(set.next()){
				BlogMessage blogMessage = new BlogMessage();
				String subject = set.getString("subject");
				String message = set.getString("message");
				
				blogMessage.setMessage(message);
				blogMessage.setSubject(subject);
				messages.add(blogMessage);
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new DbException("Could not get all messages",e);
		}finally{
			closeStatement();
			closeConnection();
		}
		
		return messages;
	}

}
