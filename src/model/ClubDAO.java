package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import tool.Constant;

public class ClubDAO {
	String db_name = "assistjoiningclub";
	final String driverClassName = "org.postgresql.Driver"; // ここからいつもの
	final String url = "jdbc:postgresql://localhost/" + db_name; // local
	final String user = "postgres";
	final String password = Constant.POSTGRES_PASSWORD;
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
	private PreparedStatement prepStmt_I_info; // INSERT用(clubinfoテーブル)

	private String strPrepSQL_I = "INSERT INTO club VALUES(?, ?, ?, ?, ?, ?)";
	private String strPrepSQL_U = "UPDATE club SET name=?, password=?, mail=?, recogn=? WHERE id=?";
	private String strPrepSQL_D = "DELETE FROM club WHERE id=?";
	private String strPrepSQL_S = "SELECT * FROM club LIMIT 50 OFFSET ?";
	private String strPrepSQL_S_id_pass = "SELECT COUNT(*) FROM club WHERE id=? AND password=?";
	private String strPrepSQL_S_id = "SELECT * FROM club WHERE id=?";
	private String strPrepSQL_S_mail = "SELECT * FROM club WHERE mail=?";
	private String strPrepSQL_S_keyword = "SELECT id FROM club WHERE name LIKE ?";
	private String strPrepSQL_I_info = "INSERT INTO clubinfo VALUES(?, null, null, 0, null, null)";

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
			prepStmt_I_info = connection.prepareStatement(strPrepSQL_I_info);
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

	protected void insert(String id, String name, String password, String mail, String recogn, String clubinfoid) { //	追加
		try {
			prepStmt_I_info.setString(1, clubinfoid);
			prepStmt_I_info.executeUpdate();
			prepStmt_I.setString(1, id);
			prepStmt_I.setString(2, name);
			prepStmt_I.setString(3, password);
			prepStmt_I.setString(4, mail);
			prepStmt_I.setString(5, recogn);
			prepStmt_I.setString(6, clubinfoid);
			prepStmt_I.executeUpdate();
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
			prepStmt_U.executeUpdate();
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
		HashSet<String> hited = new HashSet<String>();
		try {
			for (int i = 0; i < keyword.length; i++) {
				prepStmt_S_keyword.setString(1, keyword[i]);
				resultSet = prepStmt_S_keyword.executeQuery();
				while (resultSet.next()) {
					hited.add(resultSet.getString(Constant.ID));
				}
				resultSet.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Club[] clubs = new Club[hited.size()];
		Iterator<String> iterator = hited.iterator();
		for (int i = 0; i < clubs.length; i++) {
			clubs[i] = getClub(iterator.next());
		}
		return clubs;
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
