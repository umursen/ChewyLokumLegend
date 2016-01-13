package App;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GameOverPanel extends JPanel{

	private static GameOverPanel instance;
	private JLabel gameOver,victory;
	private JButton replay, nextLevel, mainMenu;
	private boolean win;

	public GameOverPanel(){

		victory = new JLabel("Victory!");
		gameOver = new JLabel("Game Over"); 
		nextLevel = new JButton("Next Level");
		replay = new JButton("Replay");
		mainMenu= new JButton("Main Menu");
		
		setLayout(null);
		setBackground(Color.RED);

			add(victory);
			victory.setBounds(80, 100, 200, 60);
			victory.setForeground(Color.BLACK);
			victory.setFont(new Font("Tahoma", Font.BOLD, 40));
			victory.setHorizontalAlignment(SwingConstants.CENTER);

			add(gameOver);
			gameOver.setBounds(80, 100, 200, 60);
			gameOver.setForeground(Color.BLACK);
			gameOver.setFont(new Font("Tahoma", Font.BOLD, 30));
			gameOver.setHorizontalAlignment(SwingConstants.CENTER);
			
			

		add(mainMenu);
		mainMenu.setBounds(190, 250, 150, 50);
		mainMenu.setBorderPainted(false);
		mainMenu.setFocusPainted(false);

		replay.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
												
				int level = Options.getInstance().getLevel();
				ApplicationWindow.removePanel(GamePanel.getInstance());
				Score.resetInstance();
				Options.resetInstance();
				Level.resetInstance();
				GamePanel.resetInstance();
				GameBoard.resetInstance();
				BoardLogic.resetInstance();
				InformationBoard.resetInstance();
				EventDispatchQueue.resetInstance();
				GameOverPanel.resetInstance();
				InformationBoard.getInstance().repaint();
				ApplicationWindow.addPanel(GamePanel.getInstance());
				GamePanel.getInstance().remove(GameOverPanel.getInstance());
				GamePanel.getInstance().remove(InformationBoard.getInstance());
				
				GamePanel.getInstance().add(GameBoard.getInstance());
				GamePanel.getInstance().add(InformationBoard.getInstance());
				
				InformationBoard.getInstance().setCurrentLevel(level);

				Score.getInstance().setScore(0);
				
				GamePanel.getInstance().requestFocusInWindow();
				GamePanel.getInstance().startGame();
				GameBoard.getInstance().repaint();
				InformationBoard.getInstance().repaint();
			}

		});

		nextLevel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int level = Level.getInstance().getCurrentLevel();
				ApplicationWindow.removePanel(GamePanel.getInstance());
				Score.resetInstance();
				Options.resetInstance();
				Level.resetInstance();
				BoardLogic.resetInstance();
				GamePanel.resetInstance();
				GameBoard.resetInstance();
				InformationBoard.resetInstance();
				EventDispatchQueue.resetInstance();
				GameOverPanel.resetInstance();
				
				Options.getInstance().setLevel(level+1);
				InformationBoard.getInstance().setCurrentLevel(level+1);;
				ApplicationWindow.addPanel(GamePanel.getInstance());
				GamePanel.getInstance().requestFocusInWindow();
				InformationBoard.getInstance().requestFocusInWindow();
				GameBoard.getInstance().requestFocusInWindow();
				GamePanel.getInstance().startGame();
				GamePanel.getInstance().repaint();
				InformationBoard.getInstance().repaint();
			}

		});


		mainMenu.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int level = Options.getInstance().getLevel();
				ApplicationWindow.removePanel(GamePanel.getInstance());
				Score.resetInstance();
				Options.resetInstance();
				Level.resetInstance();
				BoardLogic.resetInstance();
				GamePanel.resetInstance();
				GameBoard.resetInstance();
				InformationBoard.resetInstance();
				EventDispatchQueue.resetInstance();
				GameOverPanel.resetInstance();
				
				InformationBoard.getInstance().setCurrentLevel(level);
				ApplicationWindow.removePanel(ChooseLevelPanel.getInstance());
				ApplicationWindow.addPanel(MenuPanel.getInstance());
				MenuPanel.getInstance().requestFocusInWindow();
			}
		});

		repaint();
	}

	public void setWin(boolean result){
		if(result==true)
		this.win=true;
		else
		this.win=false;
	}
	
	public boolean getWinState(){
		return this.win;
	}
	
	public static GameOverPanel getInstance(){
		if(instance==null){
			instance = new GameOverPanel();
		}
		return instance;
	}
	
	public static void resetInstance(){
		instance = new GameOverPanel();
	}	
	
	public void paint(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(0,0,Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		g.setColor(new Color(0,0,0));
		
		if(win==true){
			add(nextLevel);
			victory.repaint();
			victory.setOpaque(true);
			victory.setBackground(Color.RED);
			nextLevel.setBounds(20, 250, 150, 50);
			nextLevel.setBorderPainted(false);
			nextLevel.setFocusPainted(false);
			nextLevel.repaint();
		} else {
			add(replay);
			gameOver.repaint();
			gameOver.setOpaque(true);
			gameOver.setBackground(Color.RED);
			replay.setBounds(20, 250, 150, 50);
			replay.setBorderPainted(false);
			replay.setFocusPainted(false);
			replay.repaint();
		}
		mainMenu.repaint();
	}
}
