package facility;

public class FacilityImpl implements Facility{
	
	private String name;
	private int cost;
	private int processingRate;
	private Inventory inventory;
	private Schedule schedule;
	
	public FacilityImpl(String name, int cost, int processingRate)
	{
		this.name=name;
		this.cost=cost;
		this.processingRate=processingRate;
		inventory=InventoryFactory.generateInventory(null);
		schedule=ScheduleFactory.generateSchedule(null, processingRate);
	}
	
	
	public String getName()
	{
		return name;
	}
	
	public int getCost()
	{
		return cost;
	}
	
	public int getProcessingRate()
	{
		return processingRate;
	}
	
	public boolean hasItem(String itemId)
	{
		return (inventory.hasItem(itemId))&&inventory.numItems(itemId)>0;
	}
	public boolean isDepletedItem(String itemId)
	{
		return (inventory.hasItem(itemId))&&inventory.numItems(itemId)==0;
	}
	
	public int getNumItems(String itemId)
	{
		return inventory.numItems(itemId);
	}
	
	public boolean addItem(String itemId, int qty)
	{
		return inventory.addItem(itemId, qty);
	}
	
	public boolean removeItem(String itemId, int qty) throws NegativeItemQtyException
	{
		return inventory.removeItem(itemId, qty);
	}
	
	public int daysRequiredToProcess(int startingDay,int qty)
	{
		return schedule.daysRequiredToProcess(startingDay, qty);
	}
	
	public boolean book(int startingDay,int qty)
	{
		return schedule.book(startingDay, qty);
	}
	public void printSchedule()
	{
		schedule.print();
	}

}
