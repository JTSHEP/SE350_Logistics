package network;

public class FacilityNotFoundException extends Exception{
	
	public FacilityNotFoundException()
	{
		super();
	}
	
	public FacilityNotFoundException(String facilityName)
	{
		super(facilityName);
	}


}
