package domain.userStatus;

import domain.User;

public interface UserStatus {

	public void goOnline(User u);
	public void goOffline(User u);
	public void goAway(User u);
	
}
