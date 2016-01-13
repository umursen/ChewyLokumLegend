package App;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TimeLevelPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel timeLeftInput,timeLeft;
	private int time;
	
	private static TimeLevelPanel instance;
	
	public TimeLevelPanel(){
	
		timeLeft = new JLabel("Time");
		timeLeftInput = new JLabel("0"+time);
		
		setLayout(null);
		setBackground(Constants.GAME_BACKGROUND_COLOR);
		
		add(timeLeft);
		timeLeft.setBounds(0, 0, 80, 30);
		timeLeft.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		add(timeLeftInput);
		timeLeftInput.setBounds(100, 0, 80, 30);
		timeLeftInput.setFont(new Font("Tahoma", Font.BOLD, 20));

		timeLeft.setOpaque(true);
		timeLeft.setBackground(Constants.GAME_BACKGROUND_COLOR);
		timeLeft.setForeground(Constants.TITLE_COLOR);
		
		timeLeftInput.setOpaque(true);
		timeLeftInput.setBackground(Constants.GAME_BACKGROUND_COLOR);
		timeLeftInput.setForeground(Constants.TITLE_COLOR);
		
	}
	
	public void paint(Graphics g){
		super.paintComponent(g);
    	timeLeft.repaint();
    	timeLeftInput.repaint();
	}
	
	public static TimeLevelPanel getInstance(){
		if(instance==null){
			instance = new TimeLevelPanel();
		}
		return instance;
	}
	
	public static void resetInstance(){
		instance = null;
	}
	
	public void decreaseTime(){
		this.time--;
		timeLeftInput.setText(""+time);
	}
	
	public void setTime(int currentLevel){
		this.time = Constants.NUMBER_OF_MOVES - 3*currentLevel;
	}

	public void setTimeLeft(int time){
		this.time = time;
		timeLeftInput.setText(""+time);
	}
	
	public int getTimeLeft(){
		return this.time;
	}
	
}
