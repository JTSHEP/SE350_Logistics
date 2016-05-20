package output;

public class NegativeItemQtyException extends Exception{
	
	public NegativeItemQtyException()
	{
		super();
	}
	
	public NegativeItemQtyException(String itemId)
	{
		super(itemId);
	}

}
