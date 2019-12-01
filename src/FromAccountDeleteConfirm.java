
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ClubInfoManager;
import model.ClubManager;
import tool.Constant;
import tool.SHA256;

/**
 * Servlet implementation class FromAccountDeleteConfirm
 */
@WebServlet("/FromAccountDeleteConfirm")
public class FromAccountDeleteConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClubManager clubManager;
	ClubInfoManager clubInfoManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FromAccountDeleteConfirm() {
		super();
		clubManager = new ClubManager();
		clubInfoManager = new ClubInfoManager();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("user");
		String password = request.getParameter("password");
		// パスワードをハッシュ値に変換する
		String hashPassword = SHA256.hash(password);

		switch (user) {
		case "club": // サークルアカウント
			String hashId = (String) session.getAttribute("userId");
			if (clubManager.delete(hashId, hashPassword)) { // 削除成功
				String firstIndex = request.getParameter("firstIndex");
				String[][] allClubs = clubManager.getAllClubs(Integer.parseInt(firstIndex)); // サークルアカウント情報をfirstIndexから10件取得
				String[][] allClubInfo = new String[allClubs.length][3]; // 閲覧用サークル情報
				for (int i = 0; i < allClubs.length; i++) {
					allClubInfo[i][Constant.ID] = allClubs[i][Constant.ID];
					allClubInfo[i][Constant.NAME] = allClubs[i][Constant.NAME];
					String[] clubInfo = clubInfoManager.getClubInfo(allClubs[i][Constant.CLUB_INFO_ID]);
					allClubInfo[i][2] = clubInfo[Constant.CLUB_INFO_ID];
				}
				request.setAttribute("clubs", allClubInfo);
				getServletContext().getRequestDispatcher("/viewerTop.jsp").forward(request, response);
			} else { // 削除失敗
				getServletContext().getRequestDispatcher("/accountDeleteConfirm.jsp").forward(request, response);
			}
			break;

		case "admin": // 管理者
			String clubId = request.getParameter("clubId");
			if (clubManager.delete(clubId, hashPassword)) { // 削除成功
				String firstIndex = request.getParameter("firstIndex");
				String[][] allClubs = clubManager.getAllClubs(Integer.parseInt(firstIndex)); // サークルアカウント情報をfirstIndexから10件取得
				String[][] allClubInfo = new String[allClubs.length][3]; // 閲覧用サークル情報
				for (int i = 0; i < allClubs.length; i++) {
					allClubInfo[i][Constant.ID] = allClubs[i][Constant.ID];
					allClubInfo[i][Constant.NAME] = allClubs[i][Constant.NAME];
					String[] clubInfo = clubInfoManager.getClubInfo(allClubs[i][Constant.CLUB_INFO_ID]);
					allClubInfo[i][2] = clubInfo[Constant.CLUB_INFO_ID];
				}
				request.setAttribute("clubs", allClubInfo);
				getServletContext().getRequestDispatcher("/clubDisplay.jsp").forward(request, response);
			} else { // 削除失敗
				getServletContext().getRequestDispatcher("/accountDeleteConfirm.jsp").forward(request, response);
			}
			break;
		}
	}

}