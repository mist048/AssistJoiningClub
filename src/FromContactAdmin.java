
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Constant;
import tool.PageDataManager;

/**
 * Servlet implementation class FromContactAdmin
 */
@WebServlet("/FromContactAdmin")
public class FromContactAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PageDataManager pageDataManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FromContactAdmin() {
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
		case "decision": // 管理者問い合わせ内容確認画面へ
			int code = pageDataManager.toContactInfoConfirm(request);
			if (code == Constant.SUCCESS) {
				getServletContext().getRequestDispatcher("/contactInfoConfirm.jsp").forward(request, response);
			} else {
				getServletContext().getRequestDispatcher("/contactAdmin.jsp").forward(request, response);
			}
			break;
		}
	}

}
