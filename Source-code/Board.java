import java.util.ArrayList;
import java.io.*;

public class Board extends Shape{
  private int[][] board;
  private int maxX;
  private int maxY;
  private String direction = "";
  private int score = 0;

  // default constructor that creates empty board
  public Board() {
    board = new int[16][10];
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
      maxY = shape.getY(0);
      for (int i = 0; i<4; i++){
        if (maxY < shape.getY(i)){
          maxY = shape.getY(i);
        }
      }
      // to find the block(s) to the very right of the shape and centre the placement from there
      maxX = shape.getX(0);
      for (int j = 0; j < 4; j++){
        if (maxX < shape.getX(j)){
          maxX = shape.getX(j);
        }
      }
      if (shape.getShape() != ShapeType.NoShape){
        // this if statement ensures that the shape being placed is not actually a "no shape"
        for (int k = 0; k < 4; k++){
          int x = shape.getX(k);
          int y = shape.getY(k);
          board[maxY-y][5+x-maxX] = 1;
          }
        }
      }


  /**
  * Checks if a row has been completely filled
  * @return true if one row is completely occupied, while also clearing it at the same time
  */
  public boolean checkFullRow(){
    boolean cleared = false;
    for (int i = 0; i < 16; i++){
      int increment = 0;
      for (int j = 0; j < 10; j++){
        if (board[i][j] != 0){
          increment += 1;
      }
    }
      if (increment == 10){
        // ensures that the whole row(which has 10 columns) is occupied
        for (int col = 0 ; col < 10; col++){
          board[i][col] = 0;
        }
        for (int clearedRow = i; clearedRow > 0; clearedRow--){
          for (int col = 0; col < 10; col++){
            board[clearedRow][col] = board[clearedRow-1][col];
            board[clearedRow-1][col] = 0;
          }
        }
        updateScore();
        System.out.println(score);
        cleared = true;
      }
    }
    return cleared;
  }


  //Prints the board with the current shapes that have been placed
  public void print2D(){
	for (int row = 0; row < 16; row++){
		for (int col = 0; col < 10; col++){
			System.out.print(" " + board[row][col]);
			}
		System.out.println();
		}
	}


  /**
  * Clears the board by filling it with zeroes
  */
  public void clearBoard() {
    for (int i = 0; i < 16; i++){
				for (int j = 0; j < 10; j++) {
            board[i][j] = 0;
      }
    }
  }


  /**
  * Moves the shape one space to the left
  *@param shape is the random shape that is placed on the board
  */
  public void moveLeft(Shape shape){
    if (shape.getShape() != ShapeType.NoShape){
      for (int k = 0; k < 4; k++){
        int x = shape.getX(k);
        int y = shape.getY(k);
        board[maxY-y][5+x-maxX] = 0;
        // The y is the actual coordinate of the Y-coord on the board,
        // and the maxY is the relative coordinate of a part of the shape
        // The 5+x is the actual coordinate of the X-coord on the board,
        // and the maxX is the relative coordinate of a part of the shape
        }
      }
    // sets the new x-coords after moving left
    for (int x = 0; x < 4; x++)
      shape.setNewX(x, shape.getX(x)-1);

    //applies the shape's new coordinates to the board
    if (shape.getShape() != ShapeType.NoShape){
      for (int k = 0; k < 4; k++){
        int x = shape.getX(k);
        int y = shape.getY(k);
        board[maxY-y][5+x-maxX] = 1;
        }
      }
    }


