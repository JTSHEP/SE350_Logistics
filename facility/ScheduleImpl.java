package facility;

import java.util.HashMap;

public class ScheduleImpl implements Schedule{
	
	private HashMap<Integer,Integer> schedule;
	private int itemsPerDay;
	
		public ScheduleImpl(int itemsPerDay)
		{
			schedule = new HashMap<Integer,Integer>();
			this.itemsPerDay=itemsPerDay;
		}
		
		public int daysRequiredToProcess(int startingDay, int qty)
		{
			int processed = 0;
			int current = startingDay;
			int count=0;
			while(processed<qty)
			{
				count++;
				processed+=itemsPerDay-checkDay(current);
				current++;
			}
			return count;
		}
		
		public boolean book(int startingDay,int qty)
		{
			int processed = 0;
			int current = startingDay;
			while(processed<qty)
			{
				int availible = itemsPerDay-checkDay(current);
				if(processed+availible<=qty)
					schedule.put(current, itemsPerDay);
				else
					schedule.put(current,checkDay(current)+(qty-processed));
				current++;
				processed+=availible;
			}
			return true;
		}
		
		public void print()
		{
			for(int i=0;i<=20;i++)
			{
				System.out.print(" | Day "+i+" : ");
				System.out.print((itemsPerDay-checkDay(i))+" Availible | ");
				if((i+1)%4==0)
					System.out.println(" ");
			}
			System.out.println("");
			
			
		}
		
		private int checkDay(int day)
		{
			if(!schedule.containsKey(day))
				return 0;
			else return schedule.get(day);
		}
	

}
