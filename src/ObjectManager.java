import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	int score;
	ArrayList<Projectile> p;
	ArrayList<Aliens> a;
	Rocketship r;
	long enemyTimer;
	int enemySpawnTime;

	int GetScore() {
		return score;
	}

	ObjectManager(Rocketship r) {
		score = 0;

		this.r = r;
		a = new ArrayList<Aliens>();
		p = new ArrayList<Projectile>();
		enemyTimer = 0;
		enemySpawnTime = 1538;
	}

	void update() {
		r.update();
		for (int i = 0; i < p.size(); i++) {
			p.get(i).update();
		}
		for (int i = 0; i < a.size(); i++) {
			a.get(i).update();
		}
		checkCollision();
	}

	void draw(Graphics g) {
		r.draw(g);
		for (int i = 0; i < p.size(); i++) {
			p.get(i).draw(g);
		}
		for (int i = 0; i < a.size(); i++) {
			a.get(i).draw(g);
		}
	}

	void addProjectile(Projectile pro) {
		p.add(pro);

	}

	void addAlien(Aliens e) {
		a.add(e);

	}

	public void manageEnemies() {

		if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {
			addAlien(new Aliens(new Random().nextInt(LeagueInvaders.WIDTH), 0, 50, 50));

			enemyTimer = System.currentTimeMillis();
		}
	}

	void checkCollision() {
		for (Aliens a : a) {

			if (r.collisionBox.intersects(a.collisionBox)) {

				r.isAlive = false;
				System.out.println("death");
			}

		}
		for (Projectile pro : p) {
			for (Aliens al : a) {
				if (pro.collisionBox.intersects(al.collisionBox)) {
					al.isAlive = false;
					pro.isAlive = false;
					score += 1;
					System.out.println("kill");
				}
			}

		}

	}

	public void reset() {
		p.clear();
		a.clear();
	}

	public void purgeObjects() {
		for (int i = 0; i < a.size(); i++) {
			if (a.get(i).isAlive == false) {
				a.remove(i);
				
			}
		}
		for (int i = 0; i < p.size(); i++) {
			if(p.get(i).isAlive == false) {
				p.remove(i);
			}
		}
	}

	void checkBoundaries() {
		if (r.x >= 550) {
			r.x -=r.speed;
		} else if (r.x <= 0) {
			r.x += r.speed;
		}
		if (r.y <= 0) {
			r.y +=r.speed; 
		}
		if (r.y >= 880) {
			r.y -=r.speed;
		}
	}

}
