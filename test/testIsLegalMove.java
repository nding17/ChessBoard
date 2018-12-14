import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class testIsLegalMove {

	private MoveStrategyFactory stratFac;
	private Board board;
	private BoardInitializer boardInitializer;
	ChessPieceType bishop=ChessPieceType.BISHOP;
	ChessPieceType pawn=ChessPieceType.PAWN;
	ChessPieceType queen=ChessPieceType.QUEEN;
	ChessPieceType knight=ChessPieceType.KNIGHT;
	ChessPieceType rook=ChessPieceType.ROOK;
	ChessColor white=ChessColor.WHITE;
	ChessColor black=ChessColor.BLACK;

	
	@Before
	public void setUp(){
		boardInitializer=new TraditionalStart();
		board=new Board(boardInitializer);
		stratFac = new MoveStrategyFactory();
	}
	
	@Test
	public void testIsLegalMoveForRook() {
		
		board.setPiece(new ChessPiece( ChessPieceType.BISHOP, ChessColor.WHITE ), 
				'a', 3);
		board.setPiece(new ChessPiece( ChessPieceType.ROOK, ChessColor.BLACK ), 
				'a', 6);
		assertEquals(board.isMoveLegal('a', 6, 'a', 3),1);
	}

	@Test
	public void test1(){
		
		board.setPiece(new ChessPiece(queen, white), 'h', 5);
		assertEquals(board.isMoveLegal('h', 5, 'f', 7),1);
	}
	@Test
	public void test2(){//hi
		
		board.setPiece(new ChessPiece(queen, white), 'h', 5);
		assertEquals(board.isMoveLegal('h', 5, 'e', 2),0);
	}
	
	@Test
	public void test3(){//hi
		
		board.setPiece(new ChessPiece(pawn, white), 'h', 6);
		assertEquals(board.isMoveLegal('h', 6, 'g', 7),1);
	}
	@Test
	public void test4(){
		
		board.setPiece(new ChessPiece(knight, white), 'h', 6);
		assertEquals(board.isMoveLegal('h', 6, 'g', 8),1);
	}
	@Test
	public void test5(){
		
		board.setPiece(new ChessPiece(rook, white), 'h', 6);
		assertEquals(board.isMoveLegal('h', 6, 'h', 2),0);
	}

	@Test
	public void test6(){
		
		board.setPiece(new ChessPiece(rook, black), 'h', 6);
		assertEquals(board.isMoveLegal('h', 6, 'h', 2),1);
	}
	@Test
	public void test7(){
		
		board.setPiece(new ChessPiece(rook, black), 'c', 6);
		assertEquals(board.isMoveLegal('c', 6, 'a', 2),0);
	}
	@Test
	public void test8(){
		
		board.setPiece(new ChessPiece(rook, black), 'c', 6);
		assertEquals(board.isMoveLegal('c', 6, 'c', 7),0);
	}
	@Test
	public void test9(){
		
		board.setPiece(new ChessPiece(rook, black), 'c', 6);
		assertEquals(board.isMoveLegal('c', 6, 'c', 1),0);
	}
	@Test
	public void test10(){
		
		board.setPiece(new ChessPiece(rook, black), 'c', 6);
		assertEquals(board.isMoveLegal('c', 6, 'c', 2),1);
	}
	
	@Test
	public void test11(){
		
		board.setPiece(new ChessPiece(pawn, black), 'c', 3);
		assertEquals(board.isMoveLegal('c', 3, 'b', 2),1);
	}
	@Test
	public void test12(){
		
		board.setPiece(new ChessPiece(pawn, black), 'c', 3);
		assertEquals(board.isMoveLegal('c', 3, 'c', 2),0);
	}
	@Test
	public void test13(){
		
		board.setPiece(new ChessPiece(pawn, white), 'c', 6);
		assertEquals(board.isMoveLegal('c', 6, 'd', 7),1);
	}
	@Test
	public void test14(){
		
		board.setPiece(new ChessPiece(queen, black), 'b', 5);
		board.setPiece(new ChessPiece(pawn, white), 'c', 6);
		assertEquals(board.isMoveLegal('c', 6, 'b', 5),0);
	}
	
	@Test
	public void test15(){
		board.setPiece(new ChessPiece(pawn, white), 'c', 5);
		assertEquals(board.isMoveLegal('c', 5, 'b', 6),0);
	}
	
	@Test
	public void test16(){
		board.setPiece(new ChessPiece(pawn, white), 'c', 2);
		assertEquals(board.isMoveLegal('c', 2, 'b', 4),0);
	}
	
}
