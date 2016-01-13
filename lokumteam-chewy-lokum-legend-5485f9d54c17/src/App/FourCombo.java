package App;

import java.util.ArrayList;


public class FourCombo extends Combo implements LokumGeneratingCombo{
	private Lokum generatedLokum;
	
	public FourCombo(ArrayList<Lokum> lokums){
		this.lokums = lokums;
		generateLokum();
		//test = new FourComboTest();
		this.comboPriority = 2;
	}
	
	
	
	public void generateLokum(){
		Lokum midLokum = lokums.get(1);
		Lokum nextLokum = lokums.get(2);
		int xMid = midLokum.getRowIndex();
		int yMid = midLokum.getColumnIndex();
		int xNext = nextLokum.getRowIndex();
		
		String color = midLokum.getLokumColor();
		if((xMid + 1) != xNext){
			this.generatedLokum = new HorizontalStripedLokum(xMid, yMid, color);
		}else{
			this.generatedLokum = new VerticalStripedLokum(xMid, yMid, color);
		}
	}
	
	public Lokum getGeneratedLokum(){
		return generatedLokum;
	}

	@Override
	public ArrayList<Lokum> getComboLokums() {
		// TODO Auto-generated method stub
		return lokums;
	}



	@Override
	public Lokum copyLokum(Lokum lok) {
		int x = lok.getRowIndex();
		int y = lok.getColumnIndex();
		String color = lok.getLokumColor();
		if(generatedLokum instanceof VerticalStripedLokum){
			return new VerticalStripedLokum(x, y, color);
		}else{
			return new HorizontalStripedLokum(x, y, color);
		}
	}



	@Override
	public void addGeneratedLokumtoQueue() {
		// TODO Auto-generated method stub
		StripedLokum copy = (StripedLokum) copyLokum(generatedLokum);
		LokumGenerateEvent lge = new LokumGenerateEvent(copy);
		EventDispatchQueue.getInstance().addEvent(lge);
	}



	@Override
	public void updateScore() {
		// TODO Auto-generated method stub
		Score.getInstance().scoreUpdateStripedForm();
		
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "FourCombo = " /*+ super.toString()*/;
	}
	
	public boolean repOK(){
		return super.repOK()&&lokums.size()==4;
	}
}
