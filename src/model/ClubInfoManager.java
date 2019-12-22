package model;

import tool.Constant;

public class ClubInfoManager {
	private ClubInfoDAO clubInfoDAO;

	public ClubInfoManager() {
		clubInfoDAO = new ClubInfoDAO();
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

	public boolean update(String clubId, String link, String intro, String member, String icon, String home) {
		return true;
	}

}
