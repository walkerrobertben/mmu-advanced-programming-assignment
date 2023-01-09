package uk.ac.mmu.advprog.hackathon.routes;

import spark.Request;
import spark.Response;
import spark.Route;

import uk.ac.mmu.advprog.hackathon.DB;
import uk.ac.mmu.advprog.hackathon.DBException;
import uk.ac.mmu.advprog.hackathon.Validation;

public class stopcount implements Route {
	@Override
	public Object handle(Request req, Response res) {
		
		//parse req for locality
		//validate parameters
		//run db query
		//return result
		
		//> if query fails, return server error (500)
		//> if invalid, return invalid request (400)
		
		//Extract url params
		String locality = req.queryParams("locality");
		
		//Validate params
		if (Validation.is_valid_locality(locality)) {
			
			try (DB db = new DB()) {
				
				//Count stops and return
				int stopCount = db.countStopsByLocality(locality);
				res.status(200);
				res.type("text/plain");
				return String.valueOf(stopCount);
				
				
			//Server error
			} catch (DBException dbe) {
				System.err.println("DBException occured");
				if (dbe.underlying_sqle != null) {
					dbe.underlying_sqle.printStackTrace();
				}
				res.status(500);
				return "Internal Server Error";
			}
		}
		
		//Validation failed, invalid request
		res.status(400);
		return "Invalid Request";
	}
}