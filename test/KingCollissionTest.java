import static org.junit.Assert.*;
import org.junit.*;

import org.junit.Test;

public class KingCollissionTest {
	Board board;
	BoardInitializer boardInitializer;
	KingStrategy strategy;
	ChessPieceType knight= ChessPieceType.KNIGHT;
	ChessColor black=ChessColor.BLACK;
	ChessColor white=ChessColor.WHITE;

	@Before
	public void initialize(){
		boardInitializer=new TraditionalStart();
		board=new Board(boardInitializer);
		strategy=new KingStrategy(board);
	}

	@Test
	public void kingShouldDetectCollisionIfMovingFromE1ToE2OnNormallySetupBoard() {
		
		assertTrue(strategy.checkCollisionOnSpot(board, 'e', 2));
	}
	@Test 
	public void kingShouldNotDetectCollisionIfMovingFromE1ToE2OnEmptyBoard(){
		board=new Board();
		strategy=new KingStrategy(board);
		assertFalse(strategy.checkCollisionOnSpot(board, 'e', 2));
	}
	@Test
	public void kingShouldBeAbleToDetectCollisionIfIPutAPieceOnD1(){
		board=new Board();
		board.setPiece(new ChessPiece(ChessPieceType.QUEEN, ChessColor.WHITE), 'd', 1);
		strategy = new KingStrategy(board);
		strategy.checkCollisionOnSpot(board, 'd', 1);
		assertTrue(strategy.checkCollisionOnSpot(board, 'd', 1));
	}
	

}
