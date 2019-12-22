package model;

import tool.Constant;
import tool.ErrorCheck;
import tool.SHA256;

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
		String[] clubInfo = new String[Constant.NUM_OF_CLUB_INFO_FIELD];
		clubInfo[Constant.ID] = club.getId();
		clubInfo[Constant.NAME] = club.getName();
		clubInfo[Constant.PASSWORD] = club.getPassword();
		clubInfo[Constant.MAIL] = club.getMail();
		clubInfo[Constant.RECOGN] = club.getRecogn();
		clubInfo[Constant.CLUB_INFO_ID] = club.getClubInfoId();
		return clubInfo;
	}

	public int register(String id, String name, String password, String mail) {
		String[] club = new String[Constant.NUM_OF_USER_FIELD];
		club[Constant.ID] = id;
		club[Constant.NAME] = name;
		club[Constant.PASSWORD] = password;
		club[Constant.MAIL] = mail;
		if (clubDAO.findById(id) || clubDAO.findByMail(mail)) { // IDが重複している
			return Constant.DUPLICATE;
		}
		for (int i = 0; i < club.length; i++) {
			if (errorCheck.blankCheck(club[i])) { // 空欄を含んでいる
				return Constant.CONTAINS_BLANK;
			}
			if (i != Constant.NAME) {
				if (errorCheck.notAsciiCheck(club[i])) { // ASCII文字以上を含んでいる
					return Constant.CONTAINS_EX_CHAR;
				}
			}
			if (errorCheck.exCharCheck(club[i])) { // 特殊な文字を含んでいる
				return Constant.CONTAINS_EX_CHAR;
			}
		}
		return Constant.SUCCESS;
	}

	public void registerConfirm(String id, String name, String password, String mail) {
		// サークル情報IDの自動生成
		int number = 0;
		String clubinfoid = null;
		while (true) {
			clubinfoid = SHA256.hash(String.valueOf(number));
			if (!clubDAO.findByClubinfoid(clubinfoid)) { // サークル情報IDが重複していなければ
				break;
			}
			number++;
		}
		clubDAO.insert(id, name, password, mail, "非公認", clubinfoid);
	}

	public int update(String id, String name, String password, String mail) {
		Club club = clubDAO.getClub(id);
		String[] clubInfo = new String[Constant.NUM_OF_USER_FIELD];
		clubInfo[Constant.ID] = club.getId();
		clubInfo[Constant.NAME] = club.getName();
		clubInfo[Constant.PASSWORD] = club.getPassword();
		clubInfo[Constant.MAIL] = club.getMail();
		if (!club.getMail().equals(mail)) { // メールアドレスを変更していれば
			if (clubDAO.findByMail(mail)) { // メールアドレスが重複している
				return Constant.DUPLICATE;
			}
		}
		for (int i = 0; i < clubInfo.length; i++) {
			if (i != Constant.PASSWORD) {
				if (errorCheck.blankCheck(clubInfo[i])) { // 空欄を含んでいる
					return Constant.CONTAINS_BLANK;
				}
			}
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

	public void updateConfirm(int user, String id, String name, String password, String mail, String recogn) {
		switch (user) {
		case Constant.CLUB: // サークルアカウント
			clubDAO.update(id, name, password, mail, "非公認");
			break;

		case Constant.ADMIN: // 管理者
			clubDAO.update(id, name, password, mail, recogn);
			break;
		}
	}

	public boolean delete(String id, String password) {
		Club club = clubDAO.getClub(id);
		if (clubDAO.find(id, password)) {
			clubDAO.delete(id, club.getClubInfoId());
			return true;
		}
		return false;
	}

	public String[][] searchByKeyword(String keyword) {
		return new String[][] { { "id1", "name1" }, { "id2", "name2" } };
	}

	public String[][] searchByTag(String tag) {
		return new String[][] { { "id1", "name1" }, { "id2", "name2" } };
	}

	public String[][] getAllClubs(int firstIndex) {
		Club[] clubs = clubDAO.getAllClubs(firstIndex);
		String[][] allClubs = new String[clubs.length][Constant.NUM_OF_CLUB_FIELD];
		for (int i = 0; i < clubs.length; i++) {
			allClubs[i][Constant.ID] = clubs[i].getId();
			allClubs[i][Constant.NAME] = clubs[i].getName();
			allClubs[i][Constant.PASSWORD] = clubs[i].getPassword();
			allClubs[i][Constant.MAIL] = clubs[i].getMail();
			allClubs[i][Constant.RECOGN] = clubs[i].getRecogn();
			allClubs[i][Constant.CLUB_INFO_ID] = clubs[i].getClubInfoId();
		}
		return allClubs;
	}

	public String getPassword(String id) {
		Club club = clubDAO.getClub(id);
		return club.getPassword();
	}
	
	public int getNumOfPages() {
		int count = 0;
		count = clubDAO.getNumOfClubs();
		int numOfPages = count / Constant.MAX_OF_DISPLAYS;
		if (count % Constant.MAX_OF_DISPLAYS != 0) {
			numOfPages += 1;
		}
		return numOfPages;
	}
}
