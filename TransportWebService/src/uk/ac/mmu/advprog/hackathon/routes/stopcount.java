package uk.ac.mmu.advprog.hackathon.routes;

import static spark.Spark.*;
import spark.Request;
import spark.Response;
import spark.Route;
import uk.ac.mmu.advprog.hackathon.Validation;

public class stopcount implements Route {
	
	@Override
	public Object handle(Request req, Response res) {
		
		//parse req for locality parameter
		//validate parameter exists, right datatype
		//run db query
		//return value
		
		
		String locality = req.queryParams("locality");
		if (Validation.is_valid_locality(locality)) {
			
			System.out.println(locality);
			
			
			
			
			return "0";
		}
		
		//Default to invalid request
		res.status(400);
		return "Invalid Request";
	}
}
