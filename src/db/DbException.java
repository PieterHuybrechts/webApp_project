package db;


public class DbException extends Exception{

	private static final long serialVersionUID = 452897794834597564L;

	public DbException(){
        super();
    }
    public DbException(String arg0){
        super(arg0);
    }
    
    public DbException(Throwable arg0){
        super(arg0);
    }
    
    public DbException(String arg0,Throwable arg1){
        super(arg0, arg1);
    }
    
}
