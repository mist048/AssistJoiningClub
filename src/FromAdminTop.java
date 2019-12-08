
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
@WebServlet("/FromAdminTop")
public class FromAdminTop extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PageDataManager pageDataManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FromAdminTop() {
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
		case "tagDisplay": // タグ一覧画面へ
			pageDataManager.toTagDisplay(request);
			getServletContext().getRequestDispatcher("/tagDisplay.jsp").forward(request, response);
			break;
			
		case "userDisplay": // 一般ユーザ一覧画面
			pageDataManager.toUserDisplay(request);
			getServletContext().getRequestDispatcher("/userDisplay.jsp").forward(request, response);
			break;

		case "clubDisplay": // サークル情報一覧画面
			pageDataManager.toClubDisplay(request);
			getServletContext().getRequestDispatcher("/clubDisplay.jsp").forward(request, response);
			break;
		}
	}

}
