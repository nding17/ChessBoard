import java.util.ArrayList;

public class Turns implements ChessSubject {

	private ChessColor turnColor = ChessColor.WHITE;
	private ArrayList<ChessObserver> observers = new ArrayList<ChessObserver>();
	public void addObserver(ChessObserver observer) {
		observers.add(observer);

	}

	public void removeObserver(ChessObserver observer) {
		observers.remove(observer);
	}

	public void notifyObservers() {
		for(ChessObserver observer: observers){
			observer.update(turnColor);
		}

	}
	
	public ChessColor currentTurn(){
		return turnColor;
	}
	
	public void nextTurn(){
		if(turnColor == ChessColor.BLACK){
		turnColor =ChessColor.WHITE;
		}
		else
			turnColor = ChessColor.BLACK;
		notifyObservers();
	}
	

}
