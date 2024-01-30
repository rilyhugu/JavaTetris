import java.awt.Color;
import java.util.HashMap;
import java.util.Random;

public class TetrisBlock implements Cloneable{
	private int posX;
	private int posY;
	private int[][] block;
	private int disableNum;
	private Color color;

	public TetrisBlock(int disableNum) {
		this.disableNum = disableNum;
		this.posX = 0;
		this.posY = 0;
		this.block = getRandomBlock();
	}


	//Control X
	public void addX() {
		posX++;
	}
	public void subtractX() {
		posX--;
	}

	//Controle Y
	public void addY() {
		posY++;
	}
	public void subtractY() {
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

	public void setBlock(int[][] newBlock) {
		this.block = newBlock;
	}

	public Color getColor() {
		return this.color;
	}

	public int getDisableNum() {
		return this.disableNum;
	}

	private int[][] getRandomBlock() {
		int[][][] blocks = new int[][][] {
			//棒
			{{1}, {1}, {1}, {1}},
			//左L
			{{0, 1}, {0, 1}, {1, 1}},
			//右L
			{{1, 0}, {1, 0}, {1, 1}},
			//正方形
			{{1, 1}, {1, 1}},
			//左S
			{{0, 1, 1}, {1, 1, 0}},
			//右S
			{{1, 1, 0}, {0, 1, 1}},

			//T
			{{1, 1, 1}, {0, 1, 0}}
		};
		HashMap<Integer, Color> color = new HashMap<>();
		color.put(0, Color.CYAN);
		color.put(1, Color.BLUE);
		color.put(2, Color.ORANGE);
		color.put(3, Color.YELLOW);
		color.put(4, Color.GREEN);
		color.put(5, Color.RED);
		color.put(6, Color.MAGENTA);

		Random rand = new Random();
		int random;
		while(true) {
			random = rand.nextInt(blocks.length);
			if(random != getDisableNum()) {
				break;
			}
		}

		this.disableNum = random;
		this.color = color.get(random);
		return blocks[random];
	}

	public TetrisBlock clone() {
		TetrisBlock obj = new TetrisBlock(this.disableNum);

		//フィールドもコピーしよう

		return obj;
	}
}
