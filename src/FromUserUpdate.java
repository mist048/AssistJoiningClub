
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserManager;
import tool.Constant;
import tool.SHA256;

/**
 * Servlet implementation class FromClubUpdate
 */
@WebServlet("/FromUserUpdate")
public class FromUserUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserManager userManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FromUserUpdate() {
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
		HttpSession session = request.getSession();
		String hashId = (String) session.getAttribute("userId");
		String option = request.getParameter("option");

		switch (option) {
		case "delete": // 一般ユーザ削除画面へ
			String[] general = userManager.getUser(hashId);
			request.setAttribute("name", general[Constant.NAME]);
			getServletContext().getRequestDispatcher("/accountDelete.jsp").forward(request, response);
			break;

		case "confirm": // 更新処理
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String mail = request.getParameter("mail");
			// ID、パスワードをハッシュ値に変換する
			String hashPassword = SHA256.hash(password);

			int code = userManager.update(hashId, name, hashPassword, mail); // 更新判定
			if (code == Constant.SUCCESS) { // 更新できる
				general = userManager.getUser(hashId);
				request.setAttribute("name", general[Constant.NAME]);
				request.setAttribute("mail", general[Constant.MAIL]);
				String user = (String) session.getAttribute("user");
				if (user.equals("general")) { // 一般ユーザ
					userManager.updateConfirm(hashId, name, hashPassword, mail); // 更新処理
					getServletContext().getRequestDispatcher("/userMyPage.jsp").forward(request, response);

				} else { // 管理者
					String generalId = request.getParameter("generalId");
					userManager.updateConfirm(generalId, name, hashPassword, mail); // 更新処理
					request.setAttribute("generalId", generalId);
					getServletContext().getRequestDispatcher("/userInfoDisplayForAdmin.jsp").forward(request, response);
				}

			} else if (code == Constant.EXCEED_NUM_OF_CHAR) { // 定義された文字数を超えている
				// 更新画面へのデータ
				String user = (String) session.getAttribute("user");
				if (user.equals("admin")) { // 管理者
					String generalId = request.getParameter("generalId");
					request.setAttribute("generalId", generalId);
				}
				request.setAttribute("error", Constant.EXCEED_NUM_OF_CHAR);
				request.setAttribute("name", name);
				request.setAttribute("password", password);
				request.setAttribute("mail", mail);
				getServletContext().getRequestDispatcher("/userUpdate.jsp").forward(request, response);

			} else if (code == Constant.CONTAINS_EX_CHAR) { // 特殊な文字が含まれている
				// 更新画面へのデータ
				String user = (String) session.getAttribute("user");
				if (user.equals("admin")) { // 管理者
					String generalId = request.getParameter("generalId");
					request.setAttribute("generalId", generalId);
				}
				request.setAttribute("error", Constant.CONTAINS_EX_CHAR);
				request.setAttribute("name", name);
				request.setAttribute("password", password);
				request.setAttribute("mail", mail);
				getServletContext().getRequestDispatcher("/userUpdate.jsp").forward(request, response);

			} else { // 空欄が含まれている
				// 更新画面へのデータ
				String user = (String) session.getAttribute("user");
				if (user.equals("admin")) { // 管理者
					String generalId = request.getParameter("generalId");
					request.setAttribute("generalId", generalId);
				}
				request.setAttribute("error", Constant.CONTAINS_BLANK);
				request.setAttribute("name", name);
				request.setAttribute("password", password);
				request.setAttribute("mail", mail);
				getServletContext().getRequestDispatcher("/userUpdate.jsp").forward(request, response);
			}
			break;
		}
	}

}