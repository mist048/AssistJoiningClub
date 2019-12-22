package model;

import tool.Constant;

public class TagManager {
	private TagDAO tagDAO;
	private HoldTagDAO holdTagDAO;
	
	public TagManager() {
		tagDAO=new TagDAO();
		holdTagDAO=new HoldTagDAO();
	}

	public boolean register(String[] name) {
		for (String str : name) {
			if (tagDAO.findByName(str)) {
				return false;
			}
			tagDAO.insert(str);
		}
		return true;
	}

	public boolean update(String[] id, String[] name) {
		for (int i = 0; i < name.length; i++) {
			if (tagDAO.findByName(name[i])) {
				tagDAO.update(id[i], name[i]);
				return true;
			}
		}
		return false;
	}

	public void delete(String[] id) {
		for (String n : id) {
			tagDAO.delete(n);
			holdTagDAO.deleteByTagId(n);
		}

	}

	public String[][] getAllTags(int firstIndex) {
		Tag[] tags = tagDAO.getAllTags(firstIndex);
		String[][] tagsArray = new String[tags.length][Constant.NUM_OF_TAG_FIELD];
		for (int i = 0; i < tags.length; i++) {
			tagsArray[i][Constant.ID] = tags[i].getId();
			tagsArray[i][Constant.NAME] = tags[i].getName();
		}
		return tagsArray;
	}

	public String[] getAllIds() {
		return tagDAO.getAllIds();
	}
}
