package mini;

import mini.view.JoinView;
import mini.view.loginFrame;
import mini.view.testView;
import mini.vo.joinUserinfo;
import mini.vo.userInfo;

public class MainProcess {
	
	loginFrame loginFrame;
	JoinView joinView;
	testView testView;
	

	public static void main(String[] args) {
		
		// 메인 클래스 실행
		MainProcess main = new MainProcess();
	
		// 로그인창 실행
		main.loginFrame = new loginFrame();
		
				
	}
	

}
