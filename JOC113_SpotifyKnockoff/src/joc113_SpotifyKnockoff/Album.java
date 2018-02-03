package joc113_SpotifyKnockoff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

/**
 * Provides Methods For:
 * 1. Creating Album Objects from scratch
 * 2. Creating Objects that relate to existing database records
 * 3. Updating the database as needed
 * @author Josh Chamberlain
 * @version 1.1
 */
public class Album {
	
	private String albumID;				//unique id
	private String title;				//title of album
	private String releaseDate;			//date of album release
	private String coverImagePath;		//file path for cover image
	private String recordingCompany;	//record label
	private int numberOfTracks;			//int for number of tracks in the album
	private String rating;				//PMRC rating for parents
	private double length;				//length of the album
	Map<String, Song> albumSongs;		//Map for referencing songs in the album

	
	/**
	 * Constructor for creating a new Album Object from scratch
	 * Transports this information to MySQL database
	 * @param title
	 * @param releaseDate
	 * @param recordingCompany
	 * @param numberOfTracks
	 * @param rating
	 * @param length
	 */
	public Album (String title, String releaseDate, String recordingCompany, int numberOfTracks,
			String rating, double length) {
		
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Retrieves information from database to create an Album Object
	 * @param albumID
	 */
	public Album(String albumID) {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * delete an album from the database by referncing its primary key ID
	 * @param albumID
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
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Deletes a song from Album's Hashtable and album_song junction table
	 * References the Song Object itself
	 * @param song
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
			// TODO Auto-generated catch block
			e.printStackTrace();		//debug
		}
	}
	
	/**
	 * Setter for coverImagePath param
	 * @param coverImagePath
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
	
	/**
	 * Getter for albumID
	 * @return
	 */
	public String getAlbumID() {
		return albumID;
	}
	/**
	 * Getter for coverImagePath
	 * @return
	 */
	public String getCoverImagePath() {
		return coverImagePath;
	}
	
}
