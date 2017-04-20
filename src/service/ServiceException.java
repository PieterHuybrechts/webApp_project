package service;

public class ServiceException extends Exception{
	
	private static final long serialVersionUID = -5532751923084103006L;

	public ServiceException(){
        super();
    }
    public ServiceException(String arg0){
        super(arg0);
    }
    
    public ServiceException(Throwable arg0){
        super(arg0);
    }
    
    public ServiceException(String arg0,Throwable arg1){
        super(arg0, arg1);
    }
	
}
