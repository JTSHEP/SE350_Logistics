package order;

import java.util.ArrayList;
import java.util.HashMap;

import facility.FacilityManager;

public class OrderManager {
	
	private static OrderManager instance = null;
	private ArrayList<Order> orders;
	
	private OrderManager()
	{
		orders=OrderReader.read();
	}
	
	public ArrayList<String> getOrderIds()
	{
		ArrayList<String >toReturn = new ArrayList<String>();
		for(Order o:orders)
		{
			toReturn.add(o.getId());
		}
		return toReturn;
	}
	
	public HashMap<String,Integer> getContents(String orderId) throws OrderNotFoundException
	{
		Order x  = findByString(orderId);
		ArrayList<OrderItem> items = x.getContents();
		HashMap<String,Integer> toReturn = new HashMap<String,Integer>();
		for(OrderItem i:items)
		{
			toReturn.put(i.getId(), i.getQty());
		}
		return toReturn;
	}
	public String getDestination(String orderId) throws OrderNotFoundException
	{
		Order x = findByString(orderId);
		return x.getDestination();
	}
	
	public int getDay(String orderId) throws OrderNotFoundException
	{
		Order x = findByString(orderId);
		return x.getDay();
	}
	
	private Order findByString(String x) throws OrderNotFoundException
	{
		for(Order o:orders)
		{
			if(o.getId().equalsIgnoreCase(x))
				return o;
		}
		
		throw new OrderNotFoundException();
	}
	
	
	public static OrderManager getInstance()
	{
		if(instance==null)
		{
			instance=new OrderManager();
		}
		
		return instance;
	}

}
