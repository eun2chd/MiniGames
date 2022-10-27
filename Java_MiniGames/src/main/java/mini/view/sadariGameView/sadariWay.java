package mini.view.sadariGameView;

import java.awt.Color;

import javax.swing.JOptionPane;

public class sadariWay {
	
	sadariRand sa = new sadariRand();
	int leftX = sa.getSheePleftX();
	int leftY = sa.getSheePleftY();
	
	
	
	boolean  LeftLineWayflag;
	
	int[][] sheepLineArr = {
			{100,395,170},
			{155,240,395},
			{320,0,0},
	};
	
	public void leftLineWay() {
		
		leftX = sa.getSheePleftX();
		leftY = sa.getSheePleftY();
	
	while(LeftLineWayflag) {
		int i = 0;
		int j = 0;
	
		try {
			Thread.sleep(3);
			
			if(leftY < sheepLineArr[i][j]) {
				leftY += 1;
			}else if(leftY == sheepLineArr[i][j] && leftX < sheepLineArr[i][j+1]) {
				leftX += 1;
			}else if(leftX == sheepLineArr[i][j+1] && leftY < sheepLineArr[i][j+2]) {
				leftY += 1;
			}else if(leftY == sheepLineArr[i][j+2] && leftX > sheepLineArr[i+1][j]) {
				leftX -= 1;
			}else if(leftX == sheepLineArr[i+1][j] && leftY < sheepLineArr[i+1][j+1]) {
				leftY += 1;
			}else if(leftY == sheepLineArr[i+1][j+1] && leftX < sheepLineArr[i+1][j+2]) {
				leftX += 1;
			}else {
				leftY += 1;
				if(leftY == sheepLineArr[i+2][j]) {
//					JOptionPane.showMessageDialog(null, "결과는 [짝] 입니다.");
					LeftLineWayflag = false;
				}
			}
			
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
  }
	
	
}