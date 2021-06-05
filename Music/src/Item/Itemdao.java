package Item;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import constant.Constant;
import musicdto.Musicdto;

public class Itemdao{

	public 	List<Musicdto>	findUser() {
	//テーブルデーター3件表示
	List<Musicdto> list = new ArrayList<Musicdto>();

	Connection conn = null;
    ResultSet rs = null;
    Statement stmt = null;


	try {
		conn = DriverManager.getConnection( Constant.JDBC_CONNECTION ,
    			Constant.JDBC_USER, // ログイン
    			Constant.JDBC_PASS);

		stmt = conn.createStatement();
    	String sql = "SELECT *  FROM m_music ORDER BY m_cd ASC limit 3 offset 0";

		//sql文をexcuteする
		rs = stmt.executeQuery(sql);

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
	} catch (Exception e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();

	}
	return list;
	}


	public List<Musicdto> findmusic(int m_cd, String m_name){
			//IDとCD名で検索
	        Connection con = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;

	        List<Musicdto> list = new ArrayList<Musicdto>();

	        try {
	        	con = DriverManager.getConnection( Constant.JDBC_CONNECTION ,
	        			Constant.JDBC_USER, // ログイン
	        			Constant.JDBC_PASS);

	            String sql = "SELECT * FROM m_music WHERE CAST(m_cd AS VARCHAR) LIKE ? or m_name LIKE ?";


			    ps = con.prepareStatement(sql);
			    ps.setString(1,"%"+m_cd+"%");
			    ps.setString(2,"%"+m_name+"%");
			    rs = ps.executeQuery();


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
	        } catch (Exception e) {
	    		// TODO 自動生成された catch ブロック
	    		e.printStackTrace();

	    	}
	    	return list;
	    	}
	}
