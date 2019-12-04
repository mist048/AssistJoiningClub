
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ClubInfoManager;
import model.ClubManager;
import tool.Constant;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/ToTop")
public class ToTop extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClubManager clubManager;
	ClubInfoManager clubInfoManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ToTop() {
		super();
		clubManager=new ClubManager();
		clubInfoManager=new ClubInfoManager();
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
		String firstIndex=request.getParameter("firstIndex");
		if(firstIndex==null) { // 最初のアクセスなら
			firstIndex="0";
		}
		String[][] allClubs = clubManager.getAllClubs(Integer.parseInt(firstIndex)); // サークルアカウント情報をfirstIndexから10件取得
		String[][] allClubInfo=new String[allClubs.length][3]; // 閲覧用サークル情報
		for(int i=0;i<allClubs.length;i++) {
			allClubInfo[i][Constant.ID]=allClubs[i][Constant.ID];
			allClubInfo[i][Constant.NAME]=allClubs[i][Constant.NAME];
			String[] clubInfo = clubInfoManager.getClubInfo(allClubs[i][Constant.CLUB_INFO_ID]);
			allClubInfo[i][2]=clubInfo[Constant.INTRO];
		}
		request.setAttribute("clubs", allClubInfo);

		if (session.getAttribute("login") == null) { // セッションが破棄されている場合
			getServletContext().getRequestDispatcher("/viewerTop.jsp").forward(request, response);
		} else { // ログインしている場合
			String user = (String) session.getAttribute("user");
			switch (user) {
			case "gengeral": // 一般ユーザ
				getServletContext().getRequestDispatcher("/generalTop.jsp").forward(request, response);
				break;
			case "club": // サークルアカウント
				getServletContext().getRequestDispatcher("/clubTop.jsp").forward(request, response);
				break;
			case "admin": // 管理者
				getServletContext().getRequestDispatcher("/adminTop.jsp").forward(request, response);
				break;
			}
		}
	}
}
