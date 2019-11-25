
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	// private ClubManager clubManager;
	// private ClubInfoManager clubInfoManager;
	// private TagManager tagManager;
	// private HoldTagManager holdTagManager;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// clubManager=new ClubManager();
		// clubInfoManager=new ClubInfoManager();
		// tagManager=new TagManager();
		// holdTagManager=new HoldTagManager();
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
		String prevScene = request.getParameter("prevScene");
		String option = request.getParameter("option");

		switch (prevScene) {
		case "top": // 前の画面がトップ画面
			if (option.equals("toLogin")) {
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			}
			break;

		case "login": // 前の画面がログイン画面
			if (option.equals("login")) {
				String id = request.getParameter("id");
				String password = request.getParameter("password");

				// ID、パスワードをハッシュ値に変換する
				String hashId = hash(id);
				String hashPassword = hash(password);
				/*
				 * String code=clubManager.login(hashId,hashPassword);
				 * if(code==SUCCESS){ // ログイン成功
				 * 	HttpSession session=request.getSession();
				 * 	session.setAttribute("login", true);
				 * 	session.setAttribute("user", hashId);
				 * 	getServletContext().getRequestDispatcher("/loginConfirm.jsp").forward(request, response);
				 * }else if(code==EXCEED_NUM_OF_CHAR){ // 定義された文字数を超えている
				 * 	session.setAttribute("error", EXCEED_NUM_OF_CHAR);
				 * 	getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				 * }else if(code==CONTAINS_EX_CHAR){ // 特殊な文字が含まれている
				 * 	session.setAttribute("error", CONTAINS_EX_CHAR);
				 * 	getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				 * }else{ // 空欄が含まれている
				 * 	session.setAttribute("error", CONTAINS_BLANK);
				 * 	getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				 * }
				 */
			}
			break;
		}
	}

	// SHA-256を用いたハッシュ化
	private static String hash(String str) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hashValue = digest.digest(str.getBytes());
			StringBuilder sb = new StringBuilder();
			for (byte b : hashValue) {
				sb.append(String.format("%02x", b));
			}
			String hashValueStr = sb.toString();
			return hashValueStr;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

}
