package item;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;


public class ItemReader {
	
	
	public static ArrayList<Item> gatherInput()
	{
		ArrayList<Item> toReturn=new ArrayList<Item>();
		try
		{
		
		File fXmlFile = new File("src/input/ItemCatalogue.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		
		NodeList nList = doc.getElementsByTagName("Item");
		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);
					

					
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;
				
				
				String id = eElement.getElementsByTagName("Id").item(0).getTextContent();
				int cost = Integer.parseInt(eElement.getElementsByTagName("Cost").item(0).getTextContent());

				toReturn.add(ItemFactory.generateItem("",id,cost));

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
