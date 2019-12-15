
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.PageDataManager;

/**
 * Servlet implementation class ToMyPage
 */
@WebServlet("/ToMyPage")
public class ToMyPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PageDataManager pageDataManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ToMyPage() {
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
		String user = (String) session.getAttribute("user");
		String hashId = (String) session.getAttribute("userId");

		switch (user) {
		case "general": // 一般ユーザ
			pageDataManager.toUserMyPage(request, hashId);
			getServletContext().getRequestDispatcher("/userMyPage.jsp").forward(request, response);
			break;

		case "club": // サークルアカウント
			pageDataManager.toClubMyPage(request, hashId);
			getServletContext().getRequestDispatcher("/clubMyPage.jsp").forward(request, response);
			break;
		}
	}

}
