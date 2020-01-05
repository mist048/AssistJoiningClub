
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.PageDataManager;

/**
 * Servlet implementation class FromTop
 */
@WebServlet("/FromTop")
public class FromTop extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PageDataManager pageDataManager;

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
		String option = request.getParameter("option");

		switch (option) {
		case "searchResultDisplay": // 検索結果表示画面へ
			pageDataManager.toSearchResultDisplay(request);
			getServletContext().getRequestDispatcher("/searchResultDisplay.jsp").forward(request, response);
			break;

		case "clubInfoDisplay": // サークル情報閲覧画面へ
			pageDataManager.toClubInfoDisplay(request);
			getServletContext().getRequestDispatcher("/clubInfoDisplay.jsp").forward(request, response);
			break;

		case "contactAdmin": // 管理者問い合わせ画面へ
			pageDataManager.toContactAdmin(request);
			getServletContext().getRequestDispatcher("/contactAdmin.jsp").forward(request, response);
			break;
		}
	}

}
