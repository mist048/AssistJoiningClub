
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
	PageDataManager pageDataManager;

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
		String option = request.getParameter("option");

		switch (option) {
		case "clubInfoDisplay": // サークル情報閲覧画面へ
			pageDataManager.toClubInfoDisplay(request);
			getServletContext().getRequestDispatcher("/clubInfoDisplay.jsp").forward(request, response);
			break;

		case "myPage": // マイページへ
			String hashId = (String) session.getAttribute("userId");
			pageDataManager.toUserMyPage(request, hashId);
			getServletContext().getRequestDispatcher("/userMyPage.jsp").forward(request, response);
			break;

		case "top": // トップ画面へ
			pageDataManager.toTop(request);
			String user = (String) session.getAttribute("user");
			if (user == null) { // 閲覧者
				getServletContext().getRequestDispatcher("/viewerTop.jsp").forward(request, response);
			} else if (user.equals("general")) { // 一般ユーザ
				getServletContext().getRequestDispatcher("/generalTop.jsp").forward(request, response);
			}
			break;
		}
	}

}
