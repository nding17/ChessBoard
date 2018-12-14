import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CommandTest {
	Board board;
	ChessPiece piece;
	@Before
	public void setUp() throws Exception {
		
		board = new Board(new TraditionalStart());
	}
   
	@Test
	public void testExecuteMove() {
		Command move = new ChessMove(board, 'e', 2, 'e', 4);
		piece = board.pieceAt('e',2);
		move.execute();
		assertEquals(board.pieceAt('e', 4), piece);
		assertEquals(board.pieceAt('e', 2), null);
	}
	
	@Test
	public void testUndoMove() {
		Command move = new ChessMove(board, 'e', 2, 'e', 4);
		piece = board.pieceAt('e',2);
		move.execute();
		move.undo();
		assertEquals(board.pieceAt('e', 4), null);
		assertEquals(board.pieceAt('e', 2), piece);
	}
	
	@Test
	public void testExecuteCapture() {
		ChessPiece capturing = new ChessPiece(ChessPieceType.ROOK, ChessColor.WHITE);
		board.setPiece(capturing, 'd', 4);
		Command move = new Capture(board, 'd', 4, 'd', 7);
		move.execute();
		assertEquals(board.pieceAt('d', 4), null);
		assertEquals(board.pieceAt('d', 7), capturing);
	}
	
	@Test
	public void testUndoCapture() {
		ChessPiece capturing = new ChessPiece(ChessPieceType.ROOK, ChessColor.WHITE);
		ChessPiece captured = board.pieceAt('d', 7);
		board.setPiece(capturing, 'd', 4);
		Command move = new Capture(board, 'd', 4, 'd', 7);
		move.execute();
		move.undo();
		assertEquals(board.pieceAt('d', 4), capturing);
		assertEquals(board.pieceAt('d', 7), captured);
	}
	

}
