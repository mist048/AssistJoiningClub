

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserManager;
import tool.Constant;
import tool.SHA256;

/**
 * Servlet implementation class FromClubRegistration
 */
@WebServlet("/FromUserRegistration")
public class FromUserRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserManager userManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FromUserRegistration() {
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
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String mail = request.getParameter("mail");
		// ID、パスワードをハッシュ値に変換する
		String hashId = SHA256.hash(id);
		String hashPassword = SHA256.hash(password);

		int code = userManager.register(hashId, name, hashPassword, mail); // 登録判定

		// 登録画面または登録確認画面へのデータ
		request.setAttribute("id", id);
		request.setAttribute("name", name);
		request.setAttribute("password", password);
		request.setAttribute("mail", mail);

		switch (code) {
		case Constant.SUCCESS: // 登録できる
			getServletContext().getRequestDispatcher("/userRegistrationConfirm.jsp").forward(request, response);
			break;

		case Constant.EXCEED_NUM_OF_CHAR: // 定義された文字数を超えている
			request.setAttribute("error", Constant.EXCEED_NUM_OF_CHAR);
			getServletContext().getRequestDispatcher("/userRegistration.jsp").forward(request, response);
			break;

		case Constant.CONTAINS_EX_CHAR: // 特殊な文字が含まれている
			request.setAttribute("error", Constant.CONTAINS_EX_CHAR);
			getServletContext().getRequestDispatcher("/userRegistration.jsp").forward(request, response);
			break;

		case Constant.CONTAINS_BLANK: // 空欄が含まれている
			request.setAttribute("error", Constant.CONTAINS_BLANK);
			getServletContext().getRequestDispatcher("/userRegistration.jsp").forward(request, response);
			break;

		case Constant.DUPLICATE: // 重複している
			request.setAttribute("error", Constant.DUPLICATE);
			getServletContext().getRequestDispatcher("/userRegistration.jsp").forward(request, response);
			break;

		}
	}

}