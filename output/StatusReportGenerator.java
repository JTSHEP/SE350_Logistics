package output;

import java.util.ArrayList;

import facility.FacilityManager;
import item.ItemManager;
import network.FacilityNotFoundException;
import network.NetworkManager;

public class StatusReportGenerator {
	
	public static void generateAll(FacilityManager fm, NetworkManager nm,ItemManager im)
	{
		ArrayList<String> facilityNames = new ArrayList<String>();
		facilityNames=fm.getFacilities();
		for(String s:facilityNames)
		{
			System.out.println("-------------------------");
			System.out.println(s);
			try
			{
			System.out.println("Links: "+nm.getLinks(s));
			}
			catch(FacilityNotFoundException e)
			{
				System.out.println("No Links found. Disconnected network or bad input");
			}
			System.out.println("");
			System.out.println("Inventory:");
			ArrayList<String> depleted = new ArrayList<String>();
			for(String item:im.getItemList())
			{
				if(fm.hasItem(s, item))
				{
					System.out.println(item+" : "+fm.numItems(s, item));
				}
				
				else if(fm.depletedItem(s, item))
					depleted.add(item);
				
					
					
			}
			System.out.println("");
			System.out.print("Depleted Items: ");
			for(String item:depleted)
			{
				System.out.print(" "+item+",");
			}
			System.out.println("");
			System.out.println("");
			System.out.println("Schedule:");
			fm.printScheduleRepresentation(s);
			
			System.out.println("-------------------------");
		}
	}

}
