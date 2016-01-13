package App;


public class ScoreUpdateEvent extends GameEvent {

	int newScore;
	
	public ScoreUpdateEvent(int newScore){
		this.newScore = newScore;
	}
	
	@Override
	public void executeEvent() {
		InformationBoard.getInstance().updateScore(newScore);
		InformationBoard.getInstance().repaint();
	}

}
