
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

/**
 * Servlet implementation class FromClubMypage
 */
@WebServlet("/FromClubMyPage")
public class FromClubMyPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClubManager clubManager;
	ClubInfoManager clubInfoManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FromClubMyPage() {
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
		HttpSession session = request.getSession();
		String hashId = (String) session.getAttribute("userId");
		String option = request.getParameter("option");

		String[] club = clubManager.getClub(hashId);
		String[] clubInfo = clubInfoManager.getClubInfo(club[Constant.CLUB_INFO_ID]);

		switch (option) {
		case "setting": // サークルアカウント更新画面へ
			request.setAttribute("password", club[Constant.PASSWORD]);
			request.setAttribute("name", club[Constant.NAME]);
			request.setAttribute("mail", club[Constant.MAIL]);
			request.setAttribute("recogn", club[Constant.RECOGN]);
			getServletContext().getRequestDispatcher("/clubUpdate.jsp").forward(request, response);
			break;

		case "edit": // サークル情報更新画面へ
			request.setAttribute("name", club[Constant.NAME]);
			request.setAttribute("mail", club[Constant.MAIL]);
			request.setAttribute("recogn", club[Constant.RECOGN]);
			request.setAttribute("link", clubInfo[Constant.LINK]);
			request.setAttribute("intro", clubInfo[Constant.INTRO]);
			request.setAttribute("member", clubInfo[Constant.MEMBER]);
			request.setAttribute("icon", clubInfo[Constant.ICON]);
			request.setAttribute("home", clubInfo[Constant.HOME]);
			getServletContext().getRequestDispatcher("/clubInfoUpdate.jsp").forward(request, response);
			break;

		case "top": // トップ画面へ
			String[][] allClubs = clubManager.getAllClubs(0); // サークルアカウント情報をfirstIndexから10件取得
			String[][] allClubInfo = new String[allClubs.length][3]; // 閲覧用サークル情報
			for (int i = 0; i < allClubs.length; i++) {
				allClubInfo[i][Constant.ID] = allClubs[i][Constant.ID];
				allClubInfo[i][Constant.NAME] = allClubs[i][Constant.NAME];
				clubInfo = clubInfoManager.getClubInfo(allClubs[i][Constant.CLUB_INFO_ID]);
				allClubInfo[i][2] = clubInfo[Constant.CLUB_INFO_ID];
			}
			request.setAttribute("clubs", allClubInfo);
			getServletContext().getRequestDispatcher("/clubTop.jsp").forward(request, response);
			break;
		}
	}

}
