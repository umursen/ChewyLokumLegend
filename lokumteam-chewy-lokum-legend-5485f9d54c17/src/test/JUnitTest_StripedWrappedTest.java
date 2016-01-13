package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import App.BoardLogic;
import App.HorizontalStripedLokum;
import App.StripedStripedMerge;
import App.StripedWrappedMerge;
import App.VerticalStripedLokum;
import App.WrappedLokum;

public class JUnitTest_StripedWrappedTest {
	BoardLogic boardLogic;
	HorizontalStripedLokum horizontalStripedLokum;
	VerticalStripedLokum verticalStripedLokum;
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
		this.horizontalStripedLokum= new HorizontalStripedLokum(boardLogic.getRowSize()/2, boardLogic.getColumnSize()/2, "White");
		boardLogic.introduceLogicField(horizontalStripedLokum);
		
		this.verticalStripedLokum= new VerticalStripedLokum(boardLogic.getRowSize()/2-1, boardLogic.getColumnSize()/2, "White");
		boardLogic.introduceLogicField(verticalStripedLokum);
		
		this.wrappedLokum = new WrappedLokum(boardLogic.getRowSize()/2, boardLogic.getColumnSize()/2-1, "White");
		boardLogic.introduceLogicField(wrappedLokum);
		
		System.out.println("--------------------------------------------------Test Start--------------------------------------------------");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("--------------------------------------------------Test End--------------------------------------------------");
	}

	@Test
	public void testDestroyMerge() {
		System.out.println("Board just BEFORE calling HorizontalStriped - Wrapped merge:");
		System.out.println(boardLogic);
		StripedWrappedMerge stripedWrappedMerge = new StripedWrappedMerge(horizontalStripedLokum, wrappedLokum);
		stripedWrappedMerge.destroyMerge();
		System.out.println("Board just AFTER calling HorizontalStriped - Wrapped merge:");
		System.out.println(boardLogic);
		
		try {
			setUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Board just BEFORE calling VerticalStriped - Wrapped merge:");
		System.out.println(boardLogic);
		stripedWrappedMerge = new StripedWrappedMerge(verticalStripedLokum, wrappedLokum);
		stripedWrappedMerge.destroyMerge();
		System.out.println("Board just AFTER calling VerticalStriped - Wrapped merge:");
		System.out.println(boardLogic);
	}

}
