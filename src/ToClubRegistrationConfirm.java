
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ClubManager;
import tool.Constant;
import tool.SHA256;

/**
 * Servlet implementation class ToClubRegistrationConfirm
 */
@WebServlet("/ToClubRegistrationConfirm")
public class ToClubRegistrationConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClubManager clubManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ToClubRegistrationConfirm() {
		super();
		clubManager = new ClubManager();
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
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String mail = request.getParameter("mail");
		String recogn = request.getParameter("recogn");
		// ID、パスワードをハッシュ値に変換する
		String hashId = SHA256.hash(id);
		String hashPassword = SHA256.hash(password);

		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("user");
		int code;
		if (user == null) { // 閲覧者
			code = clubManager.register(Constant.CLUB, hashId, name, hashPassword, mail);
			if (code == Constant.SUCCESS) { // 登録できる
				getServletContext().getRequestDispatcher("/clubRegistrationConfirm.jsp").forward(request, response);
			} else if (code == Constant.EXCEED_NUM_OF_CHAR) { // 定義された文字数を超えている
				request.setAttribute("error", Constant.EXCEED_NUM_OF_CHAR);
				getServletContext().getRequestDispatcher("/clubRegistration.jsp").forward(request, response);
			} else if (code == Constant.CONTAINS_EX_CHAR) { // 特殊な文字が含まれている
				request.setAttribute("error", Constant.CONTAINS_EX_CHAR);
				getServletContext().getRequestDispatcher("/clubRegistration.jsp").forward(request, response);
			} else { // 空欄が含まれている
				request.setAttribute("error", Constant.CONTAINS_BLANK);
				getServletContext().getRequestDispatcher("/clubRegistration.jsp").forward(request, response);
			}
		} else if (user.equals("admin")) { // 管理者
			code = clubManager.register(Constant.ADMIN, "", name, "", mail);
			if (code == Constant.SUCCESS) { // 登録できる
				getServletContext().getRequestDispatcher("/clubRegistrationConfirm.jsp").forward(request, response);
			} else if (code == Constant.EXCEED_NUM_OF_CHAR) { // 定義された文字数を超えている
				request.setAttribute("error", Constant.EXCEED_NUM_OF_CHAR);
				getServletContext().getRequestDispatcher("/clubRegistration.jsp").forward(request, response);
			} else if (code == Constant.CONTAINS_EX_CHAR) { // 特殊な文字が含まれている
				request.setAttribute("error", Constant.CONTAINS_EX_CHAR);
				getServletContext().getRequestDispatcher("/clubRegistration.jsp").forward(request, response);
			} else { // 空欄が含まれている
				request.setAttribute("error", Constant.CONTAINS_BLANK);
				getServletContext().getRequestDispatcher("/clubRegistration.jsp").forward(request, response);
			}
		}
	}

}
