package output;

import java.util.ArrayList;
import facility.FacilityManager;
import item.ItemManager;
import item.ItemNotFoundException;

public class OrderItemLogisticsRecord {
	
	
	private int itemCost;
	private String itemId;
	private String orderId;
	private int qty;
	private int cost;
	private int numSources;
	private int firstDay;
	private int lastDay;
	
	public OrderItemLogisticsRecord(ArrayList<FacilityRecord> solutions, String orderId, String itemId) throws ItemNotFoundException
	{
		qty=0;
		firstDay=Integer.MAX_VALUE;
		lastDay=-1;
		cost = 0;
		numSources = 0;
		FacilityManager fm = FacilityManager.getInstance();
		ItemManager im = ItemManager.getInstance();
		itemCost = im.getCost(itemId);
		this.orderId=orderId;
		this.itemId=itemId;
		
		for(FacilityRecord fr : solutions)
		{
			numSources++;
			qty+=fr.getNumOfItemsProcessed();
			cost+=(itemCost*fr.getNumOfItemsProcessed());
			cost+=(500*fr.getTT());
			cost+=(fr.getProcessingTime()*fm.getDailyProcessingCost(fr.getName()));
			if(fr.getAD()>lastDay)
				lastDay=fr.getAD();
			if(fr.getAD()<firstDay)
			{
				firstDay=fr.getAD();
			}
		}
	}
	
	public int getTotalCost()
	{
		return cost;
	}
	
	public int getQty()
	{
		return qty;
	}
	
	public int getNumSources()
	{
		return numSources;
	}
	public int getFirstDay()
	{
		if(firstDay==Integer.MAX_VALUE)
			return 0;
		return firstDay;
	}
	public int getLastDay()
	{
		if(lastDay==-1)
			return 0;
		return lastDay;
	}
	public String getOrderId()
	{
		return orderId;
	}
	public String getItemId()
	{
		return itemId;
	}

}
