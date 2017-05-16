package domain;

public class BlogMessage {
	private String subject;
	private String message;
	
	public BlogMessage(){
		
	}
	
	public void setSubject(String subject){
		this.subject = subject;
	}
	
	public void setMessage(String message){
		this.message = message;
	}
	
	public String getSubject(){
		return this.subject;
	}
	
	public String getMessage(){
		return this.message;
	}
}
