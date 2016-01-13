package App;


public abstract class LogicField {
	int rowIndex;
	int columnIndex;
	
	/**
	 * Creates a new LogicField object and introduces it to the BoardLogic instance. 
	 * @param logicFieldRowIndex
	 * @param logicFieldColumnIndex
	 */
	public LogicField(int rowIndex, int columnIndex){
		super();
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
	}
	
	public abstract LogicField copyLogicField();

	public int getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	public int getColumnIndex() {
		return columnIndex;
	}

	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}
	
	public abstract String getType();

	public static boolean isSwapable(LogicField l1) {
		if(l1 instanceof Swapable){
			return true;
		}
		return false;
	}
	public static boolean isIndicesInBounds(int argRowIndex, int argColumnIndex){
		if( (argRowIndex < 0) || (argRowIndex >= BoardLogic.getInstance().getRowSize()) )
			return false;
		if( (argColumnIndex < 0) || (argColumnIndex >= BoardLogic.getInstance().getColumnSize()) )
			return false;
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if( !(obj instanceof LogicField) )
			return false;
		LogicField argLogicField = (LogicField) obj;
		if(this.rowIndex != argLogicField.rowIndex)
			return false;
		if(this.columnIndex != argLogicField.columnIndex)
			return false;
		return true;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "(" + this.rowIndex + ", " + this.columnIndex + ")";
	}
	
	/**
	 * @return
	 * is representation of the logic field ok.
	 */
	public boolean repOK(){
		if(rowIndex<Constants.BOARD_HEIGHT&&0<=rowIndex&&columnIndex<Constants.BOARD_WIDTH&&0<=columnIndex)
			return true;
		else
			return false;
	}
	
}
