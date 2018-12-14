import java.util.*;
public class KnightStrategy implements MoveStrategy {

	private static final int ONE_SQUARE = 1,
			TWO_SQUARE = 2;
	private static final int ROW_LOWER_BOUND = 1;
	private static final int ROW_UPPER_BOUND = 8;
	private static final char COL_LOWER_BOUND = 'a';
	private static final char COL_UPPER_BOUND = 'h';
	private static final int INVALID_ROW = -1;
	private static final char INVALID_COL = 'x';
	
	private Board board;
	
	public KnightStrategy( Board board ){
		this.board = board;
	}
	
	@Override
	public boolean isSpecialCase(Board board, char fromCol, int fromRow, char toCol, int toRow) {
		return false;
	}
	
	@Override
	public boolean checkCollisionOnWay(Board b, char fromCol, int fromRow, 
			char toCol, int toRow){
		return false;
	}
	
	private boolean isMoveInLpattern( char fromCol, 
			int fromRow, char toCol, int toRow ){
		
		int horizontalIncrement = Math.abs( fromCol - toCol );
		int verticalIncrement = Math.abs( fromRow - toRow );
		
		boolean isStandingL = horizontalIncrement == ONE_SQUARE &&
				verticalIncrement == TWO_SQUARE;
		
		boolean isLyingL = horizontalIncrement == TWO_SQUARE &&
				verticalIncrement == ONE_SQUARE;
		
		return isStandingL || isLyingL;
		
	}
	
	public boolean isPatternLegal( char fromCol, 
			int fromRow, char toCol, int toRow, Board b ){
		
		return isMoveInLpattern( fromCol, fromRow, toCol, toRow );
		
	}
	
	public boolean checkCollisionOnSpot(Board b, char toCol, int toRow){
		return board.pieceAt(toCol, toRow)!=null;
	}
	
	public boolean canCapture(Board b, char fromCol, int fromRow, 
			char toCol, int toRow){
		
		ChessPiece piece = b.pieceAt(fromCol, fromRow);
		boolean isPieceKnight = piece.getType() == ChessPieceType.KNIGHT;
		
		if(checkCollisionOnSpot(b,toCol,toRow) && isPieceKnight){
			ChessPiece fromPiece = b.pieceAt(fromCol, fromRow);
			ChessPiece toPiece = b.pieceAt(toCol, toRow);
			return fromPiece.getColor() != toPiece.getColor();
		}
		//empty space, nothing to capture
		return false;
	}

	@Override
	public boolean enemyKingCheck(Board b, char col, int row){   
		
		ArrayList<ChessPiece> possibleChecks = new ArrayList<ChessPiece>();
		
		//all possible rows and columns
		int[] possibleRows = {row+1, row+2, row+2, row+1, 
				row-1, row-2, row-2, row-1}; 
		char[] possibleCols = {(char)((int)col+2), (char)((int)col+1),
				(char)((int)col-1), (char)((int)col-2), (char)((int)col-2),
				(char)((int)col-1), (char)((int)col+1), (char)((int)col+2)};
		
		for( int i = 0; i < possibleRows.length; ++i ){
			boolean isRowCorrect = possibleRows[i] >= ROW_LOWER_BOUND &&
					possibleRows[i] <= ROW_UPPER_BOUND;
			boolean isColCorrect = possibleCols[i] >= COL_LOWER_BOUND &&
					possibleCols[i] <= COL_UPPER_BOUND;
			if( !isRowCorrect || !isColCorrect ){
				possibleRows[i] = INVALID_ROW;
				possibleCols[i] = INVALID_COL;
			}
		}
		
		for( int i = 0; i < possibleRows.length; ++i ){
			if(possibleRows[i] != INVALID_ROW && 
					possibleCols[i] != INVALID_COL){
				ChessPiece currPiece = 
						board.pieceAt(possibleCols[i], possibleRows[i]);
				possibleChecks.add(currPiece);
			}
		}
	
		ChessColor currentKnightColor=b.pieceAt(col, row).getColor();
		boolean isCheck = false;
		
		for(int i=0; i<possibleChecks.size(); i++){
			boolean isNull=possibleChecks.get(i)==null;
			if(!isNull){
				if(possibleChecks.get(i).getType() == ChessPieceType.KING &&
						possibleChecks.get(i).getColor() != currentKnightColor	){
					isCheck = true;
				}
			}
		}
		return isCheck;
	}

}
