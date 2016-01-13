package App;

public class SpecialMoveDeactivateEvent extends GameEvent {

	@Override
	public void executeEvent() {
		InformationBoard.getInstance().setSpecialSwapDeactive();

	}

}
