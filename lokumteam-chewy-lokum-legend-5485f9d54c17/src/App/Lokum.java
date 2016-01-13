package App;

import java.util.ArrayList;


public abstract class Lokum extends LogicField implements ComboDestroyable{

	String lokumColor;
	ArrayList<Combo> combosThisLokumIn;

	/**
	 * Whenever a lokum is instantiated, the location in BoardLogic corresponding to Lokum's location is set to this instance.
	 * @param lokumColor
	 * @param logicFieldXPosition
	 * @param logicFieldYPosition
	 */
	public Lokum(int rowIndex, int columnIndex, String lokumColor){
		super(rowIndex, columnIndex);
		this.lokumColor = lokumColor;
		this.combosThisLokumIn = new ArrayList<Combo>();
		/*
		 * Writing: "BoardLogic.getInstance.introduceLogicField(this);" generated an error message. Hence, it is removed. But is any solution made for removing that line?
		 */
	}

	public String getLokumColor() {
		return lokumColor;
	}

	public void setLokumColor(String lokumColor) {
		this.lokumColor = lokumColor;
	}
	
	public ArrayList<Combo> getCombosThisLokumIn() {
		return combosThisLokumIn;
	}

	/**
	 * This method takes in adjacent lokums and returns a number according to the adjacency combination.
	 * @param leftLokum
	 * @param rightLokum
	 * @param aboveLokum
	 * @param belowLokum
	 * @return
	 */
	private int getAdjacentLokumCombinationNumber(Lokum leftLokum, Lokum rightLokum, Lokum aboveLokum, Lokum belowLokum){
		int combinationValue = 0; // initially, set combinationValue to 0.
		
		if(leftLokum != null)
			combinationValue += Math.pow(2, 3);
		if(rightLokum != null)
			combinationValue += Math.pow(2, 2);
		if(aboveLokum != null)
			combinationValue += Math.pow(2, 1);
		if(belowLokum != null)
			combinationValue += Math.pow(2, 0);
		return combinationValue;
	}
	
	/**
	 * This method takes in a combo to add to the combosThisLokumIn field of this lokum. After that there are 3 options:
	 * O.1) combosThisLokumIn is empty: Then just add the combo to add and return.
	 * O.2) combosThisLokumIn is empty but combo to add has a higher priority then the Combos in the combosThisLokumIn: In this case, combosThisLokumIn is first cleared,
	 * since the new combo has a higher priority and hence, makes the older combos obsole.
	 * O.3) combosThisLokumIn is empty and combo to add has the same priority then the Combos in the combosThisLokumIn: In this case, simply add the combo to add to
	 * combosThisLokumIn.
	 * @param comboToAdd
	 */
	public void addComboToComboList(Combo comboToAdd){
		if(combosThisLokumIn.isEmpty())
			combosThisLokumIn.add(comboToAdd);
		else{
			Combo firstComboThisLokumIn = combosThisLokumIn.get(0);
			if(comboToAdd.hasHigherPriority(firstComboThisLokumIn)){
				combosThisLokumIn.clear();
				combosThisLokumIn.add(comboToAdd);
			}
			else if(comboToAdd.hasSamePriority(firstComboThisLokumIn)){
				combosThisLokumIn.add(comboToAdd);
			}
		}
	}
	
