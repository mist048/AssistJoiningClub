
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Constant;
import tool.PageDataManager;

/**
 * Servlet implementation class FromClubUpdate
 */
@WebServlet("/FromClubUpdate")
public class FromClubUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PageDataManager pageDataManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FromClubUpdate() {
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
		case "delete": // サークルアカウント削除画面へ
			if (user.equals("club")) { // サークルアカウント
				pageDataManager.toAccountDelete(request, user, null, hashId);
			} else if (user.equals("admin")) { // 管理者
				String clubId = request.getParameter("id"); // 削除されるID
				pageDataManager.toAccountDelete(request, user, "club", clubId);
			}
			getServletContext().getRequestDispatcher("/accountDelete.jsp").forward(request, response);
			break;

		case "confirm": // 更新処理
			int code = -1;
			// 更新判定
			if (user.equals("club")) { // サークルアカウント
				code = pageDataManager.clubUpdate(request, hashId);
			} else if (user.equals("admin")) { // 管理者
				String clubId = request.getParameter("id"); // 更新されるID
				code = pageDataManager.clubUpdate(request, clubId);
			}

			if (code == Constant.SUCCESS) { // 更新できる
				if (user.equals("club")) { // サークルアカウント
					pageDataManager.clubUpdateConfirm(request, user, hashId); // 更新処理
					pageDataManager.toClubMyPage(request, hashId);
					getServletContext().getRequestDispatcher("/clubMyPage.jsp").forward(request, response);
				} else if (user.equals("admin")) { // 管理者
					String clubId = request.getParameter("id");
					pageDataManager.clubUpdateConfirm(request, user, clubId); // 更新処理
					pageDataManager.toClubInfoDisplayForAdmin(request);
					getServletContext().getRequestDispatcher("/clubInfoDisplayForAdmin.jsp").forward(request, response);
				}
			} else { // エラーがある
				if (user.equals("club")) { // サークルアカウント
					pageDataManager.toClubUpdate(request, hashId, code);
				} else if (user.equals("admin")) { // 管理者
					String clubId = request.getParameter("clubId");
					pageDataManager.toClubUpdate(request, clubId, code);
				}
				getServletContext().getRequestDispatcher("/clubUpdate.jsp").forward(request, response);
			}
			break;
		}
	}

}