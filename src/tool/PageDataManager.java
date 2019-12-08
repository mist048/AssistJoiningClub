package tool;

import javax.servlet.http.HttpServletRequest;

import model.ClubInfoManager;
import model.ClubManager;
import model.FavoriteManager;
import model.UserManager;

public class PageDataManager {
	private static PageDataManager pageDataManager = new PageDataManager();
	private UserManager userManager;;
	private ClubManager clubManager;
	private ClubInfoManager clubInfoManager;
	private FavoriteManager favoriteManager;

	private PageDataManager() {
		userManager = new UserManager();
		clubManager = new ClubManager();
		clubInfoManager = new ClubInfoManager();
		favoriteManager = new FavoriteManager();
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

	// 一般ユーザ更新画面へのデータ
	public void toUserUpdate(HttpServletRequest request, String generalId) {
		String[] general = userManager.getUser(generalId);
		request.setAttribute("password", general[Constant.PASSWORD]);
		request.setAttribute("name", general[Constant.NAME]);
		request.setAttribute("mail", general[Constant.MAIL]);
	}

	// 一般ユーザマイページ画面へのデータ
	public void toUserMyPage(HttpServletRequest request, String generalId) {
		String[] general = userManager.getUser(generalId);
		request.setAttribute("name", general[Constant.NAME]);
		request.setAttribute("mail", general[Constant.MAIL]);
	}

	// お気に入りサークル一覧表示画面へのデータ
	public void toFavoriteClubDisplay(HttpServletRequest request, String generalId) {
		String[][] favoriteClubs = favoriteManager.getFavorite(generalId);
		request.setAttribute("favoriteClubs", favoriteClubs);
	}

	// サークルアカウント更新画面へのデータ
	public void toClubUpdate(HttpServletRequest request, String clubId, int error) {
		String[] club = clubManager.getClub(clubId);
		request.setAttribute("id", club[Constant.ID]);
		request.setAttribute("password", club[Constant.PASSWORD]);
		request.setAttribute("name", club[Constant.NAME]);
		request.setAttribute("mail", club[Constant.MAIL]);
		request.setAttribute("recogn", club[Constant.RECOGN]);
		if (error != -1) { // エラーがあれば
			request.setAttribute("error", error);
		}
	}

	// サークルアカウント更新判定
	public int clubUpdate(HttpServletRequest request, String clubId) {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String mail = request.getParameter("mail");
		// パスワードをハッシュ値に変換する
		String hashPassword = SHA256.hash(password);
		int code = clubManager.update(clubId, name, hashPassword, mail); // 更新判定
		return code;
	}

	// サークルアカウント更新処理
	public void clubUpdateConfirm(HttpServletRequest request, String user, String clubId) {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String mail = request.getParameter("mail");
		String recogn = request.getParameter("recogn");
		// パスワードをハッシュ値に変換する
		String hashPassword = SHA256.hash(password);
		clubManager.updateConfirm(Constant.CLUB, clubId, name, hashPassword, mail, recogn); // 更新処理
	}

	// サークル情報更新画面へのデータ
	public void toClubInfoUpdate(HttpServletRequest request, String clubId) {
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

	// サークルアカウントマイページ画面へのデータ
	public void toClubMyPage(HttpServletRequest request, String clubId) {
		String[] club = clubManager.getClub(clubId);
		String[] clubInfo = clubInfoManager.getClubInfo(club[Constant.CLUB_INFO_ID]);
		request.setAttribute("name", club[Constant.NAME]);
		request.setAttribute("mail", club[Constant.MAIL]);
		request.setAttribute("recogn", club[Constant.RECOGN]);
		request.setAttribute("intro", clubInfo[Constant.INTRO]);
	}

	// サークルアカウント削除画面へ
	public void toAccountDelete(HttpServletRequest request, String user, String deletedUser, String userId) {
		String[] userInfo;
		switch (user) {
		case "general": // 一般ユーザ
			userInfo = userManager.getUser(userId);
			request.setAttribute("name", userInfo[Constant.NAME]);
			break;

		case "club": // サークルアカウント
			userInfo = clubManager.getClub(userId);
			request.setAttribute("name", userInfo[Constant.NAME]);
			break;

		case "admin": // 管理者
			if (deletedUser.equals("general")) { // 一般ユーザが対象
				userInfo = userManager.getUser(userId);
				request.setAttribute("generalId", userInfo[Constant.ID]);
				request.setAttribute("name", userInfo[Constant.NAME]);
			} else if (deletedUser.equals("club")) { // サークルアカウントが対象
				userInfo = clubManager.getClub(userId);
				request.setAttribute("clubId", userInfo[Constant.ID]);
				request.setAttribute("name", userInfo[Constant.NAME]);
			}
			break;
		}
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

	// サークルアカウント管理者閲覧用画面へのデータ
	public void toClubInfoDisplayForAdmin(HttpServletRequest request, String clubId) {
		String[] club = clubManager.getClub(clubId);
		String[] clubInfo = clubInfoManager.getClubInfo(club[Constant.CLUB_INFO_ID]);
		request.setAttribute("id", club[Constant.ID]);
		request.setAttribute("name", club[Constant.NAME]);
		request.setAttribute("mail", club[Constant.MAIL]);
		request.setAttribute("recogn", club[Constant.RECOGN]);
		request.setAttribute("intro", clubInfo[Constant.INTRO]);
	}
}
