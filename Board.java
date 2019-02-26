import java.util.Arrays;

public class Board extends Shape{
  private Shape piece;
  private int pieceX;
  private int pieceY;
  private int[][] board;
  private int maxX;
  private int maxY;

  public Board() {
    piece = new Shape();
    board = new int[16][10];

  }

  public int[][] getBoard(){
    return board;
  }

  public void placeShape(Shape shape){
      maxY = shape.getY(1);
      for (int i = 0; i<4; i++){
        if (maxY < shape.getY(i)){
          maxY = shape.getY(i);
        }
      }
      maxX = shape.getX(1);
      for (int j = 0; j < 4; j++){
        if (maxX < shape.getX(j)){
          maxX = shape.getX(j);
        }
      }
      if (shape.getShape() != ShapeType.NoShape){
        for (int k = 0; k < 4; k++){
          int x = shape.getX(k);
          int y = shape.getY(k);
          board[maxY-y][5+x-maxX] = 1;
          }
        }
      }


  public void checkFullRow(){
    for (int i = 0; i < 16; i++){
      int increment = 0;
      for (int j = 0; j < 10; j++){
        if (board[i][j] != 0){
          increment += 1;
      }
    }
      if (increment == 10){
        for (int col = 0 ; col < 10; col++){
          board[i][col] = 0;
        }
      }
    }
  }
  public void print2D(){
			for (int row = 0; row < 16; row++){
					for (int col = 0; col < 10; col++){
							System.out.print(" " + board[row][col]);
					}
					System.out.println();
			}
	}

  public void clearBoard(int[][] board) {
    for (int i = 0; i < 16; i++){
				for (int j = 0; j < 10; j++) {
            board[i][j] = 0;
          }
    }
  }

  public void moveLeft(Shape shape){
    if (shape.getShape() != ShapeType.NoShape){
      for (int k = 0; k < 4; k++){
        int x = shape.getX(k);
        int y = shape.getY(k);
        board[maxY-y][5+x-maxX] = 0;
        }
      }

    for (int x = 0; x < 4; x++)
      shape.setNewX(x, shape.getX(x)-1);

    if (shape.getShape() != ShapeType.NoShape){
      for (int k = 0; k < 4; k++){
        int x = shape.getX(k);
        int y = shape.getY(k);
        board[maxY-y][5+x-maxX] = 1;
        }
      }

  }

  public void moveRight(Shape shape){
    if (shape.getShape() != ShapeType.NoShape){
      for (int k = 0; k < 4; k++){
        int x = shape.getX(k);
        int y = shape.getY(k);
        board[maxY-y][5+x-maxX] = 0;
        }
      }

      for (int x = 0; x < 4; x++)
        shape.setNewX(x, shape.getX(x)+1);

      if (shape.getShape() != ShapeType.NoShape){
        for (int k = 0; k < 4; k++){
          int x = shape.getX(k);
          int y = shape.getY(k);
          board[maxY-y][5+x-maxX] = 1;
          }
        }
    }

  public void moveDown(Shape shape){
    if (shape.getShape() != ShapeType.NoShape){
      for (int k = 0; k < 4; k++){
        int x = shape.getX(k);
        int y = shape.getY(k);
        board[maxY-y][5+x-maxX] = 0;
        }
      }

    for (int y = 0; y < 4; y++){
    shape.setNewY(y, shape.getY(y)-1);
    }

    if (shape.getShape() != ShapeType.NoShape){
      for (int k = 0; k < 4; k++){
        int x = shape.getX(k);
        int y = shape.getY(k);
        board[maxY-y][5+x-maxX] = 1;
      }
    }
  }
    
    public boolean endGame() {
        int count = 0;
        int index = 0;
        for (int col = 0; col < 10; col++) {
            count = 0;
            for (int row = 0; row < 16; row++){
                if (board[row][col] == 1) {
                    count++;
                }  
            }
            if (count > 5) {
                index = col;
                break;
            }
        }
        if (count > 5 && board[0][index] == 1) {
            return true;
        } else {
            return false;
      }
    }
}
