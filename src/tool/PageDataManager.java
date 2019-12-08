package tool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.AdminManager;
import model.ClubInfoManager;
import model.ClubManager;
import model.FavoriteManager;
import model.TagManager;
import model.UserManager;

public class PageDataManager {
	private static PageDataManager pageDataManager = new PageDataManager();
	private UserManager userManager;;
	private ClubManager clubManager;
	private ClubInfoManager clubInfoManager;
	private FavoriteManager favoriteManager;
	private AdminManager adminManager;
	private TagManager tagManager;

	private PageDataManager() {
		userManager = new UserManager();
		clubManager = new ClubManager();
		clubInfoManager = new ClubInfoManager();
		favoriteManager = new FavoriteManager();
		adminManager = new AdminManager();
		tagManager = new TagManager();
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

	// ログイン処理
	public boolean login(HttpSession session, HttpServletRequest request, String user, String id, String password) {
		boolean result = false;
		switch (user) {
		case "general": // 一般ユーザ
			result = userManager.login(id, password); // ログイン処理
			if (result) { // ログイン成功
				session.setAttribute("user", user);
				session.setAttribute("userId", id);
				toUserMyPage(request, id);
			}
			break;

		case "club": // サークルアカウント
			result = clubManager.login(id, password); // ログイン処理
			if (result) { // ログイン成功
				session.setAttribute("user", user);
				session.setAttribute("userId", id);
				toClubMyPage(request, id);
			}
			break;

		case "admin": // 管理者
			result = adminManager.login(id, password); // ログイン処理
			if (result) { // ログイン成功
				session.setAttribute("user", user);
				session.setAttribute("userId", id);
				toTop(request);
			}
			break;
		}
		session.setAttribute("login", result);
		return result;
	}

	// 一般ユーザ登録画面へのデータ
	public void toUserRegistration(HttpServletRequest request, int error) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String mail = request.getParameter("mail");
		request.setAttribute("id", id);
		request.setAttribute("password", password);
		request.setAttribute("name", name);
		request.setAttribute("mail", mail);
		request.setAttribute("error", error);
	}

