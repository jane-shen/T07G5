public class Moves {

	public static void makeMove(MainGame game) {
		switch (game.getBoard().getDirection()) {

			//responsible for the block to move left
		case "left":
			if (!game.getBoard().leftCollision(game.getMainShape()))
				game.getBoard().moveLeft(game.getMainShape());
			game.getBoard().setDirection("");
			game.getBoard().print2D();
			break;

			//responsible for the block to move right
		case "right":
			if (!game.getBoard().rightCollision(game.getMainShape()))
				game.getBoard().moveRight(game.getMainShape());
			game.getBoard().setDirection("");
			game.getBoard().print2D();
			break;

			//responsible for the block to move down
		case "down":
			if (!game.getBoard().bottomCollision(game.getMainShape()))
				game.getBoard().moveDown(game.getMainShape());
			else
				game.setBottomHit(true);
			game.getBoard().setDirection("");
			game.getBoard().print2D();
			break;

			//responsible for the block to move all the way down until a it hits a block
		case "space":
			while (!game.getBoard().bottomCollision(game.getMainShape()))
				game.getBoard().moveDown(game.getMainShape());
			game.getBoard().checkFullRow();
			game.getBoard().setBoard();
			game.setNewShape();
			game.getMainShape().setRandomShape();
			game.getMainShape().setShape(game.getMainShape().getShape());
			game.setOrigShape();
			game.getBoard().placeShape(game.getMainShape());
			game.getBoard().checkFullRow();
			game.getBoard().print2D();
			if (game.getBoard().bottomCollision(game.getMainShape()))
				MainGame.state = MainGame.GameState.ENDGAME;
			game.getBoard().setDirection("");
			break;


		case "up":
			if (!game.getBoard().bottomCollision(game.getMainShape()))
				game.getBoard().rotateLeft(game.getMainShape(), game.getOrigShape());
			else
				game.setBottomHit(true);
			game.getBoard().setDirection("");
			game.getBoard().print2D();
			break;

			
		case "enter":
			game.enterPressed();
			game.getBoard().setDirection("");
			break;
		default:
			game.getBoard().setDirection("");
		}
		if (game.isBottomHit()) {
			game.getBoard().checkFullRow();
			game.getBoard().setBoard();
			game.setNewShape();
			game.getMainShape().setRandomShape();
			game.getMainShape().setShape(game.getMainShape().getShape());
			game.setOrigShape();
			game.getBoard().placeShape(game.getMainShape());
			game.getBoard().checkFullRow();
			game.getBoard().print2D();
			game.setBottomHit(false);
			if (game.getBoard().bottomCollision(game.getMainShape()))
				MainGame.state = MainGame.GameState.ENDGAME;
		}
	}

}
