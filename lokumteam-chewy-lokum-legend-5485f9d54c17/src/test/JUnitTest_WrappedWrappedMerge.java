package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import App.BoardLogic;
import App.HorizontalStripedLokum;
import App.StripedWrappedMerge;
import App.VerticalStripedLokum;
import App.WrappedLokum;
import App.WrappedWrappedMerge;

public class JUnitTest_WrappedWrappedMerge {
	BoardLogic boardLogic;
	WrappedLokum wrappedLokum;
	WrappedLokum wrappedTest;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		boardLogic = BoardLogic.getInstance();
		this.wrappedLokum = new WrappedLokum(boardLogic.getRowSize()/2, boardLogic.getColumnSize()/2, "White");
		boardLogic.introduceLogicField(wrappedLokum);
		
		this.wrappedTest= new WrappedLokum(boardLogic.getRowSize()/2-1, boardLogic.getColumnSize()/2, "White");
		boardLogic.introduceLogicField(wrappedLokum);
		
		System.out.println("--------------------------------------------------Test Start--------------------------------------------------");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("--------------------------------------------------Test End--------------------------------------------------");
	}

	@Test
	public void testDestroyMerge() {
		System.out.println("Board just BEFORE calling Wrapped - Wrapped merge:");
		System.out.println(boardLogic);
		WrappedWrappedMerge wrappedWrappedMerge= new WrappedWrappedMerge(wrappedLokum, wrappedTest);
		wrappedWrappedMerge.destroyMerge();
		System.out.println("Board just AFTER calling Wrapped - Wrapped merge:");
		System.out.println(boardLogic);
	}

}
