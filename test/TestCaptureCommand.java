import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestCaptureCommand {

	private Board board;
	private Capture capture;
	private ChessPiece capturing;
	private ChessPiece captured;

	@Before
	public void setup(){
		board = new Board(new TraditionalStart());
		capturing = board.pieceAt('a', 1);
		captured = board.pieceAt('a', 8);
		capture = new Capture(board,'a',1,'a',8);
		capture.execute();
	}
	
	@Test
	public void testCapture() {
		assertEquals(board.pieceAt('a', 8),capturing);
		assertNull(board.pieceAt('a', 1));
	}
	
	@Test
	public void testCaptureUndo(){
		capture.undo();
		assertEquals(board.pieceAt('a', 8), captured);
		assertEquals(board.pieceAt('a', 1), capturing);
	}

}
