package network;

import java.util.ArrayList;

public class NetworkManager {
	
	private static NetworkManager instance;
	private static ArrayList<Node> network;
	
	
	private NetworkManager()
	{
		network = NetworkReader.readNetwork();
	}
	
	private static Node returnNodeByName(String name) throws FacilityNotFoundException
	{
		for(Node n:network)
		{
			if(n.getName().equalsIgnoreCase(name))
				return n;
		}
		
		throw new FacilityNotFoundException(name);
	}
	
	public String getLinks(String facility) throws FacilityNotFoundException
	{
		Node n = returnNodeByName(facility);
		ArrayList<Connection> connections = n.getConnections();
		String toReturn = "";
		for (Connection c:connections)
		{
			toReturn+=c.getDestination()+" ("+c.getDistance()+"),  ";
		}
		return toReturn;
	}
	
	public double shortestPath(String origin, String destination) throws FacilityNotFoundException
	{
		return ShortestPathDelegate.shortestPath(network, returnNodeByName(origin), returnNodeByName(destination));
	}
	
	public double shortestPathDays(String origin, String destination,int drivingHoursPerDay,int averageMPH) throws FacilityNotFoundException
	{
		return (shortestPath(origin,destination)/(drivingHoursPerDay*averageMPH));
	}
	
	public static NetworkManager getInstance()
	{
		if(instance==null)
			instance = new NetworkManager();
		return instance;
	}

}
