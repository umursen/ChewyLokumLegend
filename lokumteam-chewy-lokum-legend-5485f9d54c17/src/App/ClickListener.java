package App;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class ClickListener implements MouseListener {
	
	Integer selectedRow = null;
	Integer selectedColumn = null; 
	boolean active = false;
	private static ClickListener instance;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		//System.out.println("clicked");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//System.out.println("pressed");
		if(active){
			if(selectedRow == null && selectedColumn == null){
				selectedRow = convertToRowIndex(e.getY());
				selectedColumn = convertToColumnIndex(e.getX());
			}else{
				int otherRow = convertToRowIndex(e.getY());
				int otherColumn = convertToColumnIndex(e.getX());
				BoardLogic.getInstance().swap(selectedColumn, selectedRow, otherColumn, otherRow);
				selectedRow = null;
				selectedColumn = null;
				//System.out.println("swapped request sent");
			}
		}	
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if(active){
			if(selectedRow != null && selectedColumn != null){
				int otherRow = convertToRowIndex(e.getY());
				int otherColumn = convertToColumnIndex(e.getX());
				if(!(selectedRow == otherRow && selectedColumn == otherColumn)){
					BoardLogic.getInstance().swap(selectedColumn, selectedRow, otherColumn, otherRow);
					selectedRow = null;
					selectedColumn = null;
				}
			}
		}
		
	}

	
	private int convertToRowIndex(Integer yLocation){
		return (int) (Constants.ANIMATION_WINDOW_HEIGHT-yLocation)/(Constants.ANIMATION_WINDOW_HEIGHT/Constants.BOARD_HEIGHT);
	}
	
	private int convertToColumnIndex(Integer xLocation){
		return (int) xLocation/(Constants.ANIMATION_WINDOW_WIDTH/Constants.BOARD_WIDTH);
	}

	public static ClickListener getInstance() {
		if (instance == null)
			instance = new ClickListener();
		return instance;
	}
	public void activate(){
		active = true;
	}
	public void deactivate(){
		active = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public static void resetInstance() {
		// TODO Auto-generated method stub
		instance=null;
	}

}
