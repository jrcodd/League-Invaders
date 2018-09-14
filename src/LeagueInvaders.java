import java.awt.Dimension;

import javax.swing.JFrame;

public class LeagueInvaders {
	JFrame f;
	static final int HEIGHT = 900;
	static final int WIDTH = 600;
	GamePanel gp;

	LeagueInvaders() {
		f = new JFrame();
		gp = new GamePanel();
	}

	public static void main(String[] args) {
		LeagueInvaders LI = new LeagueInvaders();
		LI.setup();
	}

	void setup() {
		f.add(gp);
		f.addKeyListener(gp);
		f.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(WIDTH, HEIGHT);
		f.pack();
		gp.startGame();
	}
}
