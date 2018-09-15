import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static BufferedImage alienImg;

	public static BufferedImage rocketImg;

	public static BufferedImage bulletImg;

	public static BufferedImage spaceImg;

	boolean moveUp;
	boolean moveDown;
	boolean moveRight;
	boolean moveLeft;
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
		try {

			alienImg = ImageIO.read(this.getClass().getResourceAsStream("alien.png"));

			rocketImg = ImageIO.read(this.getClass().getResourceAsStream("rocket.png"));

			bulletImg = ImageIO.read(this.getClass().getResourceAsStream("bullet.png"));

			spaceImg = ImageIO.read(this.getClass().getResourceAsStream("space.png"));

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}
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

		g.drawImage(GamePanel.spaceImg, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
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
		g.drawString("You killed " + o.GetScore() / 2 + " enemies", 178, 450);

	}

	void updateMenuState() {

	}

	void updateGameState() {
		o.checkBoundaries();
		if (r.isAlive == false) {
			currentState = END_STATE;
		}
		o.update();
		o.manageEnemies();
		o.checkCollision();
		o.purgeObjects();
		if (moveUp == true) {
			r.y -= r.speed;

		}
		if (moveDown == true) {
			r.y += r.speed;
		}
		if (moveRight == true) {
			r.x += r.speed;
		}
		if (moveLeft == true) {
			r.x -= r.speed;
		}

	}

	void updateEndState() {
		o.reset();
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

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (currentState == END_STATE) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				r = new Rocketship(250, 700, 50, 50);
				o = new ObjectManager(r);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (currentState == MENU_STATE) {
				JOptionPane.showMessageDialog(null,
						"Use the arrow keys to move and space to shoot. Good luck and dont die!!", "Intsructions",
						END_STATE);
			}
		}

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
			moveLeft = true;
			moveRight = false;
			moveDown = false;
			moveUp = false;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			moveLeft = false;
			moveRight = true;
			moveDown = false;
			moveUp = false;
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			moveLeft = false;
			moveRight = false;
			moveDown = false;
			moveUp = true;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			moveLeft = false;
			moveRight = false;
			moveDown = true;
			moveUp = false;
		}
		/*
		 * else if( e.getKeyCode() != KeyEvent.VK_DOWN){ if(e.getKeyCode() !=
		 * KeyEvent.VK_UP) { if(e.getKeyCode() != KeyEvent.VK_RIGHT) { if(e.getKeyCode()
		 * != KeyEvent.VK_LEFT) { moveRight = false; moveLeft = false; moveUp = false;
		 * moveDown = false; }}}}
		 */
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			o.addProjectile(new Projectile(r.x + 25, r.y, 10, 10));
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
