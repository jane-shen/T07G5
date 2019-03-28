import java.util.Scanner;

public class GameStart {
	private Board board;
	private Shape shape;

	//this initiates the game printing out the board and picks a random shape to start off the game
	public void startGame(Board board, Shape shape) {
		this.board = new Board();
	    this.shape = new Shape();
	    shape.setRandomShape();
	    shape.setShape(shape.getShape());
	    board.placeShape(shape);
	    board.print2D();

	//This creates a loop to keep the game going until the game is over
	    while (true){

	      Scanner move = new Scanner(System.in);
	      System.out.println("Press spacebar to respawn a random tetromino");
	      System.out.println("Press 's' to move down");
	      System.out.println("Press 'a' to move left");
	      System.out.println("Press 'd' to move right");
	// checks the user for inputs
	      if (move.hasNextLine()){
	        String input = move.nextLine();

					//when the spacebar is clicked, the whole block will go all the way down
	        if (input.equals(" ")){
	          while (!board.bottomCollision(shape))
	            board.moveDown(shape);

							//this checks continuously to check if there is a full row filled with blocks
	          if (board.checkFullRow()){
	            System.out.println();
	            board.print2D();
	          }
	          shape = new Shape();
	          shape.setRandomShape();
	          shape.setShape(shape.getShape());
	          board.placeShape(shape);
	          System.out.println();
	          board.print2D();

						//breaks the loop once the there is an expected collison under the block
	          if (board.bottomCollision(shape))
	            break;
	        }

	        else if (input.equals("s") && !board.bottomCollision(shape)){
	            // when "s" is pressed, the shape will move down, but only if no collision will occur
	          board.moveDown(shape);
	          board.print2D();
	        }

	        else if (input.equals("a") && !board.leftCollision(shape)){
	            // when "a" is pressed, the shape will move to the left, but only if no collision will occur
	          board.moveLeft(shape);

	          if (!board.bottomCollision(shape))
						//allows the block to go down when there is no collision
	            board.moveDown(shape);
	          board.print2D();
	        }
	        else if (input.equals("d") && !board.rightCollision(shape)){
	          // when "d" is pressed, the shape will move to the right, but only if no collision will occur
	          board.moveRight(shape);
	          if (!board.bottomCollision(shape))
	            board.moveDown(shape);
	          board.print2D();
	        }
	        else if (board.bottomCollision(shape)){
	          // if the shape hits something below it (edge, or another shape), and
	          // the player attempts an invalid move after (tries going down one more,
	          // moves to the left/right, but collides, or inputs an invalid key)
	          // the player is automatically stopped and a new shape will respawn
	          if (board.checkFullRow()){
	            System.out.println();
	            board.print2D();
	          }
	          shape = new Shape();
	          shape.setRandomShape();
	          shape.setShape(shape.getShape());
	          board.placeShape(shape);
	          System.out.println();
	          board.print2D();

	          if (board.bottomCollision(shape))
	            break;

	          continue;
	        }
	      }
	    }
	    System.out.println("Game Over! :C");
	    Scanner end = new Scanner(System.in);
	    System.out.println("Press ENTER to Exit");
	    if (end.hasNextLine()){
	        end.close();
	    }
	}
}
