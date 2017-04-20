package domain.userStatus;

import domain.User;

public class Online implements UserStatus{

	private String name = "Online";
	
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
