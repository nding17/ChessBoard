import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class KnightStrategyTest {

	MoveStrategy strategy;
	BoardInitializer boardinit;
	Board board;

	@Before
	public void setUp(){
		boardinit=new TraditionalStart();
		board=new Board(boardinit);
		strategy=new KnightStrategy(board);
	}
	
	@Test
	public void testKnightD4toF5() {
		assertTrue(strategy.isPatternLegal('d', 4, 'f', 5, board) );
	}
	
	@Test
	public void testKnightD4toE6() {
		assertTrue(strategy.isPatternLegal('d', 4, 'e', 6, board) );
	}
	
	@Test
	public void testKnightD4toC6() {
		assertTrue( strategy.isPatternLegal('d', 4, 'c', 6, board) );
	}
	
	@Test
	public void testKnightD4toB5() {
		assertTrue( strategy.isPatternLegal('d', 4, 'b', 5, board) );
	}
	
	@Test
	public void testKnightD4toB3() {
		assertTrue( strategy.isPatternLegal('d', 4, 'b', 3, board) );
	}
	
	@Test
	public void testKnightD4toC2() {
		assertTrue( strategy.isPatternLegal('d', 4, 'c', 2, board) );
	}
	
	@Test
	public void testKnightD4toE2() {
		assertTrue( strategy.isPatternLegal('d', 4, 'e', 2, board) );
	}
	
	@Test
	public void testKnightD4toF3() {
		assertTrue( strategy.isPatternLegal('d', 4, 'f', 3, board) );
	}
	
	@Test
	public void testKnightD4toF4() {
		assertFalse( strategy.isPatternLegal('d', 4, 'f', 4, board) );
	}
	
	@Test
	public void testKnightD4toB4() {
		assertFalse( strategy.isPatternLegal('d', 4, 'b', 4, board) );
	}
	@Test
	public void test1(){
		board.setPiece(new ChessPiece(ChessPieceType.KNIGHT, ChessColor.WHITE), 'd', 6);
		strategy.enemyKingCheck(board, 'd', 6);
		assertTrue(strategy.enemyKingCheck(board, 'd', 6));
	}

	@Test
	public void test2(){
		board.setPiece(new ChessPiece(ChessPieceType.KNIGHT, ChessColor.WHITE), 'e', 6);
		strategy.enemyKingCheck(board, 'e', 6);
		assertFalse(strategy.enemyKingCheck(board, 'e', 6));
	}
	@Test
	public void test3(){
		board.setPiece(new ChessPiece(ChessPieceType.KNIGHT, ChessColor.WHITE), 'e',4);
		strategy.enemyKingCheck(board, 'e', 4);
		assertFalse(strategy.enemyKingCheck(board, 'e', 4));
	}
	@Test
	public void test4(){
		board.setPiece(new ChessPiece(ChessPieceType.KNIGHT, ChessColor.BLACK), 'd',3);
		strategy.enemyKingCheck(board, 'd', 3);
		assertTrue(strategy.enemyKingCheck(board, 'd', 3));
	}
	@Test
	public void test5(){
		board.setPiece(new ChessPiece(ChessPieceType.KNIGHT, ChessColor.BLACK), 'f',3);
		strategy.enemyKingCheck(board, 'f', 3);
		assertTrue(strategy.enemyKingCheck(board, 'f', 3));
	}
	
	
}
