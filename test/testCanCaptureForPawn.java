import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class testCanCaptureForPawn {

	private MoveStrategyFactory stratFac;
	private Board board;
	private BoardInitializer boardInitializer;
	
	@Before
	public void setUp(){
		boardInitializer=new TraditionalStart();
		board=new Board(boardInitializer);
		stratFac = new MoveStrategyFactory();
	}
	
	@Test
	public void testPawnCaptureFromA3toA4() {
		
		MoveStrategy pawnStrategy = stratFac.getStrategy(ChessPieceType.PAWN, 
				null, board);
		board.setPiece(new ChessPiece( ChessPieceType.BISHOP, ChessColor.BLACK ), 
				'a', 4);
		board.setPiece(new ChessPiece( ChessPieceType.PAWN, ChessColor.WHITE ), 
				'a', 3);
		assertFalse( pawnStrategy.canCapture(board, 'a', 3, 'a', 4) );
		
	}
	
	@Test
	public void testPawnCaptureFromA3toB4() {
		
		MoveStrategy pawnStrategy = stratFac.getStrategy(ChessPieceType.PAWN, 
				null, board);
		board.setPiece(new ChessPiece( ChessPieceType.BISHOP, ChessColor.WHITE ), 
				'b', 4);
		board.setPiece(new ChessPiece( ChessPieceType.PAWN, ChessColor.BLACK ), 
				'a', 3);
		assertFalse( pawnStrategy.canCapture(board, 'a', 3, 'b', 4) );
	}
	
	// a white pawn is trying to capture
	@Test
	public void testPawnCaptureFromB3toC4() {
		
		MoveStrategy pawnStrategy = stratFac.getStrategy(ChessPieceType.PAWN, 
				null, board);
		board.setPiece(new ChessPiece( ChessPieceType.BISHOP, ChessColor.BLACK ), 
				'c', 4);
		board.setPiece(new ChessPiece( ChessPieceType.PAWN, ChessColor.WHITE ), 
				'b', 3);
		assertTrue( pawnStrategy.canCapture(board, 'b', 3, 'c', 4) );
	}

	//attempt to capture a piece with the same color
	@Test
	public void testPawnCaptureFromB3toC4SameColor() {
		
		MoveStrategy pawnStrategy = stratFac.getStrategy(ChessPieceType.PAWN, 
				null, board);
		board.setPiece(new ChessPiece( ChessPieceType.BISHOP, ChessColor.WHITE ), 
				'c', 4);
		board.setPiece(new ChessPiece( ChessPieceType.PAWN, ChessColor.WHITE ), 
				'b', 3);
		assertFalse( pawnStrategy.canCapture(board, 'b', 3, 'c', 4) );
	}
	
	//pretend to be a pawn and try to apply its rules
	@Test
	public void testFakePawnCaptureFromB3toC4() {
		
		MoveStrategy pawnStrategy = stratFac.getStrategy(ChessPieceType.PAWN, 
				null, board);
		board.setPiece(new ChessPiece( ChessPieceType.BISHOP, ChessColor.WHITE ), 
				'c', 4);
		board.setPiece(new ChessPiece( ChessPieceType.KING, ChessColor.BLACK ), 
				'b', 3);
		assertFalse( pawnStrategy.canCapture(board, 'b', 3, 'c', 4) );
	}
	
	//a black pawn is trying to capture
	@Test
	public void testPawnCaptureFromB4toA3() {
		
		MoveStrategy pawnStrategy = stratFac.getStrategy(ChessPieceType.PAWN, 
				null, board);
		board.setPiece(new ChessPiece( ChessPieceType.BISHOP, ChessColor.WHITE ), 
				'a', 3);
		board.setPiece(new ChessPiece( ChessPieceType.PAWN, ChessColor.BLACK ), 
				'b', 4);
		assertTrue( pawnStrategy.canCapture(board, 'b', 4, 'a', 3) );
	}
	
	//a white pawn is trying to capture
	@Test
	public void testPawnCaptureFromB5toA6() {
		
		MoveStrategy pawnStrategy = stratFac.getStrategy(ChessPieceType.PAWN, 
				null, board);
		board.setPiece(new ChessPiece( ChessPieceType.BISHOP, ChessColor.BLACK ), 
				'a', 6);
		board.setPiece(new ChessPiece( ChessPieceType.PAWN, ChessColor.WHITE ), 
				'b', 5);
		assertTrue( pawnStrategy.canCapture(board, 'b', 5, 'a', 6) );
	}
	
	//a black pawn is trying to capture
	@Test
	public void testPawnCaptureFromB6toA5() {
		
		MoveStrategy pawnStrategy = stratFac.getStrategy(ChessPieceType.PAWN, 
				null, board);
		board.setPiece(new ChessPiece( ChessPieceType.BISHOP, ChessColor.WHITE ), 
				'a', 5);
		board.setPiece(new ChessPiece( ChessPieceType.PAWN, ChessColor.BLACK ), 
				'b', 6);
		assertTrue( pawnStrategy.canCapture(board, 'b', 6, 'a', 5) );
	}

}
