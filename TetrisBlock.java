
public class TetrisBlock {
	private int posX;
	private int posY;
	private int[][] block;

	public TetrisBlock(int[][] block) {
		this.posX = 0;
		this.posY = 0;
		this.block = block;
	}


	//Control X
	public void addX() {
		posX++;
	}
	public void subractX() {
		posX--;
	}

	//Controle Y
	public void addY() {
		posY++;
	}
	public void subractY() {
		posY--;
	}

	//Getter
	public int getX() {
		return posX;
	}
	public int getY() {
		return posY;
	}
	public int[][] getBlock() {
		return block;
	}
	public int getBlockValue(int x, int y) {
		return block[x][y];
	}

}
