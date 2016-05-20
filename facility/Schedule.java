package facility;

public interface Schedule {
	
	public int daysRequiredToProcess(int startingDay,int qty);
	public boolean book(int startingDay, int qty);
	public void print();

}
