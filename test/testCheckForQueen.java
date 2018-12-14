import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class testCheckForQueen {

	private MoveStrategy strat;
	private Board board;
	private BoardInitializer boardInitializer;
	ChessPieceType queen = ChessPieceType.QUEEN;
	ChessPieceType king = ChessPieceType.KING;
	ChessColor white = ChessColor.WHITE;
	ChessColor black = ChessColor.BLACK;
	
	@Before
	public void setUp(){
		boardInitializer=new TraditionalStart();
		board=new Board(boardInitializer);
		strat = new QueenStrategy(board);
	}
	
	@Test
	public void testQueenFromD4() {
		board.setPiece( new ChessPiece( queen, white ), 'd', 4 );
		board.setPiece( new ChessPiece( king, black ), 'e', 5 );
		assertTrue( strat.enemyKingCheck(board, 'd', 4));
	}
	
	@Test
	public void testQueenFromD4KingSameColor() {
		board.setPiece( new ChessPiece( queen, white ), 'd', 4 );
		board.setPiece( new ChessPiece( king, white ), 'e', 5 );
		assertFalse( strat.enemyKingCheck(board, 'd', 4));
	}
	
	@Test
	public void testQueenFromD4KingNorth() {
		board.setPiece( new ChessPiece( queen, black ), 'd', 4 );
		board.setPiece( new ChessPiece( king, white ), 'd', 6 );
		assertTrue( strat.enemyKingCheck(board, 'd', 4));
	}
	
	@Test
	public void testQueenFromD4KingSouth() {
		board.setPiece( new ChessPiece( queen, black ), 'd', 4 );
		board.setPiece( new ChessPiece( king, white ), 'd', 2 );
		assertTrue( strat.enemyKingCheck(board, 'd', 4));
	}
	
	@Test
	public void testQueenFromD4KingWest() {
		board.setPiece( new ChessPiece( queen, black ), 'd', 4 );
		board.setPiece( new ChessPiece( king, white ), 'a', 4 );
		assertTrue( strat.enemyKingCheck(board, 'd', 4));
	}
	
	@Test
	public void testQueenFromD4KingEast() {
		board.setPiece( new ChessPiece( queen, black ), 'd', 4 );
		board.setPiece( new ChessPiece( king, white ), 'f', 4 );
		assertTrue( strat.enemyKingCheck(board, 'd', 4));
	}
	
	@Test
	public void testQueenFromD4KingNE() {
		board.setPiece( new ChessPiece( queen, black ), 'd', 4 );
		board.setPiece( new ChessPiece( king, white ), 'e', 5 );
		assertTrue( strat.enemyKingCheck(board, 'd', 4));
	}
	
	@Test
	public void testQueenFromD4KingNW() {
		board.setPiece( new ChessPiece( queen, black ), 'd', 4 );
		board.setPiece( new ChessPiece( king, white ), 'c', 5 );
		assertTrue( strat.enemyKingCheck(board, 'd', 4));
	}
	
	@Test
	public void testQueenFromD4KingSE() {
		board.setPiece( new ChessPiece( queen, black ), 'd', 4 );
		board.setPiece( new ChessPiece( king, white ), 'f', 2 );
		assertTrue( strat.enemyKingCheck(board, 'd', 4));
	}
	
	@Test
	public void testQueenFromD4KingSW() {
		board.setPiece( new ChessPiece( queen, black ), 'd', 4 );
		board.setPiece( new ChessPiece( king, white ), 'b', 2 );
		assertTrue( strat.enemyKingCheck(board, 'd', 4));
	}
	
	@Test
	public void testQueenFromD4NoKing() {
		board=new Board(boardInitializer);
		board.setPiece( new ChessPiece( queen, black ), 'd', 4 );
		assertFalse( strat.enemyKingCheck(board, 'd', 4));
	}

}
