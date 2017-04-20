package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import domain.DomainException;
import domain.User;

public class UserDbRelational implements UserDb {

	private PreparedStatement statement;
	private Connection connection;
	private Properties properties;

	public UserDbRelational(Properties properties) {
		this.properties = properties;
	}

	private Connection createConnection() throws DbException {
		try {
			String url = properties.getProperty("url");
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection(url, this.properties);
		} catch (SQLException | ClassNotFoundException e) {
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public void addUser(User u) throws DbException {
		connection=createConnection();
		
		String sql = "insert into r0466226_webApp.users(email,username,salt,password_hash) values(?,?,?,?)";
		
		try{
			statement = connection.prepareStatement(sql);
			statement.setString(1, u.getEmail());
			statement.setString(2, u.getUsername());
			statement.setString(3, u.getSalt());
			statement.setString(4, u.getHashedPasswd());
			statement.execute();
			statement.close();
		}catch (SQLException e) {
			try{
				statement.close();
			}catch(SQLException e1){
				
			}
			throw new DbException(e.getMessage(),e);
		}
		
		try {
			connection.close();
		} catch (SQLException e) {
			
		}
	}

	@Override
	public void removeUser(User u) {

	}

	@Override
	public void removeUser(String email) {

	}

	@Override
	public void removeAllUsers() {

	}

	@Override
	public User getUser(String email) throws DbException {
		connection=createConnection();
		User u=null;
		
		String sql="SELECT * FROM r0466226_WebApp.users WHERE email = ?";
		
		try{
			statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			ResultSet set = statement.executeQuery();
			set.next();
			String username = set.getString(2);
			String salt = set.getString(3);
			String passwordHash = set.getString(4);
			
			try{
				u = new User();
				u.setEmail(email);
				u.setUsername(username);
				u.setPassWdHash(passwordHash);
				u.setSalt(salt);
			}catch (DomainException e) {
				
			}
			
			statement.close();
			
		}catch(SQLException e){
			try {
				statement.close();
			} catch (SQLException e1) {
				
			}
			
			throw new DbException(e.getMessage(),e);
		}
		
		try {
			connection.close();
		} catch (SQLException e) {
			throw new DbException(e.getMessage(),e);
		}
		
		return u;
	}

	@Override
	public List<User> getAllUsers() throws DbException {
		connection = createConnection();
		List<User> users = new ArrayList<>();
		
		String sql = "SELECT * FROM r0466226_WebApp.users";
		try {
			statement = connection.prepareStatement(sql);
			ResultSet set = statement.executeQuery();
			
			while(set.next()){
				String email = set.getString("email");
				String username = set.getString("userName");
				String salt = set.getString("salt");
				String passwordHash = set.getString("password_hash");
				User u = new User();
				
				u.setEmail(email);
				u.setUsername(username);
				u.setSalt(salt);
				u.setPassWdHash(passwordHash);
				
				users.add(u);
			}			
		} catch (SQLException | DomainException e) {
			throw new DbException("Not all users could be retrieved");
		}finally{
			try {statement.close();} catch (SQLException e) {}
			try {connection.close();} catch (SQLException e) {}
		}
		
		return users;
	}

	@Override
	public void updateUser(User u) {

	}

}
