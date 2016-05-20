package network;

import java.util.ArrayList;
import java.util.HashMap;

public class ShortestPathDelegate {
	
	private static ArrayList<Node> net;
	private static HashMap<Node,Integer> tentative;
	private static ArrayList<Node> unvisited;
	
	public static int shortestPath(ArrayList<Node> network,Node origin, Node destination)
	{
		net=network;
		tentative = new HashMap<Node,Integer>();
		unvisited = new ArrayList<Node>();
		for(Node n:network)
		{
			unvisited.add(n);
			tentative.put(n, Integer.MAX_VALUE);
		}
		
		tentative.put(origin, 0);
		unvisited.remove(origin);
		
		Node current = origin;
		go(current);
		while(unvisited.contains(destination))
		{
			go(minTentative());
		}
		
		return tentative.get(destination);
		
		
	}
	
	
	private static void go(Node current)
	{
		for(Connection c:current.getConnections())
		{
			int d = c.getDistance();
			Node x = returnNodeByName(c.getDestination());
			
			int newTentative = d+tentative.get(current);
			if(newTentative<tentative.get(x))
				tentative.put(x, newTentative);
			
			
		}
		
		unvisited.remove(current);
	}
	
	
	private static Node returnNodeByName(String name)
	{
		for(Node n:net)
		{
			if(n.getName().equalsIgnoreCase(name))
				return n;
		}
		
		return null;
	}
	
	private static Node minTentative()
	{
		int min = Integer.MAX_VALUE;
		Node toReturn=null;
		for(Node n:unvisited)
		{
			if(tentative.get(n)<min)
			{
				min=tentative.get(n);
				toReturn=n;
			}
			
		}
		
		return toReturn;
	}
	

}
