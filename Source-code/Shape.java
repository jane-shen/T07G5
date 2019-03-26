import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Shape {
	protected enum ShapeType {NoShape, ZShape, SShape, IShape, TShape, SquareShape, LShape, JShape};
	protected ShapeType tetromino;
	protected int coords[][];
	protected int[][][] coordsTable;

// creates a constructor for Shape class
	public Shape () {
		coords = new int[4][2];
		setShape(ShapeType.NoShape);
	}
	public Shape (Shape copyShape) {
		coords = new int[4][2];
		setShape(copyShape.getShape());
	}

	/**
	*Sets the shapetype of the object
	*@param shape is the shape being initialized to a shapetype
	*/
	public void setShape(ShapeType shape) {
				// this 3d array stores the enum ShapeTypes NoShape, ZShape, SShape, IShape, TShape, SquareShape, LShape, JShape
				// each row is one ShapeType
				// enum referenced from http://zetcode.com/tutorials/javagamestutorial/tetris/
        coordsTable = new int[][][] {
           { { 0, 0 },   { 0, 0 },   { 0, 0 },   { 0, 0 } },
           { { 0, -1 },  { 0, 0 },   { -1, 0 },  { -1, 1 } },
           { { 0, -1 },  { 0, 0 },   { 1, 0 },   { 1, 1 } },
           { { 0, -1 },  { 0, 0 },   { 0, 1 },   { 0, 2 } },
           { { -1, 0 },  { 0, 0 },   { 1, 0 },   { 0, 1 } },
           { { 0, 0 },   { 1, 0 },   { 0, 1 },   { 1, 1 } },
           { { -1, -1 }, { 0, -1 },  { 0, 0 },   { 0, 1 } },
           { { 1, -1 },  { 0, -1 },  { 0, 0 },   { 0, 1 } }
       };

       for (int y = 0; y < 4 ; y++) {
           for (int x = 0; x < 2; ++x) {
						 	//sets the coordinates of the shape based on its ShapeType
            	coords[y][x] = coordsTable[shape.ordinal()][y][x];

           }
       }
       tetromino = shape;
   }


	 /**
	 *Gets the shape of the object ShapeType
	 *@return tetromino which is the type of tetris block (shape)
	 */
	public ShapeType getShape() {
		return tetromino;
	}
	/**
	* Assigns a random shapetype to the Shape object
	*/
	public void setRandomShape() {
		Random rand = new Random();
		int randomNum = rand.nextInt((7 - 1) + 1) + 1;
		ShapeType[] values = ShapeType.values();
	    setShape(values[randomNum]);
	}
	/**
	* Changes the x-value on a certain point of the shape
	* @param index is the point that of which we want to change its x-value
	* @param x is the new value of x-coordinate we want to give to the point
	*/
	public void setNewX(int index, int x){
		coords[index][0] = x;
	}

	/**
	* Changes the y-value on a certain point of the shape
	* @param index is the point that of which we want to change its x-value
	*@param y is the new value of y-coordinate value we want to give the point
	*/
	public void setNewY(int index, int y){
		coords[index][1] = y;
	}
	/**
	* Gets the x-value of a certain point of the shape
	* @param index is the point of the shape of which we want to get its x-value
	*/
	public int getX(int index){
		return coords[index][0];
	}
	/**
	* Gets the y-value of a certain point of the shape
	* @param index is the point of the shape of which we want to get its y-value
	*/
	public int getY(int index){
		return coords[index][1];
	}
}