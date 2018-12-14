import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class testCheckForPawn {

	private MoveStrategy strat;
	private Board board;
	private BoardInitializer boardInitializer;
	ChessPieceType pawn = ChessPieceType.PAWN;
	ChessPieceType king = ChessPieceType.KING;
	ChessColor white = ChessColor.WHITE;
	ChessColor black = ChessColor.BLACK;
	
	@Before
	public void setUp(){
		boardInitializer=new TraditionalStart();
		board=new Board(boardInitializer);
	}
	
	@Test
	public void testPawnFromD3() {
		strat = new PawnStrategy(board, white);
		board.setPiece(new ChessPiece(pawn, white), 'd', 3);
		board.setPiece(new ChessPiece(king, black), 'c', 4);
		assertTrue( strat.enemyKingCheck(board, 'd', 3) );
	}
	
	@Test
	public void testPawnFromD3KingInE4() {
		strat = new PawnStrategy(board, white);
		board.setPiece(new ChessPiece(pawn, white), 'd', 3);
		board.setPiece(new ChessPiece(king, black), 'e', 4);
		assertTrue( strat.enemyKingCheck(board, 'd', 3) );
	}
	
	@Test
	public void testBlackPawnFromD3KingInC2() {
		strat = new PawnStrategy(board, black);
		board.setPiece(new ChessPiece(pawn, black), 'd', 3);
		board.setPiece(new ChessPiece(king, white), 'c', 2);
		assertTrue( strat.enemyKingCheck(board, 'd', 3) );
	}
	
	@Test
	public void testBlackPawnFromD3KingInE2() {
		strat = new PawnStrategy(board, black);
		board.setPiece(new ChessPiece(pawn, black), 'd', 3);
		board.setPiece(new ChessPiece(king, white), 'e', 2);
		assertTrue( strat.enemyKingCheck(board, 'd', 3) );
	}
	
	@Test
	public void testBlackPawnFromD3KingSameColorInC2() {
		strat = new PawnStrategy(board, black);
		board.setPiece(new ChessPiece(pawn, black), 'd', 3);
		board.setPiece(new ChessPiece(king, black), 'c', 2);
		assertFalse( strat.enemyKingCheck(board, 'd', 3) );
	}
	
	@Test
	public void testWhitePawnFromD3KingSameColorInC4() {
		strat = new PawnStrategy(board, white);
		board.setPiece(new ChessPiece(pawn, white), 'd', 3);
		board.setPiece(new ChessPiece(king, white), 'c', 4);
		assertFalse( strat.enemyKingCheck(board, 'd', 3) );
	}
	
	@Test
	public void testPawnNoKing() {
		strat = new PawnStrategy(board, black);
		board.setPiece(new ChessPiece(pawn, black), 'd', 3);
		assertFalse( strat.enemyKingCheck(board, 'd', 3) );
	}

}
