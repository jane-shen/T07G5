import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class EndGame {

	public void tick() {
		
	}
	
	public void render(Graphics g) throws IOException {
		BufferedImage highscores = ImageIO.read(new File("C:\\Users\\gerar\\Desktop\\TeamProject\\TProject\\src\\resources\\highscore.png"));
		g.setColor(Color.black);
		g.fillRect(0, 0, 1000, 1000);
		
		g.drawImage(highscores, 50, 0, null);
	}
}
