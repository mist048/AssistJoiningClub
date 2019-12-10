package model;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TagDAO {
	//�q���̕ӂ͘b�������ŕύX����K�v����
	final private static String dbname = "tag";   // Postgre SQL DB name
	final private static String user = "postgres";     // Postgre SQL user name
	final private static String password = "password"; // Postgre SQL password
	final private static String sqlHostname = "pgs_7087";
//	final private static String sqlHostname = "localhost";
	final private static String url = "jdbc:postgresql://" + sqlHostname + "/" + dbname;
	final private static String driverClassName = "org.postgresql.Driver";

	protected void insert(String name) {
		java.sql.Connection connection;
		String sql0 = "COUNT(*) From tag";
		String sql1 = "INSERT INTO tag VALUES(?,?)";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql1);
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql0);
			String id=rs.toString();
			int n=Integer.valueOf(id);
			n++;
			id = "tag" + Integer.toString(n);

			pstmt.setString(1, id);
			pstmt.setString(2, name);

			ResultSet resultSet = pstmt.executeQuery();

			resultSet.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void update(String id,String name) {

		java.sql.Connection connection;
		String sql1 = "UPDATE tag id = ?, name = ?";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql1);

			pstmt.setString(1, id);
			pstmt.setString(2, name);

			ResultSet resultSet = pstmt.executeQuery();

			resultSet.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void delete(String id) {
		java.sql.Connection connection;
		String sql1 = "DELETE FROM�@tag WHERE id = ?";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql1);

			pstmt.setString(1, id);

			ResultSet resultSet = pstmt.executeQuery();

			resultSet.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected boolean findByName(String name) {
		java.sql.Connection connection;
		String sql1 = "SELECT id FROM�@tag WHERE name = ?";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql1);

			pstmt.setString(1, name);

			ResultSet resultSet = pstmt.executeQuery();


	        ArrayList<String> list = new ArrayList<String>();



	        while (resultSet.next()) {

	            list.add(resultSet.getString("name"));

	        }

			resultSet.close();
			connection.close();
			if(list != null) {
	        	return true;
	        }

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	protected Tag[] getAllTags() {
		java.sql.Connection connection;
		String sql1 = "SELECT * FROM�@tag WHERE * ";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql1);

			ResultSet resultSet = pstmt.executeQuery();


	        ArrayList<Tag> list = new ArrayList<Tag>();

	        while (resultSet.next()) {
	        	Tag tag0 = new Tag();
	        	tag0.setId(resultSet.getString("id"));
	        	tag0.setId(resultSet.getString("name"));
	            list.add(tag0);
	        }
	        int size = list.size();
	        Tag[] tags = new Tag[size];
	        int i = 0;
	        for(Tag tagInList:list) {
	        	tags[i] = tagInList;
	        	i++;
	        }

			resultSet.close();
			connection.close();
			return tags;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	protected String[] getAllIds() {

		java.sql.Connection connection;
		String sql1 = "SELECT * FROM�@tag WHERE * ";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql1);

			ResultSet resultSet = pstmt.executeQuery();


	        ArrayList<String> list = new ArrayList<String>();

	        while (resultSet.next()) {
	            list.add(resultSet.getString("id"));
	        }
	        int size = list.size();
	        String[] allId = new String[size];
	        int i = 0;
	        for(String idInList:list) {
	        	allId[i] = idInList;
	        	i++;
	        }

			resultSet.close();
			connection.close();
			return allId;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	protected Tag getTag(String id) {

		java.sql.Connection connection;
		String sql1 = "SELECT * FROM�@tag WHERE id = ? ";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql1);
			pstmt.setString(1, id);

			ResultSet resultSet = pstmt.executeQuery();


	        ArrayList<Tag> list = new ArrayList<Tag>();

	        while (resultSet.next()) {
	        	Tag tag0 = new Tag();
	        	tag0.setId(resultSet.getString("id"));
	        	tag0.setId(resultSet.getString("name"));
	            list.add(tag0);
	        }
	        int size = list.size();
	        Tag[] tags = new Tag[size];
	        int i = 0;
	        for(Tag tagInList:list) {
	        	tags[i] = tagInList;
	        	i++;
	        }
			resultSet.close();
			connection.close();
			return tags[0];
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

}
