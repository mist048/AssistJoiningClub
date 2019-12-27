package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import tool.Constant;

public class TagDAO {
	private final String driverClassName = "org.postgresql.Driver"; // ここからいつもの
	private final String url = "jdbc:postgresql://localhost/assistjoiningclub"; // local
	private final String user = "postgres";
	private final String password = Constant.POSTGRES_PASSWORD;
	private Connection connection;
	private ResultSet resultSet;

	private PreparedStatement prepStmt_I; // INSERT用
	private PreparedStatement prepStmt_U; // UPDATE用
	private PreparedStatement prepStmt_D; // DELETE用
	private PreparedStatement prepStmt_S; // SELECT用
	private PreparedStatement prepStmt_S_id; // SELECT用(id)
	private PreparedStatement prepStmt_S_name; // SELECT用(name)
	private PreparedStatement prepStmt_S_keyword; // SELECT用(keyword)
	private PreparedStatement prepStmt_S_count; // SELECT用(全部カウント)

	private String strPrepSQL_I = "INSERT INTO tag VALUES(?, ?)";
	private String strPrepSQL_U = "UPDATE tag SET name=? WHERE id=?";
	private String strPrepSQL_D = "DELETE FROM tag WHERE id=?";
	private String strPrepSQL_S = "SELECT * FROM tag LIMIT " + Constant.MAX_OF_DISPLAYS + " OFFSET ?";
	private String strPrepSQL_S_id = "SELECT * FROM tag WHERE id=?";
	private String strPrepSQL_S_name = "SELECT * FROM tag WHERE name=?";
	private String strPrepSQL_S_keyword = "SELECT * FROM tag WHERE name LIKE ?";
	private String strPrepSQL_S_count = "SELECT COUNT(*) AS cnt FROM tag";

	protected TagDAO() {
		try { // ドライバマネージャとコネクション
			Class.forName(driverClassName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void insert(String id, String name) {
		try {
			connection = DriverManager.getConnection(url, user, password);
			prepStmt_I = connection.prepareStatement(strPrepSQL_I);

			prepStmt_I.setString(1, id);
			prepStmt_I.setString(2, name);
			prepStmt_I.executeUpdate();

			resultSet.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void update(String id, String name) {
		try {
			connection = DriverManager.getConnection(url, user, password);
			prepStmt_U = connection.prepareStatement(strPrepSQL_U);

			prepStmt_U.setString(2, id);
			prepStmt_U.setString(1, name);

			prepStmt_U.executeUpdate();

			resultSet.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void delete(String id) {
		try {
			connection = DriverManager.getConnection(url, user, password);
			prepStmt_D = connection.prepareStatement(strPrepSQL_D);

			prepStmt_D.setString(1, id);

			prepStmt_D.executeUpdate();

			resultSet.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected boolean findById(String id) {
		int count = 0;
		try {
			connection = DriverManager.getConnection(url, user, password);
			prepStmt_S_id = connection.prepareStatement(strPrepSQL_S_id);

			prepStmt_S_id.setString(1, id);

			resultSet = prepStmt_S_id.executeQuery();
			while (resultSet.next()) {
				count++;
			}

			resultSet.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	protected boolean findByName(String name) {
		int count = 0;
		try {
			connection = DriverManager.getConnection(url, user, password);
			prepStmt_S_name = connection.prepareStatement(strPrepSQL_S_name);

			prepStmt_S_name.setString(1, name);

			resultSet = prepStmt_S_name.executeQuery();
			while (resultSet.next()) {
				count++;
			}

			resultSet.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	protected Tag[] getAllTags(int firstIndex) {
		ArrayList<Tag> list = new ArrayList<Tag>();
		try {
			connection = DriverManager.getConnection(url, user, password);
			prepStmt_S = connection.prepareStatement(strPrepSQL_S);

			prepStmt_S.setInt(1, firstIndex);

			resultSet = prepStmt_S.executeQuery();
			while (resultSet.next()) {
				Tag tag = new Tag();
				tag.setId(resultSet.getString("id"));
				tag.setName(resultSet.getString("name"));
				list.add(tag);
			}

			resultSet.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list.toArray(new Tag[list.size()]);
	}

	protected Tag getTag(String id) {
		Tag tag = new Tag();
		try {
			connection = DriverManager.getConnection(url, user, password);
			prepStmt_S_id = connection.prepareStatement(strPrepSQL_S_id);
			prepStmt_S_id.setString(1, id);

			resultSet = prepStmt_S_id.executeQuery();
			while (resultSet.next()) {
				tag.setId(resultSet.getString("id"));
				tag.setName(resultSet.getString("name"));
			}
			resultSet.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tag;

	}

	protected Tag getByName(String name) {
		Tag tag = new Tag();
		try {
			connection = DriverManager.getConnection(url, user, password);
			prepStmt_S_name = connection.prepareStatement(strPrepSQL_S_name);

			prepStmt_S_name.setString(1, name);

			resultSet = prepStmt_S_name.executeQuery();
			while (resultSet.next()) {
				tag.setId(resultSet.getString("id"));
				tag.setName(resultSet.getString("name"));
			}

			resultSet.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tag;
	}

	protected int getNumOfTags() {
		int count = 0;
		try {
			connection = DriverManager.getConnection(url, user, password);
			prepStmt_S_count = connection.prepareStatement(strPrepSQL_S_count);

			resultSet = prepStmt_S_count.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt("cnt");
			}
			resultSet.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public Tag[] findByKeyword(String[] keywords) {
		ArrayList<Tag> hited = new ArrayList<Tag>();
		try {
			connection = DriverManager.getConnection(url, user, password);
			prepStmt_S_keyword = connection.prepareStatement(strPrepSQL_S_keyword);

			for (int i = 0; i < keywords.length; i++) {
				prepStmt_S_keyword.setString(1, "%" + keywords[i] + "%");
				resultSet = prepStmt_S_keyword.executeQuery();
				while (resultSet.next()) {
					Tag tag = new Tag();
					tag.setId(resultSet.getString("id"));
					tag.setName(resultSet.getString("name"));
					boolean isDuplicate = false; // 重複フラグ
					for (Tag hitedTag : hited) {
						if (hitedTag.getId().equals(tag.getId())) {
							isDuplicate = true;
						}
					}
					if (!isDuplicate) {
						hited.add(tag);
					}
				}
				resultSet.close();
			}

			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hited.toArray(new Tag[hited.size()]);
	}

}
