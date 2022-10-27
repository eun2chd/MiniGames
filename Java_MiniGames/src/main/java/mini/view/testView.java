package mini.view;

import javax.swing.JFrame;

import mini.sql.DBcon;
import mini.vo.userInfo;

public class testView extends JFrame {
	
	String userid;
	
	
	public testView(String userId) {
		
		this.userid = userId;
		
		initdata();
		setInitLayout();
	}
	
	public void initdata() {
		
		setTitle("메뉴");
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		userinfo();
	}
	
	public void setInitLayout() {
		
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		
	}
	
	
	public void userinfo() {
		
		DBcon dBcon = new DBcon();
		dBcon.DBcon();
		
		dBcon.loginUserInfo(userid);
		
	
	}
	
	
	
	public static void main(String[] args) {
				
	}
}
