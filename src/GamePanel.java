import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	ObjectManager o;
	Rocketship r;
	Timer t;
	Font titleFont;
	Font toStartFont;
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	int currentState = 0;

	GamePanel() {
		t = new Timer(1000 / 60, this);
		titleFont = new Font("Arial", Font.BOLD, 50);
		toStartFont = new Font("Arial", Font.PLAIN, 30);
		r = new Rocketship(250, 700, 50, 50);
		o = new ObjectManager(r);
	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);

		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("LEAGUE INVADERS", 75, 250);
		g.setFont(toStartFont);
		g.setColor(Color.yellow);
		g.drawString("Press ENTER to start", 150, 450);
		g.drawString("Press SPACE for instructions", 100, 650);
	}

	void drawGameState(Graphics g) {
		g.setColor(Color.BLACK);

		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		o.draw(g);
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);

		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("Game Over", 166, 250);
		g.setFont(toStartFont);
		g.setColor(Color.black);
		g.drawString("Press ENTER to restart", 150, 650);
		g.drawString("You killed" + " enemies", 178, 450);

	}

	void updateMenuState() {

	}

	void updateGameState() {
		o.update();
	}

	void updateEndState() {

	}

	@Override

	public void paintComponent(Graphics g) {
		if (currentState == MENU_STATE) {

			drawMenuState(g);

		} else if (currentState == GAME_STATE) {

			drawGameState(g);

		} else if (currentState == END_STATE) {

			drawEndState(g);

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (currentState == MENU_STATE) {

			updateMenuState();

		} else if (currentState == GAME_STATE) {

			updateGameState();

		} else if (currentState == END_STATE) {

			updateEndState();

		}
		repaint();
	}

	void startGame() {
		t.start();

	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("ok");

	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("thing 2");
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == MENU_STATE) {
				currentState = GAME_STATE;

			} else if (currentState == GAME_STATE) {
				currentState = END_STATE;

			} else if (currentState == END_STATE) {

				currentState = MENU_STATE;

			}
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			r.left();
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			r.right();
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			r.up();
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			r.down();
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			o.addProjectile(new Projectile(r.x+ 25, r.y, 10, 10));
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("done with thing 2");
	}
}
