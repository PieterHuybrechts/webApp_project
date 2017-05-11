package db;

import java.util.List;

import domain.User;

public interface UserDb {

    public void addUser(User u) throws DbException;

    public void removeUser(User u);

    public void removeUser(String email);

    public void removeAllUsers();

    public User getUser(String email) throws DbException;

    public List<User> getAllUsers() throws DbException;

    public void updateUser(User u) throws DbException;
}