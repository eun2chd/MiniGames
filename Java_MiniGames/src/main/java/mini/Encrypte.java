package mini;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Iterator;

public class Encrypte {
	
	// 소금 만들기
	public String getSalt() {
		
		SecureRandom r = new SecureRandom();
		byte[] salt = new byte[20];
		
		r.nextBytes(salt);
		
		StringBuffer sb = new StringBuffer();
		for(byte b : salt) {
			sb.append(String.format("%02x", b));
		};
		
		return sb.toString();
		
	}
	
	// 패스워드 암호화 하기
	public String getEncrypte(String pwd, String salt) {
		
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update((pwd+salt).getBytes());
			byte[] pwdsalt = md.digest();
			
			StringBuffer sb = new StringBuffer();
			for(byte b : pwdsalt) {
				sb.append(String.format("%02x", b));
			}
			
			result = sb.toString();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
		return result;
	}

}
