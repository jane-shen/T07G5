import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;

public class Rules extends MouseAdapter {
	private MainGame game;

	public Rules (MainGame game){
		this.game = game;

	}
    public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		if (mouseOver(mx, my, 285, 620, 125, 75)) {
			if (game.state == MainGame.GameState.RULES) {
				game.state = MainGame.GameState.START;
			}
		}
    }
    public void mouseReleased(MouseEvent e) {
        
	}
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else 
			return false;
		} else 
		return false;
	}
    public void tick() {
        
    }

    public void render(Graphics g) throws MalformedURLException, IOException {
        BufferedImage background = ImageIO.read(new URL("https://raw.githubusercontent.com/jshenny/T07G5/master/Source-code/resources/tetros%20rules.png"));
		BufferedImage button = ImageIO.read(new URL("https://raw.githubusercontent.com/jshenny/T07G5/master/Source-code/resources/OKAY.png"));
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 700, 845);
		g.setColor(Color.WHITE);
		g.drawImage(background, 50, 5, null);
		g.drawImage(button, 285, 620, null);
        g.setFont(new Font("Courier New", Font.BOLD, 16));
        g.drawString("Press spacebar to respawn a random tetromino", 120, 200);
        g.drawString("Press the down arrow to move down", 120, 260);
        g.drawString("Press left arrow to move left", 120, 320);
        g.drawString("Press right arrow to move right", 120, 380);
        g.drawString("Press 'w' to rotate left", 120, 440);
		g.drawString("Press 'e' to rotate right", 120, 500);
		g.drawString("Press enter to pause", 120, 560);
	}
}
