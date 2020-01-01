package tool;

import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.AdminManager;
import model.ClubInfoManager;
import model.ClubManager;
import model.FavoriteManager;
import model.HoldTagManager;
import model.TagManager;
import model.UserManager;

public class PageDataManager {
	private static PageDataManager pageDataManager = new PageDataManager();
	private UserManager userManager;
	private ClubManager clubManager;
	private ClubInfoManager clubInfoManager;
	private FavoriteManager favoriteManager;
	private AdminManager adminManager;
	private TagManager tagManager;
	private HoldTagManager holdTagManager;
	private ErrorCheck errorCheck;

	private PageDataManager() {
		userManager = new UserManager();
		clubManager = new ClubManager();
		clubInfoManager = new ClubInfoManager();
		favoriteManager = new FavoriteManager();
		adminManager = new AdminManager();
		tagManager = new TagManager();
		holdTagManager = new HoldTagManager();
		errorCheck = ErrorCheck.getInstance();
	}

	public static PageDataManager getInstance() {
		return pageDataManager;
	}

	// トップ画面へのデータ
	public void toTop(HttpServletRequest request) {
		String StringFirstIndex = request.getParameter("firstIndex");
		int firstIndex = 0;
		if (StringFirstIndex != null) { // 最初のアクセスでなければ
			firstIndex = Integer.parseInt(StringFirstIndex);
		}
		String[][] allClubs = clubManager.getAllClubs(firstIndex); // サークルアカウント情報をfirstIndexから10件取得
		String[][] allClubInfo = new String[allClubs.length][Constant.NUM_OF_DISPLAY_CLUB_INFO]; // 閲覧用サークル情報
		for (int i = 0; i < allClubs.length; i++) {
			allClubInfo[i][Constant.DISPLAY_ID] = allClubs[i][Constant.ID];
			allClubInfo[i][Constant.DISPLAY_NAME] = allClubs[i][Constant.NAME];
			String[] clubInfo = clubInfoManager.getClubInfo(allClubs[i][Constant.CLUB_INFO_ID]);
			allClubInfo[i][Constant.DISPLAY_INTRO] = clubInfo[Constant.INTRO];
			allClubInfo[i][Constant.DISPLAY_ICON] = clubInfo[Constant.ICON];
		}
		int numOfPages = clubManager.getNumOfPages(); // ページ数
		if (firstIndex < (numOfPages - 1) * Constant.MAX_OF_DISPLAYS) { // 次のページがあればあることを返す
			request.setAttribute("next", true);
		}
		request.setAttribute("clubs", allClubInfo);
		request.setAttribute("firstIndex", firstIndex);
		request.setAttribute("numOfPages", numOfPages);
	}

