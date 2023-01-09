package uk.ac.mmu.advprog.hackathon.routes;

import spark.Request;
import spark.Response;
import spark.Route;

import uk.ac.mmu.advprog.hackathon.DB;
import uk.ac.mmu.advprog.hackathon.DBException;
import uk.ac.mmu.advprog.hackathon.Validation;
import uk.ac.mmu.advprog.hackathon.naptan.NaptanStops;


/*
 * Spark route for get request to /stops
 */
public class stops implements Route {
	@Override
	public Object handle(Request req, Response res) {
		
		
		//parse req for locality, type
		//validate parameters
		//run db query
		//parse result into 'NaptanStops' object
		//return NaptanStops.json()
		
		//if query fails, return server error (500)
		//if invalid, return invalid request (400)
		
		
		
		//Extract url params
		String locality = req.queryParams("locality");
		String type = req.queryParams("type");
		
		//Validate params
		if (Validation.is_valid_locality(locality) && Validation.is_valid_type(type)) {
			
			try (DB db = new DB()) {
				
				//Get all stops that match filter
				NaptanStops stops = db.getStopsInLocality(locality, type);
				
				//return as json string
				res.status(200);
				res.type("application/json");
				return stops.asJson();
				
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