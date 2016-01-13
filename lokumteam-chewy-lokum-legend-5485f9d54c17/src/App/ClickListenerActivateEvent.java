package App;

public class ClickListenerActivateEvent extends GameEvent {

	@Override
	public void executeEvent() {
		ClickListener.getInstance().activate();
	}

}
