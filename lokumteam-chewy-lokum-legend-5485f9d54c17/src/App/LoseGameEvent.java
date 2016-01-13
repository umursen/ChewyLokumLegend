package App;

public class LoseGameEvent extends GameEvent {

	@Override
	public void executeEvent() {
			GameOverPanel.getInstance().setWin(false);
			GamePanel.getInstance().endGame();		
	}

}
