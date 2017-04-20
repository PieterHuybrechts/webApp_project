package domain.userStatus;

import domain.User;

public class Offline implements UserStatus{

	private String name = "Offline";
	
	@Override
	public void goOnline(User u) {
		//u.setCurrentStatus(UserStatusses.ONLINE.getStatus());
	}

	@Override
	public void goOffline(User u) {
		//u.setCurrentStatus(UserStatusses.OFFLINE.getStatus());
	}

	@Override
	public void goAway(User u) {
		//u.setCurrentStatus(UserStatusses.AWAY.getStatus());
	}
	
	public String toString(){
		return this.name;
	}
	
	

}
