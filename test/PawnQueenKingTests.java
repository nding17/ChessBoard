import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PawnQueenKingTests {
	Board board=new Board();
	PawnStrategy whitePawnStrategy=new PawnStrategy( board, ChessColor.WHITE);
	PawnStrategy blackPawnStrategy=new PawnStrategy(board, ChessColor.BLACK);
	KingStrategy kingStrategy=new KingStrategy( board );
	QueenStrategy queenStrategy=new QueenStrategy( board );
	BoardInitializer boardinit;
	ChessPieceType king=ChessPieceType.KING;
	ChessPieceType pawn=ChessPieceType.PAWN;
	ChessPieceType rook=ChessPieceType.ROOK;
	ChessColor black=ChessColor.BLACK;
	ChessColor white=ChessColor.WHITE;
	
	@Before
	public void initialize(){
		
		boardinit=new TraditionalStart();
		
		board=new Board(boardinit);
	}
	

	
	@Test
	public void queenShouldReturnTrueWhenMovingFromA1ToA2() {
		assertTrue(queenStrategy.isPatternLegal('a', 1, 'a', 2, board));
	}
	@Test
	public void queenShouldReturnFalseWhenMovingFromA1ToB3(){
		assertFalse(queenStrategy.isPatternLegal('a', 1, 'b', 3, board));
	}
	@Test
	public void queenShouldReturnTrueWhenMovingFromA1ToH8(){
		assertTrue(queenStrategy.isPatternLegal('a', 1, 'h', 8, board));
	}
	@Test
	public void queenShouldReturnTrueWhenMovingFromA2ToA8(){
		assertTrue(queenStrategy.isPatternLegal('a', 2, 'a', 8, board));
	}
	@Test public void kingShouldReturnTrueWhenMovingFromB3ToB4(){
		assertTrue(kingStrategy.isPatternLegal('b', 3, 'b', 4, board));
	}
	@Test public void kingShouldReturnFalseWhenMovingFromB3ToB5(){
		assertFalse(kingStrategy.isPatternLegal('b', 3, 'b', 5, board));
	}
	@Test public void whitePawnShouldReturnTrueWhenMovingFromA2ToA4(){
		assertTrue(whitePawnStrategy.isPatternLegal('a', 2, 'a', 4, board));
	}
	@Test public void whitePawnShouldReturnFalseWhenMovingFromA2ToA5(){
		assertFalse(whitePawnStrategy.isPatternLegal('a', 2, 'a', 5, board ));
	}
	@Test public void whitePawnShouldReturnFalseWhenMovingFromA3ToA5(){
		board.setPiece(new ChessPiece(pawn, white), 'a', 3);
		assertFalse(whitePawnStrategy.isPatternLegal('a', 3, 'a', 5, board));
	}
	@Test public void whitePawnShouldReturnFalseWhenMovingFromA2ToA1(){
		assertFalse(whitePawnStrategy.isPatternLegal('a', 2, 'a', 1, board));
	}
	@Test public void blackPawnShouldReturnTrueWhenMovingFromA7ToA5(){
		assertTrue(blackPawnStrategy.isPatternLegal('a', 7, 'a', 5, board));
	}
	@Test public void blackPawnShouldReturnFalseWhenMovingFromA6ToA4(){
		board.setPiece(new ChessPiece(pawn, black), 'a', 6);
		assertFalse(blackPawnStrategy.isPatternLegal('a', 6, 'a', 4, board));
	}
	@Test public void blackPawnShouldReturnTrueWhenMovingFromA6ToA5(){
		assertTrue(blackPawnStrategy.isPatternLegal('a', 6, 'a', 5, board));
	}
	@Test
	public void kingShouldReturnTrueIfTryingToMoveFromE1ToG1IfNothingIsTHereAndHeHasntMoved(){
		board=new Board();
		board.setPiece(new ChessPiece(king, white), 'e', 1);
		board.setPiece(new ChessPiece(rook, white), 'h', 1);
		board.makeMove('e'-'a', 0, 'g'-'a', 0);
	}
	

}
