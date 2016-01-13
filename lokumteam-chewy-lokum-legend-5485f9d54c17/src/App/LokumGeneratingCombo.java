package App;


public interface LokumGeneratingCombo {
	public abstract void generateLokum();
	public abstract Lokum copyLokum(Lokum lok);
	public abstract void addGeneratedLokumtoQueue();
}
