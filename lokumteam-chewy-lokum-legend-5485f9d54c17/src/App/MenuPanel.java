package App;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.*;

public class MenuPanel extends JPanel {
	
	private JButton start,loadGame,exit;
	private JLabel label;
	private static MenuPanel instance;
	
	public MenuPanel() {
		label = new JLabel("Chewy Lokum Legend",JLabel.CENTER);
		start = new JButton("Start");
		loadGame = new JButton("Load Game");
		exit = new JButton("Exit");
		label.setFont(new Font("Tahoma", Font.BOLD, 50));
		label.setForeground(Constants.TITLE_COLOR);

		setLayout(new GridBagLayout());
		setBackground(Constants.GAME_BACKGROUND_COLOR);
		setSize(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
		
		GridBagConstraints c = new GridBagConstraints();

		c.gridx= 0;
		c.gridy= 0;
		c.insets = new Insets(10,10,10,10);		

		add(label, c);

		c.gridx= 0;
		c.gridy= 1;
		add(start,c);

		c.gridx= 0;
		c.gridy= 2;
		add(loadGame,c);

		c.gridx= 0;
		c.gridy= 3;
		add(exit,c);
		
		start.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				ApplicationWindow.removePanel(instance);
				ApplicationWindow.addPanel(ChooseLevelPanel.getInstance());
				ChooseLevelPanel.getInstance().requestFocusInWindow();
				ChooseLevelPanel.getInstance().repaint();
			}
		});
		
		loadGame.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Score.resetInstance();
				Options.resetInstance();
				Level.resetInstance();
				BoardLogic.resetInstance();
				GamePanel.resetInstance();
				GameBoard.resetInstance();
				InformationBoard.resetInstance();
				EventDispatchQueue.resetInstance();
				if(LoadGame.loadGameFromXML()){
					System.out.println("Loaded");
					ApplicationWindow.removePanel(instance);
					ApplicationWindow.addPanel(GamePanel.getInstance());
					GamePanel.getInstance().startGame();
					GameBoard.getInstance().requestFocusInWindow();
					GamePanel.getInstance().repaint();
				}
			}
			
		});

				
		exit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});	
	}
	
	public static MenuPanel getInstance(){
		if(instance==null){
			instance = new MenuPanel();
		}
		return instance;
	}
	
	public void resetInstance(){
		instance = null;
	}	
}
