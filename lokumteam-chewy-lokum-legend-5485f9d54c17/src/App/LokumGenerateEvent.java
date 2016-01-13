package App;

public class LokumGenerateEvent extends GameEvent{

	Lokum generatedLokum;
	
	public LokumGenerateEvent(Lokum generatedLokum){
		this.generatedLokum = generatedLokum;
	}
	
	
	@Override
	public void executeEvent(){
		GameBoard.getInstance().changeLokum(generatedLokum);
		GameBoard.getInstance().repaint();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception on event waiting.");
		}
	}
}
