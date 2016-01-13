package App;

public class WinGameEvent extends GameEvent{
	
	
	@Override
	public void executeEvent() {
		if(Level.getInstance() instanceof TimeLevel){
			((TimeLevel)Level.getInstance()).stopTimer();
		}
		GameOverPanel.getInstance().setWin(true);
		GamePanel.getInstance().endGame();
	}

}
