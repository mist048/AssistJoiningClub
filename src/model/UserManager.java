package model;

import tool.Constant;
import tool.ErrorCheck;

public class UserManager {
	private UserDAO userDAO;
	private ErrorCheck errorCheck;

	public UserManager() {
		userDAO = new UserDAO();
		errorCheck = ErrorCheck.getInstance();
	}

	public boolean login(String id, String password) {
		if (userDAO.find(id, password)) {
			return true;
		}
		return false;
	}

	public String[] getUser(String id) {
		User user = new User();
		user = userDAO.getUser(id);
		String[] userInfo = new String[Constant.NUM_OF_USER_INFO];
		userInfo[Constant.ID] = user.getId();
		userInfo[Constant.NAME] = user.getName();
		userInfo[Constant.PASSWORD] = user.getPassword();
		userInfo[Constant.MAIL] = user.getMail();
		return userInfo;
	}

	public int register(String id, String name, String password, String mail) {
		String[] user = new String[Constant.NUM_OF_USER_INFO];
		if (userDAO.findById(id) != 0 || userDAO.findByMail(mail) != 0) { // IDかメールアドレスが重複している
			return Constant.DUPLICATE;
		}
		for (int i = 0; i < user.length; i++) {
			if (i != Constant.NAME) {
				if (errorCheck.notAsciiCheck(user[i])) { // ASCII文字以上を含んでいる
					return Constant.CONTAINS_EX_CHAR;
				}
			}
			if (errorCheck.blankCheck(user[i])) { // 特殊な文字を含んでいる
				return Constant.CONTAINS_EX_CHAR;
			}
		}
		return Constant.SUCCESS;
	}

	public void registerConfirm(String id, String name, String password, String mail) {
		userDAO.insert(id, name, password, mail);
	}

	public int update(String id, String name, String password, String mail) {
		String[] user = new String[Constant.NUM_OF_USER_INFO];
		if (userDAO.findByMail(mail) > 1) { // メールアドレスが重複している
			return Constant.DUPLICATE;
		}
		for (int i = 0; i < user.length; i++) {
			if (i != Constant.NAME) {
				if (errorCheck.notAsciiCheck(user[i])) { // ASCII文字以上を含んでいる
					return Constant.CONTAINS_EX_CHAR;
				}
			}
			if (errorCheck.blankCheck(user[i])) { // 特殊な文字を含んでいる
				return Constant.CONTAINS_EX_CHAR;
			}
		}
		return Constant.SUCCESS;
	}

	public void updateConfirm(String id, String name, String password, String mail) {
		userDAO.update(id, name, password, mail);
	}

	public boolean delete(String id, String password) {
		if (userDAO.find(id, password)) { // ユーザ情報があれば削除する
			userDAO.delete(id, password);
			return true;
		} else {
			return false;
		}
	}

	public String[][] getAllUsers(int firstIndex) {
		User[] allUsers = userDAO.getAllUsers(firstIndex);
		String[][] allUserInfo = new String[allUsers.length][Constant.NUM_OF_USER_INFO];
		for (int i = 0; i < allUsers.length; i++) {
			allUserInfo[i][Constant.ID] = allUsers[i].getId();
			allUserInfo[i][Constant.NAME] = allUsers[i].getName();
			allUserInfo[i][Constant.PASSWORD] = allUsers[i].getPassword();
			allUserInfo[i][Constant.MAIL] = allUsers[i].getMail();
		}
		return allUserInfo;
	}

	public int numOfPages() {
		int count = 0;
		count = userDAO.getNumOfUsers();
		int numOfPages = count / Constant.MAX_OF_DISPLAYS;
		if (count % Constant.MAX_OF_DISPLAYS != 0) {
			numOfPages += 1;
		}
		return numOfPages;
	}

}
