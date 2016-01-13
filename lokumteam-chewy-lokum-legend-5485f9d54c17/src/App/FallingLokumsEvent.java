package App;

import java.util.ArrayList;
import java.util.HashMap;



public class FallingLokumsEvent extends GameEvent {
	
	HashMap<LogicField,Integer> fallingLokums;
	
	public FallingLokumsEvent(HashMap<LogicField, Integer> fallingLogicFields){
		
		this.fallingLokums = fallingLogicFields;
		
	}
	
	@Override
	public void executeEvent() {
		ArrayList<LogicField> lokumArray = new ArrayList<LogicField>();
		ArrayList<Integer> shiftArray = new ArrayList<Integer>();
		
		for(LogicField lokum: fallingLokums.keySet()){
			lokumArray.add(lokum);
			shiftArray.add(fallingLokums.get(lokum));
		}
		int count = 0;
		int target_count = lokumArray.size();
		
		while(count<target_count){
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("Exception on event waiting.");
			}
			count = 0;
			for(int i=0; i<target_count; i++){
				LogicField lokum = lokumArray.get(i);
				int lokumShift = shiftArray.get(i);
				if(lokumShift>0){
					lokum.setRowIndex(lokum.getRowIndex()-1);
					shiftArray.set(i, lokumShift-1);
					if(lokum.getRowIndex()<Constants.BOARD_HEIGHT){
						GameBoard.getInstance().changeLokum(lokum.copyLogicField());
					}
				}else
					count++;
			}
			GameBoard.getInstance().repaint();
		}
	}
}
