package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import tool.Constant;

public class FavoriteDAO {
	private final String driverClassName = "org.postgresql.Driver";
	private final String url = "jdbc:postgresql://localhost/assistjoiningclub"; // local
	private final String user = "postgres";
	private final String password = Constant.POSTGRES_PASSWORD;
	private Connection connection;
	private ResultSet resultSet;

	private PreparedStatement prepStmt_I; // INSERT用
	private PreparedStatement prepStmt_D; // DELETE用
	private PreparedStatement prepStmt_D_userid; // DELETE用
	private PreparedStatement prepStmt_D_clubid; // DELETE用
	private PreparedStatement prepStmt_S_userid_clubid; // SELECT用(userid,clubid)
	private PreparedStatement prepStmt_S_userid; // SELECT用

	private String strPrepSQL_I = "INSERT INTO club VALUES(?, ?)";
	private String strPrepSQL_D = "DELETE FROM favorite WHERE userid=? AND clubid=?";
	private String strPrepSQL_D_userid = "DELETE FROM favorite WHERE userid=?";
	private String strPrepSQL_D_clubid = "DELETE FROM favorite WHERE clubid=?";
	private String strPrepSQL_S_userid_clubid = "SELECT COUNT(*) FROM favorite WHERE userid=? AND clubid=?";
	private String strPrepSQL_S_userid = "SELECT clubid FROM favorite WHERE userid=?";

	protected FavoriteDAO() {
		try { // ドライバマネージャとコネクション
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			prepStmt_I = connection.prepareStatement(strPrepSQL_I);
			prepStmt_D = connection.prepareStatement(strPrepSQL_D);
			prepStmt_D_userid = connection.prepareStatement(strPrepSQL_D_userid);
			prepStmt_D_clubid = connection.prepareStatement(strPrepSQL_D_clubid);
			prepStmt_S_userid_clubid = connection.prepareStatement(strPrepSQL_S_userid_clubid);
			prepStmt_S_userid = connection.prepareStatement(strPrepSQL_S_userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected boolean find(String userId,String clubId) {
		try {
			prepStmt_S_userid_clubid.setString(1, userId);
			prepStmt_S_userid_clubid.setString(2, clubId);
			resultSet = prepStmt_S_userid_clubid.executeQuery();
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

	protected void delete(String userId,String clubId) {
		try {
			prepStmt_D.setString(1, userId);
			prepStmt_D.setString(2, clubId);
			prepStmt_D.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void insert(String userId,String clubId) {
		try {
			prepStmt_I.setString(1, userId);
			prepStmt_I.setString(2, clubId);
			prepStmt_I.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected Favorite[] getByUserId(String userId) {
		ArrayList<Favorite> favorites = new ArrayList<Favorite>();
		try {
			prepStmt_S_userid.setString(1, userId);
			resultSet = prepStmt_S_userid.executeQuery();
			while (resultSet.next()) {
				Favorite favorite = new Favorite();
				favorite.setUserId(userId);
				favorite.setClubId(resultSet.getString("clubid"));
				favorites.add(favorite);
			}
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return favorites.toArray(new Favorite[favorites.size()]);
	}

	protected void deleteByUserId(String userId) {
		try {
			prepStmt_D_userid.setString(1, userId);
			prepStmt_D_userid.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void deleteByClubId(String clubId) {
		try {
			prepStmt_D_clubid.setString(1, clubId);
			prepStmt_D_clubid.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
