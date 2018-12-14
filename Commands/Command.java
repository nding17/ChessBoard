
public interface Command {
	public void execute();
	public void undo();
	
	//these three methods are especially 
	//created for En Passant moving strategy
	public int getFromRow();
	public int getToRow();
	public char getToCol();
}
