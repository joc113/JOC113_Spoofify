package joc113_SpotifyKnockoff;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This is the GUI for the SpotifyKnockoff application.
 * Uses the Spotify method to search the database for the input specified by the
 * user via the GUI
 * @author Josh Chamberlain
 * version 1.1
 */
public class SpotifyGUI {

	//Variable declarations of all GUI components
	private static JFrame frame;
	private JTextField txtSearch;
	private JRadioButton radShowAlbums;
	private JRadioButton radShowArtists;
	private JRadioButton radShowSongs;
	private DefaultTableModel musicData;
	private JTable tblData;
	private JScrollPane scroll;
	//Group for buttons so only one can be selected at once
	private ButtonGroup buttons = new ButtonGroup();		

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpotifyGUI window = new SpotifyGUI();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					ErrorLogger.log(e.getMessage());
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SpotifyGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		setFrame(new JFrame("Spotify"));
		getFrame().setBounds(100, 100, 1200, 550);
		getFrame().getContentPane().setLayout(null);
		
		JLabel lblViewSelector = new JLabel("Select View");
		lblViewSelector.setBounds(20, 30, 100, 15);
		getFrame().getContentPane().add(lblViewSelector);
		
		radShowAlbums = new JRadioButton("Albums");
		radShowAlbums.addActionListener(new ActionListener() {
			//if the Albums radio button is selected, display the full list of Albums
			public void actionPerformed(ActionEvent e) {
				if (radShowAlbums.isSelected()){
					tblData.setModel(Spotify.searchAlbums(""));
				}
			}
		});
		radShowAlbums.setBounds(40, 60, 150, 25);
		radShowAlbums.setSelected(true);
		getFrame().getContentPane().add(radShowAlbums);
		
		
		radShowArtists = new JRadioButton("Artists");
		radShowArtists.addActionListener(new ActionListener() {
			//if the Artists button is selected, display the full list of Artists
			public void actionPerformed(ActionEvent e) {
				if (radShowArtists.isSelected()) {
					tblData.setModel(Spotify.searchArtists(""));
				}
			}
		});
		radShowArtists.setBounds(40, 85, 150, 25);
		getFrame().getContentPane().add(radShowArtists);
		
		radShowSongs = new JRadioButton("Songs");
		radShowSongs.addActionListener(new ActionListener() {
			//if the Songs radio button is selected, display the full list of Songs
			public void actionPerformed(ActionEvent e) {
				if (radShowSongs.isSelected()) {
					tblData.setModel(Spotify.searchSongs(""));
				}
			}
		});
		radShowSongs.setBounds(40, 110, 150, 25);
		getFrame().getContentPane().add(radShowSongs);
		
		
		//Add all of the buttons to a ButtonGroup so only one can be selected at once
		buttons.add(radShowAlbums);
		buttons.add(radShowArtists);
		buttons.add(radShowSongs);
		
		//Search label
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setBounds(20, 290, 100, 20);
		getFrame().getContentPane().add(lblSearch);
		
		//Frame and txtSearch box
		getFrame().getContentPane().add(lblViewSelector);
		txtSearch = new JTextField();
		txtSearch.setBounds(20, 315, 200, 30);
		getFrame().getContentPane().add(txtSearch);
		txtSearch.setColumns(10);
		
		//Table to hold DefaultTableModel
		tblData = new JTable(musicData);
		//I took out the setBounds here because I'm using the JScrollPane's size
		//tblData.setBounds(300, 45, 600, 400);
		tblData.setFillsViewportHeight(true);
		tblData.setShowGrid(true);
		tblData.setGridColor(Color.BLACK);
		getFrame().getContentPane().add(tblData);
		
		//I referenced Stack Overflow for this next part, since my Design tab isn't working on WindowBuilder
		//url: https://stackoverflow.com/questions/14899918/dynamically-adding-jtable-to-jscrollpane
		//     Authors: Matt and Mikhail Vladimirov
		//url: https://stackoverflow.com/questions/18906835/cant-see-components-in-jscrollpane
		//     Author: MadProgrammer
		//Create a JScrollPane
		scroll = new JScrollPane();
		scroll.setBounds(300, 45, 800, 400);
		//scroll.setBounds(x, y, width, height);
		getFrame().getContentPane().add(scroll);
		scroll.setVisible(true);
		//Add the JTable to the JScrollPane
	    scroll.setViewportView(tblData);
	    scroll.getViewport();
		
		//Set the default screen to be a complete list of Albums
		tblData.setModel(Spotify.searchAlbums(""));
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if the songs button is selected, show Song search results
				if(radShowSongs.isSelected()){
					tblData.setModel(Spotify.searchSongs(txtSearch.getText()));
					//This code was used for Dmitriy's 
					//musicData = getSongData(txtSearch.getText());
					//tblData.setModel(musicData);
				}
				//If the Artist button is selected, show Artist results
				else if(radShowArtists.isSelected()) {
					tblData.setModel(Spotify.searchArtists(txtSearch.getText()));
				}
				//if the Albums button is selected, show Album search results
				else if(radShowAlbums.isSelected()) {
					tblData.setModel(Spotify.searchAlbums(txtSearch.getText()));
				}
			}
		});
		
		//Create Search button
		btnSearch.setBounds(103, 350, 115, 30);
		
		getFrame().getContentPane().add(btnSearch);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * static getter for the frame so it can be referenced in other classes
	 * @return frame
	 */
	public static JFrame getFrame() {
		return frame;
	}

	/**
	 * static setter for the frame so it can be called by another class
	 * @param frame
	 */
	public static void setFrame(JFrame frame) {
		SpotifyGUI.frame = frame;
	}	
	
}