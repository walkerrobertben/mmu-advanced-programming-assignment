package uk.ac.mmu.advprog.hackathon.naptan;

import java.io.StringWriter;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.*;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class NaptanStops {
	
	private ArrayList<NaptanStop> naptanStops;

	public NaptanStops(ResultSet db_results) throws SQLException {
		
		//create stops arraylist
		this.naptanStops = new ArrayList<NaptanStop>();
		
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
			NaptanStop stop = new NaptanStop(
				db_results.getString(naptanCode),
				db_results.getString(commonName),
				db_results.getString(landmark),
				db_results.getString(street),
				db_results.getString(indicator),
				db_results.getString(bearing),
				db_results.getString(localityName),
				db_results.getFloat(longitude),
				db_results.getFloat(latitude),
				db_results.getString(stopType),
				db_results.getString(busStopType),
				db_results.getString(timingStatus)
			);
			this.naptanStops.add(stop);
		}
	}
	
	public String asJson() {
		//Create root node, and add stops to root
		JSONArray root = new JSONArray();
		for (NaptanStop stop : this.naptanStops) {
			root.put(stop.asJson());
		}
		return root.toString(); //return as string
	}
	
	public String asXML() {
		try {
			
			//Create XML Doc
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			
			//Add root element to doc, and add stops to root.
			Element root = doc.createElement("NearestStops");
			doc.appendChild(root);
			
			for (NaptanStop stop : this.naptanStops) {
				root.appendChild(stop.asXML(doc));
			}
			
			//Turn doc into string and return. why is this so complicated?
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			Writer output = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(output));
			
			return output.toString();
			
		
		//parsing xml threw an exception. print it and return default
		} catch (ParserConfigurationException | TransformerException err) {
			System.out.println("asXML failed");
			err.printStackTrace();
			
			//return empty xml string
			return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><NearestStops></NearestStops>";
		}
		
	}
}