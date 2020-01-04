
import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import tool.*;

/**
 * Servlet implementation class FromClubMypage
 */
@WebServlet("/FromClubMyPage")
public class FromClubMyPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PageDataManager pageDataManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FromClubMyPage() {
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
		case "setting": // サークルアカウント更新画面へ
			pageDataManager.toClubUpdate(request, hashId, -1); // -1は入力によるエラーがないとき
			getServletContext().getRequestDispatcher("/clubUpdate.jsp").forward(request, response);
			break;

		case "edit": // サークル情報更新画面へ
			pageDataManager.toClubInfoUpdate(request, hashId, -1);
			getServletContext().getRequestDispatcher("/clubInfoUpdate.jsp").forward(request, response);
			break;
		}
	}

}
