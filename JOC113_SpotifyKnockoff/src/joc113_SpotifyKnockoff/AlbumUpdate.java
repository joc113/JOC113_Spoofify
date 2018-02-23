package joc113_SpotifyKnockoff;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Provides methods to update all attributes of an Album in the database
 * @author Josh Chamberlain
 * @param efactory the EntityManagerFactory used for the Persistence layer of the application
 * @param emanager manages the EntityManagerFactory
 */
public class AlbumUpdate {
	
	//Finds the project that is linked with a particular database and look at all the associated classes.
	//Then get the data and load it into memory
	EntityManagerFactory efactory = Persistence.createEntityManagerFactory("JOC113_SpotifyKnockoff");
	//Create an instance of EntityManager, which is controlled by the Factory
	EntityManager emanager = efactory.createEntityManager();

	/**
	 * Default constructor
	 */
	public AlbumUpdate() {
	}
	/**
	 * Updates the title attribute
	 * @param albumID used to identify the Album
	 * @param newTitle is the updated title
	 */
	public void updateTitle(String albumID, String newTitle){
		
		//Start a transaction. Anything can be rolled back if something goes wrong
		emanager.getTransaction().begin();
		
		//Find a Album based on the primary key, then update this attribute
		Album album = emanager.find(Album.class, albumID);
		album.setTitle(newTitle);
	
		//Now the Album is an object in our persistence layer
		emanager.persist(album);
		emanager.getTransaction().commit();
		emanager.close();
		efactory.close();
	}
	/**
	 * Updates the length attribute
	 * @param albumID used to identify the Album
	 * @param newLength is the updated length
	 */
	public void updateLength(String albumID, int newLength) {
		
		//Start a transaction. Anything can be rolled back if something goes wrong
		emanager.getTransaction().begin();
		
		//Find a Album based on the primary key, then update this attribute
		Album album = emanager.find(Album.class, albumID);
		album.setLength(newLength);
	
		//Now the Album is an object in our persistence layer
		emanager.persist(album);
		emanager.getTransaction().commit();
		emanager.close();
		efactory.close();
	}
	/**
	 * Updates the release date attribute
	 * @param albumID used to identify the Album
	 * @param newRelease is the updated release date
	 */
	public void updateReleaseDate(String albumID, String newRelease) {
		
		//Start a transaction. Anything can be rolled back if something goes wrong
		emanager.getTransaction().begin();
		
		//Find a Album based on the primary key, then update this attribute
		Album album = emanager.find(Album.class, albumID);
		album.setReleaseDate(newRelease);
	
		//Now the Album is an object in our persistence layer
		emanager.persist(album);
		emanager.getTransaction().commit();
		emanager.close();
		efactory.close();
	}
	/**
	 * Updates the cover image path attribute
	 * @param albumID used to identify the Album
	 * @param newPath is the updated file path
	 */
	public void updateCoverImagePath(String albumID, String newPath) {
		
		//Start a transaction. Anything can be rolled back if something goes wrong
		emanager.getTransaction().begin();
		
		//Find a Album based on the primary key, then update this attribute
		Album album = emanager.find(Album.class, albumID);
		album.setCoverImagePath(newPath);
	
		//Now the Album is an object in our persistence layer
		emanager.persist(album);
		emanager.getTransaction().commit();
		emanager.close();
		efactory.close();
	}
	/**
	 * Updates the recording company attribute
	 * @param albumID used to identify the Album
	 * @param newCompany is the updated recording company
	 */
	public void updateRecordingCompany(String albumID, String newCompany) {
		
		//Start a transaction. Anything can be rolled back if something goes wrong
		emanager.getTransaction().begin();
		
		//Find a Album based on the primary key, then update this attribute
		Album album = emanager.find(Album.class, albumID);
		album.setRecordingCompany(newCompany);
	
		//Now the Album is an object in our persistence layer
		emanager.persist(album);
		emanager.getTransaction().commit();
		emanager.close();
		efactory.close();
	}
	/**
	 * Updates the number of tracks attribute
	 * @param albumID used to identify the Album
	 * @param newTracks is the updated number fo tracks in the Album
	 */
	public void updateNumberOfTracks(String albumID, int newTracks) {
		
		//Start a transaction. Anything can be rolled back if something goes wrong
		emanager.getTransaction().begin();
		
		//Find a Album based on the primary key, then update this attribute
		Album album = emanager.find(Album.class, albumID);
		album.setNumberOfTracks(newTracks);
	
		//Now the Album is an object in our persistence layer
		emanager.persist(album);
		emanager.getTransaction().commit();
		emanager.close();
		efactory.close();
	}
	/**
	 * Updates the PMRC rating attribute
	 * @param albumID used to identify the Album
	 * @param newRating is the updated PMRC rating
	 */
	public void updateRating(String albumID, String newRating) {
		
		//Start a transaction. Anything can be rolled back if something goes wrong
		emanager.getTransaction().begin();
		
		//Find a Album based on the primary key, then update this attribute
		Album album = emanager.find(Album.class, albumID);
		album.setRating(newRating);
	
		//Now the Album is an object in our persistence layer
		emanager.persist(album);
		emanager.getTransaction().commit();
		emanager.close();
		efactory.close();
	}
	/**
	 * Updates the length attribute
	 * @param albumID used to identify the Album
	 * @param newLength is the updated length of the album
	 */
	public void updateLength(String albumID, double newLength) {
		
		//Start a transaction. Anything can be rolled back if something goes wrong
		emanager.getTransaction().begin();
		
		//Find a Album based on the primary key, then update this attribute
		Album album = emanager.find(Album.class, albumID);
		album.setLength(newLength);
	
		//Now the Album is an object in our persistence layer
		emanager.persist(album);
		emanager.getTransaction().commit();
		emanager.close();
		efactory.close();
	}
}
