import static org.junit.Assert.*;
import org.junit.*;
import org.junit.Test;

public class BoardTest {
	Board board;
	
	@Before
	public void intitialize(){
		board=new Board();
	}
	@Test
	public void testGetPieceAtA1() {
		ChessPiece piece=new ChessPiece(ChessPieceType.PAWN, ChessColor.WHITE);
		board.setPiece(piece, 'a', 1);
		assertEquals(board.pieceAt('a', 1), piece);
	}
}
