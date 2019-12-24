package tool;

import javax.servlet.http.Part;

public class FileHandle {
	private static FileHandle fileHandle;

	private FileHandle() {
	}

	public static FileHandle getInstance() {
		return fileHandle;
	}

	public String getFileName(Part part) {
		String name = null;
		for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
			if (dispotion.trim().startsWith("filename")) {
				name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
				name = name.substring(name.lastIndexOf("\\") + 1);
				break;
			}
		}
		return name;
	}
}
