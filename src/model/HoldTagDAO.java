package last;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HoldTagDAO {
	//子この辺は話し合いで変更する必要あり
	final private static String dbname = "holdtag";   // Postgre SQL DB name
	final private static String user = "postgres";     // Postgre SQL user name
	final private static String password = "password"; // Postgre SQL password
	final private static String sqlHostname = "pgs_7087";
//	final private static String sqlHostname = "localhost";
	final private static String url = "jdbc:postgresql://" + sqlHostname + "/" + dbname;
	final private static String driverClassName = "org.postgresql.Driver";
	
	HoldTagDAO(){
		
	}
	
	protected void delete(String clubId) {
		java.sql.Connection connection;
		String sql1 = "DELETE FROM　tag WHERE id = ?";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql1);
			
			pstmt.setString(1, clubId);

			ResultSet resultSet = pstmt.executeQuery();

			resultSet.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void insert(String clubId,String tagId) {
		java.sql.Connection connection;
		String sql1 = "INSERT INTO tag VALUES(?,?)";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql1);
			pstmt.setString(1, clubId);
			pstmt.setString(2, tagId);

			ResultSet resultSet = pstmt.executeQuery();

			resultSet.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	protected HoldTag[] getByClubID(String clubId) {
		return null;
	}
	
	protected HoldTag[] getByTagId(String tagId) {
		return null;
	}
	protected void deleteByTagId(String id) {
		
	}

}
