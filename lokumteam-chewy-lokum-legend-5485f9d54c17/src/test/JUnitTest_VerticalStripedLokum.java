package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import App.BoardLogic;
import App.Constants;
import App.EmptyLogicField;
import App.VerticalStripedLokum;

public class JUnitTest_VerticalStripedLokum {
	BoardLogic boardLogic;
	VerticalStripedLokum verticalStripedLokum;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.boardLogic = BoardLogic.getInstance();
		this.verticalStripedLokum= new VerticalStripedLokum(boardLogic.getRowSize()/2, boardLogic.getColumnSize()/2, Constants.LOKUM_COLOR_BROWN);
		boardLogic.introduceLogicField(verticalStripedLokum);
		System.out.println("--------------------------------------------------Test Start--------------------------------------------------");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("--------------------------------------------------Test End--------------------------------------------------");
	}

	@Test
	public void testComboDestroy() {
		System.out.println("Board just BEFORE calling comboDestroy:");
		boardLogic.toString();
		assert(boardLogic.getLogicFieldAt(verticalStripedLokum.getRowIndex(), verticalStripedLokum.getColumnIndex()) instanceof VerticalStripedLokum);
		assertTrue(verticalStripedLokum.repOK());
		verticalStripedLokum.comboDestroy();
		System.out.println("Board just AFTER calling comboDestroy:");
		boardLogic.toString();
		for (int rowIndex = 0; rowIndex < boardLogic.getRowSize(); rowIndex++) {
			assert(boardLogic.getLogicFieldAt(rowIndex, verticalStripedLokum.getColumnIndex()) instanceof EmptyLogicField);
		}
	}

}
