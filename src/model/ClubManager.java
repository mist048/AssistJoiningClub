package model;

import tool.Constant;

public class ClubManager {

	public boolean login(String hashId, String hashPassword) {
		return true;
	}

	public String[] getClub(String hashId) {
		return new String[] { "0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef",
				"ホゲサークル",
				"password",
				"17fi999@ms.dendai.ac.jp",
				"公認",
				"0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef"};
	}

	public int register(int club, String id, String name, String password, String mail) {
		return Constant.SUCCESS;
	}

}
