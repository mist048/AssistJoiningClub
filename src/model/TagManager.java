package model;

import java.util.ArrayList;

import tool.Constant;
import tool.SHA256;

public class TagManager {
	private TagDAO tagDAO;
	private HoldTagDAO holdTagDAO;

	public TagManager() {
		tagDAO = new TagDAO();
		holdTagDAO = new HoldTagDAO();
	}

	public void register(String[] names) {
		for (String name : names) {
			if (!tagDAO.findByName(name)) {
				int number = 0;
				String id = null;
				while (true) {
					id = SHA256.hash(String.valueOf(number));
					if (!tagDAO.findById(id)) { // タグIDが重複していなければ
						break;
					}
					number++;
				}
				tagDAO.insert(id, name);
			}
		}
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

	public String[] getByNames(String[] tagNames) {
		ArrayList<String> tagIds = new ArrayList<String>();
		for (String tagName : tagNames) {
			Tag tag = (tagDAO.getByName(tagName));
			tagIds.add(tag.getId());
		}
		return tagIds.toArray(new String[tagIds.size()]);
	}

	public int getNumOfPages() {
		int count = 0;
		count = tagDAO.getNumOfTags();
		int numOfPages = count / Constant.MAX_OF_DISPLAYS;
		if (count % Constant.MAX_OF_DISPLAYS != 0) {
			numOfPages += 1;
		}
		return numOfPages;
	}

}
