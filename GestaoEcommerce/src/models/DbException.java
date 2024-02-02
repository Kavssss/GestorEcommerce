package models;

import java.io.IOException;
import java.sql.SQLException;

public class DbException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DbException(String msg) {
		super(msg);
	}
	
	public DbException(SQLException e) {
		e.printStackTrace();
	}
	
	public DbException(IOException e) {
		e.printStackTrace();
	}
	
	public DbException(Exception e) {
		e.printStackTrace();
	}

}
