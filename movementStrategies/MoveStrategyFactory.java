
public class MoveStrategyFactory {
	
	public MoveStrategyFactory(){}
	
	public MoveStrategy getStrategy( ChessPieceType type, 
			ChessColor color, Board board ){
		
		switch( type ){
			case ROOK:
				return new RookStrategy( board );
			case BISHOP:
				return new BishopStrategy( board );
			case KNIGHT:
				return new KnightStrategy( board );
			case PAWN:
				return new PawnStrategy( board, color );
			case QUEEN:
				return new QueenStrategy( board );
			default:
				return new KingStrategy( board );
		}
		
	}
	
}
