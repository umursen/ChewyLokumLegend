package App;

public class DestroyLokumEvent extends GameEvent {

	EmptyLogicField destroyedLokum;
	
	public DestroyLokumEvent(EmptyLogicField destroyedLokum){
		this.destroyedLokum = destroyedLokum;
	}
	
	@Override
	public void executeEvent() {
		GameBoard.getInstance().changeLokum(destroyedLokum);
		GameBoard.getInstance().repaint();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception on event waiting.");
		}
	}
}
