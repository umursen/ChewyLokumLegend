package App;


public class MoveLevel extends Level {
	private int movesLeft;

	public MoveLevel(){
		levelNumber = Options.currentLevel;
		this.movesLeft = Math.max(Constants.NUMBER_OF_MOVES - 3 * Options.currentLevel,5);
		MoveUpdateEvent mue = new MoveUpdateEvent(movesLeft);
		EventDispatchQueue.getInstance().addEvent(mue);
	}
	
	public void decreaseMove(){
		movesLeft--;
		MoveUpdateEvent mue = new MoveUpdateEvent(movesLeft);
		EventDispatchQueue.getInstance().addEvent(mue);
		if(movesLeft == 0){
			if(Score.getInstance().getCurrentScore() < Options.getInstance().targetScore){
				LoseGameEvent lge = new LoseGameEvent();
				EventDispatchQueue.getInstance().addEvent(lge);
			}else{
				WinGameEvent wge = new WinGameEvent();
				EventDispatchQueue.getInstance().addEvent(wge);
			}
		}
	}
	
	public void setMovesLeft(int movesLeft){
		this.movesLeft = movesLeft;
		MoveUpdateEvent mue = new MoveUpdateEvent(movesLeft);
		EventDispatchQueue.getInstance().addEvent(mue);
		if(movesLeft == 0){
			if(Score.getInstance().getCurrentScore() < Options.getInstance().targetScore){
				LoseGameEvent lge = new LoseGameEvent();
				EventDispatchQueue.getInstance().addEvent(lge);
			}else{
				WinGameEvent wge = new WinGameEvent();
				EventDispatchQueue.getInstance().addEvent(wge);
			}
		}
	}
	
	public int getMovesLeft(){
		return movesLeft;
	}
	
}


