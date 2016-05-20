package facility;

public class InventoryFactory {
	
	public static Inventory generateInventory(String identifier)
	{
		return new InventoryImpl();
	}

}
