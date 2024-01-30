import java.awt.Color;
import java.util.HashMap;

public class TetrisGame {
	private static TetrisBlock currentBlock;
	private static TetrisBlock holdBlock;
	private static int[][] currentField;
	private static HashMap<String, Color> currentColor;
	private static int disableNum;
	private static Boolean isHold;

	public TetrisGame() {
		currentBlock = new TetrisBlock(-1);
		currentField = copyArray(TetrisField.field);
		currentColor = new HashMap<>(TetrisField.color);
		isHold = false;

		disableNum = currentBlock.getDisableNum();
	}

	//ゲームの動作処理
	public void updateGame() {

		//ブロックの位置情報更新
		int[][] fieldDump = copyArray(currentField);
		HashMap<String, Color> colorDump = new HashMap<>(currentColor);

		TetrisField.field = currentField;
		TetrisField.color = currentColor;

		//表示更新
		for(int i = 0; i < TetrisGame.currentBlock.getBlock()[0].length; i++) {
			for(int j = 0; j < TetrisGame.currentBlock.getBlock().length; j++) {
				if(TetrisGame.currentBlock.getBlock()[j][i] == 1) {
					fieldDump[currentBlock.getY() + j][currentBlock.getX() + i] = currentBlock.getBlockValue(j, i);
					colorDump.put(String.valueOf(currentBlock.getY() + j) + String.valueOf(currentBlock.getX() + i), currentBlock.getColor());
				}
			}
		}
		//描画用配列更新
		TetrisField.field = fieldDump;
		TetrisField.color = colorDump;

		//落下処理
		if(TetrisRun.GameTick >= TetrisRun.MAX_GAME_TICK) {
			//これ以上下無理
			if(TetrisGame.currentBlock.getBlock().length + TetrisGame.currentBlock.getY() + 1 > TetrisRun.FIELD_HEIGHT || !checkMoveY()) {
				currentField = TetrisField.field;
				currentColor = TetrisField.color;

				//ブロック消滅
				int counter = 0;
				for(int i = 0; i < TetrisRun.FIELD_HEIGHT; i++) {
					for(int j = 0; j < TetrisRun.FIELD_WIDTH; j++) {
						if(currentField[i][j] == 1) {
							counter++;
						}
					}
					if(counter == TetrisRun.FIELD_WIDTH) {
						for(int k = 0; k < TetrisRun.FIELD_WIDTH; k++) {
							currentField[i][k] = 0;
						}
					}
					counter = 0;
				}

				//ブロック下に落とす
				sortField();

				//新ブロック配置
				currentBlock = new TetrisBlock(disableNum);
				isHold = false;

			//まだ下がある
			} else {
				currentBlock.addY();
				TetrisRun.GameTick = 0;
			}
		}
		TetrisRun.GameTick++;
	}

	//配列コピー用
	public static int[][] copyArray(int[][] array) {
		int[][] result = new int[array.length][];
		for(int i = 0; i < array.length; i++) {
			result[i] = array[i].clone();
			System.out.println();
		}
		return result;
	}

	//ブロックがY軸方向へ移動できるかチェック
	private static boolean checkMoveY() {
		for(int i = 0; i < TetrisGame.currentBlock.getBlock()[0].length; i++) {
			for(int j = 0; j < TetrisGame.currentBlock.getBlock().length; j++) {
				if(TetrisGame.currentBlock.getBlock()[j][i] == 1) {
					if(currentField[currentBlock.getY() + j + 1][currentBlock.getX() + i] == 1) {
						return false;
					}
				}
			}
		}
		return true;
	}

	//ブロックがX軸方向へ移動できるかチェック
	private static boolean checkMoveX(Boolean left) {
		int n = 1;
		if(left) {
			n = -1;
		}
		for(int i = 0; i < TetrisGame.currentBlock.getBlock()[0].length; i++) {
			for(int j = 0; j < TetrisGame.currentBlock.getBlock().length; j++) {
				if(TetrisGame.currentBlock.getBlock()[j][i] == 1) {
					if(currentField[currentBlock.getY() + j][currentBlock.getX() + i + n] == 1) {
						return false;
					}
				}
			}
		}
		return true;
	}

