

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Servlet implementation class GetArtistList
 */
@WebServlet("/GetArtistList")
public class GetArtistList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetArtistList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//indicate that responses will be in JSON
		response.setContentType("application/json");
		
		//Use DbUtilities to SELECT all attributes of artists from the database and put them in
		//the JSONArray called artistList
		try {
			DbUtilities db = new DbUtilities();
			String sql = "SELECT * FROM artist;";
			ResultSet rs;
			JSONArray artistList = new JSONArray();
			rs = db.getResultSet(sql);
			while (rs.next()) {
				JSONObject artist = new JSONObject();
				artist.put("id", rs.getString("artist_id"));
				artist.put("id", rs.getString("first_name"));
				artist.put("id", rs.getString("last_name"));
				artist.put("id", rs.getString("band_name"));
				artist.put("id", rs.getString("bio"));
				
				artistList.put(artist);
			}
			
			//Show the artistList
			response.getWriter().write(artistList.toString());
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
