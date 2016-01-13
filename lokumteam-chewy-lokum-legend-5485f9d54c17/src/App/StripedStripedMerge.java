package App;


public class StripedStripedMerge extends Merge {
	private BoardLogic board;
	
	public StripedStripedMerge(MergeDestroyable l1, MergeDestroyable l2){
		this.l1 = (Lokum)l1;
		this.l2 = (Lokum)l2;
		board = BoardLogic.getInstance();
	}
	
	public void destroyMerge(){
		int x2 = l2.getRowIndex();
		int y2 = l2.getColumnIndex();
		int x = l1.getRowIndex();
		int y = l1.getColumnIndex();
		EmptyLogicField removeComboLokum1 = new EmptyLogicField(x, y);
		board.introduceLogicField(removeComboLokum1);
		EmptyLogicField removeComboLokum2 = new EmptyLogicField(x2, y2);
		board.introduceLogicField(removeComboLokum2);
		EventDispatchQueue.getInstance().addEvent(new DestroyLokumEvent(removeComboLokum1));
		EventDispatchQueue.getInstance().addEvent(new DestroyLokumEvent(removeComboLokum2));
		
		VerticalStripedLokum verticalDestroyer = new VerticalStripedLokum(x2, y2, "red");
		HorizontalStripedLokum horizontalDestroyer = new HorizontalStripedLokum(x2, y2, "red");
		verticalDestroyer.comboDestroy();
		horizontalDestroyer.comboDestroy();
	}
	public boolean repOK(){
		return super.repOK()&&(l1 instanceof StripedLokum && l2 instanceof StripedLokum);
	}
}
