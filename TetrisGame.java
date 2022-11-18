
public class TetrisGame {
	private TetrisBlock currentBlock;
	private int[][] currentField;

	public TetrisGame() {
		this.currentBlock = new TetrisBlock(new int[][] {{1,1,1},{0,1,0},{0,0,0}});
		System.arraycopy(TetrisField.field, 0, currentField, 0, TetrisField.field.length);
	}

	public void updateGame() {
		int[][] fieldDump = new int[TetrisRun.FIELD_HEIGHT][TetrisRun.FIELD_WIDTH];
		System.arraycopy(currentField, 0, fieldDump, 0, currentField.length);
		this.currentBlock.addY();

		for(int i=0; i < 3; i++) {
			for(int j=0; j < 3; j++) {
				int value = this.currentBlock.getBlockValue(j, i);
				//フィールドの配列にブロックを設置
			}
		}
	}
}
