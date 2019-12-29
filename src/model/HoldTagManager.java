package model;

import java.util.ArrayList;

public class HoldTagManager {
	HoldTagDAO holdTagDAO;
	TagDAO tagDAO;

	public HoldTagManager() {
		holdTagDAO = new HoldTagDAO();
		tagDAO = new TagDAO();
	}

	public void update(String clubId, String[] addTagIds) {
		holdTagDAO.delete(clubId);
		for (String tagId : addTagIds) {
			holdTagDAO.insert(clubId, tagId);
		}
	}

	public String[] getHoldTag(String clubId) {
		HoldTag[] holdTags = holdTagDAO.getByClubID(clubId);
		String[] tagIds = new String[holdTags.length];
		for (int i = 0; i < holdTags.length; i++) {
			tagIds[i] = holdTags[i].getTagId();
		}
		return tagIds;
	}

	public String[] getHoldTagName(String tagId) {
		ArrayList<String> nameList = new ArrayList<String>();
		String[] result;
		HoldTag[] holdTags = holdTagDAO.getByTagId(tagId);
		for (HoldTag hTag : holdTags) {
			nameList.add(tagDAO.getTag(hTag.getTagId()).getName());
		}
		int size = nameList.size();
		result = new String[size];
		int i = 0;
		for (String addName : nameList) {
			result[i] = addName;
			i++;
		}
		return result;
	}
}
