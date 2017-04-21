package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import domain.DomainException;
import domain.User;

public class FriendDbRelational implements FriendDb{

	private Properties properties;
	private Connection connection;
	private PreparedStatement statement;
	
	public FriendDbRelational(Properties properties) {
		this.properties = properties;
	}
	
	private void createConnection() throws DbException, ClassNotFoundException, SQLException {
			String url = properties.getProperty("url");
			Class.forName("org.postgresql.Driver");
			connection =  DriverManager.getConnection(url, this.properties);
	}
	
	private void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			
		}
	}
	
	private void closeStatement(){
		try {
			statement.close();
		} catch (SQLException e) {
			
		}
	}
	
	@Override
	public void addFriend(User u1, User u2) throws DbException {
		addFriend(u1.getEmail(), u2.getEmail());
	}
	
	@Override
	public void addFriend(String user1_email, String user2_email) throws DbException {
		String sql = "insert into r0466226_webApp.friends(user1_email,user1_email) values(?,?)";
		
		try {
			createConnection();
			statement=connection.prepareStatement(sql);
			statement.setString(1, user1_email);
			statement.setString(2, user2_email);
			statement.execute();
		} catch (SQLException | ClassNotFoundException e) {
			throw new DbException("Couldn't add this friendship.");
		}finally{
			closeStatement();
			closeConnection();
		}
	}

	@Override
	public List<User> getAllFriendsOf(User u) throws DbException {
		return getAllFriendsOf(u.getEmail());
	}

	@Override
	public List<User> getAllFriendsOf(String email) throws DbException {
		List<User> users=new ArrayList<>();
		
		String sql = "SELECT * FROM r0466226_webapp.users INNER JOIN (SELECT user1_email AS email FROM r0466226_webapp.friends WHERE user2_email=? UNION SELECT user2_email FROM r0466226_webapp.friends WHERE user1_email=?) u USING (email)";
		
		try {
			createConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			statement.setString(2, email);
			ResultSet set = statement.executeQuery();
			
			while(set.next()){
				User user = new User();
				String mail = set.getString("email");
				String username = set.getString("userName");
				String salt = set.getString("salt");
				String passwordHash = set.getString("password_hash");
				

				user.setEmail(mail);
				user.setUsername(username);
				user.setSalt(salt);
				user.setPassWdHash(passwordHash);

				users.add(user);
			}
			
		} catch (ClassNotFoundException | DbException | SQLException | DomainException e) {
			throw new DbException("couldn't get all this users friends.");
		}finally {
			closeStatement();
			closeConnection();
		}
		
		return users;
	}
	
	



}
