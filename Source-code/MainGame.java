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
	private int score;
	private Rules rules;

	public enum GameState{
		INTRO,
		RULES,
		START,
		ENDGAME,
		PAUSE;
	}

	public static GameState state = GameState.RULES;

	//default constructor that initializes elements of the game
	public MainGame () throws IOException {

		shape = new Shape();
		originalShape = new Shape(shape);
		board = new Board();
		hud = new HUD(this);
		introScreen = new Intro();
		gameOver = new EndGame(this);
		rules = new Rules(this);

		this.addKeyListener(new KeyInput(board));
		this.addMouseListener(rules);
		this.addMouseListener(gameOver);

		new Window(WIDTH, HEIGHT, "Tetros KMS", this);
	}

	public synchronized void start() {
		img = null;
		try {
		    img = ImageIO.read(new URL("https://raw.githubusercontent.com/jshenny/T07G5/master/Source-code/resources/background.png"));
		} catch (IOException e) {
		}
		timeAfterLoss = 0;
		shape.setRandomShape();
		shape.setShape(shape.getShape());
		originalShape = new Shape(shape);
		board.placeShape(shape);
		thread = new Thread(this);
		thread.start();
		running = true;
	}

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

				if (state == GameState.START) {
					if (!board.bottomCollision(shape))
						board.moveDown(shape);
					else
						bottomHit = true;
				}
				else if (state == GameState.ENDGAME)
					timeAfterLoss++;
			}

			if (state == GameState.START || state == GameState.PAUSE)
				Moves.makeMove(this);

		}
		stop();
	}

	private void tick() {
		if (state == GameState.ENDGAME)
			gameOver.tick();
		else if (state == GameState.INTRO || state == GameState.PAUSE)
			introScreen.tick();
		else if (state == GameState.START)
			hud.tick();
		else if (state == GameState.RULES)
			rules.tick();

	}

	private void render() throws IOException {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.drawImage(img, 5, 5, this);

		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, 5, HEIGHT);
		g.fillRect(0, 805, WIDTH, 5);
		g.fillRect(690, 0, 5, HEIGHT);
		g.fillRect(0, 0, WIDTH, 5);
		g.fillRect(505, 0, 5, HEIGHT);

		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(510, 5, 180, 800);

		g.setColor(Color.pink);
		for (int k = 0; k < board.getBoard().length; k++)
			for (int l = 0; l < board.getBoard()[0].length; l++) {
				if (board.getBoard()[k][l] != 0)
					g.fill3DRect(l * 50 + 9, k * 50 + 4, 48, 48, true);
		}

		if (state == GameState.START)
			hud.render(g, board.getScore());
		else if (state == GameState.ENDGAME) {
			if (timeAfterLoss > 4) {
				gameOver.render(g, board.getScore());
				score = 0;
				HUD.TIME = 10000;
			}
			else hud.render(g, board.getScore());
		}
		else if (state == GameState.INTRO || state == GameState.PAUSE)
			introScreen.render(g);
		else if (state == GameState.RULES) 
			rules.render(g);

		g.dispose();
		bs.show();

	}

	public static int clamp(int val, int min, int max) {
		if (val > max)
			return max;
		else if (val < min)
			return min;
		else
			return val;
	}

	public void setBottomHit(boolean value) {
		bottomHit = value;
	}

	public boolean isBottomHit() {
		return bottomHit;
	}

	public void setNewShape() {
		shape = new Shape();
	}

	public Shape getMainShape() {
		return shape;
	}

	public Board getBoard() {
		return board;
	}

	//Changes the game state if enter is pressed
	public void enterPressed() {
		if (state == GameState.PAUSE)
			state = GameState.START;
		else if (state == GameState.START)
			state = GameState.PAUSE;
	}

	public void setOrigShape() {
		originalShape = new Shape(shape);
	}

	public Shape getOrigShape() {
		return originalShape;
	}
	public void setTimeAfterLoss(int time) {
		timeAfterLoss = time;
	}
	public void resetBoard() {
		for (int k = 0; k < board.getBoard().length; k++)
					for (int l = 0; l < board.getBoard()[0].length; l++) {
						board.getBoard()[k][l] = 0;
					}
	}

}
