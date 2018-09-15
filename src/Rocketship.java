import java.awt.Graphics;

public class Rocketship extends GameObject {
	int speed;

	Rocketship(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 10;
	}

	void update() {
		super.update();
		if(isAlive) {
			
	}
		
	}

	void draw(Graphics g) {
		
		if(isAlive) {
			  g.drawImage(GamePanel.rocketImg, x, y, width, height, null);

		
		}
		if(isAlive == false) {
			
		}
	}

	void left() {
		x -= speed;

	}

	void right() {
		x += speed;
	}

	void up() {
		y -= speed;
	}

	void down() {
		y += speed;
	}

}
