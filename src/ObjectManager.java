import java.awt.Graphics;
import java.util.ArrayList;

public class ObjectManager {
	ArrayList<Projectile> p;
	Rocketship r;

	ObjectManager(Rocketship r) {
		
		this.r = r;
		p = new ArrayList<Projectile>();
	}

	void update() {
		r.update();
for (int i = 0; i<p.size(); i++) {
	p.get(i).update();
}
	}

	void draw(Graphics g) {
		r.draw(g);
		for (int i = 0; i<p.size(); i++) {
			p.get(i).draw(g);
		}
	}
	void addProjectile(Projectile pro) {
	   p.add(pro);
		
		
	}
}
