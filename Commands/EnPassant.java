
public class EnPassant implements Command {
	
	private char fromCol;
	private int fromRow;
	private char toCol;
	private int toRow;
	private ChessPiece capturing;
	private ChessPiece captured;
	private Board board;

	public EnPassant(Board board, char fromCol, int fromRow, char toCol, int toRow){
		this.fromCol = fromCol;
		this.fromRow = fromRow;
		this.toCol = toCol;
		this.toRow = toRow;
		
		this.capturing = board.pieceAt(fromCol, fromRow);
		this.captured = board.pieceAt(toCol, fromRow);
		
		this.board = board;
		board.addCaptured(1);
	}
	
	public void execute(){
		board.setPiece(null, toCol, fromRow);
		
		board.setPiece(null, fromCol, fromRow);
		board.setPiece(capturing, toCol, toRow);
		relocateCaptured();
	}
	
	public void undo(){
		board.setPiece(capturing, fromCol, fromRow);
		board.setPiece(captured, toCol, fromRow);
		board.setPiece(null, toCol, toRow);
		
		double[] capturedPosition = ChessGUI.algebraicToGUI(toCol, fromRow);
		double[] capturingPosition = ChessGUI.algebraicToGUI(fromCol, fromRow);
		
		capturing.relocate(capturingPosition[0], capturingPosition[1]);
		captured.relocate(capturedPosition[0], capturedPosition[1]);
		board.addCaptured(-1);
	}
	
	private void relocateCaptured(){
		int numCaptured = board.getNumberCaptured() - 1;
		int row = numCaptured % 8;
		int col = 9 + numCaptured/8;
		double[] position = ChessGUI.getClosestSquareCoord(col, row);
		captured.relocate(position[0], position[1]);
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
}
