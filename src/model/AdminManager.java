package model;

public class AdminManager {
	UserDAO userDAO;
	ClubDAO clubDAO;
	private AdminDAO adminDAO;

	public AdminManager() {
		userDAO = new UserDAO();
		clubDAO = new ClubDAO();
		adminDAO = new AdminDAO();
	}

	public boolean login(String hashId, String hashPassword) {
		return true;
	}

	public void mailToAdmin(String user, String userId, String subject, String info) {
		String mail = null;
		switch (user) {
		case "general": // 一般ユーザ
			mail = userDAO.getMail(userId);
			break;

		case "club": // サークルアカウント
			mail = clubDAO.getMail(userId);
			break;
		}
		String adminMail = adminDAO.getMail();
	}

}
