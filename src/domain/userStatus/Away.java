package domain.userStatus;

import domain.User;

public class Away implements UserStatus{

	private String name = "Away";
	
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
