package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import tool.Constant;

public class AdminDAO {
	private final String driverClassName = "org.postgresql.Driver";
	private final String url = "jdbc:postgresql://localhost/assistjoiningclub"; // local
	private final String user = "postgres";
	private final String password = Constant.POSTGRES_PASSWORD;
	private Connection connection;
	private ResultSet resultSet;

	private PreparedStatement prepStmt_S;
	private PreparedStatement prepStmt_S_id_pass;

	private String strPrepSQL_S = "SELECT * FROM admin";
	private String strPrepSQL_S_id_pass = "SELECT COUNT(*) FROM admin WHERE id=? AND password=?";

	protected AdminDAO() {
		try { // ドライバマネージャとコネクション
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			prepStmt_S = connection.prepareStatement(strPrepSQL_S);
			prepStmt_S_id_pass = connection.prepareStatement(strPrepSQL_S_id_pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected boolean find(String id,String password) {
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

	protected String getMail() {
		String mail = null;
		try {
			resultSet = prepStmt_S.executeQuery();
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
