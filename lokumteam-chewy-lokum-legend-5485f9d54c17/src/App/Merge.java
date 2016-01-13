package App;

public abstract class Merge {
	public Lokum l1;
	public Lokum l2;
	public abstract void destroyMerge();
	
	public boolean repOK(){
		return l1!=null&&l2!=null;
	}
}
