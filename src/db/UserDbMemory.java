package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.User;

public class UserDbMemory implements UserDb {

    private static final Map<String, User> USERS = new HashMap<>();

    public UserDbMemory() {

    }

    @Override
    public void addUser(User u) throws DbException {
    	if(USERS.get(u.getEmail())!=null){
    		throw new DbException("Email already in use");
    	}
    	
        USERS.put(u.getEmail(), u);
    }

    @Override
    public void removeUser(User u) {
        USERS.remove(u.getEmail());
    }

    @Override
    public void removeUser(String email) {
        USERS.remove(email);
    }

    @Override
    public void removeAllUsers() {
        USERS.clear();
    }

    @Override
    public User getUser(String email) {
        return USERS.get(email);
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(USERS.values());
    }

    @Override
    public void updateUser(User u) {
        USERS.put(u.getEmail(), u);
    }

}
