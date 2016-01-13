package test;

import static org.junit.Assert.*;
import App.BoardLogic;
import App.EmptyLogicField;
import App.Factory;
import App.LogicField;
import App.Lokum;
import App.Obstacle;
import App.Score;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BoardLogicTest {
	BoardLogic BL;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("------------------------Test Begin------------------------");
		// runs before each test
		this.BL = BoardLogic.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("------------------------Test End------------------------");
	}

	@Test
	public void testFallLokums() {
		System.out.println("Before fall: ");
		System.out.println(toString(BL));
		System.out.println("After fall: ");
		BL.readjustBoardAfterDestroy();
		System.out.println(toString(BL));
	}

	@Test
	public void testSwapLogicFieldLogicField() {
		Lokum lokum1 = Factory.createRandomLokum( (int) Math.random() * BL.getRowSize(),
												  (int) Math.random() * BL.getColumnSize());
		
		Lokum lokum2 = Factory.createRandomLokum( (int) Math.random() * BL.getRowSize(),
												  (int) Math.random() * BL.getColumnSize());
		BoardLogic BL = BoardLogic.getInstance();
		int currentScore = Score.getInstance().getCurrentScore();
		System.out.println("Board Before swap: ");
		System.out.println("Score Before Swap: " + currentScore);
		System.out.println(toString(BL));
		int x1 = lokum1.getRowIndex();
		int y1 = lokum1.getColumnIndex();
		int x2 = lokum2.getRowIndex();
		int y2 = lokum2.getColumnIndex();
		System.out.println("Swapping: (" + x1 + ", " + y1 + ") and (" + x2 + ", " + y2 + ")");
		BL.swap(lokum1, lokum2);
		currentScore = Score.getInstance().getCurrentScore();
		System.out.println("Score after swap: " + currentScore);
		System.out.println("Board After swap: ");
		System.out.println(toString(BL));
	}

	@Test
	public void testIsBoardStabilized() {
		if(BL.isBoardStabilized()){
			System.out.println("Board is stable");
		}else{
			System.out.println("Board is not stable");
		}
	}

	@Test
	public void testShuffleBoard() {
		System.out.println("Before shuffle: ");
		System.out.println(toString(BL));
		System.out.println("After shuffle: ");
		BL.shuffleBoard();
		System.out.println(toString(BL));
	}
	
	public static String toString(BoardLogic BL){
		String boardString = "";
		LogicField[][] logicFields = BL.getLogicFields();

		for(int currentRowIndex = BL.getRowSize() - 1; currentRowIndex > -1; currentRowIndex--){
			for(int currentColumnIndex = 0; currentColumnIndex < BL.getColumnSize(); currentColumnIndex++){
				if( logicFields[currentRowIndex][currentColumnIndex] instanceof Obstacle ){
					Obstacle obs = (Obstacle) logicFields[currentRowIndex][currentColumnIndex];
					String color = Character.toString(obs.getObstacleColor().charAt(0));
					boardString += "O"+ color;
				}
				else if( !(logicFields[currentRowIndex][currentColumnIndex] instanceof EmptyLogicField) ){
					Lokum lok = (Lokum) logicFields[currentRowIndex][currentColumnIndex];
					String color = Character.toString(lok.getLokumColor().charAt(0));
					String type = Character.toString(lok.getType().charAt(0));
					boardString += type + color;
				}
				else{
					boardString += "EE";
				}
				boardString += "|";
			}
			boardString += "\n";
		}
		return boardString;
	}

}
