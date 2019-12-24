
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.PageDataManager;

/**
 * Servlet implementation class FromUserDisplay
 */
@WebServlet("/FromUserInfoDisplayForAdmin")
public class FromUserInfoDisplayForAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PageDataManager pageDataManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FromUserInfoDisplayForAdmin() {
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
		String generalId = request.getParameter("generalId");
		String option = request.getParameter("option");

		switch (option) {
		case "setting": // 一般ユーザ更新画面へ
			pageDataManager.toUserUpdate(request, generalId, -1);
			getServletContext().getRequestDispatcher("/userUpdate.jsp").forward(request, response);
			break;
		}
	}

}
