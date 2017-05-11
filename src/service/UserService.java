package service;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import db.DbException;
import db.UserDb;
import db.UserDbRelational;
import domain.User;

public class UserService {

	
    private UserDb db;
    private Map<String, MenuItem> menuItems = new LinkedHashMap<String, MenuItem>();

    public UserService(Properties properties) throws DbException {
        db = new UserDbRelational(properties);

        createMenu();
    }
    
    //Create

    public void addUser(User u) throws ServiceException {
        try {
			db.addUser(u);
		} catch (DbException e) {
			throw new ServiceException(e.getMessage(),e);
		}
    }
    
    //Read
    
    public User getUser(String email) throws ServiceException {
        try {
			return db.getUser(email);
		} catch (DbException e) {
			throw new ServiceException("User could not be found",e);
		}
    }
    
    public List<User> getAllUsers() throws ServiceException {
        try {
			return db.getAllUsers();
		} catch (DbException e) {
			throw new ServiceException("Could not get all users",e);
		}
    }
    
    //Update
    
    public void updateUser(User u) throws ServiceException {
        try {
			db.updateUser(u);
		} catch (DbException e) {
			throw new ServiceException(e.getMessage(),e);
		}
    }
    
    //Delete

    public void removeUser(User u) {
        db.removeUser(u);
    }

    public void removeUser(String email) {
        db.removeUser(email);
    }

    public void removeAllUsers() {
        db.removeAllUsers();
    }







    public User getAuthenticatedUser(String email, String password) throws ServiceException {
        User u = getUser(email);

        if (u == null || !u.getHashedPasswd().equals(hashPassword(password, u.getSalt()))) {
            throw new ServiceException("Email or password is incorrect.");
        } else {
            return u;
        }
    }

    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte salt[] = random.generateSeed(20);
        return new BigInteger(1,salt).toString(16);
    }

    public static String hashPassword(String password, String seed) {
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-256");
            crypt.reset();
            crypt.update(seed.getBytes("UTF-8"));
            crypt.update(password.getBytes("UTF-8"));
            byte[] digest = crypt.digest();
            return new BigInteger(1, digest).toString(16);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public boolean isAuthenticationNeeded(String action) {
        MenuItem item = menuItems.get(action);
        boolean authenticationNeeded;
        if (item == null) {
            authenticationNeeded = false;
        } else {
            authenticationNeeded = item.isAuthenticationNeeded();
        }
        return authenticationNeeded;
    }

    private void createMenu() {
        menuItems.put("goToHome", new MenuItem("goToHome", "Home"));
        menuItems.put("goToChat", new MenuItem("goToChat", "Chat", true));
        menuItems.put("goToLogin", new MenuItem("goToLogin", "Log in"));
        menuItems.put("goToRegister", new MenuItem("goToRegister", "Register"));
        menuItems.put("logout", new MenuItem("logout", "Log out", true));
    }

    public List<MenuItem> getMenu(boolean authenticated) {
        List<MenuItem> items = new ArrayList<MenuItem>();
        for (MenuItem item : menuItems.values()) {
            if (item.isAuthenticationNeeded() == authenticated) {
                   items.add(item);
            }
        }
        return items;
    }

}
