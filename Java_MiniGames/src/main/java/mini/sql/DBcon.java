package mini.sql;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mini.Encrypte;
import mini.vo.SadariResult;
import mini.vo.joinUserinfo;

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
	
	// idkey 중복 체크
	public String userFindId(String  Idkey) {
		
		String  useridFindSql = String.format("SELECT USERID  FROM minigames WHERE USERIDCHECK  = '%s';", Idkey);
		
		try {
			
			pstmt = conn.prepareStatement(useridFindSql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String userid = rs.getString(1);
				if(userid != null) {
					return  userid;
				} else {
					return null;
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	// key id 조회후 해당  사용자가 있는지 체크
	public int useridKeyCheck(String key, String id) {
		
		String userIdCheck = String.format("select count(*)  from minigames m where USERIDCHECK = '%s' and USERID = '%s';", key,id);
		
		try {
			pstmt = conn.prepareStatement(userIdCheck);
		    rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if (rs.getInt(1) == 1) {
					return 1; // 해당 아이디와 키값이 존재함 
				} else {
					return 0;
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2;	
	}
	
	// 패스워드 변경 (잃어버렸을때)
	public int userPasswordUpdate(String pass, String userid) {
		
		joinUserinfo joininfo = new joinUserinfo();
		int u = 0;
		
		
		enc = new Encrypte();
		// 소금 새로 셋팅하고 
		String salt = enc.getSalt();
		// 패스워드 암호화 해서
		pass = enc.getEncrypte(pass, salt);
		// update
		String userPwUpdateSql = "update minigames set SALT = ?, PASSWORD  = ? where USERID = ?";
		
		try {
			pstmt = conn.prepareStatement(userPwUpdateSql);
			pstmt.setString(1, salt);
			pstmt.setString(2, pass);
			pstmt.setString(3, userid);
			
		    u =  pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			System.out.println("sql문 오류 : " + e.getMessage());
		} finally {
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
	
		return u;
	}
	
	public void loginUserInfo(String userid) {
		
		String userSelectsql = "select * from minigames where userid = ?";
		
		try {
			pstmt = conn.prepareStatement(userSelectsql);
			pstmt.setString(1, userid);
			
			rs = pstmt.executeQuery();
			
			int useruid = 0;
			String username = "";
			String userId = "";
			
			while(rs.next()) {
				
				useruid = rs.getInt(1);
				username = rs.getString(2);
				userId = rs.getString(3);				
			}
			
			System.out.println("사용자 uid : " + useruid);
			System.out.println("사용자 이름 : " + username);
			System.out.println("사용자 id : " + userId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	// 사다리 결과물 가져오기 arrayList
	public ArrayList<SadariResult> Sadari() {
		
		String resultsql = "select ROUND ,lr,line,oe from sadariresult";
		ArrayList<SadariResult> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(resultsql);
			rs = pstmt.executeQuery();
			
			
			
			while(rs.next()) {
				
				SadariResult sa = new SadariResult();
				sa.setRound(rs.getInt(1));
				sa.setLR(rs.getString(2));
				sa.setLine(rs.getString(3));
				sa.setOven(rs.getString(4));
	
				list.add(sa);
			}
			
			
			
		} catch (Exception e) {
			System.out.println("결과 불러오기 db 오류");
		}
		return list;
	}
	
	
	
	public static void main(String[] args) {
		
		DBcon conDB = new DBcon();
		conDB.DBcon(); // db 접속 잘되는지 실행
		
	}

	
	

}
