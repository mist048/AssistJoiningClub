package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import tool.Constant;

public class ClubDAO {
	private final String driverClassName = "org.postgresql.Driver";
	private final String url = "jdbc:postgresql://localhost/assistjoiningclub"; // local
	private final String user = "postgres";
	private final String password = Constant.POSTGRES_PASSWORD;
	private Connection connection;
	private ResultSet resultSet;

	private PreparedStatement prepStmt_I; // INSERT用
	private PreparedStatement prepStmt_U; // UPDATE用
	private PreparedStatement prepStmt_D; // DELETE用
	private PreparedStatement prepStmt_S; // SELECT用
	private PreparedStatement prepStmt_S_id_pass; // SELECT用(id,passwprd)
	private PreparedStatement prepStmt_S_id; // SELECT用(ID)
	private PreparedStatement prepStmt_S_mail; // SELECT用(mail)
	private PreparedStatement prepStmt_S_clubinfoid; // SELECT用(clubinfoid)
	private PreparedStatement prepStmt_S_keyword; // SELECT用(keyword)
	private PreparedStatement prepStmt_S_count; // SELECT用(全部カウント)
	private PreparedStatement prepStmt_I_info; // INSERT用(clubinfoテーブル)
	private PreparedStatement prepStmt_D_info; // DELETE用(clubinfoテーブル)

	private String strPrepSQL_I = "INSERT INTO club VALUES(?, ?, ?, ?, ?, ?)";
	private String strPrepSQL_U = "UPDATE club SET name=?, password=?, mail=?, recogn=? WHERE id=?";
	private String strPrepSQL_D = "DELETE FROM club WHERE id=?";
	private String strPrepSQL_S = "SELECT * FROM club LIMIT " + Constant.MAX_OF_DISPLAYS + " OFFSET ?";
	private String strPrepSQL_S_id_pass = "SELECT COUNT(*) FROM club WHERE id=? AND password=?";
	private String strPrepSQL_S_id = "SELECT * FROM club WHERE id=?";
	private String strPrepSQL_S_mail = "SELECT * FROM club WHERE mail=?";
	private String strPrepSQL_S_clubinfoid = "SELECT COUNT(*) AS cnt FROM club WHERE clubinfoid=?";
	private String strPrepSQL_S_keyword = "SELECT * FROM club WHERE name LIKE ?";
	private String strPrepSQL_S_count = "SELECT COUNT(*) AS cnt FROM club";
	private String strPrepSQL_I_info = "INSERT INTO clubinfo VALUES(?, null, null, 0, null, null)";
	private String strPrepSQL_D_info = "DELETE FROM clubinfo WHERE id=?";

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
			prepStmt_S_clubinfoid = connection.prepareStatement(strPrepSQL_S_clubinfoid);
			prepStmt_S_keyword = connection.prepareStatement(strPrepSQL_S_keyword);
			prepStmt_S_count = connection.prepareStatement(strPrepSQL_S_count);
			prepStmt_I_info = connection.prepareStatement(strPrepSQL_I_info);
			prepStmt_D_info = connection.prepareStatement(strPrepSQL_D_info);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected boolean find(String id, String password) { //	idとpasswordの一致確認
		try {
			prepStmt_S_id_pass.setString(1, id);
			prepStmt_S_id_pass.setString(2, password);
			resultSet = prepStmt_S_id_pass.executeQuery();
			resultSet.next();
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

	protected void delete(String id, String clubinfoid) { //	削除
		try {
			prepStmt_D.setString(1, id);
			prepStmt_D.executeUpdate();
			prepStmt_D_info.setString(1, clubinfoid);
			prepStmt_D_info.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected boolean findById(String id) { //	重複確認（id）
		int count = 0;
		try {
			prepStmt_S_id.setString(1, id);
			resultSet = prepStmt_S_id.executeQuery();
			while (resultSet.next()) {
				count++;
			}
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	protected boolean findByMail(String mail) { //	重複確認（mail）
		int count = 0;
		try {
			prepStmt_S_mail.setString(1, mail);
			resultSet = prepStmt_S_mail.executeQuery();
			while (resultSet.next()) {
				count++;
			}
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	protected boolean findByClubinfoid(String clubinfoid) { //	重複確認（clubinfoid）
		int count = 0;
		try {
			prepStmt_S_clubinfoid.setString(1, clubinfoid);
			resultSet = prepStmt_S_clubinfoid.executeQuery();
			resultSet.next();
			count = resultSet.getInt("cnt");
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	protected Club[] findByKeyword(String keywords[]) { //	検索
		ArrayList<Club> hited = new ArrayList<Club>();
		try {
			for (int i = 0; i < keywords.length; i++) {
				prepStmt_S_keyword.setString(1, "%" + keywords[i] + "%");
				resultSet = prepStmt_S_keyword.executeQuery();
				while (resultSet.next()) {
					Club club = new Club();
					club.setId(resultSet.getString("id"));
					club.setName(resultSet.getString("name"));
					club.setPassword(resultSet.getString("password"));
					club.setMail(resultSet.getString("mail"));
					club.setRecogn(resultSet.getString("recogn"));
					club.setClubInfoId(resultSet.getString("clubinfoid"));
					boolean isDuplicate = false; // 重複フラグ
					for (Club hitedClub : hited) {
						if (hitedClub.getId().equals(club.getId())) {
							isDuplicate = true;
						}
					}
					if (!isDuplicate) {
						hited.add(club);
					}
				}
				resultSet.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hited.toArray(new Club[hited.size()]);
	}

	protected Club getClub(String id) { //	Clubクラスの作成
		Club club = new Club();
		try {
			prepStmt_S_id.setString(1, id);
			resultSet = prepStmt_S_id.executeQuery();
			while (resultSet.next()) {
				club.setId(resultSet.getString("id"));
				club.setName(resultSet.getString("name"));
				club.setPassword(resultSet.getString("password"));
				club.setMail(resultSet.getString("mail"));
				club.setRecogn(resultSet.getString("recogn"));
				club.setClubInfoId(resultSet.getString("clubinfoid"));
			}
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
			while (resultSet.next()) {
				Club club = new Club();
				club.setId(resultSet.getString("id"));
				club.setName(resultSet.getString("name"));
				club.setPassword(resultSet.getString("password"));
				club.setMail(resultSet.getString("mail"));
				club.setRecogn(resultSet.getString("recogn"));
				club.setClubInfoId(resultSet.getString("clubinfoid"));
				clubs.add(club);
			}
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clubs.toArray(new Club[clubs.size()]);
	}

	public String getMail(String id) {
		String mail = null;
		try {
			prepStmt_S_id.setString(1, id);
			resultSet = prepStmt_S_id.executeQuery();
			resultSet.next();
			mail = resultSet.getString("mail");
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mail;
	}

	protected int getNumOfClubs() {
		int count = 0;
		try {
			resultSet = prepStmt_S_count.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt("cnt");
			}
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
}
