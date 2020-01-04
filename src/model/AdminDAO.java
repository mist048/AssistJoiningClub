package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import tool.Constant;

public class AdminDAO {
	private static final String url = "jdbc:postgresql://" + Constant.SQL_HOSTNAME + "/" + Constant.DBNAME;
	private static final String driverClassName = "org.postgresql.Driver";
	private final String user = Constant.POSTGRES_USER;
	private final String password = Constant.POSTGRES_PASSWORD;
	private Connection connection;
	private ResultSet resultSet;

	private PreparedStatement prepStmt_S;
	private PreparedStatement prepStmt_S_id_pass;
	private PreparedStatement prepStmt_S_pass;

	private String strPrepSQL_S = "SELECT mail FROM admin";
	private String strPrepSQL_S_id_pass = "SELECT COUNT(*) FROM admin WHERE id=? AND password=?";
	private String strPrepSQL_S_pass = "SELECT COUNT(*) FROM admin WHERE password=?";

	protected AdminDAO() {
		try { // ドライバマネージャとコネクション
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			prepStmt_S = connection.prepareStatement(strPrepSQL_S);
			prepStmt_S_id_pass = connection.prepareStatement(strPrepSQL_S_id_pass);
			prepStmt_S_pass = connection.prepareStatement(strPrepSQL_S_pass);
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
			if (resultSet.next()) {
				count = resultSet.getInt("count");
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

	public boolean findByPassword(String password) {
		int count = 0;
		try {
			prepStmt_S_pass.setString(1, password);
			resultSet = prepStmt_S_pass.executeQuery();
			if (resultSet.next()) {
				count = resultSet.getInt("count");
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
