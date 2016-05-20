package item;

import java.util.ArrayList;

public class ItemManager {
	
	private static ItemManager instance = null;
	private ArrayList<Item> catalogue;
	
	
	private ItemManager()
	{
		instance=this;
		catalogue=ItemReader.gatherInput();
		
		
	}
	
	public ArrayList<String> getItemList()
	{
		
		ArrayList<String> toReturn = new ArrayList<String>();
		for(Item i:catalogue)
		{
			toReturn.add(i.getId());
		}
		return toReturn;
	}
	
	
	public boolean isItem(String id)
	{
		for(Item i:catalogue)
		{
			if(i.getId().equalsIgnoreCase(id))
					return true;
		}
		
		return false;
		
	}
	
	
	public int getCost(String id) throws ItemNotFoundException
	{
		
		
		for(Item i:catalogue)
		{
			if(i.getId().equalsIgnoreCase(id))
					return i.getCost();
		}
		
		throw new ItemNotFoundException(id);
		
		
	}

	
	
	public static ItemManager getInstance()
	{
		if(instance==null)
		{
			instance = new ItemManager();
		}
		
		return instance;
	}
	
}
