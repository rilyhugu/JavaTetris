//TODO
//ゲームオーバーを実装する
//NEXTを実装する
//HOLDを実装する
//スコアを実装する

//ブロッククラスのcloneメソッド完成
//消えた後に下に落ちる処理をリファクタリング

public class TetrisRun implements Runnable {
	public static final int FIELD_HEIGHT = 20;
	public static final int FIELD_WIDTH = 8;
	public static final int MAX_GAME_TICK = 50;
	public static int GameTick;

	private TetrisGUI GUI;
	private TetrisGame Game;

	public TetrisRun() {
		this.GUI = new TetrisGUI();
		this.Game = new TetrisGame();
		TetrisRun.GameTick = 0;
	}


	public static void main(String[] args) {
		Thread tetris = new Thread(new TetrisRun());
		tetris.start();
	}


	public void run() {
		try {
			while(true) {
				Thread.sleep(10);
				Game.updateGame();
				GUI.repaint();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public TetrisGame getGame() {
		return this.Game;
	}
}
