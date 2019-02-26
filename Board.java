import java.util.Arrays;
import java.util.ArrayList;

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


  public boolean checkFullRow(){
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
        return true;
      }
    }
    return false;
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

  public boolean leftCollision(Shape shape){
    for (int k = 0; k < 4; k++){
      int x = shape.getX(k);
      if ((5+x-maxX-1) < 0){
        return true;
      }
    }

    ArrayList<Integer> leftMostIndex = new ArrayList<Integer>();
    int xvalue = shape.getX(0);
    leftMostIndex.add(0);
    for (int index = 1; index < 4; index++){
      if (xvalue > shape.getX(index)){
        xvalue = shape.getX(index);
        leftMostIndex.clear();
        leftMostIndex.add(index);
      }
      else if (xvalue == shape.getX(index)){
        leftMostIndex.add(index);
      }
    }

    for (int indexed : leftMostIndex){
      int yCoord = shape.getY(indexed);
      int xCoord = shape.getX(indexed);
      if (board[maxY-yCoord][5+xCoord-maxX-1] == 1){
        return true;
      }
    }
    return false;
  }

  public boolean rightCollision(Shape shape){
    for (int k = 0; k < 4; k++){
      int x = shape.getX(k);
      if ((5+x-maxX+1) > 9){
        return true;
      }
    }

    ArrayList<Integer> rightMostIndex = new ArrayList<Integer>();
    int xvalue = shape.getX(0);
    rightMostIndex.add(0);
    for (int index = 1; index < 4; index++){
      if (xvalue < shape.getX(index)){
        xvalue = shape.getX(index);
        rightMostIndex.clear();
        rightMostIndex.add(index);
      }
      else if (xvalue == shape.getX(index)){
        rightMostIndex.add(index);
      }
    }

    for (int indexed : rightMostIndex){
      int yCoord = shape.getY(indexed);
      int xCoord = shape.getX(indexed);
      if (board[maxY-yCoord][5+xCoord-maxX+1] == 1){
        return true;
      }
    }
    return false;
  }

  public boolean bottomCollision(Shape shape){
    for (int k = 0; k < 4; k++){
      int y = shape.getY(k);
      if ((maxY-y+1) > 15){
        return true;
      }
    }
    ArrayList<Integer> indexes = new ArrayList<Integer>();
    int yvalue = shape.getY(0);
    indexes.add(0);
    for (int index = 1; index < 4; index++){
      if (yvalue > shape.getY(index)){
        yvalue = shape.getY(index);
        indexes.clear();
        indexes.add(index);
      }
      else if (yvalue == shape.getY(index)){
        indexes.add(index);
      }
    }
    for (int indexing : indexes){
      int yCoord = shape.getY(indexing);
      int xCoord = shape.getX(indexing);
      if (board[maxY-yCoord+1][5+xCoord-maxX] == 1)
        return true;
    }
    return false;
  }

  public boolean endGame(Shape shape) {
    ArrayList<Integer> indexes = new ArrayList<Integer>();
    int yvalue = shape.getY(0);
    indexes.add(0);
    for (int index = 1; index < 4; index++){
      if (yvalue > shape.getY(index)){
        yvalue = shape.getY(index);
        indexes.clear();
        indexes.add(index);
      }
      else if (yvalue == shape.getY(index)){
        indexes.add(index);
      }
    }

    for (int indexing : indexes){
      int yCoord = shape.getY(indexing);
      int xCoord = shape.getX(indexing);
      if (maxY-yCoord+1 <= 4)
        if (board[maxY-yCoord+1][5+xCoord-maxX] == 1 && 15-(maxY-yCoord+1) == (maxY-yCoord)){
          return true;
      }
    }
    return false;
  }
}
