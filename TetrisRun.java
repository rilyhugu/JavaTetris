public class TetrisRun implements Runnable{
	public static final int FIELD_HEIGHT = 20;
	public static final int FIELD_WIDTH = 8;

	private TetrisGUI GUI;

	public TetrisRun() {
		this.GUI = new TetrisGUI();
	}


	public static void main(String[] args) {
		Thread tetris = new Thread(new TetrisRun());
		tetris.start();
	}


	public void run() {
		try {
			while(true) {
				Thread.sleep(500);
				GUI.repaint();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
