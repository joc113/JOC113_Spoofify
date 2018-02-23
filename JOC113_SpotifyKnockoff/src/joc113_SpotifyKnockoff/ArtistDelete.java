package joc113_SpotifyKnockoff;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 * This is used to delete an Artist instance in the database
 * @param efactory is the Persistence factory
 * @param emanager manages the factory
 * @author Josh Chamberlain
 * version 1.1
 */
public class ArtistDelete {

	EntityManagerFactory efactory = Persistence.createEntityManagerFactory("JOC113_SpotifyKnockoff");
	EntityManager emanager = efactory.createEntityManager();
	
	/**
	 * Default constructor
	 */
	public ArtistDelete() {
	}
	/**
	 * Finds an Artist in the database, creates an object for it, then uses that to delete the object in the database
	 * @param artistID is used to identify the Artist that will be deleted
	 */
	public void delete(String artistID) {
		
		//begin the transaction
		emanager.getTransaction().begin();
		//Create an object for the Song that corresponds to the songID
		Artist artist = emanager.find(Artist.class, artistID);
		
		//Object is removed from the database
		emanager.remove(artist);
		emanager.getTransaction().commit();
		//close connection
		emanager.close();
		efactory.close();
	}

}
