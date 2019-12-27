package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import tool.Constant;

public class HoldTagDAO {
	private final String driverClassName = "org.postgresql.Driver"; // ここからいつもの
	private final String url = "jdbc:postgresql://localhost/assistjoiningclub"; // local
	private final String user = "postgres";
	private final String password = Constant.POSTGRES_PASSWORD;
	private Connection connection;
	private ResultSet resultSet;

	private PreparedStatement prepStmt_I; // INSERT用
	private PreparedStatement prepStmt_U; // UPDATE用
	private PreparedStatement prepStmt_D; // DELETE用
	private PreparedStatement prepStmt_D_tagid; // DELETE用(tagid)
	private PreparedStatement prepStmt_S_clubid; // SELECT用(clubid)
	private PreparedStatement prepStmt_S_tagid; // SELECT用(tagid)
	private PreparedStatement prepStmt_S_name; // SELECT用(name)
	private PreparedStatement prepStmt_S_count; // SELECT用(全部カウント)

	private String strPrepSQL_I = "INSERT INTO holdtag VALUES(?, ?)";
	private String strPrepSQL_U = "UPDATE tag SET name=? WHERE id=?";
	private String strPrepSQL_D = "DELETE FROM holdtag WHERE clubid=?";
	private String strPrepSQL_D_tagid = "DELETE FROM holdtag WHERE tagid=?";
	private String strPrepSQL_S_clubid = "SELECT * FROM holdtag WHERE clubid=?";
	private String strPrepSQL_S_tagid = "SELECT * FROM holdtag WHERE tagid=?";
	private String strPrepSQL_S_name = "SELECT * FROM tag WHERE name=?";
	private String strPrepSQL_S_count = "SELECT COUNT(*) AS cnt FROM tag";

	protected HoldTagDAO() {
		try { // ドライバマネージャとコネクション
			Class.forName(driverClassName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void delete(String clubId) {
		try {
			connection = DriverManager.getConnection(url, user, password);
			prepStmt_D = connection.prepareStatement(strPrepSQL_D);

			prepStmt_D.setString(1, clubId);

			prepStmt_D.executeUpdate();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void insert(String clubId, String tagId) {
		try {
			connection = DriverManager.getConnection(url, user, password);
			prepStmt_I = connection.prepareStatement(strPrepSQL_I);

			prepStmt_I.setString(1, clubId);
			prepStmt_I.setString(2, tagId);

			prepStmt_I.executeUpdate();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected HoldTag[] getByClubID(String clubId) {
		ArrayList<HoldTag> holdTags = new ArrayList<HoldTag>();
		try {
			connection = DriverManager.getConnection(url, user, password);
			prepStmt_S_clubid = connection.prepareStatement(strPrepSQL_S_clubid);

			prepStmt_S_clubid.setString(1, clubId);

			resultSet = prepStmt_S_clubid.executeQuery();
			while (resultSet.next()) {
				HoldTag holdTag = new HoldTag();
				holdTag.setClubId(resultSet.getString("clubid"));
				holdTag.setTagId(resultSet.getString("tagid"));
				holdTags.add(holdTag);
			}

			resultSet.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return holdTags.toArray(new HoldTag[holdTags.size()]);
	}

	protected HoldTag[] getByTagId(String tagId) {
		ArrayList<HoldTag> holdTags = new ArrayList<HoldTag>();
		try {
			connection = DriverManager.getConnection(url, user, password);
			prepStmt_S_tagid = connection.prepareStatement(strPrepSQL_S_tagid);

			prepStmt_S_tagid.setString(1, tagId);

			resultSet = prepStmt_S_tagid.executeQuery();
			while (resultSet.next()) {
				HoldTag holdTag = new HoldTag();
				holdTag.setClubId(resultSet.getString("clubid"));
				holdTag.setTagId(resultSet.getString("tagid"));
				holdTags.add(holdTag);
			}

			resultSet.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return holdTags.toArray(new HoldTag[holdTags.size()]);
	}

	protected void deleteByTagId(String tagId) {
		try {
			connection = DriverManager.getConnection(url, user, password);
			prepStmt_D_tagid = connection.prepareStatement(strPrepSQL_D_tagid);

			prepStmt_D_tagid.setString(1, tagId);

			prepStmt_D_tagid.executeUpdate();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
