package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import App.BoardLogic;
import App.BombLokum;
import App.BombMerge;
import App.HorizontalStripedLokum;
import App.NormalLokum;
import App.StripedStripedMerge;
import App.VerticalStripedLokum;
import App.WrappedLokum;

public class JUnitTest_StripedStripedMerge {
	BoardLogic boardLogic;
	HorizontalStripedLokum horizontalStripedLokum;
	VerticalStripedLokum verticalStripedLokum;
	HorizontalStripedLokum horizontalStripedTest;
	VerticalStripedLokum verticalStripedTest;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.boardLogic = BoardLogic.getInstance();

		this.horizontalStripedLokum= new HorizontalStripedLokum(boardLogic.getRowSize()/2, boardLogic.getColumnSize()/2, "White");
		boardLogic.introduceLogicField(horizontalStripedLokum);
		
		this.verticalStripedLokum= new VerticalStripedLokum(boardLogic.getRowSize()/2-1, boardLogic.getColumnSize()/2, "White");
		boardLogic.introduceLogicField(verticalStripedLokum);

		System.out.println("--------------------------------------------------Test Start--------------------------------------------------");

	}

	@After
	public void tearDown() throws Exception {
		System.out.println("--------------------------------------------------Test End--------------------------------------------------");
	}

	@Test
	public void testDestroyMerge() {
		System.out.println("Board just BEFORE calling HorizontalStriped - HorizontalStriped merge:");
		System.out.println(boardLogic);
		
		this.horizontalStripedTest = new HorizontalStripedLokum(boardLogic.getRowSize()/2, boardLogic.getColumnSize()/2-1, "White");
		boardLogic.introduceLogicField(horizontalStripedTest);
		
		StripedStripedMerge stripedStripedMerge = new StripedStripedMerge(horizontalStripedLokum, horizontalStripedTest);
		
		stripedStripedMerge.destroyMerge();
		System.out.println("Board just AFTER calling HorizontalStriped - HorizontalStriped merge:");
		System.out.println(boardLogic);
		
		try {
			setUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Board just BEFORE calling HorizontalStriped - VerticalStriped merge:");
		System.out.println(boardLogic);
		
		this.verticalStripedTest = new VerticalStripedLokum(boardLogic.getRowSize()/2, boardLogic.getColumnSize()/2-1, "White");
		boardLogic.introduceLogicField(verticalStripedTest);
		
		stripedStripedMerge = new StripedStripedMerge(horizontalStripedLokum, verticalStripedTest);
		
		stripedStripedMerge.destroyMerge();
		System.out.println("Board just AFTER calling HorizontalStriped - VerticalStriped merge:");
		System.out.println(boardLogic);
		
		try {
			setUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Board just BEFORE calling VerticalStriped - VerticalStriped merge:");
		System.out.println(boardLogic);
		
		this.verticalStripedTest = new VerticalStripedLokum(boardLogic.getRowSize()/2, boardLogic.getColumnSize()/2-1, "White");
		boardLogic.introduceLogicField(verticalStripedTest);
		
		stripedStripedMerge = new StripedStripedMerge(verticalStripedLokum, verticalStripedTest);
		
		stripedStripedMerge.destroyMerge();
		System.out.println("Board just AFTER calling VerticalStriped - VerticalStriped merge:");
		System.out.println(boardLogic);
	}

}
