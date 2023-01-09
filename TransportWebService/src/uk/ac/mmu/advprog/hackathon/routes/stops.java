package uk.ac.mmu.advprog.hackathon.routes;

import spark.Request;
import spark.Response;
import spark.Route;

import uk.ac.mmu.advprog.hackathon.DB;
import uk.ac.mmu.advprog.hackathon.DBException;
import uk.ac.mmu.advprog.hackathon.NaptanStops;
import uk.ac.mmu.advprog.hackathon.Validation;

public class stops implements Route {
	@Override
	public Object handle(Request req, Response res) {
		
		//> parse req for locality & type parameters
		//> validate parameters exists, right datatype
		//>   run db query
		//>    	parse resultset into 'NaptanStops' object
		//>     return NaptanStops.json();
		//> if query fails, return server error (500)
		//> if invalid, return invalid request (400)
		
		
		String locality = req.queryParams("locality");
		String type = req.queryParams("type");
		if (Validation.is_valid_locality(locality) && Validation.is_valid_type(type)) {
			
			try (DB db = new DB()) {
				
				//Get stop of type in locality
				NaptanStops stops = db.getStopsInLocality(locality, type);
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