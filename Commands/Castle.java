

public class Castle implements Command {
	
	
	private char fromCol;
	private ChessPiece king;
	private ChessPiece rook;
	private int fromRow;
	private char toCol;
	private int toRow;
	private Board board;
	
	public Castle(Board board, char fromCol, int fromRow, char toCol, int toRow){
		this.board = board;
		this.fromCol = fromCol;
		this.king=board.pieceAt(fromCol, fromRow);
		if(toCol=='g'){
			this.rook=board.pieceAt('h',fromRow);
		}
		else{
			this.rook=board.pieceAt('a', fromRow);
		}
		this.fromRow = fromRow;
		this.toCol = toCol;
		this.toRow = toRow;
	}
	
	public void execute(){
		if(toCol=='g'){
			board.setPiece(king, 'g', toRow);
			board.setPiece(null, fromCol, fromRow);
			board.setPiece(rook, 'f', fromRow);
			board.setPiece(null, 'h', fromRow);
			double[] rookPosition = ChessGUI.algebraicToGUI('f', fromRow);
			rook.relocate(rookPosition[0], rookPosition[1]);
		}
		else{
			board.setPiece(king, 'c', toRow);
			board.setPiece(null, fromCol, fromRow);
			board.setPiece(rook, 'd', fromRow);
			board.setPiece(null, 'a', fromRow);
			double[] rookPosition = ChessGUI.algebraicToGUI('d', fromRow);
			rook.relocate(rookPosition[0],rookPosition[1]);
		}
		
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
	
	public void undo(){
		if(toCol=='g'){
		board.setPiece(king, fromCol, fromRow);
		board.setPiece(null, toCol, toRow);
		board.setPiece(rook, 'h', toRow);
		board.setPiece(null, 'f', fromRow);
		double[] kingPosition = ChessGUI.algebraicToGUI(fromCol, fromRow);
		double[] rookPosition = ChessGUI.algebraicToGUI('h', toRow);
		king.relocate(kingPosition[0], kingPosition[1]);
		rook.relocate(rookPosition[0], rookPosition[1]);
		}
		else{
			board.setPiece(king, fromCol, fromRow);
			board.setPiece(null, toCol, toRow);
			board.setPiece(rook, 'a', toRow);
			board.setPiece(null, 'd', fromRow);
			double[] kingPosition = ChessGUI.algebraicToGUI(fromCol, fromRow);
			double[] rookPosition = ChessGUI.algebraicToGUI('a', toRow);
			king.relocate(kingPosition[0], kingPosition[1]);
			rook.relocate(rookPosition[0], rookPosition[1]);
		}
	}

}
