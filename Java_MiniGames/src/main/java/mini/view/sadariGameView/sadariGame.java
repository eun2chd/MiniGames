package mini.view.sadariGameView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


import mini.sql.DBcon;
import mini.vo.SadariResult;


public class sadariGame extends JFrame{
	// 이미지 그리기 위한 변수
	BufferedImage BackGroundSadariImg;
	
	public static final int WIDTH_SCREEN = 550;
	public static final int HEIGHT_SCREEN = 500;

	private JTextArea sadariResultArea;
	// 홈버튼으으로 이동할 버튼
	private JButton homeButton;
	
	private sadariGamePlay sadariGamePlay = new sadariGamePlay();
	
	// 패널 분할
	// 레이아웃 배치
	BorderLayout borderLayout;
	// 사다리 홈 패널
	JPanel sadariHomePanel;
	// 사다리 게임 패널(사다리가 나오는 패널)
	sadaDrow sadariGamePanel;
	// 사용자가 배팅하기위한 패널
	JPanel sadariBattingPanel;
	// 왼쪽으로 배치할 패널 / textArea 와 연결하여 결과값을 도출할 패널
	JScrollPane sadariResultPanel;
	
	
	public sadariGame() {
		
		initData();
		setInitLyaout();
		sadariGamePlay.start();
	}
	
	public void initData() {
		
		setTitle("사다리 게임");
		setSize(700,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		homeButton = new JButton("홈으로 돌아가기");
		
		// 사다리 홈 패널
		sadariHomePanel = new JPanel();
		// 사용자가 배팅하기위한 패널
		sadariBattingPanel = new JPanel();
		// 사다리 게임 패널(사다리가 나오는 패널)
		sadariGamePanel = new sadaDrow();
		// 왼쪽으로 배치할 패널 / textArea 와 연결하여 결과값을 도출할 패널
		sadariResultPanel = new JScrollPane();
		
		
		// 홈으로
		placeHome(sadariHomePanel);
		add(sadariHomePanel,borderLayout.NORTH);
		
		// 사다리게임 패널
		placeGamePanel(sadariGamePanel);
		add(sadariGamePanel,borderLayout.WEST);
		
		// 사다리 결과창
		placeSadariResult(sadariResultPanel);
		add(new JScrollPane(sadariResultArea), borderLayout.EAST);
		
		// 사다리 배팅 정보 패널
		placebatPanel(sadariBattingPanel);
		add(sadariBattingPanel,borderLayout.SOUTH);
		
		
		try {
			BackGroundSadariImg = ImageIO.read(new File("./src/main/java/mini/img/sadari.jpg"));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("파일이 존재하지 않습니다.");
		}
		
	}
	
	public void setInitLyaout() {
		
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		
	}
	
	// 홈으로/ 상단 메뉴바 패널
	public void placeHome(JPanel panel) {
		
		panel.setLayout(borderLayout);
		panel.setBackground(new Color(129, 159, 247));
		panel.setPreferredSize(new Dimension(600,70));
		
		JLabel label = new JLabel("지난회차 결과");
		label.setBounds(568, 0, 600, 70);
		label.setFont(new Font("맑은 고딕",Font.BOLD,17));
		label.setForeground(new Color(255,255,255));
		panel.add(label);
		
		homeButton.setBounds(0, 0, 555, 70);
		homeButton.setBackground(new Color(255,255,255));
		homeButton.setForeground(new Color(0,0,0));
		panel.add(homeButton);
	}
	
	// 사다리게임 패널
	public void placeGamePanel(JPanel panel) {
		panel.setLayout(borderLayout);
		panel.setBackground(new Color(218,183,104) );
		panel.setPreferredSize(new Dimension(555,500));
	}
	
	// 사다리 결과창
	public void placeSadariResult(JScrollPane panel) {
		
		panel.setLayout(borderLayout);
		panel.setSize(300,500);
		sadariResultArea = new JTextArea(10,7);
		sadariResultArea.setFont(new Font("맑은 고딕",Font.BOLD,17));
		sadariResultArea.setLineWrap(true);
		sadariResultArea.setEditable(false); // 편집 불가
		panel.add(sadariResultArea);
		
		ArrayList<SadariResult> list = new ArrayList<>();
		
		DBcon dBcon = new DBcon();
		dBcon.DBcon();
		
		list = dBcon.Sadari();	
		SadariResult sa = new SadariResult();
		
		for(int i = 0; i <list.size(); i ++) {
			sadariResultArea.append(list.get(i).getRound()+"회 "+
			list.get(i).getLR()+" "+list.get(i).getLine()+" "+list.get(i).getOven() + "\n");
		}
	}
	
	// 사다리 배팅 정보 패널
	public void placebatPanel(JPanel panel) {
		
		panel.setLayout(borderLayout);
		panel.setBackground(new Color(0,0,0) );
		panel.setPreferredSize(new Dimension(600,300));
	}
	
	
	
	private class sadaDrow extends JPanel {
		
		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			g.drawImage(BackGroundSadariImg, 0, 0,555,500,null);
			sadariGamePlay.gameDraw(g);
			this.repaint();
		}
	}
	


	
	public static void main(String[] args) {
		
	
		new sadariGame();
		
	} // end of main
} // end of class
