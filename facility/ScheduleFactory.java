package facility;

public class ScheduleFactory {

	public static Schedule generateSchedule(String identifier, int itemsPerDay)
	{
		return new ScheduleImpl(itemsPerDay);
	}
}
