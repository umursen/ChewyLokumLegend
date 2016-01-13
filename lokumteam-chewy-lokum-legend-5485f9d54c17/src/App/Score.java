package App;

public class Score {
	private int currentScore;
	private static Score instance;
	private ScoreUpdateEvent sue;
	
	private Score(){
		currentScore = 0;
	}
	
	public static Score getInstance(){
		if(instance == null)
			instance = new Score();
		return instance;
	}
	
	private int getGoalScore(int currentLevel){
		return (Constants.GOAL_SCORE + currentLevel) * 5000;
	}
	
	public static void resetInstance(){
		instance = null;
	}
	
	public int getCurrentScore(){
		return currentScore;
	}
	
	public void scoreUpdateStripedForm(){
		currentScore += 120;
		sue = new ScoreUpdateEvent(currentScore);
		EventDispatchQueue.getInstance().addEvent(sue);
		
	}
	
	public void scoreUpdateWrappedForm(){
		currentScore += 200;
		sue = new ScoreUpdateEvent(currentScore);
		EventDispatchQueue.getInstance().addEvent(sue);
	}
	
	public void scoreUpdateBombForm(){
		currentScore += 200;
		sue = new ScoreUpdateEvent(currentScore);
		EventDispatchQueue.getInstance().addEvent(sue);
	}
	
	public void scoreUpdateHorizontalStripedUse(){
		currentScore += BoardLogic.getInstance().getColumnSize() * 60;
		sue = new ScoreUpdateEvent(currentScore);
		EventDispatchQueue.getInstance().addEvent(sue);
	}
	
	public void scoreUpdateVerticalStripedUse(){
		currentScore += BoardLogic.getInstance().getRowSize() * 60;
		sue = new ScoreUpdateEvent(currentScore);
		EventDispatchQueue.getInstance().addEvent(sue);
	}
	
	public void scoreUpdateWrappedUse(){
		currentScore += 1080;
		sue = new ScoreUpdateEvent(currentScore);
		EventDispatchQueue.getInstance().addEvent(sue);
	}
	
	public void scoreUpdateBombUse(int numberOfLokumsDestroyed){
		currentScore += numberOfLokumsDestroyed * numberOfLokumsDestroyed * 60;
		sue = new ScoreUpdateEvent(currentScore);
		EventDispatchQueue.getInstance().addEvent(sue);
	}
	
	public void scoreUpdateWrappedWrappedMerge(){
		currentScore += 3600;
		sue = new ScoreUpdateEvent(currentScore);
		EventDispatchQueue.getInstance().addEvent(sue);
	}
	
	public void scoreUpdateBombMerge(int numberOfLokumsOnTheBoard){
		currentScore += numberOfLokumsOnTheBoard * numberOfLokumsOnTheBoard * 100;
		sue = new ScoreUpdateEvent(currentScore);
		EventDispatchQueue.getInstance().addEvent(sue);
	}
	
	public void scoreUpdateThreeCombo(){
		currentScore += 60;
		sue = new ScoreUpdateEvent(currentScore);
		EventDispatchQueue.getInstance().addEvent(sue);
	}

	public void setScore(int currentScore) {
		this.currentScore = currentScore;
		sue = new ScoreUpdateEvent(currentScore);
		EventDispatchQueue.getInstance().addEvent(sue);
	}
}
