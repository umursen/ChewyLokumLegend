package App;


public class StripedWrappedMerge extends Merge {
	
	public StripedWrappedMerge(MergeDestroyable l1, MergeDestroyable l2){
		this.l1 = (Lokum) l1;
		this.l2 = (Lokum) l2;
	}
	
	public void destroyMerge(){
		int xl2 = l2.getRowIndex();
		int yl2 = l2.getColumnIndex();
		String color = l1.getLokumColor();
		int x1 = l1.getRowIndex();
		int y1 = l1.getColumnIndex();
		
		EmptyLogicField removeComboLokum1 = new EmptyLogicField(xl2, yl2);
		BoardLogic.getInstance().introduceLogicField(removeComboLokum1);
		EmptyLogicField removeComboLokum2 = new EmptyLogicField(x1, y1);
		BoardLogic.getInstance().introduceLogicField(removeComboLokum2);
		EventDispatchQueue.getInstance().addEvent(new DestroyLokumEvent(removeComboLokum1));
		EventDispatchQueue.getInstance().addEvent(new DestroyLokumEvent(removeComboLokum2));
		
		//IMPORTANT: CHECK THE BOUNDS OF BOARD.
		for(int i = -1; i < 2; i++){
			int y = yl2 + i;
			if(LogicField.isIndicesInBounds(xl2, y)){
				VerticalStripedLokum lokumDestroyer = new VerticalStripedLokum(xl2, y, color);	
				lokumDestroyer.comboDestroy();
			}
		}

		for(int i = -1; i < 2; i++){
			int x = xl2 + i;
			if(LogicField.isIndicesInBounds(x, yl2)){
				HorizontalStripedLokum lokumDestroyer = new HorizontalStripedLokum(x, yl2, color);
				lokumDestroyer.comboDestroy();
			}
		}
	}
	public boolean repOK(){
		return super.repOK()&&((l1 instanceof StripedLokum && l2 instanceof WrappedLokum)||(l2 instanceof StripedLokum && l1 instanceof WrappedLokum));
	}
}