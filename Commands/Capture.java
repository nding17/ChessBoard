
public class Capture implements Command {

	private char fromCol;
	private int fromRow;
	private char toCol;
	private int toRow;
	private ChessPiece capturing;
	private ChessPiece captured;
	private Board board;

	public Capture(Board board, char fromCol, int fromRow, char toCol, int toRow){
		this.fromCol = fromCol;
		this.fromRow = fromRow;
		this.toCol = toCol;
		this.toRow = toRow;
		this.capturing = board.pieceAt(fromCol, fromRow);
		this.captured = board.pieceAt(toCol,toRow);
		this.board = board;
		board.addCaptured(1);
	}
	
	public int getFromRow(){
		return fromRow;
	}
	
	public int getToRow(){
		return toRow;
	}
	
	public char getToCol(){
		return toCol;
	}
	
	@Override
	public void execute() {
		board.setPiece(capturing, toCol, toRow);
		board.setPiece(null, fromCol, fromRow);
		relocateCaptured();

	}

	@Override
	public void undo() {
		board.setPiece(capturing, fromCol, fromRow);
		board.setPiece(captured, toCol, toRow);
		double[] fromPosition = ChessGUI.algebraicToGUI(fromCol, fromRow);
		double[] toPosition = ChessGUI.algebraicToGUI(toCol, toRow);
		capturing.relocate(fromPosition[0], fromPosition[1]);
		captured.relocate(toPosition[0], toPosition[1]);
		board.addCaptured(-1);
	}
	
	private void relocateCaptured(){
		int numCaptured = board.getNumberCaptured() - 1;
		int row = numCaptured % 8;
		int col = 9 + numCaptured/8;
		double[] position = ChessGUI.getClosestSquareCoord(col, row);
		captured.relocate(position[0], position[1]);
	}
    
}
