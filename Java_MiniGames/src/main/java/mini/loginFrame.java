package mini;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import mini.sql.DBcon;
import mini.vo.userInfo;

public class loginFrame extends JFrame {
	
	/*
	 * userInfo
	 * 
	 * */
	userInfo user;
	
	private MainProcess main;
	private testView testView;
	
	
	// 로그인/초기화/회원가입 버튼
	private JButton loginButton;
	private JButton resetButton;
	private JButton joinButton;
	
	// 사용자 아이디 패스워드 입력
	private JTextField userText;
	private JPasswordField passText;
	
	// 로그인 성공 및 실패 여부
	private boolean bloginCehck;
	
	JPanel jPanel;
	
	

	public loginFrame() {
		
		initData();
		setInitLayout();
			
	}
	
	// GUI 생성
	private void initData() {
		
		setTitle("로그인 및 회원가입");
		setSize(335,190);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		loginButton = new JButton("로그인");
		resetButton = new JButton("초기화");
		joinButton = new JButton("회원가입");
		
		// 로그인 패널
		jPanel = new JPanel();
		placeLoginPanel(jPanel);
		
		add(jPanel);
		

		
	}
	
	private void setInitLayout() {
		
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	
	}
	
	// 로그인 및 회원가입 jpnel 에 배치
	public void placeLoginPanel(JPanel panel) {
		
		// userid text
		panel.setLayout(null);
		JLabel userJLabel = new JLabel("USERID");
		userJLabel.setBounds(10,10,80,25);
		panel.add(userJLabel);
		
		// password text
		JLabel passJLabel = new JLabel("PASS");
		passJLabel.setBounds(10,40,80,25);
		panel.add(passJLabel);
		
		// panel 붙이기 (usertext)
		userText = new JTextField(20);
		userText.setBounds(100,10,160,25);
		panel.add(userText);
		
		// panel 붙이기 (password)
		passText = new JPasswordField(20);
		passText.setBounds(100,40,160,25);
		panel.add(passText);
		passText.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				isLoginCheck();
			}
		});
		
		// 로그인 버튼
		loginButton.setBounds(10,85,100,25);
		panel.add(loginButton);
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 사용자가 입력한 아이디 패스워드값 담기
				String userId = userText.getText();
				String password = passText.getText();
				
				isLoginCheck();
				
			}
		});
		
		// 회원가입 버튼
		joinButton.setBounds(110, 85, 100, 25);
		panel.add(joinButton);
		joinButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 회원가입 메소드
				main.showJoinView();
			}
		});
		
		// 초기화 버튼
		resetButton.setBounds(210,85,100,25);
		panel.add(resetButton);
		resetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				userText.setText("");
				passText.setText("");
			}
		});
		
		
	}
	

	// 로그인 여부 체크
	public void isLoginCheck() {
		
		// DB 객체 생성 db 접속
		DBcon con = new DBcon();
		con.DBcon();
		
		user = new userInfo();
		
		// setter 값 전달 
		user.setUserid(userText.getText());
		user.setPassword(passText.getText());
		
		/*
		 * 사용자가 입력한 값으로 sql 쿼리 질의 
		 * 반환값 int 
		 * 값 토대로 결과 출력
		 * 
		 * */ 
		int loginResult = con.login(user.getUserid(), user.getPassword());
		
		if (loginResult == 1) {
			JOptionPane.showMessageDialog(null, "Success");
			main.showTestView();
		} else if(loginResult == 0) {
			JOptionPane.showMessageDialog(null, "Password Inconsistency");
		} else if(loginResult == -1) {
			JOptionPane.showMessageDialog(null, "ID does not exist");
		} else {
			JOptionPane.showMessageDialog(null, "DB 오류 관리자에게 문의하세요");
		}
	}
	
	public boolean isLogin() {
		return bloginCehck;
	}
	
	public void setMain(MainProcess main) {
		this.main = main;
	}

	public static void main(String[] args) {
		
	}

}
