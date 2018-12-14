import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class testCheckForKnight {

	private MoveStrategy strat;
	private Board board;
	private BoardInitializer boardInitializer;
	ChessPieceType knight = ChessPieceType.KNIGHT;
	ChessPieceType king = ChessPieceType.KING;
	ChessColor white = ChessColor.WHITE;
	ChessColor black = ChessColor.BLACK;
	
	@Before
	public void setUp(){
		boardInitializer=new TraditionalStart();
		board=new Board();
		strat = new KnightStrategy(board);
	}
	
	@Test
	public void testKnightD3() {
		board=new Board(boardInitializer);

		board.setPiece( new ChessPiece( knight, black ), 'd', 3 );
		strat = new KnightStrategy(board);
		assertTrue( strat.enemyKingCheck(board, 'd', 3) );
	}
	
	@Test
	public void testKnightD4NoKing() {
		board=new Board(boardInitializer);
		board.setPiece( new ChessPiece( knight, black ), 'd', 4 );
		strat = new KnightStrategy(board);

		assertFalse( strat.enemyKingCheck(board, 'd', 4) );
	}
	
	@Test
	public void testKnightH5() {
		board=new Board(boardInitializer);
		board.setPiece( new ChessPiece( knight, black ), 'h', 5 );
		board.setPiece( new ChessPiece( king, white ), 'g', 3 );
		strat = new KnightStrategy(board);

		assertTrue( strat.enemyKingCheck(board, 'h', 5) );
	}
	
	@Test
	public void testKnightH6() {
		board=new Board(boardInitializer);
		board.setPiece( new ChessPiece( knight, black ), 'h', 6 );
		board.setPiece( new ChessPiece( king, white ), 'f', 5 );
		strat = new KnightStrategy(board);

		assertTrue( strat.enemyKingCheck(board, 'h', 6) );
	}
	
	@Test
	public void testKnightH4() {
		board=new Board(boardInitializer);
		board.setPiece( new ChessPiece( knight, black ), 'h', 4 );
		board.setPiece( new ChessPiece( king, white ), 'f', 3 );
		strat = new KnightStrategy(board);

		assertTrue( strat.enemyKingCheck(board, 'h', 4) );
	}
	
	@Test
	public void testKnightH6G4() {
		board=new Board(boardInitializer);
		board.setPiece( new ChessPiece( knight, black ), 'h', 6 );
		board.setPiece( new ChessPiece( king, white ), 'g', 4 );
		strat = new KnightStrategy(board);

		assertTrue( strat.enemyKingCheck(board, 'h', 6) );
	}
	
	@Test
	public void testKnightA5B3() {
		board=new Board(boardInitializer);
		board.setPiece( new ChessPiece( knight, black ), 'a', 5 );
		board.setPiece( new ChessPiece( king, white ), 'b', 3 );
		strat = new KnightStrategy(board);

		assertTrue( strat.enemyKingCheck(board, 'a', 5) );
	}
	
	@Test
	public void testKnightE5() {
		board=new Board(boardInitializer);
		board.setPiece( new ChessPiece( knight, black ), 'e', 5 );
		strat = new KnightStrategy(board);

		assertFalse( strat.enemyKingCheck(board, 'e', 5) );
	}

}
