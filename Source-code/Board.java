import java.util.Arrays;
import java.util.ArrayList;

public class Board extends Shape{
  private int[][] board;
  private int maxX;
  private int maxY;

  public Board() {
    // default contructor that creates empty board
    board = new int[Constants.Rows()][Constants.Columns()];

  }
  /**
  * Obtains the board of the Board object
  * @return the board
  */
  public int[][] getBoard(){
    return board;
  }

  /**
  * Places a certain shape on the board
  * @param shape This is the shape to be placed on the board
  */
  public void placeShape(Shape shape){
      // to find the "highest block" of the shape and put it on the very top of the board
      maxY = shape.getY(Constants.Zero());
      for (int i = Constants.Zero(); i<Constants.MaxLengthOfTetromino(); i++){
        if (maxY < shape.getY(i)){
          maxY = shape.getY(i);
        }
      }
      // to find the block(s) to the very right of the shape and centre the placement from there
      maxX = shape.getX(Constants.Zero());
      for (int j = Constants.Zero(); j < Constants.MaxLengthOfTetromino(); j++){
        if (maxX < shape.getX(j)){
          maxX = shape.getX(j);
        }
      }
      if (shape.getShape() != ShapeType.NoShape){
        // this if statment ensures that the shape being placed is not actually a "no shape"
        for (int k = Constants.Zero(); k < Constants.MaxLengthOfTetromino(); k++){
          int x = shape.getX(k);
          int y = shape.getY(k);
          board[maxY-y][Constants.MiddleIndex()+x-maxX] = 1;
          }
        }
      }

  /**
  * Checks if a row has been completely filled
  * @return true if one row is completely occupied, while also clearing it at the same time
  */
  public boolean checkFullRow(){
    for (int i = Constants.Zero(); i < Constants.Rows(); i++){
      int increment = Constants.Zero();
      for (int j = Constants.Zero(); j < Constants.Columns(); j++){
        if (board[i][j] != Constants.Zero()){
          increment += 1;
      }
    }
      if (increment == Constants.Columns()){
        // ensures that the whole row(which has 10 columns) is occupied
        for (int col = Constants.Zero() ; col < Constants.Columns(); col++){
          board[i][col] = Constants.Zero();
        }
        return true;
      }
    }
    return false;
  }

