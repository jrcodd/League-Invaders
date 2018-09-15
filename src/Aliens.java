import java.awt.Graphics;
import java.util.Random;

public class Aliens extends GameObject {
	Random r;

	Aliens(int x, int y, int width, int height) {
		super(x, y, width, height);
		r = new Random();
	}

	void update() {
		super.update();
		if (isAlive) {
			for (int i = 0; i <r.nextInt(200); i++) {
				x+=1;
			}
			y += 3;
			for (int i = 0; i <r.nextInt(200); i++) {
				x-=1;
			}
			y+=4;
			if (y > LeagueInvaders.HEIGHT) {
				isAlive = false;
			}
		}
		

	}

	void draw(Graphics g) {
		
		if (isAlive) {
			g.drawImage(GamePanel.alienImg, x, y, width, height, null);

		}
		if (isAlive == false) {
			
		}

	}
}
