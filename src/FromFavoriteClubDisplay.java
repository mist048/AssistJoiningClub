
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ClubInfoManager;
import model.ClubManager;
import model.FavoriteManager;
import model.UserManager;
import tool.Constant;

/**
 * Servlet implementation class FromFavoriteClubDisplay
 */
@WebServlet("/FromFavoriteClubDisplay")
public class FromFavoriteClubDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserManager userManager;
	ClubManager clubManager;
	ClubInfoManager clubInfoManager;
	FavoriteManager favoriteManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FromFavoriteClubDisplay() {
		super();
		userManager = new UserManager();
		clubManager = new ClubManager();
		clubInfoManager = new ClubInfoManager();
		favoriteManager = new FavoriteManager();
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
		String hashId = (String) session.getAttribute("userId");
		String option = request.getParameter("option");

		switch (option) {
		case "clubInfoDisplay": // サークル情報閲覧画面へ
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
			getServletContext().getRequestDispatcher("/clubInfoDisplay.jsp").forward(request, response);
			break;

		case "edit": // お気に入りサークル削除画面へ
			String[][] favoriteClubs = favoriteManager.getFavorite(hashId);
			request.setAttribute("favoriteClubs", favoriteClubs);
			getServletContext().getRequestDispatcher("/favoriteClubDelete.jsp").forward(request, response);
			break;

		case "userMyPage": // 一般ユーザマイページへ
			String[] general = userManager.getUser(hashId);
			request.setAttribute("name", general[Constant.NAME]);
			request.setAttribute("mail", general[Constant.MAIL]);
			getServletContext().getRequestDispatcher("/userMyPage.jsp").forward(request, response);
			break;
		}
	}

}
