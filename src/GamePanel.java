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
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

	Timer timer;
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	int currentState = MENU_STATE;
	Font titleFont;
	Font smallFont;
	
	 public static BufferedImage alienImg;
     public static BufferedImage rocketImg;
     public static BufferedImage bulletImg;
     public static BufferedImage spaceImg;

	Rocketship roc = new Rocketship(250, 700, 50, 50);

	ObjectManager om = new ObjectManager(roc);

	public GamePanel() {
		// TODO Auto-generated constructor stub
		timer = new Timer(1000 / 60, this);
		titleFont = new Font("Arial", Font.PLAIN, 48);
		smallFont = new Font("Arial", Font.PLAIN, 34);
		
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

	public void updateMenuState() {

	}

	public void updateGameState() {
		om.checkCollision();
		om.purgeObjects();
		om.update();
		om.manageEnemies();
		
		if(roc.isAlive==false) {
			currentState=END_STATE;
		}
	}

	public void updateEndState() {

	}

	public void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.width, LeagueInvaders.height);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("LEAGUE INVADERS", 12, 80);
		g.setFont(smallFont);
		g.drawString("Press ENTER to start", 80, 200);
		g.drawString("Press SPACE for instructions", 20, 350);
	}

	public void drawGameState(Graphics g) {
		g.drawImage(GamePanel.spaceImg, 0, 0, LeagueInvaders.width, LeagueInvaders.height, null);
		om.draw(g);

	}

	public void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.width, LeagueInvaders.height);

		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("Game Over", 112, 150);
		g.setFont(smallFont);
		g.drawString("You killed " + om.getScore() + " enemies", 80, 320);
		g.drawString("Press ENTER to restart", 60, 500);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();

		if (currentState == MENU_STATE) {
			updateMenuState();
		} else if (currentState == GAME_STATE) {
			updateGameState();
		} else if (currentState == END_STATE) {
			updateEndState();
		}
	}

	public void startGame() {
		timer.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		g.fillRect(10, 10, 100, 100);
		if (currentState == MENU_STATE) {

			drawMenuState(g);

		} else if (currentState == GAME_STATE) {

			drawGameState(g);

		} else if (currentState == END_STATE) {

			drawEndState(g);

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("This");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(currentState == END_STATE) {
				roc = new Rocketship(250, 700, 50, 50);
				om = new ObjectManager(roc);
			}
			
			currentState++;
		}
	
		if (currentState > END_STATE) {
			currentState = MENU_STATE;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			roc.direction = roc.LEFT;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			roc.direction = roc.RIGHT;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			om.addProjectile(new Projectile(roc.x + roc.width/2, roc.y, 10, 10));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			roc.direction = roc.STILL;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			roc.direction = roc.STILL;
		}
	}

}
