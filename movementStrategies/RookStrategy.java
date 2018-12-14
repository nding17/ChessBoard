
public class RookStrategy implements MoveStrategy {

	private static final int ROW_UPPER_BOUND = 8;
	private static final int ROW_LOWER_BOUND = 1;
	private static final char COL_UPPER_BOUND = 'h';
	private static final char COL_LOWER_BOUND = 'a';
	
	private Board board;

	public RookStrategy( Board board ){
		this.board = board;
	}

	public boolean checkCollisionOnSpot(Board b, char toCol, int toRow){
		return board.pieceAt(toCol, toRow)!=null;
	}

	@Override
	public boolean checkCollisionOnWay(Board b, char fromCol, int fromRow, 
			char toCol, int toRow){
		if(toCol>fromCol){
			int start=(int)fromCol+1;
			for(char i=(char)start; i<toCol; i++){
				if(board.pieceAt(i, fromRow)!=null){
					return true;
				} 
			}
		} 
		else if(toCol<fromCol){
			int start=(int)fromCol-1;
			for(char i=(char)start; i>toCol; i--){
				if(board.pieceAt(i, fromRow)!=null){
					return true;
				}
			}
		}
		else if(toRow>fromRow){
			int start=fromRow+1;
			for(int i=start; i<toRow; i++){
				if(board.pieceAt(fromCol, i)!=null){
					return true;
				}
			}
		}
		else if(fromRow>toRow){
			int start=fromRow-1;
			for(int i=start; i>toRow; i--){
				if(board.pieceAt(fromCol, i)!=null){
					return true;
				}
			}
		}
		return false;
	}

	private boolean isMoveHorizontal( char fromCol, char toCol ){
		return fromCol == toCol;
	}

	private boolean isMoveVertical( int fromRow, int toRow ) {
		return fromRow == toRow;
	}

	public boolean isPatternLegal( char fromCol, 
			int fromRow, char toCol, int toRow, Board b ){ 

		return isMoveHorizontal( fromCol, toCol ) ||
				isMoveVertical( fromRow, toRow );
	}
	
	public boolean canCapture( Board b, char fromCol, int fromRow, 
			char toCol, int toRow ){
		
		ChessPiece piece = b.pieceAt(fromCol, fromRow);
		boolean isPieceRook = piece.getType() == ChessPieceType.ROOK;
		
		if(checkCollisionOnSpot(b,toCol,toRow) && isPieceRook){
			ChessPiece fromPiece = b.pieceAt(fromCol, fromRow);
			ChessPiece toPiece = b.pieceAt(toCol, toRow);
			return fromPiece.getColor() != toPiece.getColor();
		}
		//empty space, nothing to capture
		return false;
	}
	
	public boolean enemyKingCheck( Board board, char fromCol, 
			int fromRow ){
		return isKingInDirN(board, fromCol, fromRow) || 
				isKingInDirS(board, fromCol, fromRow) ||
				isKingInDirW(board, fromCol, fromRow) ||
				isKingInDirE(board, fromCol, fromRow);
	}
	
	private boolean isKingInDirN( Board board, char fromCol, 
			int fromRow ){
		
		char currCol = fromCol;
		int currRow = fromRow+1;
		
		boolean isKing = false;
		
		for(; currRow <= ROW_UPPER_BOUND && !isKing; ++currRow){
			if(checkCollisionOnSpot(board, currCol, currRow)){
				
				ChessPiece fromPiece = board.pieceAt(fromCol, fromRow);
				ChessPiece currPiece = board.pieceAt(currCol, currRow);
				
				boolean isEnemy = fromPiece.getColor() != currPiece.getColor();
				
				isKing = currPiece.getType() == ChessPieceType.KING && 
						isEnemy;
				if (currPiece!=null && !isKing) return false;
			}
		}
		return isKing;
	}
	
	private boolean isKingInDirS( Board board, char fromCol, 
			int fromRow ){
		char currCol = fromCol;
		int currRow = fromRow-1;
		
		boolean isKing = false;
		
		for(; currRow >= ROW_LOWER_BOUND && !isKing; --currRow){
			if(checkCollisionOnSpot(board, currCol, currRow)){
				
				ChessPiece fromPiece = board.pieceAt(fromCol, fromRow);
				ChessPiece currPiece = board.pieceAt(currCol, currRow);
				
				boolean isEnemy = fromPiece.getColor() != currPiece.getColor();
				
				isKing = currPiece.getType() == ChessPieceType.KING && 
						isEnemy;
				if (currPiece!=null && !isKing) return false;
			}
		}
		return isKing;
	}
	
	private boolean isKingInDirW( Board board, char fromCol, 
			int fromRow ){
		char currCol = (char)((int)fromCol-1);
		int currRow = fromRow;
		
		boolean isKing = false;
		
		for(; currCol >= COL_LOWER_BOUND && !isKing; --currCol){
			if(checkCollisionOnSpot(board, currCol, currRow)){
				
				ChessPiece fromPiece = board.pieceAt(fromCol, fromRow);
				ChessPiece currPiece = board.pieceAt(currCol, currRow);
				
				boolean isEnemy = fromPiece.getColor() != currPiece.getColor();
				
				isKing = currPiece.getType() == ChessPieceType.KING && 
						isEnemy;
				if (currPiece!=null && !isKing) return false;
			}
		}
		return isKing;
	}
	
	private boolean isKingInDirE( Board board, char fromCol, 
			int fromRow ){
		char currCol = (char)((int)fromCol+1);
		int currRow = fromRow;
		
		boolean isKing = false;
		
		for(; currCol <= COL_UPPER_BOUND && !isKing; ++currCol){
			if(checkCollisionOnSpot(board, currCol, currRow)){
				
				ChessPiece fromPiece = board.pieceAt(fromCol, fromRow);
				ChessPiece currPiece = board.pieceAt(currCol, currRow);
				
				boolean isEnemy = fromPiece.getColor() != currPiece.getColor();
				
				isKing = currPiece.getType() == ChessPieceType.KING && 
						isEnemy;
				if (currPiece!=null && !isKing) return false;
			}
		}
		return isKing;
	}
	@Override
	public boolean isSpecialCase(Board board, char fromCol, 
			int fromRow, char toCol, int toRow) {
		return false;
	}
}
