package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import tool.Constant;

public class UserDAO {
	private static final String url = "jdbc:postgresql://" + Constant.SQL_HOSTNAME + "/" + Constant.DBNAME;
	private static final String driverClassName = "org.postgresql.Driver";
	private final String user = Constant.POSTGRES_USER;
	private final String password = Constant.POSTGRES_PASSWORD;
	private Connection connection;
	private ResultSet resultSet;

	private PreparedStatement prepStmt_I; // INSERT用
	private PreparedStatement prepStmt_U; // UPDATE用
	private PreparedStatement prepStmt_D; // DELETE用
	private PreparedStatement prepStmt_S_id; // SELECT用(IDによって)
	private PreparedStatement prepStmt_S_id_pass; // SELECT用(ID,passwprdによって)
	private PreparedStatement prepStmt_S_all; // SELECT用(全部)
	private PreparedStatement prepStmt_S_count; // SELECT用(全部カウント)
	private PreparedStatement prepStmt_S_mail; // SELECT用(メールアドレスによって)

	private String strPrepSQL_I = "INSERT INTO general VALUES(?, ?, ?, ?)";
	private String strPrepSQL_U = "UPDATE general SET name=?, password=?, mail=? WHERE id=?";
	private String strPrepSQL_D = "DELETE FROM general WHERE id=?";
	private String strPrepSQL_S_id = "SELECT * FROM general WHERE id=?";
	private String strPrepSQL_S_id_pass = "SELECT COUNT(*) AS cnt FROM general WHERE id=? AND password=?";
	private String strPrepSQL_S_all = "SELECT * FROM general LIMIT " + Constant.MAX_OF_DISPLAYS + " OFFSET ?";
	private String strPrepSQL_S_count = "SELECT COUNT(*) AS cnt FROM general";
	private String strPrepSQL_S_mail = "SELECT * FROM general WHERE mail=?";

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
			while (resultSet.next()) {
				count = resultSet.getInt("cnt");
			}
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

	protected User getUser(String id) {
		User user = new User();
		try {
			prepStmt_S_id.setString(1, id);
			resultSet = prepStmt_S_id.executeQuery();
			while (resultSet.next()) {
				user.setId(resultSet.getString("id"));
				user.setName(resultSet.getString("name"));
				user.setPassword(resultSet.getString("password"));
				user.setMail(resultSet.getString("mail"));
			}
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	protected User[] getAllUsers(int firstIndex) {
		ArrayList<User> users = new ArrayList<User>();
		try {
			prepStmt_S_all.setInt(1, firstIndex);
			resultSet = prepStmt_S_all.executeQuery();
			while (resultSet.next()) { // 次の行があれば
				User user = new User();
				user.setId(resultSet.getString("id"));
				user.setName(resultSet.getString("name"));
				user.setPassword(resultSet.getString("password"));
				user.setMail(resultSet.getString("mail"));
				users.add(user);
			}
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users.toArray(new User[users.size()]);
	}

	protected int getNumOfUsers() {
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

	public String getMail(String id) {
		String mail = null;
		try {
			prepStmt_S_id.setString(1, id);
			resultSet = prepStmt_S_id.executeQuery();
			while (resultSet.next()) {
				mail = resultSet.getString("mail");
			}
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mail;
	}

}
