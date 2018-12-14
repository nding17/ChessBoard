import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RookStrategyTest {

	private MoveStrategy rook;
	
	@Before
	public void setUp(){
		rook = new RookStrategy( null );
	}
	
	@Test
	public void testRookA1toA2() {
		assertTrue( rook.isPatternLegal('a', 1, 'a', 2, null) );
	}
	
	@Test
	public void testRookB1toC1() {
		assertTrue( rook.isPatternLegal('b', 1, 'c', 1, null) );
	}
	
	@Test
	public void testRookD1toC1() {
		assertTrue( rook.isPatternLegal('d', 1, 'c', 1, null) );
	}
	
	@Test
	public void testRookF5toF4() {
		assertTrue( rook.isPatternLegal('f', 5, 'f', 4, null) );
	}
	
	@Test
	public void testRookF5toD4() {
		assertFalse( rook.isPatternLegal('f', 5, 'd', 4, null) );
	}

	@Test
	public void testRookF5toG4() {
		assertFalse( rook.isPatternLegal('f', 5, 'g', 4, null) );
	}
	
}
