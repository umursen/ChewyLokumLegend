package App;

public class WrappedLokum extends Lokum implements ComboDestroyable,
MergeDestroyable, Swapable {

	public WrappedLokum(int rowIndex, int columnIndex, String lokumColor) {
		super(rowIndex, columnIndex, lokumColor);
		// TODO Auto-generated constructor stub
	}

	@Override
	/* ArrayIndexOutOfBoundsException veriyor. . 
	 * (non-Javadoc)
	 * @see App.ComboDestroyable#comboDestroy()
	 */
	public void comboDestroy() {
		// TODO Auto-generated method stub
		BoardLogic boardLogic = BoardLogic.getInstance();

		/*
		 * Set the wrapped lokum's position to empty.
		 */
		EmptyLogicField destroyThis = new EmptyLogicField(getRowIndex(), getColumnIndex());
		boardLogic.introduceLogicField(destroyThis);;
		EventDispatchQueue.getInstance().addEvent(new DestroyLokumEvent(destroyThis));

		for(int i=1;i>-2;i--){
			for(int j=-1;j<2;j++){
				LogicField currentLogicField = boardLogic.getLogicFieldAt( getRowIndex() + i , getColumnIndex() + j );
				if (!(currentLogicField instanceof EmptyLogicField)){
					if(currentLogicField != null){
						if( currentLogicField instanceof ComboDestroyable )
							((ComboDestroyable) currentLogicField).comboDestroy();
						// if here, then currentLogicField is not combo destroyable. So simply clear it's position.
						else{
							EmptyLogicField destroyed = new EmptyLogicField( getRowIndex() + i, getColumnIndex() + j );
							boardLogic.introduceLogicField(destroyed);

						
							EventDispatchQueue.getInstance().addEvent(new DestroyLokumEvent(destroyed.copyLogicField()));
						}
					}
				}
			}
		}
		Score.getInstance().scoreUpdateWrappedUse();
	}

	@Override
	public LogicField copyLogicField() {
		// TODO Auto-generated method stub
		return new WrappedLokum(getRowIndex(), getColumnIndex(), getLokumColor());
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "WrappedLokum";
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if( !super.equals(obj) )
			return false;
		if( !(obj instanceof WrappedLokum) )
			return false;
		return true;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "WL@" + super.toString();
	}

	//	@Override
	//	public void comboDestroy() {
	//		// TODO Auto-generated method stub
	//		BoardLogic boardLogic = BoardLogic.getInstance();
	//		
	//		/*
	//		 * Set the wrapped lokum's position to empty.
	//		 */
	//		boardLogic.introduceLogicField(new EmptyLogicField(getRowIndex(), getColumnIndex()));
	//		
	//		for(int i=1;i>-2;i--){
	//			for(int j=-1;j<2;j++){
	//				LogicField currentLogicField = boardLogic.getLogicFieldAt( getRowIndex() + i , getColumnIndex() + j ); 
	//				if( currentLogicField instanceof ComboDestroyable )
	//					((ComboDestroyable) currentLogicField).comboDestroy();
	//				// if here, then currentLogicField is not combo destroyable. So simply clear it's position.
	//				boardLogic.introduceLogicField(new EmptyLogicField( getRowIndex() + i, getColumnIndex() + j ));
	//			}
	//		}
	//	}
}
