package joc113_SpotifyKnockoff;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import java.io.FileWriter;
import java.io.BufferedWriter;

/**
 * This is a class used to log all errors to a txt file instead of stack trace
 * @author Josh Chamberlain
 * version 1.1
 */
public class ErrorLogger {

	/**
	 * method sends the error to the txt file
	 * @param errorMessage 
	 */
	public static void log(String errorMessage) {
		//Save the following information to errorlog.txt
		//Date, Time, errorMessage \n
		
		try{
			//For some reason, I have to use the absolute file path. Relative didn't work
			FileWriter fw = new FileWriter("errorlogger.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			//Set a String to a timestamp
			//Found method at https://stackoverflow.com/questions/23068676/how-to-get-current-timestamp-in-string-format-in-java-yyyy-mm-dd-hh-mm-ss
			//Author: https://stackoverflow.com/users/882403/dimoniy
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			//write timestamp to file
			bw.write(timeStamp);
			bw.newLine();
			//write error message to file
			bw.write(errorMessage);
			bw.newLine();
			bw.close();
			//This is null for now because I have not created the GUI yet
			JOptionPane.showMessageDialog(SpotifyGUI.getFrame(), "Data retrieval error");
			
			//Remind myself to use full file path name if the file can't be found
			} catch (IOException e) {
			    System.out.println("Use full file path for error messages. Do not use relative path.");
			}
	}
}
