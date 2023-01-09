package uk.ac.mmu.advprog.hackathon;

public class Validation {
	
	public static boolean is_valid_locality(String locality) {
		return locality != null && locality.length() > 0;
	}
}