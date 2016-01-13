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
import App.WrappedLokum;

public class JUnitTest_WrappedLokum {
	BoardLogic boardLogic;
	WrappedLokum wrappedLokum;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		boardLogic = BoardLogic.getInstance();
		this.boardLogic = BoardLogic.getInstance();
		this.wrappedLokum= new WrappedLokum(boardLogic.getRowSize()/2, boardLogic.getColumnSize()/2, Constants.LOKUM_COLOR_GREEN);
		boardLogic.introduceLogicField(wrappedLokum);
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
		assert(boardLogic.getLogicFieldAt(wrappedLokum.getRowIndex(), wrappedLokum.getColumnIndex()) instanceof WrappedLokum);
		assertTrue(wrappedLokum.repOK());
		wrappedLokum.comboDestroy();
		System.out.println("Board just AFTER calling comboDestroy:");
		boardLogic.toString();
		/*
		 * NOTE! Had wrapped lokum been close to the edges, the for loops below would generate an error. Keep that
		 * in mind.
		 */
		for (int rowIndex = wrappedLokum.getRowIndex()-1; rowIndex < wrappedLokum.getRowIndex()+2; rowIndex++) {
			for (int columnIndex = wrappedLokum.getColumnIndex()-1; columnIndex < wrappedLokum.getColumnIndex()+2; columnIndex++) {
				assert(boardLogic.getLogicFieldAt(rowIndex, columnIndex) instanceof EmptyLogicField);
			}
		}
	}

}
