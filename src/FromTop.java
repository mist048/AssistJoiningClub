
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.PageDataManager;

/**
 * Servlet implementation class FromTop
 */
@WebServlet("/FromTop")
public class FromTop extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PageDataManager pageDataManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FromTop() {
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
		String option = request.getParameter("option");

		if (user == null) { // 閲覧者
			switch (option) {
			case "search": // 検索結果表示画面へ
				pageDataManager.toSearchResultDisplay(request);
				getServletContext().getRequestDispatcher("/searchResultDisplay.jsp").forward(request, response);
				break;

			case "clubInfoDisplay": // サークル情報閲覧画面へ
				pageDataManager.toClubInfoDisplay(request);
				getServletContext().getRequestDispatcher("/clubInfoDisplay.jsp").forward(request, response);
				break;
			}

		} else if (user.equals("general")) { // 一般ユーザ
			String hashId = (String) session.getAttribute("userId");
			switch (option) {
			case "searchResultDisplay": // 検索結果表示画面へ
				pageDataManager.toSearchResultDisplay(request);
				getServletContext().getRequestDispatcher("/searchResultDisplay.jsp").forward(request, response);
				break;

			case "userMyPage": // 一般ユーザマイページ画面へ
				pageDataManager.toUserMyPage(request, hashId);
				getServletContext().getRequestDispatcher("/userMyPage.jsp").forward(request, response);
				break;

			case "clubInfoDisplay": // サークル情報閲覧画面へ
				pageDataManager.toClubInfoDisplay(request);
				getServletContext().getRequestDispatcher("/clubInfoDisplay.jsp").forward(request, response);
				break;
			}

		} else { // サークルアカウント
			String hashId = (String) session.getAttribute("userId");
			pageDataManager.toClubMyPage(request, hashId);
			getServletContext().getRequestDispatcher("/clubMyPage.jsp").forward(request, response);
		}
	}

}
