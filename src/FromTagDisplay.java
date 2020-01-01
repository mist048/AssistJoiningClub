
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
@WebServlet("/FromTagDisplay")
public class FromTagDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PageDataManager pageDataManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FromTagDisplay() {
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
		case "tagDisplay": // タグ一覧表示画面へ
			pageDataManager.toTagDisplay(request);
			getServletContext().getRequestDispatcher("/tagDisplay.jsp").forward(request, response);
			break;
			
		case "tagEdit": // タグ編集画面へ
			pageDataManager.toTagEdit(request);
			getServletContext().getRequestDispatcher("/tagEdit.jsp").forward(request, response);
			break;
		}
	}

}
