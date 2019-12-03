package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClubDAO {
	String driverClassName = "org.postgresql.Driver"; // ここからいつもの
	String url = "jdbc:postgresql://localhost/test";
	String user = "postgres";
	String password = "akabane";	//
	Connection connection;
	ResultSet resultSet;

	PreparedStatement prepStmt_I; // INSERT用
	PreparedStatement prepStmt_U; // UPDATE用
	PreparedStatement prepStmt_D; // DELETE用
	PreparedStatement prepStmt_S; // SELECT用(id,passwprd)
	PreparedStatement prepStmt_S_id;	// SELECT用(ID)
	PreparedStatement prepStmt_S_mail;	// SELECT用(mail)

	String db_name ="assitjoiningclub";
	String strPrepSQL_S = "SELECT * FROM "+ db_name +" WHERE id=? AND password ";
	String strPrepSQL_I = "INSERT INTO "+ db_name +" VALUES(?, ?, ?, ?, ?)";
	String strPrepSQL_U = "UPDATE "+db_name+" SET password=?, name=?, mail=?, recogn=? WHERE id=?";
	String strPrepSQL_D = "DELETE FROM postalcode WHERE number=?";

    protected boolean find(String id,String password){		//	検索

    }

    protected void insert(String id,String password,String name,String mail,String recogn){	//	追加

    }

    protected void update(String id,String password,String name,String mail,String recogn){	//	更新

    }

    protected void delete(String id){	//	削除

    }

    protected boolean findById(String id){		//	検索

    }

    protected boolean findByKeyword(String mail){		//	検索

    }

    protected Club getClub(String[] keyword){		//	検索？

    }

    protected  Club[] getAllClubs(){	//	検索？

    }
}
