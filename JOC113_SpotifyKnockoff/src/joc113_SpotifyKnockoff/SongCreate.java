package joc113_SpotifyKnockoff;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Creates a new Song in the Java Persistence layer
 * @author Josh Chamberlain
 * version 1.1
 */
public class SongCreate {
	
	//Finds the project that is linked with a particular database and look at all the associated classes.
	//Then get the data and load it into memory
	EntityManagerFactory efactory = Persistence.createEntityManagerFactory("JOC113_SpotifyKnockoff");
	//Create an instance of EntityManager, which is controlled by the Factory
	EntityManager emanager = efactory.createEntityManager();
	
	/**
	 * This is the method used to create the Song in the Persistence layer. Accepts information for all attributes
	 * @param title is the title of the Song
	 * @param length is a double for the length of the Song
	 * @param recordDate is the date the Song was recorded
	 * @param releaseDate is the date that the Song was released
	 * @param filePath is the filepath for the image associated with the Song object
	 */
	public void create(String title, double length, 
				String recordDate, String releaseDate, String filePath) {
		
		//Start a transaction. Anything can be rolled back if something goes wrong
		emanager.getTransaction().begin();
		
		//Creates a Song with some attributes shown below
		Song s = new Song();
		//Set all of the parameters for the Song object being created in the Persistence layer
		s.setSongID(UUID.randomUUID().toString());
		s.setTitle(title);
		s.setLength(length);
		s.setRecordDate(recordDate);
		s.setReleaseDate(releaseDate);
		s.setFilePath(filePath);
		
		
		//Now the Song is an object in our persistence layer
		emanager.persist(s);
		emanager.getTransaction().commit();
		emanager.close();
		efactory.close();
	}

}
