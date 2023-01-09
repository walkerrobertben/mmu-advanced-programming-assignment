package uk.ac.mmu.advprog.hackathon;

import java.sql.SQLException;

/*
 * Exception thrown by the DB class.
 * Optional underlying_sqlexception can be provided
 */
public class DBException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public SQLException underlying_sqle = null;
	
	/*
	 * Base constructor
	 */
	public DBException() {}
	
	/*
	 * SQLE constructor - pass the sqlexception that caused this DBException to be thrown
	 * 
	 * @param	sqle	the underlying sqlexception responsible for the DBException
	 */
	public DBException(SQLException sqle) {
		this.underlying_sqle = sqle;
	}
	
}