package model;

import tool.Constant;
import tool.ErrorCheck;
import tool.FileHandle;

public class ClubInfoManager {
	private ClubDAO clubDAO;
	private ClubInfoDAO clubInfoDAO;
	private ErrorCheck errorCheck;
	private FileHandle fileHandle;

	public ClubInfoManager() {
		clubDAO = new ClubDAO();
		clubInfoDAO = new ClubInfoDAO();
		errorCheck = ErrorCheck.getInstance();
		fileHandle = FileHandle.getInstance();
	}

	public String[] getClubInfo(String id) {
		ClubInfo clubInfo = clubInfoDAO.getClubInfo(id);
		String[] clubInfoArray = new String[Constant.NUM_OF_CLUB_INFO_FIELD];
		clubInfoArray[Constant.ID] = clubInfo.getId();
		clubInfoArray[Constant.LINK] = clubInfo.getLink();
		clubInfoArray[Constant.INTRO] = clubInfo.getIntro();
		clubInfoArray[Constant.MEMBER] = null;
		if (clubInfo.getMember() > 0) {
			clubInfoArray[Constant.MEMBER] = String.valueOf(clubInfo.getMember());
		}
		clubInfoArray[Constant.ICON] = clubInfo.getIcon();
		clubInfoArray[Constant.HOME] = clubInfo.getHome();
		return clubInfoArray;
	}

	public boolean update(String clubId, String link, String intro, String member) {
		// メンバーが整数かチェック
		int memberInt = 0;
		if (!member.equals("")) { // 空欄じゃなければ
			try {
				memberInt = Integer.parseInt(member);
			} catch (NumberFormatException e) {
				return false;
			}
			if (memberInt < 0) {
				return false;
			}
		}

		if (!intro.equals("")) { // 空欄じゃなければ
			// 特殊な文字が含まれているかチェック
			if (errorCheck.exCharCheck(intro)) {
				return false;
			}
		}

		if (!link.equals("")) { // 空欄じゃなければ
			// URLかどうかチェック
			if (errorCheck.notAsciiCheck(link) || errorCheck.notStartHTTPCheck(link)) {
				return false;
			}
		}

		Club club = clubDAO.getClub(clubId);
		String id = club.getClubInfoId();

		clubInfoDAO.update(id, link, intro, memberInt);
		return true;
	}

	public void updateImages(String clubId, String icon, String home) {
		Club club = clubDAO.getClub(clubId);
		String id = club.getClubInfoId();
		ClubInfo clubInfo = clubInfoDAO.getClubInfo(id);
		// 前の画像削除処理
		fileHandle.deleteFile(clubInfo.getIcon());
		fileHandle.deleteFile(clubInfo.getHome());

		clubInfoDAO.updateImages(id, icon, home);
	}

}
