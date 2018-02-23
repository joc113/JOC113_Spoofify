package joc113_SpotifyKnockoff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Provides Methods For:
 * 1. Creating Artist Objects from scratch
 * 2. Creating Objects that relate to existing database records
 * 3. Updating the database as needed
 * @param artistID is the unique ID generated for the Artist
 * @param firstName is the first name of the Artist, if there is one
 * @param lastName is the last name of the Artist, if there is one
 * @param bandName is the name of the band, if there is one
 * @param bio is a short biography about the Artist that the user can read
 * @author Josh Chamberlain
 * @version 1.1
 */
//Use JPA to connect with the database
@Entity
//Link with the desired database table
@Table (name = "artist")
public class Artist {
	
	//auto-generate a unique ID for the Artist
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	/* Specify the column names as they are in MySQL syntax so we don't run into issues
	 * since our variable names don't have the same names as the MySQL columns*/
	@Column (name = "artist_id")
	private String artistID;		//primary key
	
	@Column (name = "first_name")
	private String firstName;		//first name of artist
	
	@Column (name = "last_name")
	private String lastName;		//last name of artist
	
	@Column (name = "band_name")
	private String bandName;		//band name
	
	@Column (name = "bio")
	private String bio;				//short bio for every Artist
	
	
	/**
	 * Constructor used to create a new Artist object from scratch
	 * @param firstName is set with this method
	 * @param lastName is set with this method
	 * @param bandName is set with this method
	 */
	//No bio field in the constructor because it's not required. Instead, make it a setter
	public Artist(String firstName, String lastName, String bandName) {
		//Make a call to super
		super();
		//Set the params to the necessary values
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
			//log the error
			ErrorLogger.log(e.getMessage());
		}
		
	}
	/**
	 * Retrieves data from database to create an Artist object with those parameters
	 * @param artistID is used to identify the necessary Artist object
	 */
	//Adapted from Song constructor - retrieves an existing record in the database, and creates an object for it
	public Artist(String artistID){
		//Make call to super
		super();
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
			//log the error
			ErrorLogger.log(e.getMessage());
		}
	}
	/**
	 * //Delete an artist from a song's artists, referencing the primary key ID
	 * @param artistID is used to identify the necessary Artist object to be deleted
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
			//log the error
			ErrorLogger.log(e.getMessage());
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
			//log the error
			ErrorLogger.log(e.getMessage());
		}
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the bandName
	 */
	public String getBandName() {
		return bandName;
	}

	/**
	 * @param bandName the bandName to set
	 */
	public void setBandName(String bandName) {
		this.bandName = bandName;
	}

	/**
	 * @return the bio
	 */
	public String getBio() {
		return bio;
	}

	/**
	 * @param artistID the artistID to set
	 */
	public void setArtistID(String artistID) {
		this.artistID = artistID;
	}

	/**
	 * Default Constructor which is needed for the Persistence functions
	 */
	public Artist() {
		super();
	}

}
