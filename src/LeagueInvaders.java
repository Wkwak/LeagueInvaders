
import java.awt.Dimension;

import javax.swing.JFrame;

public class LeagueInvaders {

	JFrame frame;
	final static int width = 500;
	final static int height = 800;
	GamePanel gp;

	public LeagueInvaders() {
		// TODO Auto-generated constructor stub
		frame = new JFrame();
		gp = new GamePanel();

	}

	public static void main(String[] args) {
		LeagueInvaders invaders = new LeagueInvaders();
		invaders.setup();
	}

	public void setup() {
		frame.add(gp);
		frame.getContentPane().setPreferredSize(new Dimension(width, height));
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		gp.startGame();
		frame.addKeyListener(gp);
	}

}
