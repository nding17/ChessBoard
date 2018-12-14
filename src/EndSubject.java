
public interface EndSubject {
	public void addObserver(EndObserver observer);
	public void removeObserver(EndObserver observer);
	public void notifyObservers();
}
