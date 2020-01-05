package tool;

public class ErrorCheck {
	private static ErrorCheck errorCheck = new ErrorCheck();

	private ErrorCheck() {
	}

	public static ErrorCheck getInstance() {
		return errorCheck;
	}

	// 空欄をチェックする
	public boolean blankCheck(String str) {
		if (str.equals("")) {
			return true;
		}
		return false;
	}

	// 特殊な文字をチェックする
	public boolean exCharCheck(String str) {
		for (int i = 0; i < str.length(); i++) {
			for (char c : Constant.EX_CHAR) {
				if (str.charAt(i) == c) { // 特殊文字
					return true;
				}
			}
		}
		return false;
	}
	
	// 特殊な文字をチェックする(メール)
		public boolean exCharCheckForMail(String str) {
			for (int i = 0; i < str.length(); i++) {
				for (char c : Constant.EX_CHAR_MAIL) {
					if (str.charAt(i) == c) { // 特殊文字
						return true;
					}
				}
			}
			return false;
		}

	// ASCII文字以上をチェックする
	public boolean notAsciiCheck(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) > 0x007f) { // ASCII文字以上
				return true;
			}
		}
		return false;
	}

	// httpから始まるかどうかをチェックする
	public boolean notStartHTTPCheck(String str) {
		if (!str.startsWith("http")) {
			return true;
		}
		return false;
	}
}
