package joc113_SpotifyKnockoff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Provides Methods For:
 * 1. Creating Album Objects from scratch
 * 2. Creating Objects that relate to existing database records
 * 3. Updating the database as needed
 * @param albumID is the unique ID of the Album
 * @param title is the name of the Album
 * @param releaseDate is the date that the album was released
 * @param coverImagePath is the file path for the Album cover
 * @param numberOfTracks is the number of Songs associated with the Album
 * @param rating is the PMRC rating of the album for parental purposes
 * @param length is the length of the album
 * @author Josh Chamberlain
 * @version 1.1
 */
//Use JPA to connect with the database
@Entity
//Link with the desired database table
@Table (name = "album")
public class Album {
	
	//Create a unique ID for the Album
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	/* Specify the column names as they are in MySQL syntax so we don't run into issues
	 * since our variable names don't have the same names as the MySQL columns*/
	@Column (name = "album_id")
	private String albumID;				//unique id
	
	@Column (name = "title")
	private String title;				//title of album
	
	@Column (name = "release_date")
	private String releaseDate;			//date of album release
	
	@Column (name = "cover_image_path")
	private String coverImagePath;		//file path for cover image
	
	@Column (name = "recording_company_name")
	private String recordingCompany;	//record label
	
	@Column (name = "number_of_tracks")
	private int numberOfTracks;			//int for number of tracks in the album
	
	@Column (name = "PMRC_rating")
	private String rating;				//PMRC rating for parents
	
	@Column (name = "length")
	private double length;				//length of the album
	//Transient tag because the Map is not a column in the database
	@Transient
	Map<String, Song> albumSongs;		//Map for referencing songs in the album

	/**
	 * Default Constructor which is needed for the Persistence functions
	 */
	public Album() {
		super();
	}
	
