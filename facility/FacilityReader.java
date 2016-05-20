package facility;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import item.Item;
import item.ItemFactory;
import network.Connection;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;

public class FacilityReader {
	
	public static ArrayList<Facility> gatherInput()
	{
		ArrayList<Facility> toReturn=new ArrayList<Facility>();
		
		try
		{
		
		File fXmlFile = new File("src/input/Facilities.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		
		NodeList nList = doc.getElementsByTagName("Facility");
		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);
					

					
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;
				
				
				String name = eElement.getAttribute("Name");
				int cost = Integer.parseInt(eElement.getAttribute("Cost"));
				int rate = Integer.parseInt(eElement.getAttribute("Rate"));
				Facility toAdd = FacilityFactory.generateFacility("", name, cost, rate);
				NodeList sList = eElement.getElementsByTagName("Item");
				for (int subTemp = 0; subTemp < sList.getLength(); subTemp++)
				{
					Node sNode = sList.item(subTemp);
					
					if (sNode.getNodeType() == Node.ELEMENT_NODE) {

						Element sElement = (Element) sNode;
						String id  = sElement.getElementsByTagName("Id").item(0).getTextContent();
						int qty = Integer.parseInt( sElement.getElementsByTagName("Qty").item(0).getTextContent());
						toAdd.addItem(id, qty);
						
				}
				}
				
				
				

				toReturn.add(toAdd);

			}
		}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return toReturn;
	}

}
