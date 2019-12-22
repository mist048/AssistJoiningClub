package model;

public class HoldTag {
	private String clubId;
	private String tagId;
	
	HoldTag(){
		
	}
	protected String getClubId() {
		return clubId;
	}
	
	protected void setClubId(String clubId) {
		this.clubId = clubId;
	}
	
	protected String getTagId(){
		return tagId;
	}
	
	protected void setTagId(String tagId) {
		this.tagId = tagId;
	}

}
