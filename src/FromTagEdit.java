
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
@WebServlet("/FromTagEdit")
public class FromTagEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PageDataManager pageDataManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FromTagEdit() {
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
		case "update": // 更新処理
			// これから作成
			pageDataManager.toTagEdit(request);
			getServletContext().getRequestDispatcher("/tagEdit.jsp").forward(request, response);
			break;

		case "delete": // 削除処理
			// これから作成
			pageDataManager.toTagEdit(request);
			getServletContext().getRequestDispatcher("/tagEdit.jsp").forward(request, response);
			break;

		case "save": // タグ一覧表示画面へ
			// これから作成
			pageDataManager.toTagDisplay(request);
			getServletContext().getRequestDispatcher("/tagDisplay.jsp").forward(request, response);
			break;
		}
	}

}
