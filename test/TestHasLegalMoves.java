import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestHasLegalMoves {
	private Board board;
	
	@Before
	public void setup(){
		
	}
	@Test
	public void testWhiteStartsWithLegalMoves() {
		board = new Board(new TraditionalStart());
		assertTrue(board.hasLegalMoves(ChessColor.WHITE));
	}
	@Test
	public void testBlackCantMoveInCheckmate(){
		board = new Board(new BoardInitializer(){
			public void createBoard(Board b, Turns turn){
				b.setPiece(new ChessPiece(ChessPieceType.KING, ChessColor.BLACK), 'a', 1);
				b.setPiece(new ChessPiece(ChessPieceType.QUEEN, ChessColor.WHITE ), 'a', 8);
				b.setPiece(new ChessPiece(ChessPieceType.QUEEN, ChessColor.WHITE ), 'b', 8);
			}
		});
		assertFalse(board.hasLegalMoves(ChessColor.BLACK));
	}
	@Test
	public void testBlackCanMoveInCheck(){
		board = new Board(new BoardInitializer(){
			public void createBoard(Board b, Turns turn){
				b.setPiece(new ChessPiece(ChessPieceType.KING, ChessColor.BLACK), 'a', 1);
				b.setPiece(new ChessPiece(ChessPieceType.QUEEN, ChessColor.WHITE ), 'a', 8);
				b.setPiece(new ChessPiece(ChessPieceType.QUEEN, ChessColor.WHITE ), 'h', 1);
			}
		});
		assertTrue(board.hasLegalMoves(ChessColor.BLACK));
	}
	@Test
	public void testBlackCanCaptureInCheck(){
		board = new Board(new BoardInitializer(){
			public void createBoard(Board b, Turns turn){
				b.setPiece(new ChessPiece(ChessPieceType.KING, ChessColor.BLACK), 'a', 1);
				b.setPiece(new ChessPiece(ChessPieceType.QUEEN, ChessColor.WHITE ), 'a', 2);
				b.setPiece(new ChessPiece(ChessPieceType.QUEEN, ChessColor.WHITE ), 'b', 8);
			}
		});
		assertTrue(board.hasLegalMoves(ChessColor.BLACK));
	}

}
