package facility;

public interface Facility {
	
	
	public String getName();
	public int getCost();
	public int getProcessingRate();
	public boolean hasItem(String itemId);
	public boolean isDepletedItem(String itemId);
	public int getNumItems(String itemId);
	public boolean addItem(String itemId, int qty);
	public boolean removeItem(String itemId, int qty) throws NegativeItemQtyException;
	public int daysRequiredToProcess(int startingDay, int qty);
	public boolean book(int startingDay,int qty);
	public void printSchedule();

}
