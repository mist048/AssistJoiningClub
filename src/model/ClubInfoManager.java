package model;

public class ClubInfoManager {

	public String[] getClubInfo(String string) {
		return new String[] { "0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef",
				"https://github.com/mist048/AssistJoiningClub",
				"ほげほげ",
				"20",
				"/img/icon.jpg",
				"/img/hoge.jpg" };
	}

	public boolean update(String clubId, String link, String intro, String member, String icon, String home) {
		return true;
	}

}
