import java.io.IOException;

public class RunGame extends MainGame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2146986388815865469L;

	public RunGame() throws IOException {
		super();
	}

	public static void main(String[] args) throws IOException {
		/**
		Intro intro = new Intro();
		while (intro.introIsRunning()) {
			continue;
		}
		*/
		
		new MainGame();

	}

}
