import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Board board = new Board();
    Shape shape = new Shape();
    shape.setRandomShape();
    shape.setShape(shape.getShape());
    Shape originalShape = new Shape(shape);
    board.placeShape(shape);
    System.out.println("Welcome to Tetris :)");
    System.out.println();
    board.print2D();

//This creates a loop to keep the game going until the game is over
    while (true){

      Scanner move = new Scanner(System.in);
      System.out.println("Press spacebar to respawn a random tetromino");
      System.out.println("Press 's' to move down");
      System.out.println("Press 'a' to move left");
      System.out.println("Press 'd' to move right");
      System.out.println("Press 'w' to move rotate left");
      System.out.println("Press 'e' to move rotate right");
      
// checks the user for inputs
      if (move.hasNextLine()){
        String input = move.nextLine();

        if (input.equals(" ")){
          while (!board.bottomCollision(shape))
            board.moveDown(shape);
            
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
        else if (input.equals("w") && !board.bottomCollision(shape)) {
          board.rotateLeft(shape, originalShape);
          //if (!board.bottomCollision(shape))
            //board.moveDown(shape);
          board.print2D();
        }
        else if (input.equals("e") && !board.bottomCollision(shape)) {
          board.rotateRight(shape, originalShape);
          //if (!board.bottomCollision(shape))
            //board.moveDown(shape);
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
          originalShape = new Shape(shape);
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