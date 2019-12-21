package tool;

public class Constant {
	// コード
	public static final int SUCCESS = 0; // 成功
	public static final int EXCEED_NUM_OF_CHAR = 1; // 定義された文字数を超えている
	public static final int CONTAINS_EX_CHAR = 2; // 特殊な文字が含まれている
	public static final int CONTAINS_BLANK = 3; // 空欄が含まれている
	public static final int DUPLICATE = 4; // 重複している

	// 特殊文字
	public static final char[] EX_CHAR = { '[', ']', '?', '/', '<', '~', '#', '`', '!', '@', '$', '%', '^', '&', '*',
			'(', ')', '+', '=', '{', '|', ':', '\"', ';', '\'', '>', '}', ' ' };

	// ユーザの種類
	public static final int GENERAL = 0; // 一般ユーザ
	public static final int CLUB = 1; // サークルアカウント
	public static final int ADMIN = 2; // 管理者
	public static final int VIEWER = 3; // 閲覧者

	// 情報の要素数
	public static final int NUM_OF_USER_INFO = 4; // 一般ユーザ情報の要素数
	public static final int NUM_OF_CLUB_INFO = 6; // サークルアカウント情報の要素数

	// 登録情報
	public static final int ID = 0; // ID
	public static final int NAME = 1; // 名前
	public static final int PASSWORD = 2; // パスワード
	public static final int MAIL = 3; // メールアドレス
	public static final int RECOGN = 4; // 公認か非公認かという情報
	public static final int CLUB_INFO_ID = 5; // サークル情報ID
	public static final int LINK = 1; // リンク
	public static final int INTRO = 2; // 紹介文
	public static final int DESCRIPTION = 2; // 説明文
	public static final int MEMBER = 3; // 人数
	public static final int ICON = 4; // アイコン
	public static final int HOME = 5; // ホーム画像

	public static final int MAX_OF_DISPLAYS = 10; // 一覧表示数

	public static final String POSTGRES_PASSWORD = "dRagoon8!"; // postgresのパスワード

	private Constant() {
	}
}
