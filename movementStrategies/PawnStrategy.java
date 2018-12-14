
public class PawnStrategy implements MoveStrategy {
	
	ChessColor color;
	private Board board;
	private static final int ROW_LOWER_BOUND = 1;
	private static final int ROW_UPPER_BOUND = 8;
	private static final char COL_LOWER_BOUND = 'a';
	private static final char COL_UPPER_BOUND = 'h';
	
	public PawnStrategy( Board board, ChessColor color){
		this.board = board;
		this.color=color;
	}
	
	public boolean checkCollisionOnSpot(Board b, char toCol, int toRow){
		return board.pieceAt(toCol, toRow) != null;
	}
	
	@Override
	public boolean checkCollisionOnWay(Board b, char fromCol, int fromRow, 
			char toCol, int toRow){
		if(checkIfPieceIsWhite()){
			if(fromRow==2 && toRow==4){
				return board.pieceAt(fromCol, 3) != null;
			}
		}
		else{
			if(fromRow==7 && toRow==5){
				return board.pieceAt(fromCol, 6) != null;
			}
		}
		return false;
	}
	
	public boolean checkWhitePawnIsGoingForward(int startOfWhitePawn, 
			int endOfWhitePawn){
		return endOfWhitePawn > startOfWhitePawn;
	}

	public boolean checkWhitePawnIsNotGoingTooFar(
			int startOfWhitePawn, int endOfWhitePawn){
		if(startOfWhitePawn==2){
			return endOfWhitePawn-startOfWhitePawn <= 2;
		}
		else{
			return endOfWhitePawn-startOfWhitePawn == 1;
		}
	}

	public boolean checkPawnIsNotGoingHorizontally(char startingColumnOfPawn, 
			char endingColumnOfPawn){
		return startingColumnOfPawn == endingColumnOfPawn;
	}

	public boolean checkWhitePawnLegality(char fromColumn, int fromRow, 
			char toColumn, int toRow){
		
		return checkWhitePawnIsGoingForward(fromRow, toRow) && 
				checkWhitePawnIsNotGoingTooFar(fromRow, toRow) &&
				checkPawnIsNotGoingHorizontally(fromColumn, toColumn);
	}


	public boolean checkIfPieceIsWhite(){
		return this.color.equals(ChessColor.WHITE);
	}

	

	public boolean checkBlackPawnIsGoingForward(int startingRow, int endingRow){
		return endingRow < startingRow;
	}

	public boolean checkBlackPawnIsNotGoingTooFar(int startingRow, int endingRow){
		if(startingRow==7){
			return startingRow-endingRow <= 2;
		}
		else{
			return startingRow-endingRow == 1;
		}
	}

	public boolean checkBlackPawnLegality(char fromColumn, int fromRow, 
			char toColumn, int toRow){

		return checkBlackPawnIsGoingForward(fromRow, toRow) &&
				checkBlackPawnIsNotGoingTooFar(fromRow, toRow)
				&& checkPawnIsNotGoingHorizontally(fromColumn, toColumn);
	}
	
	@Override
	public boolean isPatternLegal(char fromColumn, int fromRow, 
			char toColumn, int toRow, Board b) {
		if(checkIfPieceIsWhite()){
			return checkWhitePawnLegality(fromColumn, fromRow, toColumn, toRow)
					|| canCapture( b, fromColumn, fromRow, toColumn, toRow );
		}
		else{
			return checkBlackPawnLegality(fromColumn, fromRow, toColumn, toRow)
					|| canCapture( b, fromColumn, fromRow, toColumn, toRow );
		}
	}
	
	// pawn has a different capture strategy
	public boolean canCapture( Board b, char fromCol, int fromRow, 
			char toCol, int toRow ){
		
		//a pawn can only capture pieces one step up front diagonally
		boolean isDiagonalForWhitePawn = Math.abs(fromCol-toCol) == 1 &&
					toRow-fromRow == 1;
		boolean isDiagonalForBlackPawn = Math.abs(fromCol-toCol) == 1 &&
				fromRow-toRow == 1;
		
		ChessPiece fromPiece = b.pieceAt(fromCol, fromRow);
		ChessPiece toPiece = b.pieceAt(toCol, toRow);
		
		ChessColor fromPieceColor = fromPiece.getColor();
		
		boolean isPawn = fromPiece.getType() == ChessPieceType.PAWN;
		
		if( isPawn && checkCollisionOnSpot(b, toCol, toRow) ){
			if(isDiagonalForWhitePawn && 
					fromPieceColor == ChessColor.WHITE ){
				return fromPiece.getColor() != toPiece.getColor();
			}else if(isDiagonalForBlackPawn && 
					fromPieceColor == ChessColor.BLACK ){
				return fromPiece.getColor() != toPiece.getColor();
			}
		}
		//empty space
		return false;
	}
	
