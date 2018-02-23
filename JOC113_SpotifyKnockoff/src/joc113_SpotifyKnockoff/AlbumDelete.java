package joc113_SpotifyKnockoff;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * This is used to delete an Album instance in the database
 * @param efactory is the Persistence factory
 * @param emanager manages the factory
 * @author Josh Chamberlain
 * version 1.1
 */
public class AlbumDelete {

	EntityManagerFactory efactory = Persistence.createEntityManagerFactory("JOC113_SpotifyKnockoff");
	EntityManager emanager = efactory.createEntityManager();
	
	/**
	 * Default constructor
	 */
	public AlbumDelete() {
	}
	/**
	 * Finds a Album in the database, creates an object for it, then uses that to delete the object in the database
	 * @param albumID is used to identify the Song that will be deleted
	 */
	public void delete(String albumID) {
		
		//begin the transaction
		emanager.getTransaction().begin();
		//Create an object for the Song that corresponds to the songID
		Album album = emanager.find(Album.class, albumID);
		album.deleteSong(albumID);
		
		//Get this object to persist to the database
		emanager.persist(album);
		emanager.getTransaction().commit();
		emanager.close();
		efactory.close();
	}

}