	public void findCombos(){
		/*
		 * Before beginning a new search, clear the combosThisLokumIn field.
		 * 
		 * NOTE: Actually, this instruction is not needed. The reason is, if a lokum was in a combo, then
		 * that lokum will be destroyed and hence, it will not exist in the board at the time of the next
		 * call to the findBoardCombos method.
		 * 
		 * IMPORTANT NOTE: Actually, this instruction is erroneous. The reason is, in a call to findCombos
		 * of a prior Lokum, a Combo might have been added to the combosThisLokumIn field of this Lokum.
		 * Removing that Combo it may lead to incorrect behavior. So I am commenting out the line below. 
		 */
//		combosThisLokumIn.clear();
		/*
		 * Then, the method checks if this Lokum is an instance of BombLokum. If so, it returns immediately from the method. If not, it keeps on finding the combos which
		 * this Lokum participates in.
		 */
		if(this instanceof BombLokum)
			return;
		/*
		 * If here, then "this" is not an instance of BombLokum. So get the Lokums that are adjacent to it.
		 */
		
		Lokum leftLokum = (Lokum) getLeftLogicField();
		Lokum rightLokum = (Lokum) getRightLogicField();
		Lokum aboveLokum = (Lokum) getAboveLogicField();
		Lokum belowLokum = (Lokum) getBelowLogicField();
		
		ArrayList<Lokum> comboLokums = new ArrayList<Lokum>();
		
		int alc = getAdjacentLokumCombinationNumber(leftLokum, rightLokum, aboveLokum, belowLokum);
		
		/*
		 * Some lokum references that may be used in the switch case below.
		 */
		Lokum leftLeftLokum = null;
		Lokum rightRightLokum = null;
		Lokum aboveAboveLokum = null;
		Lokum belowBelowLokum = null;

		if(leftLokum != null)
			leftLeftLokum = (Lokum) leftLokum.getLeftLogicField();
		if(rightLokum != null)
			rightRightLokum = (Lokum) rightLokum.getRightLogicField();
		if(aboveLokum != null)
			aboveAboveLokum = (Lokum) aboveLokum.getAboveLogicField();
		if(belowLokum != null)
			belowBelowLokum = (Lokum) belowLokum.getBelowLogicField();
		
		Combo comboToAdd;
		
		switch(alc){
		case 0x0:
			/* Combination: 0000
			 * Form:
			 * 
			 * 		X
			 * 		|
			 * 	 X--T--X
			 * 		|
			 * 		X
			 * Possible Combinations:
			 * -No combinations are possible in such a case. So simply return.
			 */
			break;
		case 0x1:
			/* Combination: 0001
			 * Form:
			 * 
			 * 		X
			 * 		|
			 * 	 X--T--X
			 * 		|
			 * 		L
			 * Possible Combinations:
			 * -No combinations are possible in such a case. So simply return.
			 */
			break;
		case 0x2:
			/* Combination: 0010
			 * Form:
			 * 
			 * 		L
			 * 		|
			 * 	 X--T--X
			 * 		|
			 * 		X
			 * Possible Combinations:
			 * -No combinations are possible in such a case. So simply return.
			 */
			break;
		case 0x3:
			/* Combination: 0011
			 * Form:
			 * 
			 * 		L
			 * 		|
			 * 	 X--T--X
			 * 		|
			 * 		L
			 * Highest Definite Combination:
			 * -3V2
			 * Possible Higher Combinations:
			 * -4V1
			 * -4V2
			 * -5V1
			 */
			
			/*
			 * First, put the lokums of the Highest Definite Combination to the comboLokums.
			 */
			comboLokums.add(belowLokum);
			comboLokums.add(this);
			comboLokums.add(aboveLokum);
			/*
			 * Check if the combo is 5V1. 
			 */
			if( (aboveAboveLokum != null) && (belowBelowLokum != null) ){
				/*
				 * If here, then we have a 5V1. So form it and return.
				 */
				comboLokums.add(0, belowBelowLokum); // insert belowBelowLokum to the beginning of this list.
				comboLokums.add(aboveAboveLokum);	 // insert belowBelowLokum to the end of this list.
				comboToAdd = new FiveCombo(comboLokums);
			}
			/*
			 * Check if the combo is 4V1. 
			 */
			else if(belowBelowLokum != null){
				comboLokums.add(0, belowBelowLokum);
				comboToAdd = new FourCombo(comboLokums); // NEED TO INDICATE THE FOUR COMBO TYPE.(horizontal OR vertical)
			}
			/*
			 * Check if the combo is 4V2. 
			 */
			else if(aboveAboveLokum != null){
				comboLokums.add(aboveAboveLokum);
				comboToAdd = new FourCombo(comboLokums); // NEED TO INDICATE THE FOUR COMBO TYPE.(horizontal OR vertical)
			}
			else{
				/*
				 * If here, then no higher combos are available. So simply form the 3 combo and return.
				 */
				comboToAdd = new ThreeCombo(comboLokums);
			}
			comboToAdd.addComboToLokums();
			break;
		case 0x4:
			/* Combination: 0100
			 * Form:
			 * 
			 * 		X
			 * 		|
			 * 	 X--T--L
			 * 		|
			 * 		X
			 * Possible Combinations:
			 * -No combinations are possible in such a case. So simply return.
			 */
			break;
		case 0x5:
			/* Combination: 0101
			 * Form:
			 * 
			 * 		X
			 * 		|
			 * 	 X--T--L
			 * 		|
			 * 		L
			 * Possible Combinations:
			 * -L1
			 */
			
			/*
			 * Check if the combo is L1. 
			 */
			if( (belowBelowLokum != null) && (rightRightLokum != null) ){
				/*
				 * If here, then we have an L1. So form it and return.
				 */
				comboLokums.add(belowBelowLokum);
				comboLokums.add(belowLokum);
				comboLokums.add(this);
				comboLokums.add(rightLokum);
				comboLokums.add(rightRightLokum);
				comboToAdd = new LCombo(comboLokums);	// CHECK THE CONSTRUCTOR OF THE LCombo!
				comboToAdd.addComboToLokums();
			}
			break;
		case 0x6:
			/* Combination: 0110
			 * Form:
			 * 
			 * 		L
			 * 		|
			 * 	 X--T--L
			 * 		|
			 * 		X
			 * Possible Combinations:
			 * -L4
			 */
			
			/*
			 * Check if the combo is L4. 
			 */
			if( (aboveAboveLokum != null) && (rightRightLokum != null) ){
				/*
				 * If here, then we have an L4. So form it and return.
				 */
				comboLokums.add(aboveAboveLokum);
				comboLokums.add(aboveLokum);
				comboLokums.add(this);
				comboLokums.add(rightLokum);
				comboLokums.add(rightRightLokum);
				comboToAdd = new LCombo(comboLokums);	// CHECK THE CONSTRUCTOR OF THE LCombo!
				comboToAdd.addComboToLokums();
			}
			break;
		case 0x7:
			/* Combination: 0111
			 * Form:
			 * 
			 * 		L
			 * 		|
			 * 	 X--T--L
			 * 		|
			 * 		L
			 * Highest Definite Combination:
			 * -3V2
			 * Possible Higher Combinations:
			 * -Some T shape.
			 * -4V1
			 * -4V2
			 * -5V1
			 */
			
			/*
			 * First, put the lokums of the Highest Definite Combination to the comboLokums.
			 */
			comboLokums.add(belowLokum);
			comboLokums.add(this);
			comboLokums.add(aboveLokum);
			/*
			 * Check if the combo is 5V1. 
			 */
			if( (aboveAboveLokum != null) && (belowBelowLokum != null) ){
				/*
				 * If here, then we have a 5V1. So form it and return.
				 */
				comboLokums.add(0, belowBelowLokum); // insert belowBelowLokum to the beginning of this list.
				comboLokums.add(aboveAboveLokum);	 // insert belowBelowLokum to the end of this list.
				comboToAdd = new FiveCombo(comboLokums);
			}
			/*
			 * Check if the combo is some T shape. 
			 */
			else if(rightRightLokum != null){
				comboLokums.add(rightLokum);
				comboLokums.add(rightRightLokum);
				comboToAdd = new TCombo(comboLokums);	// CHECK CONSTRUCTOR OF TCombo.
			}
			/*
			 * Check if the combo is 4V1. 
			 */
			else if(belowBelowLokum != null){
				comboLokums.add(0, belowBelowLokum);
				comboToAdd = new FourCombo(comboLokums); // NEED TO INDICATE THE FOUR COMBO TYPE.(horizontal OR vertical)
			}
			/*
			 * Check if the combo is 4V2. 
			 */
			else if(aboveAboveLokum != null){
				comboLokums.add(aboveAboveLokum);
				comboToAdd = new FourCombo(comboLokums); // NEED TO INDICATE THE FOUR COMBO TYPE.(horizontal OR vertical)
			}
			else{
				/*
				 * If here, then no higher combos are available. So simply form the 3 combo and return.
				 */
				comboToAdd = new ThreeCombo(comboLokums);
			}
			comboToAdd.addComboToLokums();
			break;
		case 0x8:
			/* Combination: 1000
			 * Form:
			 * 
			 * 		X
			 * 		|
			 * 	 L--T--X
			 * 		|
			 * 		X
			 * Possible Combinations:
			 * -No combinations are possible in such a case. So simply return.
			 */
			break;
		case 0x9:
			/* Combination: 1001
			 * Form:
			 * 
			 * 		X
			 * 		|
			 * 	 L--T--X
			 * 		|
			 * 		L
			 * Possible Combinations:
			 * -L2
			 */
			
			/*
			 * Check if the combo is L2. 
			 */
			if( (leftLeftLokum != null) && (belowBelowLokum != null) ){
				/*
				 * If here, then we have an L2. So form it and return.
				 */
				comboLokums.add(leftLeftLokum);
				comboLokums.add(leftLokum);
				comboLokums.add(this);
				comboLokums.add(belowLokum);
				comboLokums.add(belowBelowLokum);
				comboToAdd = new LCombo(comboLokums);	// CHECK THE CONSTRUCTOR OF THE LCombo!
				comboToAdd.addComboToLokums();
			}
			break;
		case 0xA:
			/* Combination: 1010
			 * Form:
			 * 
			 * 		L
			 * 		|
			 * 	 L--T--X
			 * 		|
			 * 		X
			 * Possible Combinations:
			 * -L3
			 */
			
			/*
			 * Check if the combo is L3. 
			 */
			if( (leftLeftLokum != null) && (aboveAboveLokum != null) ){
				/*
				 * If here, then we have an L3. So form it and return.
				 */
				comboLokums.add(leftLeftLokum);
				comboLokums.add(leftLokum);
				comboLokums.add(this);
				comboLokums.add(aboveLokum);
				comboLokums.add(aboveAboveLokum);
				comboToAdd = new LCombo(comboLokums);	// CHECK THE CONSTRUCTOR OF THE LCombo!
				comboToAdd.addComboToLokums();
			}
			break;
		case 0xB:
			/* Combination: 1011
			 * Form:
			 * 
			 * 		L
			 * 		|
			 * 	 L--T--X
			 * 		|
			 * 		L
			 * Highest Definite Combination:
			 * -3V2
			 * Possible Higher Combinations:
			 * -Some T shape.
			 * -4V1
			 * -4V2
			 * -5V1
			 */
			
			/*
			 * First, put the lokums of the Highest Definite Combination to the comboLokums.
			 */
			comboLokums.add(belowLokum);
			comboLokums.add(this);
			comboLokums.add(aboveLokum);
			/*
			 * Check if the combo is 5V1. 
			 */
			if( (aboveAboveLokum != null) && (belowBelowLokum != null) ){
				/*
				 * If here, then we have a 5V1. So form it and return.
				 */
				comboLokums.add(0, belowBelowLokum); // insert belowBelowLokum to the beginning of this list.
				comboLokums.add(aboveAboveLokum);	 // insert belowBelowLokum to the end of this list.
				comboToAdd = new FiveCombo(comboLokums);
			}
			/*
			 * Check if the combo is some T shape. 
			 */
			else if(leftLeftLokum != null){
				comboLokums.add(leftLokum);
				comboLokums.add(leftLeftLokum);
				comboToAdd = new TCombo(comboLokums); // CHECK CONSTRUCTOR OF TCombo.
			}
			/*
			 * Check if the combo is 4V1. 
			 */
			else if(belowBelowLokum != null){
				comboLokums.add(0, belowBelowLokum);
				comboToAdd = new FourCombo(comboLokums); // NEED TO INDICATE THE FOUR COMBO TYPE.(horizontal OR vertical)
			}
			/*
			 * Check if the combo is 4V2. 
			 */
			else if(aboveAboveLokum != null){
				comboLokums.add(aboveAboveLokum);
				comboToAdd = new FourCombo(comboLokums); // NEED TO INDICATE THE FOUR COMBO TYPE.(horizontal OR vertical)
			}
			else{
				/*
				 * If here, then no higher combos are available. So simply form the 3 combo and return.
				 */
				comboToAdd = new ThreeCombo(comboLokums);
			}
			comboToAdd.addComboToLokums();
			break;
		case 0xC:
			/* Combination: 1100
			 * Form:
			 * 
			 * 		X
			 * 		|
			 * 	 L--T--L
			 * 		|
			 * 		X
			 * Highest Definite Combination:
			 * -3H2
			 * Possible Higher Combinations:
			 * -4H1
			 * -4H2
			 * -5H1
			 */
			
			/*
			 * First, put the lokums of the Highest Definite Combination to the comboLokums.
			 */
			comboLokums.add(leftLokum);
			comboLokums.add(this);
			comboLokums.add(rightLokum);
			/*
			 * Check if the combo is 5V1. 
			 */
			if( (leftLeftLokum != null) && (rightRightLokum != null) ){
				/*
				 * If here, then we have a 5V1. So form it and return.
				 */
				comboLokums.add(0, leftLeftLokum); // insert belowBelowLokum to the beginning of this list.
				comboLokums.add(rightRightLokum);	 // insert belowBelowLokum to the end of this list.
				comboToAdd = new FiveCombo(comboLokums);
			}
			/*
			 * Check if the combo is 4V1. 
			 */
			else if(leftLeftLokum != null){
				comboLokums.add(0, leftLeftLokum);
				comboToAdd = new FourCombo(comboLokums); // NEED TO INDICATE THE FOUR COMBO TYPE.(horizontal OR vertical)
			}
			/*
			 * Check if the combo is 4V2. 
			 */
			else if(rightRightLokum!= null){
				comboLokums.add(rightRightLokum);
				comboToAdd = new FourCombo(comboLokums); // NEED TO INDICATE THE FOUR COMBO TYPE.(horizontal OR vertical)
			}
			else{
				/*
				 * If here, then no higher combos are available. So simply form the 3 combo and return.
				 */
				comboToAdd = new ThreeCombo(comboLokums); 
			}
			comboToAdd.addComboToLokums();
			break;
		case 0xD:
			/* Combination: 1101
			 * Form:
			 * 
			 * 		X
			 * 		|
			 * 	 L--T--L
			 * 		|
			 * 		L
			 * Highest Definite Combination:
			 * -3H2
			 * Possible Higher Combinations:
			 * -Some T shape.
			 * -4H1
			 * -4H2
			 * -5H1
			 */
			
			/*
			 * First, put the lokums of the Highest Definite Combination to the comboLokums.
			 */
			comboLokums.add(leftLokum);
			comboLokums.add(this);
			comboLokums.add(rightLokum);
			/*
			 * Check if the combo is 5V1. 
			 */
			if( (leftLeftLokum != null) && (rightRightLokum != null) ){
				/*
				 * If here, then we have a 5V1. So form it and return.
				 */
				comboLokums.add(0, leftLeftLokum); // insert belowBelowLokum to the beginning of this list.
				comboLokums.add(rightRightLokum);	 // insert belowBelowLokum to the end of this list.
				comboToAdd = new FiveCombo(comboLokums);
			}
			/*
			 * Check if the combo is some T shape. 
			 */
			else if(belowBelowLokum != null){
				comboLokums.add(belowLokum);
				comboLokums.add(belowBelowLokum);
				comboToAdd = new TCombo(comboLokums); //CHECK CONSTRUCTOR OF TCombo.
			}
			/*
			 * Check if the combo is 4V1. 
			 */
			else if(leftLeftLokum != null){
				comboLokums.add(0, leftLeftLokum);
				comboToAdd = new FourCombo(comboLokums); // NEED TO INDICATE THE FOUR COMBO TYPE.(horizontal OR vertical)
			}
			/*
			 * Check if the combo is 4V2. 
			 */
			else if(rightRightLokum!= null){
				comboLokums.add(rightRightLokum);
				comboToAdd = new FourCombo(comboLokums); // NEED TO INDICATE THE FOUR COMBO TYPE.(horizontal OR vertical)
			}
			else{
				/*
				 * If here, then no higher combos are available. So simply form the 3 combo and return.
				 */
				comboToAdd = new ThreeCombo(comboLokums); 
			}
			comboToAdd.addComboToLokums();
			break;
		case 0xE:
			/* Combination: 1110
			 * Form:
			 * 
			 * 		L
			 * 		|
			 * 	 L--T--L
			 * 		|
			 * 		X
			 * Highest Definite Combination:
			 * -3H2
			 * Possible Higher Combinations:
			 * -Some T shape.
			 * -4H1
			 * -4H2
			 * -5H1
			 */
			
			/*
			 * First, put the lokums of the Highest Definite Combination to the comboLokums.
			 */
			comboLokums.add(leftLokum);
			comboLokums.add(this);
			comboLokums.add(rightLokum);
			/*
			 * Check if the combo is 5V1. 
			 */
			if( (leftLeftLokum != null) && (rightRightLokum != null) ){
				/*
				 * If here, then we have a 5V1. So form it and return.
				 */
				comboLokums.add(0, leftLeftLokum); // insert belowBelowLokum to the beginning of this list.
				comboLokums.add(rightRightLokum);	 // insert belowBelowLokum to the end of this list.
				comboToAdd = new FiveCombo(comboLokums);
			}
			/*
			 * Check if the combo is some T shape. 
			 */
			else if(aboveAboveLokum != null){
				comboLokums.add(aboveLokum);
				comboLokums.add(aboveAboveLokum);
				comboToAdd = new TCombo(comboLokums); //CHECK CONSTRUCTOR OF TCombo.
			}
			/*
			 * Check if the combo is 4V1. 
			 */
			else if(leftLeftLokum != null){
				comboLokums.add(0, leftLeftLokum);
				comboToAdd = new FourCombo(comboLokums); // NEED TO INDICATE THE FOUR COMBO TYPE.(horizontal OR vertical)
			}
			/*
			 * Check if the combo is 4V2. 
			 */
			else if(rightRightLokum!= null){
				comboLokums.add(rightRightLokum);
				comboToAdd = new FourCombo(comboLokums); // NEED TO INDICATE THE FOUR COMBO TYPE.(horizontal OR vertical)
			}
			else{
				/*
				 * If here, then no higher combos are available. So simply form the 3 combo and return.
				 */
				comboToAdd = new ThreeCombo(comboLokums); 
			}
			comboToAdd.addComboToLokums();
			break;
		case 0xF:
			/* Combination: 1111
			 * Form:
			 * 
			 * 		L
			 * 		|
			 * 	 L--T--L
			 * 		|
			 * 		L
			 * Highest Definite Combination:
			 * -T2
			 * Possible Higher Combinations:
			 * -5H1
			 * -5V1
			 */
			
			if( (leftLeftLokum != null) && (rightRightLokum != null) ){
				comboLokums.add(leftLeftLokum);
				comboLokums.add(leftLokum);
				comboLokums.add(this);
				comboLokums.add(rightLokum);
				comboLokums.add(rightRightLokum);
				comboToAdd = new FiveCombo(comboLokums);
				comboToAdd.addComboToLokums();
				comboLokums.clear();
			}
			if( (aboveAboveLokum != null) && (belowBelowLokum != null) ){
				comboLokums.add(aboveAboveLokum);
				comboLokums.add(aboveLokum);
				comboLokums.add(this);
				comboLokums.add(belowLokum);
				comboLokums.add(belowBelowLokum);
				comboToAdd = new FiveCombo(comboLokums);
			}
			else{
				comboLokums.add(leftLokum);
				comboLokums.add(aboveLokum);
				comboLokums.add(this);
				comboLokums.add(belowLokum);
				comboLokums.add(rightLokum);
				comboToAdd = new TCombo(comboLokums); //CHECK THE DEFINITION OF TCombo!
			}
			comboToAdd.addComboToLokums();
			break;
		}
	}
	
