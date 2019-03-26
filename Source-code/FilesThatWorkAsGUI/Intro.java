import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Intro {

	public void tick() {
		
	}
	
	public void  render(Graphics g) throws MalformedURLException, IOException {
		BufferedImage intro = ImageIO.read(new URL("https://raw.githubusercontent.com/jshenny/T07G5/master/Source-code/resources/intro.png"));
		BufferedImage play = ImageIO.read(new URL("https://raw.githubusercontent.com/jshenny/T07G5/master/Source-code/resources/play.png"));
		
		g.drawImage(intro, 0, 0, 695, 810, null);
		g.drawImage(play, 250, 373, 200, 120, null);
	
		if (MainGame.state == MainGame.GameState.PAUSE) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Jokerman", Font.BOLD, 52));
			g.drawString("Game Paused", 165, 85);
		}
	}
	
}
