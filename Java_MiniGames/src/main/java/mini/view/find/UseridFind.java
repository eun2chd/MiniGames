package mini.view.find;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mini.sql.DBcon;
import mini.view.loginFrame;
import mini.vo.joinUserinfo;

public class UseridFind extends JFrame{
	
	private JButton userFindidButton;
	private JButton loginFOpenButton;
	private JTextField useridkeyText;
	private JLabel useridlable;
	
	
	
	joinUserinfo joininfo = new joinUserinfo();
	JLabel label = new JLabel();
	
	
	JPanel findIdpanel;
	
	public UseridFind() {
		
		initData();
		setinitLayout();
		
	}
	
	private void initData() {
		
		setTitle("아이디 찾기");
		setSize(420,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
		userFindidButton = new JButton("아이디 찾기");
		
		findIdpanel = new JPanel();
		placeFindpanel(findIdpanel);
		
		add(findIdpanel);
		
		
	}
	
	private void setinitLayout() {
		
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		
	}
	
	
	private void placeFindpanel(JPanel Panel) {
		
		Panel.setLayout(null);
		JLabel userIdkeylable = new JLabel("ID KEY");
		userIdkeylable.setBounds(30, 40, 80, 25);
		Panel.add(userIdkeylable);
		
		Panel.setLayout(null);
		useridlable = new JLabel("");
		useridlable.setBounds(140, 100, 150, 25);
		useridlable.setText("아이디 찾기를 클릭하세요.");
		Panel.add(useridlable);
		
		
		useridkeyText = new JTextField();
		useridkeyText.setBounds(80, 40, 195, 25);
		Panel.add(useridkeyText);
		
		userFindidButton = new JButton("아이디 찾기");
		userFindidButton.setBounds(285, 40, 100, 25);
		userFindidButton.setBackground(new Color(13,134,244));
		userFindidButton.setForeground(new Color(255,255,255));
		Panel.add(userFindidButton);
		
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
		
		
		
		userFindidButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				
				joininfo.setUserIdkey(useridkeyText.getText());
				userIdfind();
				
			}
		});
	
		
		
	}
	
	public void userIdfind() {
		
		DBcon dBcon = new DBcon();
		dBcon.DBcon();
		
		// key 값 전달해주고 아이디값 들고오기
		
		String userid = dBcon.userFindId(joininfo.getUserIdkey());
		
		if (userid != null) {
			useridlable.setText("ID : " + userid);
		} else {
			useridlable.setText("조회된 계정이 없습니다.");
		}
				
	}

}
