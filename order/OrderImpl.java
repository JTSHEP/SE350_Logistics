package order;

import java.util.ArrayList;

public class OrderImpl implements Order {
	
	
	private String id;
	private int day;
	private String destination;
	private ArrayList<OrderItem> contents;
	
	public OrderImpl(String id, int day, String destination, ArrayList<OrderItem> contents)
	{
		this.id=id;
		this.day=day;
		this.destination=destination;
		this.contents=contents;
	}


	public String getId() {
		return id;
	}

	public int getDay() {
		return day;
	}


	public String getDestination() {
		return destination;
	}


	public ArrayList<OrderItem> getContents() {
		return contents;
	}

}