	/**
	 * Remaining possible combo scenario:
	 * 3H1)
	 * 		T-L-L
	 * 3H2)
	 * 		L-T-L
	 * 3H3)
	 * 		L-L-T
	 * 
	 * @requires:
	 * R.1) 3H2
	 * 4H1)
	 * 		L-T-L-L
	 * 
	 * @requires:
	 * R.1) 3H2
	 * 4H2)
	 * 		L-L-T-L
	 * 
	 * @requires:
	 * R.1) 3H2
	 * R.2) 4H1
	 * R.3) 4H2
	 * 5H1)
	 * 		L-L-T-L-L
	 * 
	 * 3V1)  T
	 * 		 |
	 * 		 L
	 * 		 |
	 * 		 L
	 * 
	 * 3V2)  L
	 * 		 |
	 * 		 T
	 * 		 |
	 * 		 L
	 * 
	 * 3V3)  L
	 * 		 |
	 * 		 L
	 * 		 |
	 * 		 T
	 * 
	 * @requires:
	 * R.1) 3V2
	 * 4V1)  L
	 * 		 |
	 * 		 T
	 * 		 |
	 * 		 L
	 * 		 |
	 * 		 L
	 * 
	 * @requires:
	 * R.1) 3V2
	 * 4V2)  L
	 * 		 |
	 * 		 L
	 * 		 |
	 * 		 T
	 * 		 |
	 * 		 L
	 * 
	 * @requires:
	 * R.1) 3V2
	 * R.2) 4V1
	 * R.3) 4V2
	 * 5V1)  L
	 * 		 |
	 * 		 L
	 * 		 |
	 * 		 T
	 * 		 |
	 * 		 L
	 * 		 |
	 * 		 L
	 * 
	 * 
	 * @requires:
	 * R.1) 3H1
	 * R.2) 3V2
	 * T1)
	 * 		L
	 * 		|
	 * 		T--L--L
	 * 		|
	 * 		L
	 * 
	 * @requires:
	 * R.1) 3H2
	 * R.2) 3V2
	 * T2)
	 * 		   L
	 * 		   |
	 * 		L--T--L
	 * 		   |
	 * 		   L
	 * 
	 * @requires:
	 * R.1) 3H3
	 * R.2) 3V2
	 * T3)
	 * 		      L
	 * 		      |
	 * 		L--L--T
	 * 		      |
	 * 		      L
	 * 
	 * @requires:
	 * R.1) 3H1
	 * R.2) 3V1
	 * L1)  T--L--L
	 * 		|
	 * 		L
	 * 		|
	 * 		L
	 * 
	 * @requires:
	 * R.1) 3H3
	 * R.2) 3V1
	 * L2)   L--L--T
	 * 			   |
	 * 			   L
	 * 			   |
	 * 			   L
	 * 
	 * @requires:
	 * R.1) 3H3
	 * R.2) 3V3
	 * L3)			L
	 * 				|
	 * 				L
	 * 				|
	 * 		  L--L--T
	 * 
	 * @requires:
	 * R.1) 3H1
	 * R.2) 3V3
	 * L4)   L
	 * 		 |
	 * 		 L
	 * 		 |
	 * 		 T--L--L
	 */

	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * All possible combo scenarios:
	 * H.1)
	 * 		T-L-L
	 * H.2)
	 * 		L-T-L
	 * H.3)
	 * 		L-L-T
	 * H.4)
	 * 		NOT POSSIBLE! T-L-L-L
	 * H.5)
	 * 		L-T-L-L
	 * H.6)
	 * 		L-L-T-L
	 * H.7)
	 * 		NOT POSSIBLE! L-L-L-T
	 * H.8)
	 * 		NOT POSSIBLE! T-L-L-L-L
	 * H.9)
	 * 		NOT POSSIBLE! L-T-L-L-L
	 * H.10)
	 * 		L-L-T-L-L
	 * H.11)
	 * 		NOT POSSIBLE! L-L-L-T-L
	 * H.12)
	 * 		NOT POSSIBLE! L-L-L-L-T
	 * 
	 * --------------------------------------------------------------------------------------------------------------
	 * Wrapped forming lokums:
	 * T-Shapes:
	 * S.1)
	 * 		L
	 * 		|
	 * 		T--L--L
	 * 		|
	 * 		L
	 * S.2)
	 * 		   L
	 * 		   |
	 * 		L--T--L
	 * 		   |
	 * 		   L
	 * S.3)
	 * 		      L
	 * 		      |
	 * 		L--L--T
	 * 		      |
	 * 		      L
	 * L-Shapes:
	 * --------------------------------------------------------
	 * S.4) T--L--L
	 * 		|
	 * 		L
	 * 		|
	 * 		L
	 * 
	 * NOT POSSIBLE! 
	 * S.5) L--T--L
	 * 		|
	 * 		L
	 * 		|
	 * 		L
	 * 
	 * NOT POSSIBLE! 
	 * S.6) L--L--T
	 * 		|
	 * 		L
	 * 		|
	 * 		L
	 * 
	 * NOT POSSIBLE! 
	 * S.7) L--L--L
	 * 		|
	 * 		T
	 * 		|
	 * 		L
	 * 
	 * NOT POSSIBLE! 
	 * S.8) L--L--L
	 * 		|
	 * 		L
	 * 		|
	 * 		T
	 * --------------------------------------------------------
	 * S.9) T--L--L
	 * 			  |
	 * 			  L
	 * 			  |
	 * 			  L
	 * 
	 * S.10) L--T--L
	 * 			   |
	 * 			   L
	 * 			   |
	 * 			   L
	 * 
	 * S.11) L--L--T
	 * 			   |
	 * 			   L
	 * 			   |
	 * 			   L
	 * 
	 * S.12) L--L--L
	 * 			   |
	 * 			   T
	 * 			   |
	 * 			   L
	 * 
	 * S.13) L--L--L
	 * 			   |
	 * 			   L
	 * 			   |
	 * 			   T
	 * --------------------------------------------------------
	 * 
	 * S.14)		T
	 * 				|
	 * 				L
	 * 				|
	 * 		  L--L--L
	 * 
	 * S.15)		L
	 * 				|
	 * 				T
	 * 				|
	 * 		  L--L--L
	 * 
	 * S.16)		L
	 * 				|
	 * 				L
	 * 				|
	 * 		  L--L--T
	 * 
	 * S.17)		L
	 * 				|
	 * 				L
	 * 				|
	 * 		  L--T--L
	 * 
	 * S.18)		L
	 * 				|
	 * 				L
	 * 				|
	 * 		  T--L--L
	 * --------------------------------------------------------
	 * S.19) T
	 * 		 |
	 * 		 L
	 * 		 |
	 * 		 L--L--L
	 * 
	 * S.20) L
	 * 		 |
	 * 		 T
	 * 		 |
	 * 		 L--L--L
	 * 
	 * S.21) L
	 * 		 |
	 * 		 L
	 * 		 |
	 * 		 T--L--L
	 * 
	 * S.22) L
	 * 		 |
	 * 		 L
	 * 		 |
	 * 		 L--T--L
	 * 
	 * S.23) L
	 * 		 |
	 * 		 L
	 * 		 |
	 * 		 L--L--T
	 */
	
	/**
	 * @requires:
	 * R.1) "this" is an instance of Lokum.
	 * R.2) "this" is NOT an instance of BombLokum.
	 * 
	 * For a given lokum, this method checks the LogicField above this lokum. Then for current lokum,
	 * two options are possible:
	 * 
	 * O.1) There does not exists any lokum above the current lokum. In this case, method immediately
	 * returns false.
	 * O.2) If #1 is not the case, then above field exists. Then it may not be a lokum. If that is the
	 * case, false is returned.
	 * O.3) If #2 is not the case, then above field is a lokum. If the above field is a lokum, then it
	 * must not be an instance of the BombLokum. If that is not the case, false is returned.
	 * O.4) If #3 is not the case, then, the above field is a lokum and is not a BombLokum, then it's
	 * color must be the same with the color of the current lokum. If that's not the case, false is
	 * returned.
	 * 
	 * Otherwise, true is returned.
	 * 
	 * NOTES:
	 * N.1) Parameter: "String colorOfTheCombo" may be unnecessary.
	 * 
	 * @return
	 */
	public boolean isAboveLogicFieldInCombo(String colorOfTheCombo){
		int thisLokumsRowIndex = getRowIndex();
		int aboveLokumsRowIndex = thisLokumsRowIndex + 1;
		int columnIndex = getColumnIndex();
		LogicField[][] logicFields = BoardLogic.getInstance().getLogicFields();
		/*
		 * Checks O.1. If true, returns false. 
		 */
		if(aboveLokumsRowIndex >= BoardLogic.getInstance().getRowSize())
			return false;
		/*
		 * If here, then O.1 is not the case. So check O.2.
		 */
		if(!(logicFields[aboveLokumsRowIndex][columnIndex] instanceof Lokum))
			return false;
		/*
		 * If here, then O.2 is not the case. So check O.3.
		 */
		if(logicFields[aboveLokumsRowIndex][columnIndex] instanceof BombLokum)
			return false;
		/*
		 * If here, then O.3 is not the case. So check O.4.
		 */
		if(
				!((Lokum)logicFields[aboveLokumsRowIndex][columnIndex]).getLokumColor().equals(colorOfTheCombo))
			return false;
		/*
		 * Else, return true.
		 */
		return true;
	}
	
	public boolean isBelowLogicFieldInCombo(String colorOfTheCombo){
		int thisLokumsRowIndex = getRowIndex();
		int belowLokumsRowIndex = thisLokumsRowIndex - 1;
		int columnIndex = getColumnIndex();
		LogicField[][] logicFields = BoardLogic.getInstance().getLogicFields();
		/*
		 * Checks O.1. If true, returns false. 
		 */
		if(belowLokumsRowIndex < 0)
			return false;
		/*
		 * If here, then O.1 is not the case. So check O.2.
		 */
		if(!(logicFields[belowLokumsRowIndex][columnIndex] instanceof Lokum))
			return false;
		/*
		 * If here, then O.2 is not the case. So check O.3.
		 */
		if(logicFields[belowLokumsRowIndex][columnIndex] instanceof BombLokum)
			return false;
		/*
		 * If here, then O.3 is not the case. So check O.4.
		 */
		if(
				!((Lokum)logicFields[belowLokumsRowIndex][columnIndex]).getLokumColor().equals(colorOfTheCombo)) 
			return false;
		/*
		 * Else, return true.
		 */
		return true;
	}
	
	public boolean isLeftLogicFieldInCombo(String colorOfTheCombo){
		int thisLokumsColumnIndex = getColumnIndex();
		int leftLokumsColumnIndex = thisLokumsColumnIndex - 1;
		int rowIndex = getRowIndex();
		LogicField[][] logicFields = BoardLogic.getInstance().getLogicFields();
		/*
		 * Checks O.1. If true, returns false. 
		 */
		if(leftLokumsColumnIndex < 0)
			return false;
		/*
		 * If here, then O.1 is not the case. So check O.2.
		 */
		if(!(logicFields[rowIndex][leftLokumsColumnIndex] instanceof Lokum))
			return false;
		/*
		 * If here, then O.2 is not the case. So check O.3.
		 */
		if(logicFields[rowIndex][leftLokumsColumnIndex] instanceof BombLokum)
			return false;
		/*
		 * If here, then O.3 is not the case. So check O.4.
		 */
		if(
				!((Lokum)logicFields[rowIndex][leftLokumsColumnIndex]).getLokumColor().equals(colorOfTheCombo)) 
			return false;
		/*
		 * Else, return true.
		 */
		return true;
	}
	
	public boolean isRightLogicFieldInCombo(String colorOfTheCombo){
		int thisLokumsColumnIndex = getColumnIndex();
		int rightLokumsColumnIndex = thisLokumsColumnIndex + 1;
		int rowIndex = getRowIndex();
		LogicField[][] logicFields = BoardLogic.getInstance().getLogicFields();
		/*
		 * Checks O.1. If true, returns false. 
		 */
		if(rightLokumsColumnIndex >= BoardLogic.getInstance().getColumnSize())
			return false;
		/*
		 * If here, then O.1 is not the case. So check O.2.
		 */
		if(!(logicFields[rowIndex][rightLokumsColumnIndex] instanceof Lokum))
			return false;
		/*
		 * If here, then O.2 is not the case. So check O.3.
		 */
		if(logicFields[rowIndex][rightLokumsColumnIndex] instanceof BombLokum)
			return false;
		/*
		 * If here, then O.3 is not the case. So check O.4.
		 */
		if(
				!((Lokum)logicFields[rowIndex][rightLokumsColumnIndex]).getLokumColor().equals(colorOfTheCombo)) 
			return false;
		/*
		 * Else, return true.
		 */
		return true;
	}
	
	/**
	 * @requires:
	 * R.1) "this" is not an instance of BombLokum.
	 * 
	 * This method checks the logic field above and returns that if that logic field is in the combo. If not, it returns null.
	 * @return
	 */
	public LogicField getAboveLogicField(){
		if(isAboveLogicFieldInCombo(getLokumColor()))
			return BoardLogic.getInstance().getLogicFieldAt(getRowIndex()+1, getColumnIndex());
		return null;
	}
	public LogicField getBelowLogicField(){
		if(isBelowLogicFieldInCombo(getLokumColor()))
			return BoardLogic.getInstance().getLogicFieldAt(getRowIndex()-1, getColumnIndex());
		return null;
	}
	public LogicField getLeftLogicField(){
		if(isLeftLogicFieldInCombo(getLokumColor()))
			return BoardLogic.getInstance().getLogicFieldAt(getRowIndex(), getColumnIndex()-1);
		return null;
	}
	public LogicField getRightLogicField(){
		if(isRightLogicFieldInCombo(getLokumColor()))
			return BoardLogic.getInstance().getLogicFieldAt(getRowIndex(), getColumnIndex()+1);
		return null;
	}
	
	void clearCombosThisLokumInField(){
		
	}

	@Override
	public boolean equals(Object obj){
		if( !super.equals(obj) )
			return false;
		if( !(obj instanceof Lokum) )
			return false;
		Lokum argLokum = (Lokum) obj;
		if( !this.lokumColor.equals(argLokum.lokumColor) )
			return false;
		return true;
	}

	/*
	 * The method below is maybe unnecessary.
	 * (non-Javadoc)
	 * @see App.LogicField#toString()
	 */
	@Override
	public String toString(){
		return super.toString();
	}

	/**
	 * 
	 * @requires
	 * 
	 * <nothing>
	 * 
	 * @ensures
	 * 
	 * E.1) (implicit? because we actually need to call repOK() of this field.) color != null.
	 * E.2) (implicit? because we actually need to call repOK() of this field.) location != null.
	 * E.3) color's value is indeed a valid color.
	 * E.4) location is valid
	 * 
	 * @modifies
	 * 
	 * <nothing>
	 * @return
	 */
	public boolean repOK(){
		if(!super.repOK())
			return false;
		/*
		 * Following if statement may be changed if we change the way the color of a Lokum is represented.
		 */
		if(!isColorValid(lokumColor))
			return false;
		return true;
	}
	
	private boolean isColorValid(String lokumColor){
		if(lokumColor.equals(Constants.LOKUM_COLOR_BROWN)||lokumColor.equals(Constants.LOKUM_COLOR_GREEN)||lokumColor.equals(Constants.LOKUM_COLOR_RED)||lokumColor.equals(Constants.LOKUM_COLOR_WHITE)||lokumColor.equals(Constants.BOMB_LOKUM_COLOR))
			return true;
		else
			return false;
	}
	
}