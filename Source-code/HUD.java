import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;

public class HUD {

	public static int TIME = 10000;

	/**
	 * Keeps track of the timer and reduces time remaining every tick
	 * Once it reaches 0, game ends
	 */
	public void tick() {
		TIME--;
		TIME = MainGame.clamp(TIME, 0, 10000);
		if (TIME == 0) {
			MainGame.state = MainGame.GameState.ENDGAME;
		}
	}

	/**
	 * Renders the Time-Bar and Score to the right hand side of the screen
	 * @param g is the Graphics object to be drawn on
	 * @param score is the current score of the player
	 */
	public void render(Graphics g, int score) {
		g.setColor(Color.blue);
		g.fillRect(530, 400, 140, (int)(.038*TIME));
		g.setColor(Color.black);
		g.drawRect(530, 400, 140, 380);
		g.setFont(new Font("Courier New", Font.BOLD, 24));
		g.drawString("Score: " + score, 540, 100);

	}

}
