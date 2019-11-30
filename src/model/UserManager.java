package model;

public class UserManager {

	public boolean login(String hashId, String hashPassword) {
		return true;
	}

	public String[] getUser(String hashId) {
		return new String[] { "0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef",
				"ほげ",
				"password",
				"17fi999@ms.dendai.ac.jp" };
	}

}
