
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class FromLogout
 */
@WebServlet("/FromLogout")
public class FromLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FromLogout() {
		super();
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
		HttpSession session=request.getSession();
		String option = request.getParameter("option");
		switch (option) {
		case "yes": // ログアウトする
			// セッションを破棄する
			session.invalidate();
			getServletContext().getRequestDispatcher("/viewerTop.jsp").forward(request, response);
			break;

		case "no": // ログアウトしない
			String user = (String) session.getAttribute("user");
			if (user.equals("general")) { // 一般ユーザ
				getServletContext().getRequestDispatcher("/generalTop.jsp").forward(request, response);

			} else if (user.equals("club")) { // サークルアカウント
				getServletContext().getRequestDispatcher("/clubTop.jsp").forward(request, response);

			} else { // 管理者
				getServletContext().getRequestDispatcher("/adminTop.jsp").forward(request, response);
			}
			break;
		}
	}

}
