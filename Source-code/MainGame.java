import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

public class MainGame extends Canvas implements Runnable {

	private static final long serialVersionUID = 501367441143464273L;

	public static final int WIDTH = 500, HEIGHT = (int) (500 * 1.6);

	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private Board board;
	private Shape shape;
	private boolean bottomHit = false;
	private boolean endGame = false;

	public MainGame () {
		shape = new Shape();
		board = new Board();
		shape.setRandomShape();
		shape.setShape(shape.getShape());
		board.placeShape(shape);
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler, board, shape));

		new Window(WIDTH + 120, HEIGHT + 180, "Tetros KMS", this);
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			render();
			thread.join();
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
				render();
				frames++;
			}

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println("FPS: " + frames);
				frames = 0;
			}

			if (endGame)
				break;
			
			switch (board.getDirection()) {
			case "left":
				if (!board.leftCollision(shape))
					board.moveLeft(shape);
				if (!board.bottomCollision(shape))
					board.moveDown(shape);
				else
					bottomHit = true;
				board.setDirection("");
				break;
			case "right":
				if (!board.rightCollision(shape))
					board.moveRight(shape);
				if (!board.bottomCollision(shape))
					board.moveDown(shape);
				else
					bottomHit = true;
				board.setDirection("");
				break;
			case "down":
				if (!board.bottomCollision(shape))
					board.moveDown(shape);
				else
					bottomHit = true;
				board.setDirection("");
				break;
			case "space":
				while (!board.bottomCollision(shape))
					board.moveDown(shape);
				board.checkFullRow();
				shape = new Shape();
				shape.setRandomShape();
				shape.setShape(shape.getShape());
				board.placeShape(shape);
				board.checkFullRow();
				if (board.bottomCollision(shape))
					endGame = true;
			default:
				board.setDirection("");
			}
			if (bottomHit) {
				board.checkFullRow();
				shape = new Shape();
				shape.setRandomShape();
				shape.setShape(shape.getShape());
				board.placeShape(shape);
				board.checkFullRow();
				bottomHit = false;
				if (board.bottomCollision(shape))
					endGame = true;
			}
		}
		stop();
	}

	private void tick() {
		handler.tick();
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.GRAY);
		g.fillRect(25, 25, WIDTH, HEIGHT);
		g.setColor(Color.BLACK);
		for (int i = WIDTH/10 + 25; i <= WIDTH; i += WIDTH/10)
			g.fillRect(i-1, 25, 2, HEIGHT);
		for (int j = HEIGHT/16 + 25; j <= HEIGHT; j += HEIGHT/16)
			g.fillRect(25, j-1, WIDTH, 2);
		g.setColor(Color.pink);
		for (int k = 0; k < board.getBoard().length; k++)
			for (int l = 0; l < board.getBoard()[0].length; l++)
				if (board.getBoard()[k][l] == 1)
					g.fillRect((l+1)  * (HEIGHT/16) - 23, (k+1) * (WIDTH/10) - 23, 46, 46);


		handler.render(g);

		g.dispose();
		bs.show();
	}

	public static void main(String args[]) {
		new MainGame();

	}
}
