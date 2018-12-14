import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BishopStrategyCollisionTest {

	private Board board;
	private BoardInitializer boardInitializer;
	private MoveStrategy bishopStrategy;
	
	
	@Before
	public void setUp(){
		boardInitializer=new TraditionalStart();
		board=new Board(boardInitializer);
		bishopStrategy=new BishopStrategy(board);
	}
	
	
	@Test
	public void testBishopCollisionFromC1toE3() {
		
		assertTrue(bishopStrategy.checkCollisionOnWay(board, 'c', 1, 'e', 3));
	}
	
	@Test
	public void testBishopCollisionFromD1toF3() {
		
		assertTrue(bishopStrategy.checkCollisionOnWay(board, 'd', 1, 'f', 3));
	}
	
	//NE no pieces blocking in the way
	@Test
	public void testBishopNoCollisionFromC3toF5(){
		
		assertFalse(bishopStrategy.checkCollisionOnWay(board, 'c', 3, 'f', 5));

	}
	
	//NE one pieces blocking in the way
	@Test
	public void testBishopNoCollisionFromA1toH8(){
		
		assertTrue(bishopStrategy.checkCollisionOnWay(board, 'a', 1, 'h', 8));

	}
	
	//NE one piece blocking in the way
	@Test
	public void testBishopCollisionFromC3toF5(){
		
		board.setPiece(new ChessPiece(ChessPieceType.QUEEN, ChessColor.WHITE), 'd', 4);
		assertTrue(bishopStrategy.checkCollisionOnWay(board, 'c', 3, 'f', 5));
	}
	
	//NW no piece blocking in the way
	@Test
	public void testBishopNoCollisionFromC3toA5(){
		
		assertFalse(bishopStrategy.checkCollisionOnWay(board, 'c', 3, 'a', 5));
	}
	
	//NW no piece blocking in the way
	@Test
	public void testBishopCollisionFromC1toA3(){
		
		assertTrue(bishopStrategy.checkCollisionOnWay(board, 'c', 1, 'a', 3));
	}
	
	//NW no piece blocking in the way
	@Test
	public void testBishopCollisionFromD1toB3(){
		
		assertTrue(bishopStrategy.checkCollisionOnWay(board, 'd', 1, 'b', 3));
	}
	
	//NW one piece blocking in the way
	@Test
	public void testBishopCollisionFromC3toA5(){
		
		board.setPiece(new ChessPiece(ChessPieceType.QUEEN, ChessColor.WHITE), 'b', 4);
		assertTrue(bishopStrategy.checkCollisionOnWay(board, 'c', 3, 'a', 5));
	}
	
	//SE no piece blocking in the way
	
	@Test
	public void testBishopCollisionFromD3toF1(){
		
		assertTrue(bishopStrategy.checkCollisionOnWay(board, 'd', 3, 'f', 1));
	}
	
	//SE no piece blocking in the way
	@Test
	public void testBishopNoCollisionFromD5toF3(){
		
		assertFalse(bishopStrategy.checkCollisionOnWay(board, 'd', 5, 'f', 3));
	}
	
	//SE one piece blocking in the way
	@Test
	public void testBishopCollisionFromD5toF3(){
		
		board.setPiece(new ChessPiece(ChessPieceType.QUEEN, ChessColor.WHITE), 'e', 4);
		assertTrue(bishopStrategy.checkCollisionOnWay(board, 'd', 5, 'f', 3));
	}
	
	//SE one piece blocking in the way
	@Test
	public void testBishopCollisionFromA5toE1(){
		
		assertTrue(bishopStrategy.checkCollisionOnWay(board, 'a', 5, 'e', 1));
	}
	
	//SW no piece blocking the way
	@Test
	public void testBishopNoCollisionFromD5toB3(){
		
		assertFalse(bishopStrategy.checkCollisionOnWay(board, 'd', 5, 'b', 3));
	}
	
	//SW one piece blocking the way
	@Test
	public void testBishopCollisionFromC3toA1(){
		
		assertTrue(bishopStrategy.checkCollisionOnWay(board, 'c', 3, 'a', 1));
	}
	
	//SW one piece blocking the way
	@Test
	public void testBishopCollisionFromD5toB3(){
		
		board.setPiece(new ChessPiece(ChessPieceType.QUEEN, ChessColor.WHITE), 'c', 4);
		assertTrue(bishopStrategy.checkCollisionOnWay(board, 'd', 5, 'b', 3));
	}
	
	@Test
	public void testBishopCollisionFromA1toH8(){
		
		assertTrue(bishopStrategy.checkCollisionOnWay(board, 'a', 1, 'h', 8));
	}
	
	@Test
	public void testBishopCollisionFromA1toH8NoBoardSetUp(){
		board= new Board();
		bishopStrategy=new BishopStrategy(board);
		assertFalse(bishopStrategy.checkCollisionOnWay(board, 'a', 1, 'h', 8));
	}
	
	@Test
	public void testBishopCollisionFromH8toA1(){
		
		assertTrue(bishopStrategy.checkCollisionOnWay(board, 'h', 8, 'a', 1));
	}
	
	@Test
	public void testBishopCollisionFromH5toE2(){
		
		assertFalse(bishopStrategy.checkCollisionOnWay(board, 'h', 5, 'e', 2));
	}
	
	// two pieces get in the way
	@Test
	public void testBishopCollisionFromH5toE2twoPiecesGetInTheWay(){
		
		board.setPiece(new ChessPiece(ChessPieceType.QUEEN, 
				ChessColor.WHITE), 'g', 4);
		board.setPiece(new ChessPiece(ChessPieceType.QUEEN, 
				ChessColor.WHITE), 'f', 3);
		assertTrue(bishopStrategy.checkCollisionOnWay(board, 'h', 5, 'e', 2));
	}
	
	@Test
	public void testBishopCollisionFromE2toH5(){
		
		assertFalse(bishopStrategy.checkCollisionOnWay(board, 'e', 2, 'h', 5));
	}
}
