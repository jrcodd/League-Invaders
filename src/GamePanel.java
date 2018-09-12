import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	Timer t;
	GameObject go;

	@Override

	public void paintComponent(Graphics g) {
		go.draw(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		go.update();
		repaint();
	}

	GamePanel() {
		t = new Timer(1000 / 60, this);
		go = new GameObject();
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

	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("done with thing 2");
	}
}
