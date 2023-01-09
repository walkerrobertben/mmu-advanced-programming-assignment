package uk.ac.mmu.advprog.hackathon.routes;

import spark.Request;
import spark.Response;
import spark.Route;
import uk.ac.mmu.advprog.hackathon.DB;
import uk.ac.mmu.advprog.hackathon.DBException;
import uk.ac.mmu.advprog.hackathon.Validation;
import uk.ac.mmu.advprog.hackathon.naptan.NaptanStops;

public class nearest implements Route {
	@Override
	public Object handle(Request req, Response res) {
		
		//parse req for lat, lon, type
		//validate parameters
		//run db query
		//parse result into 'NaptanStops' object
		//return NaptanStops.xml()
		
		//> if query fails, return server error (500)
		//> if invalid, return invalid request (400)
		
		//Extract url params
		String lat_str = req.queryParams("latitude");
		String lon_str = req.queryParams("longitude");
		String type = req.queryParams("type");
		
		//Validate params
		if (Validation.is_valid_lat_lon(lat_str, lon_str) && Validation.is_valid_type(type)) {
			
			//convert str > float
			float lat = Float.parseFloat(lat_str);
			float lon = Float.parseFloat(lon_str);
			
			try (DB db = new DB()) {
				
				//Get 5 nearest stops
				NaptanStops stops = db.getNearestStops(lat, lon, type, 5);
				
				//return as xml string
				res.status(200);
				res.type("application/xml");
				return stops.asXML();
				
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