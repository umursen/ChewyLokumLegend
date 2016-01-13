package App;

public class MoveUpdateEvent extends GameEvent {
	
	private int moves;
	
	public MoveUpdateEvent(int moves){
		this.moves = moves;
	}

	@Override
	public void executeEvent() {
		MoveLevelPanel.getInstance().setMovesLeft(moves); 
	}

}
