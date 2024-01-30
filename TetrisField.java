import java.awt.Color;
import java.util.HashMap;

public class TetrisField {
	public static int[][] field = new int[TetrisRun.FIELD_HEIGHT][TetrisRun.FIELD_WIDTH];
	public static HashMap<String, Color> color = new HashMap<>();

	static {
		for(int i=0; i < TetrisRun.FIELD_HEIGHT; i++) {
			for(int j=0; j < TetrisRun.FIELD_WIDTH; j++) {
				TetrisField.field[i][j] = 0;
			}
		}
	}
}