	// 一般ユーザ登録判定
	public int userRegistration(HttpServletRequest request) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String mail = request.getParameter("mail");
		// ID、パスワードをハッシュ値に変換する
		String hashId = SHA256.hash(id);
		String hashPassword = SHA256.hash(password);
		int code = userManager.register(hashId, name, hashPassword, mail); // 登録判定
		return code;
	}

	// 一般ユーザ登録確認画面へのデータ
	public void toUserRegistrationConfirm(HttpServletRequest request) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String mail = request.getParameter("mail");
		request.setAttribute("id", id);
		request.setAttribute("password", password);
		request.setAttribute("name", name);
		request.setAttribute("mail", mail);
	}

	// 一般ユーザ登録処理
	public void userRegistrationConfirm(HttpServletRequest request) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String mail = request.getParameter("mail");
		// ID、パスワードをハッシュ値に変換する
		String hashId = SHA256.hash(id);
		String hashPassword = SHA256.hash(password);
		userManager.registerConfirm(hashId, name, hashPassword, mail); // 登録処理
	}

	// 一般ユーザ更新画面へのデータ
	public void toUserUpdate(HttpServletRequest request, String generalId, int error) {
		String[] general = userManager.getUser(generalId);
		request.setAttribute("password", general[Constant.PASSWORD]);
		request.setAttribute("name", general[Constant.NAME]);
		request.setAttribute("mail", general[Constant.MAIL]);
		request.setAttribute("error", error);
	}

	// 一般ユーザ更新判定
	public int userUpdate(HttpServletRequest request, String generalId) {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String mail = request.getParameter("mail");
		// パスワードをハッシュ値に変換する
		String hashPassword = SHA256.hash(password);
		int code = userManager.update(generalId, name, hashPassword, mail); // 更新判定
		return code;
	}

	// 一般ユーザ更新処理
	public void userUpdateConfirm(HttpServletRequest request, String generalId) {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String mail = request.getParameter("mail");
		// パスワードをハッシュ値に変換する
		String hashPassword = SHA256.hash(password);
		userManager.updateConfirm(generalId, name, hashPassword, mail); // 更新処理
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

	// お気に入りサークル削除画面へのデータ
	public void toFavoriteClubDelete(HttpServletRequest request, String generalId) {
		String[][] favoriteClubs = favoriteManager.getFavorite(generalId);
		request.setAttribute("favoriteClubs", favoriteClubs);
	}

	// お気に入りサークル削除処理
	public void favoriteClubDelete(HttpServletRequest request, String generalId) {
		String[] clubIds = request.getParameterValues("clubId");
		for (String clubId : clubIds) {
			favoriteManager.delete(generalId, clubId); // お気に入りサークルを削除
		}
	}

	// サークルアカウント登録画面へのデータ
	public void toClubRegistration(HttpServletRequest request, int error) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String mail = request.getParameter("mail");
		String recogn = request.getParameter("recogn");
		request.setAttribute("id", id);
		request.setAttribute("password", password);
		request.setAttribute("name", name);
		request.setAttribute("mail", mail);
		request.setAttribute("recogn", recogn);
		request.setAttribute("error", error);
	}

	// サークルアカウント登録判定
	public int clubRegistration(HttpServletRequest request, String user) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String mail = request.getParameter("mail");
		// ID、パスワードをハッシュ値に変換する
		String hashId = SHA256.hash(id);
		String hashPassword = SHA256.hash(password);
		int code = -1;
		if (user == null) { // 閲覧者
			code = clubManager.register(Constant.VIEWER, hashId, name, hashPassword, mail); // 登録判定
		} else if (user.equals("admin")) { // 管理者
			code = clubManager.register(Constant.ADMIN, "", name, "", mail); // 登録判定
		}
		return code;
	}

	// サークルアカウント登録確認画面へのデータ
	public void toClubRegistrationConfirm(HttpServletRequest request) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String mail = request.getParameter("mail");
		String recogn = request.getParameter("recogn");
		request.setAttribute("id", id);
		request.setAttribute("password", password);
		request.setAttribute("name", name);
		request.setAttribute("mail", mail);
		request.setAttribute("recogn", recogn);
	}

	// サークルアカウント登録処理
	public void clubRegistrationConfirm(HttpServletRequest request, String user) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String mail = request.getParameter("mail");
		// ID、パスワードをハッシュ値に変換する
		String hashId = SHA256.hash(id);
		String hashPassword = SHA256.hash(password);
		if (user == null) { // 閲覧者
			clubManager.registerConfirm(Constant.VIEWER, hashId, name, hashPassword, mail); // 登録処理
		} else { // 管理者
			clubManager.registerConfirm(Constant.ADMIN, "", name, "", mail); // 登録処理
		}
	}

	// サークルアカウント更新画面へのデータ
	public void toClubUpdate(HttpServletRequest request, String clubId, int error) {
		String[] club = clubManager.getClub(clubId);
		request.setAttribute("id", club[Constant.ID]);
		request.setAttribute("password", club[Constant.PASSWORD]);
		request.setAttribute("name", club[Constant.NAME]);
		request.setAttribute("mail", club[Constant.MAIL]);
		request.setAttribute("recogn", club[Constant.RECOGN]);
		request.setAttribute("error", error);
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
		switch (user) {
		case "club": // サークルアカウント
			clubManager.updateConfirm(Constant.CLUB, clubId, name, hashPassword, mail, recogn); // 更新処理
			break;

		case "admin": // 管理者
			clubManager.updateConfirm(Constant.ADMIN, clubId, name, hashPassword, mail, recogn); // 更新処理
			break;
		}
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

	// サークル情報更新処理
	public boolean clubInfoUpdate(HttpServletRequest request, String clubId) {
		String link = request.getParameter("link");
		String intro = request.getParameter("intro");
		String member = request.getParameter("member");
		String icon = request.getParameter("icon");
		String home = request.getParameter("home");
		boolean result = clubInfoManager.update(clubId, link, intro, member, icon, home); // 更新処理
		return result;
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

	// アカウント削除画面へ
	public void toAccountDelete(HttpServletRequest request, String user, String deletedUser, String userId) {
		request.setAttribute("deletedUser", deletedUser);
		String[] userInfo = null;
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
			} else if (deletedUser.equals("club")) { // サークルアカウントが対象
				userInfo = clubManager.getClub(userId);
			}
			request.setAttribute("deletedId", userInfo[Constant.ID]);
			request.setAttribute("name", userInfo[Constant.NAME]);
			break;
		}
	}

	// アカウント削除確認画面へ
	public void toAccountDeleteConfirm(HttpServletRequest request, String user, String deletedUser, String deletedId) {
		if (user.equals("admin")) { // 管理者
			request.setAttribute("deletedUser", deletedUser);
			request.setAttribute("deletedId", deletedId);
		}
	}

	// アカウント削除処理
	public boolean accountDelete(HttpServletRequest request, String user, String userId) {
		String password = request.getParameter("password");
		// パスワードをハッシュ値に変換する
		String hashPassword = SHA256.hash(password);
		boolean result = false;
		switch (user) {
		case "general": // 一般ユーザ
			result = userManager.delete(userId, hashPassword);
			break;

		case "club": // サークルアカウント
			result = clubManager.delete(userId, hashPassword);
			break;

		case "admin": // 管理者
			String deletedUser = request.getParameter("deletedUser"); // 削除される対象
			if (deletedUser.equals("general")) { // 一般ユーザが対象
				result = clubManager.delete(userId, hashPassword);
			} else if (deletedUser.equals("club")) { // サークルアカウントが対象
				result = clubManager.delete(userId, hashPassword);
			}
			break;
		}
		return result;
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

	// タグ一覧表示画面へのデータ
	public void toTagDisplay(HttpServletRequest request) {
		String firstIndex = request.getParameter("firstIndex");
		if (firstIndex == null) {
			firstIndex = "0";
		}
		String[][] allTags = tagManager.getAllTags(Integer.parseInt(firstIndex)); // タグ情報をfirstIndexから10件取得
		request.setAttribute("allTags", allTags);
		request.setAttribute("firstIndex", firstIndex);
	}

	// タグ編集画面へのデータ
	public void toTagEdit(HttpServletRequest request) {
		String firstIndex = request.getParameter("firstIndex");
		if (firstIndex == null) {
			firstIndex = "0";
		}
		String[][] allTags = tagManager.getAllTags(Integer.parseInt(firstIndex)); // タグ情報をfirstIndexから10件取得
		request.setAttribute("allTags", allTags);
		request.setAttribute("firstIndex", firstIndex);
	}

	// 一般ユーザ一覧表示画面へのデータ
	public void toUserDisplay(HttpServletRequest request) {
		String firstIndex = request.getParameter("firstIndex");
		if (firstIndex == null) { // 最初のアクセスなら
			firstIndex = "0";
		}
		String[][] allUserInfo = userManager.getAllUsers(Integer.parseInt(firstIndex)); // 一般ユーザ情報をfirstIndexから10件取得
		request.setAttribute("users", allUserInfo);
		request.setAttribute("firstIndex", firstIndex);
	}

	// 一般ユーザ管理者閲覧用画面へのデータ
	public void toUserInfoDisplayForAdmin(HttpServletRequest request) {
		String generalId = request.getParameter("generalId");
		String[] general = userManager.getUser(generalId);
		request.setAttribute("id", general[Constant.ID]);
		request.setAttribute("name", general[Constant.NAME]);
		request.setAttribute("mail", general[Constant.MAIL]);
	}

	// サークルアカウント一覧表示画面へのデータ
	public void toClubDisplay(HttpServletRequest request) {
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

	// サークルアカウント管理者閲覧用画面へのデータ
	public void toClubInfoDisplayForAdmin(HttpServletRequest request) {
		String clubId = request.getParameter("clubId");
		String[] club = clubManager.getClub(clubId);
		String[] clubInfo = clubInfoManager.getClubInfo(club[Constant.CLUB_INFO_ID]);
		request.setAttribute("id", club[Constant.ID]);
		request.setAttribute("name", club[Constant.NAME]);
		request.setAttribute("mail", club[Constant.MAIL]);
		request.setAttribute("recogn", club[Constant.RECOGN]);
		request.setAttribute("intro", clubInfo[Constant.INTRO]);
	}

}
