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
import App.HorizontalStripedLokum;

public class JUnitTest_HorizontalStripedLokum {
	BoardLogic boardLogic;
	HorizontalStripedLokum horizontalStripedLokum;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.boardLogic = BoardLogic.getInstance();
		this.horizontalStripedLokum= new HorizontalStripedLokum(boardLogic.getRowSize()/2, boardLogic.getColumnSize()/2, Constants.LOKUM_COLOR_WHITE);
		boardLogic.introduceLogicField(horizontalStripedLokum);
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
		assert(boardLogic.getLogicFieldAt(horizontalStripedLokum.getRowIndex(), horizontalStripedLokum.getColumnIndex()) instanceof HorizontalStripedLokum);
		assertTrue(horizontalStripedLokum.repOK());
		horizontalStripedLokum.comboDestroy();
		System.out.println("Board just AFTER calling comboDestroy:");
		boardLogic.toString();
		for (int columnIndex = 0; columnIndex < boardLogic.getColumnSize(); columnIndex++) {
			assert(boardLogic.getLogicFieldAt(horizontalStripedLokum.getRowIndex(), columnIndex) instanceof EmptyLogicField);
		}
	}

}
