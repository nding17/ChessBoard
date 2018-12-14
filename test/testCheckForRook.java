import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class testCheckForRook {

	private MoveStrategy strat;
	private Board board;
	private BoardInitializer boardInitializer;
	ChessPieceType rook = ChessPieceType.ROOK;
	ChessPieceType king = ChessPieceType.KING;
	ChessPieceType bishop = ChessPieceType.BISHOP;
	ChessColor white = ChessColor.WHITE;
	ChessColor black = ChessColor.BLACK;
	
	@Before
	public void setUp(){
		boardInitializer=new TraditionalStart();
		board=new Board(boardInitializer);
		strat = new RookStrategy(board);
	}
	
	
	@Test
	public void testRookFromD4North() {
		board.setPiece( new ChessPiece( rook, white ), 'd', 4 );
		board.setPiece( new ChessPiece( king, black ), 'd', 5 );
		strat.enemyKingCheck(board, 'd', 4);
		assertTrue( strat.enemyKingCheck(board, 'd', 4) );
	}
	
	@Test
	public void testRookFromD4South() {
		board.setPiece( new ChessPiece( rook, white ), 'd', 4 );
		board.setPiece( new ChessPiece( king, black ), 'd', 3 );
		assertTrue( strat.enemyKingCheck(board, 'd', 4) );
	}
	
	@Test
	public void testRookFromD4West() {
		board.setPiece( new ChessPiece( rook, white ), 'd', 4 );
		board.setPiece( new ChessPiece( king, black ), 'c', 4 );
		assertTrue( strat.enemyKingCheck(board, 'd', 4) );
	}
	
	@Test
	public void testRookFromD4East() {
		board.setPiece( new ChessPiece( rook, white ), 'd', 4 );
		board.setPiece( new ChessPiece( king, black ), 'e', 4 );
		assertTrue( strat.enemyKingCheck(board, 'd', 4) );
	}
	
	public void testRookFromD4wrongPattern(){
		board.setPiece( new ChessPiece( bishop, white ), 'd', 4 );
		board.setPiece( new ChessPiece( king, black ), 'e', 5 );
		assertFalse( strat.enemyKingCheck(board, 'd', 4) );
	}
	
	@Test
	public void testRookFromD4sameColorKing(){
		board.setPiece( new ChessPiece( bishop, black ), 'd', 4 );
		board.setPiece( new ChessPiece( king, black ), 'e', 4 );
		assertFalse( strat.enemyKingCheck(board, 'd', 4) );
	}

}
