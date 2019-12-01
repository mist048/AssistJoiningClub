
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ClubManager;
import tool.Constant;

/**
 * Servlet implementation class ToUserDeleteConfirm
 */
@WebServlet("/ToUserDeleteConfirm")
public class ToClubDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClubManager clubManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ToClubDelete() {
		super();
		clubManager = new ClubManager();
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
		// 削除画面へのデータ
		String[] club = clubManager.getClub(hashId);
		request.setAttribute("name", club[Constant.NAME]);

		getServletContext().getRequestDispatcher("/clubDelete.jsp").forward(request, response);
	}

}
