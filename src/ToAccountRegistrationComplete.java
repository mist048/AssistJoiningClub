

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
 * Servlet implementation class ToAccountRegistrationComplete
 */
@WebServlet("/ToAccountRegistrationComplete")
public class ToAccountRegistrationComplete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClubManager clubManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ToAccountRegistrationComplete() {
		super();
		clubManager = new ClubManager();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String mail = request.getParameter("mail");
		// ID、パスワードをハッシュ値に変換する
		String hashId = SHA256.hash(id);
		String hashPassword = SHA256.hash(password);

		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("user");
		if(user==null) { // 閲覧者
			clubManager.registerConfirm(Constant.VIEWER, hashId, name, hashPassword, mail); // 登録処理
		}else { // 管理者
			clubManager.registerConfirm(Constant.ADMIN, "", name, "", mail); // 登録処理
		}

		getServletContext().getRequestDispatcher("/accountRegistrationComplete.jsp").forward(request, response);
	}

}
