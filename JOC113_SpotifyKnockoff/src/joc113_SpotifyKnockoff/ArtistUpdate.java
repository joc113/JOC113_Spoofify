package joc113_SpotifyKnockoff;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ArtistUpdate {

	//Finds the project that is linked with a particular database and look at all the associated classes.
	//Then get the data and load it into memory
	EntityManagerFactory efactory = Persistence.createEntityManagerFactory("JOC113_SpotifyKnockoff");
	//Create an instance of EntityManager, which is controlled by the Factory
	EntityManager emanager = efactory.createEntityManager();
	
	public ArtistUpdate() {
	}
	public void updateFirstName(String artistID, String newFirstName) {
		
		//Start a transaction. Anything can be rolled back if something goes wrong
		emanager.getTransaction().begin();
		
		//Find a Artist based on the primary key, then update this attribute
		Artist artist = emanager.find(Artist.class, artistID);
		artist.setFirstName(newFirstName);
	
		//Now the Artist is an object in our persistence layer
		emanager.persist(artist);
		emanager.getTransaction().commit();
		emanager.close();
		efactory.close();
	}
	
	public void updateLastName(String artistID, String newLastName) {
		
		//Start a transaction. Anything can be rolled back if something goes wrong
		emanager.getTransaction().begin();
				
		//Find a Artist based on the primary key, then update this attribute
		Artist artist = emanager.find(Artist.class, artistID);
		artist.setLastName(newLastName);
			
		//Now the Artist is an object in our persistence layer
		emanager.persist(artist);
		emanager.getTransaction().commit();
		emanager.close();
		efactory.close();
	}
	
	public void updateBandName(String artistID, String newBandName) {
		
		//Start a transaction. Anything can be rolled back if something goes wrong
		emanager.getTransaction().begin();
				
		//Find a Artist based on the primary key, then update this attribute
		Artist artist = emanager.find(Artist.class, artistID);
		artist.setBandName(newBandName);
			
		//Now the Artist is an object in our persistence layer
		emanager.persist(artist);
		emanager.getTransaction().commit();
		emanager.close();
		efactory.close();
	}
	
	public void updateBio(String artistID, String newBio) {
		
		//Start a transaction. Anything can be rolled back if something goes wrong
		emanager.getTransaction().begin();
	
		//Find a Artist based on the primary key, then update this attribute
		Artist artist = emanager.find(Artist.class, artistID);
		artist.setBio(newBio);
			
		//Now the Artist is an object in our persistence layer
		emanager.persist(artist);
		emanager.getTransaction().commit();
		emanager.close();
		efactory.close();
	}
}
