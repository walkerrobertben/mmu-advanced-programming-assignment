package uk.ac.mmu.advprog.hackathon;

import static spark.Spark.*;
import spark.Request;
import spark.Response;
import spark.Route;

public class TransportWebService {

	public static void main(String[] args) {		
		
		port(8088);
		
		//Simple route so you can check things are working...
		//Accessible via http://localhost:8088/test in your browser
//		get("/test", new Route() {
//			@Override
//			public Object handle(Request request, Response response) throws Exception {
//				try (DB db = new DB()) {
//					return "Number of Entries: " + db.getNumberOfEntries();
//				}
//			}			
//		});
		
		
		//routes
		get("/stopcount", new uk.ac.mmu.advprog.hackathon.routes.stopcount());
		
		System.out.println("Server up! Don't forget to kill the program when done!");
	}

}
