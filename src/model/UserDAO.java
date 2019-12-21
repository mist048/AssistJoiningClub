package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import tool.Constant;

public class UserDAO {
	String db_name = "assistjoiningclub";
	String driverClassName = "org.postgresql.Driver"; // ここからいつもの
	String url = "jdbc:postgresql://localhost/" + db_name; // local
	String user = "postgres";
	String password = Constant.POSTGRES_PASSWORD;
	Connection connection;
	ResultSet resultSet;

	PreparedStatement prepStmt_I; // INSERT用
	PreparedStatement prepStmt_U; // UPDATE用
	PreparedStatement prepStmt_D; // DELETE用
	PreparedStatement prepStmt_S_id; // SELECT用(IDによって)
	PreparedStatement prepStmt_S_id_pass; // SELECT用(ID,passwprdによって)
	PreparedStatement prepStmt_S_all; // SELECT用(全部)
	PreparedStatement prepStmt_S_count; // SELECT用(全部カウント)
	PreparedStatement prepStmt_S_mail; // SELECT用(メールアドレスによって)

	String strPrepSQL_I = "INSERT INTO general VALUES(?, ?, ?, ?)";
	String strPrepSQL_U = "UPDATE general SET name=?, password=?, mail=? WHERE id=?";
	String strPrepSQL_D = "DELETE FROM general WHERE id=?";
	String strPrepSQL_S_id = "SELECT * FROM general WHERE id=?";
	String strPrepSQL_S_id_pass = "SELECT COUNT(*) AS cnt FROM general WHERE id=? AND password=?";
	String strPrepSQL_S_all = "SELECT * FROM general LIMIT ? OFFSET ?";
	String strPrepSQL_S_count = "SELECT COUNT(*) AS cnt FROM general";
	String strPrepSQL_S_mail = "SELECT COUNT(*) AS cnt FROM general WHERE mail=?";

	protected UserDAO() {
		try { // ドライバマネージャとコネクション
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);

			prepStmt_I = connection.prepareStatement(strPrepSQL_I);
			prepStmt_U = connection.prepareStatement(strPrepSQL_U);
			prepStmt_D = connection.prepareStatement(strPrepSQL_D);
			prepStmt_S_id = connection.prepareStatement(strPrepSQL_S_id);
			prepStmt_S_id_pass = connection.prepareStatement(strPrepSQL_S_id_pass);
			prepStmt_S_all = connection.prepareStatement(strPrepSQL_S_all);
			prepStmt_S_count = connection.prepareStatement(strPrepSQL_S_count);
			prepStmt_S_mail = connection.prepareStatement(strPrepSQL_S_mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected boolean find(String id, String password) {
		int count = 0;
		try {
			prepStmt_S_id_pass.setString(1, id);
			prepStmt_S_id_pass.setString(2, password);
			resultSet = prepStmt_S_id_pass.executeQuery();
			resultSet.next();
			count = resultSet.getInt("cnt");
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (count != 0) {
			return true;
		} else {
			return false;
		}
	}

	protected void insert(String id, String name, String password, String mail) {
		try {
			prepStmt_I.setString(1, id);
			prepStmt_I.setString(2, name);
			prepStmt_I.setString(3, password);
			prepStmt_I.setString(4, mail);
			prepStmt_I.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void update(String id, String name, String password, String mail) {
		try {
			prepStmt_U.setString(4, id);
			prepStmt_U.setString(1, name);
			prepStmt_U.setString(2, password);
			prepStmt_U.setString(3, mail);
			prepStmt_U.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void delete(String id, String password) {
		try {
			prepStmt_D.setString(1, id);
			prepStmt_D.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected boolean findById(String id) {
		int count = 0;
		try {
			prepStmt_S_id.setString(1, id);
			resultSet = prepStmt_S_id.executeQuery();
			while (resultSet.next()) { // 次の行があれば
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

	protected boolean findByMail(String mail) {
		int count = 0;
		try {
			prepStmt_S_mail.setString(1, mail);
			resultSet = prepStmt_S_mail.executeQuery();
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

	protected User getUser(String id) {
		User user = new User();
		try {
			prepStmt_S_id.setString(1, id);
			resultSet = prepStmt_S_id.executeQuery();
			resultSet.next();
			user.setId(resultSet.getString("id"));
			user.setName(resultSet.getString("name"));
			user.setPassword(resultSet.getString("password"));
			user.setMail(resultSet.getString("mail"));
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	protected User[] getAllUsers(int firstIndex) {
		ArrayList<User> allUsers = new ArrayList<User>();
		try {
			prepStmt_S_all.setInt(1, firstIndex);
			prepStmt_S_all.setInt(2, Constant.MAX_OF_DISPLAYS + 1);
			resultSet = prepStmt_S_id.executeQuery();
			while (resultSet.next()) { // 次の行があれば
				User user = new User();
				user.setId(resultSet.getString("id"));
				user.setName(resultSet.getString("name"));
				user.setPassword(resultSet.getString("password"));
				user.setMail(resultSet.getString("mail"));
				allUsers.add(user);
			}
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		User[] allUserInfo = (User[]) allUsers.toArray();
		return allUserInfo;
	}

	protected int getNumOfUsers() {
		int count = 0;
		try {
			resultSet = prepStmt_S_count.executeQuery();
			count = resultSet.getInt("cnt");
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

}
