package entry;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import Item.Itemdao;
import constant.Constant;
import dao.Login_dao;
import musicdto.Musicdto;

@WebServlet("/Entry")
@MultipartConfig(
		location="/C:/java_2019/pleiades/workspace/Music/WebContent/upload",
		maxFileSize=1000000,
		maxRequestSize=1000000,
		fileSizeThreshold=1000000
		)
public class Entry extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
	getServletContext().getRequestDispatcher("/Music_Entry.jsp").forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	int ret = 0;

    	List<Musicdto> list;
    	Connection con = null;

    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        Login_dao loginDao = new Login_dao();
        Itemdao itemdao = new Itemdao();

    	int code = 0; //取得後キャストしたid
    	int price = 0;//取得後キャストした値段
    	//idの取得
    	String no = request.getParameter("m_cd");
		if (no != null) {
		try {
		code = Integer.valueOf(no).intValue();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		} else {

		}
		//CD名の取得
		String music = request.getParameter("m_music");
		//CD歌手名の取得
		String singer = request.getParameter("m_singer");
		//CDジャンルの取得
		String genre = request.getParameter("m_genre");
		//CD価格の取得
		String price1 = request.getParameter("m_price");
		//画像名の取得
		Part part=request.getPart("m_gazou");

		//ファイル名を取得
		String filename=Paths.get(part.getSubmittedFileName()).getFileName().toString();
		long filename1=part.getSize();
		if(filename1 == 0) {
			filename = "image.jpg";
		}
		//アップロードするフォルダ
		String path=getServletContext().getRealPath("/upload");
		part.write(path+File.separator+filename);

		if(price1 != null) {
			try {
				price = Integer.valueOf(price1).intValue();
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				} else {

				}

		try {
			ret = loginDao.readJdbcDriver(request);
			con = DriverManager.getConnection( Constant.JDBC_CONNECTION ,
	    			Constant.JDBC_USER, // ログイン
	    			Constant.JDBC_PASS);

			//更新
			String sql = "INSERT INTO m_music values(?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			//作成日取得
			Timestamp sqlDate = new java.sql.Timestamp (new java.util.Date().getTime());
			//更新日取得
			Timestamp sqlDate1 = new java.sql.Timestamp (new java.util.Date().getTime());
			stmt.setInt(1, code);
			stmt.setString(2, filename);
			stmt.setString(3, music);
			stmt.setString(4, singer);
			stmt.setString(5, genre);
			stmt.setInt(6, price);
			stmt.setTimestamp(7, sqlDate);
			stmt.setTimestamp(8, sqlDate1);
			stmt.executeUpdate();


			HttpSession session = request.getSession();
			//テーブルデーター表示
			list = itemdao.findUser();
			session.setAttribute("musicdate", list);
			ServletContext context = this.getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher("/Music_Search.jsp");
			dispatcher.forward(request, response);
			stmt.close();

		} catch (Exception e) {

			ServletContext context = this.getServletContext();
	        RequestDispatcher dispatcher = context.getRequestDispatcher("/Music_Error.jsp");
	        dispatcher.forward(request, response);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				throw new ServletException();
			}
		}

	}
}