package order;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class OrderReader {
	
	public static ArrayList<Order> read()
	{
		ArrayList<Order> toReturn = new ArrayList<Order>();

		try
		{
		File fXmlFile = new File("src/input/Orders.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		
		NodeList nList = doc.getElementsByTagName("Order");
		for (int temp = 0; temp < nList.getLength(); temp++) 
		{
			Node nNode = nList.item(temp);
			
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;
				
				String id = eElement.getAttribute("id");
				String destination = eElement.getAttribute("Destination");
				int day = Integer.parseInt(eElement.getAttribute("Day"));
				ArrayList<OrderItem> contents = new ArrayList<OrderItem>();
				
				NodeList sList = eElement.getElementsByTagName("Item");
				for (int subTemp = 0; subTemp < sList.getLength(); subTemp++)
				{
					Node sNode = sList.item(subTemp);
					
					if (sNode.getNodeType() == Node.ELEMENT_NODE) {

						Element sElement = (Element) sNode;
						String subid  = sElement.getElementsByTagName("Id").item(0).getTextContent();
						int subqty = Integer.parseInt( sElement.getElementsByTagName("Qty").item(0).getTextContent());
						contents.add(OrderItemFactory.generateOrderItem(subid, subqty));
						
				}
				}
				toReturn.add(OrderFactory.generateOrder(id, day, destination, contents));
				
				

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
