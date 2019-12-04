
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserManager;
import tool.Constant;

/**
 * Servlet implementation class FromUserDisplay
 */
@WebServlet("/FromUserDisplay")
public class FromUserDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserManager userManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FromUserDisplay() {
		super();
		userManager = new UserManager();
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
		// 一般ユーザ情報管理者閲覧画面へ
		String generalId = request.getParameter("generalId");
		String[] general = userManager.getUser(generalId);
		request.setAttribute("id", general[Constant.ID]);
		request.setAttribute("name", general[Constant.NAME]);
		request.setAttribute("mail", general[Constant.MAIL]);
		getServletContext().getRequestDispatcher("/userInfoDisplayForAdmin.jsp").forward(request, response);
	}

}
