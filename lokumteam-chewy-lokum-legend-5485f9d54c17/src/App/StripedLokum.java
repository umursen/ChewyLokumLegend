package App;


public abstract class StripedLokum extends Lokum implements MergeDestroyable, ComboDestroyable, Swapable{

	public StripedLokum(int rowIndex, int columnIndex, String lokumColor) {
		super(rowIndex, columnIndex, lokumColor);
		// TODO Auto-generated constructor stub
		//BoardLogic.getInstance().introduceLogicField(this);
	}
	
}
