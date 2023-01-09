package uk.ac.mmu.advprog.hackathon;

import spark.Spark;

/*
 * Main class 
 */
public class TransportWebService {
	
	/*
	 * Entrypoint
	 */
	public static void main(String[] args) {		
		
		//Run server on port 8088 and connect routes.
		Spark.port(8088);
		Spark.get("/stopcount", new uk.ac.mmu.advprog.hackathon.routes.stopcount() );
		Spark.get("/stops",     new uk.ac.mmu.advprog.hackathon.routes.stops()     );
		Spark.get("/nearest",   new uk.ac.mmu.advprog.hackathon.routes.nearest()   );
		
		System.out.println("Server up! Don't forget to kill the program when done!");
	}

}
