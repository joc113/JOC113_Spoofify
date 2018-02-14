package joc113_SpotifyKnockoff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

/**
 * This tester has code commented out that can be used to test the various classes and methods
 * Delete slashes and enjoy
 * @author Josh Chamberlain
 * version 1.1
 */
public class Tester {
	public static void main(String[] args){
		
		
		/*
		//use this to test ErrorLogger
		try {
			int x=1;
			int y=0;
			x = x/y;
		}
		catch (Exception e){
		
			ErrorLogger.log(e.getMessage());
		}
		*/
		
		
		//Use to test Artist and Song constructors + addArtist method
		//Song testSong = new Song("Subterranean Homesick Blues", 3.25, "1965-06-07", "1964-09-22");
		//Artist testArtist = new Artist("", "", "oh boy");
		//testSong.addArtist(testArtist);
		//Now we delete what we've created
		//testSong.deleteArtist(testArtist);
		//testArtist.setBio("ohhhhhh boy");

		//Testing other constructors for data already in the database
		//Artist testArtist2 = new Artist("e75f8452-1da5-436a-bf53-8d7b3d9bb5f1");
		//Song testSong2 = new Song("094f060f-bbf4-4d9a-8b06-25cb6453f4a0");
		
		//Deletes the desired song from the database
		//Song testSong = new Song("064be841-8343-4627-be0c-6dbe69d89f51");
		//testSong.deleteSong("3b7b3500-b1ae-4d6f-aa44-36a39bf55791");
		
		//Creating a new Album from scratch and one out of an existing one in the database
		//Album testAlbum = new Album("Cleopatra", "2016-08-19", "Universal", 12, "PG-13", 1.2);
		
		//Album testAlbum = new Album("bleb", "2016-08-19", "Universal", 12, "PG-13", 1.2);
		//testAlbum.deleteAlbum("2f9d9271-8e9d-4993-b920-8ab450815b61");
		
		//Album methods testing section
		//testAlbum.addSong(testSong);
		//testAlbum.deleteSong("064be841-8343-4627-be0c-6dbe69d89f51");
		//testAlbum.deleteSong(testSong);
		
		/*
		//gets all song results
		Map<String, Song> songList = new Hashtable<String, Song>();
		Vector<Song> songTable = new Vector<>();
		try {
			DbUtilities db = new DbUtilities();
			String sql = "SELECT * FROM song;";
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){
				Song s = new Song(rs.getString("song_id"));
				songList.put(s.getSongID(), s);
				//System.out.println(s.getTitle());
			}
			//db.closeDbConnection();
			//db = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
		
	}

}
