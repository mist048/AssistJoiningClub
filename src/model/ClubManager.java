package model;

import tool.Constant;

public class ClubManager {

	public int login(String hashId, String hashPassword) {
		return Constant.SUCCESS;
	}

	public String[] getClub(String hashId) {
		return new String[] { "0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef",
				"ホゲサークル",
				"password",
				"17fi999@ms.dendai.ac.jp",
				"公認",
				"0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef"};
	}

}
