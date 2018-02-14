package joc113_SpotifyKnockoff;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

/**
 * This class is used for searching the various tables in the MySQL database
 * Sends this data to a DefaultTableModel to be displayed on the GUI
 * @author Josh Chamberlain
 * version 1.1
 */
public class Spotify {
	
	/**
	 * Used to search the songs table for the song received from the user
	 * Sends data to a DefaultTableModel
	 * @param searchTerm
	 * @return dataTable
	 */
	public static DefaultTableModel searchSongs(String searchTerm) {
		
		//SQL statement enters the String input from the user
		String sql = "SELECT * FROM song WHERE title LIKE '%" + searchTerm + "%';";
		DefaultTableModel dataTable = null;		//Used to display the data
		try {
			DbUtilities db = new DbUtilities();
			dataTable = db.getDataTable(sql);	//Pass SQL statement
		} catch (SQLException e) {
			// display error message, log the error
			ErrorLogger.log(e.getMessage());	
		}
		
		return dataTable;
	}
	/**
	 * Used to search the Albums table for the Album received from the user
	 * Sends data to a DefaultTableModel
	 * @param searchTerm
	 * @return dataTable
	 */
	public static DefaultTableModel searchAlbums(String searchTerm) {
		
		//SQL statement
		String sql = "SELECT * FROM album WHERE title LIKE '%" + searchTerm + "%';";
		DefaultTableModel dataTable = null;		//Used to display the data
		try {
			DbUtilities db = new DbUtilities();
			dataTable = db.getDataTable(sql);	//Pass SQL statement
		} catch (SQLException e) {
			// display error message, log the error
			ErrorLogger.log(e.getMessage());	
		}
		
		return dataTable;
	}

	/**
	 * Used to search the Artists table for the Artist received from the user
	 * Sends data to a DefaultTableModel
	 * @param searchTerm
	 * @return dataTable
	 */
	public static DefaultTableModel searchArtists(String searchTerm) {
		
		//SQL statement
		//String sql = "SELECT * FROM artist WHERE band_name LIKE '%" + searchTerm + "%';";
		String sql = "SELECT * FROM artist WHERE band_name LIKE '%" + searchTerm + "%' OR first_name LIKE '%" + searchTerm +
						"%' OR last_name LIKE '%" + searchTerm + "%';";
		DefaultTableModel dataTable = null;		//Used to display the data
		try {
			DbUtilities db = new DbUtilities();
			dataTable = db.getDataTable(sql);	//Pass SQL statement
		} catch (SQLException e) {
			// display error message, log the error
			ErrorLogger.log(e.getMessage());	
		}
				
		return dataTable;
	}
	

}
