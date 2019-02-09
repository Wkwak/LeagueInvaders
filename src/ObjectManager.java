import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ObjectManager {
	
	long enemyTimer = 0;
	int enemySpawnTime = 1000;
	int score = 0;
	ArrayList<Alien> aliens = new ArrayList<>();
	
	Rocketship roc;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	public ObjectManager(Rocketship roc) {
		this.roc = roc; 
	}

	public void update() {
		
		roc.update();
		for(int i = 0 ; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}
		for(int i = 0; i < aliens.size(); i++) {
			aliens.get(i).update();
		}
	}

	public void draw(Graphics g) {
		for(int i = 0; i < aliens.size(); i++) {
			aliens.get(i).draw(g);
		}
		
		roc.draw(g);
		for(int i = 0 ; i < projectiles.size(); i++) {
			projectiles.get(i).draw(g);
		}

	}

	public void addProjectile(Projectile pro) {
		projectiles.add(pro);
	}
	
	public void manageEnemies(){
        if(System.currentTimeMillis() - enemyTimer >= enemySpawnTime){
                addAlien(new Alien(new Random().nextInt(LeagueInvaders.width), 0, 50, 50));

                enemyTimer = System.currentTimeMillis();
        }
	}
	
	public void purgeObjects() {
		for(int i = 0; i < aliens.size(); i++) {
			if(aliens.get(i).isAlive == false){
				aliens.remove(aliens.get(i));
			}
		}
	}
	
	public void addAlien(Alien alien) {
		aliens.add(alien);
	}
	
	public void checkCollision(){
		for(Alien a : aliens){
	        if(roc.collisionBox.intersects(a.collisionBox)){
	                roc.isAlive = false;
	                System.out.println("hit");
	               
	        }
	        for(Projectile p: projectiles) {
	        		if(p.collisionBox.intersects(a.collisionBox)) {
	        			a.isAlive = false;
	        			score++;
	        		}
	        }
		}

	}
	
	public int getScore() {
		return score;
	}

}
