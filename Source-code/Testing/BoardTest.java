import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

public class BoardTest extends Board {
	
	//Checks that the block is placed in the center top of the board at the start
	@Test
	public void testInitialPosition() {
		Board testBoard = new Board();
		Shape testShape = new Shape();
		int[][] board = new int[16][10];
		testShape.setShape(ShapeType.LShape);
		testBoard.placeShape(testShape);
		board[0][5] = 1;
		board[1][5] = 1;
		board[2][4] = 1;
		board[2][5] = 1;
		assertTrue(java.util.Arrays.deepEquals(testBoard.getBoard(), board));
	}
	//Checks that the whole block moves one down
	@Test
	public void testMoveDown() {
		Board testBoard = new Board();
		Shape testShape = new Shape();
		int[][] board = new int[16][10];
		testShape.setShape(ShapeType.LShape);
		testBoard.placeShape(testShape);
		testBoard.moveDown(testShape);
		board[1][5] = 1;
		board[2][5] = 1;
		board[3][4] = 1;
		board[3][5] = 1;
		assertTrue(java.util.Arrays.deepEquals(testBoard.getBoard(), board));
	}
	//Checks that the whole block moves one left
	@Test
	public void testMoveLeft() {
		Board testBoard = new Board();
		Shape testShape = new Shape();
		int[][] board = new int[16][10];
		testShape.setShape(ShapeType.LShape);
		testBoard.placeShape(testShape);
		testBoard.moveLeft(testShape);
		board[0][4] = 1;
		board[1][4] = 1;
		board[2][3] = 1;
		board[2][4] = 1;
		assertTrue(java.util.Arrays.deepEquals(testBoard.getBoard(), board));
	}
	//Checks that the whole block moves one right
	@Test
	public void testMoveRight() {
		Board testBoard = new Board();
		Shape testShape = new Shape();
		int[][] board = new int[16][10];
		testShape.setShape(ShapeType.LShape);
		testBoard.placeShape(testShape);
		testBoard.moveRight(testShape);
		board[0][6] = 1;
		board[1][6] = 1;
		board[2][5] = 1;
		board[2][6] = 1;
		assertTrue(java.util.Arrays.deepEquals(testBoard.getBoard(), board));
	}
	//Checks that the block stops moving when it hits the left edge of the board
	@Test
	public void testLeftCollision() {
		Board testBoard = new Board();
		Shape testShape = new Shape();
		int[][] board = new int[16][10];
		testShape.setShape(ShapeType.LShape);
		testBoard.placeShape(testShape);
		while (!testBoard.leftCollision(testShape))
			testBoard.moveLeft(testShape);
		board[0][1] = 1;
		board[1][1] = 1;
		board[2][0] = 1;
		board[2][1] = 1;
		assertTrue(java.util.Arrays.deepEquals(testBoard.getBoard(), board));
	}
	//Checks that the block stops moving when it hits the right edge of the board
	@Test
	public void testRightCollision() {
		Board testBoard = new Board();
		Shape testShape = new Shape();
		int[][] board = new int[16][10];
		testShape.setShape(ShapeType.LShape);
		testBoard.placeShape(testShape);
		while (!testBoard.rightCollision(testShape))
            testBoard.moveRight(testShape);
		board[0][9] = 1;
		board[1][9] = 1;
		board[2][8] = 1;
		board[2][9] = 1;
		assertTrue(java.util.Arrays.deepEquals(testBoard.getBoard(), board));
	}
	//Checks that the block stops moving when it hits the bottom of the board
	@Test
	public void testBottomCollision() {
		Board testBoard = new Board();
		Shape testShape = new Shape();
		int[][] board = new int[16][10];
		testShape.setShape(ShapeType.LShape);
		testBoard.placeShape(testShape);
		while (!testBoard.bottomCollision(testShape))
            testBoard.moveDown(testShape);
		board[13][5] = 1;
		board[14][5] = 1;
		board[15][4] = 1;
		board[15][5] = 1;
		assertTrue(java.util.Arrays.deepEquals(testBoard.getBoard(), board));
	}
	//Check if the block rotates right correctly
	@Test
	public void testRotateRight() {
		Board testBoard = new Board();
		Shape testShape = new Shape();
		int[][] board = new int[16][10];
		testShape.setShape(ShapeType.LShape);
		Shape originalShape = new Shape(testShape);
		testBoard.placeShape(testShape);
        testBoard.rotateRight(testShape, originalShape);
		board[0][4] = 1;
		board[1][4] = 1;
		board[1][5] = 1;
		board[1][6] = 1;
		assertTrue(java.util.Arrays.deepEquals(testBoard.getBoard(), board));
	}
	//Checks if the block rotates left correctly
	@Test
	public void testRotateLeft() {
		Board testBoard = new Board();
		Shape testShape = new Shape();
		int[][] board = new int[16][10];
		testShape.setShape(ShapeType.LShape);
		Shape originalShape = new Shape(testShape);
		testBoard.placeShape(testShape);
        testBoard.rotateLeft(testShape, originalShape);
		board[1][4] = 1;
		board[1][5] = 1;
		board[1][6] = 1;
		board[2][6] = 1;
		assertTrue(java.util.Arrays.deepEquals(testBoard.getBoard(), board));
	}
	//Checks that the row is cleared correctly
	@Test
	public void testClearRow() {
		Board testBoard = new Board();
		Shape testShape1 = new Shape();
		Shape testShape2 = new Shape();
		Shape testShape3 = new Shape();
		testShape1.setShape(ShapeType.LShape);
		testShape2.setShape(ShapeType.IShape);
		testShape3.setShape(ShapeType.IShape);
		Shape originalShape2 = new Shape(testShape2);
		Shape originalShape3 = new Shape(testShape3);
		int[][] board = new int[16][10];
		testBoard.placeShape(testShape1);
		testBoard.moveRight(testShape1);
		testBoard.moveRight(testShape1);
		testBoard.moveRight(testShape1);
		testBoard.moveRight(testShape1);
		while (!testBoard.bottomCollision(testShape1))
			testBoard.moveDown(testShape1);

		testBoard.placeShape(testShape2);
		testBoard.rotateLeft(testShape2, originalShape2);
		testBoard.moveRight(testShape2);
		while (!testBoard.bottomCollision(testShape2))
			testBoard.moveDown(testShape2);

		testBoard.placeShape(testShape3);
		testBoard.rotateLeft(testShape3, originalShape3);
		testBoard.moveLeft(testShape3);
		testBoard.moveLeft(testShape3);
		testBoard.moveLeft(testShape3);
		while (!testBoard.bottomCollision(testShape3))
			testBoard.moveDown(testShape3);
		boolean test = testBoard.checkFullRow();
		board[15][9] = 1;
		board[14][9] = 1;
		assertTrue(java.util.Arrays.deepEquals(testBoard.getBoard(), board));
	}

}
