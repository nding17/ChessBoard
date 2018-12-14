
public interface ChessSubject {
	public void addObserver(ChessObserver observer);
	public void removeObserver(ChessObserver observer);
	public void notifyObservers();
}
