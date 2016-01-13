package App;

public class NormalLokum extends Lokum implements ComboDestroyable, Swapable{
	
	public NormalLokum(int rowIndex, int columnIndex, String lokumColor) {
		super(rowIndex, columnIndex, lokumColor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void comboDestroy() {
		/*
		 * To Do:
		 * -Check privacy scope.
		 */
		// TODO Auto-generated method stub
		EmptyLogicField destroyed = new EmptyLogicField(getRowIndex(), getColumnIndex());
		BoardLogic.getInstance().introduceLogicField(destroyed);
		EventDispatchQueue.getInstance().addEvent(new DestroyLokumEvent(destroyed.copyLogicField()));
	
	}

	@Override
	public LogicField copyLogicField() {
		// TODO Auto-generated method stub
		return new NormalLokum(getRowIndex(), getColumnIndex(), getLokumColor());
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "NormalLokum";
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if( !(super.equals(obj)) )
			return false;
		if( !(obj instanceof NormalLokum) )
			return false;
		return true;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "NL@" + super.toString();
	}
}
