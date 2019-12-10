package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import tool.Constant;

public class ClubInfoDAO {
	final String driverClassName = "org.postgresql.Driver"; // ここからいつもの
	final String url = "jdbc:postgresql://localhost/assitjoiningclub"; // local
	final String user = "postgres";
	final String password = "akabane";
	private Connection connection;
	private ResultSet resultSet;

	//private PreparedStatement prepStmt_I; // INSERT用
	private PreparedStatement prepStmt_U; // UPDATE用
	private PreparedStatement prepStmt_D; // DELETE用
	private PreparedStatement prepStmt_S_id; // SELECT用

	final String db_name = "clubinfo";
	//private String strPrepSQL_I = "INSERT INTO " + db_name + " VALUES(?, ?, ?, ?, ?, ?, ?)";
	private String strPrepSQL_U = "UPDATE " + db_name
			+ " SET link=?, recogn=?, intro=?, member=?, submember=?, icon=? home=? WHERE id=?";
	private String strPrepSQL_D = "DELETE FROM " + db_name + " WHERE id=?";
	private String strPrepSQL_S_id = "SELECT * FROM " + db_name + " WHERE id=?";

	protected ClubInfoDAO() {
		try { // ドライバマネージャとコネクション
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);

			//prepStmt_I = connection.prepareStatement(strPrepSQL_I);
			prepStmt_U = connection.prepareStatement(strPrepSQL_U);
			prepStmt_D = connection.prepareStatement(strPrepSQL_D);
			prepStmt_S_id = connection.prepareStatement(prepStmt_S_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void update(String id, String link, String recogn, String intro, int member, int submember, String icon,
			String home) { //	更新
		try {
			prepStmt_U.setString(8, id);
			prepStmt_U.setString(1, link);
			prepStmt_U.setString(2, recogn);
			prepStmt_U.setString(3, intro);
			prepStmt_U.setInt(4, member);
			prepStmt_U.setInt(5, submember);
			prepStmt_U.setString(6, icon);
			prepStmt_U.setString(7, home);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected boolean delete(String id) { //	削除
		try {
			prepStmt_D.setString(1, id);
			prepStmt_D.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	protected ClubInfo getClubInfo(String id) {
		ClubInfo clubinfo = new ClubInfo();
		try {
			prepStmt_S_id.setString(1, id);
			resultSet = prepStmt_S_id.executeQuery();
			clubinfo.setId(resultSet.getString(Constant.ID));
			clubinfo.setLink(resultSet.getString(Constant.LINK));
			clubinfo.setRecogn(resultSet.getString(Constant.RECOGN));
			clubinfo.setIntro(resultSet.getString(Constant.MAIL));
			clubinfo.setMember(resultSet.getString(Constant.RECOGN));
			clubinfo.getIcon(resultSet.getString(Constant.CLUB_INFO_ID));
			clubinfo.setHome(resultSet.getString(Constant.CLUB_INFO_ID));
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return club;
	}
}
