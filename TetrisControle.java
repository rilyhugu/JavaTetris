import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TetrisControle implements KeyListener{

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
				TetrisGame.currentBlockAddX();
				break;
			case KeyEvent.VK_LEFT:
				TetrisGame.currentBlockSubtractX();
				break;
			case KeyEvent.VK_DOWN:
				TetrisGame.currentBlockInstantMove();
				break;
			case KeyEvent.VK_UP:
				TetrisGame.currentBlockRotateRight();
				break;
			case KeyEvent.VK_SPACE:
				TetrisGame.holdCurrentBlock();
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

}
