package mini.view.sadariGameView;

import java.awt.Color;

import javax.swing.JOptionPane;

public class sadariWay {
	
	sadariRand sa = new sadariRand();
	//3줄 왼쪽 출발
	int leftX = sa.getSheePleftX();
	int leftY = sa.getSheePleftY();
	// 3줄 오른쪽 출발
	int rightX = sa.getRsheePleftX();
	int rightY = sa.getRsheePleftY();
	
	//4줄 왼쪽 출발
	int foursheePleftX = sa.getFoursheePleftX();
	int foursheePleftY = sa.getFoursheePleftY();
	//4줄 오른쪽 출발
	int fourrsheePleftX = sa.getFourrsheePleftX();
	int fourrsheePleftY = sa.getFourrsheePleftY();
	
	boolean  LineWayflag;
	//3줄 왼쪽 출발
	int[][] sheepLineArr = {
			{100,395,170},
			{155,240,395},
			{320,0,0},
	};
	// 3줄 오른쪽 출발
	int[][] rsheepLineArr = {
			{100,155,170},
			{395,240,155},
			{320,0,0},
	};
	//4줄 왼쪽 출발
	int[][] fourSheepLineArr = {
			{100,395,150},
			{155,200,395},
			{250,155,300},
	};
	//4줄 오른쪽 출발
	int[][] fourRsheepLineArr = {
			{100,155,150},
			{395,200,155},
			{250,395,300},
	};
	
	public void leftLineWay() {
		
		leftX = sa.getSheePleftX();
		leftY = sa.getSheePleftY();
	// 3줄 왼쪽 출발
	while(LineWayflag) {
		int i = 0;
		int j = 0;
	
		try {
			Thread.sleep(2);
			
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
			} else {
				leftY += 1;
				if(leftY == sheepLineArr[i+2][j]) {
//					JOptionPane.showMessageDialog(null, "결과는 [짝] 입니다.");
					LineWayflag = false;
				}
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
	}
  }
// 3줄 오른쪽 출발
	public void rightLineWay() {
		
		rightX = sa.getRsheePleftX();
		rightY = sa.getRsheePleftY();
	
	while(LineWayflag) {
		int i = 0;
		int j = 0;
	
		try {
			Thread.sleep(2);
			
			if(rightY < rsheepLineArr[i][j]) {
				rightY += 1;
			}else if(rightY == rsheepLineArr[i][j] && rightX > rsheepLineArr[i][j+1]) {
				rightX -= 1;
			}else if(rightX == rsheepLineArr[i][j+1] && rightY < rsheepLineArr[i][j+2]) {
				rightY += 1;
			}else if(rightY == rsheepLineArr[i][j+2] && rightX < rsheepLineArr[i+1][j]) {
				rightX += 1;
			}else if(rightX == rsheepLineArr[i+1][j] && rightY < rsheepLineArr[i+1][j+1]) {
				rightY += 1;
			}else if(rightY == rsheepLineArr[i+1][j+1] && rightX > rsheepLineArr[i+1][j+2]) {
				rightX -= 1;
			}else {
				rightY += 1;
				if(rightY == rsheepLineArr[i+2][j]) {
//					JOptionPane.showMessageDialog(null, "결과는 [홀] 입니다.");
					LineWayflag = false;
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
	
	// 4줄 왼쪽 출발
	public void fourLeftWay() {
		foursheePleftX = sa.getFoursheePleftX();
		foursheePleftY = sa.getFoursheePleftY();
		
	while(LineWayflag) {
		int i = 0;
		int j = 0;
	
		try {
			Thread.sleep(2);
			
			if(foursheePleftY < fourSheepLineArr[i][j]) {
				foursheePleftY += 1;
			}else if(foursheePleftY == fourSheepLineArr[i][j] && foursheePleftX < fourSheepLineArr[i][j+1]) {
				foursheePleftX += 1;
			}else if(foursheePleftX == fourSheepLineArr[i][j+1] && foursheePleftY < fourSheepLineArr[i][j+2]) {
				foursheePleftY += 1;
			}else if(foursheePleftY == fourSheepLineArr[i][j+2] && foursheePleftX > fourSheepLineArr[i+1][j]) {
				foursheePleftX -= 1;
			}else if(foursheePleftX == fourSheepLineArr[i+1][j] && foursheePleftY < fourSheepLineArr[i+1][j+1]) {
				foursheePleftY += 1;
			}else if(foursheePleftY == fourSheepLineArr[i+1][j+1] && foursheePleftX < fourSheepLineArr[i+1][j+2]) {
				foursheePleftX += 1;
			}else if(foursheePleftX == fourSheepLineArr[i+1][i+2] && foursheePleftY < fourSheepLineArr[i+2][j]) {
				foursheePleftY += 1;
			}else if(foursheePleftY == fourSheepLineArr[i+2][j] && foursheePleftX > fourSheepLineArr[i+2][j+1])
				foursheePleftX -= 1;
			else {
				foursheePleftY += 1;
				if(foursheePleftY == fourSheepLineArr[i+2][j+2]) {
//					JOptionPane.showMessageDialog(null, "결과는 [짝] 입니다.");
					LineWayflag = false;
				}
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
	  }
	}
	
	// 4줄 오른쪽 출발
	public void fourRightWay() {
		fourrsheePleftX = sa.getFourrsheePleftX();
		fourrsheePleftY = sa.getFourrsheePleftY();
		
	while(LineWayflag) {
		int i = 0;
		int j = 0;
	
		try {
			Thread.sleep(2);
			
			if(fourrsheePleftY < fourRsheepLineArr[i][j]) {
				fourrsheePleftY += 1;
			}else if(fourrsheePleftY == fourRsheepLineArr[i][j] && fourrsheePleftX > fourRsheepLineArr[i][j+1]) {
				fourrsheePleftX -= 1;
			}else if(fourrsheePleftX == fourRsheepLineArr[i][j+1] && fourrsheePleftY < fourRsheepLineArr[i][j+2]) {
				fourrsheePleftY += 1;
			}else if(fourrsheePleftY == fourRsheepLineArr[i][j+2] && fourrsheePleftX < fourRsheepLineArr[i+1][j]) {
				fourrsheePleftX += 1;
			}else if(fourrsheePleftX == fourRsheepLineArr[i+1][j] && fourrsheePleftY < fourRsheepLineArr[i+1][j+1]) {
				fourrsheePleftY += 1;
			}else if(fourrsheePleftY == fourRsheepLineArr[i+1][j+1] && fourrsheePleftX > fourRsheepLineArr[i+1][j+2]) {
				fourrsheePleftX -= 1;
			}else if(fourrsheePleftX == fourRsheepLineArr[i+1][i+2] && fourrsheePleftY < fourRsheepLineArr[i+2][j]) {
				fourrsheePleftY += 1;
			}else if(fourrsheePleftY == fourRsheepLineArr[i+2][j] && fourrsheePleftX < fourRsheepLineArr[i+2][j+1])
				fourrsheePleftX += 1;
			else {
				fourrsheePleftY += 1;
				if(fourrsheePleftY == fourRsheepLineArr[i+2][j+2]) {
//					JOptionPane.showMessageDialog(null, "결과는 [짝] 입니다.");
					LineWayflag = false;
				}
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
	  }
	}




}

