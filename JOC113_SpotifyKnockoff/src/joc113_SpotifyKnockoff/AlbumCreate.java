package joc113_SpotifyKnockoff;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AlbumCreate {
	
	//Finds the project that is linked with a particular database and look at all the associated classes.
	//Then get the data and load it into memory
	EntityManagerFactory efactory = Persistence.createEntityManagerFactory("JOC113_SpotifyKnockoff");
	//Create an instance of EntityManager, which is controlled by the Factory
	EntityManager emanager = efactory.createEntityManager();
	
	public void create(String title, String releaseDate, String recordingCompany, int numberOfTracks,
						String rating, double length) {
		
		//Start a transaction. Anything can be rolled back if something goes wrong
		emanager.getTransaction().begin();
				
		//Creates an Album with some attributes shown below
		Album album = new Album();
		//Set all of the parameters for the Album object being created in the Persistence layer
		album.setAlbumID(UUID.randomUUID().toString());
		album.setTitle(title);
		album.setReleaseDate(releaseDate);
		album.setRecordingCompany(recordingCompany);
		album.setNumberOfTracks(numberOfTracks);
		album.setRating(rating);
		album.setLength(length);
				
		//Now the Album is an object in our persistence layer
		emanager.persist(album);
		emanager.getTransaction().commit();
		emanager.close();
		efactory.close();
	}
}
