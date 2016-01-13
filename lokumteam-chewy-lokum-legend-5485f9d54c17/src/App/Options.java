package App;

public class Options {
	
	public static int currentLevel = 1;
	public int targetScore = Constants.GOAL_SCORE + currentLevel* 5000;;
	
	private static Options instance;
	
	public void setLevel(int level){
		currentLevel = level;
		targetScore = Constants.GOAL_SCORE + currentLevel* 5000;
	}
	
	public int getTargetScore(){
		return targetScore;
	}
	
	public int getLevel(){
		return currentLevel;
	}
	
	public static Options getInstance(){
		if(instance==null){
			instance = new Options();
		}
		return instance;
	}
	
	public static void resetInstance(){
		instance = null;
	}
	
}
