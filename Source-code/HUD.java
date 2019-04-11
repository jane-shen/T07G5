import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;
import java.io.*;

public class HUD {

	public static int TIME = 10000;
	private MainGame game;

	public HUD (MainGame game){
		this.game = game;
	}

	public void tick() {
		TIME--;
		TIME = MainGame.clamp(TIME, 0, 10000);
		if (TIME == 0) {
			game.state = MainGame.GameState.ENDGAME;
		}
	}

	public void render(Graphics g, int score) {
		g.setColor(Color.blue);
		g.fillRect(530, 400, 140, (int)(.038*TIME));
		g.setColor(Color.black);
		g.drawRect(530, 400, 140, 380);
		g.setFont(new Font("Courier New", Font.BOLD, 24));
		g.drawString("Score: " + score, 540, 100);

	}

}
