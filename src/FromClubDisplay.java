
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.PageDataManager;

/**
 * Servlet implementation class FromUserDisplay
 */
@WebServlet("/FromClubDisplay")
public class FromClubDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PageDataManager pageDataManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FromClubDisplay() {
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
		case "clubDisplay": // サークル情報一覧画面へ
			pageDataManager.toClubDisplay(request);
			getServletContext().getRequestDispatcher("/clubDisplay.jsp").forward(request, response);
			break;

		case "clubInfoDisplayForAdmin": // サークルアカウント管理者閲覧用画面へ
			pageDataManager.toClubInfoDisplayForAdmin(request);
			getServletContext().getRequestDispatcher("/clubInfoDisplayForAdmin.jsp").forward(request, response);
			break;
		}
	}

}
