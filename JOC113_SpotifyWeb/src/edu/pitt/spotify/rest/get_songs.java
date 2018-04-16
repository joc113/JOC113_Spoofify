package edu.pitt.spotify.rest;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.pitt.spotify.util.DbUtilities;

/**
 * Servlet implementation class get_songs
 */

//the api label is just a virtual folder that you can use to configure your application
//with no parameters, the get_songs method
@WebServlet("api/get_songs")
public class get_songs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public get_songs() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//to find each of these, it will just be a MySQL query
		//title
		//artist
		//album
		//year
		
		String title = "";
		String artist = "";
		String album = "";
		String songYear = "";
		String sql = "";
		
		
		if (request.getParameter("title") != null) {
			title = request.getParameter("title");
			if (!title.equals("")) {
				sql = "SELECT * FROM song WHERE title LIKE 'title';";
			}
		}
		else if (request.getParameter("artist") != null){
			artist = request.getParameter("artist");
			if (!artist.equals("")) {
				sql = "SELECT * FROM song " + 
						"INNER JOIN song_artist ON song_id = fk_song_id " + 
						"INNER JOIN artist ON fk_artist_id = artist_id " + 
						"WHERE artist.band_name LIKE '%" + artist + "'%' " + 
						"OR artist_first_name LIKE '%'" + artist + "'%' " +
						"OR artist_last_name LIKE '%'" + artist + "'%';";
			}
		}
		else if (request.getParameter("album") != null) {
			album = request.getParameter("album");
			if(!album.equals("")) {
			sql = "SELECT * FROM song " + 
					"INNER JOIN song_album ON song_id = fk_song_id " + 
					"INNER JOIN album ON fk_album_id = album_id " + 
					"WHERE album.title LIKE '%" + album + "'%';";
			}
		}
		
		JSONArray songList = new JSONArray();
		
		try {
			DbUtilities db = new DbUtilities();
			ResultSet rs;
			rs = db.getResultSet(sql);
			while(rs.next()) {
				
				JSONObject song = new JSONObject();
				song.put("song_id", rs.getString("song_id"));
				song.put("title", rs.getString("title"));
				song.put("release_date", rs.getString("release_date"));
				song.put("record_date", rs.getString("record_date"));
				song.put("length", rs.getString("length"));
				songList.put(song);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
