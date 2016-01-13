package App;


public class EmptyLogicField extends LogicField {

	public EmptyLogicField(int rowIndex, int columnIndex) {
		super(rowIndex, columnIndex);
		// TODO Auto-generated constructor stub
	}

	@Override
	public EmptyLogicField copyLogicField() {
		// TODO Auto-generated method stub
		return new EmptyLogicField(getRowIndex(), getColumnIndex());
	}
	/*
	 * Why is this method returning null? Shouldn't it be returning the string: "EmptyLogicField". I am not
	 * changing it in case it is used somewhere.
	 * (non-Javadoc)
	 * @see App.LogicField#getType()
	 */
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}
}
