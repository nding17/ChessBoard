import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QueenCollisionTest {

	private Board board;
	private BoardInitializer boardInitializer;
	private MoveStrategy queenStrategy;
	
	@Before
	public void setUp(){
		boardInitializer=new TraditionalStart();
		board=new Board(boardInitializer);
		queenStrategy=new QueenStrategy(board);
	}
	
	@Test
	public void testQueenCollisionFromA4toB5() {
		
		assertFalse(queenStrategy.checkCollisionOnWay(board, 'a', 4, 'b', 5));
	}
	
	@Test
	public void testQueenCollisionFromA4toC6() {
		
		assertFalse(queenStrategy.checkCollisionOnWay(board, 'a', 4, 'c', 6));
	}
	
	@Test
	public void testQueenCollisionFromA4toC6onePice() {
		
		board.setPiece(new ChessPiece(ChessPieceType.QUEEN, 
				ChessColor.WHITE), 'b', 5);
		queenStrategy.checkCollisionOnWay(board, 'a', 4, 'c', 6);
		assertTrue(queenStrategy.checkCollisionOnWay(board, 'a', 4, 'c', 6));
	}
	
	
	@Test
	public void testQueenCollisionFromC5toA3() {
		
		assertFalse(queenStrategy.checkCollisionOnWay(board, 'c', 5, 'a', 3));
	}
	
	@Test
	public void testQueenCollisionFromD3toB1() {
		
		assertTrue(queenStrategy.checkCollisionOnWay(board, 'd', 3, 'b', 1));
	}
	
	@Test
	public void testQueenCollisionFromD3toD5() {
		
		assertFalse(queenStrategy.checkCollisionOnWay(board, 'd', 3, 'd', 5));
	}
	
	@Test
	public void testQueenCollisionFromD3toA3() {
		
		assertFalse(queenStrategy.checkCollisionOnWay(board, 'd', 3, 'a', 3));
	}
	
	@Test
	public void testQueenCollisionFromD3toA3onePiece() {
		
		board.setPiece(new ChessPiece(ChessPieceType.QUEEN, 
				ChessColor.WHITE), 'c', 3);
		assertTrue(queenStrategy.checkCollisionOnWay(board, 'd', 3, 'a', 3));
	}
	
	@Test
	public void testQueenCollisionFromH8toH1onePiece() {
		
		assertTrue(queenStrategy.checkCollisionOnWay(board, 'h', 8, 'h', 1));
	}

}
