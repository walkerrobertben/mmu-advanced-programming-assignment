package uk.ac.mmu.advprog.hackathon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import uk.ac.mmu.advprog.hackathon.naptan.NaptanStops;

import java.sql.PreparedStatement;

public class DB implements AutoCloseable {
	
	private static final String JDBC_CONNECTION_STRING = "jdbc:sqlite:./data/NaPTAN.db";
	private Connection connection = null;
	
	//Constructor connects to sqlite db
	public DB() throws DBException {
		try {
			this.connection = DriverManager.getConnection(JDBC_CONNECTION_STRING);
		}
		catch (SQLException sqle) {
			throw new DBException(sqle);
		}
	}
	
	//Close connection on cleanup
	@Override
	public void close() throws DBException {
		try {
			//If connection exists (could've failed to connect) and isnt already closed
			if (this.connection != null && !this.connection.isClosed()) {
				
				//Close it
				this.connection.close();
				this.connection = null;
			}
		} catch(SQLException sqle) {
			throw new DBException(sqle);
		}
	}
	
	//Count stops matching given locality string
	public int countStopsByLocality(String locality) throws DBException {
		try {
			
			//Create parameterised statement
			String query = "SELECT COUNT(*) AS count FROM Stops WHERE LocalityName = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, locality);
			
			//Execute query and return count from first row
			ResultSet results = statement.executeQuery();
			results.next();
			return results.getInt("count");

		} catch(SQLException sqle) {
			throw new DBException(sqle);
		}
	}
	
	//Get stops matching given locality string & type
	public NaptanStops getStopsInLocality(String locality, String type) throws DBException {
		try {
			
			//Create parameterised statement
			String query = "SELECT * FROM Stops WHERE LocalityName = ? AND StopType = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, locality);
			statement.setString(2, type);
			
			//Execute query and return NaptanStops object
			ResultSet results = statement.executeQuery();
			return new NaptanStops(results);

		} catch(SQLException sqle) {
			throw new DBException(sqle);
		}
	}
	
	//Get n stops matching type, ordered by proximity to lat/lon
	public NaptanStops getNearestStops(float lat, float lon, String type, int limit) throws DBException {
		try {
			
			String query = "SELECT * FROM Stops WHERE StopType = ? AND Latitude IS NOT NULL And Longitude IS NOT NULL ORDER BY (? - Latitude) * (? - Latitude) + 0.595 * (? - Longitude) * (? - Longitude) ASC LIMIT ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, type);
			statement.setFloat(2, lat);
			statement.setFloat(3, lat);
			statement.setFloat(4, lon);
			statement.setFloat(5, lon);
			statement.setInt(6, limit);
			
			//Execute query and return NaptanStops object
			ResultSet results = statement.executeQuery();
			return new NaptanStops(results);
			
		} catch(SQLException sqle) {
			throw new DBException(sqle);
		}
	}
	
}
