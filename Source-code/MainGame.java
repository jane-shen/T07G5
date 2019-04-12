import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

import javax.imageio.ImageIO;

public class MainGame extends Canvas implements Runnable {

	private static final long serialVersionUID = 501367441143464273L;

	private static final int WIDTH = 700 , HEIGHT = 845;

	private Thread thread;
	private boolean running = false;
	private static boolean bottomHit = false;
	private Board board;
	private Shape shape;
	private Shape originalShape;
	private BufferedImage img;
	private Intro introScreen;
	private HUD hud;
	private EndGame gameOver;
	private int timeAfterLoss;
	private Rules rules;

	// The following public enumeration lists the different states the game can be in
	// It is public because we want to see access these outside the class
	public enum GameState{
		INTRO,
		RULES,
		START,
		ENDGAME,
		PAUSE;
	}

	 // We want to access this outside the class so it is public
	 // It is also static, although it can be default; it is simply easier to access
	 // by simply calling the class, rather than having to refer to an instance of a class
	 // It is on RULES state by default
	public static GameState state = GameState.RULES;

	/**
	 * default constructor that initializes elements of the game
	 * @throws IOException when loading an image but fails to do so
	 */
	public MainGame () throws IOException {
		shape = new Shape();
		originalShape = new Shape(shape);
		board = new Board();
		hud = new HUD();
		introScreen = new Intro();
		gameOver = new EndGame();
		rules = new Rules();
		img = ImageIO.read(new URL("https://raw.githubusercontent.com/jshenny/T07G5/master/Source-code/resources/background.png"));
		
		this.addKeyListener(new KeyInput(board));
		this.addMouseListener(rules);
		this.addMouseListener(gameOver);

		new Window(WIDTH, HEIGHT, "Tetros KMS", this);
	}
	
	/**
	 * This method initializes the time and shapes, starts the thread, and runs the game
	 */
	public synchronized void start() {
		timeAfterLoss = 0;
		shape.setRandomShape();
		shape.setShape(shape.getShape());
		originalShape = new Shape(shape);
		board.placeShape(shape);
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	/**
	 * This method stops exits the game completely
	 */
	public synchronized void stop() {
		try {
			render();
			thread.interrupt();
			running = false;
			System.exit(1);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Inside this method is what occurs while the game is running
	 */
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while (running) {
			
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			// the above code calls for the tick method at a set interval, giving us the ability to set a timer
			
			if (running) {
				try {
					render();
				} catch (IOException e) {
					e.printStackTrace();
				}
				frames++;
			}
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			// the above code (both if statements) keeps check of the frame rate of the game

				if (state == GameState.START) {
					if (!board.bottomCollision(shape))
						board.moveDown(shape);
					else
						bottomHit = true;
				}
				// the above code keeps the shape going down every second, always checking for bottom collision every time
				
				else if (state == GameState.ENDGAME)
					timeAfterLoss++;
				// the above code keeps track of time after the game ends so there is a delay before rendering the end game screen
			}

			if (state == GameState.START || state == GameState.PAUSE)
				Moves.makeMove(this);
				// makes the moves based on user input if currently playing the game (a.k.a. state is PAUSED or START)
		}
		stop();
	}

	/**
	 * This method occurs while playing the game every nanosecond, giving
	 * us the ability to make a timer to end the game after a while
	 * That timer is located in the HUD class
	 */
	private void tick() {
		if (state == GameState.START)
			hud.tick();
	}

	/**
	 * This method is for rendering the graphics of the game based on the current game state
	 * @throws IOException when it loads an image onto the window given the URL but fails to do so
	 */
	private void render() throws IOException {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.drawImage(img, 5, 5, this);
		// draws the background image onto the window

		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, 5, HEIGHT);
		g.fillRect(0, 805, WIDTH, 5);
		g.fillRect(690, 0, 5, HEIGHT);
		g.fillRect(0, 0, WIDTH, 5);
		g.fillRect(505, 0, 5, HEIGHT);

		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(510, 5, 180, 800);
		// these are the "gray areas" on the screen: the borders and where the score and timer are located

		g.setColor(Color.pink);
		for (int k = 0; k < board.getBoard().length; k++)
			for (int l = 0; l < board.getBoard()[0].length; l++) {
				if (board.getBoard()[k][l] != 0)
					g.fill3DRect(l * 50 + 9, k * 50 + 4, 48, 48, true);
		}
		// the above code draws the blocks on the screen

		if (state == GameState.START)
			hud.render(g, board.getScore());
			// renders the screen of the game while playing
		
		else if (state == GameState.ENDGAME) {
			if (timeAfterLoss > 3) {
				gameOver.render(g, board.getScore());
				HUD.TIME = 10000;
				// renders the screen of the end game after 4 seconds
			}
			else hud.render(g, board.getScore());
			// if the game has ended but 4 seconds hasn't passed, then game will keep showing the current "playing screen"
		}
		
		else if (state == GameState.INTRO || state == GameState.PAUSE)
			introScreen.render(g);
			// renders the INTRO or PAUSE screen, if game is on intro or is paused
		
		else if (state == GameState.RULES) 
			rules.render(g);
			// renders the rules page when game is on "rules mode"

		g.dispose();
		bs.show();

	}
	
	// Setters
	public void setBottomHit(boolean value) {
		bottomHit = value;
	}
	
	public void setNewShape() {
		shape = new Shape();
	}

	public void setOrigShape() {
		originalShape = new Shape(shape);
	}
	
	public void setTimeAfterLoss(int time) {
		timeAfterLoss = time;
	}

	//Getters
	public boolean isBottomHit() {
		return bottomHit;
	}

	public Shape getMainShape() {
		return shape;
	}

	public Board getBoard() {
		return board;
	}
	
	public Shape getOrigShape() {
		return originalShape;
	}

	// Methods
	
	/**
	 * Changes the game state if enter is pressed
	 */
	public void enterPressed() {
		if (state == GameState.PAUSE)
			state = GameState.START;
		else if (state == GameState.START)
			state = GameState.PAUSE;
	}

	/**
	 * Makes the board empty
	 */
	public void resetBoard() {
		for (int k = 0; k < board.getBoard().length; k++)
			for (int l = 0; l < board.getBoard()[0].length; l++) {
				board.getBoard()[k][l] = 0;
			}
	}
	
	/**
	 * This method is for setting a minimum and maximum value for a certain integer variable
	 * @param val is the current value of the variable
	 * @param min is the minimum allowed for the variable
	 * @param max is the maximum allowed for the variable
	 * @return max if variable is above it, min if variable is below it, and val if the variable is within the set limits
	 */
	public static int clamp(int val, int min, int max) {
		if (val > max)
			return max;
		else if (val < min)
			return min;
		else
			return val;
	}

}
