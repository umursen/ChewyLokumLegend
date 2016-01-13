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
import App.TimeLevel;
import App.TimeLokum;

public class JUnitTest_TimeLokum {
	private BoardLogic boardLogic;
	private TimeLokum timeLokum;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.boardLogic = BoardLogic.getInstance();
		this.timeLokum= new TimeLokum(boardLogic.getRowSize()/2, boardLogic.getColumnSize()/2, Constants.LOKUM_COLOR_WHITE);
		boardLogic.introduceLogicField(timeLokum);
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
		assert(boardLogic.getLogicFieldAt(timeLokum.getRowIndex(), timeLokum.getColumnIndex()) instanceof TimeLokum);
		assertTrue(timeLokum.repOK());
		int timeAward = timeLokum.getTimeAward();
		int oldTime = ((TimeLevel) TimeLevel.getInstance()).getTime();
		timeLokum.comboDestroy();
		int newTime = ((TimeLevel) TimeLevel.getInstance()).getTime();
		assertEquals(oldTime+timeAward,newTime);
		System.out.println("Board just AFTER calling comboDestroy:");
		boardLogic.toString();
		assert(boardLogic.getLogicFieldAt(timeLokum.getRowIndex(), timeLokum.getColumnIndex()) instanceof EmptyLogicField);
	}

}
