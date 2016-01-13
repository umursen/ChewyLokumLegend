package App;

public class StartTimerEvent extends GameEvent {

	@Override
	public void executeEvent() {
		((TimeLevel)Level.getInstance()).startTimer();
	}

}
