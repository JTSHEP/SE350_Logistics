package order;

public class OrderItemFactory {

	
	
	public static OrderItem generateOrderItem(String id, int qty)
	{
		return new OrderItemImpl(id,qty);
	}
}
