package joc113_SpotifyKnockoff;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Provides Methods For:
 * 1. Creating Song Objects from scratch
 * 2. Creating Objects that relate to existing database records
 * 3. Updating the database as needed
 * @param songID is the unique ID in the database
 * @param title is the title of the Song
 * @param length is the length of the Song
 * @param filePath is the file path that the Song will reference
 * @param releaseDate is the date the Song was released
 * @param recordDate is the date that the Song was recorded
 * @author Josh Chamberlain
 * @version 1.1
 */
//Use JPA to connect with the database
@Entity
//Link with the desired database table
@Table (name = "song")
public class Song {
	//Auto-generate an ID for a new Song
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	/* Specify the column names as they are in MySQL syntax so we don't run into issues
	 * since our variable names don't have the same names as the MySQL columns*/
	@Column (name = "song_id")
	private String songID;
	
	@Column (name = "title")
	private String title;
	
	@Column (name = "length")
	private double length;
	
	@Column (name = "file_path")
	private String filePath;
	
	@Column (name = "release_date")
	private String releaseDate;
	
	@Column (name = "record_date")
	private String recordDate;
	
	//This isn't a column in the database, so we have to use Transient
	//Columns that are marked as Transient are not mapped to the database. This should be used any time
	//we have a variable that should not be mapped to the database
	@Transient
	Map<String, Artist> songArtists;
	
	/**
	 * Default Constructor
	 */
	public Song() {
		super();
	}
	/**
	 * Constructor used to create a new Song object
	 * @param title song title
	 * @param length how long the song is
	 * @param releaseDate the date the song was released
	 * @param recordDate the date the song was recorded
	 */
	
