package joc113_SpotifyKnockoff;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ArtistCreate {
	
	//Finds the project that is linked with a particular database and look at all the associated classes.
		//Then get the data and load it into memory
		EntityManagerFactory efactory = Persistence.createEntityManagerFactory("JOC113_SpotifyKnockoff");
		//Create an instance of EntityManager, which is controlled by the Factory
		EntityManager emanager = efactory.createEntityManager();
	
		public ArtistCreate() {
			
		}
		public void create(String firstName, String lastName, String bandName, String bio) {
			
			//Start a transaction. Anything can be rolled back if something goes wrong
			emanager.getTransaction().begin();
			
			//Creates an Artist with some attributes shown below
			Artist artist = new Artist();
			//Set all of the parameters for the Song object being created in the Persistence layer
			artist.setArtistID(UUID.randomUUID().toString());
			artist.setFirstName(firstName);
			artist.setLastName(lastName);
			artist.setBandName(bandName);
			artist.setBio(bio);
			
			//Now the Artist is an object in our persistence layer
			emanager.persist(artist);
			emanager.getTransaction().commit();
			emanager.close();
			efactory.close();
			
		}

}
