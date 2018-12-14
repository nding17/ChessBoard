
public interface MoveStrategy {
	
	public boolean isPatternLegal( char fromCol, int fromRow, 
			char toCol, int toRow, Board b );
	
	public boolean checkCollisionOnSpot( Board board, 
		char toCol, int toRow ); 
	
	//check if there's any collisions on the way, ultimately
	//it gives isMoveLegal an idea if a piece could proceed
	public boolean checkCollisionOnWay( Board board, 
			char fromCol, int fromRow, char toCol, int toRow ); 
	
	//tell you if you can capture a piece regardless of 
	//if there's any pieces on the way
	public boolean canCapture( Board board, char fromCol, 
			int fromRow, char toCol, int toRow );
	
	public boolean enemyKingCheck(Board board, char col, int row);
	
	//handle special cases like En Passant, Castling and promotion
	public boolean isSpecialCase(Board board, char fromCol, int fromRow, char toCol, int toRow);
	
}


