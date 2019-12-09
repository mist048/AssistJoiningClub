package model;

import tool.Constant;
import tool.ErrorCheck;

public class ClubManager {
	private ClubDAO clubDAO;
	private ErrorCheck errorCheck;

	public ClubManager() {
		clubDAO = new ClubDAO();
		errorCheck = ErrorCheck.getInstance();
	}

	public boolean login(String id, String password) {
		if (clubDAO.find(id, password)) {
			return true;
		}
		return false;
	}

	public String[] getClub(String id) {
		Club club = new Club();
		club = clubDAO.getClub(id);
		String[] clubInfo = new String[Constant.NUM_OF_CLUB_INFO];
		clubInfo[Constant.ID] = club.getId();
		clubInfo[Constant.NAME] = club.getName();
		clubInfo[Constant.PASSWORD] = club.getPassword();
		clubInfo[Constant.MAIL] = club.getMail();
		clubInfo[Constant.RECOGN] = club.getRecogn();
		clubInfo[Constant.CLUB_INFO_ID] = club.getClubInfoId();
		//return clubInfo; // 本番用
		return new String[] { "0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef",
				"ホゲサークル",
				"password",
				"17fi999@ms.dendai.ac.jp",
				"公認",
				"0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef" };
	}

	public int register(int club, String id, String name, String password, String mail) {
		return Constant.SUCCESS;
	}

	public void registerConfirm(int viewer, String hashId, String name, String hashPassword, String mail) {
	}

	public int update(String hashId, String name, String hashPassword, String mail) {
		return Constant.SUCCESS;
	}

	public void updateConfirm(int admin, String hashId, String name, String hashPassword, String mail, String recogn) {
	}

	public boolean delete(String hashId, String hashPassword) {
		return true;
	}

	public String[][] searchByKeyword(String keyword) {
		return new String[][] { { "id1", "name1" }, { "id2", "name2" } };
	}

	public String[][] searchByTag(String tag) {
		return new String[][] { { "id1", "name1" }, { "id2", "name2" } };
	}

	public String[][] getAllClubs(int firstIndex) {
		return new String[][] { { "0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef",
				"ホゲサークル",
				"password",
				"17fi999@ms.dendai.ac.jp",
				"公認",
				"0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef" },
				{ "0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef",
						"ホゲサークル",
						"password",
						"17fi999@ms.dendai.ac.jp",
						"公認",
						"0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef" } };
	}
}
