
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.PageDataManager;

/**
 * Servlet implementation class FromAccountDeleteConfirm
 */
@WebServlet("/FromAccountDeleteConfirm")
public class FromAccountDeleteConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PageDataManager pageDataManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FromAccountDeleteConfirm() {
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
		case "delete": // 削除処理
			if (user.equals("general") || user.equals("club")) { // 一般ユーザかサークルアカウント
				boolean result = pageDataManager.accountDelete(request, user, hashId); // 削除処理
				if (result) { // 削除成功
					pageDataManager.toTop(request);
					getServletContext().getRequestDispatcher("/viewerTop.jsp").forward(request, response);
				} else { // 削除失敗
					pageDataManager.toAccountDeleteConfirm(request, user, null, null);
					getServletContext().getRequestDispatcher("/accountDeleteConfirm.jsp").forward(request, response);
				}
			} else if (user.equals("admin")) { // 管理者
				String deletedUser = request.getParameter("deletedUser"); // 削除される対象
				String deletedId = request.getParameter("deletedId");
				boolean result = pageDataManager.accountDelete(request, user, deletedId); // 削除処理
				if (result) { // 削除成功
					if (deletedUser.equals("general")) { // 一般ユーザが対象
						pageDataManager.toUserDisplay(request);
						getServletContext().getRequestDispatcher("/userDisplay.jsp").forward(request, response);
					} else if (deletedUser.equals("club")) { // サークルアカウントが対象
						pageDataManager.toClubDisplay(request);
						getServletContext().getRequestDispatcher("/clubDisplay.jsp").forward(request, response);
					}
				} else { // 削除失敗
					pageDataManager.toAccountDeleteConfirm(request, user, deletedUser, deletedId);
					getServletContext().getRequestDispatcher("/accountDeleteConfirm.jsp").forward(request, response);
				}
			}
			break;
		}
	}

}