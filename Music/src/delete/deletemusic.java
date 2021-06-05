package delete;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Item.Itemdao;
import constant.Constant;
import musicdto.Musicdto;

/**
 * Servlet implementation class deletemusic
 */
@WebServlet("/deletemusic")
public class deletemusic extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public deletemusic() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//ラジオボタンで指定したテーブル削除

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html:charset=UTF-8");

		Connection conn =null;
		Statement smt=null;

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try{
			conn = DriverManager.getConnection( Constant.JDBC_CONNECTION ,
					Constant.JDBC_USER, // ログイン
					Constant.JDBC_PASS); // パスワード

			smt=conn.createStatement();
			String nu=null;
			//ラジオボタンの行番号取得
			nu=request.getParameter("radio");
			int num=Integer.parseInt(nu);

			String sql="select * from m_music order by m_cd limit "+1 +" OFFSET "+ num;

			ResultSet rs =smt.executeQuery(sql);

			String str=null;
			while(rs.next()){

				str=rs.getString("m_cd");

			}

			String sq="delete from m_music where m_cd= "+ str;

			int in =smt.executeUpdate(sq);



		}catch(SQLException e){
			throw new ServletException(e);
		}finally{
			if(smt != null){
				try{smt.close();}catch(SQLException ignore){}
			}if(smt != null){
				try{conn.close();}catch(SQLException ignore){}
			}
		}
		Itemdao itemdao = new Itemdao();
		HttpSession session = request.getSession();
		List<Musicdto> list;
		list = itemdao.findUser();
		session.setAttribute("musicdate", list);
		request.getRequestDispatcher("Music_Search.jsp").forward(request,response);}
}
