package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import tool.Constant;

public class ClubInfoDAO {
	private static final String url = "jdbc:postgresql://" + Constant.SQL_HOSTNAME + "/" + Constant.DBNAME;
	private static final String driverClassName = "org.postgresql.Driver";
	private final String user = Constant.POSTGRES_USER;
	private final String password = Constant.POSTGRES_PASSWORD;
	private Connection connection;
	private ResultSet resultSet;

	private PreparedStatement prepStmt_U; // UPDATE用
	private PreparedStatement prepStmt_U_icon; // UPDATE用(アイコン画像)
	private PreparedStatement prepStmt_U_home; // UPDATE用(ホーム画像)
	private PreparedStatement prepStmt_D; // DELETE用
	private PreparedStatement prepStmt_S_id; // SELECT用

	final String db_name = "clubinfo";
	private String strPrepSQL_U = "UPDATE clubinfo SET link=?, intro=?, member=? WHERE id=?";
	private String strPrepSQL_U_icon = "UPDATE clubinfo SET icon=? WHERE id=?";
	private String strPrepSQL_U_home = "UPDATE clubinfo SET home=? WHERE id=?";
	private String strPrepSQL_D = "DELETE FROM clubinfo WHERE id=?";
	private String strPrepSQL_S_id = "SELECT * FROM clubinfo WHERE id=?";

	protected ClubInfoDAO() {
		try { // ドライバマネージャとコネクション
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);

			prepStmt_U = connection.prepareStatement(strPrepSQL_U);
			prepStmt_U_icon = connection.prepareStatement(strPrepSQL_U_icon);
			prepStmt_U_home = connection.prepareStatement(strPrepSQL_U_home);
			prepStmt_D = connection.prepareStatement(strPrepSQL_D);
			prepStmt_S_id = connection.prepareStatement(strPrepSQL_S_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void update(String id, String link, String intro, int member) { //	更新
		try {
			prepStmt_U.setString(4, id);
			prepStmt_U.setString(1, link);
			prepStmt_U.setString(2, intro);
			prepStmt_U.setInt(3, member);
			prepStmt_U.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void updateIcon(String id, String icon) { //	アイコン画像更新
		try {
			prepStmt_U_icon.setString(2, id);
			prepStmt_U_icon.setString(1, icon);
			prepStmt_U_icon.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void updateHome(String id, String home) { //	ホーム画像更新
		try {
			prepStmt_U_home.setString(2, id);
			prepStmt_U_home.setString(1, home);
			prepStmt_U_home.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void delete(String id) { //	削除
		try {
			prepStmt_D.setString(1, id);
			prepStmt_D.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected ClubInfo getClubInfo(String id) { //	ClubInfoクラスの作成
		ClubInfo clubinfo = new ClubInfo();
		try {
			prepStmt_S_id.setString(1, id);
			resultSet = prepStmt_S_id.executeQuery();
			while (resultSet.next()) {
				clubinfo.setId(resultSet.getString("id"));
				clubinfo.setLink(resultSet.getString("link"));
				clubinfo.setIntro(resultSet.getString("intro"));
				clubinfo.setMember(resultSet.getInt("member"));
				clubinfo.setIcon(resultSet.getString("icon"));
				clubinfo.setHome(resultSet.getString("home"));
			}
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clubinfo;
	}
}
