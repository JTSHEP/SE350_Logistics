package network;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;



import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;

public class NetworkReader {
	
	
	public static ArrayList<network.Node> readNetwork()
	{
		ArrayList<network.Node> toReturn=new ArrayList<network.Node>();
		try
		{
		
		File fXmlFile = new File("src/input/Network.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		
		NodeList nList = doc.getElementsByTagName("Facility");
		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);
					

					
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;
				
				String name = eElement.getAttribute("Name");
				NodeList sList = eElement.getElementsByTagName("Connection");
				ArrayList<Connection> c = new ArrayList<Connection>();
				
				for (int subTemp = 0; subTemp < sList.getLength(); subTemp++)
				{
					Node sNode = sList.item(subTemp);
					
					if (sNode.getNodeType() == Node.ELEMENT_NODE) {

						Element sElement = (Element) sNode;
						String t = sElement.getAttribute("Target");
						int distance = Integer.parseInt(sElement.getAttribute("Distance"));
						c.add(new Connection(name,t,distance));
				}
				}
				

				toReturn.add(NodeFactory.generateNode("", name, c));

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


