package constant;

public class Constant {

	/******** ↓キー **********/
	/** データベース接続 **/
	public static final String JDBC_CONNECTION = "jdbc:postgresql://localhost:5432/postgres";
	public static final String JDBC_USER = "postgres";
	public static final String JDBC_PASS = "postgres";

	//戻り値
	public static final int RET_OK = 0;
	public static final int RET_NG = -1;

	/** ログインユーザー名セッションキー **/
	public static final String LOGIN_USER_ID_KEY = "login_id";
	/** ログインユーザーIDセッションキー **/
	public static final String LOGIN_USER_PASS_KEY = "login_pass";

	/******** ↓パス **********/
	/** ログイン画面 **/
	public static final String LOGIN = "/Music_Login.jsp";
	/** 担当者検索画面 **/
	public static final String TANTO_SEARCH = "/Music_Search.jsp";

	/** エラー画面 **/
	public static final String LOGIN_ERR = "ああああああ";

	/******** ↓文字列定数 **********/
	public static final String COLON = ":";

	/** エラーメッセージ(システムエラー：JDBCドライバ読込) **/
	public static final String MSG_SYSTEM_ERR_JDBC_DRIVER = "システムエラー：JDBCドライバ読込処理で異常終了しました。";
	/** エラーメッセージ(システムエラー：) **/
	public static final String MSG_ERR_SYSTEM = "システム管理者にご連絡をお願いします。";
	/** エラーメッセージ(システムエラー：) **/
	public static final String MSG_ERR_SYSTEM_LOGIN = "システムエラー(ログイン処理)：システム管理者にご連絡をお願いします。";

	/** エラーメッセージ(ログイン) **/
	public static final String MSG_ERR_LOGIN01 = "ログインIDまたはパスワードが誤っています。";

}
