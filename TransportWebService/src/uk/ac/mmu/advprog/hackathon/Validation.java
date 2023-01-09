package uk.ac.mmu.advprog.hackathon;

public class Validation {
	
	public static boolean is_valid_locality(String locality) {
		return locality != null && locality.length() > 0;
	}
	
	public static boolean is_valid_type(String type) {
		return  type != null && 
				(   type.equals("BUS")
				||  type.equals("RLW")
				||  type.equals("MET")
				||  type.equals("FER")
				||  type.equals("AIR")
				||  type.equals("TXT"));
	}
}