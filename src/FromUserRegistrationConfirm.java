
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserManager;
import tool.SHA256;

/**
 * Servlet implementation class FromClubRegistrationConfirm
 */
@WebServlet("/FromUserRegistrationConfirm")
public class FromUserRegistrationConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserManager userManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FromUserRegistrationConfirm() {
		super();
		userManager = new UserManager();
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
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String mail = request.getParameter("mail");
		// ID、パスワードをハッシュ値に変換する
		String hashId = SHA256.hash(id);
		String hashPassword = SHA256.hash(password);

		userManager.registerConfirm(hashId, name, hashPassword, mail); // 登録処理

		// ログイン
		HttpSession session = request.getSession();
		session.setAttribute("login", true);
		session.setAttribute("user", "general");
		session.setAttribute("userId", hashId);
		getServletContext().getRequestDispatcher("/accountRegistrationComplete.jsp").forward(request, response);
	}

}