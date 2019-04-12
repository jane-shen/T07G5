import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.io.*;
import java.net.URL;
import java.awt.Color;


import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends Canvas{
	
	/**
	 *
	 */
	private static final long serialVersionUID = -8719665399369298571L;

	public Window (int width, int height, String title, MainGame game) throws IOException{
		JFrame introFrame = new JFrame();
		introFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		introFrame.setContentPane(new JPanel() {

			/**
			 *
			 */
			private static final long serialVersionUID = 8057320775622439001L;

			BufferedImage introScreen = ImageIO.read(new URL("https://raw.githubusercontent.com/jshenny/T07G5/master/Source-code/resources/intro.png"));
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(introScreen, 0, 0, null);
				int scores = 0;
				try {
		      		FileReader fileWithScore = new FileReader("scoreFile.txt");
		      		BufferedReader readScore = new BufferedReader(fileWithScore);
		      		scores = Integer.parseInt(readScore.readLine());
		      		readScore.close();
		    	} catch(IOException ioe){
		      		scores = 0;
			}	
			g.setColor(Color.WHITE);
			g.fillRect(100, 265, 280, 40);
			g.setFont(new Font("SansSerif", Font.BOLD, 32));
			g.setColor(Color.BLACK);
			g.drawString("HIGHSCORE: " + scores, 110, 300);
			}

		});

		JButton playButton = new JButton();
		playButton.setBorder(null);
		try {
			BufferedImage play = ImageIO.read(new URL("https://raw.githubusercontent.com/jshenny/T07G5/master/Source-code/resources/play.png"));
			playButton.setIcon(new ImageIcon(play));
		} catch (IOException e) {
		}

		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				introFrame.dispose();
				JFrame frame = new JFrame(title);

				frame.setPreferredSize(new Dimension(width, height));
				frame.setMaximumSize(new Dimension(width, height));
				frame.setMinimumSize(new Dimension(width, height));

				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.requestFocus();
				frame.setResizable(false);
				frame.setLocationRelativeTo(null);
				frame.add(game);
				frame.setVisible(true);

				game.start();
			}
		});

		introFrame.add(playButton);

		introFrame.setResizable(false);
		introFrame.setSize(500, 500);
		introFrame.setLocationRelativeTo(null);
		introFrame.setVisible(true);

	}

}