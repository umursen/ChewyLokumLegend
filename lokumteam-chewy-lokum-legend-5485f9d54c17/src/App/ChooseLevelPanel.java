package App;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.*;

public class ChooseLevelPanel extends JPanel {
	
	private JLabel chooseLevel;
	private static ChooseLevelPanel instance;
	private int numberOfLevels;
	
	private ChooseLevelPanel() {
		
		chooseLevel = new JLabel("Choose Level",JLabel.CENTER);
		chooseLevel.setFont(new Font("Tahoma", Font.BOLD, 40));
		chooseLevel.setForeground(Constants.TITLE_COLOR);
		numberOfLevels = 20;		

		setLayout(null);
		setBackground(Constants.GAME_BACKGROUND_COLOR);
		setSize(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
		
		add(chooseLevel);
		chooseLevel.setBounds(0, 50, Constants.SCREEN_WIDTH, 120);
		chooseLevel.setHorizontalAlignment(SwingConstants.CENTER);
		
		add(LevelBoard.getInstance());
		LevelBoard.getInstance().setBounds(0,100, 
				Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT-120);
	}
	
	public static ChooseLevelPanel getInstance(){
		if(instance==null){
			instance = new ChooseLevelPanel();
		}
		return instance;
	}
	
	public void resetInstance(){
		instance = null;
	}	
}
