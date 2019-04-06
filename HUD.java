import java.awt.Color;
import java.awt.Graphics;

public class HUD {

	public static int TIME = 10000;

	public void tick() {
		TIME--;
		TIME = MainGame.clamp(TIME, 0, 10000);
	}

	public void render(Graphics g, int score) {
		g.setColor(Color.green);
		g.fillRect(530, 400, 140, (int)(.038*TIME));
		g.setColor(Color.black);
		g.drawRect(530, 400, 140, 380);
		g.drawString("Score: " + score, 600, 100);

	}

}
