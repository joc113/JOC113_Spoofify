package joc113_SpotifyKnockoff;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class SongUpdate {
	
		//Finds the project that is linked with a particular database and look at all the associated classes.
		//Then get the data and load it into memory
		EntityManagerFactory efactory = Persistence.createEntityManagerFactory("JOC113_SpotifyKnockoff");
		//Create an instance of EntityManager, which is controlled by the Factory
		EntityManager emanager = efactory.createEntityManager();
		
		public SongUpdate() {
		}
		public void updateTitle(String songID, String newTitle) {

			//Start a transaction. Anything can be rolled back if something goes wrong
			emanager.getTransaction().begin();
			
			//Find a Song based on the primary key, then update this attribute
			Song s = emanager.find(Song.class, songID);
			s.setTitle(newTitle);
		
			//Now the Song is an object in our persistence layer
			emanager.persist(s);
			emanager.getTransaction().commit();
			emanager.close();
			efactory.close();
		}
		
		public void updateLength(String songID, double newLength) {
			
			//Start a transaction. Anything can be rolled back if something goes wrong
			emanager.getTransaction().begin();
			
			//Find the Song based on the primary key and update this attribute
			Song s = emanager.find(Song.class, songID);
			s.setLength(newLength);
		
			//Now the Song is an object in our persistence layer
			emanager.persist(s);
			emanager.getTransaction().commit();
			emanager.close();
			efactory.close();
		}
		
		public void updateRecordDate(String songID, String newRecordDate) {
			
			//Start a transaction. Anything can be rolled back if something goes wrong
			emanager.getTransaction().begin();
			
			//Find the Song based on its primary key and update this attribute
			Song s = emanager.find(Song.class, songID);
			s.setRecordDate(newRecordDate);
		
			//Now the Song is an object in our persistence layer
			emanager.persist(s);
			emanager.getTransaction().commit();
			emanager.close();
			efactory.close();
		}
		
		public void updateReleaseDate(String songID, String newReleaseDate) {
			//Start a transaction. Anything can be rolled back if something goes wrong
			emanager.getTransaction().begin();
			
			//Find the Song based on its primary key and update this attribute
			Song s = emanager.find(Song.class, songID);
			s.setReleaseDate(newReleaseDate);
		
			//Now the Song is an object in our persistence layer
			emanager.persist(s);
			emanager.getTransaction().commit();
			emanager.close();
			efactory.close();
		}
		
		public void updateFilePath(String songID, String newFilePath) {
			//Start a transaction. Anything can be rolled back if something goes wrong
			emanager.getTransaction().begin();
			
			//Find the Song based on its primary key and update this attribute
			Song s = emanager.find(Song.class, songID);
			s.setFilePath(newFilePath);
		
			//Now the Song is an object in our persistence layer
			emanager.persist(s);
			emanager.getTransaction().commit();
			emanager.close();
			efactory.close();
			
		}

}
