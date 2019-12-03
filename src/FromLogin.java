
import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import model.*;
import tool.*;

/**
 * Servlet implementation class FromLogin
 */
@WebServlet("/FromLogin")
public class FromLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserManager userManager;
	ClubManager clubManager;
	ClubInfoManager clubInfoManager;
	AdminManager adminManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FromLogin() {
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
		HttpSession session = request.getSession();
		//String user = request.getParameter("group1");
		String user = "club"; // テスト用 本番用:request.getParameter("user")
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		// ID、パスワードをハッシュ値に変換する
		String hashId = SHA256.hash(id);
		String hashPassword = SHA256.hash(password);
		boolean result;

		switch (user) {
		case "general": // 一般ユーザ
			result = userManager.login(hashId, hashPassword); // ログイン処理
			if (result) { // ログイン成功
				session.setAttribute("login", result);
				session.setAttribute("useId", hashId);

				// マイページへのデータ
				String[] general = userManager.getUser(hashId);
				request.setAttribute("name", general[Constant.NAME]);
				request.setAttribute("mail", general[Constant.MAIL]);
				getServletContext().getRequestDispatcher("/userMyPage.jsp").forward(request, response);
			} else { // ログイン失敗
				request.setAttribute("login", result);
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			}
			break;

		case "club": // サークルアカウント
			result = clubManager.login(hashId, hashPassword); // ログイン処理
			if (result) { // ログイン成功
				session.setAttribute("login", true);
				session.setAttribute("useId", hashId);

				// マイページへのデータ
				String[] club = clubManager.getClub(hashId);
				String[] clubInfo = clubInfoManager.getClubInfo(club[Constant.CLUB_INFO_ID]);
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

		case "admin": // 管理者
			result = adminManager.login(hashId, hashPassword); // ログイン処理
			if (result) { // ログイン成功
				session.setAttribute("login", true);
				session.setAttribute("useId", hashId);
				getServletContext().getRequestDispatcher("/adminTop.jsp").forward(request, response);
			} else { // ログイン失敗
				request.setAttribute("error", true);
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			}
			break;
		}
	}

}
