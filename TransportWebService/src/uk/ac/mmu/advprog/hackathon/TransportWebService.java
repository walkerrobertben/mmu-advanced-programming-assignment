package uk.ac.mmu.advprog.hackathon;

import spark.Spark;
//import static spark.Spark.get;

public class TransportWebService {

	public static void main(String[] args) {		
		
		Spark.port(8088);
		
		//routes
		Spark.get("/stopcount", new uk.ac.mmu.advprog.hackathon.routes.stopcount());
		Spark.get("/stops", new uk.ac.mmu.advprog.hackathon.routes.stops());
		
		System.out.println("Server up! Don't forget to kill the program when done!");
	}

}
