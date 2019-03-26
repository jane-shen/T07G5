import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	public static int TIME = 100;

	public void tick() {
		TIME--;
		TIME = MainGame.clamp(TIME, 0, 100);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.green);
		
		g.fillRect(530, 400, 140, (int) 3.8 * TIME);
		g.setColor(Color.black);
		g.drawRect(530, 400, 140, 380);
	}
	
}
