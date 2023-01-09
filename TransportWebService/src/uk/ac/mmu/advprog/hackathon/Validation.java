package uk.ac.mmu.advprog.hackathon;

public class Validation {
	
	//Validate locality parameter
	public static boolean is_valid_locality(String locality) {
		return locality != null && locality.length() > 0;
	}
	
	//Validate type parameter
	public static boolean is_valid_type(String type) {
		return  type != null && 
				(   type.equals("BUS")
				||  type.equals("RLW")
				||  type.equals("MET")
				||  type.equals("FER")
				||  type.equals("AIR")
				||  type.equals("TXT"));
	}

	//Validate single lat/lon parameter
	private static boolean is_valid_coordinate(String c) {
		if (c == null || c.length() == 0) return false;
		
		//if parsing to float fails, invalid
		try { Float.parseFloat(c); } catch (NumberFormatException nfe) { return false; }
		
		return true;
	}
	
	//Validate lat and lon parameters
	public static boolean is_valid_lat_lon(String lat, String lon) {
		return is_valid_coordinate(lat) && is_valid_coordinate(lon);
	}
}