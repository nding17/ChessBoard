import static org.junit.Assert.*;
import org.junit.*;

import org.junit.Test;

public class PawnCollisionTests {
	Board board;
	BoardInitializer boardInitializer;
	MoveStrategy whitePawnStrategy;
	MoveStrategy blackPawnStrategy;

	@Before
	public void initialize(){
		boardInitializer=new TraditionalStart();
		board=new Board();
		whitePawnStrategy=new PawnStrategy(board, ChessColor.WHITE);
		blackPawnStrategy=new PawnStrategy(board, ChessColor.BLACK);
	}

	@Test
	public void whitePawnShouldDetectCollisionIfMovingFromE2ToE4IfIPutPieceOnE3() {
		board.setPiece(new ChessPiece(ChessPieceType.QUEEN, ChessColor.WHITE), 'e', 3);
		assertTrue(whitePawnStrategy.checkCollisionOnWay(board, 'e', 2, 'e', 4));
	}
	@Test
	public void whitePawnShouldNotDetectCollisionIfMovingFromE2ToE4IfBoardIsEmpty() {
		assertFalse(whitePawnStrategy.checkCollisionOnWay(board, 'e', 2, 'e', 4));
	}
	@Test
	public void blackPawnShouldDetectCollisionIfMovingFromE7ToE5IfIPutPieceOnE6() {
		board.setPiece(new ChessPiece(ChessPieceType.QUEEN, ChessColor.WHITE), 'e', 6);
		assertTrue(blackPawnStrategy.checkCollisionOnWay(board, 'e', 7, 'e', 5));
	}
	@Test
	public void blackPawnShouldNotDetectCollisionIfMovingFromE7ToE5IfBoardIsEmpty() {
		assertFalse(blackPawnStrategy.checkCollisionOnWay(board, 'e', 7, 'e', 5));
	}

}
