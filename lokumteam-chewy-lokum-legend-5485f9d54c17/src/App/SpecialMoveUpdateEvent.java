package App;

public class SpecialMoveUpdateEvent extends GameEvent {
	
	private int newSpecialMoves;
	
	public SpecialMoveUpdateEvent(int newSpecialMoves){
		this.newSpecialMoves=newSpecialMoves;
	}

	@Override
	public void executeEvent() {
		InformationBoard.getInstance().setSpecialMoves(newSpecialMoves);
	}

}
