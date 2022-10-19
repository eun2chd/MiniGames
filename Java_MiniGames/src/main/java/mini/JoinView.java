package mini;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class JoinView extends JFrame{
	
	JPanel joinJPanel;
	
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
		
		
		
		
		
	}
	
	
}
