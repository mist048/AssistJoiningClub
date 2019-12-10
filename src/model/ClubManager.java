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

	public int register(int user, String id, String name, String password, String mail) {
		String[] club = new String[Constant.NUM_OF_CLUB_INFO];
		if (clubDAO.findById(id) || clubDAO.findByMail(mail)) { // IDが重複している
			return Constant.DUPLICATE;
		}
		for (int i = 0; i < club.length; i++) {
			if (i != Constant.NAME) {
				if (errorCheck.notAsciiCheck(club[i])) { // ASCII文字以上を含んでいる
					return Constant.CONTAINS_EX_CHAR;
				}
			}
			if (user != Constant.ADMIN && (i != Constant.ID || i != Constant.MAIL)) {
				if (errorCheck.blankCheck(club[i])) { // 特殊な文字を含んでいる
					return Constant.CONTAINS_EX_CHAR;
				}
			}
		}
		return Constant.SUCCESS;
	}

	public void registerConfirm(int user, String id, String name, String password, String mail) {
		if (user == Constant.VIEWER) {
			clubDAO.insert(id, name, password, mail, "非公認");
		} else if (user == Constant.ADMIN) {
			clubDAO.insert(id, name, password, mail, "公認");
		}
	}

	public int update(String id, String name, String password, String mail) {
		String[] clubInfo = new String[Constant.NUM_OF_CLUB_INFO];
		Club club = clubDAO.getClub(id);
		if (!club.getMail().equals(mail)) { // メールアドレスを変更していれば
			if (clubDAO.findByMail(mail)) { // メールアドレスが重複している
				return Constant.DUPLICATE;
			}
		}
		for (int i = 0; i < clubInfo.length; i++) {
			if (i != Constant.NAME) {
				if (errorCheck.notAsciiCheck(clubInfo[i])) { // ASCII文字以上を含んでいる
					return Constant.CONTAINS_EX_CHAR;
				}
			}
			if (errorCheck.blankCheck(clubInfo[i])) { // 特殊な文字を含んでいる
				return Constant.CONTAINS_EX_CHAR;
			}
		}
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
