import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class MainGame extends Canvas implements Runnable {

	private static final long serialVersionUID = 501367441143464273L;

	private static final int WIDTH = 700 , HEIGHT = 845;

	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private Board board;
	private Shape shape;
	private Shape originalShape;
	private boolean bottomHit = false;
	private boolean endGame = false;
	private BufferedImage img;
	private HUD hud;
	private EndGame gameOver;
	
	public enum GameState{
		INTRO,
		START,
		ENDGAME;
	}
	
	public GameState state = GameState.START;

	public MainGame () throws IOException {
		shape = new Shape();
		originalShape = new Shape(shape);
		board = new Board();
		hud = new HUD();
		gameOver = new EndGame();
		
		handler = new Handler();
		this.addKeyListener(new KeyInput(board));

		new Window(WIDTH, HEIGHT, "Tetros KMS", this);
	}

	public synchronized void start() {
		img = null;
		try {
		    img = ImageIO.read(new URL("https://raw.githubusercontent.com/jshenny/T07G5/master/Source-code/resources/background.png"));
		} catch (IOException e) {
		}
		
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
		// int frames = 0;
		// int time = 120;
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// frames++;
			}

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				hud.tick();
				// System.out.println("FPS: " + frames);
				// frames = 0;
				// time--;
				// board.print2D();
				
				if (!board.bottomCollision(shape))
					board.moveDown(shape);
				else
					bottomHit = true;
			}
			
			if (endGame)
				state = GameState.ENDGAME;
			
			switch (board.getDirection()) {
			case "left":
				if (!board.leftCollision(shape))
					board.moveLeft(shape);
				board.setDirection("");
				board.print2D();
				break;
			case "right":
				if (!board.rightCollision(shape))
					board.moveRight(shape);
				board.setDirection("");
				board.print2D();
				break;
			case "down":
				if (!board.bottomCollision(shape))
					board.moveDown(shape);
				else
					bottomHit = true;
				board.setDirection("");
				board.print2D();
				break;
			case "space":
				while (!board.bottomCollision(shape))
					board.moveDown(shape);
				board.checkFullRow();
				board.setBoard();
				shape = new Shape();
				shape.setRandomShape();
				shape.setShape(shape.getShape());
				originalShape = new Shape(shape);
				board.placeShape(shape);
				board.checkFullRow();
				board.print2D();
				if (board.bottomCollision(shape))
					endGame = true;
				board.setDirection("");
				break;
			case "up":
				if (!board.bottomCollision(shape))
					board.rotateLeft(shape, originalShape);
				else
					bottomHit = true;
				board.setDirection("");
				board.print2D();
				break;
			default:
				board.setDirection("");
			}
			if (bottomHit) {
				board.checkFullRow();
				board.setBoard();
				shape = new Shape();
				shape.setRandomShape();
				shape.setShape(shape.getShape());
				originalShape = new Shape(shape);
				board.placeShape(shape);
				board.checkFullRow();
				board.print2D();
				bottomHit = false;
				if (board.bottomCollision(shape))
					endGame = true;
			}
		}
		stop();
	}

	private void tick() {
		if (state == GameState.ENDGAME)
			gameOver.tick();
		
		handler.tick();
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
		
		handler.render(g);
		
		if (state == GameState.START)
			hud.render(g);
		else if(state == GameState.ENDGAME)
			gameOver.render(g);
		
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
		
}
