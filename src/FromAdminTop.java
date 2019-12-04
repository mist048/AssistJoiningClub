
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ClubInfoManager;
import model.ClubManager;
import model.TagManager;
import model.UserManager;
import tool.Constant;

/**
 * Servlet implementation class FromTop
 */
@WebServlet("/FromAdminTop")
public class FromAdminTop extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserManager userManager;
	ClubManager clubManager;
	ClubInfoManager clubInfoManager;
	TagManager tagManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FromAdminTop() {
		super();
		userManager = new UserManager();
		clubManager = new ClubManager();
		clubInfoManager = new ClubInfoManager();
		tagManager = new TagManager();
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
		String option = request.getParameter("option");

		switch (option) {
		case "tagDisplay": // タグ一覧画面へ
			String firstIndex = request.getParameter("firstIndex");
			if (firstIndex == null) {
				firstIndex = "0";
			}
			String[][] allTags = tagManager.getAllTags(Integer.parseInt(firstIndex));
			request.setAttribute("allTags", allTags);
			getServletContext().getRequestDispatcher("/tagDisplay.jsp").forward(request, response);
			break;

		case "clubDisplay": // サークル情報一覧画面
			String clubId = request.getParameter("clubId");
			String[] club = clubManager.getClub(clubId);
			String[] clubInfo = clubInfoManager.getClubInfo(club[Constant.CLUB_INFO_ID]);
			request.setAttribute("id", club[Constant.ID]);
			request.setAttribute("name", club[Constant.NAME]);
			request.setAttribute("mail", club[Constant.MAIL]);
			request.setAttribute("recogn", club[Constant.RECOGN]);
			request.setAttribute("link", clubInfo[Constant.LINK]);
			request.setAttribute("intro", clubInfo[Constant.INTRO]);
			request.setAttribute("member", clubInfo[Constant.MEMBER]);
			request.setAttribute("icon", clubInfo[Constant.ICON]);
			request.setAttribute("home", clubInfo[Constant.HOME]);
			getServletContext().getRequestDispatcher("/clubDisplay.jsp").forward(request, response);
			break;

		case "userDisplay": // 一般ユーザ一覧画面
			String generalId = request.getParameter("generalId");
			String[] general = userManager.getUser(generalId);
			request.setAttribute("id", general[Constant.ID]);
			request.setAttribute("name", general[Constant.NAME]);
			request.setAttribute("mail", general[Constant.MAIL]);
			getServletContext().getRequestDispatcher("/userDisplay.jsp").forward(request, response);
			break;
		}
	}

}
