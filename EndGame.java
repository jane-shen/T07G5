import java.awt.*;
import java.io.*;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class EndGame {
	private int oldScore;

	public void tick() {

	}

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
		ImageIcon icon = new ImageIcon(new URL("https://github.com/jshenny/T07G5/blob/master/Source-code/resources/highscore.png"));
    Image highscores = icon.getImage();
		g.fillRect(0, 0, 1000, 1000);
		g.drawImage(highscores, 0, 0, null);
	}
}
