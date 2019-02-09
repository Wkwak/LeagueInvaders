import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Rocketship extends GameObject {

	int speed;
	int direction = -1;
	final int LEFT = 0;
	final int RIGHT = 1;
	final int STILL = 2;

	public Rocketship(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 5;
	}

	public void update() {
		super.update();
		
		if(direction == LEFT) {
			x-=speed;
		}
		else if (direction == RIGHT) {
			x+=speed;
		}
		
	}

	public void draw(Graphics g) {
		  g.drawImage(GamePanel.rocketImg, x, y, width, height, null);
	}

}
