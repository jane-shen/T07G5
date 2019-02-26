import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Board board = new Board();
    Shape shape = new Shape();
    shape.setRandomShape();
    shape.setShape(shape.getShape());
    board.placeShape(shape);
    System.out.println("Welcome to Tetris :)");
    System.out.println();
    board.print2D();

    while (true){

      Scanner move = new Scanner(System.in);
      System.out.println("Press spacebar to respawn a random tetromino");
      System.out.println("Press 's' to move down");
      System.out.println("Press 'a' to move left");
      System.out.println("Press 'd' to move right");

      if (move.hasNextLine()){
        String input = move.nextLine();

        if (input.equals(" ")){
          shape = new Shape();
          shape.setRandomShape();
          shape.setShape(shape.getShape());
          board.placeShape(shape);
          System.out.println();
          board.print2D();
          if (board.checkFullRow())
            board.print2D();
        }
        else if (input.equals("s") && !board.bottomCollision(shape)){
          board.moveDown(shape);
          board.print2D();
        }
        else if (input.equals("a") && !board.leftCollision(shape)){
          board.moveLeft(shape);
          board.print2D();
        }
        else if (input.equals("d") && !board.rightCollision(shape)){
          board.moveRight(shape);
          board.print2D();
        }
        else if (board.bottomCollision(shape)){
          shape = new Shape();
          shape.setRandomShape();
          shape.setShape(shape.getShape());
          board.placeShape(shape);
          System.out.println();
          board.print2D();
          if (board.checkFullRow())
            board.print2D();
          continue;
        }
      }
      if (board.endGame(shape)){
        break;
      }
    }
    System.out.println("Game Over! :C");


  }
}
