import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

class DrawField extends JPanel {
	private Color color = Color.red;

	public void paintComponent(Graphics g) {
		//setColor();
		g.setColor(Color.black);
		for(int i=0; i < TetrisRun.FIELD_HEIGHT; i++) {
			for(int j=0; j < TetrisRun.FIELD_WIDTH; j++) {
				if(TetrisField.field[i][j] == 1) {
					g.setColor(this.color);
					g.fillRect(j*30, i*30, 30, 30);
				}
				//g.fillRect(j*30, i*30, 30, 30);
				g.setColor(Color.black);
				g.drawRect(j*30, i*30, 30, 30);
			}
		}
	}

	public void setColor() {
		if(this.color == Color.red) {
			this.color = Color.blue;
		} else {
			this.color = Color.red;
		}
	}
}