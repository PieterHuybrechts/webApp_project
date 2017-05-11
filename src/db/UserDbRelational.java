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

	@Override
	public void addUser(User u) throws DbException {
		

		String sql = "insert into r0466226_webApp.users(email,username,status,salt,password_hash) values(?,?,?,?,?)";

		try {
			createConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, u.getEmail());
			statement.setString(2, u.getUsername());
			statement.setString(3, u.getStatus());
			statement.setString(4, u.getSalt());
			statement.setString(5, u.getHashedPasswd());
			statement.execute();

		} catch (SQLException | ClassNotFoundException e) {
			throw new DbException("Couldn't add the user");
		} finally {
			try {statement.close();} catch (SQLException e) {}
			closeConnection();
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
		
		User u = null;

		String sql = "SELECT * FROM r0466226_WebApp.users WHERE email = ?";

		try {
			createConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			ResultSet set = statement.executeQuery();
			set.next();
			String username = set.getString("username");
			String status = set.getString("status");
			String salt = set.getString("salt");
			String passwordHash = set.getString("password_hash");

			try {
				u = new User();
				u.setEmail(email);
				u.setUsername(username);
				u.setStatus(status);
				u.setPassWdHash(passwordHash);
				u.setSalt(salt);
			} catch (DomainException e) {

			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new DbException(e.getMessage(), e);
		}finally {
			try {statement.close();} catch (SQLException e) {}
			closeConnection();
		}

		

		return u;
	}

	@Override
	public List<User> getAllUsers() throws DbException {
		
		List<User> users = new ArrayList<>();

		String sql = "SELECT * FROM r0466226_WebApp.users";
		try {
			createConnection();
			statement = connection.prepareStatement(sql);
			ResultSet set = statement.executeQuery();

			while (set.next()) {
				String email = set.getString("email");
				String username = set.getString("userName");
				String status = set.getString("status");
				String salt = set.getString("salt");
				String passwordHash = set.getString("password_hash");
				User u = new User();

				u.setEmail(email);
				u.setUsername(username);
				u.setStatus(status);
				u.setSalt(salt);
				u.setPassWdHash(passwordHash);

				users.add(u);
			}
		} catch (SQLException | DomainException | ClassNotFoundException e) {
			throw new DbException("Not all users could be retrieved");
		} finally {
			try {statement.close();} catch (SQLException e) {}
			closeConnection();
		}
		
		
		return users;
	}

	@Override
	public void updateUser(User u) throws DbException {
		String email=u.getEmail();
		String username = u.getUsername();
		String status = u.getStatus();
		String salt = u.getSalt();
		String passwordHash = u.getHashedPasswd();

		String sql="UPDATE r0466226_WebApp.users SET username=?,status=?,salt=?,password_hash=? WHERE email=?";
		
		try{
			createConnection();
			statement=connection.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, status);
			statement.setString(3, salt);
			statement.setString(4, passwordHash);
			statement.setString(5, email);
			statement.execute();
		}catch (SQLException | ClassNotFoundException e) {
			throw new DbException("Couldn't update the user",e);
		} finally {
			try {statement.close();} catch (SQLException e) {}
			closeConnection();
		}
		
	}

}
