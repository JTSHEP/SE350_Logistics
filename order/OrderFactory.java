package order;

import java.util.ArrayList;

public class OrderFactory {
	
	public static Order generateOrder(String id, int day, String destination, ArrayList<OrderItem> contents)
	{
		return new OrderImpl(id,day,destination,contents);
	}

}
