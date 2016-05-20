package network;

import java.util.ArrayList;

public class NodeImpl implements Node {
	

	private String name;
	ArrayList<Connection> connections;
	
	public NodeImpl(String name,ArrayList<Connection> connections)
	{
		this.name=name;
		this.connections=connections;
	}
	
	public String getName()
	{
		return name;
	}
	

	
	public ArrayList<Connection> getConnections()
	{
		return connections;
	}

}
