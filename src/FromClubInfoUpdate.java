
import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import tool.*;

/**
 * Servlet implementation class FromClubMypage
 */
@WebServlet("/FromClubInfoUpdate")
public class FromClubInfoUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PageDataManager pageDataManager;
	private FileHandle fileHandle;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FromClubInfoUpdate() {
		super();
		pageDataManager = PageDataManager.getInstance();
		fileHandle = FileHandle.getInstance();
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
		String hashId = (String) session.getAttribute("userId");
		String option = request.getParameter("option");

		switch (option) {
		case "confirm": // サークル情報更新処理
			boolean result = pageDataManager.clubInfoUpdate(request, hashId);
			if (result) { // 更新できる
				pageDataManager.toClubMyPage(request, hashId);
				getServletContext().getRequestDispatcher("/clubMyPage.jsp").forward(request, response);
			} else { // エラーがある
				pageDataManager.toClubInfoUpdate(request, hashId, -1);
				getServletContext().getRequestDispatcher("/clubInfoUpdate.jsp").forward(request, response);
			}
			break;
		}
	}

}
