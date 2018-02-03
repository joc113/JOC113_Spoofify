package joc113_SpotifyKnockoff;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;

/**
 * Not needed yet
 * @author Josh Chamberlain
 * version 1.1
 */
public class SpotifyGUI {
/*
	//This is the global variable for the frame that was created
	private JFrame frame;
	//I had to put this one here because WindowBuilder made it a local variable
	JRadioButton rdbtnAlbums = new JRadioButton("Albums");

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpotifyGUI window = new SpotifyGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	/*
	public SpotifyGUI() {
		initialize();
	}
	*/

	//It's very important to makre sure WindowBuilder isn't only creating local variables
	//It has to make global variables that can be accessed from other parts of the program
	/**
	 * Initialize the contents of the frame.
	 */
	/*
	private void initialize() {
		//Luckily, it did make this a global variable
		frame = new JFrame("Spotify");
		frame.setBounds(100, 100, 1000, 600);
		//This sets everything absolutely with pixels
		frame.getContentPane().setLayout(null);
		
		
		rdbtnAlbums.setBounds(40, 137, 127, 25);
		frame.getContentPane().add(rdbtnAlbums);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void getSongData() {
		
		String sql = "SELECT * FROM songs;";
		
		try {
			DbUtilities db = new DbUtilities();
			DefaultTableModel dataTable = db.getDataTable(sql);
			tblData.setFillsViewpowrtheight(true);
			tblData.setShowGrid(true);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
}
