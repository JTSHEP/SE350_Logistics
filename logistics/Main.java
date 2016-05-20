package logistics;

import facility.FacilityManager;
import output.OrderProcessor;
import item.ItemManager;
import item.ItemNotFoundException;
import network.FacilityNotFoundException;
import network.NetworkManager;
import order.OrderManager;
import order.OrderNotFoundException;

public class Main {
	
	public static void main(String[] args)
	{
		ItemManager im = ItemManager.getInstance();
		NetworkManager nm = NetworkManager.getInstance();
		FacilityManager fm = FacilityManager.getInstance();	
		OrderManager om = OrderManager.getInstance();
		
		OrderProcessor op = new OrderProcessor(im,nm,fm,om);
		try {
			op.processOrders();
		} catch (OrderNotFoundException e) {
			e.printStackTrace();
		} catch (FacilityNotFoundException e) {
			e.printStackTrace();
		} catch (ItemNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
}
