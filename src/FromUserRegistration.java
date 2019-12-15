
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Constant;
import tool.PageDataManager;

/**
 * Servlet implementation class FromClubRegistration
 */
@WebServlet("/FromUserRegistration")
public class FromUserRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PageDataManager pageDataManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FromUserRegistration() {
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
		case "confirm": // 登録判定
			int code = -1;
			// 更新判定
			code = pageDataManager.userRegistration(request);

			if (code == Constant.SUCCESS) { // 更新できる
				pageDataManager.toUserRegistrationConfirm(request);
				getServletContext().getRequestDispatcher("/userRegistrationConfirm.jsp").forward(request, response);
			} else { // エラーがある
				pageDataManager.toUserRegistration(request, code);
				getServletContext().getRequestDispatcher("/userRegistration.jsp").forward(request, response);
			}
			break;
		}
	}

}