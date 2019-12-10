package last;

public class TagManager {
	Tag tag;
	TagDAO dao;
	HoldTagDAO hold;
	public boolean register(String[] name) {
		for(String str : name) {
			if(dao.findByName(str)) {
				return false;
			}
			dao.insert(str);
		}
		return true;
	}

	public boolean update(String[] id ,String[] name) {
		for(int i=0; i<name.length; i++) {
			if(dao.findByName(name[i])) {
				dao.update(id[i], name[i]);
				return true;
			}
		}
		return false;
	}
	public void delete(String[] id) {
		for(String n : id) {
			dao.delete(n);
			hold.deleteByTagId(n);
		}
		
	}
	public Tag[] getAllTags(){
		return dao.getAllTags();
	}
	public String[] getAllIds() {
		return dao.getAllIds();
	}
}
