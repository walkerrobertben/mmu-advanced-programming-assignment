package uk.ac.mmu.advprog.hackathon;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.*;

public class NaptanStops {
	
	private ArrayList<NaptanStop> stops;

	public NaptanStops(ResultSet db_results) throws SQLException {
		
		//create stops arraylist
		this.stops = new ArrayList<NaptanStop>();
		
		//check results empty
		if (db_results.isClosed()) return;
		
		//get column indexes
		int naptanCode   = db_results.findColumn("NaptanCode");
		int commonName   = db_results.findColumn("CommonName");
		int landmark     = db_results.findColumn("Landmark");
		int street       = db_results.findColumn("Street");
		int indicator    = db_results.findColumn("Indicator");
		int bearing      = db_results.findColumn("Bearing");
		int localityName = db_results.findColumn("LocalityName");
		int longitude    = db_results.findColumn("Longitude");
		int latitude     = db_results.findColumn("Latitude");
		int stopType     = db_results.findColumn("StopType");
		int busStopType  = db_results.findColumn("BusStopType");
		int timingStatus = db_results.findColumn("TimingStatus");
		
		//parse results into arraylist of NaptanStop objects
		while (db_results.next()) {
			NaptanStop stop = new NaptanStop();
			
			stop.naptanCode   = db_results.getString(naptanCode);
			stop.commonName   = db_results.getString(commonName);
			stop.landmark     = db_results.getString(landmark);
			stop.street       = db_results.getString(street);
			stop.indicator    = db_results.getString(indicator);
			stop.bearing      = db_results.getString(bearing);
			stop.localityName = db_results.getString(localityName);
			stop.longitude    = db_results.getFloat(longitude);
			stop.latitude     = db_results.getFloat(latitude);
			stop.stopType     = db_results.getString(stopType);
			stop.busStopType  = db_results.getString(busStopType);
			stop.timingStatus = db_results.getString(timingStatus);
			
			this.stops.add(stop);
		}
	}
	
	public String asJson() {
		JSONArray root = new JSONArray();
		for (NaptanStop stop : this.stops) {
			root.put(stop.asJson());
		}
		return root.toString();
	}
	
	public String asXML() {
		return "";
	}
}

//Should refactor this to its own .java file?
class NaptanStop {
	
	public String naptanCode;
	public String commonName;
	public String landmark;
	public String street;
	public String indicator;
	public String bearing;
	public String localityName;
	public float longitude;
	public float latitude;
	public String stopType;
	public String busStopType;
	public String timingStatus;
	
	//empty-string-if-null
	private Object esin(Object v) {
		if (v == null) {
			return "";
		} else {
			return v;
		}
	}
	
	public JSONObject asJson() {
		JSONObject obj = new JSONObject();
		
		obj.put("name", esin(this.commonName));
		obj.put("locality", esin(this.localityName));
		
		JSONObject location = new JSONObject();
		{
			location.put("indicator", esin(this.indicator));
			location.put("bearing", esin(this.bearing));
			location.put("street", esin(this.street));
			location.put("landmark", esin(this.landmark));
		}
		obj.put("location", location);
		
		obj.put("type", esin(this.stopType));
		
		return obj;
	}
	
	
}