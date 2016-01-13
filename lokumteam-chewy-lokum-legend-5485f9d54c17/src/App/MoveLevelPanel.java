package App;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MoveLevelPanel extends JPanel {
	private JLabel movesLeftInput,movesLeft;
	private int moves;
	
	private static MoveLevelPanel instance;
	
	public MoveLevelPanel(){
	
		movesLeft = new JLabel("Moves");
		movesLeftInput = new JLabel(""+moves);
		
		setLayout(null);
		setBackground(Constants.GAME_BACKGROUND_COLOR);
		
		add(movesLeft);
		movesLeft.setBounds(0, 0, 80, 30);
		movesLeft.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		add(movesLeftInput);
		movesLeftInput.setBounds(100, 0, 80, 30);
		movesLeftInput.setFont(new Font("Tahoma", Font.BOLD, 20));

		movesLeft.setOpaque(true);
		movesLeft.setBackground(Constants.GAME_BACKGROUND_COLOR);
		movesLeft.setForeground(Constants.TITLE_COLOR);
		
		movesLeftInput.setOpaque(true);
		movesLeftInput.setBackground(Constants.GAME_BACKGROUND_COLOR);
		movesLeftInput.setForeground(Constants.TITLE_COLOR);
		
	}
	
	public void paint(Graphics g){
		super.paintComponent(g);
    	movesLeft.repaint();
    	movesLeftInput.repaint();
	}
	
	public static MoveLevelPanel getInstance(){
		if(instance==null){
			instance = new MoveLevelPanel();
		}
		return instance;
	}
	
	public static void resetInstance(){
		instance = null;
	}
	
	public void decreaseMoves(){
		this.moves--;
		movesLeftInput.setText(""+moves);
	}
	
	public void setMoves(int moves){
		this.moves = moves;
	}

	public void setMovesText(int moves) {
		movesLeftInput.setText(""+moves);		
	}

	public void setMovesLeft(int moves){
		this.moves = moves;
		movesLeftInput.setText(""+moves);
	}
	
	public int getMovesLeft(){
		return this.moves;
	}
}
