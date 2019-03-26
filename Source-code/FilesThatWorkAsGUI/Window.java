import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

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
