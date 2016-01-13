package App;

import java.util.*;

public class FiveCombo extends Combo implements LokumGeneratingCombo{
	private Lokum generatedLokum;
	
	public FiveCombo(ArrayList<Lokum> lokums){
		this.lokums = lokums;
		generateLokum();
		//test = new FiveComboTest();
		this.comboPriority = 4;
		
		
	}
	
	public void generateLokum(){
		Lokum midLokum = lokums.get(2);
		int x = midLokum.getRowIndex();
		int y = midLokum.getColumnIndex();
		this.generatedLokum = new BombLokum(x, y);
	}
	
	public Lokum getGeneratedLokum(){
		
		return generatedLokum;
	}
	
	public void addGeneratedLokumtoQueue(){
		BombLokum copy = (BombLokum) copyLokum(generatedLokum);
		LokumGenerateEvent lge = new LokumGenerateEvent(copy);
		EventDispatchQueue.getInstance().addEvent(lge);
	}
	

	@Override
	public ArrayList<Lokum> getComboLokums() {
		// TODO Auto-generated method stub
		return lokums;
	}
	
	/*
	 * What's the method below doing here? What's its job?
	 * (non-Javadoc)
	 * @see App.LokumGeneratingCombo#copyLokum(App.Lokum)
	 */
	@Override
	public Lokum copyLokum(Lokum lok) {
		int x = lok.getRowIndex();
		int y = lok.getColumnIndex();
		return new BombLokum(x, y);
	}

	@Override
	public void updateScore() {
		// TODO Auto-generated method stub
		Score.getInstance().scoreUpdateBombForm();
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "FiveCombo = " /*+ super.toString()*/;
	}
	public boolean repOK(){
		return super.repOK()&&lokums.size()==5;
	}
}
