
public class ChessMove implements Command {
	
	private ChessPiece piece;
	
	private char fromCol;
	private int fromRow;
	private char toCol;
	private int toRow;
	private Board board;
	
	public ChessMove(Board board, char fromCol, int fromRow, char toCol, int toRow){
		this.board = board;
		this.piece = board.pieceAt(fromCol, fromRow);
		this.fromCol = fromCol;
		this.fromRow = fromRow;
		this.toCol = toCol;
		this.toRow = toRow;
	}
	
	@Override
	public void execute() {
		board.setPiece(piece, toCol, toRow);
		board.setPiece(null, fromCol, fromRow);
	}

	@Override
	public void undo() {
		board.setPiece(piece, fromCol, fromRow);
		board.setPiece(null, toCol, toRow);
		double[] position = ChessGUI.algebraicToGUI(fromCol, fromRow);
		piece.relocate(position[0], position[1]);
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
