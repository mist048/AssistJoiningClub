package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import tool.Constant;

public class ClubDAO {
	final String driverClassName = "org.postgresql.Driver"; // ここからいつもの
	final String url = "jdbc:postgresql://localhost/assitjoiningclub"; // local
	final String user = "postgres";
	final String password = "akabane";
	private Connection connection;
	private ResultSet resultSet;

	private PreparedStatement prepStmt_I; // INSERT用
	private PreparedStatement prepStmt_U; // UPDATE用
	private PreparedStatement prepStmt_D; // DELETE用
	private PreparedStatement prepStmt_S; // SELECT用
	private PreparedStatement prepStmt_S_id_pass; // SELECT用(id,passwprd)
	private PreparedStatement prepStmt_S_id; // SELECT用(ID)
	private PreparedStatement prepStmt_S_mail; // SELECT用(mail)
	private PreparedStatement prepStmt_S_keyword; // SELECT用(keyword)

	final String db_name = "club";
	private String strPrepSQL_I = "INSERT INTO " + db_name + " VALUES(?, ?, ?, ?, ?, ?)";
	private String strPrepSQL_U = "UPDATE " + db_name + " SET name=?, password=?, mail=?, recogn=? WHERE id=?";
	private String strPrepSQL_D = "DELETE FROM " + db_name + " WHERE id=?";
	private String strPrepSQL_S = "SELECT * FROM " + db_name + " LIMIT 50 OFFSET ?";
	private String strPrepSQL_S_id_pass = "SELECT COUNT(*) FROM " + db_name + " WHERE id=? AND password=?";
	private String strPrepSQL_S_id = "SELECT * FROM " + db_name + " WHERE id=?";
	private String strPrepSQL_S_mail = "SELECT * FROM " + db_name + " WHERE mail=?";
	private String strPrepSQL_S_keyword = "SELECT * FROM " + db_name + " WHERE name LIKE ?";
	private String strPrepSQL_OR = "OR LIKE ?";

	protected ClubDAO() {
		try { // ドライバマネージャとコネクション
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);

			prepStmt_I = connection.prepareStatement(strPrepSQL_I);
			prepStmt_U = connection.prepareStatement(strPrepSQL_U);
			prepStmt_D = connection.prepareStatement(strPrepSQL_D);
			prepStmt_S = connection.prepareStatement(strPrepSQL_S);
			prepStmt_S_id_pass = connection.prepareStatement(strPrepSQL_S_id_pass);
			prepStmt_S_id = connection.prepareStatement(strPrepSQL_S_id);
			prepStmt_S_mail = connection.prepareStatement(strPrepSQL_S_mail);
			prepStmt_S_keyword = connection.prepareStatement(strPrepSQL_S_keyword);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected boolean find(String id, String password) { //	idとpasswordの一致確認
		try {
			prepStmt_S_id_pass.setString(1, id);
			prepStmt_S_id_pass.setString(2, password);
			resultSet = prepStmt_S_id_pass.executeQuery();
			if (resultSet.getInt("count") == 0) {
				resultSet.close();
				return false;
			}
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	protected void insert(String id, String name, String password, String mail, String recogn) { //	追加
		String clubinfoid = new String();

		try {
			prepStmt_I.setString(1, id);
			prepStmt_I.setString(2, name);
			prepStmt_I.setString(3, password);
			prepStmt_I.setString(4, mail);
			prepStmt_I.setString(5, recogn);
			prepStmt_I.setString(6, clubinfoid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void update(String id, String name, String password, String mail, String recogn) { //	更新
		try {
			prepStmt_U.setString(5, id);
			prepStmt_U.setString(1, name);
			prepStmt_U.setString(2, password);
			prepStmt_U.setString(3, mail);
			prepStmt_U.setString(4, recogn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected boolean delete(String id, String password) { //	削除
		if (find(id, password)) {
			try {
				prepStmt_D.setString(1, id);
				prepStmt_D.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		} else {
			return false;
		}
	}

	protected boolean findById(String id) { //	重複確認（id）
		try {
			prepStmt_S_id.setString(1, id);
			resultSet = prepStmt_S_id.executeQuery();
			if (resultSet == null) {
				resultSet.close();
				return false;
			}
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	protected boolean findByMail(String mail) { //	重複確認（mail）
		try {
			prepStmt_S_mail.setString(1, mail);
			resultSet = prepStmt_S_mail.executeQuery();
			if (resultSet == null) {
				resultSet.close();
				return false;
			}
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	protected Club[] findByKeyword(String keyword[]) { //	検索
		String keywords = "%";
		for (int i = 0; i < keyword.length; i++) {
			keywords += keyword[i] + "%";
		}
		Club club = new Club();
		try {
			prepStmt_S_keyword.setString(1, keywords);
			resultSet = prepStmt_S_keyword.executeQuery();
			if (resultSet == null) {
				resultSet.close();
				return null;
			}
			while (resultSet.next()) {
				club.setId(resultSet.getString(Constant.ID));
				club.setName(resultSet.getString(Constant.NAME));
				club.setPassword(resultSet.getString(Constant.PASSWORD));
				club.setMail(resultSet.getString(Constant.MAIL));
				club.setRecogn(resultSet.getString(Constant.RECOGN));
				club.setClubInfoId(resultSet.getString(Constant.CLUB_INFO_ID));
			}
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return club;
	}

	protected Club getClub(String id) { //	Clubクラスの作成
		Club club = new Club();
		try {
			prepStmt_S_id.setString(1, id);
			resultSet = prepStmt_S_id.executeQuery();
			club.setId(resultSet.getString(Constant.ID));
			club.setName(resultSet.getString(Constant.NAME));
			club.setPassword(resultSet.getString(Constant.PASSWORD));
			club.setMail(resultSet.getString(Constant.MAIL));
			club.setRecogn(resultSet.getString(Constant.RECOGN));
			club.setClubInfoId(resultSet.getString(Constant.CLUB_INFO_ID));
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return club;
	}

	protected Club[] getAllClubs(int firstIndex) { //	Clubクラスの作成
		ArrayList<Club> clubs = new ArrayList<Club>();
		try {
			prepStmt_S.setInt(1, firstIndex);
			resultSet = prepStmt_S.executeQuery();
			if (resultSet == null) {
				Club club = new Club();
				club.setId(resultSet.getString(Constant.ID));
				club.setName(resultSet.getString(Constant.NAME));
				club.setPassword(resultSet.getString(Constant.PASSWORD));
				club.setMail(resultSet.getString(Constant.MAIL));
				club.setRecogn(resultSet.getString(Constant.RECOGN));
				club.setClubInfoId(resultSet.getString(Constant.CLUB_INFO_ID));
				clubs.add(club);
			}
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (Club[]) clubs.toArray();
	}
}