	//luckily a pawn can never en passant a king
	public boolean enemyKingCheck( Board board, char fromCol, 
			int fromRow ){
		if(this.color == ChessColor.WHITE){
			
			boolean isKingOnLeft = false;
			boolean isKingOnRight = false;
			
			if (fromCol-1>=COL_LOWER_BOUND && fromRow+1<=ROW_UPPER_BOUND){
				boolean isPieceLeft = checkCollisionOnSpot(board, 
					(char)((int)fromCol-1), fromRow+1);
				ChessPiece leftPiece = board.pieceAt(
						(char)((int)fromCol-1), fromRow+1);
				isKingOnLeft = isPieceLeft && 
						leftPiece.getType() == ChessPieceType.KING &&
						leftPiece.getColor() == ChessColor.BLACK;
			}
			
			if (fromCol+1<=COL_UPPER_BOUND && fromRow+1<=ROW_UPPER_BOUND){
				boolean isPieceRight = checkCollisionOnSpot(board,
					(char)((int)fromCol+1), fromRow+1);
				ChessPiece rightPiece = board.pieceAt(
						(char)((int)fromCol+1), fromRow+1);
				isKingOnRight = isPieceRight && 
						rightPiece.getType() == ChessPieceType.KING &&
						rightPiece.getColor() == ChessColor.BLACK;
				
			}
			
			return isKingOnLeft || isKingOnRight;
		}else{
			boolean isKingOnLeft = false;
			boolean isKingOnRight = false;
			
			if (fromCol+1<=COL_UPPER_BOUND && fromRow-1>=ROW_LOWER_BOUND){
				boolean isPieceLeft = checkCollisionOnSpot(board, 
					(char)((int)fromCol+1), fromRow-1);
				ChessPiece leftPiece = board.pieceAt(
						(char)((int)fromCol+1), fromRow-1);
				isKingOnLeft = isPieceLeft && 
						leftPiece.getType() == ChessPieceType.KING &&
						leftPiece.getColor() == ChessColor.WHITE;
			}
			
			if (fromCol-1>=COL_LOWER_BOUND && fromRow-1>=ROW_LOWER_BOUND){
				boolean isPieceRight = checkCollisionOnSpot(board,
					(char)((int)fromCol-1), fromRow-1);
				ChessPiece rightPiece = board.pieceAt(
						(char)((int)fromCol-1), fromRow-1);
				isKingOnRight = isPieceRight && 
						rightPiece.getType() == ChessPieceType.KING &&
						rightPiece.getColor() == ChessColor.WHITE;
				
			}
			
			return isKingOnLeft || isKingOnRight;
		}
	}
	
	//En Passant moving pattern for a white pawn
	private boolean isEnPassantForWhite(Board board, char fromCol, 
			int fromRow, char toCol, int toRow){
		boolean isCollision = checkCollisionOnSpot( board, toCol, toRow );
		
		if(fromRow == 5 && toRow == 6 && !isCollision){
			char leftCol = (char)((int)fromCol-1);
			char rightCol = (char)((int)fromCol+1);
			
			if( leftCol >= 'a' && toCol-fromCol == -1){

				boolean isPieceLeft = 
						checkCollisionOnSpot( board, (char)((int)fromCol-1), fromRow );
				boolean isEnemy = board.pieceAt(fromCol, fromRow).getColor() !=
						board.pieceAt( (char)((int)fromCol-1), fromRow ).getColor();
				boolean isPawn = board.pieceAt( (char)((int)fromCol-1), fromRow ).getType()
									== ChessPieceType.PAWN;
				return isPieceLeft && isEnemy && isPawn;
			}else if(rightCol <= 'h' && toCol-fromCol == 1){
				
				boolean isPieceRight = 
						checkCollisionOnSpot( board, (char)((int)fromCol+1), fromRow );
				boolean isEnemy = board.pieceAt(fromCol, fromRow).getColor() !=
						board.pieceAt( (char)((int)fromCol+1), fromRow ).getColor();
				boolean isPawn = board.pieceAt( (char)((int)fromCol+1), fromRow ).getType()
									== ChessPieceType.PAWN;
				return isPieceRight && isEnemy && isPawn;
			}
		}
		
		return false;
	}
	
	//En Passant moving pattern for a black pawn
	private boolean isEnPassantForBlack(Board board, char fromCol, 
			int fromRow, char toCol, int toRow){
		
		boolean isCollision = checkCollisionOnSpot( board, toCol, toRow );
		
		if(fromRow == 4 && toRow == 3 && !isCollision){
			
			char leftCol = (char)((int)fromCol+1);
			char rightCol = (char)((int)fromCol-1);
			
			
			if( leftCol <= 'h' && toCol-fromCol == +1){
				boolean isPieceLeft = 
						checkCollisionOnSpot( board, (char)((int)fromCol+1), fromRow );
				boolean isEnemy = board.pieceAt(fromCol, fromRow).getColor() !=
						board.pieceAt( (char)((int)fromCol+1), fromRow ).getColor();
				boolean isPawn = board.pieceAt( (char)((int)fromCol+1), fromRow ).getType()
									== ChessPieceType.PAWN;
				return isPieceLeft && isEnemy && isPawn;
			}else if( rightCol >= 'a' && toCol-fromCol == -1){
				boolean isPieceRight = 
						checkCollisionOnSpot( board, (char)((int)fromCol-1), fromRow );
				boolean isEnemy = board.pieceAt(fromCol, fromRow).getColor() !=
						board.pieceAt( (char)((int)fromCol-1), fromRow ).getColor();
				boolean isPawn = board.pieceAt( (char)((int)fromCol-1), fromRow ).getType()
									== ChessPieceType.PAWN;
				return isPieceRight && isEnemy && isPawn;
			}
		}
		
		return false;
	}
	
	
	//handle En Passant 
	@Override
	public boolean isSpecialCase(Board board, char fromCol, int fromRow, 
			char toCol, int toRow) {
	   /*
		* prevent illegal en passant move
		* we have to pass in a toCol since we want 
		* to make sure we don't capture the wrong pawn
		* since two oponent's pawns could be at the same row 
		* side by side of my pawn. Each of them must be an 
		* illegal en passant capture
		**/
		boolean isLegalEnPassant = board.checkEnPassant(toCol);
		
		return (isLegalEnPassant && this.color == ChessColor.WHITE && isEnPassantForWhite(board, 
				fromCol, fromRow, toCol, toRow )) || 
				(isLegalEnPassant && this.color == ChessColor.BLACK && isEnPassantForBlack(board, 
						fromCol, fromRow, toCol, toRow ));
	}
}
	
	

