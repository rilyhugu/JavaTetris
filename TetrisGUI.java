import javax.swing.JFrame;

public class TetrisGUI extends JFrame {

	public TetrisGUI(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(new DrawField());
		setSize(400, 800);
		setResizable(false);
		setVisible(true);
	}
}
