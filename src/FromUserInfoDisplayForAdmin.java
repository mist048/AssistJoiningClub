
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ClubInfoManager;
import model.ClubManager;
import model.UserManager;
import tool.Constant;

/**
 * Servlet implementation class FromUserDisplay
 */
@WebServlet("/FromUserInfoDisplayForAdmin")
public class FromUserInfoDisplayForAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserManager userManager;
	ClubManager clubManager;
	ClubInfoManager clubInfoManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FromUserInfoDisplayForAdmin() {
		super();
		userManager = new UserManager();
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
		String generalId = request.getParameter("generalId");
		String option = request.getParameter("option");

		String[] general = userManager.getUser(generalId);

		switch (option) {
		case "setting": // 一般ユーザ更新画面へ
			request.setAttribute("id", general[Constant.ID]);
			request.setAttribute("password", general[Constant.PASSWORD]);
			request.setAttribute("name", general[Constant.NAME]);
			request.setAttribute("mail", general[Constant.MAIL]);
			getServletContext().getRequestDispatcher("/userUpdate.jsp").forward(request, response);
			break;

		case "top": // 管理者トップ画面へ
			String[][] allClubs = clubManager.getAllClubs(0); // サークルアカウント情報をfirstIndexから10件取得
			String[][] allClubInfo = new String[allClubs.length][3]; // 閲覧用サークル情報
			for (int i = 0; i < allClubs.length; i++) {
				allClubInfo[i][Constant.ID] = allClubs[i][Constant.ID];
				allClubInfo[i][Constant.NAME] = allClubs[i][Constant.NAME];
				String[] clubInfo = clubInfoManager.getClubInfo(allClubs[i][Constant.CLUB_INFO_ID]);
				allClubInfo[i][2] = clubInfo[Constant.CLUB_INFO_ID];
			}
			request.setAttribute("clubs", allClubInfo);
			getServletContext().getRequestDispatcher("/adminTop.jsp").forward(request, response);
			break;
		}
	}

}
