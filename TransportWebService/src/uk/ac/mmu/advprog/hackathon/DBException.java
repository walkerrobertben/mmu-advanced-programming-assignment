package uk.ac.mmu.advprog.hackathon;

import java.sql.SQLException;

public class DBException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public SQLException underlying_sqle = null;
	
	public DBException() {}
	
	//optionally provide sqle that caused it
	public DBException(SQLException underlying_sqlexception) {
		this.underlying_sqle = underlying_sqlexception;
	}
	
}