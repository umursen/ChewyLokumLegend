package App;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;


public abstract class Combo implements Comparator<Combo>{
	public ArrayList<Lokum> lokums;
	int comboPriority;
	
	public abstract ArrayList<Lokum> getComboLokums();
	
	public abstract Lokum getGeneratedLokum();
	
	public abstract void updateScore();
	
	public void addLokumToCombo(Lokum argLokum){
		lokums.add(argLokum);
	}
	
	public abstract void addGeneratedLokumtoQueue();
	
	public boolean hasHigherPriority(Combo argCombo){
		return this.comboPriority > argCombo.comboPriority;
	}
	public boolean hasSamePriority(Combo argCombo){
		return this.comboPriority == argCombo.comboPriority;
	}
	public boolean hasLowerPriority(Combo argCombo){
		return this.comboPriority < argCombo.comboPriority;
	}
	
	public void addComboToLokums(){
		ArrayList<Lokum> comboLokums = this.getComboLokums();
		for(int comboLokumsIndex=0;comboLokumsIndex<comboLokums.size();comboLokumsIndex++){
			comboLokums.get(comboLokumsIndex).addComboToComboList(this);
		}
	}

	@Override
	public int compare(Combo c1, Combo c2) {
		// TODO Auto-generated method stub
		if(c1.comboPriority < c2.comboPriority)
			return -1;
		else if(c1.comboPriority == c2.comboPriority)
			return 0;
		else
			return 1;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if( !(obj instanceof Combo) ){
//			System.out.println("Compared object is not a Combo.");
			return false;
		}
		/*
		 * If here, then obj is an instance of Combo. I guess checking for sub-type equality is not a must.
		 * The reason is, in the following lines, Combos are compared lokum by lokum. That is: In order for
		 * two combos must be the same, their lokums must exactly be the same. And in the game, there
		 * cannot be a scenario where two combos have exactly the same lokums but those combos are not
		 * the same.
		 */
		Combo argCombo = (Combo) obj;
		ArrayList<Lokum> argComboLokums = argCombo.getComboLokums();
		if( this.lokums.size() != argComboLokums.size() ){
//			System.out.println("Lokum amounts of Combos do not match.");
			return false;
		}
		/*
		 * if here, then sizes of the comboLokums of two combos are the same. So check if
		 * each element is present in one another.
		 */
		for(int i=0;i<lokums.size();i++){
			Lokum currentLokum = lokums.get(i);
			if(!argComboLokums.contains(currentLokum)){
//				System.out.println("Current lokum is not present in the compared combo.");
				return false;
			}
		}
		/*
		 * if here, then it has passed all tests. So return true.
		 */
//		System.out.println("Combos are the same.");
		return true;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int sum = 0;
		if(lokums == null)
			System.out.println("The field Combo:lokums is null!");
		for (int currentLokumIndex = 0; currentLokumIndex < lokums.size(); currentLokumIndex++) {
			Lokum currentLokum = lokums.get(currentLokumIndex);
			sum+=currentLokum.getRowIndex();
			sum+=currentLokum.getColumnIndex();
		}
		return sum;
	}

	@Override
	public String toString(){
		if(lokums.isEmpty())
			return "EmptyCombo";
		String stringToReturn = "{";
		int i;
		for(i=0;i<lokums.size()-1;i++)
			stringToReturn = stringToReturn + lokums.get(i).toString() + ", ";
		stringToReturn = stringToReturn + lokums.get(i).toString() + "}";
		return stringToReturn;
	}
	/**
	 * 
	 * @return
	 */
	public boolean repOK(){
		return (lokums!=null&&comboPriority>0);
	}
	
	/**
	 * This method compares the elements of the current combo with the elements of the argument combo. If there
	 * is at least one common element, it returns true. Otherwise, it returns true.
	 * @param argumentCombo
	 * @return
	 */
	boolean hasCommonElement(Combo argumentCombo){
		ArrayList<Lokum> argCombosLokums = argumentCombo.getComboLokums();
		int thisCombosNumberOfLokums = this.lokums.size();
		int argumentCombosNumberOfLokums = argCombosLokums.size();
		
		for (int thisCombosLokumIndex = 0; thisCombosLokumIndex < thisCombosNumberOfLokums; thisCombosLokumIndex++) {
			Lokum thisCombosCurrentLokum = this.lokums.get(thisCombosLokumIndex);
			for (int argumentCombosLokumIndex = 0; argumentCombosLokumIndex < argumentCombosNumberOfLokums; argumentCombosLokumIndex++) {
				if(argCombosLokums.get(argumentCombosLokumIndex).equals(thisCombosCurrentLokum))
					return true;
			}
		}
		return false;
	}
	
	/**
	 * This method receives a priority queue containing combos from lower to higher priority, and it determines
	 * if this combo has any common elements with the combos in the priority queue.
	 * 
	 * @requires
	 * -Given priority queue contains combos that is of higher priority with this combo. Note that this is a
	 * reasonable assumption, because the combos are polled one by one from the priority queue and hence, the
	 * priority queue's elements when calling this method contains the elements that have combos with greater
	 * than or equal to priorities.  
	 * @param boardCombos
	 * @return
	 */
	boolean hasCommonElementWithHigherCombos(PriorityQueue<Combo> boardCombos){
		Object[] boardCombosAsObjectArray = boardCombos.toArray();
		/*
		 * NOTE: Note that the elements returned by the line above are returned in no particular order, but
		 * this is not a problem for us, since we want to compare this combo with ALL combos in the priority
		 * queue.
		 */
		for (int currentCombosIndex = 0; currentCombosIndex < boardCombosAsObjectArray.length; currentCombosIndex++) {
			Combo currentCombo = (Combo) boardCombosAsObjectArray[currentCombosIndex];
			if(this.hasCommonElement(currentCombo))
				return true;
		}
		return false;
	}
	
}
