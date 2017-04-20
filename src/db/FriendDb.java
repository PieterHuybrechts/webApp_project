package db;

import java.util.List;

import domain.User;

public interface FriendDb {

	public void addFriend(User u1, User u2) throws DbException;
	public List<User> getAllFriendsOf(User u);
	
}
