package tool;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {
	// SHA-256を用いたハッシュ化
	public static String hash(String str) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hashValue = digest.digest(str.getBytes());
			StringBuilder sb = new StringBuilder();
			for (byte b : hashValue) {
				sb.append(String.format("%02x", b));
			}
			String hashValueStr = sb.toString();
			return hashValueStr;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}
