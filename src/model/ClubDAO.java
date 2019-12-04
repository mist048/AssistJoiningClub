package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClubDAO {
	String driverClassName = "org.postgresql.Driver"; // ここからいつもの
	String url = "jdbc:postgresql://localhost/test"; // local
	String user = "postgres";
	String password = "akabane";
	Connection connection;
	ResultSet resultSet;

	PreparedStatement prepStmt_I; // INSERT用
	PreparedStatement prepStmt_U; // UPDATE用
	PreparedStatement prepStmt_D; // DELETE用
	PreparedStatement prepStmt_S; // SELECT用(id,passwprd)
	PreparedStatement prepStmt_S_id; // SELECT用(ID)
	PreparedStatement prepStmt_S_mail; // SELECT用(mail)

	String db_name = "assitjoiningclub";
	String strPrepSQL_I = "INSERT INTO " + db_name + " VALUES(?, ?, ?, ?, ?)";
	String strPrepSQL_U = "UPDATE " + db_name + " SET name=?, password=?, mail=?, recogn=? WHERE id=?";
	String strPrepSQL_D = "DELETE FROM " + db_name + " WHERE id=?";
	String strPrepSQL_S = "SELECT * FROM " + db_name + " WHERE id=? AND password=?";
	String strPrepSQL_S_id = "SELECT * FROM " + db_name + " WHERE id=?";
	String strPrepSQL_S_mail = "SELECT * FROM " + db_name + " WHERE mail=?";

	protected ClubDAO() {
		try { // ドライバマネージャとコネクション
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);

			prepStmt_I = connection.prepareStatement(strPrepSQL_I);
			prepStmt_U = connection.prepareStatement(strPrepSQL_U);
			prepStmt_D = connection.prepareStatement(strPrepSQL_D);
			prepStmt_S = connection.prepareStatement(strPrepSQL_S);
			prepStmt_S_id = connection.prepareStatement(strPrepSQL_S_id);
			prepStmt_S_mail = connection.prepareStatement(strPrepSQL_S_mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected boolean find(String id, String password) { //	idとpasswordの一致確認
		try {
			prepStmt_S.setString(1, id);
			prepStmt_S.setString(2, password);
			resultSet = prepStmt_S.executeQuery();
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

	protected void insert(String id, String name,String password, String mail, String recogn) { //	追加
		try {
			prepStmt_I.setString(1, id);
			prepStmt_I.setString(2, name);
			prepStmt_I.setString(3, password);
			prepStmt_I.setString(4, mail);
			prepStmt_I.setString(5, recogn);
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

	protected void delete(String id) { //	削除
		try {
			prepStmt_D.setString(1, id);
			prepStmt_D.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected boolean findById(String id) {	//	重複確認（id）
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

	protected boolean findByMail(String mail) {	//	重複確認（mail）
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

	protected Club findByKeyword(String keyword[]) { //	検索
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

	protected Club getClub(String id) { //	Clubクラスの作成
		Club club=new Club();
		try {
			prepStmt_S_id.setString(1, id);
			resultSet = prepStmt_S_id.executeQuery();

			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return club;
	}

	protected Club[] getAllClubs() { //	Clubクラスの作成
		Club[] clubs=new Club[];
		Club club=new Club();
		return club;
	}
}
