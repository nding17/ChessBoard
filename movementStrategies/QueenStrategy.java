
public class QueenStrategy implements MoveStrategy {

	private Board board;

	public QueenStrategy( Board board ){
		this.board = board;
	}

	public boolean checkCollisionOnSpot(Board b, char toCol, int toRow){
		return board.pieceAt(toCol, toRow)!=null;
	}

	@Override
	public boolean isPatternLegal(char fromColumn, int fromRow, char toColumn, int toRow, Board b){
		int deltaColumn = Math.abs(toColumn-fromColumn);
		int deltaRow = Math.abs(fromRow-toRow);
		return deltaRow == deltaColumn || deltaRow == 0 || deltaColumn == 0;
	}

	@Override
	public boolean checkCollisionOnWay(Board b, char fromCol, int fromRow, 
			char toCol, int toRow){
		int deltaRow = toRow-fromRow;
		int deltaCol = toCol-fromCol;
		boolean diagonal = (deltaRow != 0 && deltaCol != 0);
		if(diagonal){
			return diagonalCollisionOnWay(b, fromCol, fromRow, toCol, toRow);
		}
		else{
			return horizontalOrVerticalCollisionOnWay(b, fromCol, fromRow, toCol, toRow);
		}
	}
	
	public boolean canCapture( Board b, char fromCol, int fromRow, 
			char toCol, int toRow ){
		
		ChessPiece piece = b.pieceAt(fromCol, fromRow);
		boolean isPieceQueen = piece.getType() == ChessPieceType.QUEEN;
		
		if(checkCollisionOnSpot(b,toCol,toRow) && isPieceQueen){
			ChessPiece fromPiece = b.pieceAt(fromCol, fromRow);
			ChessPiece toPiece = b.pieceAt(toCol, toRow);
			return fromPiece.getColor() != toPiece.getColor();
		}
		//empty space, nothing to capture
		return false;
	}

	public boolean horizontalOrVerticalCollisionOnWay(Board b, char fromCol, 
			int fromRow, char toCol, int toRow){
		RookStrategy rookStrat = new RookStrategy(b);
		return rookStrat.checkCollisionOnWay(b, fromCol, fromRow, toCol, toRow);
	}
	
	public boolean diagonalCollisionOnWay(Board b, char fromCol, int fromRow, 
			char toCol, int toRow){
		BishopStrategy bishopStrat = new BishopStrategy(b);
		return bishopStrat.checkCollisionOnWay(b, fromCol, fromRow, toCol, toRow);
	}   
	
	/*
	 * compositional approach for queen to borrow 
	 * rook and bishop's check methods
	 * */
	public boolean enemyKingCheck( Board board, char fromCol, 
			int fromRow ){
		MoveStrategy bishopStrat = new BishopStrategy(board);   
		MoveStrategy rookStrat = new RookStrategy(board);
		return bishopStrat.enemyKingCheck(board, fromCol, fromRow) ||
				rookStrat.enemyKingCheck(board, fromCol, fromRow); 
	}
	
	@Override
	public boolean isSpecialCase(Board board, char fromCol, 
			int fromRow, char toCol, int toRow) {
		return false;
	}
	
}
