package App;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.*;

public class LevelBoard extends JPanel {

	private JLabel label;
	private JButton[] buttonArray;
	private static LevelBoard instance;
	private int numberOfLevels = Constants.NUMBER_OF_LEVELS;

	private LevelBoard() {

		setLayout(new GridBagLayout());
		setBackground(Constants.GAME_BACKGROUND_COLOR);
		setSize(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);

		GridBagConstraints c = new GridBagConstraints();

		c.gridx= 0;
		c.gridy= 0;
		c.insets = new Insets(30,30,30,30);		

		int counterX = 0;
		int counterY = 0;
		
		buttonArray = new JButton[numberOfLevels];
		
		for(int n=0;n<numberOfLevels;n++){

			if(n%Constants.NUMBER_OF_COLUMNS == 0){
				counterX=0;
				counterY++;
			}

			c.gridx= counterX;
			c.gridy= counterY;

			buttonArray[n] = new JButton("Level " + (n + 1));
			add(buttonArray[n],c);
			counterX++;
			final int level = n;
			buttonArray[n].addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					Level.resetInstance();
					Score.resetInstance();
					Options.resetInstance();
					BoardLogic.resetInstance();
					GamePanel.resetInstance();
					GameBoard.resetInstance();
					InformationBoard.resetInstance();
					EventDispatchQueue.resetInstance();
					Options.getInstance().setLevel(level+1);
					
					ApplicationWindow.removePanel(ChooseLevelPanel.getInstance());
					ApplicationWindow.addPanel(GamePanel.getInstance());
					GamePanel.getInstance().requestFocusInWindow();
					InformationBoard.getInstance().requestFocusInWindow();
					GameBoard.getInstance().requestFocusInWindow();
					GamePanel.getInstance().startGame();
					GamePanel.getInstance().repaint();
					InformationBoard.getInstance().repaint();
				}
			});
		}
	}

	public static LevelBoard getInstance(){
		if(instance==null){
			instance = new LevelBoard();
		}
		return instance;
	}

	public void resetInstance(){
		instance = null;
	}	
}