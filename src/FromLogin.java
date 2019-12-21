
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.PageDataManager;

/**
 * Servlet implementation class FromLogin
 */
@WebServlet("/FromLogin")
public class FromLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PageDataManager pageDataManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FromLogin() {
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
		String user = request.getParameter("user");
		System.out.print(user);

		switch (option) {
		case "login": // ログイン処理
			boolean result = pageDataManager.login(session, request, user);
			if (result) { // ログイン成功
				if (user.equals("general")) { // 一般ユーザ
					getServletContext().getRequestDispatcher("/userMyPage.jsp").forward(request, response);
				} else if (user.equals("club")) { // サークルアカウント
					getServletContext().getRequestDispatcher("/clubMyPage.jsp").forward(request, response);
				} else if (user.equals("admin")) { // 管理者
					getServletContext().getRequestDispatcher("/adminTop.jsp").forward(request, response);
				}
			} else { // ログイン失敗
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			}
			break;
		}
	}

}
