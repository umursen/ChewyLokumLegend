package App;
/*
 * TO DO:
 * 1) Add a getInstance() method here.
 */
public abstract class Level {
	
	protected int levelNumber;
	private int specialMoves;
	private static Level instance;
	
	public static Level getInstance(){
		if(instance == null){
			int currentLevel =Options.currentLevel;
			if(currentLevel%2==0)
				instance = new TimeLevel();
			else
				instance = new MoveLevel();
			instance.specialMoves = currentLevel%3 +3;
			EventDispatchQueue.getInstance().addEvent(new SpecialMoveUpdateEvent(instance.getSpecialMoves()));
		}
		return instance;
	}
	public static void resetInstance(){
		instance = null;
	}
	
	public void setSpecialMoves(int newSpecialMoves){
		this.specialMoves = newSpecialMoves;
	}
	
	public int getSpecialMoves(){
		return specialMoves;
	}
	
	public int getCurrentLevel(){
		return levelNumber;
	}
}
