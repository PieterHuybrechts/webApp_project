package db;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.User;

public class FriendDbMemory implements FriendDb{

	private Map<User,List<User>> friends;
	
	public FriendDbMemory() {
		friends = new HashMap<>();
	}
	
	@Override
	public void addFriend(User u1, User u2) throws DbException {
		if(friends.get(u1)==null){
			friends.put(u1, new ArrayList<>());
		}else{
			if(friends.get(u1).contains(u2)){
				throw new DbException("These users are already friends");
			}
			
			friends.get(u1).add(u2);
		}
		
		if(friends.get(u2)==null){
			friends.put(u2, new ArrayList<>());
		}else{
			if(friends.get(u2).contains(u1)){
				throw new DbException("These users are already friends");
			}
			
			friends.get(u2).add(u1);
		}
	}

	@Override
	public List<User> getAllFriendsOf(User u) {
		List<User> users = null;
		users = friends.get(u);
		return users;
	}

}
