package network;

import java.util.ArrayList;

public class NodeFactory {
	
	

	
	public static Node generateNode(String identifier, String name, ArrayList<Connection> connections)
	{	
			return new NodeImpl(name,connections);
	}

}
