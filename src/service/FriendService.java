package service;

import java.util.List;

import db.DbException;
import db.FriendDb;
import db.FriendDbMemory;
import domain.User;

public class FriendService {

	private FriendDb db;
	
	public FriendService(){
		db = new FriendDbMemory();
	}
	
	public void addFriend(User u1,User u2) throws ServiceException{
		try {
			db.addFriend(u1, u2);
		} catch (DbException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public List<User> getAllFriendsOf(User u){
		return db.getAllFriendsOf(u);
	}
	
}
