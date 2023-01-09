package uk.ac.mmu.advprog.hackathon.naptan;

import org.json.JSONObject;

public class NaptanStop {
	
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