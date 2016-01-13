package App;

public class TimeLokum extends NormalLokum{
	
	int timeAward;
	
	public TimeLokum(int rowIndex, int columnIndex, String color) {
		super(rowIndex, columnIndex, color);
		timeAward = (int)( 1+ Math.random()*5);
	}
	
	@Override
	public void comboDestroy() {
		((TimeLevel)Level.getInstance()).updateTime(((TimeLevel)Level.getInstance()).getTime()+timeAward);
		super.comboDestroy();
	
	}

	@Override
	public LogicField copyLogicField() {
		TimeLokum copyLokum =new TimeLokum(getRowIndex(), getColumnIndex(), getLokumColor());
		copyLokum.setTimeAward(timeAward);
		return copyLokum;
	}

	private void setTimeAward(int timeAward) {
		this.timeAward = timeAward;
	}
	
	public int getTimeAward(){
		return timeAward;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "TimeLokum";
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if( !(super.equals(obj)) )
			return false;
		if( !(obj instanceof TimeLokum) )
			return false;
		return true;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "TL" + "(" + this.rowIndex + ", " + this.columnIndex + ")";
	}
	
	public boolean repOK(){
		return super.repOK()&&timeAward>=1&&timeAward<=5;
	}
	

}
