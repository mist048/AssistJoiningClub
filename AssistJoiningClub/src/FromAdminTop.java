
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

/**
 * Servlet implementation class FromTop
 */
@WebServlet("/FromAdminTop")
public class FromAdminTop extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserManager userManager;
	ClubManager clubManager;
	ClubInfoManager clubInfoManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FromAdminTop() {
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
		String option = request.getParameter("option");

		switch(option) {
		case "tagDisplay": // タグ一覧画面へ
			break;

		case "clubDisplay": // サークル情報一覧画面
			break;

		case "userDisplay": // 一般ユーザ一覧画面
			break;

		case "clubRegistration": // サークルアカウント登録画面へ
			break;
		}
	}

}
