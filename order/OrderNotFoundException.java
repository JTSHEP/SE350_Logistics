package order;

public class OrderNotFoundException extends Exception {
	
	public OrderNotFoundException(){super();}
	public OrderNotFoundException(String id){super(id);}

}
