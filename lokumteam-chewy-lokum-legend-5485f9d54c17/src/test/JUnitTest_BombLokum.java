package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import App.BoardLogic;
import App.BombLokum;
import App.EmptyLogicField;

public class JUnitTest_BombLokum {
	BoardLogic boardLogic;
	BombLokum bombLokum;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.boardLogic = BoardLogic.getInstance();
		this.bombLokum = new BombLokum(boardLogic.getRowSize()/2, boardLogic.getColumnSize()/2);
		boardLogic.introduceLogicField(bombLokum);
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
		assert(boardLogic.getLogicFieldAt(bombLokum.getRowIndex(), bombLokum.getColumnIndex()) instanceof BombLokum);
		assertTrue(bombLokum.repOK());
		bombLokum.comboDestroy();
		System.out.println("Board just AFTER calling comboDestroy:");
		boardLogic.toString();
		assert(boardLogic.getLogicFieldAt(bombLokum.getRowIndex(), bombLokum.getColumnIndex()) instanceof EmptyLogicField);
	}
}