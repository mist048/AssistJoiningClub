
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
 * Servlet implementation class FromClubRegistration
 */
@WebServlet("/FromClubRegistration")
public class FromClubRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PageDataManager pageDataManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FromClubRegistration() {
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
		String option = request.getParameter("option");

		switch (option) {
		case "confirm": // 登録判定
			int code = -1;
			// 更新判定
			code = pageDataManager.clubRegistration(request, user);

			if (code == Constant.SUCCESS) { // 更新できる
				pageDataManager.toClubRegistrationConfirm(request);
				getServletContext().getRequestDispatcher("/clubRegistrationConfirm.jsp").forward(request, response);
			} else { // エラーがある
				pageDataManager.toClubRegistration(request, code);
				getServletContext().getRequestDispatcher("/clubRegistration.jsp").forward(request, response);
			}
			break;

		case "top": // トップ画面へ
			pageDataManager.toTop(request);
			if (user == null) { // 閲覧者
				getServletContext().getRequestDispatcher("/viewerTop.jsp").forward(request, response);
			} else if (user.equals("admin")) { // 管理者
				getServletContext().getRequestDispatcher("/adminTop.jsp").forward(request, response);
			}
			break;
		}
	}

}