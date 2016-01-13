package App;

import java.util.ArrayList;
import java.util.Random;



public class BombMerge extends Merge {
	private BoardLogic board = BoardLogic.getInstance();
	private Lokum[][] lokumBoard =  board.getBoard();
	private Score score = Score.getInstance();
	
	public BombMerge(LogicField l1, LogicField l2){
		this.l1 = (Lokum) l1;
		this.l2 = (Lokum) l2;
	}

	public void destroyMerge(){
		if(l1 instanceof BombLokum)
			BoardLogic.getInstance().introduceLogicField(new EmptyLogicField(l1.getRowIndex(), l1.getColumnIndex()));
		if(l2 instanceof BombLokum)
			BoardLogic.getInstance().introduceLogicField(new EmptyLogicField(l2.getRowIndex(), l2.getColumnIndex()));

		if(l1 instanceof NormalLokum){
			String colorl1 = l1.getLokumColor();
			int numberOfDestroyedLokums = 0;
			System.out.println("l1: Normal Lokum, color: " + colorl1 + ", l2: Bomb Lokum, merge.");
			for(int i = 0; i < lokumBoard.length; i++){
				for(int j = 0; j < lokumBoard[i].length; j++){
					Lokum lokum =  lokumBoard[i][j];
					if(lokum.getLokumColor().equals(colorl1)){
						NormalLokum newLokum = new NormalLokum(i, j, colorl1);
						newLokum.comboDestroy();
						numberOfDestroyedLokums++;
					}
				}	
			}
			score.scoreUpdateBombUse(numberOfDestroyedLokums);
		}else if(l2 instanceof NormalLokum){
			String colorl2 = l2.getLokumColor();
			int numberOfDestroyedLokums = 0;
			System.out.println("l2: Normal Lokum, color: " + colorl2 + ", l1: Bomb Lokum, merge.");
			for(int i = 0; i < lokumBoard.length; i++){
				for(int j = 0; j < lokumBoard[i].length; j++){
					Lokum lokum = lokumBoard[i][j];
					if(lokum.getLokumColor().equals(colorl2)){
						NormalLokum newLokum = new NormalLokum(i, j, colorl2);
						newLokum.comboDestroy();
						numberOfDestroyedLokums++;
					}
				}
				
			}
			score.scoreUpdateBombUse(numberOfDestroyedLokums);
		}else if(l1 instanceof StripedLokum){
			ArrayList<Lokum> convertedLokums = new ArrayList<Lokum>();
			Random randomGenerator = new Random();
			String colorl1 = l1.getLokumColor();
			System.out.println("l1: Striped Lokum, color: " + colorl1 + ", l2: Bomb Lokum, merge.");
			for(int i = 0; i < lokumBoard.length; i++){
				for(int j = 0; j < lokumBoard[i].length; j++){
					Lokum lokum = lokumBoard[i][j];
					if(lokum.getLokumColor().equals(colorl1)){
						
						int randomStriped = randomGenerator.nextInt(2);
						if(randomStriped == 0){
							VerticalStripedLokum newLokum = new VerticalStripedLokum(i, j, colorl1);
							EventDispatchQueue.getInstance().addEvent(new LokumGenerateEvent(newLokum));
							convertedLokums.add(newLokum);
						}else if(randomStriped == 1){
							HorizontalStripedLokum newLokum = new HorizontalStripedLokum(i, j, colorl1);
							EventDispatchQueue.getInstance().addEvent(new LokumGenerateEvent(newLokum));
							convertedLokums.add(newLokum);
						}else{
							System.out.println("Error at Bomb + Striped Merge");
						}
					}
				}
			}
			for(int i = 0; i < convertedLokums.size(); i++){
				convertedLokums.get(i).comboDestroy();
			}
		}else if(l2 instanceof StripedLokum){
			ArrayList<Lokum> convertedLokums = new ArrayList<Lokum>();
			String colorl2 = l2.getLokumColor();
			Random randomGenerator = new Random();
			System.out.println("l2: Striped Lokum, color: " + colorl2 + ", l1: Bomb Lokum, merge.");
			for(int i = 0; i < lokumBoard.length; i++){
				for(int j = 0; j < lokumBoard[i].length; j++){
					Lokum lokum = lokumBoard[i][j];
					if(lokum.getLokumColor().equals(colorl2)){
						int randomStriped = randomGenerator.nextInt(2);
						if(randomStriped == 0){
							VerticalStripedLokum newLokum = new VerticalStripedLokum(i, j, colorl2);
							EventDispatchQueue.getInstance().addEvent(new LokumGenerateEvent(newLokum));
							convertedLokums.add(newLokum);
						}else if(randomStriped == 1){
							HorizontalStripedLokum newLokum = new HorizontalStripedLokum(i, j, colorl2);
							EventDispatchQueue.getInstance().addEvent(new LokumGenerateEvent(newLokum));
							convertedLokums.add(newLokum);
						}else{
							System.out.println("Error at Bomb + Striped Merge");
						}
					}
				}
				
			}
			for(int i = 0; i < convertedLokums.size(); i++){
				convertedLokums.get(i).comboDestroy();
			}
		}else if(l1 instanceof WrappedLokum){
			String colorl1 = l1.getLokumColor();
			String randomColor = generateRandomColor(colorl1);
			int numberOfDestroyedLokums = 0;
			System.out.println("l1: Wrapped Lokum, color: " + colorl1 + ", l2: Bomb Lokum, merge.");
			for(int i = 0; i < lokumBoard.length; i++){
				for(int j = 0; j < lokumBoard[i].length; j++){
					Lokum lokum = lokumBoard[i][j];
					if(lokum.getLokumColor().equals(colorl1)){
						NormalLokum newLokum = new NormalLokum(i, j, colorl1);
						newLokum.comboDestroy();
						score.scoreUpdateBombUse(1);
					}
					if(lokum.getLokumColor().equals(randomColor) ){
						NormalLokum newLokum = new NormalLokum(i, j, randomColor);
						newLokum.comboDestroy();
						score.scoreUpdateBombUse(1);
					}
				}
			}
			score.scoreUpdateBombUse(numberOfDestroyedLokums);
		}else if(l2 instanceof WrappedLokum){
			String colorl2 = l2.getLokumColor();
			String randomColor = generateRandomColor(colorl2);
			int numberOfDestroyedLokums = 0;
			System.out.println("l2: Wrapped Lokum, color: " + colorl2 + ", l1: Bomb Lokum, merge.");
			for(int i = 0; i < lokumBoard.length; i++){
				for(int j = 0; j < lokumBoard[i].length; j++){
					Lokum lokum = lokumBoard[i][j];
					if(lokum.getLokumColor().equals(colorl2)){
						NormalLokum newLokum = new NormalLokum(i, j, colorl2);
						newLokum.comboDestroy();
						score.scoreUpdateBombUse(1);
					}
					if(lokum.getLokumColor().equals(randomColor) ){
						NormalLokum newLokum = new NormalLokum(i, j, randomColor);
						newLokum.comboDestroy();
						score.scoreUpdateBombUse(1);
					}
				}
			}
			score.scoreUpdateBombUse(numberOfDestroyedLokums);
		}else if(l1 instanceof BombLokum || l2 instanceof BombLokum){
			for(int i = 0; i < lokumBoard.length; i++){
				for(int j = 0; j < lokumBoard[i].length; j++){
					NormalLokum newLokum = new NormalLokum(i, j, "white");
					newLokum.comboDestroy();
				}
			}
		
			score.scoreUpdateBombMerge(Constants.BOARD_HEIGHT * Constants.BOARD_WIDTH);
		}else{
			System.out.println("FATAL ERROR!: BOMBMERGE");
		}
	}
	
