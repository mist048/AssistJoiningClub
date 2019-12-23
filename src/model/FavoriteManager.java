package model;

public class FavoriteManager {
	//private FavoriteDAO favoriteDAO;
	private ClubDAO clubDAO;

	public FavoriteManager() {
		//favoriteDAO = FavoriteDAO();
		clubDAO = new ClubDAO();
	}

	public String[][] getFavorite(String generalId) {
		/*Favorite[] favorites = favoriteDAO.getByUserId(generalId);
		String[][] clubInfo = new String[favorite.length][Constant.NUM_OF_DISPLAY_FAVORITE_INFO];
		for (int i = 0; i < favorites.length; i++) {
			Club club = clubDAO.getClub(favorites[i].getClubId);
			clubInfo[i][Constant.ID] = club.getId();
			clubInfo[i][Constant.NAME] = club.getName();
		}
		return clubInfo;*/
		return new String[][] { { "id", "name" }, { "id", "name" } };
	}

	public void delete(String generalId, String clubId) {
		//favoriteDAO.delete(generalId,clubId);
	}

	public void update(String generalId, String clubId) {
		/*if (favoriteDAO.find(generalId, clubId)) { // お気に入り登録されていれば
			favoriteDAO.delete(generalId, clubId);
		} else {
			favoriteDAO.insert(generalId, clubId);
		}*/
	}

}
