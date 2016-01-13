package App;


public class SwapEvent extends GameEvent {
	
	Lokum swapLokum1;
	Lokum swapLokum2;
	
	public SwapEvent(Lokum swapLokum1, Lokum swapLokum2){
		this.swapLokum1 = swapLokum1;
		this.swapLokum2 = swapLokum2;
	}
	
	@Override
	public void executeEvent() {
		GameBoard.getInstance().changeLokum(swapLokum1);
		GameBoard.getInstance().changeLokum(swapLokum2);
		GameBoard.getInstance().repaint();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
