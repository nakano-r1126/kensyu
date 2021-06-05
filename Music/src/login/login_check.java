package login;

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
import constant.Constant;
import dao.Login_dao;
import dto.dto;
import musicdto.Musicdto;


/**
 * Servlet implementation class SampleClass
 */
@WebServlet("/login_check")
public class login_check extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public login_check() {
		super();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Login_dao loginDao = new Login_dao();

		int ret = 0;

		try {

			//インスタンスからDaoを使用
			//JDBCドライバを読み込む
			ret = loginDao.readJdbcDriver(request);

			//単体テストコード
			//ret = -1;

			if(ret == Constant.RET_OK) {

				//ServletContextに値を保管する
				ServletContext context = this.getServletContext();

				//RequestDispatcherオブジェクトは、画面の遷移先を定義する
				RequestDispatcher dispatcher = context.getRequestDispatcher(Constant.LOGIN);

				//遷移先に転送
				dispatcher.forward(request, response);

			}else {

				//読込処理：異常終了
				request.setAttribute("message", Constant.MSG_SYSTEM_ERR_JDBC_DRIVER);
				request.setAttribute("message2", Constant.MSG_ERR_SYSTEM);

				//ServletContextに値を保管する
				ServletContext context = this.getServletContext();

				//RequestDispatcherオブジェクトは、画面の遷移先を定義する
				RequestDispatcher dispatcher = context.getRequestDispatcher(Constant.LOGIN_ERR);

				//遷移先に転送
				dispatcher.forward(request, response);

			}

		} catch (Exception e) {

			e.printStackTrace();

			//異常終了
			request.setAttribute("message", Constant.MSG_ERR_SYSTEM);

			//ServletContextに値を保管する
			ServletContext context = this.getServletContext();

			//RequestDispatcherオブジェクトは、画面の遷移先を定義する
			RequestDispatcher dispatcher = context.getRequestDispatcher(Constant.LOGIN_ERR);

			//遷移先に転送
			dispatcher.forward(request, response);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		//入力されたIDの取得
		String user_id = request.getParameter("login_id");
		//入力されたpasswordの取得
		String user_pass = request.getParameter("login_pass");
		Itemdao itemdao = new Itemdao();
		Login_dao loginDao = new Login_dao();

		List<Musicdto> list;
		List<dto> lis;

		try {
			//IDとpasswardチェック
			lis = loginDao.getDBLoginChk(request, user_id, user_pass);
			//テーブル表示
			list = itemdao.findUser();

			if(lis == null || lis.size() == 0) {
				//IDとパス未入力時ログイン画面に戻る
				ServletContext context = this.getServletContext();
				RequestDispatcher dispatcher = context.getRequestDispatcher("/Music_Login.jsp");
				dispatcher.forward(request, response);

			}else {
				//ログイン成功時検索画面
				HttpSession session2 = request.getSession();
				session2.setAttribute("musicdate", list);
				HttpSession session = request.getSession();
				session.setAttribute("userId", user_id);
				ServletContext context = this.getServletContext();
				RequestDispatcher dispatcher = context.getRequestDispatcher("/Music_Search.jsp");
				dispatcher.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

	}


}
