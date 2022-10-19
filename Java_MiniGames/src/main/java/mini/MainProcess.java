package mini;

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
		
		// 로그인창에 메인 클래스 보내기
		main.loginFrame.setMain(main);
	}
	
	public void showTestView() {
		
		// 로그인 창 닫아주기
		loginFrame.dispose();
		// 테스트 프레임 오픈
		this.testView = new testView();
		
	}
	
	public void showJoinView() {
		
		// 로그인 창 닫아주기
		loginFrame.dispose();
		// 회원가입 창 오픈
		this.joinView = new JoinView();
		
	}

}
