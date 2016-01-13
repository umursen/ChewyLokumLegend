package App;

import java.util.LinkedList;

public class EventDispatchQueue {
	
	private LinkedList<GameEvent> eventQueue;
	private static EventDispatchQueue instance = new EventDispatchQueue();
	
	private EventDispatchQueue(){
		eventQueue = new LinkedList<GameEvent>();
	}
	
	public synchronized void addEvent(GameEvent newEvent){
		//System.out.println("New event!");
		eventQueue.add(newEvent);
	}
	
	public synchronized GameEvent getEvent(){
		//System.out.println(BoardLogicTest.toString(BoardLogic.getInstance()));
		return eventQueue.removeFirst();
	}
	
	public boolean isEmpty(){
		return eventQueue.isEmpty();
	}
	
	public static EventDispatchQueue getInstance(){
		if(instance==null){
			instance = new EventDispatchQueue();
		}
		return instance;
	}
	
	public static void resetInstance(){
		instance = null;
	}
	public boolean repOK(){
		return eventQueue != null;
	}
}
