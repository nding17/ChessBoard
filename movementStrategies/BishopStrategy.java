
public class BishopStrategy implements MoveStrategy {
	
	private static final int ROW_UPPER_BOUND = 8;
	private static final int ROW_LOWER_BOUND = 1;
	private static final char COL_UPPER_BOUND = 'h';
	private static final char COL_LOWER_BOUND = 'a';
	
	private Board board;
	
	public BishopStrategy( Board board ){
		this.board = board;
	}
	
	public boolean checkCollisionOnSpot(Board b, char toCol, int toRow){
		return board.pieceAt(toCol, toRow)!=null;
	}
	
	
	private boolean isPiecesInDirNE(Board b, char fromCol, int fromRow, 
			char toCol, int toRow){
		
		fromCol++;
		fromRow++;
		
		char currCol = fromCol;
		int currRow = fromRow;
		
		boolean isOnWay = false;
		
		for(; currRow < toRow; ++currRow){
			if (currRow<=ROW_UPPER_BOUND && currCol <= COL_UPPER_BOUND)
			isOnWay = checkCollisionOnSpot(b, currCol, currRow);
			if (isOnWay) return true;
			currCol++;
		}
		return false;   
	}
	
	private boolean isPiecesInDirNW(Board b, char fromCol, int fromRow, 
			char toCol, int toRow){
		
		fromCol--;
		fromRow++;
		
		char currCol = fromCol;
		int currRow = fromRow;
		
		boolean isOnWay = false;
		
		for(; currRow < toRow; ++currRow){
			if(currRow<=ROW_UPPER_BOUND && currCol>=COL_LOWER_BOUND)
			isOnWay = checkCollisionOnSpot(b, currCol, currRow);
			if (isOnWay) return true;
			currCol--;
		}
		return false;
	}
	
	private boolean isPiecesInDirSE(Board b, char fromCol, int fromRow, 
			char toCol, int toRow){
		
		fromCol++;
		fromRow--;
		
		char currCol = fromCol;
		int currRow = fromRow;
		
		boolean isOnWay = false;
		
		for(; currRow > toRow; --currRow){
			if (currRow>= ROW_LOWER_BOUND && currCol<=COL_UPPER_BOUND)
			isOnWay = checkCollisionOnSpot(b, currCol, currRow);
			if (isOnWay) return true;
			currCol++;
		}
		return false;
	}
	
	private boolean isPiecesInDirSW(Board b, char fromCol, int fromRow, 
			char toCol, int toRow){
		
		fromCol--;
		fromRow--;
		
		char currCol = fromCol;
		int currRow = fromRow;
		
		boolean isOnWay = false;
		
		for(; currRow > toRow; --currRow){
			if (currRow>=ROW_LOWER_BOUND && currCol>=COL_LOWER_BOUND)
			isOnWay = checkCollisionOnSpot(b, currCol, currRow);
			if (isOnWay) return true;
			currCol--;
		}
		return false;
	}
	
	private boolean isKingInDirNE( Board b, char fromCol, 
			int fromRow ){
		
		int currRow = fromRow+1;
		char currCol = (char)((int)fromCol+1);
		
		boolean isKing = false;
		
		for(; currRow <= ROW_UPPER_BOUND && 
				currCol <= COL_UPPER_BOUND && !isKing; ++currRow){
			if(checkCollisionOnSpot(b,currCol, currRow)){
				
				ChessPiece fromPiece = b.pieceAt(fromCol, fromRow);
				ChessPiece currPiece = b.pieceAt(currCol, currRow);
				boolean isEnemy = fromPiece.getColor() != currPiece.getColor();
				
				/*three criteria: 
				 * 1. should be a king
				 * 2. should be a bishop that's doing the check
				 * 3. should be in different color
				 * */
				isKing = b.pieceAt(currCol, currRow).getType() 
						== ChessPieceType.KING && isEnemy; 
				if (currPiece!=null && !isKing) return false;
			}
			++currCol;
		}
		return isKing;
	}
	
