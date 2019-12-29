package tool;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import javax.servlet.http.Part;

public class FileHandle {
	private static FileHandle fileHandle = new FileHandle();

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
				if (!name.endsWith("jpg") && !name.endsWith("png") && !name.endsWith("gif") && !name.endsWith("bmp")) {
					name = "";
				}
				break;
			}
		}
		return name;
	}

	public void save(Part in, File out) throws IOException {
		BufferedInputStream br = new BufferedInputStream(in.getInputStream());
		try (BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(out))) {
			int len = 0;
			byte[] buff = new byte[1024];
			while ((len = br.read(buff)) != -1) {
				bw.write(buff, 0, len);
			}
		}
	}

	public void deleteFile(String fileDir, String filename) {
		File file = new File(fileDir, filename);
		if (file.exists()) { // ファイルがあれば
			file.delete();
		}
	}

	public String getParameter(Part part) throws IOException {
		InputStream inputStream = part.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		return reader.lines().collect(Collectors.joining());
	}

}
