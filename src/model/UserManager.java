package model;

import tool.Constant;

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

	public int register(String hashId, String name, String hashPassword, String mail) {
		return Constant.SUCCESS;
	}

	public void registerConfirm(String hashId, String name, String hashPassword, String mail) {
	}

	public int update(String hashId, String name, String hashPassword, String mail) {
		return Constant.SUCCESS;
	}

	public void updateConfirm(String hashId, String name, String hashPassword, String mail) {
	}

	public boolean delete(String id, String password) {
		return true;
	}

	public String[][] getAllUsers(int parseInt) {
		return new String[][] {
				{ "0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef",
						"ほげ",
						"password",
						"17fi999@ms.dendai.ac.jp" },
				{ "0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef",
						"ほげ",
						"password",
						"17fi999@ms.dendai.ac.jp" }
		};
	}

}
