package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import tool.Constant;

public class AdminDAO {
	String driverClassName = "org.postgresql.Driver"; // ここからいつもの
	String url = "jdbc:postgresql://localhost/assistjoiningclub"; // local
	String user = "postgres";
	String password = Constant.POSTGRES_PASSWORD;
	Connection connection;
	ResultSet resultSet;

	PreparedStatement prepStmt_S;

	String strPrepSQL_S = "SELECT * FROM admin";

	protected AdminDAO() {
		try { // ドライバマネージャとコネクション
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);

			prepStmt_S = connection.prepareStatement(strPrepSQL_S);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getMail() {
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