  //Prints the board with the current shapes that have been placed
  public void print2D(){
			for (int row = Constants.Zero(); row < Constants.Rows(); row++){
					for (int col = Constants.Zero(); col < Constants.Columns(); col++){
							System.out.print(" " + board[row][col]);
					}
					System.out.println();
			}
	}
  /**
  * Clears the board by filling it with zeroes
  */
  public void clearBoard() {
    for (int i = Constants.Zero(); i < Constants.Rows(); i++){
				for (int j = Constants.Zero(); j < Constants.Columns(); j++) {
            board[i][j] = Constants.Zero();
          }
    }
  }
  /**
  * Moves the shape one space to the left
  *@param shape is the random shape that is placed on the board
  */
  public void moveLeft(Shape shape){
    if (shape.getShape() != ShapeType.NoShape){
      for (int k = Constants.Zero(); k < Constants.MaxLengthOfTetromino(); k++){
        int x = shape.getX(k);
        int y = shape.getY(k);
        board[maxY-y][Constants.MiddleIndex()+x-maxX] = Constants.Zero();
        // The maxY is the coordinate of the highest Y-coord on the board,
        // and the y is the relative coordinate of a part of the shape
        // The 5-maxX is the coordinate of the right-most X-coord on the board,
        // and the x is the relative coordinate of a part of the shape
        }
      }
    // sets the new x-coords after moving left
    for (int x = Constants.Zero(); x < Constants.MaxLengthOfTetromino(); x++)
      shape.setNewX(x, shape.getX(x)-1);

    //applies the shape's new coordinates to the board
    if (shape.getShape() != ShapeType.NoShape){
      for (int k = Constants.Zero(); k < Constants.MaxLengthOfTetromino(); k++){
        int x = shape.getX(k);
        int y = shape.getY(k);
        board[maxY-y][Constants.MiddleIndex()+x-maxX] = 1;
        }
      }

  }
  /**
  * Moves the shape one space to the right
  * @param shape is the current random shape being moved on the board
  */
  public void moveRight(Shape shape){
    if (shape.getShape() != ShapeType.NoShape){
      for (int k = Constants.Zero(); k < Constants.MaxLengthOfTetromino(); k++){
        int x = shape.getX(k);
        int y = shape.getY(k);
        board[maxY-y][Constants.MiddleIndex()+x-maxX] = Constants.Zero();
        }
      }
      // changes x-coords to the new ones after moving right
      for (int x = Constants.Zero(); x < Constants.MaxLengthOfTetromino(); x++)
        shape.setNewX(x, shape.getX(x)+1);

      if (shape.getShape() != ShapeType.NoShape){
        for (int k = Constants.Zero(); k < Constants.MaxLengthOfTetromino(); k++){
          int x = shape.getX(k);
          int y = shape.getY(k);
          board[maxY-y][Constants.MiddleIndex()+x-maxX] = 1;
          }
        }

  }
  /**
   *Moves the shape one space down
   *@param shape is the current random shape being moved on the board
   */
  public void moveDown(Shape shape){
    if (shape.getShape() != ShapeType.NoShape){
      for (int k = Constants.Zero(); k < Constants.MaxLengthOfTetromino(); k++){
        int x = shape.getX(k);
        int y = shape.getY(k);
        board[maxY-y][Constants.MiddleIndex()+x-maxX] = Constants.Zero();
        }
      }
    //sets the new y-coordinates after moving one down
    for (int y = Constants.Zero(); y < Constants.MaxLengthOfTetromino(); y++){
    shape.setNewY(y, shape.getY(y)-1);
  }
    if (shape.getShape() != ShapeType.NoShape){
      for (int k = Constants.Zero(); k < Constants.MaxLengthOfTetromino(); k++){
        int x = shape.getX(k);
        int y = shape.getY(k);
        board[maxY-y][Constants.MiddleIndex()+x-maxX] = 1;
      }
    }
  }

/**
* Checks if moving the shape left will collide with any other shapes or the edge of the board
* @param shape is the current random shape being moved on the board
* @return true if the collision will happen
*/
  public boolean leftCollision(Shape shape){
    for (int k = Constants.Zero(); k < Constants.MaxLengthOfTetromino(); k++){
      int x = shape.getX(k);
      if ((Constants.MiddleIndex()+x-maxX-1) < Constants.Zero()){
        // The 5-maxX is the coordinate of the right-most X-coord of the shape
        // being moved on the board and the x is the relative coordinate of a part of the shape
        // 5-maxX+x gives us the current point, and subutracting one gives us the point to the left
        // we can check if it's the edge by checking if this point to the left is negative
        return true;
      }
    }
    // this ArrayList stores the index in which gives us the x-coord that is to the very left
    // of the shape. We do this because the shape can have multiple left-most parts, hence
    // we check each of these to see if they collide with any shapes to the left
    ArrayList<Integer> leftMostIndex = new ArrayList<Integer>();
    int xvalue = shape.getX(Constants.Zero());
    leftMostIndex.add(Constants.Zero());
    for (int index = 1; index < Constants.MaxLengthOfTetromino(); index++){
      if (xvalue > shape.getX(index)){
        // if statement to make sure that we have the left-most x-coord
        xvalue = shape.getX(index);
        leftMostIndex.clear();
        // we clear the arraylist if there is an index with a "left-er" x-coord, and store this new index
        leftMostIndex.add(index);
      }
      else if (xvalue == shape.getX(index)){
        leftMostIndex.add(index);
        // if the x-coord is the same for two indexes, we have to check both of these
        // so we just add this new index, rather than ignoring it or replacing the previous
      }
    }

    // for-loop checks every left-most x-coord if the spot to the left of the shape is clear
    for (int indexed : leftMostIndex){
      int yCoord = shape.getY(indexed);
      int xCoord = shape.getX(indexed);
      if (board[maxY-yCoord][Constants.MiddleIndex()+xCoord-maxX-1] == 1){
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if the current shape will collide with another shape on its right or the right edge of the board
   * @param shape is the current random shape being moved on the board
   * @return true if the collision will happen
   */
  public boolean rightCollision(Shape shape){
    for (int k = Constants.Zero(); k < Constants.MaxLengthOfTetromino(); k++){
      int x = shape.getX(k);
      if ((Constants.MiddleIndex()+x-maxX+1) > 9){
        // similar to leftCollision, except adding 1 to check to the right. At the edge if greater than 9
        return true;
      }
    }
    // same as leftCollision, except storing indexes that give the right-most x-coord when using getX() method
    ArrayList<Integer> rightMostIndex = new ArrayList<Integer>();
    int xvalue = shape.getX(Constants.Zero());
    rightMostIndex.add(Constants.Zero());
    for (int index = 1; index < Constants.MaxLengthOfTetromino(); index++){
      if (xvalue < shape.getX(index)){
        xvalue = shape.getX(index);
        rightMostIndex.clear();
        rightMostIndex.add(index);
      }
      else if (xvalue == shape.getX(index)){
        rightMostIndex.add(index);
      }
    }
    // same as leftCollision, except checking if the right-most x-coords will collide against something to the right
    for (int indexed : rightMostIndex){
      int yCoord = shape.getY(indexed);
      int xCoord = shape.getX(indexed);
      // we grab the relative coordinates of both x and y of a point of a shape
      if (board[maxY-yCoord][Constants.MiddleIndex()+xCoord-maxX+1] == 1){
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if the current shape will collide with another shape below it or the bottom edge of the board
   * @param shape is the current random shape being moved on the board
   * @return true if the collision will happen
   */
  public boolean bottomCollision(Shape shape){
    for (int k = Constants.Zero(); k < Constants.MaxLengthOfTetromino(); k++){
      int y = shape.getY(k);
      if ((maxY-y+1) > 15){
        // The maxY is the highest Y-coord of the shape on the board,
        // and the y is the relative coordinate of a part of the shape
        // adding 1 will check one row below it. It's at the edge if one
        // of the points goes over the index 15
        return true;
      }
    }
    // similar to leftCollision and rightCollision, except checking the bottom-most Y-coords
    ArrayList<Integer> indexes = new ArrayList<Integer>();
    int yvalue = shape.getY(Constants.Zero());
    indexes.add(Constants.Zero());
    for (int index = 1; index < Constants.MaxLengthOfTetromino(); index++){
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
      if (board[maxY-yCoord+1][Constants.MiddleIndex()+xCoord-maxX] == 1)
        return true;
    }
    if (shape.getShape() == ShapeType.SShape || shape.getShape() == ShapeType.ZShape){
      int cornerYValue = shape.getY(2);
      int cornerXValue = shape.getX(2);
      if (board[maxY-cornerYValue+1][Constants.MiddleIndex()+cornerXValue-maxX] == 1){
        return true;
      }
    }
  return false;
}

  public boolean endGame(Shape shape){
    if (shape.getShape() == ShapeType.SShape || shape.getShape() == ShapeType.ZShape){
      int bottomYValue = shape.getY(Constants.Zero());
      int bottomXValue = shape.getX(Constants.Zero());
      int cornerYValue = shape.getY(2);
      int cornerXValue = shape.getX(2);
      if (board[maxY-bottomYValue+1][Constants.MiddleIndex()+bottomXValue-maxX] == 1 || board[maxY-cornerYValue+1][Constants.MiddleIndex()+cornerXValue-maxX] == 1){
        return true;
      }
    }
    return false;
  }

}
