package domain.userStatus;

public enum UserStatusses {

	AWAY(new Away()),
	OFFLINE(new Offline()),
	ONLINE(new Online());
	
	private UserStatus status;
	
	private UserStatusses(UserStatus status){
		this.status = status;
	}
	
	public UserStatus getStatus(){
		return this.status;
	}
	
	public String toString(){
		return status.toString();
	}
	
}
