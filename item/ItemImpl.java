package item;

public class ItemImpl implements Item{
	
	
	private String id;
	private int cost;
	
	
	public ItemImpl(String id, int cost)
	{
		this.id=id;
		this.cost=cost;
	}
	
	public String getId()
	{
		return id;
	}
	
	public int getCost()
	{
		return cost;
	}
	public String toString()
	{
		return(id+": $"+cost);
	}
	

}