	private void destroyMostOccuredColor(){
		String color = getMostOccuredColor();
		for(int i = 0; i < lokumBoard.length; i++){
			for(int j = 0; j < lokumBoard[i].length; j++){
				Lokum lokum = lokumBoard[i][j];
				if(lokum.getLokumColor().equals(color)){
					((ComboDestroyable) lokum).comboDestroy();
				}
			}
		}
	}
	
	private String getMostOccuredColor(){
		int whiteCounter = 0;
		int brownCounter = 0;
		int redCounter = 0;
		int greenCounter = 0;
		String redColor = Constants.LOKUM_COLOR_RED;
		String brownColor = Constants.LOKUM_COLOR_BROWN;
		String whiteColor = Constants.LOKUM_COLOR_WHITE;
		String greenColor = Constants.LOKUM_COLOR_GREEN;
		
		for(int i = 0; i < lokumBoard.length; i++){
			for(int j = 0; j < lokumBoard[i].length; j++){
				Lokum lokum = lokumBoard[i][j];
				String lokumColor = lokum.getLokumColor();
				if(lokumColor.equals(redColor)){
					redCounter++;
				}else if(lokumColor.equals(brownColor)){
					brownCounter++;
				}else if(lokumColor.equals(whiteColor)){
					whiteCounter++;
				}else if(lokumColor.equals(greenColor)){
					greenCounter++;
				}else{
					System.out.println("Not a valid color");
				}
			}
		}
		
		int mostColor = Math.max(Math.max(whiteCounter, greenCounter), Math.max(redCounter, brownCounter));
		if(mostColor == redCounter){
			return redColor;
		}else if(mostColor == greenCounter){
			return greenColor;
		}else if(mostColor == brownCounter){
			return brownColor;
		}else if(mostColor == whiteCounter){
			return whiteColor;
		}else{
			return null;
		}
	}
	
	private String generateRandomColor(String currentLokumColor){
		Random rgen = new Random();
		int randomNumber = rgen.nextInt(4);
		switch (randomNumber){
			case 0:
				if(Constants.LOKUM_COLOR_BROWN.equals(currentLokumColor)){
					return generateRandomColor(currentLokumColor);
				}
				return Constants.LOKUM_COLOR_BROWN;
			case 1:
				if(Constants.LOKUM_COLOR_GREEN.equals(currentLokumColor)){
					return generateRandomColor(currentLokumColor);
				}
				return Constants.LOKUM_COLOR_GREEN;
			case 2:
				if(Constants.LOKUM_COLOR_RED.equals(currentLokumColor)){
					return generateRandomColor(currentLokumColor);
				}
				return  Constants.LOKUM_COLOR_RED;
			case 3:
				if(Constants.LOKUM_COLOR_WHITE.equals(currentLokumColor)){
					return generateRandomColor(currentLokumColor);
				}
				return Constants.LOKUM_COLOR_WHITE;
			default:
				return null;
		}
	}
	
	public boolean repOK(){
		return super.repOK()&&(l1 instanceof BombLokum || l2 instanceof BombLokum);
	}
}
