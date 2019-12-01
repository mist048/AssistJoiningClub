
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
 * Servlet implementation class ClubUpdate
 */
@WebServlet("/ClubUpdate")
public class ClubUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClubManager clubManager;
	ClubInfoManager clubInfoManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClubUpdate() {
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
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String mail = request.getParameter("mail");
		String recogn = request.getParameter("recogn");
		// ID、パスワードをハッシュ値に変換する
		String hashPassword = SHA256.hash(password);

		HttpSession session = request.getSession();
		String hashId = (String) session.getAttribute("userId");
		int code = clubManager.update(hashId, name, hashPassword, mail); // 更新判定
		switch (code) {
		case Constant.SUCCESS: // 更新できる
			String user = (String) session.getAttribute("user");
			if (user.equals("club")) { // サークルアカウント
				clubManager.updateConfirm(Constant.CLUB, hashId, name, hashPassword, mail, null); // 更新処理
				getServletContext().getRequestDispatcher("/clubMyPage.jsp").forward(request, response);

			} else { // 管理者
				clubManager.updateConfirm(Constant.ADMIN, hashId, name, hashPassword, mail, recogn); // 更新処理
				getServletContext().getRequestDispatcher("/clubInfoDisplayForAdmin.jsp").forward(request, response);
			}

			// マイページへのデータ
			String[] club = clubManager.getClub(hashId);
			String[] clubInfo = clubInfoManager.getClubInfo(club[Constant.CLUB_INFO_ID]);
			request.setAttribute("name", club[Constant.NAME]);
			request.setAttribute("mail", club[Constant.MAIL]);
			request.setAttribute("recogn", club[Constant.RECOGN]);
			request.setAttribute("intro", clubInfo[Constant.INTRO]);
			break;

		case Constant.EXCEED_NUM_OF_CHAR: // 定義された文字数を超えている
			// 更新画面へのデータ
			request.setAttribute("error", Constant.EXCEED_NUM_OF_CHAR);
			request.setAttribute("name", name);
			request.setAttribute("password", password);
			request.setAttribute("mail", mail);
			request.setAttribute("recogn", recogn);
			getServletContext().getRequestDispatcher("/clubUpdate.jsp").forward(request, response);
			break;

		case Constant.CONTAINS_EX_CHAR: // 特殊な文字が含まれている
			// 更新画面へのデータ
			request.setAttribute("error", Constant.CONTAINS_EX_CHAR);
			request.setAttribute("name", name);
			request.setAttribute("password", password);
			request.setAttribute("mail", mail);
			request.setAttribute("recogn", recogn);
			getServletContext().getRequestDispatcher("/clubUpdate.jsp").forward(request, response);
			break;

		case Constant.CONTAINS_BLANK: // 空欄が含まれている
			// 更新画面へのデータ
			request.setAttribute("error", Constant.CONTAINS_BLANK);
			request.setAttribute("name", name);
			request.setAttribute("password", password);
			request.setAttribute("mail", mail);
			request.setAttribute("recogn", recogn);
			getServletContext().getRequestDispatcher("/clubUpdate.jsp").forward(request, response);
			break;
		}
	}

}
