package facility;

import java.util.HashMap;

public class InventoryImpl implements Inventory{

	
	private HashMap<String,Integer> inventory;
	
	public InventoryImpl()
	{
		inventory = new HashMap<String,Integer>();
	}
	
	
	@Override
	public boolean hasItem(String itemId) {
		return (inventory.containsKey(itemId));
	}

	@Override
	public int numItems(String itemId) {
		
		if(hasItem(itemId))
			return inventory.get(itemId);
		else return 0;
	}

	@Override
	public boolean addItem(String itemId, int qty) {
		
		inventory.put(itemId,qty);
		return true;
	}

	@Override
	public boolean removeItem(String itemId, int qty) throws NegativeItemQtyException{
		
		int current = inventory.get(itemId);
		int after = current - qty;
		if(after>=0)
		{
			inventory.put(itemId, after);
			return true;
		}
		
		throw new NegativeItemQtyException(itemId);
	}
	
	

}
