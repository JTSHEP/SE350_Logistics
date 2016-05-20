package facility;

import java.util.ArrayList;


public class FacilityManager {
	
	private static FacilityManager instance;
	private ArrayList<Facility> facilities;
	
	private FacilityManager()
	{
		facilities = FacilityReader.gatherInput();
	}
	
	private Facility findByName(String name)
	{
		for(Facility f:facilities)
		{
			if(f.getName().equalsIgnoreCase(name))
				return f;
		}
		return null;
	}
	
	public static FacilityManager getInstance()
	{
		if(instance==null)
		{
			instance=new FacilityManager();
		}
		
		return instance;
	}
	
	public int getDailyProcessingCost(String facility)
	{
		Facility f = findByName(facility);
		return f.getCost();
	}
	
	public ArrayList<String> getFacilitiesWithItem(String itemId)
	{
		ArrayList<String> toReturn = new ArrayList<String>();
		
		for(Facility f:facilities)
		{
			if (f.hasItem(itemId)){
				toReturn.add(f.getName());	}			
		}
		return toReturn;
	}
	
	public int getProcessingTime(String facility, int qty,int startingDay)
	{
		Facility f = findByName(facility);
		return f.daysRequiredToProcess(startingDay, qty);
	}
	
	public ArrayList<String> getFacilities()
	{
		ArrayList<String> toReturn = new ArrayList<String>();
		for(Facility f:facilities)
		{
			toReturn.add(f.getName());
		}
		return toReturn;
	}
	
	public boolean hasItem(String facility, String itemId)
	{
		return findByName(facility).hasItem(itemId);
	}
	public boolean depletedItem(String facility,String itemId)
	{
		return findByName(facility).isDepletedItem(itemId);
	}
	
	public int numItems(String facility,String itemId)
	{
		return findByName(facility).getNumItems(itemId);
	}
	
	public boolean addItem(String facility, String itemId, int qty)
	{
		return findByName(facility).addItem(itemId, qty);
	}
	
	
	
	public boolean removeItem(String facility,String itemId, int qty) throws NegativeItemQtyException
	{
		return findByName(facility).removeItem(itemId, qty);
	}
	
	public void printScheduleRepresentation(String facility)
	{
		Facility f = findByName(facility);
		f.printSchedule();
	}
	
	public boolean book(String facility,int qty,int startingDay)
	{
		return findByName(facility).book(startingDay, qty);
	}
	
	
	
}