	//下に落とす
	private void sortField() {
		for(int o = 0; o < 50; o++) {
			int counter = 0;
			for(int i = 1; i < TetrisRun.FIELD_HEIGHT; i++) {
				for(int j = 0; j < TetrisRun.FIELD_WIDTH; j++) {
					if(currentField[i][j] == 0) {
						counter++;
					}
				}
				if(counter == TetrisRun.FIELD_WIDTH) {
					for(int k = 0; k < TetrisRun.FIELD_WIDTH; k++) {
						currentField[i][k] = currentField[i - 1][k];
						currentField[i - 1][k] = 0;
					}
				}
				counter = 0;
			}
		}
	}

	//ブロックを右へ移動
	public static void currentBlockAddX() {
		if(currentBlock.getX() + currentBlock.getBlock()[0].length < TetrisRun.FIELD_WIDTH) {
			if(checkMoveX(false)) {
				currentBlock.addX();
			}
		}
	}

	//ブロックを左へ移動
	public static void currentBlockSubtractX() {
		if(currentBlock.getX() - 1 >= 0) {
			if(checkMoveX(true)) {
				currentBlock.subtractX();
			}
		}
	}

	//ブロックを即着地
	public static void currentBlockInstantMove() {
		while(true) {
			if(TetrisGame.currentBlock.getBlock().length + TetrisGame.currentBlock.getY() < TetrisRun.FIELD_HEIGHT) {
				if(checkMoveY()) {
					currentBlock.addY();
					TetrisRun.GameTick = TetrisRun.MAX_GAME_TICK;
				} else {
					break;
				}
			} else {
				break;
			}
		}
	}

	//ブロックを右回転
	public static void currentBlockRotateRight() {
		int[][] rotate = new int[currentBlock.getBlock()[0].length][currentBlock.getBlock().length];

		int p = 0;
		for(int i = currentBlock.getBlock().length - 1; i  >= 0; i--) {
			int q = 0;
			for(int j = 0; j < currentBlock.getBlock()[0].length; j++) {
				rotate[q][p] = currentBlock.getBlock()[i][j];
				q++;
			}
			p++;
		}

		Boolean canRotate = true;

		if(rotate.length + TetrisGame.currentBlock.getY() <= TetrisRun.FIELD_HEIGHT && currentBlock.getX() + rotate[0].length <= TetrisRun.FIELD_WIDTH) {
			for(int i = 0; i < rotate[0].length; i++) {
				for(int j = 0; j < rotate.length; j++) {
					if(rotate[j][i] == 1) {
						if(currentField[currentBlock.getY() + j][currentBlock.getX() + i] == 1) {
							canRotate = false;
						}
					}
				}
			}

			for(int i = 0; i < rotate[0].length; i++) {
				for(int j = 0; j < rotate.length; j++) {
					if(rotate[j][i] == 1) {
						if(currentField[currentBlock.getY() + j][currentBlock.getX() + i] == 1) {
							canRotate = false;
						}
					}
				}
			}

			if(canRotate) {
				currentBlock.setBlock(rotate);
			}
		}
	}

	public TetrisBlock getHoldBlock() {
		return holdBlock;
	}

	//ブロックをホールド
	public static void holdCurrentBlock() {
		if(!isHold) {
			if(holdBlock == null) {
				currentBlock = new TetrisBlock(disableNum);
				holdBlock = currentBlock.clone();
			} else {
				currentBlock = holdBlock.clone();
			}
			TetrisRun.GameTick = 0;
			isHold = true;
		}
		/*
		if(!isHold) {
			if(currentBlock == null) {
				currentBlock = new TetrisBlock(disableNum);

			} else {
				currentBlock = holdBlock;
			}

			holdBlock = currentBlock;
			TetrisRun.GameTick = 0;
			isHold = true;
		}
		*/
	}
}
