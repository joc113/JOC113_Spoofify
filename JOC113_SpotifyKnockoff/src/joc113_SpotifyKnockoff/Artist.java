package joc113_SpotifyKnockoff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Provides Methods For:
 * 1. Creating Artist Objects from scratch
 * 2. Creating Objects that relate to existing database records
 * 3. Updating the database as needed
 * @author Josh Chamberlain
 * @version 1.1
 */
public class Artist {

	private String artistID;		//primary key
	private String firstName;		//first name of artist
	private String lastName;		//last name of artist
	private String bandName;		//band name
	private String bio;				//short bio
	
	/**
	 * Constructor used to create a new Artist object from scratch
	 * @param firstName
	 * @param lastName
	 * @param bandName
	 */
	//No bio field in the constructor because it's not required. Instead, make it a setter
	public Artist(String firstName, String lastName, String bandName) {
		artistID = UUID.randomUUID().toString();
		this.firstName = firstName;
		this.lastName = lastName;
		this.bandName = bandName;
		
		//SQL Statement
		String sql = "INSERT INTO artist (artist_id, first_name, last_name, band_name) " + 
				"VALUES (?, ?, ?, ?);";
		//System.out.println(sql);
		//Import SQL Statement using prepared statement
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, artistID);
			ps.setString(2,  this.firstName);
			ps.setString(3, this.lastName);
			ps.setString(4, this.bandName);
			ps.executeUpdate();
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * Retrieves data from database to create an Artist object with those parameters
	 * @param artistID
	 */
	//Adapted from Song constructor - retrieves an existing record in the database, and creates an object for it
	public Artist(String artistID){
		String sql = "SELECT * FROM artist WHERE artist_id = '" + artistID + "';";
		// System.out.println(sql);
		//SQL statement
		DbUtilities db = new DbUtilities();
		//Import SQL Statement using prepared statement
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){
				this.artistID = rs.getString("artist_id");
				this.firstName = rs.getString("first_name");
				this.lastName = rs.getString("last_name");
				this.bandName = rs.getString("band_name");
				
				//System.out.println("artistID from database: " + this.artistID);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * //Delete an artist from a song's artists, referencing the primary key ID
	 * @param artistID
	 */
	public void deleteArtist(String artistID) {
		//SQL Statement
		String sql = "DELETE FROM artist WHERE artist_id = ?;";
		
			//Import SQL statement using prepared statement
			try {
				DbUtilities db = new DbUtilities();
				Connection conn = db.getConn();
				PreparedStatement ps;
				ps = conn.prepareStatement(sql);
				ps.setString(1, this.artistID);
				db.closeDbConnection();
				db = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * getter for artistID primary key
	 * @return
	 */
	public String getArtistID() {
		return artistID;
	}
	/**
	 * Setter for bio parameter. Updates in MySQL
	 * @param bio
	 */
	//Create a setter for bio
	public void setBio(String bio) {
		//update bio in object
		//update bio in the database
		String sql = "UPDATE artist SET bio = ? WHERE artist_id = ?;";
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,  this.bio);
			ps.setString(2,  this.artistID);
			ps = conn.prepareStatement(sql);
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	//debug
		}
	}

}