	private boolean isKingInDirNW( Board b, char fromCol, 
			int fromRow ){
		
		int currRow = fromRow+1;
		char currCol = (char)((int)fromCol-1);
		
		boolean isKing = false;
		
		for(; currRow <= ROW_UPPER_BOUND &&
				currCol >= COL_LOWER_BOUND && !isKing; ++currRow){
			if(checkCollisionOnSpot(b,currCol, currRow)){
				
				ChessPiece fromPiece = b.pieceAt(fromCol, fromRow);
				ChessPiece currPiece = b.pieceAt(currCol, currRow);
				boolean isEnemy = fromPiece.getColor() != currPiece.getColor();
				
				isKing = b.pieceAt(currCol, currRow).getType() 
						== ChessPieceType.KING && isEnemy; 
				if (currPiece!=null && !isKing) return false;
			}
			--currCol;
		}
		return isKing;
	}
	
	
	private boolean isKingInDirSE( Board b, char fromCol, 
			int fromRow ){
		
		int currRow = fromRow-1;
		char currCol = (char)((int)fromCol+1);
		
		boolean isKing = false;
		
		for(; currRow >= ROW_LOWER_BOUND &&
				currCol <= COL_UPPER_BOUND && !isKing; --currRow){
			if(checkCollisionOnSpot(b,currCol, currRow)){
				
				ChessPiece fromPiece = b.pieceAt(fromCol, fromRow);
				ChessPiece currPiece = b.pieceAt(currCol, currRow);
				boolean isEnemy = fromPiece.getColor() != currPiece.getColor();
				
				isKing = b.pieceAt(currCol, currRow).getType() 
						== ChessPieceType.KING && isEnemy; 
				if (currPiece!=null && !isKing) return false;
			}
			++currCol;
		}
		return isKing;
	}
	
	private boolean isKingInDirSW( Board b, char fromCol, 
			int fromRow ){
		
		int currRow = fromRow-1;
		char currCol = (char)((int)fromCol-1);
		
		boolean isKing = false;
		
		for(; currRow >= ROW_LOWER_BOUND &&
				currCol >= COL_LOWER_BOUND && !isKing; --currRow){
			if(checkCollisionOnSpot(b,currCol, currRow)){
				
				ChessPiece fromPiece = b.pieceAt(fromCol, fromRow);
				ChessPiece currPiece = b.pieceAt(currCol, currRow);
				boolean isEnemy = fromPiece.getColor() != currPiece.getColor();
				
				isKing = b.pieceAt(currCol, currRow).getType() 
						== ChessPieceType.KING && isEnemy; 
				if (currPiece!=null && !isKing) return false;
			}
			--currCol;
		}
		return isKing;
	}
	
	@Override
	public boolean checkCollisionOnWay(Board b, char fromCol, int fromRow, 
			char toCol, int toRow){
		
		boolean isDirNE = toCol > fromCol && toRow > fromRow;
		boolean isDirNW = toCol < fromCol && toRow > fromRow;
		boolean isDirSE = toCol > fromCol && toRow < fromRow;
		boolean isDirSW = toCol < fromCol && toRow < fromRow;
		
		if(isDirNE){
			return isPiecesInDirNE(b, fromCol, fromRow, toCol, toRow);
		}else if(isDirNW){
			return isPiecesInDirNW(b, fromCol, fromRow, toCol, toRow);
		}else if(isDirSE){
			return isPiecesInDirSE(b, fromCol, fromRow, toCol, toRow);
		}else if(isDirSW){
			return isPiecesInDirSW(b, fromCol, fromRow, toCol, toRow);
		}
		return false;
	}
	
	private boolean isMoveDiagonal( char fromCol, int fromRow,
			char toCol, int toRow ){
		
		int horizontalIncrement = Math.abs(fromCol - toCol);
		int verticalIncrement = Math.abs(fromRow - toRow);
		
		return horizontalIncrement == verticalIncrement;
		
	}
	
	public boolean isPatternLegal( char fromCol, int fromRow,
			char toCol, int toRow, Board board ){
		return isMoveDiagonal( fromCol, fromRow, toCol, toRow );
	}
	
	public boolean canCapture( Board b, char fromCol, int fromRow, 
			char toCol, int toRow ){
		
		ChessPiece piece = b.pieceAt(fromCol, fromRow);
		boolean isPieceBishop = piece.getType() == ChessPieceType.BISHOP;
		
		if(checkCollisionOnSpot(b,toCol,toRow) && isPieceBishop){
			ChessPiece fromPiece = b.pieceAt(fromCol, fromRow);
			ChessPiece toPiece = b.pieceAt(toCol, toRow);
			return fromPiece.getColor() != toPiece.getColor();
		}
		//empty space, nothing to capture
		return false;
	}
	
	public boolean enemyKingCheck( Board board, char fromCol, 
			int fromRow ){
		return isKingInDirNE( board, fromCol, fromRow ) || 
				isKingInDirNW( board, fromCol, fromRow ) ||
				isKingInDirSE( board, fromCol, fromRow ) || 
				isKingInDirSW( board, fromCol, fromRow );
	}
	
	@Override
	public boolean isSpecialCase(Board board, char fromCol, 
			int fromRow, char toCol, int toRow) {
		return false;
	}
	
}
