import java.awt.Font;
import java.awt.Graphics;

public class Projectile extends GameObject {
	int speed;
	int ammo;
	boolean outOfAmmo = false;
	Font ammoFont;

	Projectile(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 10;

	}

	void update() {
		super.update();
		if (isAlive) {
			y -= speed;
			if (y < 0) {
				isAlive = false;
			}
		}

	}

	void draw(Graphics g) {

		if (isAlive) {

			g.drawImage(GamePanel.bulletImg, x - 5, y, width, height, null);

		}
		if (isAlive == false) {

		}
	}

}
