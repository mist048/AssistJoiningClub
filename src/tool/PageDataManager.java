package tool;

import javax.servlet.http.HttpServletRequest;

import model.ClubInfoManager;
import model.ClubManager;
import model.UserManager;

public class PageDataManager {
	private static PageDataManager pageDataManager = new PageDataManager();
	private UserManager userManager;;
	private ClubManager clubManager;
	private ClubInfoManager clubInfoManager;

	private PageDataManager() {
		userManager = new UserManager();
		clubManager = new ClubManager();
		clubInfoManager = new ClubInfoManager();
	}

	public static PageDataManager getInstance() {
		return pageDataManager;
	}

	// トップ画面へのデータ
	public void toTop(HttpServletRequest request) {
		String firstIndex = request.getParameter("firstIndex");
		if (firstIndex == null) { // 最初のアクセスなら
			firstIndex = "0";
		}
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

	// 一般ユーザマイページ画面へのデータ
	public void toUserMyPage(HttpServletRequest request, String generalId) {
		String[] general = userManager.getUser(generalId);
		request.setAttribute("name", general[Constant.NAME]);
		request.setAttribute("mail", general[Constant.MAIL]);
	}

	// サークルアカウントマイページ画面へのデータ
	public void toClubMyPage(HttpServletRequest request, String clubId) {
		String[] club = clubManager.getClub(clubId);
		String[] clubInfo = clubInfoManager.getClubInfo(club[Constant.CLUB_INFO_ID]);
		request.setAttribute("name", club[Constant.NAME]);
		request.setAttribute("mail", club[Constant.MAIL]);
		request.setAttribute("recogn", club[Constant.RECOGN]);
		request.setAttribute("intro", clubInfo[Constant.INTRO]);
	}

	// サークル情報閲覧画面へのデータ
	public void toClubInfoDisplay(HttpServletRequest request) {
		String clubId = request.getParameter("clubId");
		String[] club = clubManager.getClub(clubId);
		String[] clubInfo = clubInfoManager.getClubInfo(club[Constant.CLUB_INFO_ID]);
		request.setAttribute("name", club[Constant.NAME]);
		request.setAttribute("mail", club[Constant.MAIL]);
		request.setAttribute("recogn", club[Constant.RECOGN]);
		request.setAttribute("link", clubInfo[Constant.LINK]);
		request.setAttribute("intro", clubInfo[Constant.INTRO]);
		request.setAttribute("member", clubInfo[Constant.MEMBER]);
		request.setAttribute("icon", clubInfo[Constant.ICON]);
		request.setAttribute("home", clubInfo[Constant.HOME]);
	}

	// 検索結果表示画面へのデータ
	public void toSearchResultDisplay(HttpServletRequest request) {
		String type = request.getParameter("type");
		String[][] result;
		if (type.equals("keyword")) { // キーワード検索
			String keyword = request.getParameter("keyword");
			result = clubManager.searchByKeyword(keyword); // 検索されたサークルID、サークル名を取得

		} else { // タグ検索
			String tag = request.getParameter("tag");
			result = clubManager.searchByTag(tag); // 検索されたサークルID、サークル名を取得

		}
		request.setAttribute("result", result);
	}
}
