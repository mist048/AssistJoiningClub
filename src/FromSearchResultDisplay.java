
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.PageDataManager;

/**
 * Servlet implementation class FromSearchResultDisplay
 */
@WebServlet("/FromSearchResultDisplay")
public class FromSearchResultDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PageDataManager pageDataManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FromSearchResultDisplay() {
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
		String option = request.getParameter("option");

		switch (option) {
		case "searchResultDisplay": // 検索結果一覧画面へ
			pageDataManager.toSearchResultDisplay(request);
			getServletContext().getRequestDispatcher("/searchResultDisplay.jsp").forward(request, response);
			break;

		case "clubInfoDisplay": // サークル情報閲覧画面へ
			pageDataManager.toClubInfoDisplay(request, user, hashId);
			getServletContext().getRequestDispatcher("/clubInfoDisplay.jsp").forward(request, response);
			break;
		}
	}

}
