import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	
	private Board board;
	
	public KeyInput(Board board) {
		this.board = board;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
			switch (key) {
				case KeyEvent.VK_LEFT:
					board.setDirection("left");
					System.out.println(key);
					break;
				case KeyEvent.VK_UP:
					board.setDirection("up");
					System.out.println(key);
					break;
				case KeyEvent.VK_RIGHT:
					board.setDirection("right");
					System.out.println(key);
					break;
				case KeyEvent.VK_DOWN:
					board.setDirection("down");
					System.out.println(key);
					break;
				case KeyEvent.VK_SPACE:
					board.setDirection("space");
					System.out.println(key);
				}
		}

}
