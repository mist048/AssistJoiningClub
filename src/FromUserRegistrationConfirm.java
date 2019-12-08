
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.PageDataManager;
import tool.SHA256;

/**
 * Servlet implementation class FromClubRegistrationConfirm
 */
@WebServlet("/FromUserRegistrationConfirm")
public class FromUserRegistrationConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PageDataManager pageDataManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FromUserRegistrationConfirm() {
		super();
		pageDataManager = PageDataManager.getInstance();
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
		String option = request.getParameter("option");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		// ID、パスワードをハッシュ値に変換する
		String hashId = SHA256.hash(id);
		String hashPassword = SHA256.hash(password);

		switch (option) {
		case "register": // 登録処理
			pageDataManager.userRegistrationConfirm(request);
			pageDataManager.login(session, request, "general", hashId, hashPassword);
			getServletContext().getRequestDispatcher("/accountRegistrationComplete.jsp").forward(request, response);
			break;

		case "top": // トップ画面へ
			pageDataManager.toTop(request);
			getServletContext().getRequestDispatcher("/viewerTop.jsp").forward(request, response);
			break;
		}
	}

}