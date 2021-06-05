package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import constant.Constant;
import dto.dto;

public class Login_dao {

	public int readJdbcDriver(HttpServletRequest request) throws Exception {

		// JDBCドライバの読み込み
		try {
			// postgresSQLのJDBCドライバを読みこみ
			Class.forName("org.postgresql.Driver");
		} catch(ClassNotFoundException e) {
			// JDBCドライバが見つからない場合
			e.printStackTrace();

			//読込で異常終了
			return Constant.RET_NG;

		}

		return Constant.RET_OK;

	}


	public List<dto> getDBLoginChk(HttpServletRequest request, String login_id, String login_pass) throws Exception {

		Class.forName("org.postgresql.Driver");
		//-----------------
		// 接続
		//-----------------
		//DBにコネクションするために必要　"jdbc:postgresql://[場所(Domain)]:[ポート番号]/[DB名]"
		Connection conn = DriverManager.getConnection( Constant.JDBC_CONNECTION ,
				Constant.JDBC_USER, // ログイン
				Constant.JDBC_PASS); // パスワード

		//SELECT文をターゲットにする時に必要構文
		Statement stmt = conn.createStatement();

		List<dto> list = new ArrayList<dto>();

		try {
			//loginIDとpasswordチェック
			String sql = "SELECT u_id, u_pass  FROM id ";

			sql = sql + " WHERE u_id = '" + login_id + "'";
			sql = sql + "   AND u_pass = '" + login_pass + "'";

			//sql文をexcuteする
			ResultSet rs = stmt.executeQuery(sql);

			//listにaddしていく
			while (rs.next()) {
				list.add(new dto(rs.getString("u_id"),
						rs.getString("u_pass")
						)
						);
			}

			rs.close();

		} catch (Exception e) {
			e.printStackTrace();

			//ログイン処理で異常終了
			return null;

		} finally {
			stmt.close();
			conn.close();
		}

		return list;

	}
}