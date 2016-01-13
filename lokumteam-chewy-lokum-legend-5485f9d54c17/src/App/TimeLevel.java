package App;

import java.util.Timer;
import java.util.TimerTask;


public class TimeLevel extends Level {
	
	private int timeLeft;
	private Timer timer;
	private TimerTask timerTask;

	
	public TimeLevel(){
		levelNumber = Options.currentLevel;
		updateTime(Math.max(Constants.TIME - 3*levelNumber,20));
		System.out.println("started!");
	}
	
	public int getTime(){
		return timeLeft;
	}
	public void updateTime(int newTime){
		timeLeft = newTime;
		EventDispatchQueue.getInstance().addEvent(new TimeUpdateEvent(newTime));
	}
	
	public void pauseTimer(){
		timer.cancel();
	}
	public void startTimer(){
		timer = new Timer ();
		timerTask = new TimerTask(){

			@Override
			public void run() {
				updateTime(timeLeft-1);
				System.out.println(timeLeft);
				if(timeLeft == 0){
					EventDispatchQueue.getInstance().addEvent(new LoseGameEvent());
					stopTimer();
					resetInstance();
				}
			}

		};
		timer.schedule(timerTask, 1000, 1000);
	}
	public void stopTimer(){
		timer.cancel();
	}
	
}
