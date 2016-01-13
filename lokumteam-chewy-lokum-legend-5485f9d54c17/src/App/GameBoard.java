package App;

import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import test.BoardLogicTest;

public class GameBoard extends JPanel {

	private LokumBox[][] boardArray;
	private static GameBoard instance;
	
	private GameBoard(){
		boardArray = new LokumBox[Constants.NUMBER_OF_LOKUMS][Constants.NUMBER_OF_LOKUMS];
		for(int i = 0; i < Constants.NUMBER_OF_LOKUMS; i++){
			for(int j = 0; j < Constants.NUMBER_OF_LOKUMS; j++){
				boardArray[i][j] = new LokumBox(new EmptyLogicField(i,j));
			}
		}
		requestFocusInWindow();
		addMouseListener(ClickListener.getInstance());
	}
	
	public static GameBoard getInstance(){
		if(instance == null){
			instance = new GameBoard();
		}
		return instance;
	}
	
	

	public static void resetInstance(){
		instance = null;
	}
	
	public void changeLokum(LogicField logicField){
		boardArray[logicField.getRowIndex()][logicField.getColumnIndex()].changeLogicField(logicField);
	}
	
	public void paintComponent(Graphics g){
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				boardArray[i][j].paintComponent(g);
			}
		}
	}
}