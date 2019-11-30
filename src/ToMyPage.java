
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AdminManager;
import model.ClubInfoManager;
import model.ClubManager;
import model.UserManager;
import tool.Constant;
import tool.SHA256;

/**
 * Servlet implementation class ToMyPage
 */
@WebServlet("/ToMyPage")
public class ToMyPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserManager userManager;
	ClubManager clubManager;
	ClubInfoManager clubInfoManager;
	AdminManager adminManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ToMyPage() {
		super();
		userManager = new UserManager();
		clubManager = new ClubManager();
		clubInfoManager = new ClubInfoManager();
		adminManager = new AdminManager();
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
		//String user = request.getParameter("group1");
		String user = "club"; // テスト用
		if (user != null) {
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			// ID、パスワードをハッシュ値に変換する
			String hashId = SHA256.hash(id);
			String hashPassword = SHA256.hash(password);
			boolean result;

			switch (user) {
			case "general": // 一般ユーザ
				result = userManager.login(hashId, hashPassword);
				if (result) { // ログイン成功
					HttpSession session = request.getSession();
					session.setAttribute("login", true);
					session.setAttribute("useId", hashId);

					String[] general = userManager.getUser(hashId);
					request.setAttribute("id", general[Constant.ID]);
					request.setAttribute("name", general[Constant.NAME]);
					request.setAttribute("mail", general[Constant.MAIL]);
					getServletContext().getRequestDispatcher("/userMyPage.jsp").forward(request, response);
				} else { // ログイン失敗
					request.setAttribute("error", true);
					getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				}
				break;

			case "club": // サークルアカウント
				result = clubManager.login(hashId, hashPassword);
				if (result) { // ログイン成功
					HttpSession session = request.getSession();
					session.setAttribute("login", true);
					session.setAttribute("useId", hashId);

					String[] club = clubManager.getClub(hashId);
					String[] clubInfo = clubInfoManager.getClubInfo(club[Constant.CLUB_INFO_ID]);
					request.setAttribute("id", club[Constant.ID]);
					request.setAttribute("name", club[Constant.NAME]);
					request.setAttribute("mail", club[Constant.MAIL]);
					request.setAttribute("recogn", club[Constant.RECOGN]);
					request.setAttribute("intro", clubInfo[Constant.INTRO]);
					getServletContext().getRequestDispatcher("/clubMyPage.jsp").forward(request, response);
				} else { // ログイン失敗
					request.setAttribute("error", true);
					getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				}
				break;

			case "admin":
				result = adminManager.login(hashId, hashPassword);
				if (result) { // ログイン成功
					HttpSession session = request.getSession();
					session.setAttribute("login", true);
					session.setAttribute("useId", hashId);
					getServletContext().getRequestDispatcher("/adminTop.jsp").forward(request, response);
				} else { // ログイン失敗
					request.setAttribute("error", true);
					getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				}
				break;
			}
		} else {
			request.setAttribute("error", true);
			getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
}
