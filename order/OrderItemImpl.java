package order;

public class OrderItemImpl implements OrderItem {

	
	private String id;
	private int qty;
	
	public OrderItemImpl(String id, int qty)
	{
		this.id=id;
		this.qty=qty;
	}
	

	public String getId() {
		return id;
	}

	
	public int getQty() {
		return qty;
	}

}
