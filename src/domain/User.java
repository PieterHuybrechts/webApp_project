package domain;

import service.UserService;

public class User {
    String email;
    String username;
    String salt;
    String hashedPasswd;
    
    //UserStatus currentStatus;
    String currentStatus;

    public User(){
    	currentStatus = "online";
    }
    
    public void setEmail(String email) throws DomainException{
        if(email==null || email.isEmpty()){
            throw new DomainException("Email can't be empty.");
        }else{
            email=email.toLowerCase();
            if(!email.contains("@")){
                throw new DomainException("Not a valid email.");
            }

            String secondPart;
            int addPos = email.indexOf("@");
            secondPart = email.substring(addPos+1);
            if(!secondPart.contains(".")){
                throw new DomainException("Not a valid email.");
            }
            int dotPos = secondPart.indexOf(".");
            secondPart=secondPart.substring(0, dotPos);
        }
        
        this.email=email;
    }
    
    public void setUsername(String username) throws DomainException{
        if(username==null || username.isEmpty()){
            throw new DomainException("First name can't be empty.");
        }
        
        this.username=username;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
    
    public void setPassword(String password) throws DomainException{
    	if(password==null||password.isEmpty()){
    		throw new DomainException("Password can't be empty");
    	}
    	
    	setSalt(UserService.generateSalt());
    	setPassWdHash(UserService.hashPassword(password, salt));
    }
    
    public void setPassWdHash(String passWdHash) throws DomainException{
        if(passWdHash==null || passWdHash.isEmpty()){
            throw new DomainException("Password can't be empty");
        }
        //System.out.println("hashlengt:"+passWdHash.length());
        this.hashedPasswd=passWdHash;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public String getSalt(){
        return this.salt;
    }
    
    public String getHashedPasswd(){
        return this.hashedPasswd;
    }
    
    public void setCurrentStatus(String status){
    	currentStatus = status;
    }
    
    public String getCurrentStatus(){
    	return this.currentStatus;
    }
}
