package mini.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import mini.sql.DBcon;
import mini.vo.joinUserinfo;

public class JoinView extends JFrame{
	
	private loginFrame loginFrame;
	private boolean loginSucces;

	
	joinUserinfo userinfo;
	
	
	JPanel joinJPanel;
	JLabel label = new JLabel();
	
	// 가입 버튼
	private JButton joinButton;
	// 패스워드 체크 버튼
	private JButton passCheckButton;
	// 초기화 버튼
	private JButton resetButton;
	
	private JTextField userTextName;
	private JTextField userTextId;
	private JPasswordField userPass;
	private JPasswordField userPasscheck;
	private JTextField userIdTextkey;

	public JoinView() {
		
		initData();
		setInitLayout();
	}
	
	private void initData() {
		
		setTitle("회원가입");
		setSize(300,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
		// 회원가입 패널
		joinJPanel = new JPanel();
		placeJoinpanel(joinJPanel);
		
		
		add(joinJPanel);
		
	}
	
	private void setInitLayout() {
		
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		
	}
	
	
	public void placeJoinpanel(JPanel panel) {
		
		userinfo = new joinUserinfo();
		
		// 상단 회원가입 로고
		panel.setLayout(null);
		JLabel joinText = new JLabel("회원가입");
		joinText.setBounds(100, 20, 100, 25);
		joinText.setFont(new Font("Serif", Font.BOLD,20));
		panel.add(joinText);
		
		// USERNAME
		panel.setLayout(null);
		JLabel userName = new JLabel("UESRNAME");
		userName.setBounds(20, 75, 80, 25);
		panel.add(userName);
		
		// USERID
		panel.setLayout(null);
		JLabel userId = new JLabel("USERID");
		userId.setBounds(20, 115, 80, 25);
		panel.add(userId);
		
		// USpassWord
		panel.setLayout(null);
		JLabel passWord = new JLabel("PASS");
		passWord.setBounds(20, 155, 80, 25);
		panel.add(passWord);
		
		// USpassWordCheck
		panel.setLayout(null);
		JLabel passWordCheck = new JLabel("PASSCEHCK");
		passWordCheck.setBounds(20, 195, 80, 25);
		panel.add(passWordCheck);
		
		// passwordKey
		panel.setLayout(null);
		JLabel userIdkey = new JLabel("IDKEY");
		userIdkey.setBounds(20, 230, 80, 25);
		panel.add(userIdkey);
		
		
		
		userTextName = new JTextField(20);
		userTextName.setBounds(100, 75, 160, 25);
		userTextName.setText("(실명 입력)");
		panel.add(userTextName);
		
		userTextId = new JTextField(20);
		userTextId.setBounds(100, 115, 160, 25);
		panel.add(userTextId);
		
		userPass = new JPasswordField(20);
		userPass.setBounds(100, 155, 160, 25);
		panel.add(userPass);
		
		userPasscheck = new JPasswordField(20);
		userPasscheck.setBounds(100, 195, 160, 25);
		panel.add(userPasscheck);
		
		userIdTextkey= new JTextField(20);
		userIdTextkey.setBounds(100, 230, 160, 25);
		userIdTextkey.setText("(ID 찾기 시 활용)");
		panel.add(userIdTextkey);
		
		
		passCheckButton = new JButton("PASS CHECK");
		passCheckButton.setBounds(100 ,275 , 160, 25);
		panel.add(passCheckButton);
		
		// 패스워드체크 버튼 클릭
		passCheckButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// passCheck 로직	
				String pass = userPass.getText();
				String passCheck = userPasscheck.getText();
				
				passwordCheck(pass, passCheck);
				
				
			}
		});
		
		resetButton = new JButton("초기화");
		resetButton.setBounds(10, 275, 80, 25);
		panel.add(resetButton);
		
		// 초기화 버튼 클릭
		resetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				userTextName.setText("(실명 입력)");
				userTextId.setText("");
				userPass.setText("");
				userPasscheck.setText("");
				userIdTextkey.setText("(ID 찾기 시 활용)");
				
				userPass.setEnabled(true);
				userPasscheck.setEnabled(true);
				passCheckButton.setEnabled(true);
				userIdTextkey.setEnabled(true);
			}
		});
		
		
		
		joinButton = new JButton("가입신청");
		joinButton.setBounds(100,320,100,25);
		joinButton.setEnabled(false);
		panel.add(joinButton);
		
		// 가입신청 버튼 클릭
		joinButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String userName = userTextName.getText();
				String userId = userTextId.getText();
				String useridCheck = userIdTextkey.getText();
				
				
				
				JoinWrapper(userName, userId,useridCheck);
				
			
				
			}
		});

	} // end of place()
	
	public void passwordCheck(String pass, String passCheck) {
		
		if (pass.length() != 0 && passCheck.length() != 0) {
			if (pass.equals(passCheck)) {
				if(pass.length() >= 4 && pass.length() <= 8 && passCheck.length() >= 4 && passCheck.length() <= 8) {	
					userPass.setEnabled(false);
					userPasscheck.setEnabled(false);
					JOptionPane.showMessageDialog(null, "패스워드가 체크가 완료되었습니다.!");
					userinfo.setPassword(passCheck);
					passCheckButton.setEnabled(false);
					joinButton.setEnabled(true);
					
				}else {
					JOptionPane.showMessageDialog(null, "패스워드 길이 규칙에 맞지 않습니다. 4~8 자리 사이로 입력하세요");
				}
				
			} else {
				JOptionPane.showMessageDialog(null, "패스워드가 다릅니다.");
			}

		} else {
			JOptionPane.showMessageDialog(null, "패스워드를 입력하세요");
		}
	}
	
	public void JoinWrapper(String userName, String userId, String userIdcheck) {
		
		JOptionPane.showMessageDialog(null, "가입하시겠습니까? 확인을 누른뒤 잠시 기다려주세요.");
		for(int i = 5; i > 0;i--) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		userinfo.setUsername(userName);
		userinfo.setUserid(userId);
		userinfo.setUserIdkey(userIdcheck);
		
		// db 접속
		DBcon dBcon = new DBcon();
		dBcon.DBcon();
		
		// 이름 중복 체크
		int userNameCheckResult = dBcon.userNameCheck(userName);
		// 아이디 중복 체크
		int userIdCheckResult = dBcon.userIdCheck(userId);
		// 아이디 키값 중복 체크
		int userIdcheckKey = dBcon.userIdkey(userIdcheck);
		
	
		
		if (userName.length() != 0 && userId.length() != 0) {
			if (userName.length() >= 2 && userName.length() <= 4) {
				if(userId.length() >= 4 && userId.length() <= 12) {
					if(!userName.equals(userId)) {
						if (userNameCheckResult == 1) {
							JOptionPane.showMessageDialog(null, "중복된 이름입니다.");
						} else if(userIdCheckResult == 1) {
							JOptionPane.showMessageDialog(null, "중복된 아이디 입니다.");
						} else if(userIdcheckKey == 1) {
							JOptionPane.showMessageDialog(null, "IDKEY 값이 중복되었습니다. 다시 입력해 주세요.");
						} else {
							JOptionPane.showMessageDialog(null, "가입신청이 완료되었습니다.");
							if (userName.length() == 0 || userId.length() == 0 || userIdcheck.length() == 0) {
								loginSucces = false;
							} else {
								loginSucces = true;	
							}										
						}
					}else {
						JOptionPane.showMessageDialog(null, "사용자 이름과 아이디를 다르게 설정하세요");
					}
				
				} else {
					JOptionPane.showMessageDialog(null, "아이디 길이는 4~12 자리로 입력하세요.");
				}
				
			} else {
				JOptionPane.showMessageDialog(null, "이름 길이는 2~4 자리 자리로 입력하세요.");
			}
			
		} else {
			JOptionPane.showMessageDialog(null, "이름 또는 닉네임을 입력하세요.");
		}
		
		// db insert 하고
		int insertResult = 0;
		
		if(loginSucces == true) {			
			insertResult = dBcon.userInsert(userName, userId,userinfo.getPassword(),userIdcheck);
			
			if (insertResult == 1 && loginSucces == true) {
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				// 회원가입창 비활성화 시켜주고
				setVisible(false);
				// 로그인창 다시 오픈
				loginFrame = new loginFrame();
				
			} else {
				JOptionPane.showMessageDialog(null, "가입 신청이 안되어있습니다. 다시 시도하세요");
			}
			
		} else {
			JOptionPane.showMessageDialog(null, "가입 신청 승인이 실패하였습니다.");
		}
		
	}
	


} // end of class
