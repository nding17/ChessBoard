import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class testCheckForBishop {

	private MoveStrategy strat;
	private Board board;
	private BoardInitializer boardInitializer;
	ChessPieceType bishop = ChessPieceType.BISHOP;
	ChessPieceType king = ChessPieceType.KING;
	ChessColor white = ChessColor.WHITE;
	ChessColor black = ChessColor.BLACK;
	
	@Before
	public void setUp(){
		boardInitializer=new TraditionalStart();
		board=new Board(boardInitializer);
		strat = new BishopStrategy(board);
	}
	
	@Test
	public void testBishopFromD4() {
		board.setPiece(new ChessPiece(bishop, black), 'd', 4);
		board.setPiece(new ChessPiece(king, white), 'f', 6);
		assertTrue( strat.enemyKingCheck(board, 'd', 4) );
	}
	
	@Test
	public void testBishopFromD4SameColor() {
		board.setPiece(new ChessPiece(bishop, white), 'd', 4);
		board.setPiece(new ChessPiece(king, white), 'f', 6);
		assertFalse( strat.enemyKingCheck(board, 'd', 4) );
	}
	
	@Test
	public void testBishopFromD4KingInD6() {
		board.setPiece(new ChessPiece(bishop, black), 'd', 4);
		board.setPiece(new ChessPiece(king, white), 'd', 6);
		assertFalse( strat.enemyKingCheck(board, 'd', 4) );
	}
	
	@Test
	public void testBishopFromH3KingInG4() {
		board.setPiece(new ChessPiece(bishop, black), 'h', 3);
		board.setPiece(new ChessPiece(king, white), 'g', 4);
		assertTrue( strat.enemyKingCheck(board, 'h', 3) );
	}
	
	@Test
	public void testBishopFromD4KingInC5() {
		board.setPiece(new ChessPiece(bishop, black), 'd', 4);
		board.setPiece(new ChessPiece(king, white), 'c', 5);
		assertTrue( strat.enemyKingCheck(board, 'd', 4) );
	}
	
	@Test
	public void testBishopFromD4KingInC3() {
		board.setPiece(new ChessPiece(bishop, black), 'd', 4);
		board.setPiece(new ChessPiece(king, white), 'c', 3);
		assertTrue( strat.enemyKingCheck(board, 'd', 4) );
	}
	
	@Test
	public void testBishopFromD4KingInE3() {
		board.setPiece(new ChessPiece(bishop, black), 'd', 4);
		board.setPiece(new ChessPiece(king, white), 'e', 3);
		assertTrue( strat.enemyKingCheck(board, 'd', 4) );
	}
	
	@Test
	public void testBishopFromD4KingInD5() {
		board.setPiece(new ChessPiece(bishop, black), 'd', 4);
		board.setPiece(new ChessPiece(king, white), 'd', 5);
		assertFalse( strat.enemyKingCheck(board, 'd', 4) );
	}

}
