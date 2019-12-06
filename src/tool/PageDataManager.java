package tool;

import javax.servlet.http.HttpServletRequest;

import model.ClubInfoManager;
import model.ClubManager;

public class PageDataManager {
	private static PageDataManager pageDataManager = new PageDataManager();
	private ClubManager clubManager;
	private ClubInfoManager clubInfoManager;

	private PageDataManager() {
		clubManager = new ClubManager();
		clubInfoManager = new ClubInfoManager();
	}
	
	public static PageDataManager getInstance() {
		return pageDataManager;
	}

	public void toViewerTop(HttpServletRequest request,String firstIndex) {
		String[][] allClubs = clubManager.getAllClubs(Integer.parseInt(firstIndex)); // サークルアカウント情報をfirstIndexから10件取得
		String[][] allClubInfo = new String[allClubs.length][3]; // 閲覧用サークル情報
		for (int i = 0; i < allClubs.length; i++) {
			allClubInfo[i][Constant.ID] = allClubs[i][Constant.ID];
			allClubInfo[i][Constant.NAME] = allClubs[i][Constant.NAME];
			String[] clubInfo = clubInfoManager.getClubInfo(allClubs[i][Constant.CLUB_INFO_ID]);
			allClubInfo[i][2] = clubInfo[Constant.INTRO];
		}
		request.setAttribute("clubs", allClubInfo);
		request.setAttribute("firstIndex", firstIndex);
	}
}
