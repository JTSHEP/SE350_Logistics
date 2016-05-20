package output;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import facility.FacilityManager;
import facility.NegativeItemQtyException;
import item.ItemManager;
import item.ItemNotFoundException;
import network.FacilityNotFoundException;
import network.NetworkManager;
import order.OrderManager;
import order.OrderNotFoundException;

public class OrderProcessor {
	
	
	private ItemManager im;
	private NetworkManager nm;
	private FacilityManager fm;
	private OrderManager om;
	private ArrayList<String> orders;
	private ArrayList<String> itemList;

	
	
	public OrderProcessor(ItemManager im, NetworkManager nm, FacilityManager fm, OrderManager om)
	{
		this.im=im;
		this.nm=nm;
		this.fm=fm;
		this.om=om;
		orders = om.getOrderIds();
		itemList = im.getItemList();
	
	}
	
	public void processOrders() throws OrderNotFoundException, FacilityNotFoundException, ItemNotFoundException
	{
		
		for(String o:orders)
		{
		processOrder(o);
		}
		
		StatusReportGenerator.generateAll(fm, nm, im);
		
	}
	
	private void processOrder(String orderId) throws OrderNotFoundException, FacilityNotFoundException, ItemNotFoundException
	{
		
		HashMap<String,Integer> contents = om.getContents(orderId);
		ArrayList<OrderItemLogisticsRecord> solutions = new ArrayList<OrderItemLogisticsRecord>();
		
		for(String i:itemList)
		{
			if(contents.containsKey(i))
			{
				solutions.add(processOrderItem(orderId,i,contents));
			}
		}
		
		OutputGeneratorDelegate.genertateOutput(solutions);
	}
	
	private OrderItemLogisticsRecord processOrderItem(String orderId,String itemId,HashMap<String,Integer> contents) throws OrderNotFoundException, FacilityNotFoundException, ItemNotFoundException
	{
		ArrayList<FacilityRecord> facilityRecords = new ArrayList<FacilityRecord>();
		
		ArrayList<String> facilitiesWithItem = fm.getFacilitiesWithItem(itemId);
		
		for(String facility:facilitiesWithItem)
		{
			int travelTime=Integer.MAX_VALUE;
			travelTime = (int)Math.ceil(nm.shortestPathDays(facility, om.getDestination(orderId), 8, 50));
			int processingTime = fm.getProcessingTime(facility, Math.min(fm.numItems(facility, itemId),contents.get(itemId)),om.getDay(orderId));
			
			facilityRecords.add(new FacilityRecord(facility,Math.min(fm.numItems(facility, itemId),contents.get(itemId)),om.getDay(orderId),processingTime,travelTime));
		}
		
		Collections.sort(facilityRecords);
		ArrayList<FacilityRecord> solution = new ArrayList<FacilityRecord>();
		int qtyNeeded = contents.get(itemId);
		while(qtyNeeded>0)
		{
			if(facilityRecords.isEmpty())
			{
				break;
			}
			
			FacilityRecord current = facilityRecords.remove(0);
			
			
			if((qtyNeeded-current.getNumOfItemsAvailible())<=0)
			{
				
				try {
					fm.removeItem(current.getName(), itemId, qtyNeeded);
					current.processItems(qtyNeeded);
				} catch (NegativeItemQtyException | output.NegativeItemQtyException e) {
					e.printStackTrace();
				}
				
				fm.book(current.getName(), qtyNeeded, om.getDay(orderId));
				solution.add(current);
				qtyNeeded=0;
			}
			else
			{
				
				try {
					fm.removeItem(current.getName(), itemId, current.getNumOfItemsAvailible());
					current.processItems(current.getNumOfItemsAvailible());
				} catch (NegativeItemQtyException | output.NegativeItemQtyException e) {
					e.printStackTrace();
				}
				
				fm.book(current.getName(), current.getNumOfItemsAvailible(), om.getDay(orderId));
				solution.add(current);
				qtyNeeded-=current.getNumOfItemsAvailible();
			}
			
			
			
			
			
		}
		
		
		return new OrderItemLogisticsRecord(solution, orderId, itemId);
		
		
	}
	
	
	
	
	
	
}
	
	
	
	
	