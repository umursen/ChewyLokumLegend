package App;

import java.util.Random;


public class Factory {
	
	public static Lokum createRandomLokum(int x, int y){
		int randomNumber = generateRandomNumber();
		switch (randomNumber){
			case 0:
				return new NormalLokum(x, y, Constants.LOKUM_COLOR_BROWN);
			case 1:
				return new NormalLokum(x, y, Constants.LOKUM_COLOR_GREEN);
			case 2:
				return new NormalLokum(x, y, Constants.LOKUM_COLOR_RED);
			case 3:
				return new NormalLokum(x, y, Constants.LOKUM_COLOR_WHITE);
			case 4:
				randomNumber = generateRandomNumber();
				if(Options.currentLevel % 2 == 0 && randomNumber == 1 )
					return new TimeLokum(x, y, generateRandomColor());
				return createRandomLokum(x, y);
			default:
				return null;
		}
	}
	
	public static LogicField createLogicField(int x, int y, String type, String color){
		if (type == null){
			return new Obstacle(x, y, color);
		}else{
			switch (type){
				case "NormalLokum":
					return new NormalLokum(x, y, color);
				case "VerticalStripedLokum":
					return new VerticalStripedLokum(x, y, color);
				case "HorizontalStripedLokum":
					return new HorizontalStripedLokum(x, y, color);
				case "WrappedLokum":
					return new WrappedLokum(x, y, color);
				case "BombLokum":
					return new BombLokum(x, y);
				case "TimeLokum":
					return new TimeLokum(x, y, color);
				default:
					return null;
			}
		}
	}
	
	public static int generateRandomNumber(){
		Random randomGenerator = new Random();
		int randomNumber = randomGenerator.nextInt(5);
		return randomNumber;
	}
	
	public static String generateRandomColor(){
		Random randomGenerator = new Random();
		int randomNumber = randomGenerator.nextInt(4);
		switch (randomNumber){
		case 0:
			return Constants.LOKUM_COLOR_BROWN;
		case 1:
			return Constants.LOKUM_COLOR_GREEN;
		case 2:
			return Constants.LOKUM_COLOR_RED;
		case 3:
			return Constants.LOKUM_COLOR_WHITE;
		default:
			return Constants.LOKUM_COLOR_RED;
		}
	}
	
	public static Merge createMerge(LogicField l1, LogicField l2){
		if((l1 instanceof HorizontalStripedLokum || l1 instanceof VerticalStripedLokum) && (l2 instanceof VerticalStripedLokum || l2 instanceof HorizontalStripedLokum)){
			System.out.println("Striped + Striped Merge");
			return new StripedStripedMerge((MergeDestroyable) l1, (MergeDestroyable) l2);
		}else if((l1 instanceof StripedLokum && l2 instanceof WrappedLokum) || (l2 instanceof StripedLokum && l1 instanceof WrappedLokum)){
			System.out.println("Striped + Wrapped Merge");
			return new StripedWrappedMerge((MergeDestroyable) l1, (MergeDestroyable) l2);
		}else if(l1 instanceof BombLokum || l2 instanceof BombLokum){
			System.out.println("Bomb Merge");
			return new BombMerge( l1,  l2);
		}else if(l1 instanceof WrappedLokum && l2 instanceof WrappedLokum){
			System.out.println("Wrapped + Wrapped Merge");
			return new WrappedWrappedMerge((MergeDestroyable) l1, (MergeDestroyable) l2);
		}else{
			System.out.println("NOT A MERGE");
			return null;
		}
	}
}
