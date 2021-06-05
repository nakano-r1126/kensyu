package musicupdate;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Item.Itemdao;
import constant.Constant;
import dao.Login_dao;
import musicdto.Musicdto;

/**
 * Servlet implementation class Music_Update
 */
@WebServlet("/Music_Update")
public class Music_Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Music_Update() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//更新処理
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		Itemdao itemdao = new Itemdao();
		List<Musicdto> list;
    	Connection conn = null;
    	//更新時間の取得
    	Timestamp date_update = new java.sql.Timestamp (new java.util.Date().getTime());

    	//IDの取得
    	int m_cd = Integer.parseInt(request.getParameter("m_cd"));
    	//CD名の取得
    	String m_name = request.getParameter("m_name");
    	//CD歌手名の取得
		String m_singer = request.getParameter("m_singer");
		//CDジャンルの取得
		String m_genre = request.getParameter("m_genre");
		//画像の取得
		String m_gazou =request.getParameter("m_gazou");
		//CD価格の取得
		int m_price = Integer.parseInt(request.getParameter("m_price"));
		//作成時間の取得
		String date_create = request.getParameter("date_create");

    	try{
            Class.forName("org.postgresql.Driver");
        } catch(Exception e) {
            e.printStackTrace();
        }
    	try {
    		conn = DriverManager.getConnection( Constant.JDBC_CONNECTION ,
        			Constant.JDBC_USER, // ログイン
        			Constant.JDBC_PASS);
    		if(m_gazou != "") {
    		//画像のあった時の処理
			String sql = "UPDATE m_music SET m_cd = ?, m_gazou = ?, m_name = ?, m_singer = ?, m_genre = ?, m_price = ?, date_create = ?, date_update = ? where m_cd = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, m_cd);
			stmt.setString(2, m_gazou);
			stmt.setString(3, m_name);
			stmt.setString(4, m_singer);
			stmt.setString(5, m_genre);
			stmt.setInt(6, m_price);
			stmt.setTimestamp(7, Timestamp.valueOf(date_create));
			stmt.setTimestamp(8, date_update);
			stmt.setInt(9, m_cd);
			stmt.executeUpdate();
    		}
    		else {
    			//画像がない場合の処理
    			String sql = "UPDATE m_music SET m_cd = ?, m_name = ?, m_singer = ?, m_genre = ?, m_price = ?, date_create = ?, date_update = ? where m_cd = ?";

    			PreparedStatement stmt = conn.prepareStatement(sql);

    			stmt.setInt(1, m_cd);
    			stmt.setString(2, m_name);
    			stmt.setString(3, m_singer);
    			stmt.setString(4, m_genre);
    			stmt.setInt(5, m_price);
    			stmt.setTimestamp(6, Timestamp.valueOf(date_create));
    			stmt.setTimestamp(7, date_update);
    			stmt.setInt(8, m_cd);
    			stmt.executeUpdate();
    		}

    	} catch (Exception e) {
    		// TODO 自動生成された catch ブロック
    		e.printStackTrace();

    	}
    	HttpSession session = request.getSession();
		list = itemdao.findUser();
		session.setAttribute("musicdate", list);
		ServletContext context = this.getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/Music_Search.jsp");
		dispatcher.forward(request, response);

	}

}
