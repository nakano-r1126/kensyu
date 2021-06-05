package update;

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

import constant.Constant;
import updatedto.UpdateDto;

/**
 * Servlet implementation class Update
 */
@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ラジオボタンで選択された番号のテーブルデータを更新画面に表示
        response.setContentType("text/html; charset=UTF-8");
     request.setCharacterEncoding("UTF-8");

     Connection conn =null;
     Statement smt=null;

     String num1=request.getParameter("radio");
     int num=Integer.parseInt(num1);

     String sql="select * from m_music order by m_cd limit "+1 +" OFFSET "+ num;


     try{
    	 conn = DriverManager.getConnection( Constant.JDBC_CONNECTION ,
					Constant.JDBC_USER, // ログイン
					Constant.JDBC_PASS); // パスワード
         smt = conn.createStatement();
         ResultSet rs =smt.executeQuery(sql);

         List<UpdateDto> list = new ArrayList<UpdateDto>();

         while(rs.next()){

        	 UpdateDto m_table=new UpdateDto();

        	 m_table.setm_cd(rs.getInt("m_cd"));
        	 m_table.setm_gazou(rs.getString("m_gazou"));
        	 m_table.setm_name(rs.getString("m_name"));
        	 m_table.setm_singer(rs.getString("m_singer"));
        	 m_table.setm_genre(rs.getString("m_genre"));
        	 m_table.setm_price(rs.getInt("m_price"));
        	 m_table.setdate_create(rs.getTimestamp("date_create"));
        	 m_table.setdate_update(rs.getTimestamp("date_update"));

             list.add(m_table);
         }
         request.setAttribute("musicdate",list);

     }catch(SQLException e){
         throw new ServletException(e);
     }finally{
         if(smt != null){
             try{smt.close();}catch(SQLException ignore){}
         }if(smt != null){
             try{conn.close();}catch(SQLException ignore){}
         }
     }


     ServletContext context = this.getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/Music_Update.jsp");
		dispatcher.forward(request, response);

 }

}
