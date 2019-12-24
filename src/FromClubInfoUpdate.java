
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import tool.FileHandle;
import tool.PageDataManager;

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
		case "add": // タグ追加処理
			pageDataManager.toClubInfoUpdate(request, hashId);
			getServletContext().getRequestDispatcher("/clubInfoUpdate.jsp").forward(request, response);
			break;

		case "confirm": // サークル情報更新処理
			// 画像アップロード処理
			Part part;
			part = request.getPart("icon");
			String icon = fileHandle.getFileName(part);
			part.write(getServletContext().getRealPath("./images") + "/" + icon);
			part = request.getPart("home");
			String home = fileHandle.getFileName(part);
			part.write(getServletContext().getRealPath("./images") + "/" + home);

			boolean result = pageDataManager.clubInfoUpdate(request, hashId, icon, home);
			if (result) { // 更新できる
				pageDataManager.toClubMyPage(request, hashId);
				getServletContext().getRequestDispatcher("/clubMyPage.jsp").forward(request, response);
			} else { // エラーがある
				pageDataManager.toClubInfoUpdate(request, hashId);
				getServletContext().getRequestDispatcher("/clubInfoUpdate.jsp").forward(request, response);
			}
			break;
		}
	}

}
