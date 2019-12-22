package model;

import java.util.ArrayList;

public class HoldTagManager {
	HoldTagDAO holdDAO = new HoldTagDAO();
	TagDAO dao = new TagDAO();
	
	public void update(String clubId , String tagId) {
		holdDAO.delete(clubId);
		holdDAO.insert(clubId,tagId);
	}
	
	public String[] getHoldTagId(String tagId){
		ArrayList<String> idList = new ArrayList<String>();
		String[] result ;
		HoldTag[] holdTags = holdDAO.getByTagId(tagId);
		for(HoldTag hTag:holdTags) {
			idList.add(dao.getTag(hTag.getTagId()).getId());
		}
		int size = idList.size();
		result = new String[size];
		int i=0;
		for(String addId:idList) {
			result[i]=addId;
			i++;
		}
		return result;
	}
	public String[] getHoldTagName(String tagId){
		ArrayList<String> nameList = new ArrayList<String>();
		String[] result ;
		HoldTag[] holdTags = holdDAO.getByTagId(tagId);
		for(HoldTag hTag:holdTags) {
			nameList.add(dao.getTag(hTag.getTagId()).getName());
		}
		int size = nameList.size();
		result = new String[size];
		int i=0;
		for(String addName:nameList) {
			result[i]=addName;
			i++;
		}
		return result;
	}
}
