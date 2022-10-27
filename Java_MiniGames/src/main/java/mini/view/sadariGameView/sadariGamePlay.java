package mini.view.sadariGameView;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class sadariGamePlay extends Thread{
	
	sadariRand sa = new sadariRand();
	sadariWay saWay = new sadariWay();
	
	int count = 0;
	
	private long pretime;
	
	private int delay = 20; // 딜레이
	
	private boolean isOver;
	
	private int cnt;
	
	private int countTime;
	
	
	// 가림막
	ImageIcon wall =  new ImageIcon("./src/main/java/mini/img/wall.png");
	// 홀
	ImageIcon odd =  new ImageIcon("./src/main/java/mini/img/odd.png");
	// 짝
	ImageIcon even =  new ImageIcon("./src/main/java/mini/img/even.png");
	// x 이미지
	ImageIcon Xicon =  new ImageIcon("./src/main/java/mini/img/x.png");
	// 아래 화살표
	ImageIcon bottom =  new ImageIcon("./src/main/java/mini/img/bottom.png");
	// 양
	ImageIcon sheep =  new ImageIcon("./src/main/java/mini/img/Sheep.png");
	// -------------------------------------------------------------------------
	// 가림막
	Image wall_img = wall.getImage();
	Image wall_ch = wall_img.getScaledInstance(200,200,Image.SCALE_SMOOTH);
	
	// 홀
	Image odd_img = odd.getImage();
	Image odd_ch = odd_img.getScaledInstance(50,50,Image.SCALE_SMOOTH);
	
	// 짝
	Image even_img = even.getImage();
	Image even_ch = even_img.getScaledInstance(50,50,Image.SCALE_SMOOTH);
	
	// x 이미지
	Image Xicon_img = Xicon.getImage();
	Image Xicon_ch = Xicon_img.getScaledInstance(50,50,Image.SCALE_SMOOTH);
	
	// 아래 화살표
	Image bottom_img = bottom.getImage();
	Image bottom_ch = bottom_img.getScaledInstance(50,50,Image.SCALE_SMOOTH);
	
	// 양
	Image sheep_img = sheep.getImage();
	Image sheep_ch = sheep_img.getScaledInstance(50,50,Image.SCALE_SMOOTH);
	

	@Override
	public void run() {
		System.out.println(countTime);
		while(true) {
			reset();
			while(!isOver) {
				pretime = System.currentTimeMillis();
				
				if(System.currentTimeMillis() - pretime < delay) {
					try {
						Thread.sleep(500);
						Timeset();
						System.out.println(countTime);
						cnt++;;
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			}
			
			try {
				Thread.sleep(5000);
				count++;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	public void reset() {
		
		countTime = 4;
		isOver = false;
		// 다시 사다리 화면 가려주기
		sa.setLine(0);
		
	}
	
	// 게임 안에 요소들 그려주기
	public void gameDraw(Graphics g) {
		
		
		if(sa.getLine() == 0) {
			sadariInfo(g);
			sadariCh(g);
		} else if (sa.getLine() == 1) {
			sadarLineThree(g);
			sadariCh(g);
		} else {
			sadarLineFour(g);
			sadariCh(g);
		}
		
	}
	

	public void sadariInfo(Graphics g) {
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(30,BasicStroke.CAP_ROUND,0));
		// 기본 사다리 가림판
		g.drawLine(180, 50, 180,350);
		g.drawLine(420, 50, 420,350);
		g.drawImage(wall_img, 195, 120,210,150,null);
		g.drawImage(Xicon_img, 155, 25, null);
		g.drawImage(Xicon_img, 395, 25, null);
		g.setColor(Color.white);
		g.setFont(new Font("맑은 고딕",Font.BOLD,16));
		g.drawString(countTime/60 + "분" + countTime%60 + "초 후 결과 추첨", 220, 200);
	}
	
	public void sadarLineThree(Graphics g) {
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(30,BasicStroke.CAP_ROUND,0));
		// 사다리 3줄 그리기
		g.drawLine(180, 50, 180,350);
		g.drawLine(180, 130, 420,130);
		g.drawLine(180, 200, 420,200);
		g.drawLine(180, 270, 420,270);
		g.drawLine(420, 50, 420,350);
		g.drawImage(Xicon_img, 155, 25, null);
		g.drawImage(Xicon_img, 395, 25, null);
		if(sa.getLine() == 1) {
			sadariLeft(g);
		}else {
			sadariRight(g);
		}
		
	}
	
	public void sadarLineFour(Graphics g) {
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(30,BasicStroke.CAP_ROUND,0));
		// 사다리 4줄 그리기
		g.drawLine(180, 50, 180,350);
		g.drawLine(180, 130, 420,130);
		g.drawLine(180, 180, 420,180);
		g.drawLine(180, 230, 420,230);
		g.drawLine(180, 280, 420,280);
		g.drawLine(420, 50, 420,350);
		g.drawImage(Xicon_img, 155, 25, null);
		g.drawImage(Xicon_img, 395, 25, null);
		if(sa.getLine() == 1) {
			sadariLeft(g);
		}else {
			sadariRight(g);
		}
	}
	
	public void sadariCh(Graphics g) {

		g.drawImage(even_img, 368, 310,100,100, null);
		g.drawImage(odd_img,135,310,95,95, null);
	}
	
	public void sadariLeft(Graphics g) {
		g.drawImage(bottom_ch,155,25,null );
		g.drawImage(sheep_ch,saWay.leftX,saWay.leftY, null);
		
	}
	public void sadariRight(Graphics g) {
		g.drawImage(bottom_ch,395,25,null );
		g.drawImage(sheep_ch,395,35, null);
	}
	
	
	// 타이머
	public void Timeset() {
		
		countTime--;
		if(countTime == 0) {
			isOver = true;
			sa.setLine(1);
			sa.setLR(1);
			saWay.LeftLineWayflag = true;
			saWay.leftLineWay();
		}
	}
	
	

	public boolean isOver() {
		return isOver;
	}

	public void setOver(boolean isOver) {
		this.isOver = isOver;
	}

}
