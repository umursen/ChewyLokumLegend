package App;

import java.util.*;

public class ThreeCombo extends Combo {
	
	public ThreeCombo(ArrayList<Lokum> lokums){
		this.lokums = lokums;
		this.comboPriority = 1;	
	}
	

	@Override
	public Lokum getGeneratedLokum() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Lokum> getComboLokums() {
		// TODO Auto-generated method stub
		return lokums;
	}

	@Override
	public void addGeneratedLokumtoQueue() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateScore() {
		// TODO Auto-generated method stub
		Score.getInstance().scoreUpdateThreeCombo();
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ThreeCombo = " /*+ super.toString()*/;
	}
	
	public boolean repOK(){
		return super.repOK()&&lokums.size()==3;
	}
}
