package mini.view.sadariGameView;

public class sadariRand {
	
	// 3줄 좌출발 양 위치
	int sheePleftX = 155;
	int sheePleftY = 35;
	
	// 3줄 우출발 양위치
	int rsheePleftX = 395;
	int rsheePleftY = 35;
	
	//4줄 왼쪽 출발
	int foursheePleftX = 155;
	int foursheePleftY = 35;
	//4줄 오른쪽 출발
	int fourrsheePleftX = 395;
	int fourrsheePleftY = 35;
	
	// 홀짝 랜덤
	private int oddeven = 0;
	
	// 좌우 랜덤
	private int LR = LrRandom();
	
	// 줄 랜덤
	private int Line = lineRandom();
	
	// 줄 라인 랜덤값 생성
	public int lineRandom() {
		
		int randWord = (int)(Math.random() * 7) + 1;
		
		if(randWord == 3 || randWord == 4 || randWord == 5) {
			randWord = 1;
		}else if(randWord == 6 || randWord == 7) {
			randWord = 2;
		}
		return randWord;
	}
	
	public int LrRandom() {
		
		int randWord = (int)(Math.random() * 7) + 1;
		
		if(randWord == 3 || randWord == 4 || randWord == 5) {
			randWord = 1;
		}else if(randWord == 6 || randWord == 7) {
			randWord = 2;
		}
		return randWord;
	}

	public int getOddeven() {
		return oddeven;
	}

	public void setOddeven(int oddeven) {
		this.oddeven = oddeven;
	}

	public int getLR() {
		return LR;
	}

	public void setLR(int lR) {
		LR = lR;
	}

	public int getLine() {
		return Line;
	}

	public void setLine(int line) {
		Line = line;
	}

	public int getSheePleftX() {
		return sheePleftX;
	}

	public void setSheePleftX(int sheePleftX) {
		this.sheePleftX = sheePleftX;
	}

	public int getSheePleftY() {
		return sheePleftY;
	}

	public void setSheePleftY(int sheePleftY) {
		this.sheePleftY = sheePleftY;
	}

	public int getRsheePleftX() {
		return rsheePleftX;
	}

	public void setRsheePleftX(int rsheePleftX) {
		this.rsheePleftX = rsheePleftX;
	}

	public int getRsheePleftY() {
		return rsheePleftY;
	}

	public void setRsheePleftY(int rsheePleftY) {
		this.rsheePleftY = rsheePleftY;
	}

	public int getFoursheePleftX() {
		return foursheePleftX;
	}

	public void setFoursheePleftX(int foursheePleftX) {
		this.foursheePleftX = foursheePleftX;
	}

	public int getFoursheePleftY() {
		return foursheePleftY;
	}

	public void setFoursheePleftY(int foursheePleftY) {
		this.foursheePleftY = foursheePleftY;
	}

	public int getFourrsheePleftX() {
		return fourrsheePleftX;
	}

	public void setFourrsheePleftX(int fourrsheePleftX) {
		this.fourrsheePleftX = fourrsheePleftX;
	}

	public int getFourrsheePleftY() {
		return fourrsheePleftY;
	}

	public void setFourrsheePleftY(int fourrsheePleftY) {
		this.fourrsheePleftY = fourrsheePleftY;
	}
	
	
}
