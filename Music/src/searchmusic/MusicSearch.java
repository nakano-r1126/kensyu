package searchmusic;

import java.io.IOException;
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
import musicdto.Musicdto;

/**
 * Servlet implementation class MusicSearch
 */
@WebServlet("/MusicSearch")
public class MusicSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MusicSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		Itemdao itemdao = new Itemdao();
		String m_name = null;
		int m_cd = 0;

		String no= request.getParameter("m_cd");
		if (no != "") {
			try {
			m_cd = Integer.valueOf(no).intValue();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}else {
				no = "aaaaaaaaaaaaaaaaaaaa";
			}


		m_name = request.getParameter("m_name");
		if(m_name == "") {
			m_name = "aaaaaaaaaaaaaaaaaaaaa";
		}
		else {

		}
		List<Musicdto> list = itemdao.findmusic(m_cd, m_name);

		HttpSession session = request.getSession();
		session.setAttribute("musicdate", list);

        ServletContext context = this.getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/Music_Search.jsp");
		dispatcher.forward(request, response);
	}
}
