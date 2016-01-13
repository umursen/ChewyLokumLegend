package App;

public class ClickListenerDeactiveEvent extends GameEvent {

	@Override
	public void executeEvent() {
		ClickListener.getInstance().deactivate();
	}

}
