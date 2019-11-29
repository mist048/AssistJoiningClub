
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/ToTop")
public class ToTop extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ToTop() {
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
		HttpSession session = request.getSession();

		if (session.getAttribute("login") == null) { // セッションが破棄されている場合
			getServletContext().getRequestDispatcher("/viewerTop.jsp").forward(request, response);
		} else { // ログインしている場合
			String user = (String) session.getAttribute("user");
			switch (user) {
			case "gengeral": // 一般ユーザ
				getServletContext().getRequestDispatcher("/generalTop.jsp").forward(request, response);
				break;
			case "club": // サークルアカウント
				getServletContext().getRequestDispatcher("/clubTop.jsp").forward(request, response);
				break;
			case "admin": // 管理者
				getServletContext().getRequestDispatcher("/adminTop.jsp").forward(request, response);
				break;
			}
		}
	}
}
