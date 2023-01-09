package uk.ac.mmu.advprog.hackathon;

import spark.Spark;

/*
 * Main class
 * 
 * Code overview:
 * 
 * Main class (this one) starts spark server and binds routes
 * 
 * Route controllers are in the uk.ac.mmu.advprog.hackathon.routes package
 * Each route is handled in an individual file
 * 
 * Route handlers:
 *  - validate parameters (validation checks in the Validation.java file)
 *  - ask the DB.java class for the data
 *  - return
 *  
 *  DB.java handles connecting, reading, and closing, the sqlite connection
 *  For routes that return stops (/stops and /nearest) the db rows are parsed into a NaptanStops
 *  
 *  NaptanStops object contains an arraylist of NaptanStop objects
 *  NaptanStop objects represent a single stop
 *  
 *  NaptanStops object provides asJson() and asXML() methods used by routes to build strings.
 * 
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
