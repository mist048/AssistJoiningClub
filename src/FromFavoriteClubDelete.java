

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.FavoriteManager;

/**
 * Servlet implementation class FromFavoriteClubDelete
 */
@WebServlet("/FromFavoriteClubDelete")
public class FromFavoriteClubDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FavoriteManager favoriteManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FromFavoriteClubDelete() {
		super();
		favoriteManager = new FavoriteManager();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String hashId=(String)session.getAttribute("userId");
		String[] clubIds=request.getParameterValues("clubId");
		for(String clubId:clubIds) {
			favoriteManager.delete(hashId,clubId); // お気に入りサークルを削除
		}
		getServletContext().getRequestDispatcher("/favoriteClubDisplay.jsp").forward(request, response);
	}

}
