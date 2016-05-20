package facility;

public interface Inventory {
	
	public boolean hasItem(String itemId);
	public int numItems(String itemId);
	public boolean addItem(String itemId, int qty);
	public boolean removeItem(String itemId, int qty) throws NegativeItemQtyException;
	

}
