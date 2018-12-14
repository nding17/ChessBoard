import static org.junit.Assert.*;
import org.junit.*;

import org.junit.Test;

public class RookCollisionTest {
	Board board;
	BoardInitializer boardInitializer;
	RookStrategy strategy;

	@Before
	public void initialize(){
		boardInitializer=new TraditionalStart();
		board=new Board();
		strategy=new RookStrategy(board);
	}

	@Test
	public void rookShouldBeAbleToDetectCollisionIfIMoveFromA1ToA5AndQueenIsOnA2(){
		board.setPiece(new ChessPiece(ChessPieceType.QUEEN, ChessColor.WHITE), 'a', 2);
		assertTrue(strategy.checkCollisionOnWay(board, 'a', 1, 'a', 5));
	}
	@Test
	public void rookShouldBeUnableToDetectCollisionIfIMoveFromA1ToA5OnEmptyBoard(){
		assertFalse(strategy.checkCollisionOnWay(board, 'a', 1, 'a', 5));
	}

	@Test
	public void rookShouldBeAbleToDetectCollisionIfIMoveFromD4ToB4AndQueenIsOnC4(){
		board.setPiece(new ChessPiece(ChessPieceType.QUEEN, ChessColor.WHITE), 'c', 4);
		assertTrue(strategy.checkCollisionOnWay(board, 'd', 4, 'b', 4));
	}
	@Test
	public void rookShouldBeUnableToDetectCollisionIfIMoveFromD4ToB4AndBoardIsEmpty(){
		assertFalse(strategy.checkCollisionOnWay(board, 'd', 4, 'b', 4));
	}
	@Test
	public void rookShouldBeUnableToDetectCollisionIfIMoveFromD8ToD1AndBoardIsEmpty(){
		assertFalse(strategy.checkCollisionOnWay(board, 'd', 8, 'd', 1));
	}
	@Test
	public void rookShouldBeUnableToDetectCollisionIfIMoveFromA1ToF1AndBoardIsEmpty(){
		assertFalse(strategy.checkCollisionOnWay(board, 'd', 8, 'd', 1));
	}
	@Test
	public void rookShouldBeAbleToDetectCollisionIfIMoveFromD1ToF1AndQueenIsOnE1(){
		board.setPiece(new ChessPiece(ChessPieceType.QUEEN, ChessColor.WHITE), 'e', 1);
		assertTrue(strategy.checkCollisionOnWay(board, 'd', 1, 'f', 1));
	}
	@Test
	public void rookShouldBeAbleToDetectCollisionIfIMoveFromE8ToE1AndQueenIsOnE4(){
		board.setPiece(new ChessPiece(ChessPieceType.QUEEN, ChessColor.WHITE), 'e', 4);
		assertTrue(strategy.checkCollisionOnWay(board, 'e', 8, 'e', 1));
	}
}