	public Song(String title, double length, String releaseDate, String recordDate){
		super();
		this.title = title;
		this.length = length;
		this.releaseDate = releaseDate;
		this.recordDate = recordDate;
		this.songID = UUID.randomUUID().toString();
		
		//We need to instantiate the value for this Hashtable so we can use it in the addArtist method
		//The reason my method wasn't working before is because I was adding to a Hashtable that did not exist
		//Now it is automatically made any time the constructor is called
		songArtists = new Hashtable<String, Artist>();
		
		// System.out.println(this.songID);
		// String sql = "INSERT INTO song (song_id,title,length,file_path,release_date,record_date,fk_album_id) ";
		// sql += "VALUES ('" + this.songID + "', '" + this.title + "', " + this.length + ", '', '" + this.releaseDate + "', '" + this.recordDate + "', '" + this.albumID + "');";
		String sql = "INSERT INTO song (song_id,title,length,file_path,release_date,record_date) ";
		sql += "VALUES (?, ?, ?, ?, ?, ?);";
		
		//System.out.println(sql);
		
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.songID);
			ps.setString(2,  this.title);
			ps.setDouble(3, this.length);
			ps.setString(4, "");
			ps.setString(5, this.releaseDate);
			ps.setString(6, this.recordDate);
			ps.executeUpdate();
			db.closeDbConnection();
		} catch (SQLException e) {
			//log the error
			ErrorLogger.log(e.getMessage());
		}
	}
	
	/**
	 * Another constructor for when we don't want to have to open and close a connection for each time we call it
	 * @param songID unique ID for Song object
	 * @param title title of the song
	 * @param length
	 * @param releaseDate
	 * @param recordDate
	 */
	//This makes it so that we can just use the data that we already have access to without creating and closing connections continuously
	public Song(String songID, String title, double length, String releaseDate, String recordDate){
		super();
		this.songID = songID;
		this.title = title;
		this.length = length;
		this.releaseDate = releaseDate;
		this.recordDate = recordDate;
		//this.songID = UUID.randomUUID().toString();
		
		//We need to instantiate the value for this Hashtable so we can use it in the addArtist method
		//The reason my method wasn't working before is because I was adding to a Hashtable that did not exist
		//Now it is automatically made any time the constructor is called
		songArtists = new Hashtable<String, Artist>();
		
		// System.out.println(this.songID);
		// String sql = "INSERT INTO song (song_id,title,length,file_path,release_date,record_date,fk_album_id) ";
		// sql += "VALUES ('" + this.songID + "', '" + this.title + "', " + this.length + ", '', '" + this.releaseDate + "', '" + this.recordDate + "', '" + this.albumID + "');";
		String sql = "INSERT INTO song (song_id,title,length,file_path,release_date,record_date,fk_album_id) ";
		sql += "VALUES (?, ?, ?, ?, ?, ?, ?);";
		
		//System.out.println(sql);
		
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.songID);
			ps.setString(2,  this.title);
			ps.setDouble(3, this.length);
			ps.setString(4, "");
			ps.setString(5, this.releaseDate);
			ps.setString(6, this.recordDate);
			ps.executeUpdate();
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			//log the error
			ErrorLogger.log(e.getMessage());
		}
	}
	
	/**
	 * This creates an Object by pulling frmo the database of existing records
	 * @param songID This constructor only uses the primary key ID as the identifier
	 */
	//Retrieve an existing song object from the database based on the songID
	public Song(String songID){
		super();
		//We need to instantiate the value for this Hashtable so we can use it in the addArtist method
		songArtists = new Hashtable<String, Artist>();
		String sql = "SELECT * FROM song WHERE song_id = '" + songID + "';";
		// System.out.println(sql);
		DbUtilities db = new DbUtilities();
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){
				this.songID = rs.getString("song_id");
				// System.out.println("Song ID from database: " + this.songID);
				this.title = rs.getString("title");
				this.releaseDate = rs.getDate("release_date").toString();
				this.recordDate = rs.getDate("record_date").toString();
				this.length = rs.getDouble("length");
				// System.out.println("Song title from database: " + this.title);
			}
		} catch (SQLException e) {
			//log error
			ErrorLogger.log(e.getMessage());
		}
	
	}
	/**
	 * This deletes a song instance in the database by referencing its primary key
	 * @param songID This method references the primary key ID
	 */
	//Deletes song instance in database
	//As a note, nothing should be all the way deleted from a database
	//Just because something is not visible to the user doesn't mean it should be deleted from the database
	//You can instead make an isActive clause somewhere where queries ignore data where isActive is set to false
	public void deleteSong(String songID) {
		//Create instance of Hashtable
		songArtists = new Hashtable<String, Artist>();

		//sql statement
		String sql = "DELETE FROM song WHERE song_id = (?);";
		
		//Import String into MySQL
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps;
			ps = conn.prepareStatement(sql);
			ps.setString(1, this.songID);
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			//log the error
			ErrorLogger.log(e.getMessage());
		}
	}
	
	/**
	 * Adds to the song's artists in the junction table and the Hashtable
	 * @param artist the Artist object to be added to the database
	 */
	//adds an artist to the song's list of artists by accepting an Artist object
	public void addArtist(Artist artist) {
		songArtists = new Hashtable<String, Artist>();
		//Put artist into the Hashtable 
		songArtists.put(artist.getArtistID(), artist);
		
		//SQL Statement
		String sql = "INSERT INTO song_artist (fk_song_id, fk_artist_id) VALUES (?, ?);";
		
		//Import the SQL statement using prepared statement
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.songID);
			ps.setString(2, artist.getArtistID());
			ps.executeUpdate();
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			//log the error
			ErrorLogger.log(e.getMessage());
		}
		
	}
	
	/**
	 * Delete an Artist from the Hashtable and the junction table in the database
	 * References Artist's artistID
	 * @param artistID the unique ID referenced for this method
	 */
	//deletes an artist from a song's list of artists using that artist's artistID
	public void deleteArtist(String artistID) {
		//Create object out of the ID first, so it can be removed
		Artist artist = new Artist(artistID);
		songArtists = new Hashtable<String, Artist>();
		songArtists.remove(artist.getArtistID(), artist);
		//sql statement
		String sql = "DELETE FROM song_artist WHERE fk_artist_id = ?;";
		
		try {
			//Create database object to pass the sql statement
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, artistID);
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			//log the error
			ErrorLogger.log(e.getMessage());
		}
	}
	/**
	 * Delete from the junction table and the Hashtable by referencing the object itself
	 * @param artist entire Artist object is referenced
	 */
	public void deleteArtist(Artist artist) {
		//instantiate songArtists as Hashtable, then remove the object passed from the Hashtable
		songArtists = new Hashtable<String, Artist>();
		songArtists.remove(artist.getArtistID(), artist);
		
		//SQL delete statement
String sql = "DELETE FROM song_artist WHERE fk_artist_id = ?;";
		
		try {
			//Create database object to pass the sql statement
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, artist.getArtistID());
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			//log the error
			ErrorLogger.log(e.getMessage());
		}
	}
	/**
	 * This is a setter for the filePath of the Song
	 * @param filePath String that represents file path
	 */
	public void setFilePath(String filePath) {
		//It's not enough to just set the class property. We also need to update it in the database
		this.filePath = filePath;
		
		//SQL statement
		String sql = "UPDATE song SET file_path = ? WHERE song_id = ?;";
		//Import SQL statement using prepared statement
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			//update the filepath for the specified song
			ps.setString(1,  this.filePath);
			ps.setString(2, this.songID);
			ps = conn.prepareStatement(sql);
			db.closeDbConnection();
		} catch (SQLException e) {
			//log the error
			ErrorLogger.log(e.getMessage());
		}
	}
/**
 * Getter for songID
 * @return songID the unique song id
 */
	public String getSongID() {
		return songID;
	}

	public void setSongID(String songID) {
	this.songID = songID;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public void setSongArtists(Map<String, Artist> songArtists) {
		this.songArtists = songArtists;
	}

	//Have not used any of these methods yet, but took them from the code example.
	//Will hold onto them for now, in case we need them later
	public String getReleaseDate() {
		return releaseDate;
	}


	public String getTitle() {
		return title;
	}

	public double getLength() {
		return length;
	}

	public String getFilePath() {
		return filePath;
	}

	public String getRecordDate() {
		return recordDate;
	}

	public Map<String, Artist> getSongArtists() {
		return songArtists;
	}
	
	//I started on a method that was supposed to check if the artist existed in the database already, and would not add if it was unnecessary
	//Found out that the unnecessary thing was this method
	//Keeping for now because it may be useful later
	/*
	public void addArtist(String artist) {
		//Create new DbUtilities
		DbUtilities db = new DbUtilities();
		//Check if the artist already exists
		String sql = "SELECT * FROM artist WHERE first_name + last_name = '" + artist + "' OR"
				+ " band_name = '" + artist + "';";
		try {
			ResultSet rs = db.getResultSet(sql);
			//
			if (rs == null){
				Artist newArtist = new Artist(artist);
				songArtists.put(artist, newArtist);
			}
			
			else {
				
			}
		} catch (SQLException e) {
			//log the error
			ErrorLogger.log(e.getMessage());
		}
		
	}
		*/


}
