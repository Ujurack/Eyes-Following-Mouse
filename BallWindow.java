package ballTracker;

import javax.swing.JFrame;

public class BallWindow{

	public static void main(String[] args) {
		new BallWindow(800,800);
	}
	private BallWindow(int Width, int Height) {
		JFrame f = new JFrame("Ball Tracker");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setContentPane(new BallsPanel(Width,Height));
		f.setResizable(false);
		f.pack();
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		
	}

}
