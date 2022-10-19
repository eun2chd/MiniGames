package mini.sql;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBcon {
	
	private static final String driver = "org.mariadb.jdbc.Driver";
	private static final String DB_IP = "127.0.0.1";
	private static final String DB_PORT = "3306";
	private static final String DB_NAME = "echong";
	private static final String DB_URL = "jdbc:mariadb://127.0.0.1:3306/echong";
	
	private static Connection conn = null;
	private static  PreparedStatement pstmt = null;
	private static ResultSet rs = null;
	
	
	// mariaDB 접속
	public static void DBcon() {
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(DB_URL,"root","1234");
			if(conn != null) {
				System.out.println("DB 접속 완료");
			}
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			System.out.println("드라이버 로드 실패");
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("DB 접속 실패");
			e.printStackTrace();
		}
		
	}
	
	// 사용자 로그인 로직
	public int login(String userId, String userPassword) {
		
		String loginSql = String.format("SELECT password FROM minigames WHERE userid = '%s';",userId);
		
		
		try {
			pstmt = conn.prepareStatement(loginSql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if (rs.getString(1).contentEquals(userPassword)) {
					return 1; // 로그인 성공
				} else {
					return 0; // 비밀번호 불일치
				}
			}
			return -1; // 아이디가 없음
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return -2; // DB 오류
	}
	
	
	public static void main(String[] args) {
		
		DBcon conDB = new DBcon();
		conDB.DBcon(); // db 접속 잘되는지 실행
		
	}

}
