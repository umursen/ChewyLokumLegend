package App;

public class BombLokum extends Lokum implements ComboDestroyable,
		MergeDestroyable, Swapable {

	public BombLokum(int rowIndex, int columnIndex) {
		/*
		 * Actually, BombLokum should not have a color. So just enter a dummy value as the color, since
		 * this field will not have any significance for the BombLokum.
		 */
		super(rowIndex, columnIndex, Constants.BOMB_LOKUM_COLOR);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void comboDestroy() {
		// TODO Auto-generated method stub
		EmptyLogicField destroyed = new EmptyLogicField(getRowIndex(), getColumnIndex());
		BoardLogic.getInstance().introduceLogicField(destroyed);
		
		EventDispatchQueue.getInstance().addEvent(new DestroyLokumEvent(destroyed.copyLogicField()));
		
	}

	@Override
	public LogicField copyLogicField() {
		// TODO Auto-generated method stub
		return new BombLokum(getRowIndex(), getColumnIndex());
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "BombLokum";
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if( !super.equals(obj) )
			return false;
		if( !(obj instanceof BombLokum) )
			return false;
		return true;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "BL@" + super.toString();
	}
	
//	@Override
//	public void comboDestroy() {
//		// TODO Auto-generated method stub
//		BoardLogic.getInstance().introduceLogicField(new EmptyLogicField(getRowIndex(), getColumnIndex()));
//	}
}
