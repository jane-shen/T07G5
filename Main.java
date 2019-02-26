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
        }
        else if (input.equals("s")){
          board.moveDown(shape);
          board.print2D();
        }
        else if (input.equals("a")){
          board.moveLeft(shape);
          board.print2D();
        }
        else if (input.equals("d")){
          board.moveRight(shape);
          board.print2D();
        }
        board.checkFullRow();
      }
    }
  }
}
