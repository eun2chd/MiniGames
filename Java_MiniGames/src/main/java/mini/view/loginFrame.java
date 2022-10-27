package mini.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
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
import mini.view.find.UseridFind;
import mini.view.find.userPwFind;
import mini.vo.userInfo;

public class loginFrame extends JFrame {
	
	/*
	 * userInfo
	 * 
	 * */
	userInfo user = new userInfo();
	
	private testView testView;
	private JoinView joinView;
	
	// 로그인/초기화/회원가입 버튼
	private JButton loginButton;
	private JButton resetButton;
	private JButton joinButton;
	private JButton idfindButton;
	private JButton pwFindButton;
	

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
		setSize(455,190);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		loginButton = new JButton("로그인");
		resetButton = new JButton("초기화");
		joinButton = new JButton("회원가입");
		idfindButton = new JButton("아이디 찾기");
		pwFindButton = new JButton("패스워드 찾기");
		
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
		
		// 회원탈퇴 안내 문구
		JLabel Withdrawal = new JLabel("회원탈퇴시 관리자에게 문의바랍니다.");
		Withdrawal.setBounds(110, 130, 215, 25);
		panel.add(Withdrawal);
		

		
		
		// panel 붙이기 (usertext)
		userText = new JTextField(20);
		userText.setBounds(100,10,160,25);
		panel.add(userText);
		
		// panel 붙이기 (password)
		passText = new JPasswordField(20);
		passText.setBounds(100,40,160,25);
		panel.add(passText);
		
		// id 찾기 버튼
		idfindButton.setBounds(270, 10, 150, 25);
		idfindButton.setBackground(new Color(13,93,191));
		idfindButton.setForeground(new Color(255,255,255));
		idfindButton.setFont(new Font("맑은 고딕",Font.BOLD,13));
		panel.add(idfindButton);
		
		
		idfindButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				UseridFind idfind = new UseridFind();
			
			}
		});
		
		
		
		// pw 찾기 버튼
		pwFindButton.setBounds(270, 40, 150, 25);
		pwFindButton.setBackground(new Color(13,93,191));
		pwFindButton.setForeground(new Color(255,255,255));
		pwFindButton.setFont(new Font("맑은 고딕",Font.BOLD,12));
		panel.add(pwFindButton);
		
		pwFindButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				userPwFind userpwfind = new userPwFind();
			}
		});
		
		
		// 로그인 버튼
		loginButton.setBounds(50,85,90,30);
		loginButton.setBackground(new Color(13,93,191));
		loginButton.setForeground(new Color(255,255,255));
		loginButton.setFont(new Font("맑은 고딕",Font.BOLD,13));
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
		joinButton.setBounds(165, 85, 90, 30);
		joinButton.setBackground(new Color(255,255,255));
		joinButton.setForeground(new Color(0,0,0));
		joinButton.setFont(new Font("맑은 고딕",Font.BOLD,13));
		panel.add(joinButton);
		joinButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 회원가입 창 오픈
				setVisible(false);
				joinView = new JoinView();
				
			}
		});
		
		// 초기화 버튼
		resetButton.setBounds(275,85,90,30);
		resetButton.setBackground(new Color(255,255,255));
		resetButton.setForeground(new Color(0,0,0));
		resetButton.setFont(new Font("맑은 고딕",Font.BOLD,13));
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
		
		
		// setter 값 전달 
		user.setUserid(userText.getText());
		user.setPassword(passText.getText());
		
		String userId = user.getUserid();
		
		/*
		 * 사용자가 입력한 값으로 sql 쿼리 질의 
		 * 반환값 int 
		 * 값 토대로 결과 출력
		 * 
		 * */ 
		int loginResult = con.login(user.getUserid(), user.getPassword());
		
		if (loginResult == 1) {
			JOptionPane.showMessageDialog(null, "Success");
			user.setUserid(userText.getText());
			System.out.println("로그인 프레임" + user.getUserid());
			dispose();
			testView = new testView(userId);
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
	


	public static void main(String[] args) {
		
	}

}
