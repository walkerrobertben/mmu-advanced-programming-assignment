package uk.ac.mmu.advprog.hackathon;

/*
 * Validation class
 * 
 * Contains static functions used to validate user-provided parameters
 */
public class Validation {
	
	/*
	 * Validate 'locality' parameter.
	 * Valid if not null and not empty string.
	 * 
	 * @param	locality	the locality parameter to validate
	 * 
	 * @return true if valid
	 * 
	 */
	public static boolean is_valid_locality(String locality) {
		return locality != null && locality.length() > 0;
	}
	
	/*
	 * Validate 'type' parameter.
	 * Valid if not null and matches one of either BUS, RLW, MET, FER, AIR or TXT.
	 * 
	 * @param	type	the type parameter to validate
	 * 
	 * @return true if valid
	 * 
	 */
	public static boolean is_valid_type(String type) {
		return  type != null && 
				(   type.equals("BUS")
				||  type.equals("RLW")
				||  type.equals("MET")
				||  type.equals("FER")
				||  type.equals("AIR")
				||  type.equals("TXT"));
	}

	/*
	 * Validate a single lat/lon string
	 * Valid if not null and string parses into a float successfully
	 * 
	 * @param	c	the coordinate to validate
	 * 
	 * @return true if valid
	 * 
	 */
	private static boolean is_valid_coordinate(String c) {
		if (c == null || c.length() == 0) return false;
		
		//if parsing to float fails, invalid
		try { Float.parseFloat(c); } catch (NumberFormatException nfe) { return false; }
		
		return true;
	}
	
	/*
	 * Validate a lat and lon parameters
	 * Valid if not null and both strings parse into a float successfully
	 * 
	 * @param	lat		the latitude coordinate to validate
	 * @param	lon		the longitude coordinate to validate
	 * 
	 * @return true if valid
	 * 
	 */
	public static boolean is_valid_lat_lon(String lat, String lon) {
		return is_valid_coordinate(lat) && is_valid_coordinate(lon);
	}
}