	// ログイン処理
	public boolean login(HttpSession session, HttpServletRequest request, String user) {
		boolean result = false;
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		// ID、パスワードをハッシュ値に変換する
		String hashId = SHA256.hash(id);
		String hashPassword = SHA256.hash(password);
		switch (user) {
		case "general": // 一般ユーザ
			result = userManager.login(hashId, hashPassword); // ログイン処理
			toUserMyPage(request, hashId);
			break;

		case "club": // サークルアカウント
			result = clubManager.login(hashId, hashPassword); // ログイン処理
			if (result) { // ログイン成功
				toClubMyPage(request, hashId);
			}
			break;

		case "admin": // 管理者
			result = adminManager.login(hashId, hashPassword); // ログイン処理
			if (result) { // ログイン成功
				toTop(request);
			}
			break;
		}
		if (result) { // ログイン成功
			session.setAttribute("user", user);
			session.setAttribute("userId", hashId);
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
		int code = -1;
		code = userManager.register(hashId, name, hashPassword, mail); // 登録判定
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
		request.setAttribute("id", general[Constant.ID]);
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

	// お気に入り登録・削除処理
	public void favorite(HttpServletRequest request, String generalId) {
		String clubId = request.getParameter("clubId");
		favoriteManager.update(generalId, clubId);
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
		request.setAttribute("id", id);
		request.setAttribute("password", password);
		request.setAttribute("name", name);
		request.setAttribute("mail", mail);
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
		code = clubManager.register(id, name, password, mail); // 登録判定
		return code;
	}

	// サークルアカウント登録確認画面へのデータ
	public void toClubRegistrationConfirm(HttpServletRequest request) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String mail = request.getParameter("mail");
		request.setAttribute("id", id);
		request.setAttribute("password", password);
		request.setAttribute("name", name);
		request.setAttribute("mail", mail);
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
		clubManager.registerConfirm(hashId, name, hashPassword, mail); // 登録処理
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
		String hashPassword = null;
		if (password.equals("")) { // パスワードが空欄だったら前のパスワードに変更
			hashPassword = clubManager.getPassword(clubId);
		} else {
			// パスワードをハッシュ値に変換する
			hashPassword = SHA256.hash(password);
		}
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

		String[] tagIds = holdTagManager.getHoldTag(clubId);
		String[][] tags = tagManager.getTags(tagIds);
		request.setAttribute("tags", tags);
	}

	// サークル情報更新処理
	public boolean clubInfoUpdate(HttpServletRequest request, String clubId) {
		String[] tagNames = request.getParameterValues("addTagNames[]");
		if (tagNames != null) { // 追加リストがあれば
			tagManager.register(tagNames);
			String[] addTagIds = tagManager.getByNames(tagNames);
			holdTagManager.update(clubId, addTagIds);
		}

		String link = request.getParameter("link");
		String intro = request.getParameter("intro");
		String member = request.getParameter("member");
		boolean result = clubInfoManager.update(clubId, link, intro, member); // 更新処理
		return result;
	}

	// サークル情報の画像更新処理
	public void clubInfoImageUpdate(String clubId, String type, String image) {
		if (type.equals("icon")) {
			clubInfoManager.updateImage(clubId, "icon", image); // 更新処理
		} else if (type.equals("home")) {
			clubInfoManager.updateImage(clubId, "home", image); // 更新処理
		}
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
				request.setAttribute("deletedUser", "general");
			} else if (deletedUser.equals("club")) { // サークルアカウントが対象
				userInfo = clubManager.getClub(userId);
				request.setAttribute("deletedUser", "club");
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
				if (adminManager.confirmPassword(hashPassword)) {
					String[] general = userManager.getUser(userId);
					result = userManager.delete(userId, general[Constant.PASSWORD]);
				}
			} else if (deletedUser.equals("club")) { // サークルアカウントが対象
				if (adminManager.confirmPassword(hashPassword)) {
					String[] club = clubManager.getClub(userId);
					result = clubManager.delete(userId, club[Constant.PASSWORD]);
				}
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
		request.setAttribute("clubId", club[Constant.ID]);
		request.setAttribute("name", club[Constant.NAME]);
		request.setAttribute("mail", club[Constant.MAIL]);
		request.setAttribute("recogn", club[Constant.RECOGN]);
		request.setAttribute("link", clubInfo[Constant.LINK]);
		request.setAttribute("intro", clubInfo[Constant.INTRO]);
		request.setAttribute("member", clubInfo[Constant.MEMBER]);
		request.setAttribute("icon", clubInfo[Constant.ICON]);
		request.setAttribute("home", clubInfo[Constant.HOME]);

		String[] tagIds = holdTagManager.getHoldTag(clubId);
		String[][] tags = tagManager.getTags(tagIds);
		request.setAttribute("tags", tags);
	}

	// 検索結果表示画面へのデータ
	public void toSearchResultDisplay(HttpServletRequest request) {
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		String[][] result;
		if (type.equals("keyword")) { // キーワード検索
			result = clubManager.searchByKeyword(keyword); // 検索されたサークルID、サークル名、紹介文、アイコンを取得

		} else { // タグ検索
			result = clubManager.searchByTag(keyword); // 検索されたサークルID、サークル名、紹介文、アイコンを取得

		}
		request.setAttribute("result", result);
	}

	// タグ一覧表示画面へのデータ
	public void toTagDisplay(HttpServletRequest request) {
		String StringFirstIndex = request.getParameter("firstIndex");
		int firstIndex = 0;
		if (StringFirstIndex != null) { // 最初のアクセスでなければ
			firstIndex = Integer.parseInt(StringFirstIndex);
		}
		String[][] allTags = tagManager.getAllTags(firstIndex); // タグ情報をfirstIndexから10件取得
		int numOfPages = tagManager.getNumOfPages(); // ページ数
		if (firstIndex < (numOfPages - 1) * Constant.MAX_OF_DISPLAYS) { // 次のページがあればあることを返す
			request.setAttribute("next", true);
		}
		request.setAttribute("allTags", allTags);
		request.setAttribute("firstIndex", firstIndex);
		request.setAttribute("numOfPages", numOfPages);
	}

	// タグ編集画面へのデータ
	public void toTagEdit(HttpServletRequest request) {
		String StringFirstIndex = request.getParameter("firstIndex");
		int firstIndex = 0;
		if (StringFirstIndex != null) { // 最初のアクセスでなければ
			firstIndex = Integer.parseInt(StringFirstIndex);
		}
		String[][] allTags = tagManager.getAllTags(firstIndex); // タグ情報をfirstIndexから10件取得
		int numOfPages = tagManager.getNumOfPages(); // ページ数
		if (firstIndex < (numOfPages - 1) * Constant.MAX_OF_DISPLAYS) { // 次のページがあればあることを返す
			request.setAttribute("next", true);
		}
		request.setAttribute("allTags", allTags);
		request.setAttribute("firstIndex", firstIndex);
		request.setAttribute("numOfPages", numOfPages);

		// 削除リストに入れる
		String[] tagIds = request.getParameterValues("deleteTagIds[]");
		String tagId = request.getParameter("deleteTagId");
		if (tagId != null) { // 削除したいタグIDが送られてきてたら
			ArrayList<String> tagIdsList = null;
			if (tagIds == null) { // 削除リストがなければ
				tagIdsList = new ArrayList<String>();
			} else {
				tagIdsList = new ArrayList<String>(Arrays.asList(tagIds));
			}
			if (tagIdsList.contains(tagId)) { // タグIDが重複していれば
				tagIdsList.remove(tagId);
			} else {
				tagIdsList.add(tagId);
			}
			request.setAttribute("deleteTagIds", tagIdsList.toArray(new String[tagIdsList.size()]));
		}
	}

	// タグ削除処理
	public void tagsDelete(HttpServletRequest request) {
		String[] tagIds = request.getParameterValues("deleteTagIds[]");
		if (tagIds != null) {
			tagManager.delete(tagIds);
		}
	}

	// 一般ユーザ一覧表示画面へのデータ
	public void toUserDisplay(HttpServletRequest request) {
		String StringFirstIndex = request.getParameter("firstIndex");
		int firstIndex = 0;
		if (StringFirstIndex != null) { // 最初のアクセスでなければ
			firstIndex = Integer.parseInt(StringFirstIndex);
		}
		String[][] allUserInfo = userManager.getAllUsers(firstIndex); // 一般ユーザ情報をfirstIndexから10件取得
		int numOfPages = userManager.getNumOfPages(); // ページ数
		if (firstIndex < (numOfPages - 1) * Constant.MAX_OF_DISPLAYS) { // 次のページがあればあることを返す
			request.setAttribute("next", true);
		}
		request.setAttribute("users", allUserInfo);
		request.setAttribute("firstIndex", firstIndex);
		request.setAttribute("numOfPages", numOfPages);
	}

	// 一般ユーザ管理者閲覧用画面へのデータ
	public void toUserInfoDisplayForAdmin(HttpServletRequest request) {
		String generalId = request.getParameter("id");
		String[] general = userManager.getUser(generalId);
		request.setAttribute("id", general[Constant.ID]);
		request.setAttribute("name", general[Constant.NAME]);
		request.setAttribute("mail", general[Constant.MAIL]);
	}

	// サークルアカウント一覧表示画面へのデータ
	public void toClubDisplay(HttpServletRequest request) {
		String StringFirstIndex = request.getParameter("firstIndex");
		int firstIndex = 0;
		if (StringFirstIndex != null) { // 最初のアクセスでなければ
			firstIndex = Integer.parseInt(StringFirstIndex);
		}
		String[][] allClubs = clubManager.getAllClubs(firstIndex); // サークルアカウント情報をfirstIndexから10件取得
		String[][] allClubInfo = new String[allClubs.length][Constant.NUM_OF_DISPLAY_CLUB_INFO]; // 閲覧用サークル情報
		for (int i = 0; i < allClubs.length; i++) {
			allClubInfo[i][Constant.DISPLAY_ID] = allClubs[i][Constant.ID];
			allClubInfo[i][Constant.DISPLAY_NAME] = allClubs[i][Constant.NAME];
			String[] clubInfo = clubInfoManager.getClubInfo(allClubs[i][Constant.CLUB_INFO_ID]);
			allClubInfo[i][Constant.DISPLAY_INTRO] = clubInfo[Constant.INTRO];
			allClubInfo[i][Constant.DISPLAY_ICON] = clubInfo[Constant.ICON];
		}
		int numOfPages = clubManager.getNumOfPages(); // ページ数
		if (firstIndex < (numOfPages - 1) * Constant.MAX_OF_DISPLAYS) { // 次のページがあればあることを返す
			request.setAttribute("next", true);
		}
		request.setAttribute("clubs", allClubInfo);
		request.setAttribute("firstIndex", firstIndex);
		request.setAttribute("numOfPages", numOfPages);
	}

	// サークルアカウント管理者閲覧用画面へのデータ
	public void toClubInfoDisplayForAdmin(HttpServletRequest request) {
		String clubId = request.getParameter("id");
		String[] club = clubManager.getClub(clubId);
		String[] clubInfo = clubInfoManager.getClubInfo(club[Constant.CLUB_INFO_ID]);
		request.setAttribute("id", club[Constant.ID]);
		request.setAttribute("name", club[Constant.NAME]);
		request.setAttribute("mail", club[Constant.MAIL]);
		request.setAttribute("recogn", club[Constant.RECOGN]);
		request.setAttribute("intro", clubInfo[Constant.INTRO]);
	}

	// 管理者問い合わせ内容確認画面へのデータ
	public int toContactInfoConfirm(HttpServletRequest request) {
		String subject = request.getParameter("subject");
		String info = request.getParameter("info");
		int error = -1;
		if (errorCheck.blankCheck(subject) || errorCheck.blankCheck(subject)) {
			error = Constant.CONTAINS_BLANK;
		} else if (errorCheck.exCharCheck(subject) || errorCheck.exCharCheck(info)) {
			error = Constant.CONTAINS_EX_CHAR;
		} else {
			error = Constant.SUCCESS;
		}
		request.setAttribute("subject", subject);
		request.setAttribute("info", info);
		request.setAttribute("error", error);
		return Constant.SUCCESS;
	}

	// 管理者問い合わせ確認通知画面へのデータ
	public void contactAdmin(HttpServletRequest request, String user, String userId) {
		String subject = (String) request.getAttribute("subject");
		String info = (String) request.getAttribute("info");
		adminManager.mailToAdmin(user, userId, subject, info);
	}

}
