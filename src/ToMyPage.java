
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
 * Servlet implementation class ToMyPage
 */
@WebServlet("/ToMyPage")
public class ToMyPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//UserManager userManager;
	ClubManager clubManager;
	ClubInfoManager clubInfoManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ToMyPage() {
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
		/*String user = request.getParameter("group1");
		if (user != null) {*/
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		// ID、パスワードをハッシュ値に変換する
		String hashId = SHA256.hash(id);
		String hashPassword = SHA256.hash(password);

		/*switch (user) {
		case "general": // 一般ユーザ
			int code = userManager.login(hashId, hashPassword);
			if (code == Constant.SUCCESS) { // ログイン成功
				HttpSession session = request.getSession();
				session.setAttribute("login", true);
				session.setAttribute("useId", hashId);
				getServletContext().getRequestDispatcher("/userMyPage.jsp").forward(request, response);
			} else if (code == Constant.EXCEED_NUM_OF_CHAR) { // 定義された文字数を超えている
				request.setAttribute("error", Constant.EXCEED_NUM_OF_CHAR);
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			} else if (code == Constant.CONTAINS_EX_CHAR) { // 特殊な文字が含まれている
				request.setAttribute("error", Constant.CONTAINS_EX_CHAR);
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			} else { // 空欄が含まれている
				request.setAttribute("error", Constant.CONTAINS_BLANK);
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			}
			break;

		case "club": // サークルアカウント*/
		int code = clubManager.login(hashId, hashPassword);
		if (code == Constant.SUCCESS) { // ログイン成功
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

		} else if (code == Constant.EXCEED_NUM_OF_CHAR) { // 定義された文字数を超えている
			request.setAttribute("error", Constant.EXCEED_NUM_OF_CHAR);
			getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);

		} else if (code == Constant.CONTAINS_EX_CHAR) { // 特殊な文字が含まれている
			request.setAttribute("error", Constant.CONTAINS_EX_CHAR);
			getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);

		} else { // 空欄が含まれている
			request.setAttribute("error", Constant.CONTAINS_BLANK);
			getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);

		}
		/*break;
		 *
		case "admin":
		int code = adminManager.login(hashId, hashPassword);
		if (code == Constant.SUCCESS) { // ログイン成功
			HttpSession session = request.getSession();
			session.setAttribute("login", true);
			session.setAttribute("useId", hashId);
			getServletContext().getRequestDispatcher("/adminTop.jsp").forward(request, response);
		} else if (code == Constant.EXCEED_NUM_OF_CHAR) { // 定義された文字数を超えている
			request.setAttribute("error", Constant.EXCEED_NUM_OF_CHAR);
			getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		} else if (code == Constant.CONTAINS_EX_CHAR) { // 特殊な文字が含まれている
			request.setAttribute("error", Constant.CONTAINS_EX_CHAR);
			getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		} else { // 空欄が含まれている
			request.setAttribute("error", Constant.CONTAINS_BLANK);
			getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}
		break;
		}
		} else {

		}*/
	}

}