  /**
  * Moves the shape one space to the right
  * @param shape is the current random shape being moved on the board
  */
  public void moveRight(Shape shape){
    if (shape.getShape() != ShapeType.NoShape){
      for (int k = 0; k < 4; k++){
        int x = shape.getX(k);
        int y = shape.getY(k);
        board[maxY-y][5+x-maxX] = 0;
        }
      }
      // changes x-coords to the new ones after moving right
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


  /**
   *Moves the shape one space down
   *@param shape is the current random shape being moved on the board
   */
  public void moveDown(Shape shape){
    if (shape.getShape() != ShapeType.NoShape){
      for (int k = 0; k < 4; k++){
        int x = shape.getX(k);
        int y = shape.getY(k);
        board[maxY-y][5+x-maxX] = 0;
        }
      }
    //sets the new y-coordinates after moving one down
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



/**
* Checks if moving the shape left will collide with any other shapes or the edge of the board
* @param shape is the current random shape being moved on the board
* @return true if the collision will happen
*/
  public boolean leftCollision(Shape shape){
    for (int k = 0; k < 4; k++){
      int x = shape.getX(k);
      int y = shape.getY(k);
      if ((5+x-maxX-1) < 0){
        /**
         * x gives us the coordinate in terms of index on the board
         * so subtracting one checks the left of the coordinate. If it is out of the board
         * meaning the index is negative
         */
    	 return true;
      }
      else if (board[maxY-y][5+x-maxX-1] > 1) {
    	  /**
    	   * If it is not out of the board, it checks the value. If it is less than 1,
    	   * it means there's a shape already placed there
    	   */
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
    for (int k = 0; k < 4; k++){
      int x = shape.getX(k);
      int y = shape.getY(k);
      if ((5+x-maxX+1) > 9){
        // similar to leftCollision, except adding 1 to check to the right. At the edge if greater than 9
        return true;
      }
      else if (board[maxY-y][5+x-maxX+1] > 1) {
      	 return true;
        }
    }
    return false;
  }


  /**
   * Checks if the current shape will collide with another shape below it or the bottom edge of the board
   * It is also used for endgame; if a new shape respawns and encounters a bottom collision right away,
   * the game ends
   * @param shape is the current random shape being moved on the board
   * @return true if the collision will happen
   */
  public boolean bottomCollision(Shape shape){
    for (int k = 0; k < 4; k++){
      int y = shape.getY(k);
      int x = shape.getX(k);
      if ((maxY-y+1) > 15){
        // The y is the highest Y-coord of the shape on the board,
        // and the maxY is the relative coordinate of a part of the shape
        // adding 1 will check one row below it. It's at the edge if one
        // of the points goes over the index 15
        return true;
      }
      if (board[maxY-y+1][5+x-maxX] > 1)
        return true;
    }
      return false;
    }



  // this is the method that is responsible for rotating the block to the left
  public void rotateLeft(Shape shape, Shape originalShape) {
	    boolean rotatable = true;
	    ArrayList<Integer> originalXValues = new ArrayList<Integer>();
	    int centerX = shape.getX(2);
	    int centerY = shape.getY(2);
	    if (shape.getShape() != ShapeType.NoShape && shape.getShape() != ShapeType.SquareShape){
	      for (int k = 0; k < 4; k++){
	        int x = shape.getX(k);
	        int y = shape.getY(k);
	        board[maxY-y][5+x-maxX] = 0;
	        }
	      }
	    for (int x = 0; x < 4; x++) {
        //this is makes the y value of the given x negative which is responsible for flipping
	      originalXValues.add(originalShape.getX(x));
	      originalShape.setNewX(x, -originalShape.getY(x));
	    }

	    for (int y = 0; y < 4; y++){
	      originalShape.setNewY(y, originalXValues.get(y));
	    }

	    //check if there is a block in the way of rotating
	    if (shape.getShape() != ShapeType.NoShape && shape.getShape() != ShapeType.SquareShape){
	      for (int k = 0; k < 4; k++){
	        int x = centerX + originalShape.getX(k);
	        int y = centerY + originalShape.getY(k);
	        if ((5+x-maxX) < 0 || (5+x-maxX) > 9 || (maxY-y) > 15 || (maxY - y) < 0) {
	          rotatable = false;
	        } else if ((board[1-y][5+x-maxX] == 1)) {
	          rotatable = false;
	        }
	      }
	    }
	    if (shape.getShape() != ShapeType.NoShape && shape.getShape() != ShapeType.SquareShape && rotatable == true){
	        for (int k = 0; k < 4; k++){
	          int x = centerX + originalShape.getX(k);
	          int y = centerY + originalShape.getY(k);
	          shape.setNewX(k, x);
	          shape.setNewY(k, y);
	          board[maxY-y][5+x-maxX] = 1;
	        }
	      }
	    //undos the rotate
	    else if (rotatable == false) {
	      for (int k = 0; k < 4; k++){
	        int x = shape.getX(k);
	        int y = shape.getY(k);
	        board[maxY-y][5+x-maxX] = 1;
	      }
	    }
	  }



    //this method is responsible for rotating the block to the right
	  public void rotateRight(Shape shape, Shape originalShape) {
	    boolean rotatable = true;
	    ArrayList<Integer> originalXValues = new ArrayList<Integer>();
	    int centerX = shape.getX(2);
	    int centerY = shape.getY(2);
	    if (shape.getShape() != ShapeType.NoShape && shape.getShape() != ShapeType.SquareShape){
	      for (int k = 0; k < 4; k++){
	        int x = shape.getX(k);
	        int y = shape.getY(k);
	        board[maxY-y][5+x-maxX] = 0;
	        }
	      }
	    for (int x = 0; x < 4; x++) {
	      originalXValues.add(originalShape.getX(x));
	      originalShape.setNewX(x, originalShape.getY(x));
	    }

	    for (int y = 0; y < 4; y++){
	      originalShape.setNewY(y, -originalXValues.get(y));
	    }

	    //check if there is a block in the way of rotating
	    if (shape.getShape() != ShapeType.NoShape && shape.getShape() != ShapeType.SquareShape){
	      for (int k = 0; k < 4; k++){
	        int x = centerX + originalShape.getX(k);
	        int y = centerY + originalShape.getY(k);
	        if ((5+x-maxX) < 0 || (5+x-maxX) > 9 || (maxY-y) > 15 || (maxY - y) < 0) {
	          rotatable = false;
	        } else if ((board[1-y][5+x-maxX] == 1)) {
	          rotatable = false;
	        }
	        System.out.println(rotatable);
	      }
	    }
	    if (shape.getShape() != ShapeType.NoShape && shape.getShape() != ShapeType.SquareShape && rotatable == true){
	        for (int k = 0; k < 4; k++){
	          int x = centerX + originalShape.getX(k);
	          int y = centerY + originalShape.getY(k);
	          shape.setNewX(k, x);
	          shape.setNewY(k, y);
	          board[maxY-y][5+x-maxX] = 1;
	        }
	      }
	    //undoes the rotate
	    else if (rotatable == false) {
	      for (int k = 0; k < 4; k++){
	        int x = shape.getX(k);
	        int y = shape.getY(k);
	        board[maxY-y][5+x-maxX] = 1;
	      }
	    }
	}
  
	  public void setDirection(String direction) {
			this.direction = direction;
	  }

	  public String getDirection() {
		  	return direction;
    }
    
    public void updateScore() {
      score += 40;
    }
    public int getScore() {
      return score;
    }
    public void saveScore() {
      try {
    	File file = new File("highscores.txt");
    	String fileName = "highscores.txt";
    	if (!file.exists()) {
    		file.createNewFile();
    	}
		PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
        output.println(score);
        output.close();
        } catch (Exception e) {
          
        }
    }
	  public void setBoard() {
		  for (int row = 0; row<16 ; row++)
			  for (int col = 0; col<10 ; col++)
				  if (board[row][col] == 1)
					  board[row][col] = 2;
	  }
}
