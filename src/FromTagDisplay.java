
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TagManager;

/**
 * Servlet implementation class FromTop
 */
@WebServlet("/FromTagDisplay")
public class FromTagDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TagManager tagManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FromTagDisplay() {
		super();
		tagManager = new TagManager();
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

		// タグ編集画面へ
		String firstIndex = request.getParameter("firstIndex");
		if (firstIndex == null) {
			firstIndex = "0";
		}
		String[][] allTags = tagManager.getAllTags(Integer.parseInt(firstIndex));
		request.setAttribute("allTags", allTags);
		getServletContext().getRequestDispatcher("/tagEdit.jsp").forward(request, response);
	}

}
