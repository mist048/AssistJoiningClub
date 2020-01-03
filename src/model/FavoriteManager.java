package model;

import tool.Constant;

public class FavoriteManager {
	private FavoriteDAO favoriteDAO;
	private ClubDAO clubDAO;
	private ClubInfoDAO clubInfoDAO;

	public FavoriteManager() {
		favoriteDAO = new FavoriteDAO();
		clubDAO = new ClubDAO();
		clubInfoDAO = new ClubInfoDAO();
	}

	public String[][] getFavorite(String generalId, int firstIndex) {
		Favorite[] favorites = favoriteDAO.getByUserId(generalId, firstIndex);
		String[][] favoriteClubInfo = new String[favorites.length][Constant.NUM_OF_DISPLAY_CLUB_INFO];
		for (int i = 0; i < favorites.length; i++) {
			Club club = clubDAO.getClub(favorites[i].getClubId());
			favoriteClubInfo[i][Constant.DISPLAY_ID] = club.getId();
			favoriteClubInfo[i][Constant.DISPLAY_NAME] = club.getName();
			ClubInfo clubInfo = clubInfoDAO.getClubInfo(club.getClubInfoId());
			favoriteClubInfo[i][Constant.DISPLAY_INTRO] = clubInfo.getIntro();
			favoriteClubInfo[i][Constant.DISPLAY_ICON] = clubInfo.getIcon();
		}
		return favoriteClubInfo;
	}

	public void delete(String generalId, String clubId) {
		favoriteDAO.delete(generalId, clubId);
	}

	public void update(String generalId, String clubId) {
		if (favoriteDAO.find(generalId, clubId)) { // お気に入り登録されていれば
			favoriteDAO.delete(generalId, clubId);
		} else {
			favoriteDAO.insert(generalId, clubId);
		}
	}

	public int getNumOfPages(String generalId) {
		int count = 0;
		count = favoriteDAO.getNumOfFavorites(generalId);
		int numOfPages = count / Constant.MAX_OF_DISPLAYS;
		if (count % Constant.MAX_OF_DISPLAYS != 0) {
			numOfPages += 1;
		}
		return numOfPages;
	}

}
