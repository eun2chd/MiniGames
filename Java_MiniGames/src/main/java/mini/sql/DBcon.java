package mini.sql;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mini.Encrypte;

public class DBcon {
	
	private static final String driver = "org.mariadb.jdbc.Driver";
	private static final String DB_IP = "127.0.0.1";
	private static final String DB_PORT = "3306";
	private static final String DB_NAME = "echong";
	private static final String DB_URL = "jdbc:mariadb://127.0.0.1:3306/echong";
	
	private static Connection conn = null;
	private static  PreparedStatement pstmt = null;
	private static ResultSet rs = null;
	
	Encrypte enc;
	
	
	// mariaDB 접속
	public static void DBcon() {
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(DB_URL,"root","1234");
			if(conn != null) {
				System.out.println("DB 접속 완료");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로드 실패");
			
		} catch (Exception e) {
			System.out.println("DB 접속 실패");
			e.printStackTrace();
		}
		
	}
	
	// 사용자 로그인 로직
	public int login(String userId, String userPassword) {
		
		String loginSql = String.format("SELECT password,salt FROM minigames WHERE userid = '%s';",userId);
		
		System.out.println(userId);
	
		try {
			pstmt = conn.prepareStatement(loginSql);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				// 해당로그인하려는 사용자의 소금값을 가져와야합니다.
				enc = new Encrypte();
				String pass = rs.getString(1);
				String salt = rs.getString(2);
				
				userPassword = enc.getEncrypte(userPassword, salt);
				
				if (pass.equals(userPassword)) {
					return 1; // 로그인 성공
				} else {
					return 0; // 비밀번호 불일치
				}
			}
			return -1; // 아이디가 없음
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	return -2; // DB 오류
	
	}
	
	
	// 이름 중복 체크
	public int userNameCheck(String userNmae) {
		
		String userNamesql = String.format("SELECT count(*)  FROM minigames WHERE username = '%s';",userNmae);
		
		try {
			pstmt = conn.prepareStatement(userNamesql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if (rs.getInt(1) == 1) {
					return 1; // 이름 중복
				} else {
					return 0; // 사용가능
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2;
	}
	
	// 아이디 중복 체크
	public int userIdCheck(String userId) {
		
		String userNamesql = String.format("SELECT count(*)  FROM minigames WHERE userid = '%s';",userId);
		
		try {
			pstmt = conn.prepareStatement(userNamesql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if (rs.getInt(1) == 1) {
					return 1; // 이름 중복
				} else {
					return 0; // 사용가능
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2;
	}
	
	// id 찾는 키값 중복 체크
	public int userIdkey(String key) {
		
		String userIdkeySql = String.format("SELECT count(*)  FROM minigames WHERE USERIDCHECK = '%s';", key);
		
		try {
			pstmt = conn.prepareStatement(userIdkeySql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if (rs.getInt(1) == 1) {
					return 1;
				} else {
					return 0;
				}
			}
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2;
	}
	
	
	
	// 회원가입 insert
	public int userInsert(String userName,String userId,String userPass,String userIdcheck) {
		
		String userInsertSql = "insert into minigames(uid,username,userid,useridcheck,password,salt) values(?,?,?,?,?,?)";
		int r = 0;
	
		/*
		 * 회원가입 유저 패스워드 암호화 진행
		 * SHA-256 (단방향 + 소금)
		 * 
		 * 소금 생성 및 암호화 진행
		 * */
		
		enc = new Encrypte();
		
		String salt = enc.getSalt();
		userPass = enc.getEncrypte(userPass, salt);
		
				
		try {
			pstmt = conn.prepareStatement(userInsertSql);
			pstmt.setInt(1, 0);
			pstmt.setString(2, userName);
			pstmt.setString(3, userId);
			pstmt.setString(4, userIdcheck);
			pstmt.setString(5, userPass);
			pstmt.setString(6, salt);
					
			r = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("sql문 오류 : " + e.getMessage());
		}  finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			
			if (conn !=  null) {
				try {
					conn.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			
		}
	
		return r;
	}
	
	
	public static void main(String[] args) {
		
		DBcon conDB = new DBcon();
		conDB.DBcon(); // db 접속 잘되는지 실행
		
	}

	
	

}
