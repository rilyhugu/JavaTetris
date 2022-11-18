
public class TetrisField {
	public static int[][] field = new int[TetrisRun.FIELD_HEIGHT][TetrisRun.FIELD_WIDTH];

	static {
		for(int i=0; i < TetrisRun.FIELD_HEIGHT; i++) {
			for(int j=0; j < TetrisRun.FIELD_WIDTH; j++) {
				if(i >= 18) {
					TetrisField.field[i][j] = 1;
				} else {
					TetrisField.field[i][j] = 0;
				}
			}
		}
	}
}
