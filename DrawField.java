import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

class DrawField extends JPanel {

	public void paintComponent(Graphics g) {
		g.setColor(Color.black);
		for(int i=0; i < TetrisRun.FIELD_HEIGHT; i++) {
			for(int j=0; j < TetrisRun.FIELD_WIDTH; j++) {
				if(TetrisField.field[i][j] == 1) {
					if(TetrisField.color.get(String.valueOf(i) + String.valueOf(j)) != null) {
						g.setColor(TetrisField.color.get(String.valueOf(i) + String.valueOf(j)));
					} else {
						g.setColor(Color.RED);
					}
					g.fillRect(j*30, i*30, 30, 30);
				}
				//g.fillRect(j*30, i*30, 30, 30);
				g.setColor(Color.black);
				g.drawRect(j*30, i*30, 30, 30);
			}
		}
	}
}