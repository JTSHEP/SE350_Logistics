package network;

public class Connection {
	
	private String origin;
	private String destination;
	private int distance;
	
	
	public Connection(String origin,String destination, int distance)
	{
		this.origin=origin;
		this.destination=destination;
		this.distance=distance;
	}
	
	public String getOrigin()
	{
		return origin;
	}
	
	public String getDestination()
	{
		return destination;
	}
	
	public int getDistance()
	{
		return distance;
	}

}
