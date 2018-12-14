import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class testCanCapture {

	private MoveStrategyFactory stratFac;
	private Board board;
	private BoardInitializer boardInitializer;
	
	@Before
	public void setUp(){
		boardInitializer=new TraditionalStart();
		board=new Board(boardInitializer);
		stratFac = new MoveStrategyFactory();
	}
	
	//knight
	@Test
	public void testKnightCapture() {
		
		MoveStrategy knightStrategy = stratFac.getStrategy(ChessPieceType.KNIGHT, 
				null, board);
		//nothing to capture yet
		assertFalse( knightStrategy.canCapture(board, 'g', 1, 'f', 3) );
	}
	
	//king
	@Test
	public void testKingCapture() {
		
		MoveStrategy kingStrategy = stratFac.getStrategy(ChessPieceType.KING, 
				null, board);
		//nothing to capture yet
		assertFalse( kingStrategy.canCapture(board, 'e', 1, 'e', 2) );
	}
	
	//queen
	@Test
	public void testQueenCapture() {
		
		MoveStrategy queenStrategy = stratFac.getStrategy(ChessPieceType.QUEEN, 
				null, board);
		//nothing to capture yet
		assertFalse( queenStrategy.canCapture(board, 'd', 1, 'd', 2) );
	}
	
	//rook
	@Test
	public void testRookCapture() {
		
		MoveStrategy rookStrategy = stratFac.getStrategy(ChessPieceType.ROOK, 
				null, board);
		//nothing to capture yet
		assertFalse( rookStrategy.canCapture(board, 'a', 1, 'a', 2) );
	}

	//rook
	@Test
	public void testBishopCapture() {
		
		MoveStrategy bishopStrategy = stratFac.getStrategy(ChessPieceType.BISHOP, 
				null, board);
		//nothing to capture yet
		assertFalse( bishopStrategy.canCapture(board, 'c', 1, 'd', 2) );
	}
	
	// add a piece that can be captured by a black knight
	@Test
	public void testKnightCaptureOnePiece() {
		
		MoveStrategy knightStrategy = stratFac.getStrategy(ChessPieceType.KNIGHT, 
				null, board);
		board.setPiece( new ChessPiece( ChessPieceType.BISHOP, ChessColor.WHITE ),
				'd', 5);
		board.setPiece( new ChessPiece( ChessPieceType.KNIGHT, ChessColor.BLACK ),
				'c', 3);
		assertTrue( knightStrategy.canCapture(board, 'c', 3, 'd', 5) );
	}
	
	// black knight capture but fail because of the same color
	@Test
	public void testKnightCaptureOnePieceFail() {
		
		MoveStrategy knightStrategy = stratFac.getStrategy(ChessPieceType.KNIGHT, 
				null, board);
		board.setPiece( new ChessPiece( ChessPieceType.BISHOP, ChessColor.BLACK ),
				'd', 5);
		board.setPiece( new ChessPiece( ChessPieceType.KNIGHT, ChessColor.BLACK ),
				'c', 3);
		assertFalse( knightStrategy.canCapture(board, 'c', 3, 'd', 5) );
	}
	
	// a piece pretends to be a black knight 
	@Test
	public void testFakeKnightCaptureOnePieceFail() {
		
		MoveStrategy knightStrategy = stratFac.getStrategy(ChessPieceType.KNIGHT, 
				null, board);
		board.setPiece( new ChessPiece( ChessPieceType.BISHOP, ChessColor.WHITE ),
				'd', 5);
		board.setPiece( new ChessPiece( ChessPieceType.QUEEN, ChessColor.BLACK ),
				'c', 3);
		assertFalse( knightStrategy.canCapture(board, 'c', 3, 'd', 5) );
	}
	
	//try different position by using a black knight
	@Test
	public void testKnightCaptureOnePieceFromF4toD5() {
		
		MoveStrategy knightStrategy = stratFac.getStrategy(ChessPieceType.KNIGHT, 
				null, board);
		board.setPiece( new ChessPiece( ChessPieceType.BISHOP, ChessColor.WHITE ),
				'd', 5);
		board.setPiece( new ChessPiece( ChessPieceType.KNIGHT, ChessColor.BLACK ),
				'f', 4);
		assertTrue( knightStrategy.canCapture(board, 'f', 4, 'd', 5) );
	}
	
	// add a piece that can be captured by a black king
	@Test
	public void testKingCaptureOnePiece() {
		
		MoveStrategy kingStrategy = stratFac.getStrategy(ChessPieceType.KING, 
				null, board);
		board.setPiece( new ChessPiece( ChessPieceType.BISHOP, ChessColor.WHITE ),
				'd', 5);
		board.setPiece( new ChessPiece( ChessPieceType.KING, ChessColor.BLACK ),
				'c', 5);
		assertTrue( kingStrategy.canCapture(board, 'c', 5, 'd', 5) );
	}
		
	// black king capture but fail because of the same color
	@Test
	public void testKingCaptureOnePieceFail() {
		
		MoveStrategy kingStrategy = stratFac.getStrategy(ChessPieceType.KING, 
				null, board);
		board.setPiece( new ChessPiece( ChessPieceType.BISHOP, ChessColor.BLACK ),
				'd', 5);
		board.setPiece( new ChessPiece( ChessPieceType.KING, ChessColor.BLACK ),
				'c', 5);
		assertFalse( kingStrategy.canCapture(board, 'c', 5, 'd', 5) );
	}
		
	// a piece pretends to be a black king 
	@Test
	public void testFakeKingCaptureOnePieceFail() {
		
		MoveStrategy kingStrategy = stratFac.getStrategy(ChessPieceType.KING, 
				null, board);
		board.setPiece( new ChessPiece( ChessPieceType.BISHOP, ChessColor.WHITE ),
				'd', 5);
		board.setPiece( new ChessPiece( ChessPieceType.QUEEN, ChessColor.BLACK ),
				'c', 5);
		assertFalse( kingStrategy.canCapture(board, 'c', 5, 'd', 5) );
	}
		
	//try different position by using a black king
	@Test
	public void testKingCaptureOnePieceFromE5toD5() {
		
		MoveStrategy kingStrategy = stratFac.getStrategy(ChessPieceType.KING, 
				null, board);
		board.setPiece( new ChessPiece( ChessPieceType.BISHOP, ChessColor.WHITE ),
				'd', 5);
		board.setPiece( new ChessPiece( ChessPieceType.KING, ChessColor.BLACK ),
				'e', 5);
		assertTrue( kingStrategy.canCapture(board, 'e', 5, 'd', 5) );
	}

	// add a piece that can be captured by a black queen
	@Test
	public void testQueenCaptureOnePiece() {
		
		MoveStrategy queenStrategy = stratFac.getStrategy(ChessPieceType.QUEEN, 
				null, board);
		board.setPiece( new ChessPiece( ChessPieceType.BISHOP, ChessColor.WHITE ),
				'd', 5);
		board.setPiece( new ChessPiece( ChessPieceType.QUEEN, ChessColor.BLACK ),
				'c', 5);
		assertTrue( queenStrategy.canCapture(board, 'c', 5, 'd', 5) );
	}
			
	// black queen capture but fail because of the same color
	@Test
	public void testQueenCaptureOnePieceFail() {
		
		MoveStrategy queenStrategy = stratFac.getStrategy(ChessPieceType.QUEEN, 
				null, board);
		board.setPiece( new ChessPiece( ChessPieceType.BISHOP, ChessColor.BLACK ),
				'd', 5);
		board.setPiece( new ChessPiece( ChessPieceType.KING, ChessColor.BLACK ),
				'c', 5);
		assertFalse( queenStrategy.canCapture(board, 'c', 5, 'd', 5) );
	}
			
	// a piece pretends to be a black queen 
	@Test
	public void testFakeQueenCaptureOnePieceFail() {
		
		MoveStrategy queenStrategy = stratFac.getStrategy(ChessPieceType.QUEEN, 
				null, board);
		board.setPiece( new ChessPiece( ChessPieceType.BISHOP, ChessColor.WHITE ),
				'd', 5);
		board.setPiece( new ChessPiece( ChessPieceType.KING, ChessColor.BLACK ),
				'c', 5);
		assertFalse( queenStrategy.canCapture(board, 'c', 5, 'd', 5) );
	}
			
	//try different position by using a black queen
	@Test
	public void testQueenCaptureOnePieceFromE5toD5() {
		
		MoveStrategy queenStrategy = stratFac.getStrategy(ChessPieceType.QUEEN, 
				null, board);
		board.setPiece( new ChessPiece( ChessPieceType.BISHOP, ChessColor.WHITE ),
				'd', 5);
		board.setPiece( new ChessPiece( ChessPieceType.QUEEN, ChessColor.BLACK ),
				'f', 5);
		queenStrategy.canCapture(board, 'f', 5, 'd', 5);
		assertTrue( queenStrategy.canCapture(board, 'f', 5, 'd', 5) );
	}
	
	// add a piece that can be captured by a black bishop
	@Test
	public void testBishopCaptureOnePiece() {
		
		MoveStrategy bishopStrategy = stratFac.getStrategy(ChessPieceType.BISHOP, 
				null, board);
		board.setPiece( new ChessPiece( ChessPieceType.BISHOP, ChessColor.WHITE ),
				'd', 5);
		board.setPiece( new ChessPiece( ChessPieceType.BISHOP, ChessColor.BLACK ),
				'f', 3);
		assertTrue( bishopStrategy.canCapture(board, 'f', 3, 'd', 5) );
	}
				
	// black queen capture but fail because of the same color
	@Test
	public void testBishopCaptureOnePieceFail() {
		
		MoveStrategy bishopStrategy = stratFac.getStrategy(ChessPieceType.BISHOP, 
				null, board);
		board.setPiece( new ChessPiece( ChessPieceType.BISHOP, ChessColor.BLACK ),
				'd', 5);
		board.setPiece( new ChessPiece( ChessPieceType.BISHOP, ChessColor.BLACK ),
				'f', 3);
		assertFalse( bishopStrategy.canCapture(board, 'f', 3, 'd', 5) );
	}
				
	// a piece pretends to be a black queen 
	@Test
	public void testFakeBishopCaptureOnePieceFail() {
		
		MoveStrategy bishopStrategy = stratFac.getStrategy(ChessPieceType.BISHOP, 
				null, board);
		board.setPiece( new ChessPiece( ChessPieceType.BISHOP, ChessColor.WHITE ),
				'd', 5);
		board.setPiece( new ChessPiece( ChessPieceType.KING, ChessColor.BLACK ),
				'f', 3);
		assertFalse( bishopStrategy.canCapture(board, 'f', 3, 'd', 5) );
	}
				
	//try different position by using a black queen
	@Test
	public void testBishopCaptureOnePieceFromC6toD5() {
		
		MoveStrategy bishopStrategy = stratFac.getStrategy(ChessPieceType.BISHOP, 
				null, board);
		board.setPiece( new ChessPiece( ChessPieceType.BISHOP, ChessColor.WHITE ),
				'd', 5);
		board.setPiece( new ChessPiece( ChessPieceType.BISHOP, ChessColor.BLACK ),
				'c', 6);
		assertTrue( bishopStrategy.canCapture(board, 'c', 6, 'd', 5) );
	}
	
	// add a piece that can be captured by a black rook
	@Test
	public void testRookCaptureOnePiece() {
		
		MoveStrategy rookStrategy = stratFac.getStrategy(ChessPieceType.ROOK, 
				null, board);
		board.setPiece( new ChessPiece( ChessPieceType.BISHOP, ChessColor.WHITE ),
				'd', 5);
		board.setPiece( new ChessPiece( ChessPieceType.ROOK, ChessColor.BLACK ),
				'd', 3);
		assertTrue( rookStrategy.canCapture(board, 'd', 3, 'd', 5) );
	}
					
	// black rook capture but fail because of the same color
	@Test
	public void testRookCaptureOnePieceFail() {
		
		MoveStrategy rookStrategy = stratFac.getStrategy(ChessPieceType.ROOK, 
				null, board);
		board.setPiece( new ChessPiece( ChessPieceType.BISHOP, ChessColor.BLACK ),
				'd', 5);
		board.setPiece( new ChessPiece( ChessPieceType.ROOK, ChessColor.BLACK ),
				'd', 3);
		assertFalse( rookStrategy.canCapture(board, 'd', 3, 'd', 5) );
	}
					
	// a piece pretends to be a black rook
	@Test
	public void testFakeRookCaptureOnePieceFail() {
		
		MoveStrategy rookStrategy = stratFac.getStrategy(ChessPieceType.ROOK, 
				null, board);
		board.setPiece( new ChessPiece( ChessPieceType.BISHOP, ChessColor.WHITE ),
				'd', 5);
		board.setPiece( new ChessPiece( ChessPieceType.KING, ChessColor.BLACK ),
				'd', 3);
		assertFalse( rookStrategy.canCapture(board, 'd', 3, 'd', 5) );
	}
					
	//try different position by using a black rook
	@Test
	public void testRookCaptureOnePieceFromC6toD5() {
		
		MoveStrategy rookStrategy = stratFac.getStrategy(ChessPieceType.ROOK, 
				null, board);
		board.setPiece( new ChessPiece( ChessPieceType.BISHOP, ChessColor.WHITE ),
				'd', 5);
		board.setPiece( new ChessPiece( ChessPieceType.ROOK, ChessColor.BLACK ),
				'd', 7);
		assertTrue( rookStrategy.canCapture(board, 'd', 7, 'd', 5) );
	}

}
