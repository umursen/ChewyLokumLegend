package App;

import java.util.ArrayList;

public class LCombo extends Combo implements LokumGeneratingCombo{
	private Lokum generatedLokum;
	
	public LCombo(ArrayList<Lokum> lokums){
		this.lokums = lokums;
		generateLokum();
		//test = new LComboTest();
		this.comboPriority = 3;
		
	}
	
	public Lokum getGeneratedLokum(){
		
		return generatedLokum;
	}
	
	public void generateLokum(){
		Lokum midLokum = lokums.get(2);
		int x = midLokum.getRowIndex();
		int y = midLokum.getColumnIndex();
		String color = midLokum.getLokumColor();
		this.generatedLokum = new WrappedLokum(x, y, color);
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
		return new WrappedLokum(x, y, color);
	}

	@Override
	public void addGeneratedLokumtoQueue() {
		// TODO Auto-generated method stub
		WrappedLokum copy = (WrappedLokum) copyLokum(generatedLokum);
		LokumGenerateEvent lge = new LokumGenerateEvent(copy);
		EventDispatchQueue.getInstance().addEvent(lge);
	}

	@Override
	public void updateScore() {
		// TODO Auto-generated method stub
		Score.getInstance().scoreUpdateWrappedForm();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "LCombo = " /*+ super.toString()*/;
	}
	public boolean repOK(){
		return super.repOK()&&lokums.size()==5;
	}
}
