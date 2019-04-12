import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
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
	private BufferedImage highscores;
	private BufferedImage button;
	
	public EndGame () throws MalformedURLException, IOException {
		highscores = ImageIO.read(new URL("https://raw.githubusercontent.com/jshenny/T07G5/master/Source-code/resources/highscore.png"));
		button = ImageIO.read(new URL("https://raw.githubusercontent.com/jshenny/T07G5/master/Source-code/resources/play%20again.png"));
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
	
	/**
	* Saves the final score of the game in a file called scoreFile.txt
	* @param score this is the final score
	*/
	public void saveScore(int score) {
		try{
			FileReader fileWithScore = new FileReader("scoreFile.txt");
			BufferedReader readScore = new BufferedReader(fileWithScore);
			oldScore = Integer.parseInt(readScore.readLine());
			readScore.close();
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
	}

	/**
	 * This method looks at the score stored in the file called scoreFile.txt
	 * @return the score found in the file
	 */
	public int readScore() {
		try {
			FileReader fileWithScore = new FileReader("scoreFile.txt");
			BufferedReader readScore = new BufferedReader(fileWithScore);
			int scoreFound = Integer.parseInt(readScore.readLine());
			readScore.close();
			return scoreFound;
		} catch(IOException ioe){
			return 0;
		}
	}
	
	/**
	 * @param g is the graphics you see in the game
	 * @param score is the score of the player
	 */
	public void render(Graphics g, int score) throws IOException {
		int scores = 0;
		saveScore(score);
		scores = readScore();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1000, 1000);
		g.drawImage(highscores, 50, 5, null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Courier New", Font.BOLD, 32));
		g.drawString("" + scores, 300, 300);
		g.drawImage(button, 285, 520, null);
	}
}