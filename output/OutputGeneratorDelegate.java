package output;

import java.util.ArrayList;
import java.util.HashMap;

import order.OrderManager;
import order.OrderNotFoundException;

public class OutputGeneratorDelegate {
	
	public static void genertateOutput(ArrayList<OrderItemLogisticsRecord> solutions) throws OrderNotFoundException
	{
		OrderManager om = OrderManager.getInstance();
		String orderId = solutions.get(0).getOrderId();
		
		
		System.out.println("------------------");
		System.out.println("Order ID: "+orderId);
		System.out.println("Order Day: "+om.getDay(orderId));
		System.out.println("Destination: "+om.getDestination(orderId));
		System.out.println("List of Order Items:");
		int totalCost=0;
		int firstDay=Integer.MAX_VALUE;
		int lastDay=Integer.MIN_VALUE;
		HashMap<String, Integer> contents = om.getContents(orderId);
		
		for(String oid:contents.keySet())
		{
			System.out.println("    Item ID: "+oid+"  QTY: "+contents.get(oid));
		}
		
		for(OrderItemLogisticsRecord s:solutions)
		{
			totalCost+=s.getTotalCost();
			if(s.getFirstDay()<firstDay)
				firstDay=s.getFirstDay();
			if(s.getLastDay()>lastDay)
				lastDay=s.getLastDay();
		}
		
		System.out.println("");
		System.out.println("Processing Solution:");
		System.out.println("Order ID: "+orderId);
		System.out.println("Destination: "+om.getDestination(orderId));
		System.out.println("Total Cost: $"+totalCost);
		System.out.println("First Delivery Day: "+firstDay);
		System.out.println("Last Delivery Day: "+lastDay);
		System.out.println("Order Items:");
		System.out.println("Item ID      QTY         Cost       Number of Sources    First Day       Last Day");
		for(OrderItemLogisticsRecord s:solutions)
		{
		System.out.println(s.getItemId()+"       "+s.getQty()+"          "+s.getTotalCost()+"          "+s.getNumSources()+"                   "+s.getFirstDay()+"                 "+s.getLastDay());
		}
		System.out.println("------------------");
	}

}
