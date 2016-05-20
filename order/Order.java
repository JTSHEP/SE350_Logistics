package order;

import java.util.ArrayList;

public interface Order {

	
	
	public String getId();
	public int getDay();
	public String getDestination();
	public ArrayList<OrderItem> getContents();
	
}
