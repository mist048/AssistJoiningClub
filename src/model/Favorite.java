package model;

public class Favorite {
	private String userId;
	private String clubId;

	protected String getUserId() {
		return userId;
	}

	protected void setUserId(String userId) {
		this.userId=userId;
	}

	protected String getClubId() {
		return clubId;
	}

	protected void setClubId(String clubId) {
		this.clubId=clubId;
	}
}
