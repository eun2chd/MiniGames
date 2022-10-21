package mini.view.find;

import java.awt.Color;
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
import mini.view.loginFrame;
import mini.vo.joinUserinfo;

public class userPwFind extends JFrame{
	
	private JButton loginFOpenButton;
	private JButton usercheckChangeButton;
	private JButton userpwuUdateButton;	
	private JTextField userIdkeytext;
	private JTextField userIdtext;	
	private JPasswordField userPwtext;
	private JPasswordField userPwChecktext;
	
	JLabel label = new JLabel();
	
	joinUserinfo joininfo = new joinUserinfo();

	JPanel findPwpanel;
	
	public userPwFind() {
		
		initData();
		setInitLayout();
	
	}
	
	private void initData() {
		
		setTitle("패스워드 찾기");
		setSize(400,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		findPwpanel = new JPanel();
		placeFindpwPanel(findPwpanel);
		
		add(findPwpanel);
		
	}
	
	private void setInitLayout() {
		
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		
	}
	
	
	private void placeFindpwPanel(JPanel Panel) {
		
		
		Panel.setLayout(null);
		JLabel userIdkeylable = new JLabel("IDKEY");
		userIdkeylable.setBounds(40, 40, 80, 25);
		Panel.add(userIdkeylable);
		
		
		Panel.setLayout(null);
		JLabel userIdlable = new JLabel("USERID");
		userIdlable.setBounds(40, 80, 80, 25);
		Panel.add(userIdlable);
		
		Panel.setLayout(null);
		JLabel changePw = new JLabel("CHANGE PW");
		changePw.setBounds(40, 120, 80, 25);
		Panel.add(changePw);
		
		
		Panel.setLayout(null);
		JLabel changePwcheck = new JLabel("PW CHECK");
		changePwcheck.setBounds(40, 160, 80, 25);
		Panel.add(changePwcheck);
		
	
		
		userIdkeytext = new JTextField();
		userIdkeytext.setBounds(140, 40, 195, 25);
		Panel.add(userIdkeytext);
		
		userIdtext = new JTextField();
		userIdtext.setBounds(140, 80, 195, 25);
		Panel.add(userIdtext);
		
	
		userPwtext = new JPasswordField();
		userPwtext.setBounds(140, 120, 195, 25);
		userPwtext.setEnabled(false);
		Panel.add(userPwtext);
		
		userPwChecktext = new JPasswordField();
		userPwChecktext.setBounds(140, 160, 195, 25);
		userPwChecktext.setEnabled(false);
		Panel.add(userPwChecktext);
		

		
		loginFOpenButton = new JButton("로그인 하러가기");
		loginFOpenButton.setBounds(0, 0, 150, 25);
		loginFOpenButton.setBackground(new Color(13,134,244));
		loginFOpenButton.setForeground(new Color(255,255,255));
		Panel.add(loginFOpenButton);
		
		loginFOpenButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

					setVisible(false);
					loginFrame login = new loginFrame();
				
			}
		});
		
		
		usercheckChangeButton = new JButton("패스워드 입력");
		usercheckChangeButton.setBounds(30, 210, 150, 25);
		usercheckChangeButton.setBackground(new Color(13,134,244));
		usercheckChangeButton.setForeground(new Color(255,255,255));
		Panel.add(usercheckChangeButton);
		
		usercheckChangeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String userkey = userIdkeytext.getText();
				String userId = userIdtext.getText();
				
				
				// 키 아이디 조회 검증
				userIdkeyCheck(userkey,userId);
			
			}
		});
		
		
		userpwuUdateButton = new JButton("패스워드 업데이트");
		userpwuUdateButton.setBounds(210, 210, 150, 25);
		userpwuUdateButton.setBackground(new Color(13,134,244));
		userpwuUdateButton.setForeground(new Color(255,255,255));
		userpwuUdateButton.setEnabled(false);
		Panel.add(userpwuUdateButton);
		
		userpwuUdateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				// PW 업데이트 쿼리 필요
				String pass = userPwtext.getText();
				String passCHeck = userPwChecktext.getText();
				String userid = userIdtext.getText();
							
				userPassCehck(pass, passCHeck,userid);
			}
		});
	}

	public void userIdkeyCheck(String userkey, String userid) {
		
		DBcon dbcon = new DBcon();
		dbcon.DBcon();
		int idcheckResult = 0;
		
		joininfo.setUserIdkey(userkey);
		joininfo.setUserid(userid);
		
		
		if (userkey.length() != 0 && userid.length() != 0) {
			idcheckResult =  dbcon.useridKeyCheck(userkey, userid);
			
			System.out.println("id check : " + idcheckResult);
			
			if(idcheckResult == 1) {
				
				JOptionPane.showMessageDialog(null, "사용자 정보를 확인하였습니다. \n변경할 패스워드를 입력하세요");
				
				userPwtext.setEnabled(true);
				userPwChecktext.setEnabled(true);
				userpwuUdateButton.setEnabled(true);
			} else if(idcheckResult == 0){
				JOptionPane.showMessageDialog(null, "일치하는 사용자가 없습니다.");
			} else {
				JOptionPane.showMessageDialog(null, "관리자에게 문의하세요.");
			}
			
		} else {
			JOptionPane.showMessageDialog(null, "빈 공백은 입력할 수 없습니다.");
		}
			
	}
	
	public void userPassCehck(String pass, String passCheck,String userid) {
		
		int result = 0;
		
		if (pass.length() != 0 && passCheck.length() != 0) {
			
			if (!pass.equals(passCheck)) {
				JOptionPane.showMessageDialog(null, "패스워드가 일치하지 않습니다..!");
			} else {
				if (pass.length() >= 4 && pass.length() <= 8 && passCheck.length() >= 4 && passCheck.length() <= 8) {
					
					// 업데이트 db 연결
					DBcon dBcon = new DBcon();
					dBcon.DBcon();
					
					result = dBcon.userPasswordUpdate(passCheck, userid);
				} else {
					JOptionPane.showMessageDialog(null, "패스워드 길이는 4 ~ 8 이내로 입력하세요...");
				}
			}
		
			if (result != 1) {
				JOptionPane.showMessageDialog(null, "패스워드 업데이트에 실패 하였습니다...");
			} else {
				JOptionPane.showMessageDialog(null, "패스워드가 정상적으로 변경되었습니다...");
				userIdkeytext.setEnabled(false);
				userIdtext.setEnabled(false);
				userPwtext.setEnabled(false);
				userPwChecktext.setEnabled(false);
			}
			
		} else {
			JOptionPane.showMessageDialog(null, "패스워드는 공백 입력이 불가능 합니다..");
		}
	}
	
	
	public static void main(String[] args) {
		
		userPwFind pwfind = new userPwFind();
		
	}
	

}
