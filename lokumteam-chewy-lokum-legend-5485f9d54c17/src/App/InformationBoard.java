package App;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InformationBoard extends JPanel {
	private JLabel level,target,score,specialMoves,levelInput,targetInput,scoreInput,specialMovesInput;
	private JButton saveButton,specialMovesButton;
	private Color specialSwapOvalColor;
	private static InformationBoard instance;

	public InformationBoard(){

		level = new JLabel("Level");
		target = new JLabel("Target");
		score = new JLabel("Score");
		specialMoves = new JLabel("SM");
		levelInput = new JLabel("");
		targetInput = new JLabel("");
		scoreInput = new JLabel("");
		specialMovesInput = new JLabel("");
		saveButton = new JButton("Save Game");
		specialMovesButton = new JButton("Special Move");
		specialSwapOvalColor = Color.RED;
		
		setCurrentLevel(Options.getInstance().getLevel());
		setGoalScore(Options.getInstance().getTargetScore());
		
		setLayout(null);
		setBackground(Constants.GAME_BACKGROUND_COLOR);
		
		add(level);
		level.setBounds(0, 0,80,30);
		level.setFont(new Font("Tahoma", Font.BOLD, 20));

		add(target);
		target.setBounds(0, 60, 80, 30);
		target.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		add(score);
		score.setBounds(0, 120, 80, 30);
		score.setFont(new Font("Tahoma", Font.BOLD, 20));

		add(levelInput);
		levelInput.setBounds(100, 0,80,30);
		levelInput.setFont(new Font("Tahoma", Font.BOLD, 20));

		add(targetInput);
		targetInput.setBounds(100, 60, 80, 30);
		targetInput.setFont(new Font("Tahoma", Font.BOLD, 20));

		add(scoreInput);
		scoreInput.setBounds(100, 120, 80, 30);
		scoreInput.setFont(new Font("Tahoma", Font.BOLD, 20));

		if (Options.getInstance().getLevel()%2 == 1){
			add(MoveLevelPanel.getInstance());
			MoveLevelPanel.getInstance().setBounds(0,180,200,30);
		} else {
			add(TimeLevelPanel.getInstance());
			TimeLevelPanel.getInstance().setBounds(0,180,200,30);
		}

		add(specialMoves);
		specialMoves.setBounds(0, 240, 80, 30);
		specialMoves.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		add(specialMovesInput);
		specialMovesInput.setBounds(100, 240, 80, 30);
		specialMovesInput.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		add(specialMovesButton);
		specialMovesButton.setBounds(0, 300, 150, 50);
		specialMovesButton.setBorderPainted(false);
		specialMovesButton.setFocusPainted(false);
		specialMovesButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int remainedSpecialMoves = Level.getInstance().getSpecialMoves();
				if(remainedSpecialMoves != 0){
					if(!BoardLogic.getInstance().isSpecialSwapActive()){
						setSpecialSwapActive();
						BoardLogic.getInstance().setSpecialSwapActive(true);
					}else{
						setSpecialSwapDeactive();
						BoardLogic.getInstance().setSpecialSwapActive(false);
					}
				}
			}

		});
		
		add(saveButton);
		saveButton.setBounds(0, 380, 150, 50);
		saveButton.setBorderPainted(false);
		saveButton.setFocusPainted(false);

		level.setOpaque(true);
		level.setBackground(Constants.GAME_BACKGROUND_COLOR);
		level.setForeground(Constants.TITLE_COLOR);

		target.setOpaque(true);
		target.setBackground(Constants.GAME_BACKGROUND_COLOR);
		target.setForeground(Constants.TITLE_COLOR);

		score.setOpaque(true);
		score.setBackground(Constants.GAME_BACKGROUND_COLOR);
		score.setForeground(Constants.TITLE_COLOR);
		
		specialMoves.setOpaque(true);
		specialMoves.setBackground(Constants.GAME_BACKGROUND_COLOR);
		specialMoves.setForeground(Constants.TITLE_COLOR);

		levelInput.setOpaque(true);
		levelInput.setBackground(Constants.GAME_BACKGROUND_COLOR);
		levelInput.setForeground(Constants.TITLE_COLOR);

		targetInput.setOpaque(true);
		targetInput.setBackground(Constants.GAME_BACKGROUND_COLOR);
		targetInput.setForeground(Constants.TITLE_COLOR);

		scoreInput.setOpaque(true);
		scoreInput.setBackground(Constants.GAME_BACKGROUND_COLOR);
		scoreInput.setForeground(Constants.TITLE_COLOR);

		specialMovesInput.setOpaque(true);
		specialMovesInput.setBackground(Constants.GAME_BACKGROUND_COLOR);
		specialMovesInput.setForeground(Constants.TITLE_COLOR);
		
		saveButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					SaveGame.saveBoardToXML();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				GamePanel.getInstance().stopGame();
				ApplicationWindow.removePanel(GamePanel.getInstance());
				ApplicationWindow.addPanel(MenuPanel.getInstance());
				MenuPanel.getInstance().requestFocusInWindow();
				MenuPanel.getInstance().repaint();
				Score.resetInstance();
				Options.resetInstance();
				if(Level.getInstance() instanceof TimeLevel)
					((TimeLevel)Level.getInstance()).stopTimer();
				Level.resetInstance();
				BoardLogic.resetInstance();
				InformationBoard.resetInstance();
				EventDispatchQueue.resetInstance();
				GamePanel.resetInstance();
				GameBoard.resetInstance();
			}
		});
	}
	
	public void setSpecialSwapActive(){
		specialSwapOvalColor = Color.GREEN;
		repaint();
	}
	
	public void setSpecialSwapDeactive(){
		specialSwapOvalColor = Color.RED;
		repaint();
	}

	public void paint(Graphics g){
		super.paintComponent(g);

		level.repaint();
		target.repaint();
		score.repaint();
		specialMoves.repaint();
		
		levelInput.repaint();
		targetInput.repaint();
		scoreInput.repaint();
		specialMovesInput.repaint();
		
		saveButton.repaint();
		specialMovesButton.repaint();
		
		
		g.setColor(specialSwapOvalColor);
		g.fillOval(165, 300, 50, 50);
		
		if (Options.getInstance().getLevel()%2 == 1){
		MoveLevelPanel.getInstance().repaint();
		} else {
		TimeLevelPanel.getInstance().repaint();
		}
	}

	public static InformationBoard getInstance(){
		if(instance==null){
			instance = new InformationBoard();
		}
		return instance;
	}

	public static void resetInstance(){
		instance = null;
	}

	public void updateScore(int currentScore) {
		scoreInput.setText(""+currentScore);
	}

	public void setCurrentLevel(int currentLevel){
		levelInput.setText(""+currentLevel);
	}

	public void setGoalScore(int goalScore) {
		targetInput.setText(""+goalScore);
	}

	public void setSpecialMoves(int sm){
		specialMovesInput.setText(""+ sm);
	}

}