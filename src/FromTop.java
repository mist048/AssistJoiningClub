
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ClubInfoManager;
import model.ClubManager;
import model.UserManager;
import tool.Constant;

/**
 * Servlet implementation class FromTop
 */
@WebServlet("/FromTop")
public class FromTop extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserManager userManager;
	ClubManager clubManager;
	ClubInfoManager clubInfoManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FromTop() {
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
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("user");
		String option = request.getParameter("option");

		if (user == null) { // 閲覧者
			switch (option) {
			case "search": // 検索結果表示画面へ
				search(request);
				getServletContext().getRequestDispatcher("/searchResultDisplay.jsp").forward(request, response);
				break;

			case "clubInfoDisplay": // サークル情報閲覧画面へ
				getClubInfoData(request);
				getServletContext().getRequestDispatcher("/clubInfoDisplay.jsp").forward(request, response);
				break;
			}

		} else if (user.equals("general")) { // 一般ユーザ
			String hashId = (String) session.getAttribute("userId");
			switch (option) {
			case "searchResultDisplay": // 検索結果表示画面へ
				search(request);
				getServletContext().getRequestDispatcher("/searchResultDisplay.jsp").forward(request, response);
				break;

			case "userMyPage": // 一般ユーザマイページ画面へ
				String[] general = userManager.getUser(hashId);
				request.setAttribute("name", general[Constant.NAME]);
				request.setAttribute("mail", general[Constant.MAIL]);
				getServletContext().getRequestDispatcher("/userMyPage.jsp").forward(request, response);
				break;

			case "clubInfoDisplay": // サークル情報閲覧画面へ
				getClubInfoData(request);
				getServletContext().getRequestDispatcher("/clubInfoDisplay.jsp").forward(request, response);
				break;
			}

		} else { // サークルアカウント
			// サークルアカウントマイページ画面へ
			String hashId = (String) session.getAttribute("userId");
			String[] club = clubManager.getClub(hashId);
			String[] clubInfo = clubInfoManager.getClubInfo(club[Constant.CLUB_INFO_ID]);
			request.setAttribute("name", club[Constant.NAME]);
			request.setAttribute("mail", club[Constant.MAIL]);
			request.setAttribute("recogn", club[Constant.RECOGN]);
			request.setAttribute("intro", clubInfo[Constant.INTRO]);
			getServletContext().getRequestDispatcher("/clubMyPage.jsp").forward(request, response);
		}
	}

	private void search(HttpServletRequest request) {
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

	private void getClubInfoData(HttpServletRequest request) {
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

}
