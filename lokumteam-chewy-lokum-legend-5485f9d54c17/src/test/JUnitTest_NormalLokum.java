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
import App.NormalLokum;

public class JUnitTest_NormalLokum {
	BoardLogic boardLogic;
	NormalLokum normalLokum;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.boardLogic = BoardLogic.getInstance();
		this.normalLokum= new NormalLokum(boardLogic.getRowSize()/2, boardLogic.getColumnSize()/2, Constants.LOKUM_COLOR_WHITE);
		boardLogic.introduceLogicField(normalLokum);
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
		assert(boardLogic.getLogicFieldAt(normalLokum.getRowIndex(), normalLokum.getColumnIndex()) instanceof NormalLokum);
		assertTrue(normalLokum.repOK());
		normalLokum.comboDestroy();
		System.out.println("Board just AFTER calling comboDestroy:");
		boardLogic.toString();
		assert(boardLogic.getLogicFieldAt(normalLokum.getRowIndex(), normalLokum.getColumnIndex()) instanceof EmptyLogicField);
	}

}
