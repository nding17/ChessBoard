import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BoardLayout {
	private Board board;
	
	@Before
	public void setup(){
		board = new Board(new TraditionalStart());
	}
	
	@Test
	public void testWhiteRookAtA1() {
		ChessPiece piece = board.pieceAt('a', 1);
		assertTrue(piece.getColor()==ChessColor.WHITE && piece.getType()==ChessPieceType.ROOK);
	}
	@Test
	public void testWhiteKnightAtB1() {
		ChessPiece piece = board.pieceAt('b', 1);
		assertTrue(piece.getColor()==ChessColor.WHITE && piece.getType()==ChessPieceType.KNIGHT);
	}
	@Test
	public void testWhiteBishopAtC1() {
		ChessPiece piece = board.pieceAt('c', 1);
		assertTrue(piece.getColor()==ChessColor.WHITE && piece.getType()==ChessPieceType.BISHOP);
	}
	@Test
	public void testWhiteQueenAtD1() {
		ChessPiece piece = board.pieceAt('d', 1);
		assertTrue(piece.getColor()==ChessColor.WHITE && piece.getType()==ChessPieceType.QUEEN);
	}
	@Test
	public void testWhiteKingAtE1() {
		ChessPiece piece = board.pieceAt('e', 1);
		assertTrue(piece.getColor()==ChessColor.WHITE && piece.getType()==ChessPieceType.KING);
	}
	@Test
	public void testWhiteBishopAtF1() {
		ChessPiece piece = board.pieceAt('f', 1);
		assertTrue(piece.getColor()==ChessColor.WHITE && piece.getType()==ChessPieceType.BISHOP);
	}
	@Test
	public void testWhiteKnightAtG1() {
		ChessPiece piece = board.pieceAt('g', 1);
		assertTrue(piece.getColor()==ChessColor.WHITE && piece.getType()==ChessPieceType.KNIGHT);
	}
	@Test
	public void testWhiteRookAtH1() {
		ChessPiece piece = board.pieceAt('h', 1);
		assertTrue(piece.getColor()==ChessColor.WHITE && piece.getType()==ChessPieceType.ROOK);
	}
	
	
	@Test
	public void testBlackRookAtA8() {
		ChessPiece piece = board.pieceAt('a', 8);
		assertTrue(piece.getColor()==ChessColor.BLACK && piece.getType()==ChessPieceType.ROOK);
	}
	@Test
	public void testBlackKnightAtB8() {
		ChessPiece piece = board.pieceAt('b', 8);
		assertTrue(piece.getColor()==ChessColor.BLACK && piece.getType()==ChessPieceType.KNIGHT);
	}
	@Test
	public void testBlackBishopAtC8() {
		ChessPiece piece = board.pieceAt('c', 8);
		assertTrue(piece.getColor()==ChessColor.BLACK && piece.getType()==ChessPieceType.BISHOP);
	}
	@Test
	public void testBlackQueenAtD8() {
		ChessPiece piece = board.pieceAt('d', 8);
		assertTrue(piece.getColor()==ChessColor.BLACK && piece.getType()==ChessPieceType.QUEEN);
	}
	@Test
	public void testBlackKingAtE8() {
		ChessPiece piece = board.pieceAt('e', 8);
		assertTrue(piece.getColor()==ChessColor.BLACK && piece.getType()==ChessPieceType.KING);
	}
	@Test
	public void testBlackBishopAtF8() {
		ChessPiece piece = board.pieceAt('f', 8);
		assertTrue(piece.getColor()==ChessColor.BLACK && piece.getType()==ChessPieceType.BISHOP);
	}
	@Test
	public void testBlackKnightAtG8() {
		ChessPiece piece = board.pieceAt('g', 8);
		assertTrue(piece.getColor()==ChessColor.BLACK && piece.getType()==ChessPieceType.KNIGHT);
	}
	@Test
	public void testBlackRookAtH8() {
		ChessPiece piece = board.pieceAt('h', 8);
		assertTrue(piece.getColor()==ChessColor.BLACK && piece.getType()==ChessPieceType.ROOK);
	}
	
	@Test
	public void testWhitePawns(){
		for (char i = 'a'; i<='h'; i++){
			ChessPiece piece = board.pieceAt(i, 2);
			assertTrue(piece.getColor()==ChessColor.WHITE && piece.getType()==ChessPieceType.PAWN);
		}
	}
	
	@Test
	public void testBlackPawns(){
		for (char i = 'a'; i<='h'; i++){
			ChessPiece piece = board.pieceAt(i, 7);
			assertTrue(piece.getColor()==ChessColor.BLACK && piece.getType()==ChessPieceType.PAWN);
		}
	}

}
