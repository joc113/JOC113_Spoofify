package joc113_SpotifyKnockoff;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This is mainly a notes page. Will delete before final submission
 * Contains some light notes on DbUtilities class, etc.
 * @author Josh Chamberlain
 * version 1.1
 */
public class Spotify {
	
	public static void main (String[] args) {
		/*
		String hostName = "sis-teach-01.sis.pitt.edu";
		String dbName = "spotify_knockoff";
		String userName = "spotifyUser";
		String password = "spotifyUser123";
		
		//Creating a connection String
		//This is a way of taking those four variables we just made in a way that authenticates with
		//JDBC connection stipulations
		//This is the very specific syntax we have to use
		String connString = "jdbc:mysql://" + hostName + "/"
				+ dbName + "?user=" + userName
				+ "&password=" + password;
		
		//It's good to test the program frequently throughout design. This is one way to do it
		System.out.println(connString);
		
		//This is a singleton design pattern. It's case sensitive
		//We also have to add the try/multi-catch, since we're connecting to a database
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			//This is connecting us to our specific url that we created above
			//We'll have to import a connection from java.sql
			Connection conn = DriverManager.getConnection(connString);
			
			//Statement allows us to convert a String into an object that jdbc understands
			String sql = "SELECT * FROM users;";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				//Important to note that MySQL columns are 1-based, so we start with the 1st String
				System.out.println(rs.getString(1) + "/t" + rs.getString(2));
				//You can use either the number of the column (1,2,etc) or you can use the name of the column as a String
				//This will just retrieve String data
				
			}
			
			//This updates the data in MySQL and puts the data into there
			//The "sql" variable is just a MySQL query
			statement.executeUpdate(sql);
			
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		//This catch block is here because we had to complete the try clause that we added above with the url thing
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	

}
