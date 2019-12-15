
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
@WebServlet("/FromUserUpdate")
public class FromUserUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PageDataManager pageDataManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FromUserUpdate() {
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
		case "delete": // 一般ユーザ削除画面へ
			if (user.equals("general")) { // 一般ユーザ
				pageDataManager.toAccountDelete(request, user, null, hashId);
			} else if (user.equals("admin")) { // 管理者
				String generalId = request.getParameter("generalId"); // 削除されるID
				pageDataManager.toAccountDelete(request, user, "general", generalId);
			}
			getServletContext().getRequestDispatcher("/accountDelete.jsp").forward(request, response);
			break;

		case "confirm": // 更新処理
			int code = -1;
			// 更新判定
			if (user.equals("general")) { // 一般ユーザ
				code = pageDataManager.userUpdate(request, hashId);
			} else if (user.equals("admin")) { // 管理者
				String generalId = request.getParameter("generalId"); // 更新されるID
				code = pageDataManager.userUpdate(request, generalId);
			}

			if (code == Constant.SUCCESS) { // 更新できる
				if (user.equals("general")) { // サークルアカウント
					pageDataManager.userUpdateConfirm(request, hashId); // 更新処理
					pageDataManager.toUserMyPage(request, hashId);
					getServletContext().getRequestDispatcher("/userMyPage.jsp").forward(request, response);
				} else { // 管理者
					String generalId = request.getParameter("generalId");
					pageDataManager.userUpdateConfirm(request, generalId); // 更新処理
					pageDataManager.toUserInfoDisplayForAdmin(request);
					getServletContext().getRequestDispatcher("/userInfoDisplayForAdmin.jsp").forward(request, response);
				}
			} else { // エラーがある
				if (user.equals("general")) { // 一般ユーザ
					pageDataManager.toUserUpdate(request, hashId, code);
				} else if (user.equals("admin")) { // 管理者
					String generalId = request.getParameter("generalId");
					pageDataManager.toUserUpdate(request, generalId, code);
				}
				getServletContext().getRequestDispatcher("/userUpdate.jsp").forward(request, response);
			}
			break;
		}
	}

}