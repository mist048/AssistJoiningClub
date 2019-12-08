
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.PageDataManager;

/**
 * Servlet implementation class FromClubMypage
 */
@WebServlet("/FromUserMyPage")
public class FromUserMyPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PageDataManager pageDataManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FromUserMyPage() {
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
		String hashId = (String) session.getAttribute("userId");
		String option = request.getParameter("option");

		switch (option) {
		case "setting": // 一般ユーザ更新画面へ
			pageDataManager.toUserUpdate(request, hashId, -1); // -1は入力によるエラーがないとき
			getServletContext().getRequestDispatcher("/userUpdate.jsp").forward(request, response);
			break;

		case "favoriteClubDisplay": // お気に入りサークル一覧表示画面へ
			pageDataManager.toFavoriteClubDisplay(request, hashId);
			getServletContext().getRequestDispatcher("/favoriteClubDisplay.jsp").forward(request, response);
			break;

		case "top": // トップ画面へ
			pageDataManager.toTop(request);
			getServletContext().getRequestDispatcher("/generalTop.jsp").forward(request, response);
			break;
		}
	}

}
