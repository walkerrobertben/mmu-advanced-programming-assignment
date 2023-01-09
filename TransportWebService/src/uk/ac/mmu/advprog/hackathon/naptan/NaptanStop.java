package uk.ac.mmu.advprog.hackathon.naptan;

import org.json.*;
import org.w3c.dom.*;

/*
 * NaptanStop class
 * 
 * Used to parse and represent a single database row
 */
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
	
	/*
	 * NaptanStops constructor
	 * 
	 * @param	naptanCode		naptanCode from database
	 * @param	commonName		commonName from database
	 * @param	landmark		landmark from database
	 * @param	street			street from database
	 * @param	indicator		indicator from database
	 * @param	bearing			bearing from database
	 * @param	localityName	localityName from database
	 * @param	longitude		longitude from database
	 * @param	latitude		latitude from database
	 * @param	stopType		stopType from database
	 * @param	busStopType		busStopType from database
	 * @param	timingStatus	timingStatus from database
	 * 
	 */
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

	/*
	 * esin = "empty string if null"
	 * utility function for handling null values when converting to json/xml representation
	 * 
	 * ResultSet.getString seems to return "---" instead of null, so handle that too.
	 * 
	 * @return	the original object, or an empty string if the original object was null
	 * 
	 */
	private Object esin(Object v) {
		if (v == null || "---".equals(v)) {
			return "";
		} else {
			return v;
		}
	}
	
	/*
	 * Get this stop as a JSON node
	 * 
	 * @return	a JSON object representing this stop
	 * 
	 */
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
	
	
	/*
	 * Get this stop as an XML element
	 * 
	 * @param	doc		Document the element will be appended to is building for.
	 * 
	 * @return	an XML element representing this stop
	 * 
	 */
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
			
			//append to location
			location.appendChild(street);
			location.appendChild(landmark);
			location.appendChild(latitude);
			location.appendChild(longitude);
		}
		
		//apend to root
		stop.appendChild(name);
		stop.appendChild(locality);
		stop.appendChild(location);
		
		return stop;
	}
	
	
}