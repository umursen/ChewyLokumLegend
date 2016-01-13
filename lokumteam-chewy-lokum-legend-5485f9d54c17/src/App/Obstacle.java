package App;


public class Obstacle extends LogicField{
	
	private String obstacleColor;

	public Obstacle(int rowIndex, int columnIndex, String obstacleColor) {
		super(rowIndex, columnIndex);
		this.obstacleColor = obstacleColor;
	}

	@Override
	public LogicField copyLogicField() {
		// TODO Auto-generated method stub
		return new Obstacle(getRowIndex(), getColumnIndex(), getObstacleColor());
	}

	public String getObstacleColor() {
		return obstacleColor;
	}

	public void setObstacleColor(String obstacleColor) {
		this.obstacleColor = obstacleColor;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Obstacle";
	}

}