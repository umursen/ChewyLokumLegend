package App;

public class TimeUpdateEvent extends GameEvent {
	
	private int newTime;
	
	public TimeUpdateEvent(int newTime){
		this.newTime = newTime;
	}

	@Override
	public void executeEvent() {
		TimeLevelPanel.getInstance().setTimeLeft(newTime);
	}

}
