package uk.ac.mmu.advprog.hackathon.naptan;

import org.json.*;
import org.w3c.dom.*;

public class NaptanStop {

	//Represents a single NaptanStop
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
	
	//Constructor takes in a single database row
	public NaptanStop(String naptanCode, String commonName, String landmark, String street, String indicator, String bearing, String localityName, float longitude, float latitude, String stopType, String busStopType, String timingStatus) {
		this.naptanCode   = naptanCode;
		this.commonName   = commonName;
		this.landmark     = landmark;
		this.street       = street;
		this.indicator    = indicator;
		this.bearing      = bearing;
		this.localityName = localityName;
		this.longitude    = longitude;
		this.latitude     = latitude;
		this.stopType     = stopType;
		this.busStopType  = busStopType;
		this.timingStatus = timingStatus;
	}
	
	//empty-string-if-null
	//utility function for handling null values
	private Object esin(Object v) {
		if (v == null) {
			return "";
		} else {
			return v;
		}
	}
	
	//Return this stop as a JSON node
	public JSONObject asJson() {
		JSONObject obj = new JSONObject();
		
		//add name & locality
		obj.put("name", esin(this.commonName));
		obj.put("locality", esin(this.localityName));
		
		//add location
		JSONObject location = new JSONObject();
		{
			location.put("indicator", esin(this.indicator));
			location.put("bearing", esin(this.bearing));
			location.put("street", esin(this.street));
			location.put("landmark", esin(this.landmark));
		}
		obj.put("location", location);
		
		
		//add stop type
		obj.put("type", esin(this.stopType));
		
		return obj;
	}
	
	
	//Return this stop as an XML element
	public Element asXML(Document doc) {
		
		Element stop = doc.createElement("Stop");
		stop.setAttribute("code", this.naptanCode); // << code is attribute not element!
		
		//add name and locality
		Element name = doc.createElement("Name");
		name.setTextContent(String.valueOf(esin(this.commonName)));
		
		Element locality = doc.createElement("Locality");
		locality.setTextContent(String.valueOf(esin(this.localityName)));
		
		//add location
		Element location = doc.createElement("Location");
		{
			Element street = doc.createElement("Street");
			street.setTextContent(String.valueOf(esin(this.street)));
			
			Element landmark = doc.createElement("Landmark");
			landmark.setTextContent(String.valueOf(esin(this.landmark)));
			
			Element latitude = doc.createElement("Latitude");
			latitude.setTextContent(String.valueOf(esin(this.latitude)));
			
			Element longitude = doc.createElement("Longitude");
			longitude.setTextContent(String.valueOf(esin(this.longitude)));
			
			location.appendChild(street);
			location.appendChild(landmark);
			location.appendChild(latitude);
			location.appendChild(longitude);
		}
		
		stop.appendChild(name);
		stop.appendChild(locality);
		stop.appendChild(location);
		
		return stop;
	}
	
	
}