package App;

import java.io.IOException;



public class Run {

	public static void main(String[] args) throws IOException {
		
		ApplicationWindow frame = ApplicationWindow.getInstance();
		frame.pack();
		frame.setSize(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);

//		TimeLevel.getInstance();
//		NormalLokum nl = new NormalLokum(0,0,"red");
//		
		
		
		
		
//		NormalLokum testNormal = new NormalLokum(0, 0, "red");
//		NormalLokum testNormal1 = new NormalLokum(1, 1, "red");
//		NormalLokum testNormal2 = new NormalLokum(0, 2, "red");
//		NormalLokum testNormal3 = new NormalLokum(0, 3, "red");
//		BombLokum testBomb = new BombLokum(1, 1);
//		WrappedLokum testWrapped = new WrappedLokum(10, 5, "brown");
//		VerticalStripedLokum testVStriped = new VerticalStripedLokum(1, 3, "white");
//		HorizontalStripedLokum testHStriped = new HorizontalStripedLokum(1, 1, "white");
		
//		
//		BoardLogic.getInstance().introduceLogicField(testBomb);
		
//		BoardLogic.getInstance().introduceLogicField(testNormal);
//		BoardLogic.getInstance().introduceLogicField(testNormal1);
//		BoardLogic.getInstance().introduceLogicField(testNormal2);
//		BoardLogic.getInstance().introduceLogicField(testNormal3);
		//System.out.println(BoardLogicTest.toString(BoardLogic.getInstance()));
		//BoardLogicTest.swapTest(testNormal1, BoardLogic.getInstance().getLogicFieldAt(8, 1));
//		MergeTest.mergeTest(Factory.createMerge(testBomb, testNormal)); //--> Works
		//BoardLogicTest.shuffleBoardTest(); --> Works
		//MergeTest.mergeTest(Factory.createMerge(testWrapped, testVStriped)); --> Works
		//MergeTest.mergeTest(Factory.createMerge(testBomb, testWrapped)); //--> Works
		//MergeTest.mergeTest(Factory.createMerge(testWrapped, testWrapped)); //--> Works
		//MergeTest.mergeTest(Factory.createMerge(testBomb, testBomb)); //--> Works
		//MergeTest.mergeTest(Factory.createMerge(testHStriped, testBomb));//--> Works
		//Combo test classes are in the Combo classes. It will only useful when tested in swap.
		//BoardLogicTest.fallLokumsTest(); //--> Array Index Out of Bounds!!!!
		//BoardLogicTest.testForStability();// --> Works but needs more testing.
		//BoardLogicTest.swapTest(testNormal, testBomb);
		//------>Swap locations suitable works.
		//------>Merge swap works.
		//testWrapped.comboDestroy(); --> Works
		//testVStriped.comboDestroy(); --> Works
		//testHStriped.comboDestroy(); --> Works
		//BoardLogicTest.swapTest(testNormal2, testNormal3);
		//BoardLogicTest.findBoardCombosTest(); //Works
		//SaveGame.saveBoardToXML(); --> Works
		//Score updates works.
	}

}
