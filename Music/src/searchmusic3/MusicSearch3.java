package searchmusic3;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constant.Constant;
import musicdto.Musicdto;

/**
 * Servlet implementation class MusicSearch3
 */
@WebServlet("/MusicSearch3")
public class MusicSearch3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MusicSearch3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
	     request.setCharacterEncoding("UTF-8");

	     Connection conn =null;
	     Statement smt=null;
	     List<Musicdto> list = new ArrayList<Musicdto>();

	     try{
	    	 conn = DriverManager.getConnection( Constant.JDBC_CONNECTION ,
						Constant.JDBC_USER, // ログイン
						Constant.JDBC_PASS); // パスワード
	         smt = conn.createStatement();
	         String sql = "SELECT *  FROM m_music ORDER BY m_cd ASC limit 3 offset 6";
	         ResultSet rs =smt.executeQuery(sql);

	        	 while (rs.next()) {
	     		    list.add(new Musicdto(
	     		    		rs.getInt("m_cd"),
	     		    		rs.getString("m_gazou"),
	     		    		rs.getString("m_name"),
	     		    		rs.getString("m_singer"),
	     		    		rs.getString("m_genre"),
	     		    		rs.getInt("m_price"),
	     		    		rs.getTimestamp("date_create"),
	     		    		rs.getTimestamp("date_update")
	     		    						)
	     		    		);
	     		}

	     }catch(SQLException e){
	         throw new ServletException(e);
	     }finally{
	         if(smt != null){
	             try{smt.close();}catch(SQLException ignore){}
	         }if(smt != null){
	             try{conn.close();}catch(SQLException ignore){}
	         }
	     }
			HttpSession session = request.getSession();
			session.setAttribute("musicdate", list);

	     ServletContext context = this.getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher("/Music_Search3.jsp");
			dispatcher.forward(request, response);

	 }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
