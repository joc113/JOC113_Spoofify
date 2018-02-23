package joc113_SpotifyKnockoff;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * This is used to delete a Song instance in the database
 * @param efactory is the Persistence factory
 * @param emanager manages the factory
 * @author Josh Chamberlain
 * version 1.1
 */
public class SongDelete {

	EntityManagerFactory efactory = Persistence.createEntityManagerFactory("JOC113_SpotifyKnockoff");
	EntityManager emanager = efactory.createEntityManager();
	
	/**
	 * Default constructor
	 */
	public SongDelete() {
	}
	/**
	 * Finds a Song in the database, creates an object for it, then uses that to delete the object in the database
	 * @param songID is used to identify the Song that will be deleted
	 */
	public void delete(String songID) {
		
		//begin the transaction
		emanager.getTransaction().begin();
		//Create an object for the Song that corresponds to the songID
		Song song = emanager.find(Song.class, songID);
		song.deleteSong(songID);
		
		//Get this object to persist to the database
		emanager.persist(song);
		emanager.getTransaction().commit();
		emanager.close();
		efactory.close();
	}

}