	/**
	 * Constructor for creating a new Album Object from scratch
	 * Transports this information to MySQL database
	 * @param title is set with this constructor
	 * @param releaseDate is set with this constructor
	 * @param recordingCompany is set with this constructor
	 * @param numberOfTracks is set with this constructor
	 * @param rating is set with this constructor
	 * @param length is set with this constructor
	 */
	public Album (String title, String releaseDate, String recordingCompany, int numberOfTracks,
			String rating, double length) {
		super();
		//Set appropriate values
		this.albumID = UUID.randomUUID().toString();
		this.title = title;
		this.releaseDate = releaseDate;
		this.recordingCompany = recordingCompany;
		this.numberOfTracks = numberOfTracks;
		this.rating = rating;
		this.length = length;
		
		//SQL statement
		String sql = "INSERT INTO album (album_id,title,release_date,cover_image_path,"
				+ "recording_company_name,number_of_tracks,PMRC_rating,length) ";
		sql += "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		
		//Send to MySQL database using prepared statement
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.albumID);
			ps.setString(2,  this.title);
			ps.setString(3, this.releaseDate);
			ps.setString(4, "");
			ps.setString(5, this.recordingCompany);
			ps.setInt(6, this.numberOfTracks);
			ps.setString(7, this.rating);
			ps.setDouble(8, length);
			ps.executeUpdate();
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			//log the error
			ErrorLogger.log(e.getMessage());
		}
	}
	/**
	 * Retrieves information from database to create an Album Object
	 * @param albumID the String passed into the method that is used to identify which Album to retrieve
	 */
	public Album(String albumID) {
		super();
		//SQL Statement
		String sql = "SELECT * FROM album WHERE album_id = '" + albumID + "';";
		// System.out.println(sql);
		
		//Send statement to MySQL using prepared statement
		try {
			DbUtilities db = new DbUtilities();
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){
				this.albumID = rs.getString("album_id");
				this.title = rs.getString("title");
				this.releaseDate = rs.getDate("release_date").toString();
				this.coverImagePath = rs.getString("cover_image_path");
				this.recordingCompany = rs.getString("recording_company_name");
				this.numberOfTracks = rs.getInt("number_of_tracks");
				this.rating = rs.getString("PMRC_rating");
				this.length = rs.getDouble("length");
				// System.out.println("Album title from database: " + this.title);
			}
		} catch (SQLException e) {
			//log the error
			ErrorLogger.log(e.getMessage());
		}
	}
	/**
	 * delete an album from the database by referncing its primary key ID
	 * @param albumID the unique ID used to identify which Album is being retrieved
	 */
	public void deleteAlbum(String albumID) {
		
		String sql = "DELETE FROM album WHERE album_id = ?;";
		
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps;
			ps = conn.prepareStatement(sql);
			ps.setString(1, this.albumID);
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			//log the error
			ErrorLogger.log(e.getMessage());
		}
	}
	
	/**
	 * Add a Song object to the Album's Hashtable and the album_song junction table
	 * References Song Object
	 * @param song
	 */
	public void addSong(Song song) {
		
		albumSongs = new Hashtable<String, Song>();
		albumSongs.put(song.getSongID(), song);
		
		//SQL Statement
		String sql = "INSERT INTO album_song (fk_album_id, fk_song_id) VALUES (?, ?);";
		
		//Import statement to MySQL using prepared statement
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.albumID);
			ps.setString(2, song.getSongID());
			ps.executeUpdate();
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			//log the error
			ErrorLogger.log(e.getMessage());
		}
		
	}
	
	/**
	 * Delete a song from an album's Hashtable and album_song junciton table
	 * References Song's primary key ID
	 * @param songID
	 */
	public void deleteSong(String songID) {
		Song song = new Song(songID);
		albumSongs = new Hashtable<String, Song>();
		albumSongs.remove(songID, song);
		//sql statement
		String sql = "DELETE FROM album_songs WHERE fk_song_id = ?;";
		
		//Import statement to MySQL using prepared statement
		try {
			//Create database object to pass the sql statement
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, songID);
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			//log the error
			ErrorLogger.log(e.getMessage());
		}
	}
	
	/**
	 * Deletes a song from Album's Hashtable and album_song junction table
	 * References the Song Object itself
	 * @param song is the Song object being referenced by the method
	 */
	public void deleteSong(Song song) {
		albumSongs = new Hashtable<String, Song>();
		albumSongs.remove(song.getSongID(), song);
		
		//SQL Statement
		String sql = "DELETE FROM album_songs WHERE fk_song_id = ?;";
		
		//Import SQL statement using prepared statement
		try {
			//Create database object to pass the sql statement
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, song.getSongID());
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			//log the error
			ErrorLogger.log(e.getMessage());
		}
	}
	
	/**
	 * Setter for coverImagePath param
	 * @param coverImagePath is the attribute being updated
	 */
	public void setCoverImagePath(String coverImagePath) {
	
		//It's not enough to just set the class property. We also need to update it in the database
		this.coverImagePath = coverImagePath;
		
		//Update this in the database
		String sql = "UPDATE album SET cover_image_path = ? WHERE album_id = ?;";
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			//update the filepath for the specified song
			ps.setString(1,  this.coverImagePath);
			ps.setString(2, this.albumID);
			ps = conn.prepareStatement(sql);
			db.closeDbConnection();
		} catch (SQLException e) {
			//log the error
			ErrorLogger.log(e.getMessage());
			}
		}
	
	/**
	 * Getter for albumID
	 * @return albumID
	 */
	public String getAlbumID() {
		return albumID;
	}
	/**
	 * Getter for coverImagePath
	 * @return albumID
	 */
	public String getCoverImagePath() {
		return coverImagePath;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the releaseDate
	 */
	public String getReleaseDate() {
		return releaseDate;
	}

	/**
	 * @param releaseDate the releaseDate to set
	 */
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
	 * @return the recordingCompany
	 */
	public String getRecordingCompany() {
		return recordingCompany;
	}

	/**
	 * @param recordingCompany the recordingCompany to set
	 */
	public void setRecordingCompany(String recordingCompany) {
		this.recordingCompany = recordingCompany;
	}

	/**
	 * @return the numberOfTracks
	 */
	public int getNumberOfTracks() {
		return numberOfTracks;
	}

	/**
	 * @param numberOfTracks the numberOfTracks to set
	 */
	public void setNumberOfTracks(int numberOfTracks) {
		this.numberOfTracks = numberOfTracks;
	}

	/**
	 * @return the rating
	 */
	public String getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}

	/**
	 * @return the length
	 */
	public double getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(double length) {
		this.length = length;
	}

	/**
	 * @return the albumSongs
	 */
	public Map<String, Song> getAlbumSongs() {
		return albumSongs;
	}

	/**
	 * @param albumSongs the albumSongs to set
	 */
	public void setAlbumSongs(Map<String, Song> albumSongs) {
		this.albumSongs = albumSongs;
	}

	/**
	 * @param albumID the albumID to set
	 */
	public void setAlbumID(String albumID) {
		this.albumID = albumID;
	}
	
}
