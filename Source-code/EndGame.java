import java.awt.*;
import java.io.*;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.Runtime;

public class EndGame extends MouseAdapter{
	private int oldScore;
	private MainGame game;

	/**
	*this creates the constructor
	* @param game takes the current instance of the MainGame
	*/
	public EndGame(MainGame game){
		this.game = game;
	}

	// this checks if the play again button is pressed
    public void mousePressed(MouseEvent e) {
			int mx = e.getX();
			int my = e.getY();
			System.out.println(mx);
			System.out.println(my);
			if (mouseOver(mx, my, 285, 520, 125, 75)) {
				try{
					Runtime.getRuntime().exec("java RunGame");
					System.exit(1);
				} catch(Exception supere){}
			}
    }

	//checks if mouse is clicked over the a certain area that is provided by the parameter
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

/**
* @param g is the graphics you see in the game
* @param score is the score of the player
*/
	public void render(Graphics g, int score) throws IOException {
	try{
      	FileReader fileWithScore = new FileReader("scoreFile.txt");
      	BufferedReader readScore = new BufferedReader(fileWithScore);
      	oldScore = Integer.parseInt(readScore.readLine());
    } catch(IOException ioe){
      	oldScore = 0;
    }
		if (score > oldScore){
			try{
	      File file = new File("scoreFile.txt");
	      PrintWriter writer = new PrintWriter(file);
	      writer.println(score);
	      writer.close();
	    	} catch(IOException ioe){
	    }
		}

		BufferedImage highscores = ImageIO.read(new URL("https://raw.githubusercontent.com/jshenny/T07G5/master/Source-code/FilesThatWorkAsGUI/resources/highscore.png"));
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1000, 1000);
		g.drawImage(highscores, 50, 5, null);
		int scores = 0;
		try {
			FileReader fileWithScore = new FileReader("scoreFile.txt");
			BufferedReader readScore = new BufferedReader(fileWithScore);
			scores = Integer.parseInt(readScore.readLine());
		} catch(IOException ioe){
			scores = 0;
		}
		g.setColor(Color.WHITE);
		g.setFont(new Font("Courier New", Font.BOLD, 32));
		g.drawString("" + scores, 300, 300);

		g.drawString("Play", 310, 550);
		g.drawString("Again", 300, 585);
		g.drawRect(285, 520, 125, 75);

	}
}
